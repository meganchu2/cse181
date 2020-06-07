/**
 * Megan Chu 1/14/18
 * please replace pattern and d in main method with inputs
 * that you want to test
 */

import java.util.ArrayList;
public class Neighbor {
    public static void main(String args[]) 
    {
        String pattern = "CCGGGGCGA";
        int d = 2;
        ArrayList<String> neighborhood = neighbors(pattern, d);
        for(int i = 0; i < neighborhood.size() - 1; i++)
        {
            System.out.println(neighborhood.get(i));
        }
        System.out.print(neighborhood.get(neighborhood.size() - 1));
    }

    public static ArrayList<String> neighbors(String pattern, int d)
    {
        ArrayList<String> neighborhood = new ArrayList<String>();
        if(d == 0) // base case no more mismatches allowed
        {
            neighborhood.add(pattern);
            return neighborhood;
        }
        if(pattern.length() == 1) // base case any nucleotide is ok
        {
            neighborhood.add("A");
            neighborhood.add("C");
            neighborhood.add("G");
            neighborhood.add("T");
            return neighborhood;
        }
        ArrayList<String> suffixN = neighbors(pattern.substring(1, pattern.length()), d);
        for(String text: suffixN)
        {
            if(hammingDistance(pattern.substring(1, pattern.length()), text) < d)
            // we have hamming distance to spare
            {
                neighborhood.add("A" + text);
                neighborhood.add("C" + text);
                neighborhood.add("G" + text);
                neighborhood.add("T" + text);
            }
            else
            // no more hamming distance left
            {
                neighborhood.add(pattern.substring(0, 1) + text);
            }
        }
        return neighborhood;
    }

    public static int hammingDistance(String dna1, String dna2)
    {
        char[] c1 = dna1.toCharArray(); // separate chars from dna
        char[] c2 = dna2.toCharArray();
        int ham = 0;
        for(int i = 0; i < c1.length; i++)
        {
            if(c1[i] != c2[i]) // compare chars
            {
                ham++;
            }
        }
        return ham;
    }
}