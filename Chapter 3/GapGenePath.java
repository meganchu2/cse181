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
public class GapGenePath {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k
        int k = Integer.parseInt(in.next());
        int d = Integer.parseInt(in.next());
        in.nextLine();
        
        ArrayList<ArrayList<String>> patterns = new ArrayList<ArrayList<String>>();

        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                ArrayList<String> arr = new ArrayList<String>();
                String s1 = s.substring(0, k);
                arr.add(s1);
                s1 = s.substring(k+1);
                arr.add(s1);
                patterns.add(arr);
            }
        }

        ArrayList<ArrayList<ArrayList<String>>> adjList = deBruijnGraph(patterns);
        ArrayList<ArrayList<Integer>> adjListInt = new ArrayList<ArrayList<Integer>>();

        ArrayList<ArrayList<String>> allPat = new ArrayList<ArrayList<String>>();
        for(ArrayList<ArrayList<String>> s: adjList)
        {
            allPat.add(s.get(0));
        }
        for(int i = 0; i < adjList.size(); i++)
        {
            adjListInt.add(new ArrayList<Integer>());
            for(int j = 0; j < adjList.get(i).size(); j++)
            {
                ArrayList<String> pair = adjList.get(i).get(j);
                int p = allPat.indexOf(pair);
                if(p == -1)
                {
                    allPat.add(pair);
                }
                p = allPat.indexOf(pair);
                adjListInt.get(i).add(p);
            }
        }

        ArrayList<Integer> eCycle = findEulerianPath(adjListInt);
        ArrayList<ArrayList<String>> eCycleStr = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < eCycle.size(); i++)
        {
            ArrayList<String> pair = allPat.get(eCycle.get(i));
            eCycleStr.add(pair);
        }
        System.out.print(stringSpelled(eCycleStr, k, d));
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

    public static String stringSpelled(ArrayList<ArrayList<String>> patterns, int k, int d)
    {
        String text = patterns.get(0).get(0);
        k--; // account for short 1 k because nodes are k-1 in db graph
        d++; // account for longer 1 d because kmer nodes are shorter than orignal k by 1
        for(int a = 1; a <= d; a++)
        {
            text = text + patterns.get(a).get(0).substring(k-1, k);
        }
        text = text + patterns.get(0).get(1);
        for(int i = 1; i < patterns.size(); i++)
        {
            text = text + patterns.get(i).get(1).substring(k-1, k);
        }
        return text;
    }

    public static ArrayList<ArrayList<ArrayList<String>>> deBruijnGraph(ArrayList<ArrayList<String>> patterns)
    {
        ArrayList<ArrayList<ArrayList<String>>> dBGraph = new ArrayList<ArrayList<ArrayList<String>>>();
        int k = patterns.get(0).get(0).length();

        for(int i = 0; i < patterns.size(); i++)
        {
            ArrayList<String> pair = patterns.get(i);
            ArrayList<String> pre = new ArrayList<String>();
            ArrayList<String> post = new ArrayList<String>();
            pre.add(pair.get(0).substring(0, k-1));
            pre.add(pair.get(1).substring(0, k-1));
            post.add(pair.get(0).substring(1, k));
            post.add(pair.get(1).substring(1, k));
            if(dBGraph.isEmpty())
            {
                dBGraph.add(new ArrayList<ArrayList<String>>());
                dBGraph.get(0).add(pre);
                dBGraph.get(0).add(post);
            }
            else
            {
                int x = 0;
                while(x < dBGraph.size())
                {
                    if(pre.get(0).compareTo(dBGraph.get(x).get(0).get(0)) < 0)
                    {
                        dBGraph.add(x, new ArrayList<ArrayList<String>>());
                        dBGraph.get(x).add(pre);
                        break;
                    }
                    if(pre.get(0).compareTo(dBGraph.get(x).get(0).get(0)) == 0 &&
                       pre.get(1).compareTo(dBGraph.get(x).get(0).get(1)) == 0)
                    {
                        break;
                    }
                    x++;
                    if(x == dBGraph.size())
                    {
                        dBGraph.add(new ArrayList<ArrayList<String>>());
                        dBGraph.get(x).add(pre);
                        break;
                    }
                }
                dBGraph.get(x).add(post);
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
}