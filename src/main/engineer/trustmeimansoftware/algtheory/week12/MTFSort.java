package engineer.trustmeimansoftware.algtheory.week12;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MTFSort {
    static int sort(List<Integer> problem) {
        int indexOutOfOrder = indexFirstOutOfOrder(problem);
        if(indexOutOfOrder < 0) return 0;
        int toFront = problem.get(indexOutOfOrder);
        problem.remove((int) indexOutOfOrder);
        problem.add(0, toFront);
        return 1 + sort(problem);
    }

    static int indexFirstOutOfOrder(List<Integer> problem) {
        if(problem.size() <= 1) return -1;

        int last = problem.get(0);
        for(int i = 1; i < problem.size(); i++) {
            if(!(last <=  problem.get(i))) return i;
            last = problem.get(i);
        }
        return -1;
    }

    static void expectedMovesForLength(int len) {
        // two things to calculate: number of distinct permutations, number of all moves for all distinct permutations

        // number of distinct permutations = len!

        // when len is 0 or 1, the problem is in order
        // for every problem length, there is exactly one with 0 moves (the already sorted one)
        // i-th element is only touched when all 0 .. i-1 elements are in ascending order
            // so it is sth. like f(n) = f(n-1) * n + something
        // since all up to the i-th element is solved, and we now have to solve the i-th, it is sth. like (n-1)! possible ways
        // how many moves are possible when looking at the i-th step?

        BigDecimal count = BigDecimal.ZERO;
        BigDecimal factorial = BigDecimal.ONE;

        for(int i = 2; i < len; i++) {
            BigDecimal left = count.multiply(new BigDecimal(i+""));
            BigDecimal right = new BigDecimal(2+"").pow(i-1).subtract(BigDecimal.ONE).multiply(factorial);
            count = left.add(right);
            factorial = factorial.multiply(new BigDecimal(i+""));
            BigDecimal expected = count.divide(factorial, 3, RoundingMode.HALF_DOWN);
            System.out.printf("%d => %s\n", i, expected.toString());
        }
    }
}


// NOTES
// 1 2 3 4 => 0
// 1 2 4 3 => 4 1 2 3
// 1 4 2 3 => 1 2 4 3
// 1 2 4 3 => 3 1 2 4
// 3 1 2 4 => 1 3 2 4
// 1 3 2 4 => 2 1 3 4
// 2 1 3 4 => 1 2 3 4

// 1 2 3
// 1 3 2 => 2 1 3 => 1 2 3
// 2 1 3 => 1 2 3
// 2 3 1 => 1 2 3
// 3 1 2 => 1 3 2 => 2 1 3 => 1 2 3
// 3 2 1 => 1 3 2 => 2 1 3 => 1 2 3

