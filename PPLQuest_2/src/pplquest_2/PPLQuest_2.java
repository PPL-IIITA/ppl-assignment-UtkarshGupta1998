package pplquest_2;
import Boy.*;
import Girl.*;
import Couple.*;
import Gift.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class PPLQuest_2 {
    static int no_of_couple=0;
    static void setCouple(Boy boy[],Girl girl[],Couple couple[],int boyindex,int girlindex,int coupleindex)
    {
        couple[coupleindex]=new Couple(boy[boyindex].name,girl[girlindex].name,boy[boyindex].attractiveness,girl[girlindex].attractiveness,boy[boyindex].intelligence_level,girl[girlindex].intelligence_level,boy[boyindex].budget,girl[girlindex].maintainence_budget,boy[boyindex].happiness,girl[girlindex].happiness,boyindex,girlindex);
        couple[coupleindex].SetCompatibility();    
    }        
    static void make_couple(Boy boy[],Girl girl[],Couple couple[],int no_of_boy,int no_of_girl) throws FileNotFoundException, IOException
    {
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("log.txt"));
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
    }
    static void girl_happiness(Girl girl[],int girlindex,int Value)
    {
        switch (girl[girlindex].type) {
                    case "choosy":
                        girl[girlindex].happiness=(int)log(girl[girlindex].cost_of_gifts);
                        break;
                    case "normal":
                        girl[girlindex].happiness=girl[girlindex].cost_of_gifts + Value;
                        break;
                    case "desperate":
                        girl[girlindex].happiness=(int)pow(1,girl[girlindex].cost_of_gifts);
                        break;
                    default:
                        break;
                }
    }
    static void exchange_gift(Boy boy[],Girl girl[],Couple couple[],Essential essential[],Utility utility[],Luxury luxury[],int no_of_boy,int no_of_girl,int no_of_couple,int no_of_essential,int no_of_utility,int no_of_luxury) throws FileNotFoundException, IOException
    {
        DataOutputStream PrintToLog = new DataOutputStream(new FileOutputStream("giftlog.txt"));
        for(int i=0;i<no_of_couple;i++)
        {
            int Value=0;
            int boyindex=couple[i].boyindex;
            int girlindex=couple[i].girlindex;
            if(boy[boyindex].type.equals("Miser"))
            {
                for(int j=0;j<no_of_essential;j++)
                {
                    if(essential[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0 && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=essential[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an essential gift of price " + essential[j].price + " and value " + essential[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=essential[j].price;
                       girl[girlindex].cost_of_gifts+=essential[j].price;
                       Value+=essential[j].value;
                       essential[j].taken();
                    }
                }
                for(int j=0;j<no_of_utility;j++)
                {
                    if(utility[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0 && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=utility[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an utility gift of price " + utility[j].price + " and value " + utility[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=utility[j].price;
                       girl[girlindex].cost_of_gifts+=utility[j].price;
                       Value+=utility[j].value;
                       utility[j].taken();
                    }
                }
                for(int j=0;j<no_of_luxury;j++)
                {
                    if(luxury[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0 && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=luxury[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an luxury gift of price " + luxury[j].price + " and value " + luxury[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=luxury[j].price;
                       girl[girlindex].cost_of_gifts+=luxury[j].price;
                       Value+=luxury[j].value;
                       luxury[j].taken();
                    }
                }
                girl_happiness(girl,girlindex,Value);
                boy[boyindex].happiness=boy[boyindex].budget-boy[boyindex].cost_of_gifts;
            }
            else if(boy[boyindex].type.equals("Generous"))
            {
                for(int j=0;j<no_of_essential;j++)
                {
                    if(essential[j].available==true && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=essential[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an essential gift of price " + essential[j].price + " and value " + essential[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=essential[j].price;
                       girl[girlindex].cost_of_gifts+=essential[j].price;
                       Value+=essential[j].value;
                       essential[j].taken();
                    }
                }
                for(int j=0;j<no_of_utility;j++)
                {
                    if(utility[j].available==true && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=utility[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an utility gift of price " + utility[j].price + " and value " + utility[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=utility[j].price;
                       girl[girlindex].cost_of_gifts+=utility[j].price;
                       Value+=utility[j].value;
                       utility[j].taken();
                    }
                }
                for(int j=0;j<no_of_luxury;j++)
                {
                    if(luxury[j].available==true && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=luxury[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an luxury gift of price " + luxury[j].price + " and value " + luxury[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=luxury[j].price;
                       girl[girlindex].cost_of_gifts+=luxury[j].price;
                       Value+=luxury[j].value;
                       luxury[j].taken();
                    }
                }
                girl_happiness(girl,girlindex,Value);
                boy[boyindex].happiness=girl[girlindex].happiness;
            }
            else if(boy[boyindex].type.equals("Geek"))
            {
                for(int j=0;j<no_of_essential;j++)
                {
                    if(essential[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0 && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=essential[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an essential gift of price " + essential[j].price + " and value " + essential[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=essential[j].price;
                       girl[girlindex].cost_of_gifts+=essential[j].price;
                       Value+=essential[j].value;
                       essential[j].taken();
                    }
                }
                for(int j=0;j<no_of_utility;j++)
                {
                    if(utility[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0 && boy[boyindex].budget-boy[boyindex].cost_of_gifts>=utility[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an utility gift of price " + utility[j].price + " and value " + utility[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=utility[j].price;
                       girl[girlindex].cost_of_gifts+=utility[j].price;
                       Value+=utility[j].value;
                       utility[j].taken();
                       
                    }
                }
                for(int j=0;j<no_of_luxury;j++)
                {
                    if(luxury[j].available==true && girl[girlindex].maintainence_budget-girl[girlindex].cost_of_gifts>=0&& boy[boyindex].budget-boy[boyindex].cost_of_gifts>=luxury[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an luxury gift of price " + luxury[j].price + " and value " + luxury[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=luxury[j].price;
                       girl[girlindex].cost_of_gifts+=luxury[j].price;
                       Value+=luxury[j].value;
                       luxury[j].taken();
                    }
                }
                for(int j=0;j<no_of_luxury;j++)
                {
                    if(luxury[j].available==true && boy[boyindex].budget-boy[boyindex].cost_of_gifts >= luxury[j].price)
                    {
                       PrintToLog.writeBytes(boy[boyindex].name + " gifted to " + girl[girlindex].name + " an luxury gift of price " + luxury[j].price + " and value " + luxury[j].value + " on " + LocalDate.now() + " " + LocalTime.now() + "\n" );
                       boy[boyindex].cost_of_gifts+=luxury[j].price;
                       girl[girlindex].cost_of_gifts+=luxury[j].price;
                       Value+=luxury[j].value;
                       luxury[j].taken();
                       break;
                    }
                }
                girl_happiness(girl,girlindex,Value);
                boy[boyindex].happiness=girl[girlindex].intelligence_level;
            }
            couple[i].happiness=boy[boyindex].happiness+girl[girlindex].happiness;
        }
    }
    static void swap(Couple couple[],int a,int b)
    {
        String boy_name=couple[a].boy_name;
        String girl_name=couple[a].girl_name;
        int happiness=couple[a].happiness;
        int compatibility=couple[a].compatibility;
        int attractiveness_diff=couple[a].attractiveness_diff;
        int intelligence_diff=couple[a].intelligence_diff;
        int budget_diff=couple[a].budget_diff;
        int boyindex=couple[a].boyindex;
        int girlindex=couple[a].girlindex;
        couple[a].boy_name=couple[b].boy_name;
        couple[a].girl_name=couple[b].girl_name;
        couple[a].happiness=couple[b].happiness;
        couple[a].compatibility=couple[b].compatibility;
        couple[a].attractiveness_diff=couple[b].attractiveness_diff;
        couple[a].intelligence_diff=couple[b].intelligence_diff;
        couple[a].budget_diff=couple[b].budget_diff;
        couple[a].boyindex=couple[b].boyindex;
        couple[a].girlindex=couple[b].girlindex;
        couple[b].boy_name=boy_name;
        couple[b].girl_name=girl_name;
        couple[b].happiness=happiness;
        couple[b].compatibility=compatibility;
        couple[b].attractiveness_diff=attractiveness_diff;
        couple[b].intelligence_diff=intelligence_diff;
        couple[b].budget_diff=budget_diff;
        couple[b].boyindex=boyindex;
        couple[b].girlindex=girlindex;
    }
    static void kmostcompatible(Couple couple[],int no_of_couple,int k) throws FileNotFoundException, IOException
    {
        DataOutputStream GoToLog = new DataOutputStream(new FileOutputStream("kmostcompatiblelog.txt"));
        for(int i=0;i<no_of_couple-1;i++)
            for(int j=0;j<no_of_couple-i-1;j++)
                if(couple[j].compatibility<couple[j+1].compatibility)
                    swap(couple,j,j+1);
        for(int i=0;i<k&&i<no_of_couple;i++)
            GoToLog.writeBytes(couple[i].boy_name + " and " + couple[i].girl_name + " have compatiblity  value " + couple[i].compatibility + "\n" );
            
    }
    static void kmosthappiest(Couple couple[],int no_of_couple,int k) throws FileNotFoundException, IOException
    {
        DataOutputStream DirectToLog = new DataOutputStream(new FileOutputStream("kmosthappiestlog.txt"));
        for(int i=0;i<no_of_couple-1;i++)
            for(int j=0;j<no_of_couple-i-1;j++)
                if(couple[j].happiness<couple[j+1].happiness)
                    swap(couple,j,j+1);
        for(int i=0;i<k&&i<no_of_couple;i++)
            DirectToLog.writeBytes(couple[i].boy_name + " and " + couple[i].girl_name + " have happiness  value " + couple[i].happiness + "\n" );
            
    }
    public static void main(String[] args) throws IOException {
        Boy boy[]=new Boy[107];
        Girl girl[]=new Girl[107];
        Couple couple[]=new Couple[107];
        Essential essential[]=new Essential[107];
        Utility utility[]=new Utility[107];
        Luxury luxury[]=new Luxury[107];
        int no_of_boy=0;
        int no_of_girl=0;
        int no_of_essential=0;
        int no_of_utility=0;
        int no_of_luxury=0;
        String csvFile = "girls_data.csv";
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] g = line.split(cvsSplitBy);
                String name = g[0];
                String type=g[1];
                int attractiveness = Integer.parseInt(g[2]);
                int budget = Integer.parseInt(g[3]);
                int intelligence = Integer.parseInt(g[4]);
                String criterion=g[5];
                girl[no_of_girl++]=new Girl(name,type,attractiveness,budget,intelligence,criterion);
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        csvFile = "boys_data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] b = line.split(cvsSplitBy);
                String name = b[0];
                String type=b[1];
                int attractiveness = Integer.parseInt(b[2]);
                int budget = Integer.parseInt(b[3]);
                int intelligence = Integer.parseInt(b[4]);
                int min_attractiveness_req=Integer.parseInt(b[5]);
                boy[no_of_boy++]=new Boy(name,type,attractiveness,budget,intelligence,min_attractiveness_req);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        csvFile = "essential_data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] b = line.split(cvsSplitBy);
                int price = Integer.parseInt(b[0]);
                int value = Integer.parseInt(b[1]);
                essential[no_of_essential++]=new Essential(price,value);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        csvFile = "utility_data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] b = line.split(cvsSplitBy);
                int price = Integer.parseInt(b[0]);
                int value = Integer.parseInt(b[1]);
                int ultity_Val=Integer.parseInt(b[2]);
                int utility_class=Integer.parseInt(b[3]);
                utility[no_of_utility++]=new Utility(price,value,ultity_Val,utility_class);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        csvFile = "luxury_data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] b = line.split(cvsSplitBy);
                int price = Integer.parseInt(b[0]);
                int value = Integer.parseInt(b[1]);
                int luxury_rating=Integer.parseInt(b[2]);
                int diff_rating=Integer.parseInt(b[3]);
                luxury[no_of_luxury++]=new Luxury(price,value,luxury_rating,diff_rating);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        make_couple(boy,girl,couple,no_of_boy,no_of_girl);
        exchange_gift(boy,girl,couple,essential,utility,luxury,no_of_boy,no_of_girl,no_of_couple,no_of_essential,no_of_utility,no_of_luxury);
        kmostcompatible(couple,no_of_couple,5);
        kmosthappiest(couple,no_of_couple,6);
    }
}
