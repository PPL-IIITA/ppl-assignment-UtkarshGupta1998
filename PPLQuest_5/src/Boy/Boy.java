package Boy;
/**
 * Boy Class
 * @author user
 */
public class Boy {
    public String name;
    public String type;
    public boolean relationship_status;
    public String girlfriend_name;
    public int happiness;
    public int attractiveness;
    public int budget;
    public int intelligence_level;
    public int cost_of_gifts;
    public int attraction_requirement;
    public int boyindex;
    /**
     * Constructor of Boy Class
     * @param Name Name of Boy
     * @param typ Type of Boy
     * @param attr  Attractiveness Of Boy
     * @param mbudget Budget Of Boy
     * @param intlevel Intelligence Of Boy
     * @param atreq  Attractiveness Of Boy
     * @param boyind Index of boy in inputted boys array
     */
    public Boy(String Name,String typ,int attr,int mbudget,int intlevel,int atreq,int boyind)
    {
        name=Name;
        type=typ;
        attractiveness=attr;
        budget=mbudget;
        intelligence_level=intlevel;
        happiness=0;
        cost_of_gifts=0;
        relationship_status=false;
        attraction_requirement=atreq;
        boyindex=boyind;
    }
    /**
     * Assigns a girlfriend to a boy
     * @param Name  Name of Girlfriend
     */
    public void SetGirlfriend(String Name)
    {
        girlfriend_name=Name;
    }
    /**
     * Sets relationship status of a boy as true
     */
    public void SetRelationship()
    {
        relationship_status=true;
    }
}
