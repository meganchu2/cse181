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
public class TwoBreakGenGraph
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<ArrayList<int[]>> val1 = new ArrayList<ArrayList<int[]>>();
        ArrayList<int[]> temp = new ArrayList<int[]>();

        String e = in.nextLine();
        int y = 1;
        while(y < e.length())
        {
            int[] t = new int[2];
            int end1 = y;
            while(e.charAt(end1) != ',')
            {
                end1++;
            }
            t[0] = Integer.parseInt(e.substring(y, end1));
            int end2 = end1 + 2;
            while(e.charAt(end2) != ')')
            {
                end2++;
            }
            t[1] = Integer.parseInt(e.substring(end1 + 2, end2));
            y = end2 + 4;

            if(temp.isEmpty())
            {
                temp.add(t);
            }
            else if(Math.abs(temp.get(0)[0] - t[1]) == 1)
            {
                temp.add(t);
            }
            else if(Math.abs(temp.get(temp.size() - 1)[1] - t[0]) == 1)
            {
                temp.add(t);
            }
        }

        ArrayList<Integer> index = new ArrayList<Integer>();
        String ind = in.nextLine();
        String[] indss = ind.split(", ");
        int[] inds = new int[4];
        inds[0] = Integer.parseInt(indss[0]);
        inds[1] = Integer.parseInt(indss[1]);
        inds[2] = Integer.parseInt(indss[2]);
        inds[3] = Integer.parseInt(indss[3]);

        ArrayList<int[]> edges = twoBreakOnGenomeGraph(temp, inds[0], inds[1], inds[2], inds[3]);
        for(int i = 0; i < edges.size() - 1; i++)
        {
            System.out.print("(" + edges.get(i)[0] + ", " + edges.get(i)[1] + "), ");
        }
        System.out.print("(" + edges.get(edges.size() - 1)[0] + ", " + edges.get(edges.size() - 1)[1] + ")");
    }

    public static ArrayList<int[]> twoBreakOnGenomeGraph(ArrayList<int[]> edges, int i1, int i2, int i3, int i4)
    {
        for(int i = edges.size() - 1; i >= 0; i--)
        {
            int[] t = edges.get(i);
            if((t[0] == i1 && t[1] == i2) || (t[0] == i2 && t[1] == i1))
            {
                edges.get(i)[0] = i2;
                edges.get(i)[1] = i4;
            }
            if((t[0] == i3 && t[1] == i4) || (t[0] == i4 && t[1] == i3))
            {
                edges.get(i)[0] = i3;
                edges.get(i)[1] = i1;
            }
        }
        return edges;
    }
    
    public static int twoBreakDistance(ArrayList<int[]> edges1, ArrayList<int[]> edges2)
    {
        int dist = 0;
        int[] prev = new int[2];
        int beginVal = 0;
        int next = 0;
        while(!edges1.isEmpty() && !edges2.isEmpty())
        {
            dist++;
            prev = edges1.remove(0);
            beginVal = prev[0];
            next = prev[1];
            while(next != beginVal)
            {
                for(int i = 0; i < edges2.size(); i++)
                {
                    if(edges2.get(i)[0] == next)
                    {
                        next = edges2.get(i)[1];
                        edges2.remove(i);
                        break;
                    }
                    if(edges2.get(i)[1] == next)
                    {
                        next = edges2.get(i)[0];
                        edges2.remove(i);
                        break;
                    }
                }
                if(next == beginVal)
                {
                    break;
                }
                for(int o = 0; o < edges1.size(); o++)
                {
                    if(edges1.get(o)[0] == next)
                    {
                        next = edges1.get(o)[1];
                        edges1.remove(o);
                        break;
                    }
                    if(edges1.get(o)[1] == next)
                    {
                        next = edges1.get(o)[0];
                        edges1.remove(o);
                        break;
                    }
                }
            }
        }
        return dist;
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