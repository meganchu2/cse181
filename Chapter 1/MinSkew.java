/**
 * Megan Chu 1/14/17
 * the string for genome is too large, so please put the "filename with .txt" as
 * the first and only argument of MinSkew
 */

import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class MinSkew {
    public static void main(String args[]) throws FileNotFoundException
    {
        String genome = args[0];
        ArrayList minI = minimizingSkew(genome);
        for(int i = 0; i < minI.size() - 1; i++)
        {
            System.out.print(minI.get(i) + " ");
        }
        System.out.print(minI.get(minI.size() - 1));
    }

    public static ArrayList minimizingSkew(String genome) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(genome));
        in.useDelimiter(""); // read 1 char at a time
        ArrayList<Integer> minI = new ArrayList<Integer>();

        int min = 0; // tracks min skew value
        int tracker = 0; // tracks skew at each nucleotide
        int i = 0; // tracks position in genome
        while(in.hasNext())
        {
            char pre = (in.next()).charAt(0);
            if(pre == 'C')
            {
                tracker--;
            }
            else if(pre == 'G')
            {
                tracker++;
            }
            if(pre == 'C' || pre == 'G' || pre == 'A' || pre == 'T')
            // excludes spaces or new lines at end of input file
            {
                if(tracker == min)
                {
                    minI.add(i+1); // +1 accounts for zeroth index always 0
                }
                if(tracker < min)
                {
                    min = tracker;
                    minI.clear();
                    minI.add(i+1); // +1 accounts for zeroth index always 0
                }
                i++;
            }
        }
        in.close();
        return minI;
    }
}