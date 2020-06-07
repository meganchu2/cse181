/**
 * Megan Chu 1/14/17
 * please replace text, k, and d in main method with the inputs
 * you want to test
 */

import java.util.ArrayList;
public class FreqWordsWM {
    public static void main(String args[]) 
    {
        String text = "CGCTTCCATAGCCGTTAATCATCTAGGCGCTTCCATTCATCTAGGTCATCTAGGATAACCGGTCAAAACGGCCAGCCGTTAAAGCCGTTAAAAAACGGCCCGCTTCCATTCATCTAGGCGCTTCCATAAAACGGCCATAACCGGTCCGCTTCCATATAACCGGTCATAACCGGTCATAACCGGTCCGCTTCCATAAAACGGCCAGCCGTTAAATAACCGGTCATAACCGGTCAAAACGGCCATAACCGGTCATAACCGGTCAAAACGGCCTCATCTAGGCGCTTCCATCGCTTCCATAGCCGTTAAAAAACGGCCAAAACGGCCCGCTTCCATTCATCTAGGAAAACGGCCATAACCGGTCATAACCGGTCAAAACGGCCAAAACGGCCAGCCGTTAATCATCTAGGAGCCGTTAAATAACCGGTCATAACCGGTCATAACCGGTCTCATCTAGGATAACCGGTCAAAACGGCCCGCTTCCATATAACCGGTCATAACCGGTCATAACCGGTCAGCCGTTAAAAAACGGCCCGCTTCCATTCATCTAGGATAACCGGTCAGCCGTTAAATAACCGGTCTCATCTAGGAAAACGGCCATAACCGGTCAAAACGGCCATAACCGGTCCGCTTCCATTCATCTAGGTCATCTAGGCGCTTCCATTCATCTAGGCGCTTCCATAAAACGGCCAAAACGGCCATAACCGGTCAGCCGTTAATCATCTAGGAAAACGGCCATAACCGGTCAAAACGGCCAGCCGTTAAAAAACGGCCATAACCGGTCATAACCGGTCAAAACGGCCTCATCTAGGCGCTTCCATAGCCGTTAAAAAACGGCCAAAACGGCCAGCCGTTAAAAAACGGCCATAACCGGTCCGCTTCCATCGCTTCCATATAACCGGTCCGCTTCCATAAAACGGCCATAACCGGTCATAACCGGTCATAACCGGTCTCATCTAGG";
        int k = 6;
        int d = 3;
        ArrayList<String> frequentPatterns = frequentWordsWM(text, k, d);
        for(int i = 0; i < frequentPatterns.size() - 1; i++)
        {
            System.out.print(frequentPatterns.get(i) + " ");
        }
        System.out.print(frequentPatterns.get(frequentPatterns.size() - 1));            
    }

    public static ArrayList<String> frequentWordsWM(String text, int k, int d)
    {
        ArrayList<String> frequentPatterns = new ArrayList<String>();
        int[] count = new int[(int)Math.pow(4, k)]; 
        // frequency array of all possible patterns

        int maxCount = 0;
        for(int i = 0; i < count.length; i++)
        {
            String pattern = numberToPattern(i, k);
            count[i] = approximatePatternMatching(pattern, text, d).size();
            if(count[i] == maxCount) // update frequentPatterns
            {
                if(!frequentPatterns.contains(pattern))
                {
                    frequentPatterns.add(pattern);
                }
            }
            else if(count[i] > maxCount)
            // update maxCount and frequentPatterns
            {
                maxCount = count[i];
                frequentPatterns.clear();
                frequentPatterns.add(pattern);
            }
        }
        return frequentPatterns;
    }

    public static int patternToNumber(String pattern)
    {
        int num = 0;
        int shift = (int)(Math.pow(4, pattern.length())/4);
        for(int i = 0; i < pattern.length(); i++)
        // rightmost nucleotides have least # value
        {
            char c = pattern.charAt(i);
            if(c == 'A')
            {
                //do nothing
            }
            else if(c == 'C')
            {
                num += shift;
            }
            else if(c =='G')
            {
                num += 2*shift;
            }
            else
            {
                num += 3*shift;
            }
            shift = shift/4;
        }
        return num;
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
        }
        return pattern;
    }

    public static ArrayList<Integer> approximatePatternMatching(String pattern, String text, int d)
    {
        ArrayList<Integer> matches = new ArrayList<Integer>();
        int k = pattern.length();
        for(int i = 0; i <= text.length() - k; i++) // go through text
        {
            if(hammingDistance(pattern, text.substring(i, i+k)) <= d)
            // compare hamming distance between pattern and substring of text
            {
                matches.add(i);
            }
        }
        return matches;
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