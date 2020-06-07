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
public class LongShare
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner in = new Scanner(new FileReader(args[0]));
        
        String text = in.nextLine();
        text += "$";
        String text2 = in.nextLine();
        text2 += "$";
        //text = text + "#" + text2 + "$";
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

       // tree = treeColor(text.indexOf('#'), tree);
        
        System.out.print(longestSharedSubstring(text, text2, tree, tree2));
    }

    public static ArrayList<Node> treeColor(int a, ArrayList<Node> tree)
    {
        System.out.println("Hi");
        ArrayList<Node> toExplore = new ArrayList<Node>();
        ArrayList<Node> explored = new ArrayList<Node>();
        for(Node n: tree)
        {
            if(n.next.size() == 0)
            {
                toExplore.add(n);
                explored.add(n);
            }
        }
        while(!toExplore.isEmpty())
        {
            Node e = toExplore.remove(0);
            if(e.next.size() == 0)
            {
                if(e.val <= a)
                {
                    e.color = 'b';
                    
                }
                else
                {
                    e.color = 'r';
                }
            }
            else
            {
                boolean red = false;
                boolean blue = false;
                for(Node child: e.next)
                {
                    if(child.color == 'r')
                    {
                        red = true;
                    }
                    else if(child.color == 'b')
                    {
                        blue = true;
                    }
                    else if(child.color == 'p')
                    {
                        red = true;
                        blue = true;
                    }
                    if(red && blue)
                    {
                        e.color = 'p';
                        break;
                    }
                }
                if(red && !blue)
                {
                    e.color = 'r';
                }
                else if(!red && blue)
                {
                    e.color = 'b';
                }
                
            }
            if(e.prev != null && !explored.contains(e.prev))
            {
                toExplore.add(e.prev);
                explored.add(e.prev);
            }
        }
        return tree;
    }

    public static String longestSharedSubstring(String text, ArrayList<Node> tree)
    {
        int max = 0;
        String longest = "";
        for(Node n: tree)
        {
            if(n.next.size() == 0)
            {
                String s = "";
                Node curr = n;
                while(curr.color != 'p')
                {
                    curr = curr.prev;
                }
                while(curr.val != -1)
                {
                    s = text.substring(curr.val, curr.end) + s;
                    curr = curr.prev;
                }
                if(s.length() >= max)
                {
                    max = s.length();
                    longest = s;
                }
            }
        }
        return longest;
    }

    public static String longestSharedSubstring(String text, String text2, ArrayList<Node> tree, ArrayList<Node> tree2)
    {
        ArrayList<String> sub1 = new ArrayList<String>();
        ArrayList<String> sub2 = new ArrayList<String>();
        int max = 0;
        String longest = "";
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
            for(String s2: sub2)
            {
                int stop = Math.min(s1.length(), s2.length());
                for(int g = 1; g <= stop; g++)
                {
                    if((s1.substring(0, g)).compareTo(s2.substring(0, g)) == 0)
                    {
                        if(g >= max)
                        {
                            max = g;
                            longest = s1.substring(0, g);
                        }
                    }
                    else
                    {
                        break;
                    }
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