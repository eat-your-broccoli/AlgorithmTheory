package engineer.trustmeimansoftware.algtheory.week05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CharSortTest {
    public static Character[][] getProblem(int problemLength, int charLength) {
        RNG rng = new RNG();
        rng.setSeed(100);
        int n = problemLength;
        Character[][] a = new Character[n][n];
        for(int i = 0; i < n; i++) {
            a[i] = getCharSeq(1, Math.min(n, charLength), rng);
        }
        return a;
    }

    // https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
    public static Character[] getCharSeq(int minLength, int maxLength, RNG rng) {
        Character[] arr = new Character[rng.rand(minLength, maxLength)];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = getChar(rng);
        }
        return arr;
    }

    // https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
    public static Character getChar(RNG rng) {
        return (char)(rng.rand(26) + 'a');
    }


    @Test
    public void sortCharArray() {
        Character[] a = new Character[] {'x','b', 'c', 'd', 'e', 'f'};
        System.out.println(Arrays.deepToString(a));
        new CharSort().qsort(a, 0, a.length-1, Character::compareTo, PivotFinder.threeWayPivot);
        for(Character chars : a) {
            System.out.println(chars);
        }
    }

    @Test
    public void sortCharArrayArrayDet() {
        Character[][] a = getProblem(20, 10);
        CharArrayComparator c = new CharArrayComparator();
        System.out.println(Arrays.deepToString(a));
        new CharSort().qsort(a, 0, a.length-1, c, PivotFinder.threeWayPivot);
        for(Character[] chars : a) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println();
        System.out.println("Comparisons: "+c.counter);
    }

    @Test
    public void sortCharArrayArrayRand() {
        Character[][] a = getProblem(20, 10);
        CharArrayComparator c = new CharArrayComparator();
        System.out.println(Arrays.deepToString(a));
        new CharSort().qsort(a, 0, a.length-1, c, PivotFinder.randomPivot);
        for(Character[] chars : a) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println();
        System.out.println("Comparisons: "+c.counter);
    }

    @Test
    public void sortCharArrayArrayRandNTimes() {
        int runs = 10000;
        int problemSize = 2000;
        int min = Integer.MAX_VALUE;
        int max = 0;
        long sum = 0;
        Character[][] problem = getProblem(problemSize, 4);
        System.out.println("Problem: "+ Arrays.deepToString(problem));
        for(int i = 0; i < runs; i++) {
            Character[][] a = Arrays.copyOf(problem, problem.length);
            CharArrayComparator c = new CharArrayComparator();
            new CharSort().qsort(a, 0, a.length-1, c, PivotFinder.randomPivot);
            int comparisons = c.counter;
            if(comparisons > max) max = comparisons;
            if(comparisons < min) min = comparisons;
            sum += comparisons;
        }

        System.out.println("Sorted problem "+runs+" times");
        System.out.println("Problem size: "+problemSize);
        System.out.println("min: "+min);
        System.out.println("max: "+max);
        System.out.println("On average "+(int) (sum / runs)+" comparisons needed");
    }
}