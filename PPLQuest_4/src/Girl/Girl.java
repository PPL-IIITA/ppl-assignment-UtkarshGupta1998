package Girl;
/**
 * Girl Class
 * @author user
 */
public class Girl {
    public String name;
    public String type;
    public boolean relationship_status;
    public String boyfriend_name;
    public int happiness;
    public int attractiveness;
    public int maintainence_budget;
    public int intelligence_level;
    public int cost_of_gifts;
    public String choosing_criterion;
    public int girlindex;
    /**
     * Girl Class Constructor
     * @param Name Name of Girl
     * @param typ Type of Girl
     * @param attr  Attractiveness Of Girl
     * @param mbudget Budget Of Girl
     * @param intlevel Intelligence Of Girl
     * @param criterion  Choosing criterion Of Girl
     * @param girlind Index of girl in inputted girls array
     */
    public Girl(String Name,String typ,int attr,int mbudget,int intlevel,String criterion,int girlind)
    {
        name=Name;
        type=typ;
        choosing_criterion=criterion;
        attractiveness=attr;
        maintainence_budget=mbudget;
        intelligence_level=intlevel;
        happiness=0;
        cost_of_gifts=0;
        relationship_status=false;
        girlindex=girlind;
    }

    public Girl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Assigns a boyfriend to a girl
     * @param Name Name of Boyfriend
     */
    public void SetBoyfriend(String Name)
    {
        boyfriend_name=Name;
    }
    /**
     * Sets relationship status of a girl as true
     */
    public void SetRelationship()
    {
        relationship_status=true;
    }
}
