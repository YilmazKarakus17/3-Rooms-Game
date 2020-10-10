import javax.swing.ImageIcon;
/**
 * Write a description of class RoomCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RoomCreator
{
    /********* Instance Variables *********/
    private Hero Player;
    private Character[] Villains;
    private Items item;
    private int VillainsLeft;
    
    //Instance variable of a custom frame that logs everything
    /********* Constructors ************/
    //constructor for nogui
    public RoomCreator(Hero ParamPlayer, int AmountOfVillians)
    {
        Player = ParamPlayer;
        Villains = Initializer.InitializeVillainsArray(AmountOfVillians);
        item = Initializer.InitializeItem(ParamPlayer);
        ParamPlayer.setItems(item);
        VillainsLeft = Villains.length;
    }
    /******** Instance Methods *********/
    public void CreateRoomGUI(int roomNumber)
    {
        int rows = Initializer.initializeRoomRow(Villains.length);
        int cols = Initializer.initializeRoomCols(Villains.length);
        ImageIcon RoomFloor = Initializer.initializeRoomImage();        
    }
    public Items getItem()
    {
        return item;
    }
    //method for decrementing amount of enemies
    public void decrementEnemyCount()
    {
        VillainsLeft = VillainsLeft -1;        
    }
    //returns the amount of villains left
    public int getVillainAmount()
    {
        return VillainsLeft;
    }
    //getter method for villain array
    public Character[] getVillains()
    {
        return Villains;
    }
}
