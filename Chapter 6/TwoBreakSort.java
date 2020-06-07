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
public class TwoBreakSort
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<ArrayList<Integer>> val1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> val2 = new ArrayList<ArrayList<Integer>>();
        int blocks = 0;

        while(in.hasNext())
        {
            String s = in.next();
            if(s.charAt(0) == '(')
            {
                val1.add(new ArrayList<Integer>());
                val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(1, s.length())));
                blocks++;
            }
            else if(s.charAt(s.length() - 1) == ')')
            {
                val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(0, s.length() - 1)));
                blocks++;
                in.nextLine();
                break;
            }
            else 
            {
                int sp = s.indexOf(")(");
                if(sp >= 0)
                {
                    val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(0, sp)));
                    val1.add(new ArrayList<Integer>());
                    val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(sp + 2, s.length())));
                    blocks += 2;
                }
                else
                {
                    val1.get(val1.size() - 1).add(Integer.parseInt(s));
                    blocks++;
                }
            }
        }

        while(in.hasNext())
        {
            String s = in.next();
            if(s.charAt(0) == '(')
            {
                val2.add(new ArrayList<Integer>());
                val2.get(val2.size() - 1).add(Integer.parseInt(s.substring(1, s.length())));
            }
            else if(s.charAt(s.length() - 1) == ')')
            {
                val2.get(val2.size() - 1).add(Integer.parseInt(s.substring(0, s.length() - 1)));
                break;
            }
            else 
            {
                int sp = s.indexOf(")(");
                if(sp >= 0)
                {
                    val2.get(val2.size() - 1).add(Integer.parseInt(s.substring(0, sp)));
                    val2.add(new ArrayList<Integer>());
                    val2.get(val2.size() - 1).add(Integer.parseInt(s.substring(sp + 2, s.length())));
                }
                else
                {
                    val2.get(val2.size() - 1).add(Integer.parseInt(s));
                }
            }
        }

        ArrayList<int[]> edges1 = coloredEdges(val1);
        
        ArrayList<int[]> edges2 = coloredEdges(val2);
        
        System.out.print(blocks - twoBreakDistance(edges1, edges2));
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