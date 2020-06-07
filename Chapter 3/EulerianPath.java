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
public class EulerianPath {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        int maxInd = 0;
        while(in.hasNextLine())
        {
            if(in.hasNext())
            {
                int i = Integer.parseInt(in.next());
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                if(i > maxInd)
                {
                    maxInd = i;
                }
                in.next();
                String s = in.next();
                String[] ints = s.split(",");
                for(String num: ints)
                {
                    int n = Integer.parseInt(num);
                    temp.add(n);
                    if(n > maxInd)
                    {
                        maxInd = n;
                    }
                }
                if(in.hasNextLine())
                {
                    in.nextLine();
                }

                if(adjList.isEmpty())
                {
                    adjList.add(temp);
                }
                else
                {
                    for(int c = 0; c < adjList.size(); c++)
                    {
                        if(adjList.get(c).get(0) > i)
                        {
                            adjList.add(c, temp);
                            break;
                        }
                        else if(c == adjList.size() - 1)
                        {
                            adjList.add(temp);
                            break;
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i <= maxInd; i++)
        {
            if(adjList.get(i).get(0) != i)
            {
                adjList.add(i, new ArrayList<Integer>());
                adjList.get(i).add(i);
            }
        }

        ArrayList<Integer> eCycle = findEulerianPath(adjList);
        for(int i = 0; i < eCycle.size() - 1; i++)
        {
            System.out.print(eCycle.get(i) + "->");
        }
        System.out.print(eCycle.get(eCycle.size() - 1));
    }

    public static ArrayList<Integer> findEulerianPath(ArrayList<ArrayList<Integer>> adjList)
    {
        int[] balances = balance(adjList);
        int start = -1;
        int end = -1;
        for(int a = 0; a < balances.length; a++)
        {
            if(balances[a] == -1)
            {
                start = a;
            }
            if(balances[a] == 1)
            {
                end = a;
            }
        }
        adjList.get(end).add(start);

        for(int j = adjList.size() - 1; j >= 0; j--)
        {
            if(adjList.get(j).size() <= 1)
            {
                adjList.remove(j);
            }
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
                            int temp = eCycle.remove(eCycle.size() - 1);
                            eCycle.add(0, temp);
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
        while(eCycle.get(eCycle.size() - 1) != end)
        {
            int temp = eCycle.remove(eCycle.size() - 1);
            eCycle.add(0, temp);
        }
        return eCycle;
    }

    public static int[] balance(ArrayList<ArrayList<Integer>> adjList)
    {
        int[] balances = new int[adjList.size()];
        for(int i = 0; i < adjList.size(); i++)
        {
            for(int j = 1; j < adjList.get(i).size(); j++)
            {
                balances[i] -= 1;
                balances[adjList.get(i).get(j)] += 1;
            }
        }
        return balances;
    }
}