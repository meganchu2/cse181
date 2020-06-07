import java.util.ArrayList;
public class MyClass {
    public static void main(String args[]) 
    {
        String pattern = "GTTGACAGAG";
        String text = "TCAGGCATTTGAAGCGCTGCTGTTACCGCGATAGGGTATTCTTCCGATTACAACAACGATAAGCAACAAGGGGGTCAGGCCTCCAATGACGCCAAACCGGGCCAGGGTAAAAGCTGATCCTTACGTGTCGTTGTGCCCAAACAAGGCCGGTTACCATGTTGTACTTACAATGAGACTTTTATAAGCGCGCTGGTCTGCACCAGACGGTGGGCGAATATTTAAAGTCACCAATTGGTGGAGATGCGAACCCGTGCTCAGAGACTTCTGATAACGCGTCGCATTTCAAACTAGTCTCAAATGCAAGATCGCGTTATGGCTTACGGCAGTCGCGGTTCCTATGAGTGCAATCAGGATCCCGGATACCTCAGGGTGCGTTTAGCGAGGCACGAATGCCCTATGCAATGGACATGGTTCAGGGAACATGTGAGGATGTTCTCGGTTCGGTTGGACGCAAACACAAAGTGGTCCTAGTAGTTACACCGCATCACTGTGGCCAAGTGCAATCCTTGCATATGTTTGCGGGCCTACGTAAGCGCCTAACAGCCCGAGATAGACTCGAGCACTGGGCGAGTCGGGCTAGCTTCAGAACAATTCTACCGAAGGAGGGCTGATCCTTCGTCAATCCACACCCACGGGGAGCCGACTCTAACCTCGCCACCATCTCTGGCCATACATTCCTTCGAAACAGGAGGAGCGAAAAGGATCAACTTCTACGCCGGATCTCTAAGCGCAGCTCGGTGATGCAATGAAAATGTCCCCCATCAAAAGCCGTCATGACTCCTACTCGCCACACTCCTCTTAGGCTCCATTGTGGGCTGTAGTTGAACAGCGGCTATGCGATGGTACGGGCACCACATGCCTATTAATTCACCGGGACCCCAAATGAAATCTGAGTGGCGGTCTAAGGGAGTCGGCTCCAGAAAGCGGGAGGTTTCGTGAAATTAAAGTAACTATTTGTGCCGTCAATCACGTTGCCACTAACAAGTTCCCGGTTGCGATCCGTGTATCAACCCGATCTATCCACTGCAAGTACCTCACCCATGCTTAACGAGATGGCGTTATGTCGCTTGCCTCCCGGCAACCCACTATGTCATAATATGACCCAAGTATAATGATGTGCCCCAATTTTATCCTGGGACGGCGCGTAGTTATTGTAAGTTTTTTACACCCCTGTTATCACCAATATCGGTCGTGCTAACATTGACTTTGGCCTCGCCTATCTGCCCAAACGCGACCCCGTGCAACAGCTCACATCGTAAATTAGCAGGGGTTTCTCATTGTCGGTGGTATCCCGCGTGTAGCCGGCGTTGACGAATTTGTGGGGACCAGTAGGGTTGCAGAAAAGTTAGGTGAAAAGGGATCCAACACGAACCTAGTTGGCCTCGTCTTGTTTAGAGAAGATGGGGTCAAGAAATGACATAGATAGCGATAGGTCTAAGTGAATTGGTATGGTCCATAATCGGAGTCTTACTTCGGTAGGTTACGTTGGAGTATGAGGAGGCCCTCTCACTCCACTACGAGCATCGGAAAGTCGGCCTGCTTAGTTGACCAGTGACTCAGTGTAGGTGCCGCGTGGGCGGGAGCGTCTAAGATTGTGAAAGTCTATCCAACCTATGTCCTCCCCATCCAGACAATTTTACCGTCTGGTGAACGCCCCTCTCTCATTTCAGAGCTGTGTTTTGAGGCCCGAACATCACCAAGGATATCGATCAGGGCTTGAAACAAATGGAACTGAGCAGCCTCGAATGATGTGTCACAGATTTCCATGAAGGTCCATAACGCGGGACGGTGTCCACACGGTCCCAAACTCTGGATCGTAGTACCAAGGCTACTCTCCGAGTTGCGATCTTGGTAGTCGCAAAACCTGCGGCACTTGACGACACATACTGAAAAATTGGTAACTATCCTACAGAGAATCATTTACGGGGTTGGGATTTTACAGTATCTTACGAATAGGATTCACCGAAGACCGTAACGCCCATTAGCTTACCTAACTTGAGAAGGCTATCTAGGCGGAGTCTGCCCAGAATATGCTTATCCATGTGAACCCAGAATCTGCTTGTGAGCATTCCGCCCCGCATGGTCACGTACGCGAAGCCCTACCTAGAATGGTAATCGTATGATTGACAACAAAAGAGCTCGTGGGAGCATTGTGCGTGCGAAAGGTCGACGACCCTACAGTGTATGTAGCCGTGTTATAATAAGCTGGCCTATGGATCGCCTGGTTGCGTAAAATGATAAACGCCACTGTCCACTAGATGTTGAACTGATGTAACGACCATCCTCTAAGTACAGTCTCAGTTCCAGTCAGTCGACTCGCGTGCTCTAATTTGCGTGTACTGCTTAATCACATCTAAGGCGTCGACACTCCCGTGAGTTCTCTAAATGCCAATTATAACAACGGATAACGGAGCTTCAGATAGAGCGGCCGTGAGAACAAAAGCCTGCATCGATCGACCCAGTGATTGCCGCTTATTAGTTCGAACTTAGATAGTAGGGTCCTCCAGCGTGAATGCACAGTCCTAATAAGGTAGGACAGATTACACCTCATCCTGAACGTCACCGCCTGGGGCTGACCACATAATTAATCAGTATATCCGTTGACGACCACAATCGGCTATGCCCCTCCCATGCCACAATACATACACGAATGCCATTTACACTACGGCTGTGCGTCCGCCCCGTGCTACAACGAACTGAACCACTTCGGGTGAAACACACCTCGCCCCAGGAAGGGTTAAGAGCTTGGGGCTACGGACGCTATTAACTTGCCAAACGGAGCCGAAAGGGCCAGGGTTCAGCCAGGTCGGATCGACGGAAAGGAATGAATACATCTTGTAGGTTACAGAGGTGTAAGTCCCAGCGTACAATTCGTTTACTTCTCGTCACTTTTAGAGTCGAAGCTTTCTCTTCTTCGTAATACGTCCCTTGCTGGCTTAGAGAACAGTAAGCGCCCCAGTAACTCAATGACATCACAAATCAACGCCAGACACATAGACGAGTAAACCCCCAACGTCTGCCCCCACTAGCGTCGGGGCAGGGCGGTCGTGCCGAATCAACGACCGGCGCGGCGTTGGGGGTCAAATAGCGACTTTTGGAGTCAGCGCCTTGCACATGTCCCAGGGGTTATGTTTTTCGTCGCTACTCTAGCCAACTGGTATTCTGCTCCCAAGACATAGTTGCATCACCACGCCTTGCCACCATTTCCTAGCTTCTTGGTGTGAAGGACCCCCGAGAGGCACCCCTGATGGGCTGCCTAGCCAACAGGGCTTGTACAACTCCAAGCGCGGCGCTATCGAGCCAATGTGCTTCCCCGCAGTACATTCATGGCGCTAACTAGTGATGCCTAATGCGTATTGTAAACGTTCAATGTTTGTCTCCGAACCTTCGCTCGCCTAAGAGGAGGACAAATCTGAAGGTGGACAAGAGTTTTGCGGAGACGCTACTCGTCCGCGAGGACGACTTTTAGTTGAATCGTCTCCTTTTAGAATTGACGCTGCCGTTGGCGAGTTAAACTGCCAAACAGATTTGCTCTTAAGAGTAGATGTCAAATCAAGAGCGTATGCCCACAATTTCCTCGCATTAGACCGAAGCGCAAGGTTCGAATCCCCGTTACGTATAGTGGTCTGAAAATGGAACCAATAAGTGCCGGCTAGATTGGATTCCCATGGACTGTTTCAGGTTATTGCCAACTGTTCTGTGATATAGTGAATCGAAACTAGGAGAGGCCTCTGCCGCCTCCGCACGAAGGAGTACTACAAAGGGCCCGACCCTTTCCAGCATACCCACGCGCAACTCTAATGCATTTCTTCGAACCCTAATAAGAACGTGTGAGACATCCCGATGATGGGCTCATTATTGAGCCTTCACCCAGCGACCCAGATCGTTAGTCATGCACCGCTTCAAAGGTCGTAACTCACTTAATCGCAAAAGAGGGCCGGCCCGTTCATCCACTTACCCCAGGCGCTAGTGTCTTATCCAGTCGTATTGTCTCACGAATTGAACTCTGCTAGCTCTGCCTGGCGAGGCGGGGATTATTACCTGCTACAGTTAGCTCCTGCACAGGAGAACGGATATATGTCACACACTTTCACACAATTCTGAGGTCATAGGGTGAGTGGAATGGCCAGAAGAACATCTCCGATCATATGGATAACAAGAGCCGCACATTGAGAGTCGCCGATAATTAGGGCAATAATGGAAATAGGCTTACTTACATTTAATGGTGAGAATGTAACACCTTCAGAAGAACGAGTCCAATCAGCCGGCCTCGATCATGTTGGTCGCTGACAATTCGGCAGGGATCCGCGACCCAATATTCAGTTGTGAGTCCAAAATGTGCTGGGGTAGGCTCTCCCTATTGCCTTGGTTAACATTTCAGAATGAAATGGACCGAGACGTACTAGTGCATACAGCGCCCTTGACGGAAAAAGGTTGTTGTCCGCATACTTACCAATAAGTGTCCTGTGCGTTGAACTGGGCGAGTCGGGGTCAGGACGTTCGCTGCCGATGAAAGTGGGTCCGCCTCTACACCAACTGGGTCCCATCGCTCGATCCCGAATTCGACCGTCGAGCATGAATATCAGCGACGTCTCGAGCGGACCGACGGCCCTACCATGTCGAACAAAACCTGAAAACAAGTTGTTGCTGTTCTTTACAGTTGGTGCATGGGGCACTTCCTCAAGGCAGCATCCGCGATACCGCATCAAGGGGAGAAGGGGGAATGAACATGCCAGGCCCAGGGTCATGGCACCCTACCCCGTCTTACTATCGTAAGGCGGTCTTTCGTCTTGGCCTCAGTATAATCTTCATCGTATCGACTCCCTCGGATTTATCAATCGCTGACGGGGAGAATACTACACGTCAACCCAAGATAATTGCTCAACGCTGGGCCAAGTCAACGCCCATTAAGGTAGAAGGGTGTCCGGCTATCGTGAGATGCGGACGGGAGGCATCTCAATCCGGAGTACGTGAGCATATTTCTATATCTAAGGAAAACGTGCAAAGCCATCCGCCGGCACGGTCCACATCGCGTCTCAAGGTCGTTGTACTGTCTAGGGCGACAACGGGCCGCACGGTCCCTCACGATCTATTGTTCAGACGACGGCAAAAGCGAGTTTAGACAAAGTCCGAACTACAATTAGTCCGAGGCGGATGTTGCAATGTTTTGTTATTCATATGACCGCAAGTTGCTAGGTAGTCTCAAGTGTTCGGTTACATCGAAACTTATTATACATCTCGCGACGCCTACACCACCTTTAGGTCTTAAGTTAACCTCAGAGTTCCGCCTCTGAGATTTAAGATACCTCCCTACCGTTCAGTTAAGTGAAATGAGAGGTTTACCATCTCGGTGTTCGTAAGTACTTGGGCCATCCGAAGGTCAGCGAGTATACATACCGTGCCTTCTATGTATTATATTTTCGTCCCTATAGGGCAGATCATAGGACATCTATATTCGAATAACGGGACCTGGATCGGACGCCCTAGGATAAGAGTTTCCCACGTTCGAAACACTAATGGACTGGTTTAAGCGGAGCGATGGGACTCCAAGGCAATCGACATGGTTAGCCCCGCAGGTATTGTCGCTACGTGTCCTCGCGCTGGACGAGTTTTTAGGCCGCGCTGCTTGTCCTCGCACCCTGAAACCCCATAACAATTCCAGGAGCGGAAACGGTCAGTGAGCTTCTTACACCTCGGATGCATTTCTTAATCACGTGCGGGCGCACGCCGCATTAAACCCATGTCTGCTTTGCTTGCGGAAGTCAGGGGAGCGGGGATTCCCCGCCGGGTCTGCATTGGTTAGCACATACTTGTTCCGATATCAATGGGCAGTCAGTCCAGAGAGTGCCACCAGATGAACGAGGCTGGGACGGGGGAGTTGCAGGGAAAGCTGTCCGACTAGCCGTTGTGGCGGTTCCGTTTACCACCTTTGCTGGTGTAACGCGTTATTTGACTATCCTGATAGCGTTGTATCTAGATTTACCCATTCCCCTTCCGTTATGCCAGTATAGCAGCAGAAAGTTTACGGCTCTAACAATTTACACTATTCACCTCCACACTAGAGTTGATGACCAGGCCAGTACATCTTCAGCTAGTGTTCGTGGGTGGCCATTACTGTGATCGCAATAGGGTACTACCTATATACTACTCTGTCGCCACGGTGCGATGTACGGAGGACTACACCCTTTTGAGCCCTTAGTGTGTATGCACCGGCGTCAGAGTCCCACAGGCGAGATTAATCGTATACCCTGGCCCCGAGTCCAAACTGTAAGAGGGTACCAAGTGAATCGATCGTCTCGTTTACGAGGTCCTTGTCTGGAGTACCCAACCATGGAGTTTGATTCTAGTACATATCGTATAGACGGCGAGGTCCCGTTCCCCCAATTTCACTACGGTATATTTCAGCGCACTGAAAAATTTTCGCCCTATCGGTTTTCAACTAAGGCGAATTATCTGCTCAGTACCCCTTGGCTCGTGCGAGCTGCTTCGGAGTACGAGGAACCGGTGTGCTTACCTCTAACTTGACATCGGGGCCAAAAGATGGCGAGGCTGTATCAATGTCAATTCTAAATAACCTAAGTCGGCGCAATGCTGCTGATTGTGTCGCACCAATTGAGTAGCCACGAACTGCAGCGAACCTGCACCCGCTGATATCCATGTCTGAGAAATATAGGAGAACCCATTTGATAGCTATAGGACAAAGGACGAACCGGTACATCTAAGCATGATAAAAGTATAAACGACCAGTTATGGCCTGTTGTGGCTCCTCCAATATTCTATAAAAGGTGTCAGACATCCTTAAGTTCCACTCGTCACGAACGGCTCGTAACGATGAAGGACGATGACATCGGCTGCATCCAGTTCCAGTCTGGTTCGATTTCTAGCTTTTTTGGTTATGAACCGCCTCATACTTATCTGGGCCGAGAGTGTTCCCCGTGTCCGGACTACCAAAAGTTCATATAAAACCTATTGATTTGGTATCTTGTGCAGACTTTTCTCCGCTTCCTATCGGTTGATATGCACAGCTAACTCACTACAATTCAGGCCACATGCTCGTGTCGACTGAATCAACGTGATAACTTTTCATGGACTGAAATCGTTGTTTTGCCAGGCCTGACACTGAGGAACATTCGGTGGTACGGTTCATCCTAACTACTTCTCATATCGCCTGGAGACCCCGCGTTAGAAAAAGTGTCGGCGTGTCCTATATGTTTGCCTTCGTTGGTTGAGGCGGTAGGGACGTATAAGAATACGCGCGTACGACCCGATAAAGCCGGGACTACTGTGATGCGTCTGGTTGATTAACGGGTTTCAACTTATTGTTCTCACTTAGCTAGGTAACAAGTTGCGGTTGGCTGATTTCAGGCCTATGGTGTCTCTACACTCGCAATTAAAGAGTCAATGAAGTTACATAGCGTACCCAGGCCTCGAAAATATAACCGGTAACGCAGGCCCATTCAACCACTTATTCTCCCGCGTTAGAACTTCTTATTTTGGAACAGGGTTATTCACTAACTTTGCGTAACGGTGCCGTTGCCGCCTGTCACCTTCACGGATGACCCCACGGTAATCAATCATGTTACCTCGACAGATCCGTAGCCGATGCCTCTCCTCCAGCATGCTTATCTCATTTCCGATTTACATTATTTACACCAGCGTTCTACTAGTACACAATCCGTTGTTGTACTCGCACTACTTACGGACAAGACGTACGCCCTTGTGTTAGCCAGGGCCCTCTTTTTCAGAGAAAAAGACCTCTATACCTTTGATACTGCCAAGCGGGCAAGGTCGGTTAACACTTTAGGGAAGAACTATGGCGCATTCGCAGATATGTTAACAGTAAGCTGAGTAAAATCGTAGACCATGGGTTCACTACTAACGAGGACCCATTTGGATAGCGAATTAGCTATATCACTTCAGTCAAATCCTGATGGTAGAGCCTAATGTAATCCTAGTGGTACGGTTCAGTACTACTTTGCAAGGGCGTAGGACGTTCCGCTTTTATGAATGCCTTAGAAACTTAGGAATTAGGGCAAATCATTGCACGGCTCCATAACGCCGCCTCCTTTGGGGTACCAGACGCCTAGGAGATGTTTCATGCGTCTGTGAAACTTCTCAAGTTGTCCACCGGTCATAGTTCGTTCACTCTGATCCTGAAAGCGTACCAGCCCCCAAAGCTCCTTCGGTTGCCTCCATCTTTGGCATTGTCGTTTAATGTAGAGGGGCGATGGGGCTGATACAGTAGCGTGGCGTGGACGCGGCGCTAATGGAAGCTCATCCAATTCGGGTGCACCCCTTGATCTGCGCACTCTTGTGACCCATAAATAGGATAAGAGAGTCGCGTTTAGAATATGTGTCTCGCTCAATCGTGTATGTATGGTCGGACGCCTCGTGCAATCTGATTTTCCAACGACGCTGTTAATCCATATAGTTCTTGGATCATCGTCCTCGGGTTCGAAATGTGATACATAGATCGTAGACCCATTATTTTATAATTGACGATTAGGCACAGTAGTGTGAGAATCCGCTACAGCGGCCGCATTTCCTATCGCACTGCGCTCTCCTCGTGACCACACACGTCTGTCAATTCCTATACAAACATTGGTGCTCAGTTAGACCTCCCTTAAACCTGCTTCGTGAACGGTATTGACCCTATTATTGAAAATCACCGTGCGCTGCAGACGTAAGTCTAAAGAATCTAACCCGGGCGAGTCAAAGGTAGAACTTGCAATCTCACGCTATATGATCCGGCCCACCATGGCACCTGATGCCAGAGTGGCGGTATGTGAATGTGACGCGAATACCCACGATACGACAGGATGGTCGAGGGAGCGCGAAACCCACTACAGGGGGCTGGAGGCGTCGTTCTCACAGAGGGACATGTCCTGGGACTACAACAGTTGACTTAGGTAATAGTCGCATGGCCCACGCTCATTTAACCTGTTCCTCTTAAGTGTGTAGCTGACGCTAAAGGCATTATCAATTGTTTTGGGGAGTAGACTGACCCCTGCACCGAGGCAAGCTCCACTCGACGAAGAAACCGGGAGTGGTCTGATCTTTTTACAAACACAAGGGGCGTACCCGCACTGACCCATAAAAGCAGTGGGCATTGTCTCATATGCCTACTTGGCTGAGCCCTTGAGCATATTGGAGGATGTATGAGCGCAACTGGGATATGAGTTCGCCTCACCTTAGCTGGGGGAGTTGTACCCAACGGGCATCAACCTCTACACGATGACAAACTACAGCGACCGTCGACGTCATCCCAGTCTACGCATGCCTCTGACATGAGCAACTATCCTCGATTACTGTGTCGTGTCTTGTTAGAAACTATGCAATTCAACTACTCCCTTAGAGTATTCACTGCCGGACGGCTTAAAGCTGAATCCATTATAGTACGGTGACCCCGTCAGGCATAACACCTCGCTACAGCTAACATTTTCACATTGATCTACTATCAAATAAAACGCAAATCCTGGATTGGGCAATCGAGGCATACGCCGAAGTGTAGCGAAAGTCAGCTCAGATGTCCTTATAATTTTTCGTGGGGTTAGACGAAACAACCAACTCGTCTGGGGTACTAAGACACGCTAAGCCCCTGTATTAGGGCCCATGCTCGGTTCATTAAACGATCAATTACGATTTAGCATCCAGAAATTGTTATCAATCTAATCTGACTACAATTGCTTCAGTTCTACATGTGAATCATTGGAGGGCCCTCTGACGCGACCGTCTTAAAATCATGTCCCCTCTTAGCGTGACCTAATTCCGCACACGTTGTATAGTTCTTGTTTAGCGACGGACCGCTTATAATGAGCAACATGTATTTACCATACCCCTAGGCATCTTGGAATCCGGGTAACGGGTTAGTCTACGGCGCACGCGTACGTTTCAGGGATTACTGCTCGCGAAATCTTACATGAAAACTTCATATTGCCAGATTCCTTAATTACTTAATTGATGTCAGGAGTAGAGTGGCTTCACGCTAAATATGAGCTCATTCGGGCGGTTACCGGTTGTTCCCAACTAAGAACTGTCTTAAGATTGATCTCCGTCGCTCGGGCGCATCCTGAGTGCCGCTGTAGGTCGTGGTGGGTTACGCTTCAAGAGGCGGATGGAAGCAGAGCCTAGTCCTCTACTACGGTAGCTGGCGCGCGTGATGCAATAGCGTCGAATACAATTTAACGTCGTCCGGTTGGAACTAGATTACATGTATAGTATGTACAACTTGACTCTTTGTCGGATCACGTGGGATTAAACTACACGTGCACGCCTGTATGTATAAGAGGGAGATTGCCGATTATTTATTCTATAGGTCACTGATATTCGCATTCCGAGTCTGTTGCTGCGTGCCTAAGCGATGTCATTCTGTGATGGTCGTTCCATCTGTTCCCGTAAGGTGCAAATACAGGCCATATCTGTTGCGAGTCTCTCTAAACGTATTCTGGACTTCCGCAAGACGTGACCGCAAAGTGTAATCATAGACTGAGCAGGACCGAGCAGCGGCTCTCTGGAGTCTTGGTATCCGTGGTTTGGTAGCGGTTGTCGGCGCAGAGGGATACTGCAACGAAGCACGATGCACTTGCCAAGGCTTTACGTTTCGCCTCTCATATCATTGTCGTAAGGTTGAATGGTAGTCTGTTCCCGTATAAGTGCTCCGTCTCGAGGCAAACGGATGCTGTGGGTACCCTGCCGTCGTGAGTCTCTGGACAGTGTACAGGCCCTGGTTTCTCACGGTACTGGCGCGTCGTAGCCTAGATACGATTATCATGACGGATTCGAAAAACGGGCATCCTACCTTACGTGTAATAATACTGAGTTACAGGTGAGCTAACGCTGGTTAACAAAAAATGATGGCAATTTGCGACATAACGCTCCGGCTTGTTCGGAAGATAACGGCGGGTGCACAAACGAGGATCTACAACGCGTACAGCACGATATCACGGGGATTGAAAGTTCAGTCCAGCCTCGGTTCGTTCCACGATGTGTAATTTGTATTTCAGCTCTCTACGGCGTATTGAATCTTGTTTGCAGTGCCTGTCTGCAGTAACAACTAGCAGTATAAAAGTACGCGGGTAGATGCATAGCGCTCCGCACCTTTTCGCAAACTCTTAGATGCGCATACAAACGAGAAATAATATCAGTGGCGGTCACAAAGGCGGTGTTTGGAATTAAGTAGTTTTAAAGTTCGCCTCGATAAGGGGAAAACAAGGACGCATCCACCCCTTACAGTGGGGAGAACCATTAAGGTCTTAGCCCAAGAATCGGCTCAACCAAGGTTCCTAGCTCAGGCCCGTGAATATGACAGGCGGTTCGCAAACGGATTAGTCGAGTGCTGTCACACCGTTGCAGGCAGTGCCCTCACGGTGTGCAATACTTTCTCGACCCCCCCTTGCTTGACTGGGCCAAATGTAGAGGATGGTATCAGCAGGAAAACCCTAAGATGTGCGCCCGCGCGGACCACGATATCCGTGCACAGCGATCAAATAGATCCACAAAGCAATTTACATAAGTAGAGAACGGGATACACCTGTTCCTTTGTTACTCAGGATATAGGTACAAAACTTTCGACGGGAAGCGAAGGGATTCGCACCCGCCGCCCGACACCGCTGGGGTCTGCTCAATAATAGCTCTAGAAGATAGCATACTCGGTCAGGCGTCGCCGGAAATCCTCCGGAAGGTAGAAAGAGCAAAAATTTCCTGGATAAGGAGATGGACTGATAATTGGCCCCTGTCGGGTATTAGGAAAGGCGATCCAACCCCATTAGGGCCTAACGACTGAGTGTTGAGCAAGTTACCCGCAGCGTTGGCTGTGAGGAGGTGAGTAGCTCCGGATTGCAGTGGGAGCGGCATCTTGACACGTTTGATAATTATGTCTGCGGAGTCGCTTAAACCGCTACTGTCGCAGCGTTAGACGTATAACGCCTTCCAAAGAGCTTGGTTGAGCTGAACTCTGGGCGATAAGTACGGGATTATAGAACTTAAATAATGCCACCTAATAAGATTCAACTCCCGCGTGATTCCAGCGACAGAAAACCCTCAGGGATCAACCAGTTACTCTGTTACGGCAGTCCGTCTTAGTGATGAGCTCGAGCACCAAATTATTTAAGAGACCTCCCATTCGATATTCAGTCTAATCGCTGACAGCAACATGCCTGTGGAACCAGTGATCTCAGAGGGACGAGCGAAAGGTCACAACCTGTTGGGGCTACAAACTCGGGTGATTGGGATACTTACATTAGGCGAACACTTGGAATGATGTTTTGACCCTGGGCAAGACCATAGCCAAGGTCCCTCAATAAATTTATGCACGCCTACTATTGCGCGTTTTGCGGCGAGGATGACCCAATTACAAACACCATAAATGAGGCGCGCGTCAACACACAGGCGCGTGCTACCTTTCACAAAGCACCCGATAGGCCTCATATCTGGCCCCGAAATGTGACCTAGGGACACGGACCTAGTCTCTGTACGCACGTAGCCATGTGCGCTCATGGAATGCGATTTAAACTTCCCTAAATTCGCCGGTCATGAACGGCTGTTTGATGGGCCATTCCAGAAGGGATGACTTTTATCATCCCGACGCCCTGGCAGTTTCCAGGCCAATAATCTGGTAGGCATGCTCTCAATATAACAATCTGGAGTTGCGACGCGAGACTGTCATTGACCGGCATTGGAGTAATTTAAGGTACCAAGACATTAAAGCCCCCCCCTCTTCCGTTCCGAGCTGTAGAGGCGCATGTCAGATGCTGTACTTGGCTTCACGACTAGAAAATAAAATACGCAATCGTAAAGATGCGTATGATGAAACGATGATTGATATATCGCACTGGTACGAAATGCGCCCGGTGGTCATCATGGGAGGCAACACCCCGACCTGGCGGTATCTGTTTGGGTTGGGGTTCCTTTTTCTTAGAACGCGCGCCAGAATCAAGTCAATGTCATTAGGGTGAGTGTTTATAGAGACGCTTTTCTCAGGTGCCAGCCTCCTCTATCCCCTTGGAGGTGCCTCTTGTTCACAGTCGTGTGATGCGCCTAGAGAACGAAGACAATAAAGAAGGGGCTATAGCCTGATAAAGGTTCACTAACTCACCAGGTGAGCAGTGGGCGAGGCGGTCCTGCAAGTATCCATTTTTACCTAAAAACCATGAGATACTAGCTAGTGTGAGATTCATTGACAATCTCGTAAGACTGCTGTATACTGAGTAACCTTAATTAACCAGCTGCTTTACCTGTCCGTTTGCATTACTCGTGGTTGGAGCGTACCCCCCAACCTATGAGTGAATACACTGCTGCGAACAAATTCGGATCCTTCATGACCGCCTCTCGGGTAGTGGAACTATCACTCAAGGTGACCGTGTACTCGGAATAGTCGGTTCGCCGCAACTGTTATAACACGAGAACTCGCTCGCCTTCCTACCACTTAGATGTCCGCCCCTGACCGACGTCACAACAGACCTTGAATTGACTGTTGTATGTGTCGACCGCGCCGCGACAATTGTGTCCCGCCCTCTAATTGTGGGTACCTCACGTAGTATGTGGAACGAGCGATAAGTTTTTCGCTCGCGGAGCGGCCCTTGACAGTCTGACATAAAGAAGTCCGCCTCCACACCAACCGATTACTTAATGGTGAAAGCCGACATGCATAAAAGGCACATAGGGGTTACTGTTTAGGGACGGCTAGCAGAAACGCAGGTTGATTTGGCCGTGTACTATATGGCTAGTTAATGGGGTACGTGGTGCAAGTGCTGGGTTGGAGCGAGACCATCATTGTTCCCAGGCGGGGTCTGGATATTCTGTTTCACAGGATTACAGAAGTCATCTAATAACGAGAAGCCAACGACTTCAATAGCCGTGGCTTATATGATCATACGAGTATCGCCTCCAGCCCCACCACGTGTGCCTGATTCGGGGCTCCCCAGGTAGGGCCGTCTATTCTAAAAAGTTTCACCGGACTGTTACGGGGCCACGATCATCTAGGCTTCTGGGCGGATACGTCAACATTCGTTGAAATCACACACCTGCATCTATAACTCTCAATCTCATTCGTTCCGTCGTCCGATACCGCAGGTCCTTTGTCTTTCCTGACTTCTCTGGTAAGCTTTACGCCCCGTCACCCGAAGCTCCGGCTTGCAAAGACGGCCTTACAAATCTACGGTCTGTGGCTCCGAAATCAATACGTCGCTATTGTTTTTAGGTGTTACCTCCGCAATATTGTGGCCCGCCCTCAAGCAGCTCTTTGCGGTATATTCGGCACTGCAGGCCGCAGCCTTTGTGTGGGGATTAGACGACATAACTCCAATGATTTTGTATCGGCAGATTGCTAATGAGCGCGGGCAGTGCTTAAATGCGAAATATCCTGTACAAGCGAGACGAGGTTAGTGCGAAGACGGGCTACACTTGCCGTTCAGATCTTCAGAGACCCCGGAGACAGTAACCAGAGCACGAGAAGTCTAGAGAACAACCGAATTGCGGAGGTTCGCGCTAGGGCTAATACTATGATCGTACAGGGCTACTTCGACAGTAGGCTGTCTTTTCGGCAGCCATGAATATGTGACTACTGGCCGGTCTATTTCGATGACCGATGGTCGAGGCCATCTTAAAGGCTTATGGACGATTCCTATCACTAATTGGGATGATAAACACCTCCTGGCCGTCGCTCGTCGATAGGTGGCAGGAGTGGAACCTGTCGTGTAGCTACAGTGTTCACCTCTTGTACCAGTCACCGTGATCATTATTTGCGCGCTCATTTATCTTGTCCACGCCTTCTACTGTTGAACCCAGACCCCTAGACGGCTACGTTGCAGTTGATCTTCTATTTGACGCCTTCACAAAAGTCAATCCGGTTTCCGGGTCCCTAGAGCATCTGATGTTCTACTAGTGTAGAACAACATGACATCGGTTCCTTTAGCCAACGCTCGGCTTAGGGGAATAATCCTTTACTAAATAAGCCAAAGGTATTGAGCCTCCCTGCTTCAGCCCCAGTAGGCTTACGGGACAGCCTTTAGGGACGAGTGCAATAGTATGGATGTCTTGAGCCAGAGATTTGCCTTCAGAGTTGTCAGTTCGGAGATCATTCGCGCTCACCGATTACACAATAGTCACACTGCGCCCATCATTGCCAAGGGATACAGCATTCGTGTCGGGCTTCAGTACCATCCATCATTTACAGCAAACATCAACGTGGGAGCAGCGCCTCCGTCAGTGTGAGACACCCTATCGGCGTTTTCACTTGGCCAGATATAGCTATTCGTAGATGTCCTACGGCGACTAAGTGGACCCCTGTCCCGGGGGATCGATTACTACACGCTGCCCTTACATTGTAACTACACCGTCATCAGATTACGCGCTCCCTATGGTACCAATCAGATTAACTTTGATGAAATTGCCATCGTGAGATGCACAATAGGCCTCTATACCATGCTCCCACGGCGATGTCGAGTGGTTCCGTGTATTCAGGTTGAACATTAGTCGAGGGTTCCAAGCTCGGTACTAGGTACAATACTCCCTTCGCAATAGAGGTTGATGTTAGCGAATATAATTCGGCGCTAGCGGAGTGGAATTTCCCTACTACATCGCTGATATTGTAGTACATCAACCGAAACGGCGCCATATGTTGATCAGACCGGTTATCAACACCAGAAGTTCCGGCTAGAAGGGGAGCGGCACAGACAGATTGTGGATCGACGAATATTTGATAATTACCCACGTCGACGCGCACCAGGACCAGAACTCCGCAAGCCGTGACCCAGCACGTCTAACATTCGATCGTCCCCGCCGTAAGGAACAGAGACGGTAGAACCTGTAGTAGGCATCTACGCCACGAATCCATGATGTAGCTAGTGCCTCGTTTTCAATAGACGGTTATTCTTAGGAGTGTGTGCACTTGATCCGCCCGGGCCTAGGCATTCTGTTCTAATTGCATTTCGGAAATTACGTATGCGGTAGGATTCCGAAAATAGCTAGCAGACGTTAACGAAGTGTCTAACCTGGCGATGCCGCGGTTCGCAGTAGCACCGCTGGAGCTAGGGTCGCCTGCATGTCTTCAACTGCGTTACGCACGTTCATTGCTACGATTGTATTGCCCTCAGCGTTTTAAGTGGTCTTAGAGTTACGCGTAGAGAAGGAAGCGATTACCTAATTCGTGTGGGGCTACACCACGTTGTGTGCTTCCGGAGCCAGGGGGGGGACTGCTTCTGTCCCGACGATCCAGGGTAGACGGCGGAAACTCACGATTGGAACGTCTAACGTGGTATGCGACTGGGTGGTGATAACGCCTACAGGGAAGACTGATCCATTCATTAAAAGTGACGTGACCATCCTTAGACCGAGGATGAACAAAATCTAAGCTGGTGACAAGAAGGGTTCGTTTATGGAGACACCGATGGCGCTATACGAACACACATGGAAGCCCTGTGTTCTTGTGCCGACTGCTTGGTGTAATATGCAACCTGAGCGCGTGGGCATCCGCAGCATTTGCGGCAGACATTACACTCAGCATATCGCACGAAGACGCCAATTAGGCGTTGATAAAATTCTAATATAGTAAATAGGCGGATATTCAGTCGGAGATTATCACCAGTGAGCTTTTGTAGATTGCAGGTAAATGGAACAGCCATATTAAAGTGCGCTTCAACCAATGTCTAATGGCCTAGATCAAAAGCTAGATAGGCGTTGCATGGCATCCGCGGCGGGGGGTGGCGGACACTGTCCGCCGTAGTATACTGCGTGGACTCACAAGACGCTCAGGCCGGCGCTCAACATCTGCCGGCCACTGATATGTTTCGATATGGATCGCCGATGTGGTTTGAAGAGAAAAAGATGGCCAGTCGCAATCAGAACACTTAGGCGTCTCATTTAACTAGAGCACCTCCACTCGTTAAAGACTTCCCTTAGGCCTATCTTTACGGAGATTCGGGACATGAAAAAAGAGCGTTGTTATATTTGCCCGGTGGTGGATGAGCGGCGCGTGTGTAGCAAGATCTAGTTGGTGCGAACTACGCTGCAAGCATGTTAGATTATGACGGAGTGTCGACGTCGGGTACCTT";
        int d = 6;
        ArrayList matches = approximatePatternMatching(pattern, text, d);
        for(int i = 0; i < matches.size() - 1; i++)
        {
            System.out.print(matches.get(i) + " ");
        }
        System.out.print(matches.get(matches.size() - 1));
    }

    public static ArrayList approximatePatternMatching(String pattern, String text, int d)
    {
        ArrayList<Integer> matches = new ArrayList<Integer>();
        int k = pattern.length();
        for(int i = 0; i <= text.length() - k; i++)
        {
            if(hammingDistance(pattern, text.substring(i, i+k)) <= d)
            {
                matches.add(i);
            }
        }
        return matches;
    }

    public static int hammingDistance(String dna1, String dna2)
    {
        char[] c1 = dna1.toCharArray();
        char[] c2 = dna2.toCharArray();
        int ham = 0;
        for(int i = 0; i < c1.length; i++)
        {
            if(c1[i] != c2[i])
            {
                ham++;
            }
        }
        return ham;
    }
}