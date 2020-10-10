/**
 * Interface used by all characters to help standardise certain method calls
 *
 * @author (Yilmaz)
 * @version (3/3/2020)
 */
public interface Character
{
    public void takeDamage(int DamageGiven);
    public int getHp();
    public int getDamage();
    public String getName();
    public int primaryAttack();
    public int secondaryAttack();
    public int getCoins();
    public int dropCoins();
}