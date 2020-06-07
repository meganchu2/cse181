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
public class Overlap {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k and text  
        ArrayList<String> patterns = new ArrayList<String>();
        while(in.hasNextLine())
        {
            String s = in.nextLine();
            if(s != "")
            {
                patterns.add(s);
            }
        }

        ArrayList<ArrayList<String>> overlap = overlapGraph(patterns);
        int i = 0;
        while (i < overlap.size() - 1)
        {
            System.out.println(overlap.get(i).get(0) + " -> " + overlap.get(i).get(1));
            i++;
        }
        System.out.print(overlap.get(i).get(0) + " -> " + overlap.get(i).get(1));
    }

    public static ArrayList<ArrayList<String>> overlapGraph(ArrayList<String> patterns)
    {
        ArrayList<ArrayList<String>> overlap = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < patterns.size(); i++)
        {
            String one = patterns.get(i);
            for(int j = 0; j < patterns.size(); j++)
            {
                if(i != j)
                {
                    String two = patterns.get(j);
                    if(one.substring(1, one.length()).compareTo(two.substring(0, two.length() - 1)) == 0)
                    {
                        if(overlap.isEmpty())
                        {
                            overlap.add(new ArrayList<String>());
                            overlap.get(overlap.size() - 1).add(one);
                            overlap.get(overlap.size() - 1).add(two);
                        }
                        else
                        {
                            int x = 0;
                            while(x < overlap.size())
                            {
                                if(one.compareTo(overlap.get(x).get(0)) <= 0)
                                {
                                    break;
                                }
                                x++;
                            }
                            overlap.add(x, new ArrayList<String>());
                            overlap.get(x).add(one);
                            overlap.get(x).add(two);
                        }
                    }
                }
            }
        }
        return overlap;
    }
}