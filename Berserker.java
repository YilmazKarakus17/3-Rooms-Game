import java.util.ArrayList;
/**
 * Berserker is a subclass to hero
 *
 * @author (Yilmaz Karakus)
 * @version (27/02/2019)
 */
public final class Berserker extends Hero
{
    /***************** Instance Variables *****************/
    private int OdenSon = 5;
    private int resetHp;
    private int resetStamina = 100;
    private int primaryStaminaReduction = 10;
    private int secondaryStaminaReduction = 50;
    /***************** Constructors *****************/
    public Berserker()
    {
        super(150, 50, 75, 1, 100, 15, 9);
        resetHp = 150;
    }
    public Berserker(String Name)
    {
        super(Name,150, 50, 75, 1, 100, 15, 9);
        resetHp = 150;
    }
    //constructor for when a game save is read
    public Berserker(String name, int MaxHp, double armour, int damage, int speed, int stamina, int coins, int StaminaRegenAmount, ArrayList<Items> inventory)
    {
        super(name,MaxHp,armour,damage,speed,stamina,coins,StaminaRegenAmount,inventory);
        resetHp = MaxHp;
    }
    /***************** Overriding Methods *****************/
    //getter methods for how much primary and secondary attack costs in terms of stamina reduction
    public int getPrimaryStaminaReduction()
    {
        return primaryStaminaReduction;
    }
    public int getSecondaryStaminaReduction()
    {
        return secondaryStaminaReduction;
    }

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
        return (finalDamage + OdenSon);
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
    //regens stamina 
    public  void StaminaRegen()
    {
        setStamina(getStamina() + getStaminaRegenAmount());
    }
    //method handling hp reduction when player gets damaged
    public void takeDamage(int DamageGiven)
    {
        setHp((getHp() + OdenSon) - (int)(DamageGiven - ((CombatMechanics.getCombinedArmourAffectOfItems(getItems()))/10)));
    }
    //sets the maximum hp
    public void setMaxHP(int amount)
    {
       resetHp = resetHp+amount; 
       setHp(resetHp);
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
    //returns the max hp of the hero
    public int getMaxHp()
    {
        return resetHp;
    }
    //returns berserker
    public String getHeroType()
    {
        return "berserker";
    }
}
