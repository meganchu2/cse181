/**
 * Megan Chu 3/4/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class BWT
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();


        System.out.print(burrowsWheelerTransform(text));
    }

    public static String burrowsWheelerTransform(String text)
    {        
        ArrayList<String> rotation = new ArrayList<String>();
        String transform = "";
        for(int i = 0; i < text.length(); i++)
        {
            String s = text.substring(i, text.length()) + text.substring(0, i);
            int j = 0;
            while(j < rotation.size())
            {
                if(s.compareTo(rotation.get(j)) < 0)
                {
                    rotation.add(j, s);
                    break;
                }
                j++;
            }
            if(j == rotation.size())
            {
                rotation.add(s);
            }
        }

        for(String s: rotation)
        {
            transform += s.substring(s.length() - 1, s.length());
        }

        return transform;
    }
}