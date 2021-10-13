package engineer.trustmeimansoftware.algtheory.week02;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LatticePaths3D {

    static final int X = 1;
    static final int O = -1;

    int[][] moves = {
            {X, X, X},
            {X, X, O},
            {X, O, X},
            {X, O, O},
            {O, X, X},
            {O, X, O},
            {O, O, X},
            {O, O, O},
    };
    int startPoint;

    // we're using euclidean distance without the square root part in the end
    // it's pointless to calc the square root, if we can directly compare the un-rooted value
    // they retain their comparability
    // our smallest distance is either 0 or 1, both not affected by the root
    // if you have distances d | 0 < d < 1, then you should use the square root instead
    HashMap<String, Long> distance = new HashMap<>();
    HashMap<String, BigInteger> paths = new HashMap<>();

    public LatticePaths3D(int n) {
        this.startPoint = n;
    }

    public BigInteger paths(int n) {
        // lets do a top-down-approach
        int[] cords = new int[]{this.startPoint, this.startPoint, this.startPoint};
        return this.pathsToZero(cords, this.euclideanDistance(cords)+1);
    }

    public BigInteger pathsToZero(int[] cords, float fromDistance) {
        sortCords(cords);

        String cordsStr = this.cordsToString(cords);
        // paths from this point to zero is
        float distance = this.euclideanDistance(cords);

        // 0 when the last point was closer
        // we have to calculate distance first and check if move is allowed
        // otherwise a illegal move would get same paths assigned as a allowed path
        if(distance >= fromDistance) return BigInteger.ZERO;

        // if cache hit, use that value
        if(this.paths.containsKey(cordsStr)) return this.paths.get(cordsStr);

        // 1 when we're exactly on 0,0,0
        // base case of recursion
        if(distance == 0) return BigInteger.ONE;

        // the sum of all other steps that are closer to 0
        BigInteger pathSum = BigInteger.ZERO;
        for (int[] move : moves) {
            int[] newCords = addCoordinates(cords, move);
            pathSum = pathSum.add(this.pathsToZero(newCords, distance));
        }

        this.paths.put(cordsStr, pathSum);
        return pathSum;
    }

    public BigInteger paths() {
        return this.paths(this.startPoint);
    }

    // cords have to be passed in sorted order
    public long euclideanDistance(int[] cords) {
        sortCords(cords);
        String s = this.cordsToString(cords);
        if(this.distance.containsKey(s)) return this.distance.get(s);
        long dist = 0L;
        for(int cord: cords) {
            dist += cord * cord;
        }
        this.distance.put(s, dist);
        return dist;
    }

    private String cordsToString(int[] cords) {
        sortCords(cords);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < cords.length; i++) {
            int cord = cords[i];
            builder.append(cord);
            if(i < cords.length -1) builder.append(".");
        }
        return builder.toString();
    }

    private static int[] addCoordinates(int[] a, int[] b) {
        if(a.length != b.length) throw new Error("must be same length");
        int[] newCoords = new int[a.length];
        for(int i = 0; i < newCoords.length; i++) {
            newCoords[i] = a[i] + b[i];
        }
        return newCoords;
    }

    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            System.out.println("Enter n for LatticePaths3D:");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("exit")) running = false;
            else {
                try {
                    int n = Integer.parseInt(input);
                    LatticePaths3D l = new LatticePaths3D(n);
                    BigInteger result = l.paths();
                    System.out.println("For n = "+n+" number of paths is: ");
                    System.out.println(result.toString());
                } catch(Exception err) {
                    System.out.println(err.toString());
                    running = false;
                }
            }
        }
    }

    private static int[] sortCords(int[] cords) {
        Arrays.sort(cords);
        return cords;
    }

    private static BigInteger distinctCords(int[] cords) {
        HashSet<Integer> set = new HashSet<>();
        for (int cord : cords) {
            set.add(cord);
        }
        return new BigInteger(set.size()+"");
    }
}
