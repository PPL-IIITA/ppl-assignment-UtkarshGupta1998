package pplquest_4;
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
public class PPLQuest_4 {
    static int no_of_couple=0;
    /**
     *  This function calculates the girl's happiness. 
       Different girls have different happiness determining formulas.
     * @param girl
     * @param girlindex
     * @param Value 
     */
    static void girl_happiness(Girl girl[],int girlindex,int Value)
    {
        /*
            This function calculates the girl's happiness
            Different girls have different happiness determining formulas.
        */
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
    /**
     * This function facilitates the exchange of gifts between the couples formed. 
        The gift exchanging algorithm takes into account the requirement of the girl and the budget of the boy as well as the other criterion specified in the problem
     * @param boy
     * @param girl
     * @param couple
     * @param essential
     * @param utility
     * @param luxury
     * @param no_of_boy
     * @param no_of_girl
     * @param no_of_couple
     * @param no_of_essential
     * @param no_of_utility
     * @param no_of_luxury
     * @throws FileNotFoundException
     * @throws IOException 
     */
    static void exchange_gift(Boy boy[],Girl girl[],Couple couple[],Essential essential[],Utility utility[],Luxury luxury[],int no_of_boy,int no_of_girl,int no_of_couple,int no_of_essential,int no_of_utility,int no_of_luxury) throws FileNotFoundException, IOException
    {
        /*
            This function facilitates the exchange of gifts between the couples formed
            The gift exchanging algorithm takes into account the requirement of the girl and the budget of the boy as weel as the other criterion specified in the problem
        */
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
        /*
            Reading girl data file
        */
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
                girl[no_of_girl++]=new Girl(name,type,attractiveness,budget,intelligence,criterion,no_of_girl-1);
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        /*
            Reading Boy data file
        */
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
                boy[no_of_boy++]=new Boy(name,type,attractiveness,budget,intelligence,min_attractiveness_req,no_of_boy-1);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        /*
            Reading Essential Gift data file
        */
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
        /*
            Reading Utility Gift data file
        */
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
        /*
            Reading Luxury Gift data file
        */
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
        Couple tmp=new Couple(" "," ",0,0,0,0,0,0,0,0,0,0);
        no_of_couple=tmp.make_couple(boy,girl,couple,no_of_boy,no_of_girl);
        exchange_gift(boy,girl,couple,essential,utility,luxury,no_of_boy,no_of_girl,no_of_couple,no_of_essential,no_of_utility,no_of_luxury);
        tmp.kmostcompatible(couple,no_of_couple,5);
        tmp.kmosthappiest(couple,no_of_couple,6);
        no_of_couple=tmp.breakup(couple, girl, boy, no_of_couple,5);
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("all_couples_after_reassingment.txt"));
        int i;
        for(i=0;i<no_of_couple;i++)
            WriteToLog.writeBytes(couple[i].girl_name + " got committed to " + couple[i].boy_name + "\n");
    }
}
