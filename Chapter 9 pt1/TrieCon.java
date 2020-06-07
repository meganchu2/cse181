/**
 * Megan Chu 3/4/18
 * please put the input txt file as the first argument when running the
 * code in "fileName.txt" format
 */

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class TrieCon
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        ArrayList<String> patterns = new ArrayList<String>();
        while(in.hasNextLine())
        {
            patterns.add(in.nextLine());
        }
        
        ArrayList<Node> trie = trieConstruction(patterns);
        for(int i = 1; i < trie.size() - 1; i++)
        {
            Node n = trie.get(i);
            int prev = trie.indexOf(n.prev);
            System.out.println(prev + "->" + i + ":" + n.val);
        }
        Node n = trie.get(trie.size() - 1);
        int prev = trie.indexOf(n.prev);
        System.out.print(prev + "->" + (trie.size() - 1) + ":" + n.val);       
    }

    public static ArrayList<Node> trieConstruction(ArrayList<String> patterns)
    {
        ArrayList<Node> trie = new ArrayList<Node>();
        trie.add(new Node(' ', null));
        for(String p: patterns)
        {
            Node curr = trie.get(0);
            for(int i = 0; i < p.length(); i++)
            {
                if(curr.edges.contains(p.charAt(i)))
                {
                    int k = curr.edges.indexOf(p.charAt(i));
                    curr = curr.next.get(k);
                }
                else
                {
                    Node n = new Node(p.charAt(i), curr);
                    trie.add(n);
                    curr.addNext(n);
                    curr = n;
                }
            }
        }
        return trie;
    }

    public static class Node
    {
        public char val = 0;
        public ArrayList<Character> edges = new ArrayList<Character>();
        public ArrayList<Node> next = new ArrayList<Node>();
        public Node prev = null;

        public Node(char v, Node p)
        {
            val = v;
            prev = p;
        }
        private void addNext(Node n)
        {
            next.add(n);
            edges.add(n.val);
        }
    }
    
}