import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Room panel acts as a IO for room interactions 
 *
 * @author (YK)
 * @version (26/03/2020)
 */

public class gameOutputPanel extends JPanel
{
    /********* Instance Variables *********/
    //non gui variables    
    private RoomCreator CurrentRoom;
    private int RoomNumber;
    private int MaxNumOfRoomsAllowed;
    private Character[] Villains;
    private ArrayList<Items> ItemsAvailable = new ArrayList<Items>();
    private GameIO.GamePanel game;
    //everytime player picks up item this gets incremented
    private int currentItem = 0;
    //Message Label
    private Label Message;
    /************** Left hand side panel instance variables ******************/
    //rows and columns of the panels
    private final int rows = 10;
    private final int cols = 15;
    //Images
    private ImageIcon FloorImage;
    private ImageIcon HeroStill = new ImageIcon("Hero.png");
    private ImageIcon Enemy = new ImageIcon("enemy.png");
    private ImageIcon Item = new ImageIcon("item.png");
    //Matrices
    private JLabel[][] GameOutputMatrix = new JLabel[rows][cols];
    private Character[][] EnemyLocationMatrix = new Character[rows][cols];
    private Items[][] ItemLocationMatrix = new Items[rows][cols];
    //Player variables
    private Hero Player;
    private int currentPlayerX;
    private int currentPlayerY;
    /********* Constructor *********/
    public gameOutputPanel(Hero Player, RoomCreator CurrentRoom, Character[] Villains, GameIO.GamePanel game)
    {
        this.Player = Player;
        this.CurrentRoom = CurrentRoom;
        this.RoomNumber = RoomNumber;
        this.MaxNumOfRoomsAllowed = MaxNumOfRoomsAllowed;
        this.Villains = Villains;
        this.game = game;
        //setting dimensions
        Dimension PanelSize = new Dimension(799,602);
        setSize(PanelSize);
        setMaximumSize(PanelSize);
        setMinimumSize(PanelSize);
        setPreferredSize(PanelSize);
        //settingLayout
        setLayout(new GridLayout(rows,cols));
        //creating GamoutputPanel
        createGameOutputPanel();
    }
    //creates and adds game input panel
    public void createGameOutputPanel()
    {
        //creating game floor panel
        createGamePanel();
        initializePlayerLocation();
        initializeEnemyLocation();
        initializeItemLocation();
        //positioning game panel
        this.setBounds(0,0, 800, 600);
    }
    /************** Instance Methods **************************/
    //creates the game panel 
    public void createGamePanel()
    {
        setFloorImage();
        for (int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                GameOutputMatrix[i][j] = new JLabel(FloorImage);
                this.add(GameOutputMatrix[i][j]);
            }
        }      
    }
    //sets the variable FloorImage to one of the floor images at random
    public void setFloorImage()
    {
        FloorImage = (MyUtil.random(100) <=50? new ImageIcon("CobbleStone.png"): new ImageIcon("wood.png"));
    }
    //initializes the players location
    public void initializePlayerLocation()
    { 
        GameOutputMatrix[0][0].setIcon(HeroStill);
        currentPlayerX = 0;
        currentPlayerY = 0;
    } 
    //initializes the enemy location
    public void initializeEnemyLocation()
    { 
        for(int i = 0; i < this.Villains.length; i++){
            int enemyrow = Initializer.getLocationRow(rows);
            int enemycol = Initializer.getLocationCol(cols);
            //if location doesn't already exist and either the row or col isn't 0
            if(checkLocationNotTaken(enemyrow, enemycol))
            {
                EnemyLocationMatrix[enemyrow][enemycol] = this.Villains[i];
                GameOutputMatrix[enemyrow][enemycol].setIcon(Enemy);
            }
            else{
                while(checkLocationNotTaken(enemyrow, enemycol) == false)
                {
                     enemyrow = Initializer.getLocationRow(rows);
                     enemycol = Initializer.getLocationCol(cols);                    
                }
                EnemyLocationMatrix[enemyrow][enemycol] = this.Villains[i];
                GameOutputMatrix[enemyrow][enemycol].setIcon(Enemy);
            }
        }
    } 
    //initializes the item location
    public void initializeItemLocation()
    { 
        createItemList();
        for(Items i: ItemsAvailable){
            int itemrow = Initializer.getLocationRow(rows);
            int itemcol = Initializer.getLocationCol(cols);
            //if location doesn't already exist and either the row or col isn't 0
            if(checkLocationNotTaken(itemrow, itemcol))
            {
                ItemLocationMatrix[itemrow][itemcol] = i;
                GameOutputMatrix[itemrow][itemcol].setIcon(Item);
            }
            else{
                while(checkLocationNotTaken(itemrow, itemcol) == false)
                {
                     itemrow = Initializer.getLocationRow(rows);
                     itemcol = Initializer.getLocationCol(cols);                    
                }
                ItemLocationMatrix[itemrow][itemcol] = i;
                GameOutputMatrix[itemrow][itemcol].setIcon(Item);
            }
        }
    }
    //adds items to the item Arraylist
    public void createItemList()
    {
        for(int i =1; i<=MyUtil.random(3); i++)
        {
            ItemsAvailable.add(Initializer.InitializeItem());
        }
    }
    //returns true if the location generated doesn't already have a character
    public boolean checkLocationNotTaken(int row, int col)
    {
      if(EnemyLocationMatrix[row][col] == null && (row !=0 || col !=0) && ItemLocationMatrix[row][col] == null)
      {
         return true; 
      }
      else{
          return false;
      }
    } 
    //returns true if action if buttons that either decrement x or y  allowed or false if out of index 
    public boolean checkIfDecrementPostionActionAllowed(int playerLocationIfPressed)
    {
        if (playerLocationIfPressed < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //returns true if action if right button that increment x(columns) is allowed or false if out of index 
    public boolean checkIfXIncrementPostionActionAllowed(int playerLocationIfPressed)
    {
        if (playerLocationIfPressed > cols-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //returns true if action if down button that increment y(rows) is allowed or false if out of index 
    public boolean checkIfYIncrementPostionActionAllowed(int playerLocationIfPressed)
    {
        if (playerLocationIfPressed > rows-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //checks if the player is on a item and if the person is then they pick up the item
    public void checkForItem()
    {
        if(ItemLocationMatrix[currentPlayerY][currentPlayerX] !=null)
        {
            givePlayerItem();
            this.game.itemReceivedMessage(ItemsAvailable.get(currentItem).getInfo());
            currentItem = currentItem + 1;
        }
        ItemLocationMatrix[currentPlayerY][currentPlayerX] = null;
    }
    //gives player the item
    public void givePlayerItem()
    {
        if(ItemsAvailable.get(currentItem) instanceof GoldenSword)
        {
            Player.setItems(new GoldenSword(Player));
        }
        else if(ItemsAvailable.get(currentItem) instanceof Knife)
        {
            Player.setItems(new Knife(Player));
        }
        else if(ItemsAvailable.get(currentItem) instanceof Shield)
        {
            Player.setItems(new Shield(Player));
        }
    }
    //calls on combatgui fight method if player is on a enemy
    public void checkForEnemy()
    {
       if (EnemyLocationMatrix[currentPlayerY][currentPlayerX] != null)
       {
           CurrentRoom.decrementEnemyCount();
           game.nextFight(EnemyLocationMatrix[currentPlayerY][currentPlayerX]);
       }
       EnemyLocationMatrix[currentPlayerY][currentPlayerX] = null;
    }
    //checks whether enemy left to continue or ends room
    public void checkIfEnemyLeft(){
        if(CurrentRoom.getVillainAmount() > 0)
        {
            
        }
        else{
            if(game.getRoomNum() != 3)
            {
                game.turnOnNextButton();
            }
        }           
    }
    
    //-------------------- methods for buttons -----------------------------------//
    //method that changes the players position and icon in the game to go up
    public void upKey()
    {
        //changing the position of the players icon to be 1 up
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(FloorImage);
        currentPlayerY = currentPlayerY - 1;
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(HeroStill);

        checkForEnemy();
        checkForItem();  
    }
    //method that changes the players position and icon in the game to go left
    public void leftKey()
    {
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(FloorImage);
        currentPlayerX = currentPlayerX - 1;
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(HeroStill);
        
        checkForEnemy();
        checkForItem();  
    }
    //method that changes the players position and icon in the game to go right
    public void rightKey()
    {
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(FloorImage);
        currentPlayerX = currentPlayerX + 1;
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(HeroStill);
                    
        checkForEnemy();     
        checkForItem();            
    }
    //method that changes the players position and icon in the game to go down
    public void downKey()
    {
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(FloorImage);
        currentPlayerY = currentPlayerY + 1;
        GameOutputMatrix[currentPlayerY][currentPlayerX].setIcon(HeroStill);
        
        checkForEnemy();  
        checkForItem();   
    }
    
    //getter methods
    public int getcurrentPlayerY()
    {
        return currentPlayerY;
    }
    
    public int getcurrentPlayerX()
    {
        return currentPlayerX;
    }
}