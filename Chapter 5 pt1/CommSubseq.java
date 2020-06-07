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
public class CommSubseq
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get 2 strings
        String dna1 = in.next();
        in.nextLine();
        String dna2 = in.next();
        
        int[][] bT = lCSBackTrack(dna1, dna2);

        System.out.print(outputLCS(bT, dna1, dna1.length(), dna2.length()));
    }

    public static String outputLCS(int[][] bT, String v, int i, int j)
    {
        String toReturn = new String();
        if(i == 0 || j == 0)
        {
            return toReturn;
        }
        if(bT[i][j] == -1)
        {
            toReturn += outputLCS(bT, v, i-1, j);
        }
        else if(bT[i][j] == 1)
        {
            toReturn += outputLCS(bT, v, i, j-1);
        }
        else
        {
            toReturn += outputLCS(bT, v, i-1, j-1);
            toReturn += v.substring(i-1, i);
        }
        return toReturn;
    }

    public static int[][] lCSBackTrack(String v, String w)
    {
        int[][] s = new int[v.length() + 1][w.length() + 1];
        int[][] bT = new int[v.length() + 1][w.length() + 1];
        for(int i = 0; i <= v.length(); i++)
        {
            s[i][0] = 0;
        }
        for(int j = 0; j <= w.length(); j++)
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