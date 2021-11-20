package engineer.trustmeimansoftware.algtheory.week08;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Subsets {
    public static final int SUBSET_SIZE = 5;

    public static int a(int n) {
        if(n <= 0) throw new IllegalArgumentException("n cannot be below 1");
        int upperLimit = SUBSET_SIZE * n;
        ArrayList<int[]> subsets = generateSubsets(n, upperLimit);
        DLX dlx = new DLX(upperLimit, subsets);
        dlx.search(0);
        return dlx.cnt;
    }
    public static ArrayList<int[]> generateSubsets(int n, int upperLimit) {
        ArrayList<int[]> subsets = new ArrayList<>();
        // maxStepSize is set so that SUBSET_SIZE elements can fit in
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i + 4 * k <= upperLimit; i++) {
                int[] nextProblem = new int[SUBSET_SIZE];
                for(int j = 0; j <= 4; j++) nextProblem[j] = i + j*k;
                subsets.add(nextProblem);
            }
        }
        return subsets;
    }

    public static void subsetsToDLX(int from, int to, ArrayList<int[]> subsets) {

    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("enter n: ");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            a(n);
            System.out.println("-----------\n\n");
        }
    }
}
