/**
 * Megan Chu 2/4/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class LongPath
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get 2 source and sink
        int source = Integer.parseInt(in.next());
        in.nextLine();
        int sink = Integer.parseInt(in.next());
        in.nextLine();
        
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        ArrayList<Integer> drain = new ArrayList<Integer>();
        
        in.useDelimiter("");
        while(in.hasNextLine())
        {
            if(in.hasNext())
            {
                String s = in.next();
                String total1 = new String();
                while(s.charAt(0) != '-')
                {
                    total1 += s;
                    s = in.next();
                }
                in.next();
                s = in.next();
                String total2 = new String();
                while(s.charAt(0) != ':')
                {
                    total2 += s;
                    s = in.next();
                }
                String w = in.nextLine();
                edges.add(new ArrayList<Integer>());
                edges.get(edges.size() - 1).add(Integer.parseInt(total1));
                edges.get(edges.size() - 1).add(Integer.parseInt(total2));
                edges.get(edges.size() - 1).add(Integer.parseInt(w));
                if(!nodes.contains(edges.get(edges.size() - 1).get(0)))
                {
                    nodes.add(edges.get(edges.size() - 1).get(0));
                    candidates.add(edges.get(edges.size() - 1).get(0));
                }
                if(!nodes.contains(edges.get(edges.size() - 1).get(1)))
                {
                    nodes.add(edges.get(edges.size() - 1).get(1));
                    drain.add(edges.get(edges.size() - 1).get(1));
                }
            }
        }
        for(int g: drain)
        {
            if(candidates.contains(g))
            {
                candidates.remove(candidates.indexOf(g));
            }
        }

        longestPath(edges, nodes, source, sink, candidates);
    }

    public static void longestPath(ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> nodes, int source, int sink, ArrayList<Integer> candidates)
    {
        int[] s = new int[nodes.size()];
        for(int i= 0; i < nodes.size(); i++)
        {
            s[i] = 0;
        }

        ArrayList<Integer> sorted = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> tempE = edges;
        int start = candidates.remove(candidates.indexOf(source));
        while(candidates.size() >= 0)
        {
            sorted.add(start);
            System.out.println(start);
            for(int e = tempE.size() - 1; e >= 0; e--)
            {
                if(tempE.get(e).get(0) == start)
                {
                    int b = tempE.get(e).get(1);
                    tempE.remove(e);
                    boolean drain = false;
                    for(ArrayList<Integer> arr: tempE)
                    {
                        if(arr.get(1) == b)
                        {
                            drain = true;
                            break;
                        }
                    }
                    if(!drain)
                    {
                        candidates.add(b);
                    }
                }
            }
            if(!candidates.isEmpty())
            {
                start = candidates.remove(0);
            }
            else
            {
                break;
            }
        }
        s[sorted.indexOf(source)] = 0;
        for(int o = 0; o < sorted.size(); o++)
        {
            int thisNode = sorted.get(0);
            int max = 0;
            for(int p = 0; p < o; p++)
            {
                int pre = sorted.get(p);
                for(ArrayList<Integer> w: edges)
                {
                    if(w.get(0) == pre && w.get(1) == thisNode && s[p] + w.get(2) > max)
                    {
                        max = s[p] + w.get(2);
                        System.out.println("Hi");

                    }
                }
            }
            s[o] = max;
        }
        System.out.println(s[sorted.indexOf(sink)]);
        ArrayList<Integer> output = outputLP(s, sorted, edges, s[sorted.indexOf(sink)], sink);
        for(int h = 0; h < output.size() - 1; h++)
        {
            System.out.print(output.get(h) + "->");
        }
        System.out.print(output.get(output.size() - 1));

    }

    public static ArrayList<Integer> outputLP(int[] s, ArrayList<Integer> sorted, ArrayList<ArrayList<Integer>> edges, int weight, int sink)
    {
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        if(weight == 0)
        {
            toReturn.add(sink);
            return toReturn;
        }
        for(ArrayList<Integer> e: edges)
        {
            if(sorted.indexOf(e.get(0)) >= 0)
            {
                if(e.get(1) == sink &&(weight - e.get(2)) == s[sorted.indexOf(e.get(0))])
                {
                    toReturn.addAll(outputLP(s, sorted, edges, weight - e.get(2), e.get(0)));
                    toReturn.add(sink);
                    System.out.println(sink);
                    break;
                }
            }
        }
        return toReturn;
    }
}