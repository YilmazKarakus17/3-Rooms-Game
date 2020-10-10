
/**
 * Write a description of class Sword here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knife extends Items
{
    private String ItemInfoPassive = "Knife: \nPassive Ability: once picked up item adds 33% of its damage amount to users damage amount"; 
    private String ItemInfoUniqueAffect = "\nUnique Affect: adds 10% of its durability to the armour every time player takes damage";
    private double Sharpness = 0.33;
    /**
     * Constructor for objects of class Sword
     */
    public Knife()
    {
        super(10, 1, 50);
    }
    public Knife(Hero Player)
    {
      super(10, 1, 50);
      Player.setDamage(Player.getDamage()+(int)(getDamage()*Sharpness));
    }
    public Knife(Berserker Player)
    {
      super(9, 1, 50);
      Player.setDamage(Player.getDamage()+(int)(getDamage()*Sharpness));
    }
    public Knife(Speedster Player)
    {
      super(9, 1, 50); 
      Player.setDamage(Player.getDamage()+(int)(getDamage()*Sharpness));
    }
    /******** setters and getters *************/
     public void setSharpness(double amount)
    {
        Sharpness = amount;
    }
    public double getSharpness()
    {
        return Sharpness;
    }   
    /**********  Overriding methods ***********/
    public int AffectOnPlayerDamage()
    {
        return getDamage() + (int)(getDamage()*Sharpness);
    }
    public double AffectOnPlayerArmour(){
        return (getBlock()+(getDurability()/10));
    }
    public void DecreaseDurability(){
        if(getDurability() > 1)
        {
            setDurability(getDurability()-(Sharpness*50));
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
    //returns knife
    public String getInstance()
    {
        return "knife";
    }
}
