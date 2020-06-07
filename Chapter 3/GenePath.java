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
public class GenePath {
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

        System.out.print(stringSpelled(patterns));
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
}