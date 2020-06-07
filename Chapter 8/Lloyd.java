/**
 * Megan Chu 3/16/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class Lloyd 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        int k = Integer.parseInt(in.next());
        int m = Integer.parseInt(in.next());
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
        
        ArrayList<double[]> centers = lloydAlgorithm(data, k);
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

    public static ArrayList<double[]> lloydAlgorithm(ArrayList<double[]> data, int k) 
    {
        ArrayList<ArrayList<double[]>> clusters = new ArrayList<ArrayList<double[]>>();
        ArrayList<double[]> centers = new ArrayList<double[]>();

        for(int i = 0; i < k; i++)
        {
            centers.add(data.get(i));
            clusters.add(new ArrayList<double[]>());
        }
        
        ArrayList<double[]> oldCenters = new ArrayList<double[]>();

        while(!converged(centers, oldCenters))
        {
            for(ArrayList<double[]> group: clusters)
            {
                group.clear();
            }            

            for(double[] point: data)
            {
                double minDist = Double.POSITIVE_INFINITY;
                int cGroup = -1;
                for(int i = 0; i < k; i++)
                {
                    double thisDist = 0;
                    for(int x = 0; x < centers.get(i).length; x++)
                    {
                        thisDist += Math.pow(point[x] - centers.get(i)[x], 2);
                    }
                    if(thisDist < minDist)
                    {
                        minDist = thisDist;
                        cGroup = i;
                    }
                }
                clusters.get(cGroup).add(point);
            }
            
            oldCenters.clear();
            oldCenters.addAll(centers);
            centers.clear();

            for(ArrayList<double[]> group: clusters)
            {
                double[] mid = new double[group.get(0).length];
                for(double[] point: group)
                {
                    for(int i = 0; i < mid.length; i++)
                    {
                        mid[i] += point[i];
                    }
                }
                for(int j = 0; j < mid.length; j++)
                {
                    mid[j] = mid[j]/group.size();
                }
                centers.add(mid);
            }
        }

        return centers;
    }

    public static boolean converged(ArrayList<double[]> one, ArrayList<double[]> two)
    {
        if(one.size() != two.size())
        {
            return false;
        }
        for(int i = 0; i < one.size(); i++)
        {
            if(!Arrays.equals(one.get(i), two.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}