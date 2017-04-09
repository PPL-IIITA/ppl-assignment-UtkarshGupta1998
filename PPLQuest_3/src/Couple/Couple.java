package Couple;
import static java.lang.Math.abs;
/**
 * Couple class consists of couple information
 * @author user
 */
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
    /**
     * Couple Class Constructor
     * @param Boy_name name of Boy
     * @param Girl_name name of Girl
     * @param atboy attractiveness of Boy
     * @param atgirl attractiveness of Girl
     * @param intboy intelligence of Boy
     * @param intgirl intelligence of Girl
     * @param budboy budget of Boy
     * @param budgirl maintainence_budget of Girl
     * @param hapboy happiness of Boy
     * @param hapgirl happiness of Girl
     * @param boy_index index of boy in inputted boys array
     * @param girl_index index of boy in inputted girls array
     */
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
    /**
     * Setcompatibility method of couple class calculates the compatibility of the formed couple
     */
    public void SetCompatibility()
    {
         compatibility=attractiveness_diff+intelligence_diff+budget_diff;
    }
}
