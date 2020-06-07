/**
 * Megan Chu 3/16/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class SquaredError 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        int k = Integer.parseInt(in.next());
        int m = Integer.parseInt(in.next());
        in.nextLine();
        ArrayList<double[]> centers = new ArrayList<double[]>();
        ArrayList<double[]> data = new ArrayList<double[]>();
        String c = in.nextLine();
        while(c.substring(0, 1).compareTo("-") != 0)
        {
            String[] v = c.split(" ");
            double[] coordinate = new double[m];
            for(int i = 0; i < coordinate.length; i++)
            {
                coordinate[i] = Double.parseDouble(v[i]);
            }
            centers.add(coordinate);
            c = in.nextLine();
        }
        
        while(in.hasNextLine())
        {
            c = in.nextLine();
            String[] v = c.split(" ");
            double[] coordinate = new double[m];
            for(int i = 0; i < coordinate.length; i++)
            {
                coordinate[i] = Double.parseDouble(v[i]);
            }
            data.add(coordinate);
        }
        
        System.out.printf("%.3f", squaredErrorDistortion(data, centers));
    }

    public static double squaredErrorDistortion(ArrayList<double[]> data, ArrayList<double[]> centers) 
    {
        ArrayList<ArrayList<double[]>> clusters = new ArrayList<ArrayList<double[]>>();
        for(double[] d: centers)
        {
            clusters.add(new ArrayList<double[]>());
        }

        double square = 0;
        for(double[] point: data)
        {
            double minDist = Double.POSITIVE_INFINITY;
            for(int i = 0; i < centers.size(); i++)
            {            
                double thisDist = 0;
                for(int x = 0; x < centers.get(i).length; x++)
                {
                    thisDist += Math.pow(point[x] - centers.get(i)[x], 2);
                }
                if(thisDist < minDist)
                {
                    minDist = thisDist;
                }
            }
            square += minDist;
        }

        square = square/data.size();

        return square;
    }
}