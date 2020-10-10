import java.util.*;
/**
 * This is a superclass to many specialized subclasses that process game mechanics 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class ItemProcessing
{
    //function that checks whether the item passed is a golden sword (true) or not (false)
    public static boolean instanceofGoldenSword(Items item)
    {
        if(item instanceof GoldenSword){return true;}
        else{return false;}
    }
    //function that checks whether the item passed is a knife (true) or not (false)    
    public static boolean instanceofKnife(Items item)
    {
        if(item instanceof Knife){return true;}
        else{return false;}
    }
    //function that checks whether the item passed is a shield (true) or not (false)    
    public static boolean instanceofShield(Items item)
    {
        if(item instanceof Shield){return true;}
        else{return false;}
    }
    //function that checks whether the array passed contains any golden sword (true) or not (false)
    public static boolean checkArrayForGoldenSword(ArrayList<Items> ItemsList)
    {
        boolean result = false;
        for(Items item: ItemsList)
        {
            if(instanceofGoldenSword(item))
            {
                result = true;
            }
        }
        return result;
    }
    //function that checks whether the array passed contains any Knife (true) or not (false)
    public static boolean checkArrayForKnife(ArrayList<Items> ItemsList)
    {
        boolean result = false;
        for(Items i: ItemsList)
        {
            if(instanceofKnife(i))
            {
                result = true;
            }
        }
        return result;
    }
    //function that checks whether the array passed contains any Shield (true) or not (false)
    public static boolean checkArrayForShield(ArrayList<Items> ItemsList)
    {
        boolean result = false;
        for(Items i: ItemsList)
        {
            if(instanceofShield(i))
            {
                result = true;
            }
        }
        return result;
    }
}
