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
public class TwoBreakGen
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<ArrayList<Integer>> val1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();

        String e = in.nextLine();
        e = e.substring(1, e.length() - 1);
        String[] sp = e.split(" ");
        for(String s: sp)
        {
            temp.add(Integer.parseInt(s));
        }
        val1.add(temp);

        ArrayList<Integer> index = new ArrayList<Integer>();
        String ind = in.nextLine();
        String[] indss = ind.split(", ");
        int[] inds = new int[4];
        inds[0] = Integer.parseInt(indss[0]);
        inds[1] = Integer.parseInt(indss[1]);
        inds[2] = Integer.parseInt(indss[2]);
        inds[3] = Integer.parseInt(indss[3]);

        ArrayList<ArrayList<Integer>> p = twoBreakOnGenome(val1, inds[0], inds[1], inds[2], inds[3]);
        for(ArrayList<Integer> arr: p)
        {
            System.out.print("(");
            for(int i = 1; i < arr.size() - 1; i++)
            {
                if(arr.get(i) >= 0)
                {
                    System.out.print("+" + arr.get(i) + " ");
                }
                else
                {
                    System.out.print(arr.get(i) + " ");
                }
            }  
            if(arr.get(arr.size() - 1) >= 0)
            {
                System.out.print("+" + arr.get(arr.size() - 1) + ")");
            }
            else
            {
                System.out.print(arr.get(arr.size() - 1) + ")"); 
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> twoBreakOnGenome(ArrayList<ArrayList<Integer>> p, int i1, int i2, int i3, int i4)
    {
        ArrayList<int[]> edges = coloredEdges(p);
        //edges = twoBreakOnGenomeGraph(edges, i1, i2, i3, i4);
        
        ArrayList<ArrayList<int[]>> val1 = new ArrayList<ArrayList<int[]>>();
        ArrayList<int[]> temp = new ArrayList<int[]>();
        
        int[] edgeSet = new int[4];
        int[] t = new int[2];
        while(!edges.isEmpty())
        {
            if(temp.isEmpty())
            {
                temp.add(edges.remove(0));
                t = temp.get(temp.size() - 1);
            }
            else
            {
                for(int i = 0; i < edges.size(); i++)
                {
                    if(Math.abs(temp.get(0)[0] - edges.get(i)[1]) == 1)
                    {
                        t = edges.remove(i);
                        temp.add(t);
                        val1.add(temp);
                        temp = new ArrayList<int[]>();
                        break;
                    }
                    else if(Math.abs(temp.get(temp.size() - 1)[1] - edges.get(0)[0]) == 1)
                    {
                        t = edges.remove(i);
                        temp.add(t);
                    }
                }
            }
            
        }        

        return graphToGenome(val1);
    }

    public static ArrayList<int[]> twoBreakOnGenomeGraph(ArrayList<int[]> edges, int i1, int i2, int i3, int i4)
    {
        for(int i = edges.size() - 1; i >= 0; i--)
        {
            int[] t = edges.get(i);
            if(t[0] == i1 && t[1] == i2)
            {
                edges.get(i)[1] = i3;
            }
            if(t[0] == i2 && t[1] == i1)
            {   
                edges.get(i)[1] = i4;
            }
            if(t[0] == i3 && t[1] == i4)
            {
                edges.get(i)[1] = i1;
            }
            if(t[0] == i4 && t[1] == i3)
            {
                edges.get(i)[1] = i2;
            }
        }
        return edges;
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
            if( i > 0)
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

    public static ArrayList<int[]> coloredEdges(ArrayList<ArrayList<Integer>> p)
    {
        ArrayList<int[]> edges = new ArrayList<int[]>();
        for(ArrayList<Integer> arr: p)
        {
            ArrayList<Integer> c = chromosomeToCycle(arr);
            c.remove(0);
            for(int j = 1; j <= arr.size(); j++)
            {
                int[] i = new int[2];
                i[0] = c.get(2*j - 1);
                i[1] = c.get((2*j)%c.size());
                edges.add(i);
            }
        }
        return edges;
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