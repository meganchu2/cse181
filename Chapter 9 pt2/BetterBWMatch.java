/**
 * Megan Chu 3/11/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class BetterBWMatch
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        String pat = in.nextLine();
        String[] pats = pat.split(" ");

        char[] temp = text.toCharArray();
        ArrayList<String> lastCol = new ArrayList<String>();
        ArrayList<String> firstCol = new ArrayList<String>();

        for(char c: temp)
        {
            String s = String.valueOf(c);
            lastCol.add(s);
            if(firstCol.isEmpty())
            {
                firstCol.add(s);
            }
            else
            {
                int i = 0;
                while(i < firstCol.size())
                {
                    if(s.compareTo(firstCol.get(i)) < 0)
                    {
                        firstCol.add(i, s);
                        break;
                    }
                    i++;
                }
                if(i == firstCol.size())
                {
                    firstCol.add(s);
                }
            }
        }

        for(int k = 0; k < firstCol.size(); k++)
        {
            int n = 0;
            if(firstCol.get(k).length() == 1)
            {
                String let = firstCol.get(k);
                firstCol.set(k, let + n);
                for(int m = k + 1; m < firstCol.size(); m++)
                {
                    if(firstCol.get(m).compareTo(let) == 0)
                    {
                        n++;
                        firstCol.set(m, let + n);
                    }
                }
            }
        }

        for(int k = 0; k < lastCol.size(); k++)
        {
            int n = 0;
            if(lastCol.get(k).length() == 1)
            {
                String let = lastCol.get(k);
                lastCol.set(k, let + n);
                for(int m = k + 1; m < lastCol.size(); m++)
                {
                    if(lastCol.get(m).compareTo(let) == 0)
                    {
                        n++;
                        lastCol.set(m, let + n);
                    }
                }
            }
        }
        
        ArrayList<Integer> match = new ArrayList<Integer>();
        for(String p: pats)
        {
            match.add(betterBWMatching(firstCol, lastCol, p));
        }

        for(int x = 0; x < match.size() - 1; x++)
        {
            System.out.print(match.get(x) + " ");
        }
        System.out.print(match.get(match.size() - 1));
    }

    public static int betterBWMatching(ArrayList<String> firstCol, ArrayList<String> lastCol, String pattern)
    {
        int top = 0;
        int bottom = lastCol.size() - 1;
        while(top <= bottom)
        {
            if(pattern.length() > 0)
            {
                String s = pattern.substring(pattern.length() - 1, pattern.length());
                pattern = pattern.substring(0, pattern.length() - 1);
                if(firstOccurrence(firstCol, s) >= 0)
                {
                    top = firstOccurrence(firstCol, s) + count(top, lastCol, s);
                    bottom = firstOccurrence(firstCol, s) + count(bottom + 1, lastCol, s) - 1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                return bottom - top + 1;
            }
        }
        return 0;
    }

    public static int count(int i, ArrayList<String> lastCol, String symbol)
    {
        int count = 0;
        for(int j = 0; j < i; j++)
        {
            if(lastCol.get(j).substring(0, 1).compareTo(symbol) == 0)
            {
                count++;
            }
        }
        return count;
    }

    public static int firstOccurrence(ArrayList<String> firstCol, String symbol)
    {
        for(int i = 0; i < firstCol.size(); i++)
        {
            if(firstCol.get(i).substring(0, 1).compareTo(symbol) == 0)
            {
                return i;
            }
        }
        return -1;
    }

    public static int lastToFirstMapping(ArrayList<String> firstCol, ArrayList<String> lastCol, int index)
    {        
        
        String last = lastCol.get(index);
        int lastToFirst = firstCol.indexOf(last);
        return lastToFirst;     
    }
}