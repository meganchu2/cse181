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
public class TrieMatch
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        ArrayList<String> patterns = new ArrayList<String>();
        while(in.hasNextLine())
        {
            patterns.add(in.nextLine());
        }
        
        ArrayList<Node> trie = trieConstruction(patterns);
        ArrayList<Integer> p = trieMatching(text, trie);
        for(int i = 0; i < p.size() - 1; i++)
        {
            System.out.print(p.get(i) + " ");
        }
        System.out.print(p.get(p.size() - 1));
    }

    public static ArrayList<Integer> trieMatching(String text, ArrayList<Node> trie)
    {
        int i = 0;
        ArrayList<Integer> p = new ArrayList<Integer>();
        while(text.length() > 0)
        {
            if(prefixTrieMatching(text, trie))
            {
                p.add(i);
            }
            i++;
            text = text.substring(1, text.length());
        }
        return p;
    }

    public static boolean prefixTrieMatching(String text, ArrayList<Node> trie)
    {
        Node v = trie.get(0);
        int i = 0;
        while(i < text.length())
        {
            char c = text.charAt(i);
            if(v.next.isEmpty())
            {
                return true;
            }
            else if(v.edges.contains(c))
            {
                int nextInd = v.edges.indexOf(c);
                v = v.next.get(nextInd);
            }
            else
            {
                return false;
            }
            i++;
        }
        return false;
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