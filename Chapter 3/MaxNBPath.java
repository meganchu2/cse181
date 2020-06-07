/**
 * Megan Chu 1/28/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class MaxNBPath {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get pattern
        
        ArrayList<ArrayList<Integer>> adjList =  new ArrayList<ArrayList<Integer>>();;

        while(in.hasNextLine())
        {
            if(in.hasNext())
            {
                int i = Integer.parseInt(in.next());
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                in.next();
                String s = in.next();
                String[] ints = s.split(",");
                for(String num: ints)
                {
                    int n = Integer.parseInt(num);
                    temp.add(n);
                }
                if(in.hasNextLine())
                {
                    in.nextLine();
                }
                adjList.add(temp);
            }
        }

        ArrayList<ArrayList<Integer>> eCycle = findContigs(adjList);

        for(int i = 0; i < eCycle.size() - 1; i++)
        {
            for(int j = 0; j < eCycle.get(i).size() - 1; j++)
            {
                System.out.print(eCycle.get(i).get(j) + " -> ");
            }
            System.out.println(eCycle.get(i).get(eCycle.get(i).size() - 1));
        }
        int u = eCycle.size() - 1;
        for(int j = 0; j < eCycle.get(u).size() - 1; j++)
        {
            System.out.print(eCycle.get(u).get(j) + " -> ");
        }
        System.out.print(eCycle.get(u).get(eCycle.get(u).size() - 1));
    }

    public static ArrayList<ArrayList<Integer>> findContigs(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> v = balance(adjList);
        ArrayList<Integer> allPat = new ArrayList<Integer>();
        ArrayList<Integer> nonV = new ArrayList<Integer>();
        for(ArrayList<Integer> arr: adjList)
        {
            allPat.add(arr.get(0));
            if(!v.contains(arr.get(0)))
            {
                nonV.add(arr.get(0));
            }
        }
        boolean jump = false;
        for(ArrayList<Integer> arr: adjList)
        {
            for(int i: arr)
            {
                if(!allPat.contains(i))
                {
                    allPat.add(i);
                    if(!v.contains(i))
                    {
                        nonV.add(i);
                    }
                    adjList.add(new ArrayList<Integer>());
                    jump = true;
                    break;
                }
            }
            if(jump)
            {
                break;
            }
        }

        ArrayList<ArrayList<Integer>> eCycle = new ArrayList<ArrayList<Integer>>();

        for(int start: nonV)
        {
            ArrayList<Integer> thisAdj = adjList.get(allPat.indexOf(start));
            if(thisAdj.size() <= 1)
            {
                // do nothing
            }
            else
            {
                for(int q = thisAdj.size() - 1; q >= 1; q--)
                {
                    ArrayList<Integer> contig = new ArrayList<Integer>();   
                    contig.add(start);
                    int nextStart = adjList.get(allPat.indexOf(start)).remove(q);
                    while(v.contains(nextStart))
                    {
                        contig.add(nextStart);
                        nextStart = (adjList.get(allPat.indexOf(nextStart)).remove(1));
                    }
                    contig.add(nextStart);
                    eCycle.add(contig);
                }
            }
        }
        for(int g = adjList.size() - 1; g >= 0; g--)
        {
            if(adjList.get(g).size() <= 1)
            {
                adjList.remove(g);
            }
        }
        
        while(!adjList.isEmpty())
        {
            allPat.clear();
            for(ArrayList<Integer> arr: adjList)
            {
                allPat.add(arr.get(0));
            }
            int newBegin = adjList.get(0).get(0);
            int store = Integer.valueOf(newBegin);
            ArrayList<Integer> cycle = new ArrayList<Integer>();
            cycle.add(newBegin);
            newBegin = adjList.get(0).remove(1);
            while(newBegin != store)
            {
                cycle.add(newBegin);
                newBegin = adjList.get(allPat.indexOf(newBegin)).remove(1);
            }
            cycle.add(newBegin);
            eCycle.add(cycle); 
            for(int g = adjList.size() - 1; g >= 0; g--)
            {
                if(adjList.get(g).size() <= 1)
                {
                    adjList.remove(g);
                }
            } 
        }       
        return eCycle;
    }

    public static ArrayList<Integer> balance(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> balances = new ArrayList<Integer>();
        
        ArrayList<Integer> allPat = new ArrayList<Integer>();
        for(ArrayList<Integer> i: adjList)
        {
            allPat.add(i.get(0));
        }
        for(int start: allPat)
        {
            int in = 0;
            int out = 0;
            
            for(int i = 0; i < adjList.size(); i++)
            {
                if(adjList.get(i).get(0) == start)
                {
                    out += adjList.get(i).size() - 1;
                }
                for(int j = 1; j < adjList.get(i).size(); j++)
                {
                    if(adjList.get(i).get(j) == start)
                    {
                        in += 1;
                    }
                }
            }
            if( in == 1 && out == 1)
            {
                balances.add(start);
            }
        }
        return balances;
    }
}