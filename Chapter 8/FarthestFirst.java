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
public class FarthestFirst 
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
        
        ArrayList<double[]> centers = farthestFirstTraversal(data, k);
        for(int j = 0; j < centers.size() - 1; j++)
        {
            for(int x = 0; x < centers.get(j).length - 1; x++)
            {
                System.out.print(centers.get(j)[x] + " ");
            }
            System.out.println(centers.get(j)[centers.get(j).length - 1]);
        }
        for(int x = 0; x < centers.get(centers.size() - 1).length - 1; x++)
        {
            System.out.print(centers.get(centers.size() - 1)[x] + " ");
        }
        System.out.print(centers.get(centers.size() - 1)[centers.get(centers.size() - 1).length - 1]);
    }

    public static ArrayList<double[]> farthestFirstTraversal(ArrayList<double[]> data, int k) 
    {
        ArrayList<ArrayList<double[]>> clusters = new ArrayList<ArrayList<double[]>>();
        clusters.add(data);
        ArrayList<double[]> centers = new ArrayList<double[]>();
        centers.add(data.get(0));

        for(int i = 1; i < k; i++)
        {
            double maxDist = -1;
            double[] farthest = null;

            for(int j = 0; j < clusters.size(); j++)
            {
                ArrayList<double[]> thisCluster = clusters.get(j);
                double[] thisCenter = centers.get(j);
                for(double[] point: thisCluster)
                {
                    double thisDist = 0;
                    for(int x = 0; x < thisCenter.length; x++)
                    {
                        thisDist += Math.pow(point[x] - thisCenter[x], 2);
                    }
                    thisDist = Math.sqrt(thisDist);
                    if(thisDist > maxDist)
                    {
                        maxDist = thisDist;
                        farthest = point;
                    }
                }
            }

            centers.add(farthest);
            ArrayList<double[]> newCluster = new ArrayList<double[]>();
            newCluster.add(farthest);

            for(int j = 0; j < clusters.size(); j++)
            {
                ArrayList<double[]> thisCluster = clusters.get(j);
                double[] thisCenter = centers.get(j);
                thisCluster.remove(farthest);
                for(int y = thisCluster.size() - 1; y >= 0; y--)
                {
                    double[] point = thisCluster.get(y);
                    double thisDist = 0;
                    double newDist = 0;
                    for(int x = 0; x < thisCenter.length; x++)
                    {
                        thisDist += Math.pow(point[x] - thisCenter[x], 2);
                        newDist += Math.pow(point[x] - farthest[x], 2);
                    }
                    thisDist = Math.sqrt(thisDist);
                    newDist = Math.sqrt(newDist);
                    if(newDist < thisDist)
                    {
                        newCluster.add(thisCluster.remove(y));
                    }
                }
            }

            clusters.add(newCluster);
        }
        return centers;
    }
}