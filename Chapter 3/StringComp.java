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
public class StringComp {
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get k and text  
        int k = Integer.parseInt(in.next());
        in.nextLine();
        String text = in.nextLine();

        ArrayList<String> composition = stringComposition(k, text);
        for(int i = 0; i < composition.size() - 1; i++)
        {
            System.out.println(composition.get(i) + " ");
        }
        System.out.print(composition.get(composition.size() - 1));
    }

    public static ArrayList<String> stringComposition(int k, String text)
    {
        ArrayList<String> composition = new ArrayList<String>();
        for(int i = 0; i <= text.length() - k; i++)
        {
            String kmer = text.substring(i, i+k);
            if(composition.isEmpty())
            {
                composition.add(kmer);
            }
            else
            {
                int j = 0;
                while (j < composition.size())
                {
                    if(kmer.compareTo(composition.get(j)) <= 0)
                    {
                        composition.add(j, kmer);
                        break;
                    }
                    j++;
                }
                if (j == composition.size())
                {
                    composition.add(kmer);
                }
            }
        }
        return composition;
    }
}