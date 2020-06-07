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
public class DeBruijnGraph {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get kmers
        ArrayList<String> patterns = new ArrayList<String>();
        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                patterns.add(s);
            }
        }
        
        ArrayList<ArrayList<String>> dBGraph = deBruijnGraph(patterns);
        int i = 0;
        while (i < dBGraph.size() - 1)
        {            
            System.out.print(dBGraph.get(i).get(0) + " -> ");
            int j = 1;
            while(j < dBGraph.get(i).size() - 1)
            {
                System.out.print(dBGraph.get(i).get(j) + ",");
                j++;
            }
            System.out.println(dBGraph.get(i).get(j));
            i++;
        }
        System.out.print(dBGraph.get(i).get(0) + " -> ");
        int j = 1;
        while(j < dBGraph.get(i).size() - 1)
        {
            System.out.print(dBGraph.get(i).get(j) + ",");
            j++;
        }
        System.out.print(dBGraph.get(i).get(j));
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
}