
/**
 * warriors which unlike other enemies have different items based on the subclass enemy type
 *
 * @author (yk)
 * @version (21/03/2020)
 */
public abstract class Warriors implements Character
{
    /******* Instance Variables ******/    
    private int hp;
    private int damage;
    private Items EquipmentBonus;
    private int coins = 5;
    /******* Constructors ******/
    public Warriors(int Hp, int Damage, Items Equipment)
    {
        this.hp = Hp;
        this.damage = Damage;
        this.EquipmentBonus = Equipment;
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
    //returns bonus damage from equipment
    public int getEquipmentBonusDamage()
    {
        return EquipmentBonus.getDamage();
    }
    //returns bonus block from equipment
    public int getEquipmentBonusBlock()
    {
        return (int)EquipmentBonus.getBlock();
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
