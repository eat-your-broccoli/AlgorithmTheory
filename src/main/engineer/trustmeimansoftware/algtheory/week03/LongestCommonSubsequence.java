package engineer.trustmeimansoftware.algtheory.week03;

import engineer.trustmeimansoftware.algtheory.util.MatrixToString;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class LongestCommonSubsequence {
    public int[][] subseq;

    String a;
    String b;

    int lenA;
    int lenB;

    public String[] longestSubseq;
    public int longestSubseqLength = 0;

    public LongestCommonSubsequence(String a, String b) {
        this.a = a;
        this.lenA = a.length();
        this.b = b;
        this.lenB = b.length();
        this.subseq = new int[lenA + 1][lenB + 1];
    }

    public String[] get() {
        for(int i = 0; i <= lenA; i++) {
            subseq[i][0] = 0;
        }
        for(int j = 0; j <= lenB; j++) {
            subseq[0][j] = 0;
        }

        for(int i = 1; i <= lenA; i++) {
            for(int j = 1; j <= lenB; j++) {
                lcs(i,j);
            }
        }
        longestSubseq = traceback(lenA, lenB);
        if(longestSubseq.length > 0) longestSubseqLength = longestSubseq[0].length();
        return longestSubseq;
    }

    private int lcs(int i, int j) {
        // if one of the strings is empty, return
        if(i == 0 || j == 0) return 0;

        // if chars match, it ads to the existing sequence
        if(a.charAt(i - 1) == b.charAt(j - 1)) {
            subseq[i][j] = lcs(i-1, j-1) + 1;
            return subseq[i][j];
        }
        // if not, it's the longest sequence of the neighbours
        subseq[i][j] = Math.max(lcs(i-1, j), lcs(i, j-1));
        return subseq[i][j];
    }

    /**
     * build the string by going back from point m,n
     * every time the value increases along the path, we added a char
     */
    private String[] traceback(int i, int j) {
        // we reached the end
        if(i == 0 || j == 0) return new String[]{""};

        int val = subseq[i][j];
        int from = subseq[i-1][j-1];
        // the sequence increased, so we added the char
        if(val > from) {
            String sChar = String.valueOf(a.charAt(i-1));
            String[] s = traceback(i-1,j-1);
            s = Arrays.stream(s).map(str -> str = str.concat(sChar)).toArray(String[]::new);
            return s;
        }
        // sequence was not increased. so we continue with the longest neighbour sequences
        int left = subseq[i][j-1];
        int above = subseq[i-1][j-1];
        if(left == above) {
            String[] resultAbove = traceback(i, j-1);
            String[] resultLeft = traceback(i-1, j);
            // merge results
            return Stream.concat(Arrays.stream(resultAbove), Arrays.stream(resultLeft))
                    // remove duplicates
                    .distinct()
                    // and collect result stream to a new Array
                    .toArray(String[]::new);
        }
        if(left > above) return traceback(i, j-1);
        else return traceback(i - 1, j);
    }


    public static void main(String[] args) {
        while(true) {
            System.out.println("Longest common subsequence");
            Scanner sc = new Scanner(System.in);
            System.out.println("String a:");
            String a = sc.nextLine();
            System.out.println("String b:");
            String b = sc.nextLine();
            LongestCommonSubsequence lcs = new LongestCommonSubsequence(a, b);
            lcs.get();
            System.out.println("Length of longest Subsequences: "+lcs.longestSubseqLength);
            System.out.println("Number of longest Subsequences: "+lcs.longestSubseq.length);
            System.out.println("Longest Subsequences:");
            Arrays.stream(lcs.longestSubseq).forEach(System.out::println);
            System.out.println();
        }
    }
}
