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
public class MultipleAlign 
{
    public static String v = "";
    public static String w = "";
    public static String u = "";

    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get strings
        v = in.next();
        in.nextLine();
        w = in.next();
        in.nextLine();
        u = in.next();

        int[][][] bT = lCSBackTrack();
        String[] align = outputLCS(bT, v.length(), w.length(), u.length());
        System.out.println(align[0]);
        System.out.println(align[1]);
        System.out.println(align[2]);
        System.out.print(align[3]);
    }

    public static String[] outputLCS(int[][][] bT, int i, int j, int k)
    {
        String[] toReturn = new String[4];
        toReturn[0] = "0";
        toReturn[1] = "";
        toReturn[2] = "";
        toReturn[3] = "";
        if(i == 0 && j == 0 && k == 0)
        {
            return toReturn;
        }
        else if((j == 0 && k == 0) || bT[i][j][k] == 0)
        {
            String[] temp = outputLCS(bT, i-1, j, k);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += "-";
            toReturn[3] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
        else if((i == 0 && k == 0) || bT[i][j][k] == 1)
        {
            String[] temp = outputLCS(bT, i, j-1, k);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, j);
            toReturn[3] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
        else if((i == 0 && j == 0) || bT[i][j][k] == 2)
        {
            String[] temp = outputLCS(bT, i, j, k-1);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += "-";
            toReturn[2] += "-";
            toReturn[3] += u.substring(k-1, k);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
         else if(k == 0 || bT[i][j][k] == 3)
        {
            String[] temp = outputLCS(bT, i-1, j-1, k);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += w.substring(j-1, j);;
            toReturn[3] += "-";
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
        else if(j == 0 || bT[i][j][k] == 4)
        {
            String[] temp = outputLCS(bT, i-1, j, k-1);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += v.substring(i-1, i);;
            toReturn[2] += "-";
            toReturn[3] += u.substring(k-1, k);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
        else if(i == 0 || bT[i][j][k] == 5)
        {
            String[] temp = outputLCS(bT, i, j-1, k-1);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += "-";
            toReturn[2] += w.substring(j-1, j);
            toReturn[3] += u.substring(k-1, k);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]));
        }
        else if(bT[i][j][k] == 6 || bT[i][j][k] == 7)
        {
            String[] temp = outputLCS(bT, i-1, j-1, k-1);
            toReturn[1] += temp[1];
            toReturn[2] += temp[2];
            toReturn[3] += temp[3];
            toReturn[1] += v.substring(i-1, i);
            toReturn[2] += w.substring(j-1, j);
            toReturn[3] += u.substring(k-1, k);
            toReturn[0] = Integer.toString(Integer.parseInt(temp[0]) + bT[i][j][k] - 6);
        }
        return toReturn;
    }

    public static int[][][] lCSBackTrack()
    {
        int[][][] s = new int[v.length() + 1][w.length() + 1][u.length() + 1];
        int[][][] bT = new int[v.length() + 1][w.length() + 1][u.length() + 1];
        s[0][0][0] = 0;
        for(int i = 1; i <= v.length(); i++)
        {
            s[i][0][0] = s[i-1][0][0];
            bT[i][0][0] = 0;
        }
        for(int j = 1; j <= w.length(); j++)
        {
            s[0][j][0] = s[0][j-1][0];
            bT[0][j][0] = 2;
        }
        for(int k = 1; k <= u.length(); k++)
        {
            s[0][0][k] = s[0][0][k-1];
            bT[0][0][k] = 3;
        }
        for(int i = 1; i <= v.length(); i++)
        {
            for(int j = 1; j <= w.length(); j++)
            {
                for(int k = 1; k <= u.length(); k++)
                {
                    char v1 = v.charAt(i-1);
                    char w1 = w.charAt(j-1);
                    char u1 = u.charAt(k-1);
                    
                    int allSame = 0;
                    if(v1 == w1 && w1 == u1)
                    {
                        allSame = 1;
                    }
                    s[i][j][k] = (int) Double.NEGATIVE_INFINITY;
                    if(s[i-1][j][k] > s[i][j][k])
                    {
                        s[i][j][k] = s[i-1][j][k];
                        bT[i][j][k] = 0;
                    }
                    if(s[i][j-1][k] > s[i][j][k])
                    {
                        s[i][j][k] = s[i][j-1][k];
                        bT[i][j][k] = 1;
                    }
                    if(s[i][j][k-1] > s[i][j][k])
                    {
                        s[i][j][k] = s[i][j][k-1];
                        bT[i][j][k] = 2;
                    }
                    if(s[i-1][j-1][k] > s[i][j][k])
                    {
                        s[i][j][k] = s[i-1][j-1][k];
                        bT[i][j][k] = 3;
                    }
                    if(s[i-1][j][k-1] > s[i][j][k])
                    {
                        s[i][j][k] = s[i-1][j][k-1];
                        bT[i][j][k] = 4;
                    }
                    if(s[i][j-1][k-1] > s[i][j][k])
                    {
                        s[i][j][k] = s[i][j-1][k-1];
                        bT[i][j][k] = 5;
                    }
                    if(s[i-1][j-1][k-1] + allSame > s[i][j][k])
                    {
                        s[i][j][k] = s[i-1][j-1][k-1] + allSame;
                        bT[i][j][k] = 6;
                        if(allSame == 1)
                        {
                            bT[i][j][k] = 7;
                        }
                    }
                }
            }
        }
        return bT;
    }    
}