package engineer.trustmeimansoftware.algtheory.week03;

import engineer.trustmeimansoftware.algtheory.util.MatrixToString;

import java.util.Arrays;
import java.util.Scanner;

public class InfixSimilarity {

    public int[][] sim;

    String a;
    String b;

    int lenA;
    int lenB;

    int largestInfixPos[] = new int[]{0,0};
    int largestInfixSum = -1;

    public InfixSimilarity(String a, String b) {
        this.a = a;
        this.lenA = a.length();
        this.b = b;
        this.lenB = b.length();
        this.sim = new int[lenA + 1][lenB + 1];
    }

    public String[] getMostSimilarInfixes() {
        if(this.largestInfixSum < 0) this.similarity();
        StringBuilder ba = new StringBuilder();
        StringBuilder bb = new StringBuilder();
        int i = largestInfixPos[0];
        int j = largestInfixPos[1];
        for(; i > 0 && j > 0; i--, j-- ) {
            // if we come to a point where similarity is negative, we have end of infix reached
            int sim = this.sim[i][j];
            if(sim < 0) i = 0;
            else {
                ba.append(a.charAt(i - 1));
                bb.append(b.charAt(j - 1));
            }
        }
        String infixA = ba.reverse().toString();
        String infixB = bb.reverse().toString();

        return new String[]{infixA, infixB};
    }

    public int similarity() {
        for(int i = 0; i <= lenA; i++) {
            similarity(i, 0);
        }
        for(int j = 0; j <= lenB; j++) {
            similarity(0, j);
        }
        for(int i = 1; i <= lenA; i++) {
            for(int j = 1; j <= lenB; j++) {
                int charSim = similarity(i, j);
                int lastSimSeq = sim[i-1][j-1];
                if(lastSimSeq <= 0) sim[i][j] = charSim;
                else sim[i][j] = charSim + lastSimSeq;
                if(sim[i][j] > largestInfixSum) {
                    largestInfixSum = sim[i][j];
                    largestInfixPos[0] = i;
                    largestInfixPos[1] = j;
                }
            }
        }
        return largestInfixSum;
    }

    public int similarity(int i, int j, int length) {
        int sum = 0;
        for(int index = 0; index < length; index++) {
            sum += similarity(i + index, j+index);
        }
        return sum;
    }

    public int similarity(int i, int j) {
        // if one of chars is out of bounds, it's empty
        if(i <= 0 || i > lenA) return -2;
        if(j <= 0 || j > lenB) return -2;

        char ai = a.charAt(i - 1);
        char bj = b.charAt(j - 1);

        if(ai == '-') return -2;
        if(bj == '-') return -2;

        if(ai == bj) return 1;
        return -1;
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("Most similar infixes of two Strings");
            Scanner sc = new Scanner(System.in);
            System.out.println("String a:");
            String a = sc.nextLine();
            System.out.println("String b:");
            String b = sc.nextLine();
            InfixSimilarity infsim = new InfixSimilarity(a, b);
            System.out.println("Max infix similarity: "+infsim.similarity());
            System.out.println("Most similar infixes: ");
            System.out.println(Arrays.toString(infsim.getMostSimilarInfixes()));
            System.out.println("Matrix: ");
            System.out.println(MatrixToString.print(infsim.sim));
            System.out.println();
        }
    }
}
