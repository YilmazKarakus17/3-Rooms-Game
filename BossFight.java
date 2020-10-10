import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.io.*;
import java.util.ArrayList;
/**
 * Write a description of class BossFight here.
 *
 * @author (yk)
 * @version (26/03/2020)
 */
public class BossFight extends JFrame
{
    public BossFight(Hero Player, Character Boss, RoomCreator currentRoom, gameOutputPanel gameOutput, GameIO.GamePanel game)
    {
        super("Boss Fight");
        BossFightPanel panel = new BossFightPanel(Player,Boss,currentRoom,gameOutput,game);
        add(panel);
        pack();
        setVisible(true);
    }
    /********** Inner panel class ************/
    public class BossFightPanel extends JPanel implements ActionListener
    {
        /********* Instance Variables *********/
        //Important variables
        private Hero Player;
        private Character Boss;
        private GameIO.GamePanel game;
        private RoomCreator currentRoom;
        private gameOutputPanel gameOutput;
        //gui instance variables
        private Image Background = (new ImageIcon("bossFightBackground.png").getImage());
        private bossCombatButtons primaryAttackButton;
        private bossCombatButtons secondaryAttackButton;
        private JTextArea listActions;
        private JScrollPane scrollbar;
        /********* Constructors *********/
        public BossFightPanel(Hero Player, Character Boss, RoomCreator currentRoom, gameOutputPanel gameOutput, GameIO.GamePanel game)
        {
            this.Player = Player;
            this.game = game;
            this.currentRoom = currentRoom;
            this.gameOutput = gameOutput;
            this.Boss = Boss;
            //setting layout
            setLayout(null);
            //adding the components
            createSize();
            createInfoDisplayingComponents();        
            createButtons();
        }
        //setting the dimensions
        public void createSize()
        {
            Dimension imageSize = new Dimension(Background.getWidth(null), Background.getHeight(null));
            setSize(imageSize);
            setMaximumSize(imageSize);
            setMinimumSize(imageSize);
            setPreferredSize(imageSize);
        }
        //creates field are where information will be stored on
        public void createInfoDisplayingComponents()
        {
            listActions = new JTextArea();
            listActions.append("----------------------------------");
            listActions.append("BOSS FIGHT!" + "\n");
            listActions.append("BOSS HP:" + Boss.getHp() + "\n");
            listActions.append("BOSS Primary Attack:" + Boss.primaryAttack() + "\n");

            listActions.setEditable(false);
            scrollbar = new JScrollPane(listActions);
            scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setBounds(20, 100, 510,150);
            this.add(scrollbar);
        }
        //creates and Positions the Buttons
        public void createButtons()
        {
            //Primary Attack Button
            primaryAttackButton = new bossCombatButtons("Primary");
            primaryAttackButton.addActionListener(this);
            primaryAttackButton.setBackground(Color.YELLOW);
            primaryAttackButton.setBounds(20, 280, 250, 100);
            add(primaryAttackButton);
            //Secondary Attack Button
            secondaryAttackButton = new bossCombatButtons("Secondary");
            secondaryAttackButton.addActionListener(this);
            secondaryAttackButton.setBackground(Color.ORANGE);
            secondaryAttackButton.setBounds(280, 280, 250, 100);
            add(secondaryAttackButton);
        }
        //adds to the text area
        public void appendTextField(String Message)
        {
            listActions.append("\n" + Message + "\n");
        }
        //overriding painComponent method to set the background
        public void paintComponent(Graphics g)
        {
            g.drawImage(Background,0,0,null); 
        }
               
        public void killedDeath()
        {
            appendTextField("You Killed the BOSS!");
            CombatMechanics.enemyDead(Player, Boss);
            game.playerWins();
            
        }
        /**************** Implemented method for ActionListener Interface ******************/
        public void actionPerformed(ActionEvent evt)
        {
            String Attack = evt.getActionCommand();
            if (Attack.equals("Primary"))
            {
               try
                {
                    if(Boss.getHp() <=0)
                    {
                        killedDeath();
                    }
                    else 
                    {   
                        CombatMechanics.PrimaryAttack(Player, Boss);
                        appendTextField("Your Primary Attack Reduces DEATH's Hp Down to: " + Boss.getHp());
                        //edit stats
                        if(Boss.getHp() > 0) //if Boss is not dead
                        {
                            Player.takeDamage(Boss.primaryAttack());
                            this.appendTextField("Death Primary Attacks You, as a result your Hp has been reduced down to: " + Player.getHp());
                            Player.StaminaRegen();
                            this.appendTextField("Death attack has given you to regenerate your stamina ");
                            if(Player.getHp() <=0) //if player dies from Death attack
                            {
                                game.playerDiesBossFight();
                            }
                        }
                        else{
                            killedDeath();
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
                    if(Boss.getHp() <=0)
                    {
                        killedDeath();
                    }
                    else 
                    {   
                        CombatMechanics.SecondaryAttack(Player, Boss);
                        appendTextField("Your Secondary Attack Reduces Enemy Hp Down to: "+Boss.getHp());
                        //edit stats
                        if(Boss.getHp() > 0) //if Boss is not dead
                        {
                            Player.takeDamage(Boss.secondaryAttack());
                            this.appendTextField("Death Secondary Attacks You, as a result your Hp has been reduced down to: " + Player.getHp());
                            Player.StaminaRegen();
                            this.appendTextField("Death attack has given you to regenerate your stamina ");
                            if(Player.getHp() <=0) //if player dies from Death attack
                            {
                                game.playerDiesBossFight();
                            }
                        }
                        else{
                            killedDeath();
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
        public class bossCombatButtons extends JButton
        {
            public bossCombatButtons(String Message)
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
}