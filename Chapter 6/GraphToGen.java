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
public class GraphToGen
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<ArrayList<int[]>> val1 = new ArrayList<ArrayList<int[]>>();
        ArrayList<int[]> temp = new ArrayList<int[]>();

        while(in.hasNext())
        {
            String s1 = in.next();
            String s2 = in.next();
            s1 = s1.substring(1, s1.length() - 1);
            int sp = s2.indexOf(")");
            s2 = s2.substring(0, sp);
            int[] t = new int[2];
            t[0] = Integer.parseInt(s1);
            t[1] = Integer.parseInt(s2);
            if(temp.isEmpty())
            {
                temp.add(t);
            }
            else if(Math.abs(temp.get(0)[0] - t[1]) == 1)
            {
                temp.add(t);
                val1.add(temp);
                temp = new ArrayList<int[]>();
            }
            else if(Math.abs(temp.get(temp.size() - 1)[1] - t[0]) == 1)
            {
                temp.add(t);
            }
        }

        ArrayList<ArrayList<Integer>> chromosomes = graphToGenome(val1);
        
        for(ArrayList<Integer> arr: chromosomes)
        {
            System.out.print("(");
            for(int x = 1; x < arr.size() - 1; x++)
            {
                if(arr.get(x) > 0)
                {
                     System.out.print("+" + arr.get(x) + " ");
                }
                else
                {
                    System.out.print(arr.get(x) + " ");
                }
            }
            if(arr.get(arr.size() - 1) > 0)
            {
                System.out.print(arr.get(arr.size() - 1) > 0);
            }
            else
            {
                System.out.print(arr.get(arr.size() - 1) + ")");
            }
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

    public static ArrayList<ArrayList<Integer>> graphToGenome(ArrayList<ArrayList<int[]>> g)
    {
        ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>();
        for(ArrayList<int[]> edges: g)
        {
            ArrayList<Integer> c = new ArrayList<Integer>();
            for(int j = 0; j < 2*edges.size(); j++)
            {
                c.add(0);
            }
            for(int i = 1; i <= edges.size(); i++)
            {
                c.set(2*i - 1, edges.get(i-1)[0]);
                c.set((2*i)%c.size(), edges.get(i-1)[1]);
            }
            c.add(0, 0);
            ArrayList<Integer> chrom = cycleToChromosome(c);
            p.add(chrom);
        }
        return p;
    }
}