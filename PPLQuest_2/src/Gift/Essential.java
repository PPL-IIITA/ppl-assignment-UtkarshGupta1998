package Gift;
public class Essential {
    public int price;
    public int value;
    public boolean available;
    public Essential(int Price,int Value)
    {
        price=Price;
        value=Value;
        available=true;
    }
    public void taken()
    {
        available=false;
    }
}
