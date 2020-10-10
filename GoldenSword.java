public final class GoldenSword extends Items
{
    private int IncreaseSpeed = 2;
    private int gracefullyUsedAmount;
    private boolean Trained;
    private String ItemInfoPassive = "Golden Sword: \nPassive Ability: once picked up item adds 2 to the players maximum speed points and when used it has a damage of 1000"; 
    private String ItemInfoUniqueAffect = "\nUnique Affect: Every time player attacks the amount of times used is incremented and it is added with its own damage points PLUS half of its durability points";
    /************* Constructors **********/
    public GoldenSword()
    {
        super(1000,2,50);
        Trained = false;
    }
    
    public GoldenSword(Berserker Player)
    {
        super(1000,2,50);
        Player.setSpeed(Player.getSpeed() + IncreaseSpeed);
        gracefullyUsedAmount = 0;
        Trained = false;
    }

    public GoldenSword(Speedster Player)
    {
        super(1000,2,50);
        Player.setSpeed(Player.getSpeed() + IncreaseSpeed); 
        gracefullyUsedAmount = 0;
        Trained = false;
    }

    public GoldenSword(Hero Player)
    {
        super(1000,2,50);
        Player.setSpeed(Player.getSpeed() + IncreaseSpeed);        
        gracefullyUsedAmount = 0;
        Trained = false;
    }
    /******* Instance methods *******/
    //setter method
    public void setTrainedTrue()
    {
        Trained = true;
    }
    //gets whether player has trained with item
    public boolean getTrained()
    {
        return Trained;
    }
    //increments the counter for amount of uses
    public void incrementUses()
    {
       gracefullyUsedAmount = gracefullyUsedAmount + 1; 
    }
   /***************** Overriding Methods *****************/
   //method calculates damage of weapon based on whether player has trained 
    public int AffectOnPlayerDamage()
    {        
        if(Trained == false)
        {
            return getDamage() + (int)(getDurability()*0.5);           
        }
        else
        {
            incrementUses(); //calling this method means player is using item
            return getDamage() + gracefullyUsedAmount + (int)(getDurability()*0.5);            
        }       
    }
    //Method that returns the int value of the additional armour it give to the player every use
    public double AffectOnPlayerArmour()
    {
        return getBlock();
    }
    //Method that decreases the durability of the item every use    
    public void DecreaseDurability()
    {
        if(getDurability() > 1)
        {
            setDurability(getDurability()-1.5);
        }
        else
        {
          setDurability(1);  
        }
    }
    //Function that concatenates information about the Item and returns its String value
    public String getInfo()
    {
        return ItemInfoPassive + ItemInfoUniqueAffect;
    }
    //returns goldensword
    public String getInstance()
    {
        return "goldensword";
    }
}