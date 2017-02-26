package Girl;
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
    public Girl(String Name,String typ,int attr,int mbudget,int intlevel,String criterion)
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
    }
    public void SetBoyfriend(String Name)
    {
        boyfriend_name=Name;
    }
    public void SetRelationship()
    {
        relationship_status=true;
    }
}
