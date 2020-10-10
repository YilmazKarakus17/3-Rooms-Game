
/**
 * Warrior subclass BigJeff are berserkers named jeff who have +5 damage due to strengh
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BigJeff extends Warriors
{
    // instance variables - replace the example below with your own
    private String name = "Berserker Jeff";
    private int Strength = 5; 
    /**
     * Constructor for objects of class BigJeff
     */
    public BigJeff()
    {
        super(125, 75, new Shield());
    }
   /********** Overriding Methods *************/
    //Method to return the damage of primary attack weakest amongst the two
    public int primaryAttack()
    {
        return getDamage() + getEquipmentBonusDamage();
    }
    //function that returns higher damage than primary attack
    public int secondaryAttack()
    {
        return (getDamage() + getEquipmentBonusDamage()) + Strength;
    }
    //procedure to subtract monster hp
    public void takeDamage(int DamageGiven){
        setHp(getHp()-(DamageGiven-getEquipmentBonusBlock()));
    }
    //returns the name
    public String getName()
    {
        return name;
    }

}
