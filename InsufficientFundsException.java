
/**
 * Exception method that is used whenever there is insufficient funds and contains the message on why
 *
 * @author (YK)
 * @version (9/3/20)
 */
public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(String Message){super(Message);}    
}
