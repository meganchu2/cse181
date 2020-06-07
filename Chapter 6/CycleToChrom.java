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
public class CycleToChrom
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<Integer> val1 = new ArrayList<Integer>();

        String s = in.nextLine();
        String sub = s.substring(1, s.length() - 1);
        String [] sp = sub.split(" ");
        val1.add(0);
        for(String str: sp)
        {
            val1.add(Integer.parseInt(str));
        }        

        ArrayList<Integer> chrom = cycleToChromosome(val1);
       
        System.out.print("(");
        for(int i = 1; i < chrom.size() - 1; i++)
        {
            if(chrom.get(i) >= 0)
            {
                System.out.print("+" + chrom.get(i) + " ");
            }
            else
            {
                System.out.print(chrom.get(i) + " ");
            }
        }
        if(chrom.get(chrom.size() - 1) >= 0)
        {
                System.out.print("+" + chrom.get(chrom.size() - 1) + ")");
        }
        else
        {
            System.out.print(chrom.get(chrom.size() - 1) + ")");
        }
    }

    public static ArrayList<Integer> cycleToChromosome(ArrayList<Integer> cycle)
    {
        ArrayList<Integer> chrom = new ArrayList<Integer>();
        for(int k = 0; k <= cycle.size()/2; k++)
        {
            chrom.add(0);
        }
        for(int j = 1; j <= cycle.size()/2; j++)
        {
            if(cycle.get(2*j - 1) < cycle.get(2*j))
            {
                chrom.set(j, cycle.get(2*j)/2);
            }
            else
            {
                chrom.set(j, -cycle.get(2*j - 1)/2);
            }
        }
        return chrom;
    }
}