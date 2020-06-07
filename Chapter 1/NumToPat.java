/**
 * Megan Chu 1/14/17
 * please replace index and k in main method with the inputs
 * you want to test
 */

import java.lang.Math;
public class NumToPat {
    public static void main(String args[]) 
    {
        int index = 6504;
        int k = 7;
        System.out.print(numberToPattern(index, k));
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