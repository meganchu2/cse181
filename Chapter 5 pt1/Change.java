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
public class Change 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));

        // get money and coins
        int money = Integer.parseInt(in.next());
        in.nextLine();

        ArrayList<Integer> coins = new ArrayList<Integer>();
        String s = in.nextLine();
        String[] coin = s.split(",");
        for(String i: coin)
        {
            coins.add(Integer.parseInt(i));
        }

        System.out.print(dpChange(money, coins));
    }

    public static int dpChange(int money, ArrayList<Integer> coins)
    {
        ArrayList<Integer> min = new ArrayList<Integer>();
        min.add(0);
        for(int i = 1; i <= money; i++)
        {
            min.add(-1);
            for(int j = 0; j < coins.size(); j++)
            {
                if(i >= coins.get(j))
                {
                    if((min.get(i - coins.get(j)) + 1) < min.get(i))
                    {
                        min.set(i, min.get(i - coins.get(j)) + 1);
                    }
                    else if(min.get(i) == -1)
                    {
                        min.set(i, min.get(i - coins.get(j)) + 1);
                    }
                }
            }
        }
        return min.get(money);
    }
}