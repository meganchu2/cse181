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
public class ProfileProb {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        // get dna string and k
        String text = in.nextLine();
        int k = Integer.parseInt(in.next());
        in.nextLine();

        // get profile matrix
        ArrayList<Double> matrix = new ArrayList<Double>();
        while(in.hasNextDouble())
        {
            matrix.add(in.nextDouble());
            if(in.hasNext())
            {
                in.skip("");
            }
        }
        System.out.print(mostProbable(text, k, matrix));
    }

    public static String mostProbable(String text, int k, ArrayList<Double> matrix)
    {
        String kmer = new String();
        double prob = -1; // ensures kmer will be set to some string even with probability 0

        for(int i = 0; i <= text.length() - k; i++) // go through text
        {
            double thisProb = 1;
            String pattern = text.substring(i, i + k); // go through all patterns in string
            for(int j = 0; j < pattern.length(); j++)
            {
                char c = pattern.charAt(j);
                if(c == 'A')
                {
                    thisProb *= matrix.get(j);
                }
                else if(c == 'C')
                {
                    thisProb *= matrix.get(j + k);
                }
                else if(c == 'G')
                {
                    thisProb *= matrix.get(j + 2*k);
                }
                else if(c == 'T')
                {
                    thisProb *= matrix.get(j + 3*k);
                }
            }
            if(thisProb > prob) // get kmer with highest probability
            {
                prob = thisProb;
                kmer = pattern;
            }
        }
        return kmer;
    }
}