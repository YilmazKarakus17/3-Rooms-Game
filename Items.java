
/**
 * Abstract class Items - superclass to specialized items and aims to provide 
 *                        standardised methods
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Items
{
    /******* Instance Variables ******/
    private int damage;
    private double block;
    private double durability;
    /******* Constructor ******/
    public Items(int Damage, int Block, double Durability)
    {
        damage = Damage;
        block = Block;
        durability = Durability;
    }
    /******* Getter Methods ******/
    //returns the int value of the the damage variable
    public int getDamage()
    {
        return damage;
    }
    //returns the int value of the block variable
    public double getBlock()
    {
        return block;
    }
    //returns the int value of the Durability variable
    public double getDurability()
    {
        return durability;
    }
    /******* Setter Methods ******/
    //returns the int value of the Durability variable
    public void setDurability(double amount)
    {
        durability = amount;
    }
    /******* Abstract Methods ******/
    public abstract int AffectOnPlayerDamage();
    public abstract double AffectOnPlayerArmour();
    public abstract void DecreaseDurability();
    public abstract String getInfo();
    public abstract String getInstance();
}