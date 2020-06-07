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
public class GreedySort 
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

        greedySorting(val);
    }

    public static int greedySorting(int[] p)
    {
        int approxRevDist = 0;
        for(int k = 0; k < p.length; k++)
        {
            if(p[k] != k + 1)
            {
                int[] temp = new int[p.length];
                int j = k;
                while(k < p.length)
                {
                    temp[j] = p[k];
                    if(p[j] == -(j + 1) || p[j] == j + 1)
                    {
                        break;
                    }
                    j++;
                }
                int tempInd = 0;
                for(int i = k; i <= j; i++)
                {
                    p[i] = -(temp[j - tempInd]);
                    tempInd++;
                }
                out(p);
                approxRevDist++;
                if(p[k] == -(k + 1))
                {
                    p[k] = k + 1;
                    out(p);
                    approxRevDist++;
                }
            }
        }
        return approxRevDist;
    }

    public static void out(int[] p)
    {
        System.out.print("(");
        for(int i = 0; i < p.length - 1; i++)
        {
            if(p[i] >= 0)
            {
                System.out.print("+" + p[i] + " ");
            }
            else
            {
                System.out.print(p[i] + " ");
            }
        }
        if(p[p.length - 1] >= 0)
        {
            System.out.print("+" + p[p.length - 1] + ")");
        }
        else
        {
            System.out.print(p[p.length - 1] + ")");
        }
    }
}