import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer.*;
import java.io.*;

/**
 * This class is the main game class, where the frame containing the rooms and all the options the game has to offer
 *
 * @author (YK)
 * @version (25/03/2020)
 */
public class GameIO extends JFrame
{
    /********* Instance Variables *********/
    private Hero Player;
    private String FileName;
    //room variables
    private RoomCreator currentRoom;
    private gameOutputPanel currentRoomGUI;
    private final int RoomsAllowed = 3;
    private int RoomNumber;    
    /********* Constructors *********/
    //Constructor for when the game starts
    public GameIO(Hero Player, String FileName)
    {
        super("Game");
        this.RoomNumber = 0;
        this.Player = Player;
        this.FileName = FileName;
        
        GamePanel gamePanel = new GamePanel(Player, FileName, this);
        add(gamePanel);
        
        pack();   
        setResizable(false);
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //changes the panel to another instance of the panel GamePanel when next room is pressed
    public void changeRoom()
    {
       if(RoomNumber !=3)
       {
           GamePanel newGamePanel = new GamePanel(Player, FileName, this);
           setContentPane(newGamePanel);
       }
       else
       {
           System.out.println("END");
       }
    }
    //changes panel to LostGame
    public void lostGame()
    {
        LostGame panel  = new LostGame();
        setContentPane(panel);
        pack();
    }
    public void winGame()
    {
        WinGame panel  = new WinGame();
        setContentPane(panel);
        pack();
    }
    //gets Room numner
    public int getRoomNumber()
    {
        return this.RoomNumber;
    }
    //gets how many rooms allowed
    public int getRoomsAllowed()
    {
        return this.RoomsAllowed;
    }
    //increments room number
    public void incrementRoomNumber()
    {
        this.RoomNumber++;
    }
    /***************************** Inner Panel  *************************************/
    public class GamePanel extends JPanel implements ActionListener
    {
        /********* Instance Variables *********/
        //Important Variables
        private GameIO Game;
        private Hero Player;
        private String FileName;
        private RoomCreator CurrentRoom;
        private gameOutputPanel gameOutput; 
        private BossFight bf;
        private boolean currentlyInCombat;
        private int roomNum;
        //Images
        private Image gameBackground = (new ImageIcon("GameBackground.png").getImage());
        //Bottom Buttons
        private bottomButtons bossFightButton;
        private bottomButtons shopButton;
        private bottomButtons saveButton;
        private bottomButtons saveExitButton;
        //gameButtons
        private gameInputButtons upButton;
        private gameInputButtons leftButton;
        private gameInputButtons rightButton;
        private gameInputButtons downButton;
        //Combat Panel
        private CombatPanel currentCombatPanel;
        //NextButton
        private JButton nextButton;
        //Labels
        private JLabel roomNumberLabel;
        private JLabel nameLabel;
        private JLabel statsLabel;
        private JLabel outputMessage;
        /********* Constructors *********/
        public GamePanel(Hero Player, String FileName, GameIO Game)
        {
            this.Game = Game;
            this.Player = Player;
            this.FileName = FileName;
            this.currentlyInCombat = false;
            //increments room number to 1
            Game.incrementRoomNumber();
            this.roomNum = Game.getRoomNumber();
            //setting panel Size
            setGamePanelSize();
            //setting layout
            setLayout(null);
            //Create Buttons
            createBottomButtons();
            //creating Next Button
            nextButton = new JButton("Next");
            nextButton.addActionListener(this);
            nextButton.setBounds(1200, 20, 300,70);
            nextButton.setForeground(Color.WHITE);
            nextButton.setBackground(Color.ORANGE);
            nextButton.setFont(new java.awt.Font("ITALIC", Font.BOLD, 35));
            nextButton.setVisible(false);
            add(nextButton);
            //creates Inventory Button
            JButton invenotryButton = new JButton("Inventory");
            invenotryButton.addActionListener(this);
            invenotryButton.setBounds(950, 20, 200,70);
            invenotryButton.setForeground(Color.WHITE);
            invenotryButton.setBackground(Color.DARK_GRAY);
            invenotryButton.setFont(new java.awt.Font("ITALIC", Font.BOLD, 35));
            invenotryButton.setBorder(BorderFactory.createRaisedBevelBorder());
            add(invenotryButton);            
            //adding Labels
            createLabels();
            //createOutputPanel
            createGameOutputPanel();
            //create gameinput
            createGameInput();
            //createCombatPanel
            createCombatPanel();
        }
        //sets the game panel size to the image size
        public void setGamePanelSize()
        {
            Dimension ImageSize = new Dimension(gameBackground.getWidth(null), gameBackground.getHeight(null));
            setSize(ImageSize);
            setPreferredSize(ImageSize);
            setMaximumSize(ImageSize);
            setMinimumSize(ImageSize);
        }
        //creates the game outputPanel
        public void createGameOutputPanel()
        {
            createRoom();
            
            gameOutput = new gameOutputPanel(Player,CurrentRoom, CurrentRoom.getVillains(), this);
            gameOutput.setBounds(20, 120, gameOutput.getWidth(),gameOutput.getHeight());
            add(gameOutput);
        }
        //creates and positions comabt panel
        public void createCombatPanel()
        {
            currentCombatPanel = new CombatPanel(Player, CurrentRoom, gameOutput, this);
            currentCombatPanel.setBounds(gameOutput.getWidth()+30,120, 600,700);
            add(currentCombatPanel);
        }
        //depending on which room user is currently in it creates rooms with varying difficulty
        public void createRoom()
        {
            int possibleAmountOfVillains;
            if(Game.getRoomNumber() == 1)
            {
                possibleAmountOfVillains = 5;
                this.CurrentRoom = new RoomCreator(Player,possibleAmountOfVillains);
                outputSuccessMessage("This Room gave you: " + CurrentRoom.getItem().getInstance());
            }
            else if (Game.getRoomNumber() == 2)
            {
                possibleAmountOfVillains = 8;
                this.CurrentRoom = new RoomCreator(Player,possibleAmountOfVillains);
                outputSuccessMessage("This Room gave you: " + CurrentRoom.getItem().getInstance());
            }
            else if(Game.getRoomNumber() == 3)
            {
                possibleAmountOfVillains = 10;
                this.CurrentRoom = new RoomCreator(Player,possibleAmountOfVillains);
                outputSuccessMessage("This Room gave you: " + CurrentRoom.getItem().getInstance());
            }
            else{
                //means 3 rooms have been played
            }
        }
        //creates game input buttons
        public void createGameInput()
        {
            //upButton
            upButton = new gameInputButtons("UP");
            upButton.addActionListener(this);
            upButton.setBounds(gameOutput.getWidth()+220,gameOutput.getHeight()-80, 200,100);
            add(upButton);          
            //leftButton
            leftButton = new gameInputButtons("LEFT");
            leftButton.addActionListener(this);
            leftButton.setBounds(gameOutput.getWidth()+20,gameOutput.getHeight()+20, 200,100);
            add(leftButton);              
            //rightButton
            rightButton = new gameInputButtons("RIGHT");
            rightButton.addActionListener(this);
            rightButton.setBounds(gameOutput.getWidth()+420,gameOutput.getHeight()+20, 200,100);
            add(rightButton);             
            //downButton          
            downButton = new gameInputButtons("DOWN");
            downButton.addActionListener(this);
            downButton.setBounds(gameOutput.getWidth()+220,gameOutput.getHeight()+20, 200,100);
            add(downButton);               
        }
        
        //creates and positions the buttons
        public void createBottomButtons()
        {          
            //shopButton
            shopButton = new bottomButtons("Shop");
            shopButton.addActionListener(this);
            shopButton.setBounds(20, 800, 200,70);
            add(shopButton); 
            //Save Button
            saveButton = new bottomButtons("Save");
            saveButton.addActionListener(this);
            saveButton.setBounds(270, 800, 200,70);
            add(saveButton);
            //Exit Button
            saveExitButton = new bottomButtons("Exit");
            saveExitButton.addActionListener(this);
            saveExitButton.setBounds(520, 800, 200,70);
            add(saveExitButton);
            //bossFightButton
            bossFightButton = new bottomButtons("Boss Fight");
            bossFightButton.addActionListener(this);
            bossFightButton.setBounds(823, 800, 590,70);
            add(bossFightButton);             
        }
        //creates and positions the labels
        public void createLabels()
        {
            //roomNumberLabel
            roomNumberLabel = new JLabel("Welcome to Room: " + Game.getRoomNumber());
            roomNumberLabel.setBounds(20, 20, 300, 50);
            roomNumberLabel.setForeground(Color.RED);
            roomNumberLabel.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 30));
            add(roomNumberLabel);
            //add Player Name Label
            nameLabel = new JLabel("Hello " + Player.getPlayerInstance() + ", " + Player.getName());
            nameLabel.setBounds(20, 50, 300, 50);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 20));
            add(nameLabel);
            //add Player Stats
            String stats = "Hp:  " + Player.getHp() +  " |  " + "Primary Damage:  " + Player.primaryAttack() + " | " + "Secondary Damage:  " + Player.secondaryAttack() + " | " + "Armour: " + (Player.getArmour()/100) + " | " + "Speed: " + Player.getSpeed() ;
            nameLabel = new JLabel(stats);
            nameLabel.setBounds(20, 70, 900, 50);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 18));
            add(nameLabel);
            //add message output label
            outputMessage = new JLabel("");
            outputMessage.setBounds(120, 720, 700,70);
            outputMessage.setForeground(Color.WHITE);            
            outputMessage.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 30));
            add(outputMessage);            
        }
        //outputs Success message
        public void outputSuccessMessage(String Message)
        {
            outputMessage.setText(Message);
            outputMessage.setForeground(Color.GREEN);
        }
        //outputs error message
        public void outputErrorMessage(String Message)
        {
            outputMessage.setText(Message);
            outputMessage.setForeground(Color.RED);
        }
        //outputs the name of the info of the item received
        public void itemReceivedMessage(String itemInfo)
        {
           outputMessage.setBounds(20, 720, 1200,70);
           outputMessage.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 15));
           outputMessage.setText(itemInfo);
           outputMessage.setForeground(Color.WHITE); 
        }
        //updates the stats of the player
        public void updateStats()
        {
            String stats = "Hp:  " + Player.getHp() +  " |  " + "Primary Damage:  " + Player.primaryAttack() + " | " + "Secondary Damage:  " + Player.secondaryAttack() + " | " + "Armour: " + Player.getArmour() + " | " + "Speed: " + Player.getSpeed() ;
            nameLabel.setText(stats);           
        }
        //sets the in combat to true
        public void setInCombatTrue()
        {
            currentlyInCombat = true;
        }
        //sets the in combat to false
        public void setInCombatFalse()
        {
            currentlyInCombat = false;
        }
        //gets the room number
        public int getRoomNum()
        {
            return this.roomNum;
        }
        //creates a fight panel that acts as the io to player vs villain
        public void nextFight(Character villain)
        {
           setInCombatTrue();
           currentCombatPanel.nextFight(villain);
        }
        //changes combat panel and sets currentlyInCombat to false
        public void changeCombatStatus()
        {
            setInCombatFalse();
            gameOutput.checkIfEnemyLeft();
        }
        //turns the button for next room visible
        public void turnOnNextButton()
        {
            nextButton.setVisible(true);
        }
        //when the player dies the data is saved but the coins are dropped and the player is presented which the option of exiting game.
        public void playerDies()
        {
            Player.dropCoins();     
            saveGame();
            Game.lostGame();
        }
        //loads BossFight Window
        public void loadBossFight()
        {
            this.bf = new BossFight(Player, new Death(500), currentRoom, gameOutput, this);
        }
        //player dies from boss fight
        public void playerDiesBossFight()
        {
            bf.dispose();
            Player.dropCoins();     
            saveGame();
            Game.lostGame();
        }
        //Player Wins
        public void playerWins()
        {
            Player.addCoins(50);
            bf.dispose();
            saveGame();
            Game.winGame();
        }
        //method to save data
        public void saveGame()
        {
            try{
                if(new File(FileName).exists() == false){throw new FileException("File Doesn't Exist!");}
                //if file exists
                PrintWriter outputStream = new PrintWriter(new FileWriter(FileName));
                outputStream.println(Player.getHeroType()); //1 name
                outputStream.println(Player.getName()); //1 name
                outputStream.println(Player.getHp()); //2 hp
                outputStream.println(Player.getArmour()); //3 armour
                outputStream.println(Player.getDamage()); //4 damage
                outputStream.println(Player.getSpeed()); //5 speed
                outputStream.println(Player.getStamina()); //6 stamina
                outputStream.println(Player.getCoins()); //7 coins
                outputStream.println(Player.getStaminaRegenAmount()); //8 staminaregen
                
                // for items in arraylist
                ArrayList<Items> inventory = Player.getItems();
                for(Items i: inventory)
                {
                    outputStream.println(i.getInstance());
                }
                outputStream.close();
                outputSuccessMessage("File Successfully Saved!");
            }
            catch(IOException e)
            {
                outputErrorMessage(e.getMessage());
            } 
            catch(FileException e)
            {
                outputErrorMessage(e.getMessage());
            }            
        }
        //overriding painComponent method to set the background
        public void paintComponent(Graphics g)
        {
            g.drawImage(gameBackground,0,0,null); 
        }
        /**************** Implemented method for ActionListener Interface ******************/
        public void actionPerformed(ActionEvent evt)
        {
            String Command = evt.getActionCommand();
            if(Command.equals("Shop"))
            {
                Shop shop = new Shop();
                ShopIO ShopGUI = new ShopIO(shop, Player);
            }
            else if(Command.equals("Save"))
            {
                saveGame();
            }
            else if(Command.equals("Exit"))
            {
                saveGame();
                System.exit(0);
            }
            else if(Command.equals("Next"))
            {
                changeRoom();
            }
            else if(Command.equals("Boss Fight"))
            {
                loadBossFight();
            }
            else if(Command.equals("Inventory"))
            {
                ViewInventory inv = new ViewInventory(Player.getItems());
            }
            else if(Command.equals("UP"))
            {
                if(gameOutput.checkIfDecrementPostionActionAllowed(gameOutput.getcurrentPlayerY()-1) && currentlyInCombat == false)
                {
                    gameOutput.upKey();
                    updateStats();
                }
            }
            else if (Command.equals("LEFT"))
            {
                if(gameOutput.checkIfDecrementPostionActionAllowed(gameOutput.getcurrentPlayerX()-1) && currentlyInCombat == false)
                {
                    gameOutput.leftKey();
                    updateStats();
                }            
            }
            if(Command.equals("RIGHT"))
            {
                if(gameOutput.checkIfXIncrementPostionActionAllowed(gameOutput.getcurrentPlayerX()+1) && currentlyInCombat == false)
                {
                    gameOutput.rightKey();
                    updateStats();
                }
            }
            else if (Command.equals("DOWN"))
            {
                if(gameOutput.checkIfYIncrementPostionActionAllowed(gameOutput.getcurrentPlayerY()+1) && currentlyInCombat == false)
                {
                    gameOutput.downKey();
                    updateStats();
                }            
            }
        }
    }
    /********************************* Inner Class For Bottom Buttons *********************************/
    public class bottomButtons extends JButton
    {
        public bottomButtons(String buttonName)
        {
            super(buttonName);
            setSize(350, 70);
            setBackground(Color.ORANGE);
            setForeground(Color.RED);
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 30));
            setBorder(null);
        }
    }
    /********************************* Inner Class For Game Input Buttons *********************************/
    public class gameInputButtons extends JButton
    {
        public gameInputButtons(String buttonName)
        {
            super(buttonName);
            setSize(350, 110);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            setFont(new java.awt.Font("ITALIC", Font.BOLD, 30));
            setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }    
}
