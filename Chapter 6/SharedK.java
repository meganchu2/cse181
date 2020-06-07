/**
 * Megan Chu 2/25/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class SharedK
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        int k = Integer.parseInt(in.nextLine());
        String v = in.nextLine();
        String w = in.nextLine();

        ArrayList<int[]> share = sharedKmers(k, v, w);
        
        for(int i = 0; i < share.size() - 1; i++)
        {
            System.out.println("(" + share.get(i)[0] + ", " + share.get(i)[1] + ")");
        }        
        System.out.print("(" + share.get(share.size() - 1)[0] + ", " + share.get(share.size() - 1)[1] + ")");
    }
    
    public static ArrayList<int[]> sharedKmers(int k, String v, String w)
    {
        ArrayList<int[]> share = new ArrayList<int[]>();
        for(int i = 0; i <= v.length() - k; i++)
        {
            String vK = v.substring(i, i + k);
            for(int j = 0; j <= w.length() - k; j++)
            {
                String wK = w.substring(j, j + k);
                if(vK.equals(wK) || vK.equals(revComp(wK)))
                {
                    int[] t = {i, j};
                    share.add(t);
                }
            }
        }
        return share;
    }
    
    public static String revComp(String s)
    {
        String rC = "";
        for(int i = s.length() - 1; i >= 0; i--)
        {
            char c= s.charAt(i);
            if(c == 'A')
            {
                rC += 'T';
            }
            if(c == 'C')
            {
                rC += 'G';
            }
            if(c == 'G')
            {
                rC += 'C';
            }
            if(c == 'T')
            {
                rC += 'A';
            }
        }
        return rC;
    }
}