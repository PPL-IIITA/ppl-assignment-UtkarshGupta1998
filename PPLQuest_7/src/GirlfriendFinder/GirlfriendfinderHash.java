package GirlfriendFinder;

import Boy.Boy;
import Couple.Couple;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GirlfriendfinderHash inherit GirlfriendFinder Class
 * @author user
 */
public class GirlfriendfinderHash extends GirfriendFinder{
    /**
     * Uses a builtin hashmap to search for girlfriend of given boy
     * @param couple This array contains information about all the couples
     * @param com_boy This array contains a list of boys whose girlfriends need to be searched
     * @param boy  This array contains all the boys
     * @param no_of_couple Total No of couples
     * @param no_of_comboy Size of inputted array of boys whose girlfriend needs to be searched
     * @throws FileNotFoundException
     * @throws IOException 
     */
     public void girlfinder(Couple couple[],String com_boy[],Boy boy[],int no_of_couple,int no_of_comboy) throws FileNotFoundException, IOException
    {
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("girlfriend_of_commitedboys.txt"));
        int i;
        Map<String,String>mp=new HashMap<String,String>();
        for(i=0;i<no_of_couple;i++)
            mp.put(couple[i].boy_name, couple[i].girl_name);
        for(i=0;i<no_of_comboy;i++)
            if(mp.get(com_boy[i])!=null)
                WriteToLog.writeBytes(mp.get(com_boy[i]) +" is committed to " + com_boy[i] + "\n");
            else
                WriteToLog.writeBytes(com_boy[i] + "is not committed " + "\n");
        
    }    
}
