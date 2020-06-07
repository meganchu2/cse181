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
public class NumBreak
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get strings
        String v = in.nextLine();
        String num = v.substring(1, v.length()-1);
        String[] nums = num.split(" ");
        int[] val = new int[nums.length];
        for(int i = 0; i < nums.length; i++)
        {
            val[i] = Integer.parseInt(nums[i]);
        }        

        System.out.print(breakpoints(val));
    }

    public static int breakpoints(int[] p)
    {
        int bP = 0;
        for(int k = 0; k < p.length - 1; k++)
        {
            if(Math.abs(p[k] - p[k+1]) != 1)
            {
                bP++;
            }
            else if(p[k] >= 0 && p[k+1] == p[k] - 1)
            {
                bP++;
            }
            else if(p[k] < 0 && p[k+1] == p[k] - 1)
            {
                bP++;
            }
        }
        if(p[0] != 1)
        {
            bP++;
        }
        if(p[p.length - 1] != p.length)
        {
            bP++;
        }
        return bP;
    }
}