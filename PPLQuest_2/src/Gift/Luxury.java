package Gift;
public class Luxury {
    public int price;
    public int value;
    public int luxury_rating;
    public int difficulty_rating;
    public boolean available;
    public Luxury(int Price,int Value,int luxuryrating,int diffrating)
    {
        price=Price;
        value=Value;
        luxury_rating=luxuryrating;
        difficulty_rating=diffrating;
        available=true;
    }
    public void taken()
    {
        available=false;
    }
}
