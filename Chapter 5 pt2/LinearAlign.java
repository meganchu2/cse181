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
public class LinearAlign 
{
    public static ArrayList<String> index = new ArrayList<String>();
    public static int[][] mismatch = new int[27][27];
    public static String v = "";
    public static String w = "";

    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get strings
        v = in.next();
        in.nextLine();
        w = in.next();

        Scanner in2 = new Scanner(new FileReader(args[1]));
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

        String[] align = linearSpaceAlignment(0, v.length(), 0, w.length(), 0, 0);

        int score = 0;
        for(int i = 0; i < align[0].length(); i++)
        {
            if(align[0].charAt(i) == '-' || align[1].charAt(i) == '-')
            {
                score -= 5;
            }
            else
            {
                int row = index.indexOf(align[0].substring(i, i+1));
                int column = index.indexOf(align[1].substring(i, i+1));
                score += mismatch[row][column];
            }
        }
        System.out.println(score);
        System.out.println(align[0]);
        System.out.print(align[1]);
    }

    public static String[] linearSpaceAlignment(int top, int bottom, int left, int right, int forwardVal, int backWardVal)
    {
        String[] align = new String[2];
        align[0] = "";
        align[1] = "";
        if(left == right)
        {
            while(top < bottom)
            {
                align[0] += v.substring(top, top + 1);
                align[1] += "-";
                top++;
            }
            return align;
        }
        if(top == bottom)
        {
            while(left < right)
            {
                align[0] += "-";
                align[1] += w.substring(left, left + 1);
                left++;
            }
            return align;   
        }
        int midCol = (int)((left + right)/2);
        int[] midEdge = middleEdge(top, bottom, left, right, forwardVal, backWardVal);
        int midRow = midEdge[0];
        String[] temp = linearSpaceAlignment(top, midRow, left, midCol, forwardVal, midEdge[3]);
        align[0] += temp[0];
        align[1] += temp[1];
        if(midEdge[1] == -1)
        {
            align[0] += v.substring(midRow, midRow + 1);
            align[1] += "-";
        }
        else if(midEdge[1] == 1)
        {
            align[0] += "-";
            align[1] += w.substring(midCol, midCol + 1);
        }
        else if(midEdge[1] == 0)
        {
            align[0] += v.substring(midRow, midRow + 1);
            align[1] += w.substring(midCol, midCol + 1);
        }
        if(midEdge[1] == 1 || midEdge[1] == 0)
        {
            midCol += 1;
        }
        if(midEdge[1] == -1 || midEdge[1] == 0)
        {
            midRow += 1;
        }
        
        if(midRow > bottom)
        {
            System.out.println(midRow + " " + bottom);
        }
        temp = linearSpaceAlignment(midRow, bottom, midCol, right, midEdge[2], backWardVal);
        align[0] += temp[0];
        align[1] += temp[1];
        return align;       
    }

    public static int[] middleEdge(int top, int bottom, int left, int right, int forwardVal, int backWardVal)
    {
        int[] forward = lCSBackTrackF(top, bottom, left, right, forwardVal);
        ArrayList<int[]> backward = lCSBackTrackB(top, bottom, left, right, backWardVal);
        int[] mid = backward.get(0);
        int[] bT = backward.get(1);
        
        int[] toReturn = new int[4];

        int max = (int)Double.NEGATIVE_INFINITY;
        int midRow = -1;
        for(int i = 0; i < forward.length; i++)
        {
            if(forward[i] + mid[i] > max)
            {
                max = forward[i] + mid[i];
                midRow = i;
                toReturn[2] = forward[i];
                toReturn[3] = mid[i];
            }
        }

        if(bT[midRow] == -1)
        {
            toReturn[1] = -1;
        }
        else if(bT[midRow] == 1)
        {
            toReturn[1] = 1;
        }
        else if(bT[midRow] == 0)
        {
            toReturn[1] = 0;
        } 
        toReturn[0] = top + midRow;
        return toReturn;
    }

    public static int[] lCSBackTrackF(int top, int bottom, int left, int right, int forwardVal)
    {
        int[][] s = new int[bottom - top + 1][right - left + 1];
        int[][] bT = new int[bottom - top + 1][right - left + 1];
        int[] mid = new int[bottom - top + 1];
        s[0][0] = forwardVal;
        for(int i = 1; i <= bottom - top; i++)
        {
            s[i][0] = s[i-1][0] - 5;
        }
        for(int j = 1; j <= (int)((right - left)/2); j++)
        {
            s[0][j] = s[0][j-1] - 5;
        }
        for(int i = 1; i <= bottom - top; i++)
        {
            for(int j = 1; j <= (int)((right - left)/2); j++)
            {
                int row = index.indexOf(Character.toString(v.charAt(top + i-1)));
                int col = index.indexOf(Character.toString(w.charAt(left + j-1)));

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
            mid[i] = s[i][(int)((right - left)/2)];
        }
        mid[0] = s[0][(int)((right - left)/2)];
        return mid;
    }
    
    public static ArrayList<int[]> lCSBackTrackB(int top, int bottom, int left, int right, int backWardVal)
    {
        int[][] s = new int[bottom - top + 1][right - left + 1];
        int[][] bT = new int[bottom - top + 1][right - left + 1];
        int[] mid = new int[bottom - top + 1];
        int[] bTM = new int[bottom - top + 1];
        s[bottom - top][right - left] = backWardVal;
        for(int i = bottom - top - 1; i >= 0; i--)
        {
            s[i][right - left] = s[i+1][right - left] - 5;
        }
        for(int j = right - left - 1; j >= (int)((right - left)/2); j--)
        {
            s[bottom - top][j] = s[bottom - top][j+1] - 5;
        }
        for(int i = bottom -top - 1; i >= 0; i--)
        {
            for(int j = right - left - 1; j >= (int)((right - left)/2); j--)
            {
                int row = index.indexOf(Character.toString(v.charAt(top + i)));
                int col = index.indexOf(Character.toString(w.charAt(left + j)));

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
            mid[i] = s[i][(int)((right - left)/2)];
            bTM[i] = bT[i][(int)((right - left)/2)];
        }
        mid[bottom - top] = s[bottom - top][(int)((right - left)/2)];
        bTM[bottom - top] = 1;
        ArrayList<int[]> toReturn = new ArrayList<int[]>();
        toReturn.add(mid);
        toReturn.add(bTM);
        return toReturn;
    }
}