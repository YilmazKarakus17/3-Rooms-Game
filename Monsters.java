
/**
 * 
 *
 * @author (yk)
 * @version (18/03/2020)
 */
public abstract class Monsters implements Character
{
    /******* Instance Variables ******/    
    private int hp;
    private int damage;
    private int deformityPoints;
    private int coins = 2;
    /******* Constructors ******/
    public Monsters(int Hp, int Damage, int DeformityPoints)
    {
        hp = Hp;
        damage = Damage;
        deformityPoints = DeformityPoints;
    }
    /******* Setter Methods ******/
    //sets the instance variable hp to the argument passed through ParamHp
    public void setHp(int ParamHp)
    {
        hp = ParamHp;
    }
    //sets the instance variable damage to the argument passed through ParamDamage
    public void setDamage(int ParamDamage)
    {
        damage = ParamDamage;
    }
    /******* Getter Methods ******/
    //returns value stored in private instance variable hp
    public int getHp()
    {
        return hp;
    }
    //returns value stored in private instance variable damage
    public int getDamage()
    {
        return damage;
    }
    //returns the value stored in the private instance variable deformityPoints
    public int getDeformityPoints()
    {
        return deformityPoints;
    }
    //returns coins
    public int getCoins()
    {
        return coins;
    }
    //once monster dies this method is used by other methods to give coins
    public int dropCoins()
    {
        return getCoins();
    }
    /******* Abstract Methods ******/    
    public abstract String getName();   
    public abstract void takeDamage(int DamageGiven);
    public abstract int primaryAttack();
    public abstract int secondaryAttack();
}