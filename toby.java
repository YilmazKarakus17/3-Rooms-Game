
/**
 * Write a description of class jim here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class toby extends Monsters
{
    private String name = "toby <---- (this guys trash)";
    private int screwToby = 100;
    public toby()
    {
        super(100,1,100);
    }
    //Both of toby's attacks are weak
    public int primaryAttack()
    {
        return (getDamage()+getDeformityPoints())-screwToby;
    }
    public int secondaryAttack()
    {
        return (getDamage()+getDeformityPoints())-screwToby;
    } 
    //----------------------------------------
    //procedure to subtract monster hp   
    public void takeDamage(int DamageGiven){
        setHp(getHp()-DamageGiven);
    }
    //returns the name
    public String getName()
    {
        return name;
    }
}