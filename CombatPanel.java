import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.io.*;
import java.util.ArrayList;
/**
 * Class which acts as a combat IO between player and a 
 *
 * @author (YK)
 * @version (26/05/2020)
 */
public class CombatPanel extends JPanel implements ActionListener
{
    /********* Instance Variables *********/
    //Important variables
    private Hero Player;
    private Character Villain;
    private int attackCounter;
    private GameIO.GamePanel game;
    private RoomCreator currentRoom;
    private gameOutputPanel gameOutput;
    //gui instance variables
    private Image combatBackground = (new ImageIcon("combatBackground.png").getImage());
    private combatButtons primaryAttackButton;
    private combatButtons secondaryAttackButton;
    private JTextArea listActions;
    private JScrollPane scrollbar;
    private JLabel counterLabel;
    /********* Constructors *********/
    public CombatPanel(Hero Player, RoomCreator currentRoom, gameOutputPanel gameOutput, GameIO.GamePanel game)
    {
        this.Player = Player;
        this.attackCounter = 0;
        this.game = game;
        this.currentRoom = currentRoom;
        this.gameOutput = gameOutput;
        //setting layout
        setLayout(null);
        //adding the components
        createSize();
        createInfoDisplayingComponents();        
        createButtons();
    }
    //for when initializing next combat
    public void nextFight(Character Villain)
    {
         this.Villain = Villain;
         
         //output message
         listActions.append(" "+"\n");
         listActions.append("Your Enemy: " + Villain.getName() +"\n");
         listActions.append("Your Enemy Hp: " + Villain.getHp() +"\n");
    }
    //setting the dimensions
    public void createSize()
    {
        Dimension imageSize = new Dimension(combatBackground.getWidth(null), combatBackground.getHeight(null));
        setSize(imageSize);
        setMaximumSize(imageSize);
        setMinimumSize(imageSize);
        setPreferredSize(imageSize);
    }
    //creates field are where information will be stored on
    public void createInfoDisplayingComponents()
    {
        listActions = new JTextArea();
        listActions.append("This is your combat simulator");
        listActions.setEditable(false);
        scrollbar = new JScrollPane(listActions);
        scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollbar.setBounds(20, 100, 510,150);
        this.add(scrollbar);
        //adding counter label
        counterLabel = new JLabel("COMBO:" + attackCounter);
        counterLabel.setFont(new java.awt.Font("ITALIC", Font.BOLD, 15));
        counterLabel.setForeground(Color.WHITE);
        counterLabel.setSize(100,50);
        counterLabel.setBounds(20,50,100,50);
        this.add(counterLabel);
    }
    //creates and Positions the Buttons
    public void createButtons()
    {
        //Primary Attack Button
        primaryAttackButton = new combatButtons("Primary");
        primaryAttackButton.addActionListener(this);
        primaryAttackButton.setBackground(Color.YELLOW);
        primaryAttackButton.setBounds(20, 280, 250, 100);
        add(primaryAttackButton);
        //Secondary Attack Button
        secondaryAttackButton = new combatButtons("Secondary");
        secondaryAttackButton.addActionListener(this);
        secondaryAttackButton.setBackground(Color.ORANGE);
        secondaryAttackButton.setBounds(280, 280, 250, 100);
        add(secondaryAttackButton);
    }
    //adds to the text area
    public void appendTextField(String Message)
    {
        listActions.append(Message + "\n");
    }
    //overriding painComponent method to set the background
    public void paintComponent(Graphics g)
    {
        g.drawImage(combatBackground,0,0,null); 
    }
    //method for when enemy is killed
    public void killedEnemy()
    {
        appendTextField("You Killed the Enemy!");
        attackCounter = 0;
        counterLabel.setText("COMBO:" + attackCounter);
        CombatMechanics.enemyDead(Player, Villain);
        game.changeCombatStatus();
    }
    //for when player chooses primary attack
    public void chosenPrimaryAttack() throws InsufficientStaminaException
    {
        CombatMechanics.PrimaryAttack(Player, Villain);
        appendTextField("Your Primary Attack Reduces Enemy Hp Down to: " + Villain.getHp());
        //edit stats
        attackCounter = attackCounter + 1;
        counterLabel.setText("COMBO:" + attackCounter); 
    }
    //for when player chooses secondary attack
    public void chosenSecondaryAttack() throws InsufficientStaminaException
    {
        CombatMechanics.SecondaryAttack(Player, Villain);
        appendTextField("Your Secondary Attack Reduces Enemy Hp Down to: "+Villain.getHp());
        //edit stats
        attackCounter = attackCounter + 1;
        counterLabel.setText("COMBO:" + attackCounter);
    }
    //when villain attacks
    public void villainAttack()
    {
        CombatMechanics.villainAttack(Villain, Player, this);
        attackCounter = 0;
        counterLabel.setText("COMBO:" + attackCounter);
    }
    /**************** Implemented method for ActionListener Interface ******************/
    public void actionPerformed(ActionEvent evt)
    {
        String Attack = evt.getActionCommand();
        if (Attack.equals("Primary"))
        {
           try
            {
                if(Villain.getHp() <=0)
                {
                    killedEnemy();
                }
                else 
                {   
                    chosenPrimaryAttack();
                    if(Villain.getHp() > 0) //if villain is not dead
                    {
                        if(CombatMechanics.checkIfEnemyAttacks(attackCounter, Player.getSpeed()) || (Player.getStamina()<Player.getPrimaryStaminaReduction()) || (Player.getStamina()<Player.getSecondaryStaminaReduction()))
                        {              
                            villainAttack();
                            if(Player.getHp() <=0) //if player dies from villain attack
                            {
                                game.playerDies();
                            }
                        }
                    }
                    else{
                        killedEnemy();
                    }
                } 
            }
            catch(InsufficientStaminaException e)
            {
                appendTextField(e.getMessage());
            } 
        }
        else if (Attack.equals("Secondary"))
        {
            try
            {
                if(Villain.getHp() <=0)
                {
                    killedEnemy();
                }
                else 
                {   
                    chosenSecondaryAttack();
                    if(Villain.getHp() > 0) //if villain is not dead
                    {
                        if(CombatMechanics.checkIfEnemyAttacks(attackCounter, Player.getSpeed()) || (Player.getStamina()<Player.getPrimaryStaminaReduction()) || (Player.getStamina()<Player.getSecondaryStaminaReduction()))
                        {             
                            villainAttack();
                            if(Player.getHp() <=0) //if player dies from villain attack
                            {
                                game.playerDies();
                            }
                        }
                    }
                    else{
                        killedEnemy();
                    }
                }
            }
            catch(InsufficientStaminaException e)
            {
                appendTextField(e.getMessage());
            }    
        }
    }
    /*************************************** Inner Class for custom Buttons ***********************************/
    public class combatButtons extends JButton
    {
        public combatButtons(String Message)
        {
            super(Message);
            setSize(150, 100);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 30));
            setBorder(BorderFactory.createRaisedBevelBorder());            
        }
    }
}
