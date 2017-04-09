package Couple;
import Girl.*;
import Boy.*;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Math.abs;
import java.time.LocalDate;
import java.time.LocalTime;
/**
*Couple class consists of couple information
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
     *    Setcompatibility method of couple class calculates the compatibility of the formed couple
    */
    public void SetCompatibility()
    {
         compatibility=attractiveness_diff+intelligence_diff+budget_diff;
    }
    /**
     *   This function creates a new object of type couple. 
    *    The boy and girl forming a couple is specified in the function parameter
     * @param boy
     * @param girl
     * @param couple
     * @param boyindex
     * @param girlindex
     * @param coupleindex 
     */
    public void setCouple(Boy boy[],Girl girl[],Couple couple[],int boyindex,int girlindex,int coupleindex)
    {
        
        couple[coupleindex]=new Couple(boy[boyindex].name,girl[girlindex].name,boy[boyindex].attractiveness,girl[girlindex].attractiveness,boy[boyindex].intelligence_level,girl[girlindex].intelligence_level,boy[boyindex].budget,girl[girlindex].maintainence_budget,boy[boyindex].happiness,girl[girlindex].happiness,boyindex,girlindex);
        couple[coupleindex].SetCompatibility(); 
    } 
    /**
     * This function assigns to each girl a boy based on the requirement of the boy of a particular type.
    *   Return the no of couple formed
     * This function is responsible for formation of couple.
     * Each girl is assigned a boy based on her requirement
     *  @param boy Boys Array
     * @param girl Girls Array
     * @param couple Couples Array
     * @param no_of_boy No of boys
     * @param no_of_girl No of Girls
     * @return no of couples formed
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int make_couple(Boy boy[],Girl girl[],Couple couple[],int no_of_boy,int no_of_girl) throws FileNotFoundException, IOException
    {
        
        int no_of_couple=0;
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("all_couples_before_breakup.txt"));
        for(int i=0;i<no_of_girl;i++)
        {
            int index=-1;
            int curr_max=0;
            for(int j=0;j<no_of_boy;j++)
            {
                if(boy[j].relationship_status==false && boy[j].budget>=girl[i].maintainence_budget && girl[i].attractiveness>=boy[j].attraction_requirement)
                       if(girl[i].choosing_criterion.equals("Attractiveness"))
                       {   if(boy[j].attractiveness > curr_max)
                           {
                               curr_max = boy[j].attractiveness;
                               index=j;
                           }
                       }
                       else if(girl[i].choosing_criterion.equals("Richness"))
                       {   if(boy[j].budget > curr_max)
                           {
                               curr_max = boy[j].budget;
                               index=j;
                           }
                       }
                       else if(girl[i].choosing_criterion.equals("Intelligence"))
                       {   if(boy[j].intelligence_level > curr_max)
                           {
                               curr_max = boy[j].intelligence_level;
                               index=j;
                           }
                       }
            }
            if(index!=-1)
            {
                WriteToLog.writeBytes(girl[i].name+" got committed to "+boy[index].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
                girl[i].SetRelationship();
                girl[i].SetBoyfriend(boy[index].name);
                boy[index].SetGirlfriend(girl[i].name);
                boy[index].SetRelationship();
                setCouple(boy,girl,couple,index,i,no_of_couple);
                no_of_couple++;
            }
        }
        return no_of_couple;
    }
    /**
     * Swap function used in sorting
     * @param couple
     * @param a
     * @param b 
     */
    public void swap(Couple couple[],int a,int b)
    {
        String bboy_name=couple[a].boy_name;
        String ggirl_name=couple[a].girl_name;
        int hhappiness=couple[a].happiness;
        int commpatibility=couple[a].compatibility;
        int aattractiveness_diff=couple[a].attractiveness_diff;
        int inttelligence_diff=couple[a].intelligence_diff;
        int bbudget_diff=couple[a].budget_diff;
        int bboyindex=couple[a].boyindex;
        int ggirlindex=couple[a].girlindex;
        couple[a].boy_name=couple[b].boy_name;
        couple[a].girl_name=couple[b].girl_name;
        couple[a].happiness=couple[b].happiness;
        couple[a].compatibility=couple[b].compatibility;
        couple[a].attractiveness_diff=couple[b].attractiveness_diff;
        couple[a].intelligence_diff=couple[b].intelligence_diff;
        couple[a].budget_diff=couple[b].budget_diff;
        couple[a].boyindex=couple[b].boyindex;
        couple[a].girlindex=couple[b].girlindex;
        couple[b].boy_name=bboy_name;
        couple[b].girl_name=ggirl_name;
        couple[b].happiness=hhappiness;
        couple[b].compatibility=commpatibility;
        couple[b].attractiveness_diff=aattractiveness_diff;
        couple[b].intelligence_diff=inttelligence_diff;
        couple[b].budget_diff=bbudget_diff;
        couple[b].boyindex=bboyindex;
        couple[b].girlindex=ggirlindex;
    }
    /**
     * This function sorts the couples in the decreasing order of their compatibility and prints the k most compatible couples in a file.

     * @param couple
     * @param no_of_couple
     * @param k
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void kmostcompatible(Couple couple[],int no_of_couple,int k) throws FileNotFoundException, IOException
    {
        
        DataOutputStream GoToLog = new DataOutputStream(new FileOutputStream("kmostcompatiblelog.txt"));
        for(int i=0;i<no_of_couple-1;i++)
            for(int j=0;j<no_of_couple-i-1;j++)
                if(couple[j].compatibility<couple[j+1].compatibility)
                    swap(couple,j,j+1);
        for(int i=0;i<k&&i<no_of_couple;i++)
            GoToLog.writeBytes(couple[i].boy_name + " and " + couple[i].girl_name + " have compatiblity  value " + couple[i].compatibility + "\n" );
            
    }
    /**
     * This function sorts the couples in the decreasing order of their happiness and prints the k most happiest couples in a file

     * @param couple
     * @param no_of_couple
     * @param k
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void kmosthappiest(Couple couple[],int no_of_couple,int k) throws FileNotFoundException, IOException
    {
        DataOutputStream DirectToLog = new DataOutputStream(new FileOutputStream("kmosthappiestlog.txt"));
        for(int i=0;i<no_of_couple-1;i++)
            for(int j=0;j<no_of_couple-i-1;j++)
                if(couple[j].happiness<couple[j+1].happiness)
                    swap(couple,j,j+1);
        for(int i=0;i<k&&i<no_of_couple;i++)
            DirectToLog.writeBytes(couple[i].boy_name + " and " + couple[i].girl_name + " have happiness  value " + couple[i].happiness + "\n" );
            
    }
    /**
     * This function sorts the couple array in the increasing order of their happiness.

     * @param couple
     * @param no_of_couple
     * @param k
     * @throws FileNotFoundException
     * @throws IOException 
     */
    void kleasthappiest(Couple couple[],int no_of_couple,int k) throws FileNotFoundException, IOException
    {
        
        DataOutputStream DirectToLog = new DataOutputStream(new FileOutputStream("kleasthappiestlog.txt"));
        for(int i=0;i<no_of_couple-1;i++)
            for(int j=0;j<no_of_couple-i-1;j++)
                if(couple[j].happiness>couple[j+1].happiness)
                    swap(couple,j,j+1);
        for(int i=0;i<k&&i<no_of_couple;i++)
            DirectToLog.writeBytes(couple[i].boy_name + " and " + couple[i].girl_name + " have happiness  value " + couple[i].happiness + "\n" );
    }
    /**
     * This function reassigns a girl to a boy after their breakup from previous partner

     * @param couple
     * @param girl
     * @param boy
     * @param ggirl
     * @param bboy
     * @param k
     * @return no of couples reassigned
     * @throws IOException 
     */
    public int reassign(Couple couple[],Girl girl[],Boy boy[],Girl ggirl[],Boy bboy[],int k) throws IOException
    {
        
        int no_of_couple=0;
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("reassign.txt"));
        for(int i=k-1;i>=0;i--)
        {
            int index=-1;
            int curr_max=0;
            for(int j=k-1;j>=0;j--)
            {
                if(bboy[j].relationship_status==false && bboy[j].girlfriend_name.equals(ggirl[i].name)==false && bboy[j].budget>=ggirl[i].maintainence_budget && ggirl[i].attractiveness>=bboy[j].attraction_requirement)
                       if(ggirl[i].choosing_criterion.equals("Attractiveness"))
                       {   if(bboy[j].attractiveness > curr_max)
                           {
                               curr_max = bboy[j].attractiveness;
                               index=j;
                           }
                       }
                       else if(ggirl[i].choosing_criterion.equals("Richness"))
                       {   if(bboy[j].budget > curr_max)
                           {
                               curr_max = bboy[j].budget;
                               index=j;
                           }
                       }
                       else if(ggirl[i].choosing_criterion.equals("Intelligence"))
                       {   if(bboy[j].intelligence_level > curr_max)
                           {
                               curr_max = bboy[j].intelligence_level;
                               index=j;
                           }
                       }
            }
            if(index!=-1)
            {
                WriteToLog.writeBytes(ggirl[i].name+" got reassigned to "+bboy[index].name+" on "+LocalDate.now()+" "+LocalTime.now()+"\n");
                ggirl[i].SetRelationship();
                ggirl[i].SetBoyfriend(boy[index].name);
                bboy[index].SetGirlfriend(girl[i].name);
                bboy[index].SetRelationship();
                girl[ggirl[i].girlindex]=ggirl[i];
                boy[bboy[index].boyindex]=bboy[index];
                setCouple(boy,girl,couple,bboy[index].boyindex,ggirl[i].girlindex,no_of_couple);
                no_of_couple++;
            }
        }
        return no_of_couple;
    }
    /**
     * This function performs a breakup between k previous formed least happiest couples and then assigns them new partner

     * @param couple
     * @param girl
     * @param boy
     * @param no_of_couple
     * @param k
     * @return no of couples after breakup and reassignment
     * @throws IOException 
     */
    public int breakup(Couple couple[],Girl girl[],Boy boy[],int no_of_couple,int k) throws IOException
    {
        kleasthappiest(couple,no_of_couple,k);
        Girl ggirl[]=new Girl[k+1];
        Boy bboy[]=new Boy[k+1];
        int i,j;
        for(i=0;i<k;i++)
        {
            girl[couple[i].girlindex].relationship_status=false;
            boy[couple[i].boyindex].relationship_status=false;
            ggirl[i]=girl[couple[i].girlindex];
            bboy[i]=boy[couple[i].boyindex];
        }
        int tmp=reassign(couple,girl,boy,ggirl,bboy,k);
        i=k;
        j=tmp;
        while(i<no_of_couple)
        {
            couple[j]=couple[i];
            i++;
            j++;
        }
        return j;
    }
}
