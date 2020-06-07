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
public class ShortNonShare
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        text += "$";
        String text2 = in.nextLine();
        text2 += "$";
        ArrayList<String> suffix = new ArrayList<String>();
        ArrayList<String> suffix2 = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++)
        {
            suffix.add(text.substring(i, text.length()));
        }
        for(int i = 0; i < text2.length(); i++)
        {
            suffix2.add(text2.substring(i, text2.length()));
        }

        ArrayList<Node> trie = trieConstruction(suffix);
        ArrayList<Node> trie2 = trieConstruction(suffix2);

        ArrayList<Node> tree = suffixTreeConstruction(trie);
        ArrayList<Node> tree2 = suffixTreeConstruction(trie2);
        
        System.out.print(shortestNonSharedSubstring(text, text2, tree, tree2));
    }

    public static String shortestNonSharedSubstring(String text, String text2, ArrayList<Node> tree, ArrayList<Node> tree2)
    {
        ArrayList<String> sub1 = new ArrayList<String>();
        ArrayList<String> sub2 = new ArrayList<String>();
        int min = text.length() + 1;
        String shortest = "";
        for(Node n: tree)
        {
            if(n.next.size() == 0)   
            {
                String s = "";
                while(n.val != -1)
                {
                    s = text.substring(n.val, n.end) + s;
                    n = n.prev;
                }
                if(s.length() > 1)
                {
                    sub1.add(s.substring(0, s.length() - 1));
                }
            }
        }

        for(Node n2: tree2)
        {     
            if(n2.next.size() == 0)
            {       
                String s = "";
                while(n2.val != -1)
                {
                    s = text2.substring(n2.val, n2.end) + s;
                    n2 = n2.prev;
                }
                if(s.length() > 1)
                {
                    sub2.add(s.substring(0, s.length() - 1));
                }
            }            
        }
        
        for(String s1: sub1)
        {
            for(int h = 1; h <= s1.length(); h++)
            {
                String a = s1.substring(0, h);
                boolean inS2 = false;
                for(String s2: sub2)
                {
                    if(s2.length() >= a.length())
                    {
                        if(a.compareTo(s2.substring(0, a.length())) == 0)
                        {
                            inS2 = true;
                            break;
                        }
                    }
                }
                if(!inS2 && (a.length() < min))
                {
                    min = a.length();
                    shortest = a;
                    break;
                }
            }
        }

        return shortest;
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
        public char color = 'b';
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