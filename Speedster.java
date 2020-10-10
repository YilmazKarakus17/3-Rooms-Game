import java.util.ArrayList;
/**
 * Speedster is a subclass to hero
 *
 * @author (Yilmaz Karakus)
 * @version (27/02/2019)
 */
public final class Speedster extends Hero
{
    /***************** Instance Variables *****************/
    private int TimeLineDamage = 15;
    private int resetHp;
    private int resetStamina = 200;
    private int primaryStaminaReduction = 25;
    private int secondaryStaminaReduction = 90;
    private int SpeedsterExtraRegen = 50;
    /***************** Constructors *****************/
    public Speedster()
    {
        super(100, 20, 50, 3, 200, 0, 10);
        resetHp = 100;
    }
    
    public Speedster(String Name)
    {
        super(Name,100, 20, 50, 3, 200, 0, 10);
        resetHp = 100;
    }
    //constructor for when a game save is read
    public Speedster(String name, int MaxHp, double armour, int damage, int speed, int stamina, int coins, int StaminaRegenAmount, ArrayList<Items> inventory)
    {
        super(name,MaxHp,armour,damage,speed,stamina,coins,StaminaRegenAmount,inventory);
        resetHp = MaxHp;
    }    
    /***************** Overriding Methods *****************/
    //getter method for primary attack stamina reduction
    public int getPrimaryStaminaReduction()
    {
        return primaryStaminaReduction;
    }
    //getter method for and secondary attack stamina reduction
    public int getSecondaryStaminaReduction()
    {
        return secondaryStaminaReduction;
    }
    //methods for primary and secondary attack
    public int primaryAttack()
    {
        int finalDamage = 0;
        finalDamage = getDamage();
        return (finalDamage);
    }
    public int secondaryAttack()
    {
        int finalDamage = 0;
        finalDamage = getDamage() + CombatMechanics.getCombinedDamageOfItems(getItems());
        return (finalDamage + TimeLineDamage);
    }
    //reduces stamina every primary attack
    public void StaminaReductionPrimary()
    {
        setStamina(getStamina() - primaryStaminaReduction);
    }
   //reduces stamina every primary attack
    public void StaminaReductionSecondary()
    {
        setStamina(getStamina() - secondaryStaminaReduction);
    } 
    //regenerates stamina 
    public  void StaminaRegen()
    {
        setStamina(getStamina() + getStaminaRegenAmount() + SpeedsterExtraRegen);
    }
    //method handling hp reduction when player gets damaged
    public void takeDamage(int DamageGiven)
    {
        //Speedsters get 15% chance to go back in time and avoid getting hit
        if(MyUtil.random(100) <= 15)
        { 
            MyUtil.print("You used time travel to go in time and avoid getting hit");
        }
        else
        {
            setHp(getHp() - (int)(DamageGiven - ((CombatMechanics.getCombinedArmourAffectOfItems(getItems()))/10)));    
        }
        
    }
    //resets players hp
    public void resetHp()
    {
        setHp(resetHp);
    }
    //resets players Stamina
    public void resetStamina()
    {
        setStamina(resetStamina);
    }
    //sets the maximum hp
    public void setMaxHP(int amount)
    {
       resetHp = resetHp+amount; 
       setHp(resetHp);
    } 
    //returns the max hp of the hero
    public int getMaxHp()
    {
        return resetHp;
    }
    //returns speedster
    public String getHeroType()
    {
        return "speedster";
    }
}
