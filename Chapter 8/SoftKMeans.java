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
public class SoftKMeans 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        int k = Integer.parseInt(in.next());
        int m = Integer.parseInt(in.next());
        double b = Double.parseDouble(in.next());
        in.nextLine();
        ArrayList<double[]> data = new ArrayList<double[]>();      
        while(in.hasNextLine())
        {
            String c = in.nextLine();
            String[] v = c.split(" ");
            double[] coordinate = new double[m];
            for(int i = 0; i < coordinate.length; i++)
            {
                coordinate[i] = Double.parseDouble(v[i]);
            }
            data.add(coordinate);
        }
        
        ArrayList<double[]> centers = softKMeansClustering(data, k, m, b);
        for(int j = 0; j < centers.size() - 1; j++)
        {
            for(int x = 0; x < centers.get(j).length - 1; x++)
            {
                System.out.printf("%.3f ", centers.get(j)[x]);
            }
            System.out.printf("%.3f\n", centers.get(j)[centers.get(j).length - 1]);
        }
        for(int x = 0; x < centers.get(centers.size() - 1).length - 1; x++)
        {
            System.out.printf("%.3f ", centers.get(centers.size() - 1)[x]);
        }
        System.out.printf("%.3f", centers.get(centers.size() - 1)[centers.get(centers.size() - 1).length - 1]);
    }

    public static ArrayList<double[]> softKMeansClustering(ArrayList<double[]> data, int k, int m, double b) 
    {
        ArrayList<double[]> centers = new ArrayList<double[]>();

        for(int o = 0; o < k; o++)
        {
            centers.add(data.get(o));
        }
        
        for(int t = 0; t < 100; t++)
        {
            double[][] hiddenMatrix = new double[k][data.size()];
            for(int j = 0; j < data.size(); j++)
            {
                double sum = 0;
                for(int i = 0; i < k; i++)
                {
                    hiddenMatrix[i][j] = Math.exp(-b*d(data.get(j), centers.get(i)));
                    sum += hiddenMatrix[i][j];
                }
                for(int i = 0; i < k; i++)
                {
                    hiddenMatrix[i][j] /= sum;
                }
            }
            
            for(int i = 0; i < k; i++)
            {
                double[] newCenter = new double[m];
                for(int j = 0; j < newCenter.length; j++)
                {
                    newCenter[j] = calc(hiddenMatrix[i], data, j);
                }
                centers.set(i, newCenter);
            }
        }

        return centers;
    }

    public static double calc(double[] hiddenVector, ArrayList<double[]> data, int j)
    {
        double numerator = 0;
        double denominator = 0;

        for(int i = 0; i < hiddenVector.length; i++)
        {
            numerator += hiddenVector[i]*data.get(i)[j];
            denominator += hiddenVector[i];
        }

        double centerVal = numerator/denominator;

        return centerVal;
    }

    public static double d(double[] one, double[] two)
    {
        double dist = 0;

        for(int i = 0; i < one.length; i++)
        {
            dist += Math.pow(one[i] - two[i], 2);
        }

        dist = Math.sqrt(dist);

        return dist;
    }
}