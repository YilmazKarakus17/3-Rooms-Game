
/**
 * Write a description of class Wizards here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class MagicBasedVillains implements Character
{
    /******* Instance Variables ******/    
    private int hp;
    private int damage;
    private int MagicBonus;
    private int coins = 3;
    /******* Constructors ******/
    public MagicBasedVillains(int Hp, int Damage, int MagicBonus)
    {
        this.hp = Hp;
        this.damage = Damage;
        this.MagicBonus = MagicBonus;
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
    //returns magic bonus
    public int getMagicBonus()
    {
        return MagicBonus;
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
