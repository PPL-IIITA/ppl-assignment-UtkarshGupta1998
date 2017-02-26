package pplquest_1;
import Boy.*;
import Girl.*;
import Couple.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class PPLQuest_1 {
    static int no_of_couple=0;
    static void setCouple(Boy boy[],Girl girl[],Couple couple[],int boyindex,int girlindex,int coupleindex)
    {
        couple[coupleindex]=new Couple(boy[boyindex].name,girl[girlindex].name,boy[boyindex].attractiveness,girl[girlindex].attractiveness,boy[boyindex].intelligence_level,girl[girlindex].intelligence_level,boy[boyindex].budget,girl[girlindex].maintainence_budget,boy[boyindex].happiness,girl[girlindex].happiness);
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
    public static void main(String[] args) throws IOException {
        Boy boy[]=new Boy[107];
        Girl girl[]=new Girl[107];
        Couple couple[]=new Couple[107];
        int no_of_boy=0;
        int no_of_girl=0;
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
        make_couple(boy,girl,couple,no_of_boy,no_of_girl);
    }
}
