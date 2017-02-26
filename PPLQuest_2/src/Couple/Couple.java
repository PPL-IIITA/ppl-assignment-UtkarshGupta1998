package Couple;
import static java.lang.Math.abs;
public class Couple {
    public String boy_name;
    public String girl_name;
    public int happiness;
    public int compatibility;
    public int attractiveness_diff;
    public int intelligence_diff;
    public int budget_diff;
    public int boyindex;
    public int girlindex;
    public Couple(String Boy_name,String Girl_name,int atboy,int atgirl,int intboy,int intgirl,int budboy,int budgirl,int hapboy,int hapgirl,int boy_index,int girl_index)
    {
        boy_name=Boy_name;
        girl_name=Girl_name;
        attractiveness_diff=abs(atboy-atgirl);
        intelligence_diff=abs(intboy-intgirl);
        budget_diff=abs(budboy-budgirl);
        happiness=hapboy+hapgirl;
        boyindex=boy_index;
        girlindex=girl_index;
    }
    public void SetCompatibility()
    {
         compatibility=attractiveness_diff+intelligence_diff+budget_diff;
    }
}
