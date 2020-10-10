
/**
 * Write a description of class Ogre here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class Ogre extends Monsters
{   
    private String name = "Ogre";
    private int OgreAnger = 15;
    private int Ogrekindness = 5;
    public Ogre()
    {
        super(175,55,15);
    }
    /********** Overriding Methods *************/
    //Method to return the damage of primary attack weakest amongst the two
    public int primaryAttack()
    {
        return getDamage() + getDeformityPoints();
    }
    //function that returns higher damage than primary attack
    public int secondaryAttack()
    {
        return (getDamage() + getDeformityPoints()) + OgreAnger;
    }
    //procedure to subtract monster hp
    public void takeDamage(int DamageGiven)
    {
        setHp(getHp()-(DamageGiven-Ogrekindness));
    }
    //returns the name
    public String getName()
    {
        return name;
    }
}