package Gift;
/**
 * Utility Gift Class inherit Gift class
 * @author user
 */
public class Utility extends Gift{
    public int utility_value;
    public int utility_class;
    /**
     * Constructor of Utility Gift Class
     * @param Price Price of Gift
     * @param Value Value Of Gift
     * @param utilityval Utility Value Of Gift
     * @param utilityclass Utility Class Of Gift
     */
    public Utility(int Price,int Value,int utilityval,int utilityclass)
    {
        price=Price;
        value=Value;
        utility_value=utilityval;
        utility_class=utilityclass;
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
