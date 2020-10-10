import javax.swing.ImageIcon;
/**
 * Abstract class Initializer - this abstract class contains static functions and procedures which initialize certain game aspects 
 *
 * @author (YK)
 * @version (6/3/2020)
 */
public abstract class Initializer
{
    /******** Initializes villians for a Character array and returns the whole array ***************/
    public static Character[] InitializeVillainsArray(int arraySize){
        Character[] VillainArray = new Character[MyUtil.random(arraySize)];
        for (int i=0; i<VillainArray.length; i++)
        {
            VillainArray[i] = (MyUtil.random(10) <= 3? (MyUtil.random(2) <= 1? new Ogre(): new toby()): (MyUtil.random(100) <= 50? (MyUtil.random(100) <= 1? new Death(): new Warlock()): (MyUtil.random(100) <= 50? new BigJeff(): new Knight()))); 
        }
        return VillainArray;
    }
    /************ Initializes a random item *************/
    //2% Chance of returning Golden Sword
        //else 50:50 chance of either returning a 
    public static Items InitializeItem(Hero Player)
    {
        Items Item;
        if(MyUtil.random(100) < 2){Item = new GoldenSword(Player);}
        else
        {
            if(MyUtil.random(100) <= 50){Item = new Knife(Player);}
            else{Item = new Shield(Player);}
        }
        return Item;
    }
    public static Items InitializeItem()
    {
        Items Item;
        if(MyUtil.random(100) < 2){Item = new GoldenSword();}
        else
        {
            if(MyUtil.random(100) <= 50){Item = new Knife();}
            else{Item = new Shield();}
        }
        return Item;
    }
    /******************* Room Initializers *****************************/
    //initializes the rows of the gui
    public static int initializeRoomRow(int AmountVillains)
    {
        return (AmountVillains + MyUtil.random(3));
    }
    //initializes the cols of the gui
    public static int initializeRoomCols(int AmountVillains)
    {
        return (AmountVillains + MyUtil.random(3));
    }
    //initializes the imageicon
    public static ImageIcon initializeRoomImage()
    {
        return (MyUtil.random(100) <= 50? new ImageIcon("cobblestone.png"): new ImageIcon("Grass.png"));
    }
    //finds a random row for enemy to go to
    public static int getLocationRow(int GUIRow)
    {
        return (MyUtil.randomFromZero(GUIRow-1));
    }
    //finds a random col for enemy to go to
    public static int getLocationCol(int GUICol)
    {
        return (MyUtil.randomFromZero(GUICol-1));
    }
}
