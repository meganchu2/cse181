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
public class HierarchicalCluster 
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        int n = Integer.parseInt(in.nextLine());
        
        ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();      
        for(int index = 0; index < n; index++)
        {
            String c = in.nextLine();
            String[] v = c.split(" ");
            ArrayList<Double> row = new ArrayList<Double>();
            for(int i = 0; i < n; i++)
            {
                row.add(Double.parseDouble(v[i]));
            }
            matrix.add(row);
        }
        
        ArrayList<ArrayList<Integer>> newClusters = hierarchicalClustering(matrix, n);
        for(int j = 0; j < newClusters.size() - 1; j++)
        {
            for(int x = 0; x < newClusters.get(j).size() - 1; x++)
            {
                System.out.print(newClusters.get(j).get(x) + " ");
            }
            System.out.println(newClusters.get(j).get(newClusters.get(j).size() - 1));
        }
        for(int x = 0; x < newClusters.get(newClusters.size() - 1).size() - 1; x++)
        {
            System.out.print(newClusters.get(newClusters.size() - 1).get(x) + " ");
        }
        System.out.print(newClusters.get(newClusters.size() - 1).get(newClusters.get(newClusters.size() - 1).size() - 1));
    }

    public static ArrayList<ArrayList<Integer>> hierarchicalClustering(ArrayList<ArrayList<Double>> matrix, int n) 
    {
        ArrayList<ArrayList<Integer>> newClusters = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Double>> dist = new ArrayList<ArrayList<Double>>();

        for(ArrayList<Double> d: matrix)
        {
            ArrayList<Double> temp = new ArrayList<Double>();
            for(double val: d)
            {
                temp.add(val);
            }
            dist.add(temp);
        }

        for(int o = 0; o < n; o++)
        {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(o + 1);
            clusters.add(temp);
        }
        
        while(clusters.size() > 1)
        {
            double minDist = Double.POSITIVE_INFINITY;
            int cluster1 = -1;
            int cluster2 = -1;

            for(int i = 0; i < dist.size(); i++)
            {
                for(int j = i + 1; j < dist.get(i).size(); j++)
                {
                    if(dist.get(i).get(j) < minDist)
                    {
                        minDist = dist.get(i).get(j);
                        cluster1 = i;
                        cluster2 = j;
                    }
                }
            }
            ArrayList<Integer> cNew = new ArrayList<Integer>();

            cNew.addAll(clusters.get(cluster1));
            cNew.addAll(clusters.remove(cluster2));
            clusters.remove(cluster1);

            dist.remove(cluster2);
            dist.remove(cluster1);
            for(ArrayList<Double> m: dist)
            {
                m.remove(cluster2);
                m.remove(cluster1);
            }
            
            ArrayList<Double> dAvg = new ArrayList<Double>();

            for(int g = 0; g < dist.size(); g++)
            {
                    double sum = 0;
                    for(int a = 0; a < cNew.size(); a++)
                    {
                        int i = cNew.get(a) - 1;
                        for(int b = 0; b < clusters.get(g).size(); b++)
                        {
                            int j = clusters.get(g).get(b) - 1;
                            sum += matrix.get(i).get(j);
                        }
                    }
                    sum /= (cNew.size()*clusters.get(g).size());
                    dAvg.add(sum);
            }

            for(int x = 0; x < dist.size(); x++)
            {
                dist.get(x).add(dAvg.get(x));
            }

            dAvg.add(0.0);
            dist.add(dAvg);

            clusters.add(cNew);
            newClusters.add(cNew);
        }

        return newClusters;
    } 
}