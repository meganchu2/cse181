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
public class MedString {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        ArrayList dna = new ArrayList<String>();

        // get k
        int k = Integer.parseInt(in.next());
        in.nextLine();

        // get dna strings
        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                dna.add(s);
            }
        }
        System.out.print(medianString(dna, k));
    }

    public static String medianString(ArrayList<String> dna, int k)
    {
        String median = new String();
        int numPat = (int)Math.pow(4, k);
        double distance = Double.POSITIVE_INFINITY;

        for(int i = 0; i <= numPat; i++) // go through all possible patterns
        {
            int patHam = 0;
            String pattern = numberToPattern(i, k);

            for(String text: dna)
            // for each dna string check if variant of pattern is there
            {
                int minHam = k+1;
                for(int j = 0; j <= text.length() - k; j++)
                {
                    int newHam = hammingDistance(pattern, text.substring(j, j+k));
                    if(newHam < minHam)
                    {
                        minHam = newHam; // get closest match to pattern in each string
                    }
                }
                patHam += minHam; // total hamming distance for this pattern with all strings
            }
            if(patHam < distance)
            {
                distance = patHam;
                median = pattern;
            }
        }
        return median;
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

    public static String numberToPattern(int index, int k)
    {
        String pattern = new String();
        double shift = Math.pow(4, k)/4;
        while(k != 0)
        // build pattern left to right
        {
            if(index >= 3*shift)
            {
                pattern = pattern + "T";
                index -= 3*shift;
            }
            else if(index >= 2*shift)
            {
                pattern = pattern + "G";
                index -= 2*shift;
            }
            else if(index >= shift)
            {
                pattern = pattern + "C";
                index -= shift;
            }
            else
            {
                pattern = pattern + "A";
            }
            k--;
            shift /= 4;
            // divide value for 4 bases each time we move to next position
        }
        return pattern;
    }
}