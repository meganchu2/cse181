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
public class ChromToCycle
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<Integer> val1 = new ArrayList<Integer>();

        String s = in.nextLine();
        String sub = s.substring(1, s.length() - 1);
        String [] sp = sub.split(" ");
        for(String str: sp)
        {
            val1.add(Integer.parseInt(str));
        }        

        ArrayList<Integer> cycle = chromosomeToCycle(val1);
       
        System.out.print("(");
        for(int i = 1; i < cycle.size() - 1; i++)
        {
            System.out.print(cycle.get(i) + " ");
        }
        System.out.print(cycle.get(cycle.size() - 1) + ")");
    }

    public static ArrayList<Integer> chromosomeToCycle(ArrayList<Integer> chrom)
    {
        ArrayList<Integer> n = new ArrayList<Integer>();
        for(int k = 0; k <= 2*chrom.size(); k++)
        {
            n.add(0);
        }
        for(int j = 1; j <= chrom.size(); j++)
        {
            int i = chrom.get(j - 1);
            if( i >= 0)
            {
                n.set(2*j - 1, 2*i - 1);
                n.set(2*j, 2*i);
            }
            else
            {
                n.set(2*j - 1, -2*i);
                n.set(2*j, -2*i - 1);
            }
        }
        return n;
    }
}