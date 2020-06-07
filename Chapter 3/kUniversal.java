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
public class kUniversal {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k
        int k = Integer.parseInt(in.next());
        
        ArrayList<String> patterns = allBin(k, new ArrayList<String>());

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

        ArrayList<Integer> eCycle = findEulerianPath(adjListInt);
        ArrayList<String> eCycleStr = new ArrayList<String>();

        for(int i = 0; i < eCycle.size(); i++)
        {
            String pat = allPat.get(eCycle.get(i));
            eCycleStr.add(pat);
        }
        System.out.print(stringSpelled(eCycleStr));
    }

    public static ArrayList<Integer> findEulerianPath(ArrayList<ArrayList<Integer>> adjList)
    {
        int[] startEnd = balance(adjList);
        if(startEnd[0] >= 0 && startEnd[1] >= 0)
        {
            adjList.add(new ArrayList<Integer>());
            adjList.get(adjList.size() - 1).add(startEnd[1]);
            adjList.get(adjList.size() - 1).add(startEnd[0]);
        }

        ArrayList<Integer> eCycle = new ArrayList<Integer>();
        int nextStart = adjList.get(0).get(0);
        int store = Integer.valueOf(nextStart);
        while(!adjList.isEmpty())
        {
            for(int i = 0; i < adjList.size(); i++)
            {
                if(eCycle.contains(adjList.get(i).get(0)) || eCycle.isEmpty())
                {
                    nextStart = adjList.get(i).get(0);
                    if(!eCycle.isEmpty())
                    {
                        while(eCycle.get(0) != nextStart)
                        {
                            int temp = eCycle.remove(0);
                            eCycle.add(temp);
                        }
                    }
                    store = Integer.valueOf(nextStart);
                    eCycle.add(nextStart); // traverse new cycle...
                    nextStart = adjList.get(i).remove(1);
                    if(adjList.get(i).size() <= 1)
                    {
                        adjList.remove(i);
                    }
                    break;
                }
            } // finish get new start
            while(nextStart != store)
            {
                for(int i = 0; i < adjList.size(); i++)
                {
                    if(adjList.get(i).get(0) == nextStart)
                    {
                        eCycle.add(nextStart);
                        nextStart = adjList.get(i).remove(1);
                        if(adjList.get(i).size() <= 1)
                        {
                            adjList.remove(i);
                        }
                        break;
                    }
                }
                
            } // complete a cycle
        }
        while((eCycle.get(eCycle.size() - 1) != startEnd[1] || eCycle.get(0) != startEnd[0]) && (startEnd[0] >= 0 && startEnd[1] >= 0))
        {
            int temp = eCycle.remove(0);
            eCycle.add(temp);
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
        for(int i = 0; i < patterns.size()-1; i++)
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
                        break;
                    }
                }
                dBGraph.get(x).add(node2);
            }
        }
        return dBGraph;
    }

    public static int[] balance(ArrayList<ArrayList<Integer>> adjList)
    {
        int[] balances = new int[adjList.size()];
        int[] startEnd = {-1, -1, -1};
        ArrayList<Integer> allPat = new ArrayList<Integer>();
        for(ArrayList<Integer> i: adjList)
        {
            allPat.add(i.get(0));
        }

        for(int i = 0; i < adjList.size(); i++)
        {
            for(int j = 1; j < adjList.get(i).size(); j++)
            {
                if(allPat.contains(adjList.get(i).get(j)))
                {
                    balances[i] -= 1;
                    int ind = allPat.indexOf(adjList.get(i).get(j));
                    balances[ind] += 1;
                }
                else
                {
                    balances[i] -= 1;
                    startEnd[1] = adjList.get(i).get(j);
                }
            }
        }

        for(int a = 0; a < balances.length; a++)
        {
            if(balances[a] == -1)
            {
                startEnd[0] = allPat.get(a);
            }
            if(balances[a] == 1)
            {
                startEnd[1] = allPat.get(a);
                startEnd[2] = a;
            }
            if(startEnd[0] != -1 && startEnd[1] != -1)
            {
                break;
            }
        }
        return startEnd;
    }

    public static ArrayList<String> allBin(int k, ArrayList<String> binaries)
    {
        ArrayList<String> newBin = new ArrayList<String>();
        if(k == 1)
        {
            for(String s: binaries)
            {
                newBin.add(("1" + s));
                newBin.add(("0" + s));
            }
            return newBin;
        }
        else if(binaries.isEmpty())
        {
            newBin.add("1");
            newBin.add("0");
            return allBin(k - 1, newBin);
        }
        else  
        {
            for(String s: binaries)
            {
                newBin.add(("1" + s));
                newBin.add(("0" + s));
            }
            return allBin(k - 1, newBin);
        }   
    }
}