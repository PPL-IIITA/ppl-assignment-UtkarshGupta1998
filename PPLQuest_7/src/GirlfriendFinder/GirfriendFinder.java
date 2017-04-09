package GirlfriendFinder;
import Boy.*;
import Girl.*;
import Couple.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *GirlfriendFinder Base Class
 * @author user
 */
public class GirfriendFinder {
    /**
     * Swap function for used in sorting the Boy according to their name
     * @param bboy array of all the commited boys
     * @param a index of array whose contents needs to be swapped
     * @param b index of array whose contents needs to be swapped
     */
    public void swapBoy(Boy bboy[],int a,int b)
    {
        Boy tmp;
        tmp=bboy[a];
        bboy[a]=bboy[b];
        bboy[b]=tmp;
    }
    /**
     * Sort function for sorting the Boy according to their name
     * @param bboy array of all the commited boys
     * @param no_of_boy total no of commited boys
     */
    public void sortBoy(Boy bboy[],int no_of_boy)
    {
        int i,j;
        for(i=0;i<no_of_boy;i++)
                for(j=0;j<no_of_boy-i-1;j++)
                       if(bboy[j].name.compareTo(bboy[j+1].name)>0)
                           swapBoy(bboy,j,j+1);
    }
    /**
     * Default Girlfriend finder function. 
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
