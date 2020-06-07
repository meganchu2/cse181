/**
 * Megan Chu 1/21/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class DistPatStr {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        ArrayList dna = new ArrayList<String>();

        // get pattern
        String pattern = in.nextLine();

        // get dna strings
        while(in.hasNext())
        {
            dna.add(in.next());
            if(in.hasNext())
            {
                in.skip("");
            }
        }
        System.out.print(distanceBetweenPatternAndStrings(pattern, dna));
    }

    public static int distanceBetweenPatternAndStrings(String pattern, ArrayList<String> dna)
    {
        int k = pattern.length();
        int distance = 0;

        for(String text: dna) // for every dna string
        {
            int minHam = k+1;
            for(int j = 0; j <= text.length() - k; j++)
            {
                // calculate hamming distance of each kmer in dna with pattern
                int newHam = hammingDistance(pattern, text.substring(j, j+k));
                if(newHam < minHam)
                {
                    minHam = newHam; // get lowest of such distances
                }
            }
            distance += minHam; // add to total distance for this pattern and dna set
        }
        return distance;
    }

    public static int hammingDistance(String dna1, String dna2)
    {
        char[] c1 = dna1.toCharArray(); // separate chars in dna
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