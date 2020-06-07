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
public class LastToFirst
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        int i = Integer.parseInt(in.nextLine());

        System.out.print(lastToFirstMapping(text, i));
    }

    public static int lastToFirstMapping(String text, int index)
    {        
        char[] temp = text.toCharArray();
        ArrayList<String> lastCol = new ArrayList<String>();
        ArrayList<String> firstCol = new ArrayList<String>();
        String inverse = "$";

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
        
        String last = lastCol.get(index);
        int lastToFirst = firstCol.indexOf(last);
        return lastToFirst;     
    }
}