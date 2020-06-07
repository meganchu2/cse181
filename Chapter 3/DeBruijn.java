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
public class DeBruijn {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k and text  
        int k = Integer.parseInt(in.next());
        in.nextLine();
        String text = in.nextLine();
        
        ArrayList<ArrayList<String>> dBGraph = deBruijnGraph(k, text);
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

    public static ArrayList<ArrayList<String>> deBruijnGraph(int k, String text)
    {
        ArrayList<ArrayList<String>> dBGraph = new ArrayList<ArrayList<String>>();
        for(int i = 0; i <= text.length() - k; i++)
        {
            String node1 = text.substring(i, i + k - 1);
            String node2 = text.substring(i + 1, i + k);
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