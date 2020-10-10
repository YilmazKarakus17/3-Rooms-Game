
/**
 * Shield is a subclass of Items and mainly affects characters block
 * Shield has the passive ability of adding 25 health to the players maximum health
 * @author (yk)
 * @version (5/3/2020)
 */
public final class Shield extends Items
{
    private final int healthBenefit = 25;
    private double ShieldBlockBonus = 5;
    private String ItemInfoPassive = "Shield: \nPassive Ability: once picked up item adds 25 points to the players maximum HP"; 
    private String ItemInfoUniqueAffect = "\nUniqueAffect: its armour increase is its block amount plus half of its durability points";  
    /********* Constructor **********/
    public Shield()
    {
        super(5,20,20);
    }
    //constructors also adds to the Players object's maximum hp 
    public Shield(Berserker Player)
    {
        super(5,20,20);
        Player.setMaxHP(healthBenefit);
    }

    public Shield(Speedster Player)
    {
        super(5,20,20);
        Player.setMaxHP(healthBenefit);   
    }

    public Shield(Hero Player)
    {
        super(5,20,20);
        Player.setMaxHP(healthBenefit);
    }
    /******** setters and getters *************/
    public void setShieldBlockBonus(double amount)
    {
        ShieldBlockBonus = amount;
    }
    public double getShieldBlockBonus()
    {
        return ShieldBlockBonus;
    }
    /***************** Overriding Methods *****************/
    //Method that returns the int value of the additional damage it gives to the player every use
    public int AffectOnPlayerDamage()
    {
        return getDamage();
    }
    //Method that returns the int value of the additional armour it give to the player every use
    public double AffectOnPlayerArmour()
    {
        return (getBlock()+(getDurability()/10)+ShieldBlockBonus/10);
    }
    //Method that decreases the durability of the item every use
    public void DecreaseDurability()
    {
        if(getDurability() > 1)
        {
            setDurability(getDurability()-0.2);
        }
        else
        {
          setDurability(1);  
        }
    }
    //Function that concatenates information about the Item and returns its String value
    public String getInfo()
    {
        return ItemInfoPassive + ItemInfoUniqueAffect;
    }
    //returns shield
    public String getInstance()
    {
        return "shield";
    }
}
