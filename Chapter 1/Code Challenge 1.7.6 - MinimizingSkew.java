import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class MyClass {
    public static void main(String args[]) throws FileNotFoundException
    {
        String genome = args[0];
        ArrayList minI = minimizingSkew(genome);
        for(int i = 0; i < minI.size() - 1; i++)
        {
            System.out.print(minI.get(i) + " ");
        }
        if(minI.size() > 0)
        {
          System.out.print(minI.get(minI.size() - 1));
        }
    }

    public static ArrayList minimizingSkew(String genome) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(genome));
        in.useDelimiter("");
        ArrayList<Integer> minI = new ArrayList<Integer>();
        int min = 0;
        int tracker = 0;
        int i = 0;
        while(in.hasNext())
        {
            char pre = (in.next()).charAt(0);
            if(pre == 'C')
            {
                tracker--;
            }
            else if(pre == 'G')
            {
                tracker++;
            }
            if(pre == 'C' || pre == 'G' || pre == 'A' || pre == 'T')
            {
                if(tracker == min)
                {
                    minI.add(i+1);
                }
                if(tracker < min)
                {
                    min = tracker;
                    minI.clear();
                    minI.add(i+1);
                }
                i++;
            }
        }
        in.close();
        return minI;
    }
}