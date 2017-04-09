package Gift;
/**
 * Luxury Gift Class inherit Gift Class
 * @author user
 */
public class Luxury extends Gift{
    public int luxury_rating;
    public int difficulty_rating;
    /**
     * Constructor of Luxury Gift Class
     * @param Price Price of Gift
     * @param Value Value Of Gift
     * @param luxuryrating Luxury Rating Of Gift
     * @param diffrating Difficulty Rating Of Gift
     */
    public Luxury(int Price,int Value,int luxuryrating,int diffrating)
    {
        price=Price;
        value=Value;
        luxury_rating=luxuryrating;
        difficulty_rating=diffrating;
        available=true;
    }
    @Override
    /**
     * Sets the availability status of the gift as false
     */
    public void taken()
    {
        available=false;
    }
}
