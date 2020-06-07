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
public class LongRepeat
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        text = text + "$";
        ArrayList<String> suffix = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++)
        {
            suffix.add(text.substring(i, text.length()));
        }

        ArrayList<Node> trie = trieConstruction(suffix);

        ArrayList<Node> tree = suffixTreeConstruction(trie);
        
        System.out.print(longestRepeat(text, tree));
    }

    public static String longestRepeat(String text, ArrayList<Node> tree)
    {
        ArrayList<String> substrings = new ArrayList<String>();
        ArrayList<Node> toExplore = new ArrayList<Node>();
        int max = 0;
        String longest = "";
        for(Node n: tree)
        {
            if(n.next.size() == 0)
            {
                String s = "";
                Node curr = n.prev;
                while(curr.val != -1)
                {
                    s = text.substring(curr.val, curr.end) + s;
                    curr = curr.prev;
                }
                if(s.length() > max)
                {
                    max = s.length();
                    longest = s;
                }
            }
        }
        return longest;
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
                    for(Node n: curr.next)
                    {
                        n.prev = curr;
                    }
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