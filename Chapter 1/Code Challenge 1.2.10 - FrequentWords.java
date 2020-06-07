import java.util.ArrayList;
public class MyClass 
{
    public static void main(String args[]) 
    {
        String text = "GATAATCAGATAATCACCCCGTGCCTTATAAGTCCGCGTTCCGCGTGATAATCACTTATAAGCTTATAAGTGAGTGATTCTTATAAGTGAGTGATTTGAGTGATTCCCCGTGCCTTATAAGTGAGTGATTTGAGTGATTCCCCGTGCTCCGCGTTCCGCGTCCCCGTGCCCCCGTGCTGAGTGATTCTTATAAGTCCGCGTTGAGTGATTGATAATCAGATAATCAGATAATCACTTATAAGGATAATCAGATAATCACCCCGTGCCCCCGTGCTGAGTGATTCTTATAAGTCCGCGTCTTATAAGCCCCGTGCGATAATCACCCCGTGCTGAGTGATTTCCGCGTCTTATAAGCTTATAAGTGAGTGATTTGAGTGATTTGAGTGATTCCCCGTGCGATAATCACCCCGTGCGATAATCATGAGTGATTTCCGCGTCCCCGTGCCTTATAAGTGAGTGATTGATAATCATCCGCGTCTTATAAGTGAGTGATTCTTATAAGCCCCGTGCGATAATCACTTATAAGCCCCGTGCGATAATCATGAGTGATTCTTATAAGGATAATCACCCCGTGCCCCCGTGCTCCGCGTTGAGTGATTCTTATAAGTCCGCGTTCCGCGTTCCGCGTCTTATAAGGATAATCACCCCGTGCTCCGCGTGATAATCATCCGCGTGATAATCACTTATAAGGATAATCACTTATAAGTGAGTGATTCTTATAAGGATAATCACCCCGTGCGATAATCACTTATAAGCCCCGTGCGATAATCAGATAATCACTTATAAGTGAGTGATTCCCCGTGCTCCGCGTCCCCGTGCCCCCGTGCCTTATAAGGATAATCACTTATAAGTGAGTGATT";
        int k = 13;
        ArrayList<String> frequentPatterns = frequentWords(text, k);
        for(int i = 0; i < frequentPatterns.size() - 1; i++)
        {
            System.out.print(frequentPatterns.get(i) + " ");
        }
        System.out.print(frequentPatterns.get(frequentPatterns.size() - 1));            
    }

    public static ArrayList<String> frequentWords(String text, int k)
    {
        ArrayList<String> frequentPatterns = new ArrayList<String>();
        int[] count = new int[text.length() - k + 1];
        for(int i = 0; i < (text.length() - k); i++)
        {
            String pattern = text.substring(i, i+k);
            count[i] = patternCount(text, pattern);
        }
        int maxCount = count[0];
        for(int i: count)
        {
            if(i > maxCount)
            {
                maxCount = i;
            }
        }
        for(int i = 0; i < (text.length() - k); i++)
        {
            if(count[i] == maxCount && !frequentPatterns.contains(text.substring(i, i+k)))
            {
                frequentPatterns.add(text.substring(i, i+k));
            }
        }
        return frequentPatterns;
    }

    public static int patternCount(String text, String pattern)
    {
        int count = 0;
        for(int i = 0; i < (text.length() - pattern.length() + 1); i++)
        {
            if((text.substring(i, i+pattern.length())).compareTo(pattern) == 0)
            {
                count++;
            }
        }
        return count;
    }
}