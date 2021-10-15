package engineer.trustmeimansoftware.algtheory.week03;

import engineer.trustmeimansoftware.algtheory.util.MatrixToString;
import engineer.trustmeimansoftware.algtheory.week01.Matrix;

import java.util.Arrays;
import java.util.Scanner;

public class LevenshteinEditDistance {
    public int[][] edits;

    String a;
    String b;

    int lenA;
    int lenB;

    public LevenshteinEditDistance(String a, String b) {
        this.a = a;
        this.lenA = a.length();
        this.b = b;
        this.lenB = b.length();

        // init array with -1. -1 indicated that sub problem is not solved yet
        this.edits = new int[lenA + 1][lenB + 1];
        for(int[] row: edits) {
            Arrays.fill(row, -1);
        }
    }

    public int getMinEditDistance() {
        // init table
        this.edits[0][0] = 0;
        for(int i = 0; i <= lenA; i++) {
            edits[i][0] = i;
        }
        for(int j = 0; j <= lenB; j++) {
            edits[0][j] = j;
        }

        // kick off recursive dist calculation
        for(int i = 1; i <= lenA; i++) {
            for(int j = 1; j <= lenB; j++) {
                calcDist(i, j);
            }
        }
        return edits[lenA][lenB];
    }

    public String getEditSequence() {
        // populate edits matrix if empty
        if(edits[lenA][lenB] == -1) getMinEditDistance();
        return editOperation(new StringBuilder(), lenA, lenB).toString();
    }

    public StringBuilder editOperation(StringBuilder builder, int i, int j) {
        if(i == j && i == 0) return builder;

        if(i >= 0 && edits[i][j] == edits[i -1][j] +1){
            builder.append("delete a["+i+"] == ").append(this.a.charAt(i - 1)).append("\n");
            return editOperation(builder, i - 1, j);
        }
        if(j >= 0 && edits[i][j] == edits[i][j - 1] +1) {
            builder.append("insert b["+j+"] == ").append(this.b.charAt(j - 1)).append("\n");
            return editOperation(builder, i, j -1);
        } else if(edits[i][j] != edits[i - 1][j-1]) {
            builder.append("replace a[")
                    .append(i)
                    .append("] == ")
                    .append(a.charAt(i - 1))
                    .append(" by b[")
                    .append(j)
                    .append("] == ")
                    .append(b.charAt(j - 1))
                    .append("\n");
        }
        return editOperation(builder, i - 1, j - 1);
    }

    public int calcDist(int i, int j) {
        if(a.charAt(i - 1) == b.charAt(j - 1)) {
            edits[i][j] = edits[i - 1][j-1];
        } else {
            edits[i][j] = min(
                    edits[i-1][j],
                    edits[i][j-1],
                    edits[i-1][j-1]
            ) + 1;
        }
        return edits[i][j];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    public static void main(String[] args) {
        while(true) {
            System.out.println("Get edit distance");
            Scanner sc = new Scanner(System.in);
            System.out.println("String a:");
            String a = sc.nextLine();
            System.out.println("String b:");
            String b = sc.nextLine();
            LevenshteinEditDistance led = new LevenshteinEditDistance(a, b);
            System.out.println("Minimum Edits needed: "+led.getMinEditDistance());
            System.out.println("Edit sequence: ");
            System.out.println(led.getEditSequence());
            System.out.println("Matrix: ");
            System.out.println(MatrixToString.print(led.edits));
            System.out.println();
        }
    }
}
