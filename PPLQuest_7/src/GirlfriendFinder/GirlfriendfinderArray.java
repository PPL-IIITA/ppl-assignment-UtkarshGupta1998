
package GirlfriendFinder;

import Boy.Boy;
import Couple.Couple;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * GirlfriendfinderArray inherit GirlfriendFinder Class
 * @author user
 */
public class GirlfriendfinderArray extends GirfriendFinder {

   /**
    * This function uses a linear search to search the girlfriend of the given boy.
    * @param couple This array contains information about all the couples
     * @param com_boy This array contains a list of boys whose girlfriends need to be searched
     * @param boy  This array contains all the boys
     * @param no_of_couple Total No of couples
     * @param no_of_comboy Size of inputted array of boys whose girlfriend needs to be searched
    * @throws IOException 
    */
public void girlfinder(Couple couple[],String com_boy[],Boy boy[],int no_of_couple,int no_of_comboy) throws IOException
    {
        DataOutputStream WriteToLog = new DataOutputStream(new FileOutputStream("girlfriend_of_commitedboys.txt"));
        int i,j;
        Boy bboy[]=new Boy[no_of_couple];
        for(i=0;i<no_of_couple;i++)
            bboy[i]=boy[couple[i].boyindex];
        for(i=0;i<no_of_comboy;i++)
        {
            for(j=0;j<no_of_couple;j++)
            {  
                if(bboy[j].name.equals(com_boy[i]))
                {    
                    WriteToLog.writeBytes(bboy[j].girlfriend_name+" is committed to " + com_boy[i] + "\n");
                    break;
                }
            }
            if(j>=no_of_couple)
                WriteToLog.writeBytes(com_boy[i]+" is not committed " + "\n");
        }
    }    
}
