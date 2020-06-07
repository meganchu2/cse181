/**
 * Megan Chu 2/4/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class GlobalAlign
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get 2 strings
        String one = in.next();
        in.nextLine();
        String two = in.next();
        
        globalAlignment(one, two);
    }

    public static void globalAlignment(String a, String b)
    {
        int[][] s = new int[a.length()][b.length()];
        for(int i = 1; i <= a.length(); i++)
        {
            s[i][0] = 
        }
        for(int j = 0; j <= b.length(); j++)
        {
            s[0][j] = 0;
        }
        for(int i = 1; i <= v.length(); i++)
        {
            for(int j = 1; j <= w.length(); j++)
            {
                if(v.charAt(i-1) == w.charAt(j-1))
                {
                    s[i][j] = Math.max(Math.max(s[i-1][j], s[i][j-1]), s[i-1][j-1] + 1);
                }
                else
                {
                    s[i][j] = Math.max(s[i-1][j], s[i][j-1]);
                }
                if(s[i][j] == s[i-1][j])
                {
                    bT[i][j] = -1;
                }
                else if(s[i][j] == s[i][j-1])
                {
                    bT[i][j] = 1;
                }
                else if(s[i][j] == s[i-1][j-1] + 1 && v.charAt(i-1) == w.charAt(j-1))
                {
                    bT[i][j] = 0;
                }
            }
        }
        return bT;
    }
}