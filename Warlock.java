
/**
 * Write a description of class Warlock here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class Warlock extends MagicBasedVillains
{
    // instance variables
    private String name = "Warlock";
    private int BeyondEvilPoints = 15;
    private int WarLockShieldOfEvil = 5;
    /**
     * Constructor for objects of class Warlock
     */
    public Warlock()
    {
        super(150, 35, 15);
    }
    /********** Overriding Methods *************/
    //Method to return the damage of primary attack weakest amongst the two
    public int primaryAttack()
    {
        return getDamage() + getMagicBonus();
    }
    //function that returns higher damage than primary attack
    public int secondaryAttack()
    {
        return (getDamage() + getMagicBonus()) + BeyondEvilPoints;
    }
    //procedure to subtract monster hp
    public void takeDamage(int DamageGiven){
        setHp(getHp()-(DamageGiven-WarLockShieldOfEvil));
    }
    //returns the name
    public String getName()
    {
        return name;
    }
}
