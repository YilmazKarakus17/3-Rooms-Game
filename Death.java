
/**
 * Write a description of class Death here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class Death extends MagicBasedVillains
{
    // instance variables
    private String name = "Death";
    private int DeadlyTouch = 100;
    /**
     * Constructor for objects of class Warlock
     */
    public Death()
    {
        super(200, 75, 25);
    }
    /***************** Constructor For Final Boss ******************************/
    //constructor is called to make a stronger death character as the boss fight
    public Death(int BossBonus)
    {
        super((200+BossBonus), (75 + BossBonus), (25+BossBonus));
    }
    /********** Overriding Methods *************/
    //Method to return the damage of primary attack weakest amongst the two
    public int primaryAttack()
    {
        return getDamage() + getMagicBonus();
    }
    //function that returns higher damage than primary attack
    public int secondaryAttack(){
        if(MyUtil.random(DeadlyTouch) < 2)
        {
            return (getDamage() + getMagicBonus() + DeadlyTouch);
        }
        else
        {
            return (getDamage() + getMagicBonus() + (int)(DeadlyTouch*4));
        }
    }
    //procedure to subtract monster hp
    public void takeDamage(int DamageGiven){
        setHp(getHp()-(DamageGiven));
    }
    //returns the name
    public String getName()
    {
        return name;
    }
}
