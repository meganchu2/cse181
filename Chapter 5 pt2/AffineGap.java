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
public class AffineGap 
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

        ArrayList<int[][]> bT = lCSBackTrack(dna1, dna2, index, mismatch);
        String[] align = outputLCS(bT, dna1, dna2, dna1.length(), dna2.length(), index, mismatch);
        // System.out.println(align[0]);
        System.out.println(align[1]);
        System.out.print(align[2]);
    }

    public static String[] outputLCS(ArrayList<int[][]> bT, String v, String w, int i, int j, ArrayList<String> index, int[][] mismatch)
    {
        String[] toReturn = new String[3];
        toReturn[0] = "0";
        toReturn[1] = "";
        toReturn[2] = "";
        int bTStart = (bT.get(3))[0][0];
        int[][] bTS = bT.get(bTStart);
        if(i == 0 && j == 0)
        {
            return toReturn;
        }

       /* if(i == 0)
        {
            String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, i);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
        }
        else if(j == 0)
        {
            String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
        }*/
        else if(bTS[i][j] == -1)
        {
            (bT.get(3))[0][0] = 0;
            bTS = bT.get(0);
            if(bTS[i][j] == -2)
            {
                String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
                toReturn[1] += temp[1];
                toReturn[2] += temp[2];
                toReturn[1] += v.substring(i-1, i);
                toReturn[2] += "-";
                toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
            }
            else if(bTS[i][j] == -11)
            {
                (bT.get(3))[0][0] = 1;
                String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
                toReturn[1] += temp[1];
                toReturn[2] += temp[2];
                toReturn[1] += v.substring(i-1, i);
                toReturn[2] += "-";
                toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 11);
            }
        }
        else if(bTS[i][j] == 1)
        {
            (bT.get(3))[0][0] = 2;
            bTS = bT.get(2);
            if(bTS[i][j] == 2)
            {
                String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
                toReturn[1] += temp[1];
                toReturn[2] += temp[2];
                toReturn[1] += "-";
                toReturn[2] += w.substring(j-1, j);
                toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
            }
            else if(bTS[i][j] == 11)
            {
                (bT.get(3))[0][0] = 1;
                String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
                toReturn[1] += temp[1];
                toReturn[2] += temp[2];
                toReturn[1] += "-";
                toReturn[2] += w.substring(j-1, j);
                toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 11);
            }
        }
        else if(bTS[i][j] == -2)
        {
            String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
        }
        else if(bTS[i][j] == -11)
        {
            (bT.get(3))[0][0] = 1;
            String[] temp = outputLCS(bT, v, w, i-1, j, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 11);
        }
        else if(bTS[i][j] == 2)
        {
            String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, j);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 1);
        }
        else if(bTS[i][j] == 11)
        {
            (bT.get(3))[0][0] = 1;
            String[] temp = outputLCS(bT, v, w, i, j-1, index, mismatch);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, j);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) - 11);
        }
        else if(bTS[i][j] == 0)
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

    public static ArrayList<int[][]> lCSBackTrack(String v, String w, ArrayList<String> index, int[][] mismatch)
    {
        int[][] mid = new int[v.length() + 1][w.length() + 1];
        int[][] up = new int[v.length() + 1][w.length() + 1];
        int[][] low = new int[v.length() + 1][w.length() + 1];
        int[][] bTM = new int[v.length() + 1][w.length() + 1];
        int[][] bTU = new int[v.length() + 1][w.length() + 1];
        int[][] bTL = new int[v.length() + 1][w.length() + 1];
        mid[0][0] = 0;
        for(int i = 0; i <= v.length(); i++)
        {
            low[i][0] = (int) Double.NEGATIVE_INFINITY;
        }
        for(int j = 0; j <= w.length(); j++)
        {
            up[0][j] = (int) Double.NEGATIVE_INFINITY;
        }
        for(int i = 1; i <= v.length(); i++)
        {
            for(int j = 1; j <= w.length(); j++)
            {
                int row = index.indexOf(Character.toString(v.charAt(i-1)));
                int col = index.indexOf(Character.toString(w.charAt(j-1)));

                low[i][j] = Math.max(low[i-1][j] - 1, mid[i-1][j] - 11);
                up[i][j] = Math.max(up[i][j-1] - 1, mid[i][j-1] - 11);
                mid[i][j] = Math.max(Math.max(low[i][j], up[i][j]), mid[i-1][j-1] + mismatch[row][col]);

                if(low[i][j] == low[i-1][j] - 1)
                {
                    bTL[i][j] = -2;
                }
                else if(low[i][j] == mid[i-1][j] - 11)
                {
                    bTL[i][j] = -11;                
                }

                if(up[i][j] == up[i][j-1] - 1)
                {
                    bTU[i][j] = 2;
                }
                else if(up[i][j] == mid[i][j-1] - 11);
                {
                    bTU[i][j] = 11;
                }

                if(mid[i][j] == low[i][j])
                {
                    bTM[i][j] = -1;
                }
                else if(mid[i][j] == up[i][j])
                {
                    bTM[i][j] = 1;
                }
                else if(mid[i][j] == mid[i-1][j-1] + mismatch[row][col])
                {
                    bTM[i][j] = 0;
                }
                
            }
        }
               
        int score = Math.max(Math.max(low[v.length()][w.length()], up[v.length()][w.length()]), mid[v.length()][w.length()]);
        System.out.println(score);

        int[][] bTS = new int[1][1];
        if(score == low[v.length()][w.length()])
        {
            bTS[0][0] = 0;
        }
        else if(score == mid[v.length()][w.length()])
        {
            bTS[0][0] = 1;
        }
        else if(score == up[v.length()][w.length()])
        {
            bTS[0][0] = 2;
        }

        ArrayList<int[][]> bT = new ArrayList<int[][]>();
        bT.add(bTL);
        bT.add(bTM);
        bT.add(bTU);
        bT.add(bTS);
        return bT;
    }
}