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
public class SuffixArr
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();

        ArrayList<String> suffix = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++)
        {
            String s = (text.substring(i, text.length()));
            int j = 0;
            while(j < suffix.size())
            {
                if(s.compareTo(suffix.get(j)) < 0)
                {
                    suffix.add(j, s);
                    break;
                }
                j++;
            }
            if(j == suffix.size())
            {
                suffix.add(s);
            }
        }

        int[] sufArr = suffixArrayConstruction(text, suffix);

        for(int i = 0; i < sufArr.length - 1; i++)
        {
            System.out.print(sufArr[i] + ", ");
        }
        System.out.print(sufArr[sufArr.length - 1]);
    }

    public static int[] suffixArrayConstruction(String text, ArrayList<String> suffix)
    {
        int[] sufArr = new int[suffix.size()];
        for(int i = 0; i < sufArr.length; i++)
        {
            sufArr[i] = text.lastIndexOf(suffix.get(i));
        }
        return sufArr;
    }
}