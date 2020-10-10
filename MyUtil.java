import java.util.*;
/**
 * This class contains a range of static methods I will be using for utilitarian purposes
 *
 * @author (YK)
 * @version (5/3/2020)
 */
public abstract class MyUtil
{
    public static int random(int limit)
    {
        Random randomizer = new Random();
        return randomizer.nextInt(limit) + 1;
    }
    public static int randomFromZero(int limit)
    {
        Random randomizer = new Random();
        return randomizer.nextInt(limit);
    }
    
    //returns a string reponse from user after asking a question
    public static String Ask(String Message)
    {
        print(Message);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    //system.out.println a message
    public static void print(String Message)
    {
        System.out.println(Message);
    }
}
