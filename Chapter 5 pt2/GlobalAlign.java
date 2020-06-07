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
public class GlobalAlign 
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

        int[][] bT = lCSBackTrack(dna1, dna2, index, mismatch);
        String[] align = outputLCS(bT, dna1, dna2, dna1.length(), dna2.length(), index, mismatch);
        System.out.println(align[0]);
        System.out.println(align[1]);
        System.out.print(align[2]);
    }

    public static String[] outputLCS(int[][] bT, String v, String w, int i, int j, ArrayList<String> index, int[][] mismatch)
    {
        String[] toReturn = new String[3];
        toReturn[0] = "0";
        toReturn[1] = "";
        toReturn[2] = "";
        if(i == 0 && j == 0)
        {
            return toReturn;
        }
        else if(i == 0)
        {
            String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, i);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 5);
        }
        else if(j == 0)
        {
            String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 5);
        }
        else if(bT[i][j] == -1)
        {
            String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 5);
        }
        else if(bT[i][j] == 1)
        {
            String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, j);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 5);
        }
        else
        {
            String[] temp = outputLCS(bT, v, w, i-1, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += w.substring(j-1, j);
            int row = index.indexOf(v.substring(i-1, i));
            int col = index.indexOf(w.substring(j-1, j));
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) + mismatch[row][col]);
        }
        return toReturn;
    }

    public static int[][] lCSBackTrack(String v, String w, ArrayList<String> index, int[][] mismatch)
    {
        int[][] s = new int[v.length() + 1][w.length() + 1];
        int[][] bT = new int[v.length() + 1][w.length() + 1];
        s[0][0] = 0;
        for(int i = 1; i <= v.length(); i++)
        {
            s[i][0] = s[i-1][0] - 5;
        }
        for(int j = 1; j <= w.length(); j++)
        {
            s[0][j] = s[0][j-1] - 5;
        }
        for(int i = 1; i <= v.length(); i++)
        {
            for(int j = 1; j <= w.length(); j++)
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
        }
        return bT;
    }    

    public class Node
    {
        String val = new String();
        Node prev = null;
        public void setVal(String s)
        {
            val = s;
        }
        public void setPrev(Node n)
        {
            prev = n;
        }
    }
}