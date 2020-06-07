/**
 * Megan Chu 2/25/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class ColorEdge
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        ArrayList<ArrayList<Integer>> val1 = new ArrayList<ArrayList<Integer>>();

        while(in.hasNext())
        {
            String s = in.next();
            if(s.charAt(0) == '(')
            {
                val1.add(new ArrayList<Integer>());
                val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(1, s.length())));
            }
            else if(s.charAt(s.length() - 1) == ')')
            {
                val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(0, s.length() - 1)));
                // in.nextLine();
                break;
            }
            else 
            {
                int sp = s.indexOf(")(");
                if(sp >= 0)
                {
                    val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(0, sp)));
                    val1.add(new ArrayList<Integer>());
                    val1.get(val1.size() - 1).add(Integer.parseInt(s.substring(sp + 2, s.length())));
                }
                else
                {
                    val1.get(val1.size() - 1).add(Integer.parseInt(s));
                }
            }
        }

        ArrayList<int[]> edges = coloredEdges(val1);
        
        for(int i = 0; i < edges.size() - 1; i++)
        {
            System.out.print("(" + edges.get(i)[0] + ", " + edges.get(i)[1] + "), ");
        }
        System.out.print("(" + edges.get(edges.size() - 1)[0] + ", " + edges.get(edges.size() - 1)[1] + ")");
    }

    public static ArrayList<Integer> chromosomeToCycle(ArrayList<Integer> chrom)
    {
        ArrayList<Integer> n = new ArrayList<Integer>();
        for(int k = 0; k <= 2*chrom.size(); k++)
        {
            n.add(0);
        }
        for(int j = 1; j <= chrom.size(); j++)
        {
            int i = chrom.get(j - 1);
            if( i > 0)
            {
                n.set(2*j - 1, 2*i - 1);
                n.set(2*j, 2*i);
            }
            else
            {
                n.set(2*j - 1, -2*i);
                n.set(2*j, -2*i - 1);
            }
        }
        return n;
    }

    public static ArrayList<int[]> coloredEdges(ArrayList<ArrayList<Integer>> p)
    {
        ArrayList<int[]> edges = new ArrayList<int[]>();
        for(ArrayList<Integer> arr: p)
        {
            ArrayList<Integer> c = chromosomeToCycle(arr);
            c.remove(0);
            for(int j = 1; j <= arr.size(); j++)
            {
                int[] i = new int[2];
                i[0] = c.get(2*j - 1);
                i[1] = c.get((2*j)%c.size());
                edges.add(i);
            }
        }
        return edges;
    }
}