import java.util.*;
import java.util.Timer;
import java.util.Timer.*;
import java.util.TimerTask;
/**
 * Abstract class Mechanics - Where the games combat mechanics are declared
 *
 * @author (Yilmaz Karakus)
 * @version (3/3/2020)
 */
public abstract class CombatMechanics
{
    private static String ChosenAttack;   
    //Returns teh combined damage of all the items in the players inventory
    public static int getCombinedDamageOfItems(ArrayList<Items> PlayerItemArray)
    {
        int TotalCombinedDamageOfItems = 0;
        for (Items i: PlayerItemArray)
        {
            TotalCombinedDamageOfItems = TotalCombinedDamageOfItems + i.AffectOnPlayerDamage();               
        }
        return TotalCombinedDamageOfItems;
    }
    //Returns the combined armour increase of all the items in player inventory
    public static double getCombinedArmourAffectOfItems(ArrayList<Items> PlayerItemArray)
    {
        double TotalCombinedArmour = 0;
        for (Items i: PlayerItemArray)
        {
            TotalCombinedArmour = TotalCombinedArmour + i.AffectOnPlayerArmour();
        }
        return TotalCombinedArmour;
    }
    
    /*********** Player Attacks Moves ******************/
    //----------------------------------------- NON GUI ROOM METHODs ---------------------------------------------
    //method that randomizes whether enemy does primary or secondary attack against the player
    public static void villainAttack(Character Villain, Hero Player, CombatPanel combatIO)
    {
        if(MyUtil.random(3) <= 2)
        {
            Player.takeDamage(Villain.primaryAttack());
            combatIO.appendTextField("Enemy Primary Attacks You, as a result your Hp has been reduced down to: " + Player.getHp());
            Player.StaminaRegen();
            combatIO.appendTextField("This enemy attack has given you to regenerate your stamina ");
        }
        else
        {
            Player.takeDamage(Villain.secondaryAttack());
            combatIO.appendTextField("Enemy Secondary Attacks You, as a result your Hp has been reduced down to: " + Player.getHp());
            Player.StaminaRegen();
            combatIO.appendTextField("This enemy attack has given you time to regenerate your stamina ");
        }
        Player.StaminaRegen();
    }
    //checks to see if the enemy can attack(true) or not(false)
    public static boolean checkIfEnemyAttacks(int counter, int speed)
    {
        if(counter%speed == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //when enemy is killed this method is called
    public static void enemyDead(Hero Player, Character Enemy)
    {
        Player.resetHp();
        Player.resetStamina();
        Player.addCoins(Enemy.dropCoins());
    }
    //Method used when player chooses primary attack
    public static void PrimaryAttack(Hero Player, Character Villain) throws InsufficientStaminaException
    {
        if(Player.getStamina() >= Player.getPrimaryStaminaReduction())
        {
            Villain.takeDamage(Player.primaryAttack());
            Player.StaminaReductionPrimary();
        }
        else{
            throw new InsufficientStaminaException();
        }
    }
    //Method used when player chooses secondary attack
    public static void SecondaryAttack(Hero Player, Character Villain) throws InsufficientStaminaException
    {
        if(Player.getStamina() >= Player.getSecondaryStaminaReduction())
        {
            Villain.takeDamage(Player.secondaryAttack());
            Player.StaminaReductionSecondary();
        }
        else{
            throw new InsufficientStaminaException();
        }
    }
}
