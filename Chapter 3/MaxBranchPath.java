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
public class Contig {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get pattern
        
        ArrayList<String> patterns = new ArrayList<String>();

        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                patterns.add(s);
            }
        }

        ArrayList<ArrayList<String>> adjList = deBruijnGraph(patterns);

        ArrayList<ArrayList<Integer>> adjListInt = new ArrayList<ArrayList<Integer>>();

        ArrayList<String> allPat = new ArrayList<String>();
        for(ArrayList<String> s: adjList)
        {
            allPat.add(s.get(0));
        }
        for(int i = 0; i < adjList.size(); i++)
        {
            adjListInt.add(new ArrayList<Integer>());
            for(int j = 0; j < adjList.get(i).size(); j++)
            {
                String pat = adjList.get(i).get(j);
                int p = allPat.indexOf(pat);
                if(p == -1)
                {
                    allPat.add(pat);
                }
                p = allPat.indexOf(pat);
                adjListInt.get(i).add(p);
            }
        }

        ArrayList<ArrayList<Integer>> eCycle = findContigs(adjListInt);
        ArrayList<ArrayList<String>> eCycleStr = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < eCycle.size(); i++)
        {
            ArrayList<String> temp = new ArrayList<String>();
            for(int j = 0; j < eCycle.get(i).size(); j++)
            {
                String pat = allPat.get(eCycle.get(i).get(j));
                temp.add(pat);
            }
            eCycleStr.add(temp);
        }

        for(ArrayList<String> s: eCycleStr)
        {
            System.out.print(stringSpelled(s) + " ");
        }
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
                for(int q = 1; q < thisAdj.size(); q++)
                {
                    ArrayList<Integer> contig = new ArrayList<Integer>();   
                    contig.add(start);
                    int nextStart = thisAdj.get(q);
                    while(v.contains(nextStart))
                    {
                        contig.add(nextStart);
                        nextStart = (adjList.get(allPat.indexOf(nextStart)).get(1));
                    }
                    contig.add(nextStart);
                    eCycle.add(contig);
                }
            }
        }
        return eCycle;
    }

    public static String stringSpelled(ArrayList<String> patterns)
    {
        String text = patterns.get(0);
        for(int i = 1; i < patterns.size(); i++)
        {
            String pattern = patterns.get(i);
            text = text + pattern.charAt(pattern.length() - 1);
        }
        return text;
    }

    public static ArrayList<ArrayList<String>> deBruijnGraph(ArrayList<String> patterns)
    {
        ArrayList<ArrayList<String>> dBGraph = new ArrayList<ArrayList<String>>();
        int k = patterns.get(0).length();
        for(int i = 0; i < patterns.size(); i++)
        {
            String node1 = patterns.get(i).substring(0, k - 1);
            String node2 = patterns.get(i).substring(1, k);
            if(dBGraph.isEmpty())
            {
                dBGraph.add(new ArrayList<String>());
                dBGraph.get(0).add(node1);
                dBGraph.get(0).add(node2);
            }
            else
            {
                int x = 0;
                while(x < dBGraph.size())
                {
                    if(node1.compareTo(dBGraph.get(x).get(0)) < 0)
                    {
                        dBGraph.add(x, new ArrayList<String>());
                        dBGraph.get(x).add(node1);
                        break;
                    }
                    if(node1.compareTo(dBGraph.get(x).get(0)) == 0)
                    {
                        break;
                    }
                    x++;
                    if(x == dBGraph.size())
                    {
                        dBGraph.add(new ArrayList<String>());
                        dBGraph.get(x).add(node1);
                    }
                }
                dBGraph.get(x).add(node2);
            }
        }
        return dBGraph;
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