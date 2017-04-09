/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GirlfriendFinder;

import Boy.Boy;
import Couple.Couple;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * GirlfriendfinderSorted inherit GirlfriendFinder Class
 * @author user
 */
public class GirlfriendfinderSorted extends GirfriendFinder {
    /**
     * Function Uses Binary search to search the girlfriend of the given boys.
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
        int i,j;
        Boy bboy[]=new Boy[no_of_couple];
        for(i=0;i<no_of_couple;i++)
            bboy[i]=boy[couple[i].boyindex];
        sortBoy(bboy,no_of_couple);
        for(i=0;i<no_of_comboy;i++)
        {
            int f=0,lp=0,llp=no_of_couple-1;
            while(lp<llp)
            {
                int mid=(lp+llp)/2;
                if(bboy[mid].name.equals(com_boy[i]))
                {
                    WriteToLog.writeBytes(bboy[mid].girlfriend_name+" is committed to " + com_boy[i] + "\n");
                    f=1;
                    break;
                }
                else if(bboy[mid].name.compareTo(com_boy[i])<0)
                    lp=mid+1;
                else
                    llp=mid-1;
            }
            if(f==0)
               if(bboy[lp].name.equals(com_boy[i]))
                    WriteToLog.writeBytes(bboy[lp].girlfriend_name+" is committed to " + com_boy[i] + "\n");
                else
                   WriteToLog.writeBytes(com_boy[i] +" is not committed " + "\n");
        }
    }    
    
}
