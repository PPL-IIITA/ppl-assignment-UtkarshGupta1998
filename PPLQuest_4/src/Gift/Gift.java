package Gift;
/**
 * Abstract class Gift
 * @author user
 */
public abstract class Gift {
    public int price;
    public int value;
    public boolean available;
    /**
     * Sets the availability status of the gift as false
     */
    public abstract void taken();
}
