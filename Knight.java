
/**
 * Warrior subclass Knight that has 25% to not take damage
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Warriors
{
    // instance variables - replace the example below with your own
    private String name = "Knight";
    private int KnightBlockingSpeed = 25;
    /**
     * Constructor for objects of class BigJeff
     */
    public Knight()
    {
        super(75, 65, new Knife());
    }
   /********** Overriding Methods *************/
    //Method to return the damage of primary attack - weakest amongst the two forms of attack
    public int primaryAttack()
    {
        return getDamage() + getEquipmentBonusDamage();
    }
    //function that returns higher damage than primary attack
    public int secondaryAttack()
    {
        return (getDamage() + getEquipmentBonusDamage());
    }
    //procedure to subtract monster hp
    public void takeDamage(int DamageGiven){
        if(MyUtil.random(100) < KnightBlockingSpeed){
            POPUP popup = new POPUP("Knight has blocked the hit: no damage given");
        }
        else{
            setHp(getHp()-(DamageGiven-getEquipmentBonusBlock()));
        }    
    }
    //returns the name
    public String getName()
    {
        return name;
    }

}
