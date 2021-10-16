package engineer.trustmeimansoftware.algtheory.week03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSubsequenceTest {

    private static boolean arrayContainsStr(String[] arr, String target) {
        for(String s: arr) {
            if(s.equals(target)) return true;
        }
        return false;
    }

    @Test
    public void get() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("GAC", "AGCAT");
        String[] result = lcs.get();
        System.out.println(Arrays.toString(result));
        assertTrue(arrayContainsStr(result, "GA"));
        assertTrue(arrayContainsStr(result, "GC"));
        assertTrue(arrayContainsStr(result, "AC"));
    }

    @Test
    public void caseAbrakadabraBarbarossa() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("abrakadabra", "barbarossa");
        lcs.get();
        System.out.println("Length of longest Subsequences: "+lcs.longestSubseqLength);
        System.out.println("Number of longest Subsequences: "+lcs.longestSubseq.length);
        System.out.println("Longest Subsequences:");
        Arrays.stream(lcs.longestSubseq).forEach(System.out::println);
    }
}