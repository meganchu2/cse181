/**
 * Megan Chu 1/21/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class MotifEnum {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        ArrayList dna = new ArrayList<String>();

        // get k and d
        int k = Integer.parseInt(in.next());
        in.skip("");
        int d = Integer.parseInt(in.next());
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

        ArrayList<String> motifs = motifEnumeration(dna, k, d);
        for(int i = 0; i < motifs.size() - 1; i++)
        {
            System.out.print(motifs.get(i) + " ");
        }
        System.out.print(motifs.get(motifs.size() - 1));
    }

    public static ArrayList motifEnumeration(ArrayList<String> dna, int k, int d)
    {
        ArrayList<String> patterns = new ArrayList<String>();
        String text = dna.get(0); // get first dna string

        for(int i = 0; i <= text.length() - k; i++)
        // go through first dna string
        {
            String pattern = text.substring(i, i+k);
            ArrayList<String> n = neighbors(pattern, d);
            // generate all neighbors of pattern in first string

            for(String diff: n)
            {
                boolean motif = true;
                for(int x = 1; x < dna.size(); x++)
                // go through all other dna strings to check if neighbors are there
                {
                    String nextStr = dna.get(x);
                    boolean inStr = false;
                    for(int j = 0; j <= nextStr.length() - k; j++)
                    {
                        if(hammingDistance(diff, nextStr.substring(j, j+k)) <= d)
                        {
                            inStr = true; // neighbor is in this string
                            break;
                        }
                    }
                    if(!inStr) // pattern not in this string
                    {
                        motif = false;
                        break;
                    }
                }
                if(motif && !patterns.contains(diff))
                {
                    patterns.add(diff); // neighbor was in all strings
                }
            }                
        }
        return patterns;
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