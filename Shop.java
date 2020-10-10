import java.util.*;
/**
 * Class that is used to create a shop object which the player can buy items from
 *
 * @author (yk)
 * @version (9/3/20)
 */
public class Shop
{
    private final int ChestPrice = 5;
    private final int TrainingPrice = 1;
    private final int SharpenPrice = 10;
    private final int ShieldPerkPrice = 1;
    public Shop()
    {
        
    }       
    //method to buy a chest
    public Items buyChest(Hero Player, ShopIO shop) throws InsufficientFundsException
    {
        AuthorisePayment(Player.getCoins(),ChestPrice);
        
        //GUI Animation For Chest
        Items ChestItem = Initializer.InitializeItem(Player);
        
        CarryOutTransaction(Player,ChestPrice);
        Player.setItems(ChestItem);
        shop.setMessage(ChestItem.getInstance());
        return ChestItem;
    }
    //sets the first goldensword item training to true
    public void BuyTraining(Hero Player) throws InsufficientFundsException,ItemsException
    {
        //Method which throws InsufficientFundsException if player doesn't have enough coins
        AuthorisePayment(Player.getCoins(),TrainingPrice);
        ArrayList<Items> itemlist = Player.getItems();
        boolean foundAnItem = false;
        for(Items i: itemlist)
        {
            if((i instanceof GoldenSword))
            {
                GoldenSword sword = (GoldenSword)i;
                if(sword.getTrained() == false)
                {
                    sword.setTrainedTrue();
                    foundAnItem = true;
                    return;
                }                
            }
        }
        if(foundAnItem == false)
        {
            throw new ItemsException("You Dont Have A Golden Sword To Train With!");
        }
        else
        {
            CarryOutTransaction(Player,TrainingPrice);
        }
    }
    //increases the knifes sharpness
    public void BuySharpness(Hero Player) throws InsufficientFundsException,ItemsException
    {
        //Method which throws InsufficientFundsException if player doesn't have enough coins
        AuthorisePayment(Player.getCoins(),SharpenPrice);
        boolean foundAnItem = false;        
        ArrayList<Items> itemlist = Player.getItems();
        for(Items i: itemlist)
        {
            if(i instanceof Knife)
            {
                Knife knife = (Knife)i;
                knife.setSharpness(knife.getSharpness() + 0.2);
                foundAnItem = true;
            }
        }
        if(foundAnItem == false)
        {
            throw new ItemsException("No Knives to Sharpen!");
        }
        else
        {
            CarryOutTransaction(Player,SharpenPrice);
        }
    }
    //makes the first shield in the inventory of the player stronger
    public void BuyShieldPerk(Hero Player) throws InsufficientFundsException,ItemsException
    {
        //Method which throws InsufficientFundsException if player doesn't have enough coins
        AuthorisePayment(Player.getCoins(),ShieldPerkPrice);
        boolean foundAnItem = false;    
        ArrayList<Items> itemlist = Player.getItems();
        for(Items i: itemlist)
        {
            if(i instanceof Shield)
            {
                Shield shield = (Shield)i;
                shield.setShieldBlockBonus(shield.getShieldBlockBonus() + 2);
                CarryOutTransaction(Player,ShieldPerkPrice);
                return;
            }
        }        
        if(foundAnItem == false)
        {
            throw new ItemsException("You Have No Shields!");
        }
    }
    //checks whether player has sufficient funds to pay for the chest
    public void AuthorisePayment(int funds, int cost) throws InsufficientFundsException
    {
        if(funds < cost){throw new InsufficientFundsException("Payment Authorisation Failed: "+(cost-funds) + " more coins needed");}
        else {}
    }
    //Method to subtract coins from players account
    public void CarryOutTransaction(Hero Player, int amount)
    {
        Player.spendCoin(amount);
    }    
    /********** Getter Methods **********/
    //returns the price of the chest
    public int getChestPrice(){return ChestPrice;}
}
