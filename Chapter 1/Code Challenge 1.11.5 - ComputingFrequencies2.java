import java.lang.Math;
import java.util.ArrayList;
public class MyClass {
    public static void main(String args[]) 
    {
        String text = "GACCCCATGATTTCCGAAGACCCCGTCTCTGGTGAATCAAGGTCCCTACGATAGAAGGGGAACTTGCTACAAATCATAATGCATAGATTTATCCGAACACCGTCTTCATCGTAGGTTAACTCGTAGTGCAGTTCTCAGTGTCCACGTGGCCAGCCTCTGAGCGCCCTCCGTAACCGCGAATGTACCAGTGGATACGCTCGGAGAAGCCTTAAAGTAAGCTAATACGTCAAGACGATCAGAGCTTACCGGTATAGGACCAACAATGGAACCTGTCGCTTTAATACTCTCCTAGGGCAGGCTGGAGACCCCTTGGGGGCGAGGTTTATAGCAGTGTTCCCCGCTAATATAGGCCGATTTGCGGAGGTCGTACCGCCGGTGATCCTGTACATGCAGGATAATAGCGACCGAAAGACCATAGGGGAGTAGACCTTCCCCGGCGATCGTACCTTAAAATGATTGATACGAAACCTAAGACGGCTGAGCTGCTCCAGACTAAGCCCCTGAGAGGAGATGACGGCGTCTTGTGCCGAAGCAAGTATCGTACGATAAAAGACGCTTGGCTTTCTTTTTACGTTCGGCCGCGACTCACCAGGTTTGCACGATATGTCCGCGAGTACAGATCCAGATCAGAGGCGGCTCTCGGCTGCACCTGCT";
        int k = 8;
        int[] freqArray = computingFrequencies(text, k);
        for(int i = 0; i < freqArray.length - 1; i++)
        {
            System.out.print(freqArray[i] + " ");
        }
        System.out.print(freqArray[freqArray.length - 1]);
    }

    public static int[] computingFrequencies(String text, int k)
    {
        int[] count = new int[(int)Math.pow(4, k)];
        for(int i = 0; i <= text.length() - k; i++)
        {
            String pattern = text.substring(i, i+k);
            int pIndex = patternToNumber(pattern);
            count[pIndex]++;
        }
        return count;
    }
        
    public static int patternToNumber(String pattern)
    {
        int num = 0;
        int shift = (int)(Math.pow(4, pattern.length())/4);
        for(int i = 0; i < pattern.length(); i++)
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
}