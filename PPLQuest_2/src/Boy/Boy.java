package Boy;
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
    public Boy(String Name,String typ,int attr,int mbudget,int intlevel,int atreq)
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
    }
    public void SetGirlfriend(String Name)
    {
        girlfriend_name=Name;
    }
    public void SetRelationship()
    {
        relationship_status=true;
    }
}
