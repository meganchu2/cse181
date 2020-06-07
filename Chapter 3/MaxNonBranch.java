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
public class ReadPairs {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k
        int k = Integer.parseInt(in.next());
        int d = Integer.parseInt(in.next());
        in.nextLine();

        ArrayList<String[]> patterns = new ArrayList<String[]>();
        ArrayList<String> patterns1 = new ArrayList<String>();
        ArrayList<String> patterns2 = new ArrayList<String>();

        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                String[] strArr = s.split("|");
                patterns.add(strArr);
            }
        }
        ArrayList<ArrayList<String>> adjList1 = deBruijnGraph(patterns1);
        ArrayList<ArrayList<String>> adjList2 = deBruijnGraph(patterns2);

        ArrayList<ArrayList<Integer>> adjListInt1 = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> adjListInt2 = new ArrayList<ArrayList<Integer>>();

        ArrayList<String> allPat1 = new ArrayList<String>();
        ArrayList<String> allPat2 = new ArrayList<String>();
        for(ArrayList<String> s: adjList1)
        {
            allPat1.add(s.get(0));
        }
        for(ArrayList<String> s: adjList2)
        {
            allPat2.add(s.get(0));
        }
        for(int i = 0; i < adjList1.size(); i++)
        {
            adjListInt1.add(new ArrayList<Integer>());
            for(int j = 0; j < adjList1.get(i).size(); j++)
            {
                String pat = adjList1.get(i).get(j);
                int p = allPat1.indexOf(pat);
                if(p == -1)
                {
                    allPat1.add(pat);
                }
                p = allPat1.indexOf(pat);
                adjListInt1.get(i).add(p);
            }
        }
        for(int i = 0; i < adjList2.size(); i++)
        {
            adjListInt1.add(new ArrayList<Integer>());
            for(int j = 0; j < adjList2.get(i).size(); j++)
            {
                String pat = adjList2.get(i).get(j);
                int p = allPat2.indexOf(pat);
                if(p == -1)
                {
                    allPat2.add(pat);
                }
                p = allPat2.indexOf(pat);
                adjListInt2.get(i).add(p);
            }
        }

        ArrayList<Integer> eCycle = findEulerianPath(adjListInt1, adjListInt2, d);
        ArrayList<String> eCycleStr = new ArrayList<String>();

        for(int i = 0; i < eCycle.size(); i++)
        {
            String pat = allPat.get(eCycle.get(i));
            eCycleStr.add(pat);
        }
        System.out.print(stringSpelled(eCycleStr));
    }

    public static ArrayList<Integer> maxNonbranchingPath(ArrayList<ArrayList<Integer>> adjList)
    {
        ArrayList<Integer> startInd = balance(AdjList);
        ArrayList<ArrayList<Integer>> eCycle = new ArrayList<ArrayList<Integer>>();
        int nextStart = adjList.get(0).get(0);
        int store = Integer.valueOf(nextStart);
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(ArrayList<Integer> arr: adjList)
        {
            index.add(arr.get(0));
        }
        for(int start: startInd)
        {
            for(int i = 1; i < adjList.get(index.indexOf(start)).size(); i++)
            {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                
            }

            for(int i = 0; i < adjList1.size(); i++)
            {
                if(eCycle.contains(adjList1.get(i).get(0)) || eCycle.isEmpty())
                {
                    if(adjList2.contains(adjList1.get(i).get(1)))
                    {
                        nextStart = adjList1.get(i).get(0);
                        if(!eCycle.isEmpty())
                        {
                            while(eCycle.get(0) != nextStart)
                            {
                                int temp = eCycle.remove(0);
                                eCycle.add(temp);
                            }
                        }
                    }
                    store = Integer.valueOf(nextStart);
                    eCycle.add(nextStart); // traverse new cycle...
                    
                        nextStart = adjList1.get(i).remove(1);
                        adjList2.get(i).remove(lastIndexOf(nextStart));
                    }
                    if(adjList1.get(i).size() <= 1)
                    {
                        adjList1.remove(i);
                    }
                    if(adjList2.get(i).size() <= 1
                    {
                        adjList2.remove(i);
                    }
                    break;
                }
            } // finish get new start
            while(nextStart != store)
            {
                for(int i = 0; i < adjList1.size(); i++)
                {
                    if(adjList1.get(i).get(0) == nextStart)
                    {
                        eCycle.add(nextStart);
                        for(int j = 1; j < adjList.size(); j++)

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

    public static ArrayList<Integer> balance(ArrayList<ArrayList<Integer>> adjList)
    {
        int[] balances = new int[adjList.size()];
        ArrayList<Integer> startInd = new ArrayList<Integer>();
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
                }
            }
        }

        for(int a = 0; a < balances.length; a++)
        {
            if(balances[a] <= -1)
            {
                startInd.add(allPat.get(a));
            }
        }
        return startInd;
    }
}