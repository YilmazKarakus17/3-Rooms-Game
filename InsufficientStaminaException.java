
/**
 * My own checked exception class that is used when player does not have enough stamina and it is thrown 
 *
 * @author (YK)
 * @version (14/3/2020)
 */
public class InsufficientStaminaException extends Exception
{
    public InsufficientStaminaException()
    {
        super("Not Enough Stamina!");
    }
}
