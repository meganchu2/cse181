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
public class ArrayMatch
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
        
        ArrayList<String> patterns = new ArrayList<String>();
        while(in.hasNextLine())
        {
            patterns.add(in.nextLine());
        }

        int[] sufArr = suffixArrayConstruction(text, suffix);
        ArrayList<Integer> match = suffixArrayPatternMatching(patterns, suffix, sufArr);

        for(int i = 0; i < match.size() - 1; i++)
        {
            System.out.print(match.get(i) + " ");
        }
        System.out.print(match.get(match.size() - 1));
    }

    public static ArrayList<Integer> suffixArrayPatternMatching(ArrayList<String> patterns, ArrayList<String> suffix, int[] sufArr)
    {
        ArrayList<Integer> match = new ArrayList<Integer>();
        for(String p: patterns)
        {
            for(int i = 0; i < suffix.size(); i++)
            {
                String s = suffix.get(i);
                if(s.length() >= p.length() && p.compareTo(s.substring(0, p.length())) == 0)
                {
                    int toAdd = sufArr[i];
                    int k = 0;
                    while(k < match.size())
                    {
                        if(toAdd < match.get(k))
                        {
                            match.add(k, toAdd);
                            break;
                        }
                        k++;
                    }
                    if(k == match.size())
                    {
                        match.add(toAdd);
                    }
                }
            }
        }
        return match;
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