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
public class RandomMS {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        ArrayList<String> dna = new ArrayList<String>(); 

        // get k and t  
        int k = Integer.parseInt(in.next());
        in.skip("");
        int t = Integer.parseInt(in.next());
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

        ArrayList<String> bestMotifs = randomizedMotifSearch(dna, k, t);
        for(int i = 0; i < 1000; i++)
        {
            ArrayList<String> next = randomizedMotifSearch(dna, k, t);
            if(score(next, k) < score(bestMotifs, k))
            {
                bestMotifs = next;
            }
        } // get bestMotifs with minimum score

        for(int i = 0; i < bestMotifs.size() - 1; i++)
        {
            System.out.println(bestMotifs.get(i) + " ");
        }
        System.out.print(bestMotifs.get(bestMotifs.size() - 1));
    }

    public static ArrayList<String> randomizedMotifSearch(ArrayList<String> dna, int k, int t)
    {
        ArrayList<String> bestMotifs = new ArrayList<String>();
        ArrayList<String> motifs = new ArrayList<String>();
        for(String text: dna)
        {
            int i = (int)(Math.random()*(text.length()-k+1));
            motifs.add(text.substring(i, i+k));
        } // random motifs chosen from each dna string

        bestMotifs = motifs;
        while(true)
        {
            ArrayList<Double> profile = profile(motifs, k);
            ArrayList<String> mostProbMotifs = new ArrayList<String>();
            for(String text: dna)
            {
                String motif = mostProbable(text, k, profile);
                mostProbMotifs.add(motif);
            } // find most probable motif set based on profile of old set

            motifs = mostProbMotifs;
            if(score(motifs, k) < score(bestMotifs, k))
            {
                bestMotifs = motifs;
            }
            else // return motifs once there scores no longer improve
            {
                return bestMotifs;
            }
        }
    }

    public static int score(ArrayList<String> motifs, int k)
    {
        String s = consensus(motifs, k);
        int score = 0;
        for(int i = 0; i < k; i++)
        // go through all k positions
        {
            for(String motif: motifs)
            // for each motif
            {
                if(motif.charAt(i) != s.charAt(i))
                {
                    score++; // increment score for a mismatch with consensus
                }
            }
        }
        return score;
    }

    public static String consensus(ArrayList<String> motifs, int k)
    {
        ArrayList<Double> matrix = profile(motifs, k);
        String s = new String();

        for(int i = 0; i < k; i++)
        // for each position in motif
        {
            double max = matrix.get(i);
            int base = 0;
            for(int j = 1; j <= 3; j++)
            {
                if(matrix.get(i + j*k) > max)
                {
                    max = matrix.get(i + j*k);
                    base = j;
                }
            } // we have base with greatest probability

            if(base == 0)
            {
                s = s + "A";
            }
            else if(base == 1)
            {
                s = s + "C";
            }
            else if(base == 2)
            {
                s = s + "G";
            }
            else if(base == 3)
            {
                s = s + "T";
            } // we have appended base with highest probability to consensus
        }
        return s;
    }

    public static ArrayList<Double> profile(ArrayList<String> motifs, int k)
    {
        ArrayList<Double> matrix = new ArrayList<Double>();
        for(int i = 0; i < 4*k; i++)
        {
            matrix.add(0.0);
        } // empty profile

        for(int i = 0; i < k; i++)
        {
            // with pseudocounts, I arbitrarily chose 1, 
            // I believe 0.8 is usually most optimal
            double a = 1;
            double c = 1;
            double g = 1;
            double t = 1;

            for(String motif: motifs)
            // tally bases at given index in every motif
            {
                 char ch = motif.charAt(i);
                 if(ch == 'A')
                 {
                      a += 1;
                 }
                 else if(ch == 'C')
                 {
                      c += 1;
                 }
                 else if(ch == 'G')
                 {
                      g += 1;
                 }
                 else if(ch == 'T')
                 {
                      t += 1;
                 }
             }

             // set probability of a base at given position
             matrix.set(i, a/((double)motifs.size()));
             matrix.set(i + k, c/((double)motifs.size()));
             matrix.set(i + 2*k, g/((double)motifs.size()));
             matrix.set(i + 3*k, t/((double)motifs.size()));
        }
        return matrix;
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