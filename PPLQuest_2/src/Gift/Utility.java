package Gift;
public class Utility {
    public int price;
    public int value;
    public int utility_value;
    public int utility_class;
    public boolean available;
    public Utility(int Price,int Value,int utilityval,int utilityclass)
    {
        price=Price;
        value=Value;
        utility_value=utilityval;
        utility_class=utilityclass;
        available=true;
    }
    public void taken()
    {
        available=false;
    }
}
