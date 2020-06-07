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
public class EulerianCycle {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        while(in.hasNextLine())
        {
            if(in.hasNext())
            {
                adjList.add(new ArrayList<Integer>());
                adjList.get(adjList.size() - 1).add(Integer.parseInt(in.next()));
                in.next();
                String s = in.next();
                String[] ints = s.split(",");
                for(String num: ints)
                {
                    adjList.get(adjList.size() - 1).add(Integer.parseInt(num));
                }
                if(in.hasNextLine())
                {
                    in.nextLine();
                }
            }
        }
        
        ArrayList<Integer> eCycle = findEulerianCycle(adjList);
        for(int i = 0; i < eCycle.size() - 1; i++)
        {
            System.out.print(eCycle.get(i) + "->");
        }
        System.out.print(eCycle.get(eCycle.size() - 1));
    }

    public static ArrayList<Integer> findEulerianCycle(ArrayList<ArrayList<Integer>> adjList)
    {
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
        eCycle.add(store);
        return eCycle;
    }
}