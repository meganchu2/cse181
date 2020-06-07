/**
 * Megan Chu 2/11/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class MidEdge 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get strings
        String dna1 = in.next();
        in.nextLine();
        String dna2 = in.next();

        Scanner in2 = new Scanner(new FileReader(args[1]));
        ArrayList<String> index = new ArrayList<String>();
        int[][] mismatch = new int[27][27];
        in2.skip("   ");
        String col = in2.nextLine();
        String[] cols = col.split("  ");
        for(String s: cols)
        {
            index.add(s);
        }
        for(int i = 0; i < index.size(); i++)
        {
            in2.next(); // skip first col
            for(int j = 0; j < index.size(); j++)
            {
                mismatch[i][j] = Integer.parseInt(in2.next());
            }
            if(in2.hasNextLine())
            {
                in2.nextLine();
            }
        }

        int[] forward = lCSBackTrackF(dna1, dna2, index, mismatch);
        ArrayList<int[]> backward = lCSBackTrackB(dna1, dna2, index, mismatch);
        int[] mid = backward.get(0);
        int[] bT = backward.get(1);

        int max = (int)Double.NEGATIVE_INFINITY;
        int midRow = -1;
        for(int i = 1; i < mid.length; i++)
        {
            if(forward[i] + mid[i] > max)
            {
                max = forward[i] + mid[i];
                midRow = i;
            }
        }
        System.out.print("(" + midRow + ", " + (int)(dna2.length()/2) + ") ");
        if(bT[midRow] == -1)
        {
            System.out.print("(" + (midRow + 1) + ", " + (int)(dna2.length()/2) + ")");
        }
        else if(bT[midRow] == 1)
        {
            System.out.print("(" + midRow + ", " + ((int)(dna2.length()/2) + 1) + ")");
        }
        else if(bT[midRow] == 0)
        {
            System.out.print("(" + (midRow + 1) + ", " + ((int)(dna2.length()/2) + 1) + ")");
        }
    }

    public static int[] lCSBackTrackF(String v, String w, ArrayList<String> index, int[][] mismatch)
    {
        int[][] s = new int[v.length() + 1][w.length() + 1];
        int[][] bT = new int[v.length() + 1][w.length() + 1];
        int[] mid = new int[v.length() + 1];
        s[0][0] = 0;
        for(int i = 1; i <= v.length(); i++)
        {
            s[i][0] = s[i-1][0] - 5;
        }
        for(int j = 1; j <= (int)(w.length()/2); j++)
        {
            s[0][j] = s[0][j-1] - 5;
        }
        for(int i = 1; i <= v.length(); i++)
        {
            for(int j = 1; j <= (int)(w.length()/2); j++)
            {
                int row = index.indexOf(Character.toString(v.charAt(i-1)));
                int col = index.indexOf(Character.toString(w.charAt(j-1)));

                s[i][j] = Math.max(Math.max(s[i-1][j] - 5, s[i][j-1] - 5), s[i-1][j-1] + mismatch[row][col]);

                if(s[i][j] == s[i-1][j] - 5)
                {
                    bT[i][j] = -1;
                }
                else if(s[i][j] == s[i][j-1] - 5)
                {
                    bT[i][j] = 1;
                }
                else if(s[i][j] == s[i-1][j-1] + mismatch[row][col])
                {
                    bT[i][j] = 0;
                }
            }
            mid[i] = s[i][(int)(w.length()/2)];
        }
        return mid;
    }
    
    public static ArrayList<int[]> lCSBackTrackB(String v, String w, ArrayList<String> index, int[][] mismatch)
    {
        int[][] s = new int[v.length() + 1][w.length() + 1];
        int[][] bT = new int[v.length() + 1][w.length() + 2];
        int[] mid = new int[v.length() + 1];
        int[] bTM = new int[v.length() + 1];
        s[v.length()][w.length()] = 0;
        for(int i = v.length() - 1; i >= 1; i--)
        {
            s[i][0] = s[i+1][0] - 5;
        }
        for(int j = w.length() - 1; j >= (int)(w.length()/2); j--)
        {
            s[0][j] = s[0][j+1] - 5;
        }
        for(int i = v.length() - 1; i >= 1; i--)
        {
            for(int j = w.length() - 1; j >= (int)(w.length()/2); j--)
            {
                int row = index.indexOf(Character.toString(v.charAt(i)));
                int col = index.indexOf(Character.toString(w.charAt(j)));

                s[i][j] = Math.max(Math.max(s[i+1][j] - 5, s[i][j+1] - 5), s[i+1][j+1] + mismatch[row][col]);

                if(s[i][j] == s[i+1][j] - 5)
                {
                    bT[i][j] = -1;
                }
                else if(s[i][j] == s[i][j+1] - 5)
                {
                    bT[i][j] = 1;
                }
                else if(s[i][j] == s[i+1][j+1] + mismatch[row][col])
                {
                    bT[i][j] = 0;
                }
            }
            mid[i] = s[i][(int)(w.length()/2)];
            bTM[i] = bT[i][(int)(w.length()/2)];
        }
        ArrayList<int[]> toReturn = new ArrayList<int[]>();
        toReturn.add(mid);
        toReturn.add(bTM);
        return toReturn;
    }
}