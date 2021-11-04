package engineer.trustmeimansoftware.algtheory.week05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RNGTest {

    @Test
    public void test() {
        int n = 100;
        int rounds = 10 * n * n;
        RNG rng = new RNG();
        int[] values = new int[n];
        for(int i = 0; i < rounds; i++) {
            int rnd = rng.rand(n);
            values[rnd]++;
        }
        System.out.println(Arrays.toString(values));
        Arrays.sort(values);
        int min = values[0];
        int max = values[n-1];
        System.out.println("Minimum occurrence: "+min);
        System.out.println("Maximum occurrence: "+max);
    }

    @Test
    public void randMinMax() {
        int min = 5;
        int max = 20;
        int[] values = new int[max-min+1];
        RNG rng = new RNG();

        for(int i = 0; i < 10000; i++) {
            int x = rng.rand(min, max);
            values[x-min]++;
        }
        System.out.println(Arrays.toString(values));
    }
}