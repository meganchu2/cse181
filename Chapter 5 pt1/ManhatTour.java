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
public class ManhatTour
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get n and m
        int n = Integer.parseInt(in.next());
        int m = Integer.parseInt(in.next());
        in.nextLine();

        int[][] down = new int[n+1][m+2];
        String s = in.nextLine();
        int k = 1;
        while(k <= n)
        {
            String[] w = s.split(" ");
            for(int i = 0; i < w.length; i++)
            {
                down[k][i] = Integer.parseInt(w[i]);
            }
            s = in.nextLine();
            k++;   
        }

        int[][] right = new int[n+2][m+1];
        k = 0;
        while(k < m)
        {
            s = in.nextLine();
            String[] w = s.split(" ");
            for(int i = 0; i < w.length; i++)
            {
                right[k][i+1] = Integer.parseInt(w[i]);
            }
            k++;
        }

        System.out.print(manhattanTourist(n, m, down, right));
    }

    public static int manhattanTourist(int n, int m, int[][] down, int[][] right)
    {
        int[][] s = new int[n+1][m+1];
        s[0][0] = 0;
        for(int i = 1; i <= n; i++)
        {
            s[i][0] = s[i-1][0] + down[i][0];
        }
        for(int j = 1; j <= m; j++)
        {
            s[0][j] = s[0][j-1] + right[0][j];
        }
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= m; j++)
            {
                s[i][j] = Math.max(s[i-1][j] + down[i][j], s[i][j-1] + right[i][j]);
            }
        }
        return s[n][m];
    }
}