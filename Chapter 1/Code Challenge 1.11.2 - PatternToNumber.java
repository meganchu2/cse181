import java.lang.Math;
public class MyClass {
    public static void main(String args[]) 
    {
        String pattern = "CCGCGCTCTCTACCCAACTA";
        System.out.print(String.format ("%.0f", patternToNumber(pattern)));
    }

    public static double patternToNumber(String pattern)
    {
        double num = 0;
        double shift = Math.pow(4, pattern.length())/4;
        for(int i = 0; i < pattern.length(); i++)
        {
            char c = pattern.charAt(i);
            if(c == 'A')
            {
                //do nothing
            }
            else if(c == 'C')
            {
                num += shift;
            }
            else if(c =='G')
            {
                num += 2*shift;
            }
            else
            {
                num += 3*shift;
            }
            shift = shift/4;
        }
        return num;
    }
}