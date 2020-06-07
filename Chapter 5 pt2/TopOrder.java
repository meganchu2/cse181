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
public class TopOrder
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        ArrayList<Integer> candidates = new ArrayList<Integer>();
        ArrayList<Integer> drain = new ArrayList<Integer>();

        while(in.hasNextLine())
        {
            if(in.hasNext())
            {
                edges.add(new ArrayList<Integer>());
                edges.get(edges.size() - 1).add(Integer.parseInt(in.next()));
                in.next();
                String s = in.next();
                String[] ints = s.split(",");
                for(String num: ints)
                {
                    edges.get(edges.size() - 1).add(Integer.parseInt(num));
                }
                if(in.hasNextLine())
                {
                    in.nextLine();
                }
            }
        }
        for(ArrayList<Integer> arr: edges)
        {
            if(!nodes.contains(arr.get(0)))
            {
                nodes.add(arr.get(0));
                candidates.add(arr.get(0));
            }
            for(int h = 1; h < arr.size(); h++)
            {
                if(!drain.contains(arr.get(h)))
                {
                    drain.add(arr.get(h));
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

        ArrayList<Integer> order = topologicalOrdering(edges, nodes, candidates);
        if(!order.isEmpty())
        {
            for(int i = 0; i < order.size() - 1; i++)
            {
                System.out.print(order.get(i) + ", ");
            }
            System.out.print(order.get(order.size() - 1));
        }
    }

    public static ArrayList<Integer> topologicalOrdering(ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> nodes,  ArrayList<Integer> candidates)
    {
        ArrayList<Integer> order = new ArrayList<Integer>();
        int start = 0;
        while(candidates.size() > 0)
        {
            start = candidates.remove(0);
            order.add(start);
            ArrayList<Integer> sink = new ArrayList<Integer>();
            if(!nodes.isEmpty() && !edges.isEmpty() && nodes.indexOf(start) != -1)
            {
                sink = edges.get(nodes.indexOf(start));
                for(int i = sink.size() - 1; i >= 1; i--)
                {
                    int b = sink.get(i);
                    sink.remove(i);
                    boolean drain = false;
                    for(ArrayList<Integer> arr: edges)
                    {
                        for(int j = 1; j < arr.size(); j++)
                        {
                            if(arr.get(j) == b)
                            {
                                drain = true;
                                break;
                            }
                        }
                    }
                    if(!drain)
                    {
                        candidates.add(b);
                    }
                }
                if(sink.size() <= 1)
                {
                    edges.remove(nodes.indexOf(start));
                    nodes.remove(nodes.indexOf(start));
                }
            }
        }
        if(!edges.isEmpty())
        {
            return new ArrayList<Integer>();
        }
        return order;
    }
}