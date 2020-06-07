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
public class SuffixTree
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();

        ArrayList<String> suffix = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++)
        {
            suffix.add(text.substring(i, text.length()));
        }

        ArrayList<Node> trie = trieConstruction(suffix);

        ArrayList<Node> tree = suffixTreeConstruction(trie);
        for(int i = 1; i < tree.size() - 1; i++)
        {
            System.out.println(text.substring(tree.get(i).val, tree.get(i).end));
        }
        System.out.print(text.substring(tree.get(tree.size() - 1).val, 
                               tree.get(tree.size() - 1).end));
    }

    public static ArrayList<Node> suffixTreeConstruction(ArrayList<Node> trie)
    {
        ArrayList<Node> toMerge = new ArrayList<Node>();
        toMerge.add(trie.get(0));
        while(!toMerge.isEmpty())
        {
            Node curr = toMerge.remove(0);
            if(curr.next.size() > 1)
            {
                toMerge.addAll(curr.next);
            }
            else if(curr.next.size() == 1)
            {
                while(curr.next.size() == 1)
                {
                    Node toRemove = curr.next.get(0);
                    curr.next.clear();
                    curr.next.addAll(toRemove.next);
                    curr.end = toRemove.end;
                    trie.remove(toRemove);
                }
                if(curr.next.size() > 1)
                {
                    toMerge.addAll(curr.next);
                }
            }
        }
        return trie;
    }

    public static ArrayList<Node> trieConstruction(ArrayList<String> patterns)
    {
        ArrayList<Node> trie = new ArrayList<Node>();
        trie.add(new Node(-1, null));
        int start = 0;
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
                    Node n = new Node(start + i, curr);
                    trie.add(n);
                    curr.addNext(n, p.charAt(i));
                    curr = n;
                }
            }
            start++;
        }
        return trie;
    }

    public static class Node
    {
        public int val = 0;
        public int end = 0;
        public ArrayList<Character> edges = new ArrayList<Character>();
        public ArrayList<Node> next = new ArrayList<Node>();
        public Node prev = null;

        public Node(int v, Node p)
        {
            val = v;
            end = v + 1;
            prev = p;
        }
        private void addNext(Node n, char c)
        {
            next.add(n);
            edges.add(c);
        }
    }
    
}