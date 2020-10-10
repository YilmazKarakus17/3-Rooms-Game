import java.util.ArrayList;
/**
 * A abstract superclass called Hero which contains instance variables that different
 * types of hero cubclasses will us
 *
 * @author (Yilmaz Karakus)
 * @version (27/02/2019)
 */
public abstract class Hero implements Character{
    /******* Instance Variables ******/
    private String name;
    private int hp;
    private double armour;
    private int damage;
    private int speed;
    private int stamina;
    private int StaminaRegenAmount = 50;
    private int coins;
    private ArrayList<Items> PlayerItemArray = new ArrayList<Items>();
    /******* Constructors ******/
    public Hero(int hp, double armour, int damage, int speed, int stamina, int coins, int StaminaRegenAmount){
        this.hp = hp;
        this.armour = armour;
        this.damage = damage;
        this.speed = speed;
        this.stamina = stamina;
        this.coins = coins;
        this.StaminaRegenAmount = StaminaRegenAmount;
    }    
    public Hero(String name,int hp, double armour, int damage, int speed, int stamina, int coins, int StaminaRegenAmount){
        this.name = name;
        this.hp = hp;
        this.armour = armour;
        this.damage = damage;
        this.speed = speed;
        this.stamina = stamina;
        this.coins = coins;
        this.StaminaRegenAmount = StaminaRegenAmount;
    }
    //constructor for when a game save is read
    public Hero(String name,int hp, double armour, int damage, int speed, int stamina, int coins, int StaminaRegenAmount, ArrayList<Items> inventory)
    {
        this.name = name;
        this.hp = hp;
        this.armour = armour;
        this.damage = damage;
        this.speed = speed;
        this.stamina = stamina;
        this.coins = coins;
        this.StaminaRegenAmount = StaminaRegenAmount;
        this.PlayerItemArray = inventory;
    }
    /******* Setter Methods ******/
    //sets the instance variable hp to the argument passed through ParamHp
    public void setHp(int ParamHp)
    {
        hp = ParamHp;
    }
    //sets the instance variable armour to the argument passed through ParamArmour
    public void setArmour(double ParamArmour)
    {
        armour = ParamArmour;
    }
    //sets the instance variable damage to the argument passed through ParamDamage
    public void setDamage(int ParamDamage)
    {
        damage = ParamDamage;
    }
    //sets the instance variable speed to the argument passed through ParamSpeed
    public void setSpeed(int ParamSpeed)
    {
        speed = ParamSpeed;
    }
    //sets the instance variable stamina to the argument passed through ParamStamina    
    public void setStamina(int ParamStamina)
    {
        stamina = ParamStamina;
    }
    //sets the instance variable StaminaRegenAmount to the argument passed     
    public void setStaminaRegenAmount(int amount)
    {
         this.StaminaRegenAmount = amount;
    }
    //adds to the coins 
    public void addCoins(int amount)
    {
        coins +=amount;
    }
    /******* Getter Methods ******/
    //returns the name
    public String getName()
    {
        return name;
    }
    //returns player hp
    public int getHp()
    {
        return hp;
    }
    //returns player armour
    public double getArmour()
    {
        return armour;
    }
    //returns player damage
    public int getDamage()
    {
        return damage;
    }
    //returns player speed
    public int getSpeed()
    {
        return speed;
    }
    //returns player stamina
    public int getStamina()
    {
        return stamina;
    }
    //returns the amount of stamina regen hero can do
    public int getStaminaRegenAmount()
    {
        return StaminaRegenAmount;
    }
    //returns the item object in a specific location in the array
    public Items getItem(int position)
    {
        return PlayerItemArray.get(position);
    }
    //returns the whole item array
    public ArrayList<Items> getItems()
    {
        return PlayerItemArray;
    }
    //returns the amount of coins
    public int getCoins(){
        return coins;
    }
    //drops coins 
    public int dropCoins(){
        coins = 0;
        return coins;
    }
    //removes a number of coins - mainly used by methods that are involved in transaction
    public void spendCoin(int spent){
        this.coins -= spent;
    }
    /******* Instance methods *******/
    //combines calls on other methods with conditionals to only set the item passed if if doesn't already exist in the players inventory
    public void setItems(Items Item)
    {
        PlayerItemArray.add(Item);
    }
    //returns the string name of the class the player is a instance of
    public String getPlayerInstance()
    {
        String result = "";
        if(this instanceof Berserker)
        {
            result = "Berserker";
        }
        else if(this instanceof Speedster)
        {
            result = "Speedster";
        }
        return result;       
    }
    /******* Abstract Methods ******/;
    public abstract void takeDamage(int DamageGiven);
    public abstract void resetHp();
    public abstract void resetStamina();
    public abstract void setMaxHP(int amount);    
    public abstract int getMaxHp();
    public abstract int primaryAttack();
    public abstract int secondaryAttack();
    public abstract void StaminaReductionPrimary();
    public abstract void StaminaReductionSecondary();
    public abstract void StaminaRegen();  
    public abstract int getPrimaryStaminaReduction();
    public abstract int getSecondaryStaminaReduction();
    public abstract String getHeroType();
}