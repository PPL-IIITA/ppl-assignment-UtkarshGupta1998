package pplquest_5;
import Boy.*;
import Girl.*;
import Couple.*;
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
import java.util.Scanner;
public class PPLQuest_5 {
    static int no_of_couple=0;
    /**
     * This function calculates the girl's happiness. 
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
    public static void main(String[] args) throws IOException {
        Boy boy[]=new Boy[107];
        Girl girl[]=new Girl[107];
        Couple couple[]=new Couple[107];
        int no_of_boy=0;
        int no_of_girl=0;
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
        Couple tmp=new Couple(" "," ",0,0,0,0,0,0,0,0,0,0);
        System.out.println("Choose couple forming algorithm\n 1.Each girl is assigned a boy \n 2.Girls and Boys are assigned partner alternately\n");
        Scanner tp=new Scanner(System.in);
        int a=tp.nextInt();
        if(a==1)
            no_of_couple=tmp.make_couple(boy,girl,couple,no_of_boy,no_of_girl);
        else
            no_of_couple=tmp.make_couple(boy,girl,couple,no_of_boy,no_of_girl,1);
        tmp.kmosthappiest(couple, no_of_couple, a);
    }
}
