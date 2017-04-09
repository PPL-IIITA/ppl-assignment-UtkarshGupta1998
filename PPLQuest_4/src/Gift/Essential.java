package Gift;
/**
 * Essential Gift Class inherit Gift class
 * @author user
 */
public class Essential extends Gift {
    /**
     * Constructor of Essential Gift Class
     * * @param Price Price of Gift
     * @param Value Value Of Gift
     */
    public Essential(int Price,int Value)
    {
        price=Price;
        value=Value;
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
