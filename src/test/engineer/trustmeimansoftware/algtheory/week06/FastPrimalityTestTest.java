package engineer.trustmeimansoftware.algtheory.week06;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FastPrimalityTestTest {

    @Test
    public void primesTo1000() {
        ArrayList<Long> primes = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            if(FastPrimalityTest.isPrime(i, 2)) primes.add((long)i);
        }
        long[] filteredPrimes = primes.stream().mapToLong(i -> i).toArray();

        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l <= 3 || FastPrimalityTest.isPrime(l, 3)).toArray();
        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l <= 5 || FastPrimalityTest.isPrime(l, 5)).toArray();
        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l <= 7 || FastPrimalityTest.isPrime(l, 7)).toArray();

        System.out.println(Arrays.toString(filteredPrimes));
    }

    @Test
    public void primesTo1000BigInteger() {
        ArrayList<BigInteger> primes = new ArrayList<>();

        for(int i = 0; i < 1000; i++) {
            if(FastPrimalityTest.isPrime(i, 2)) primes.add(new BigInteger(String.valueOf(i)));
        }
        BigInteger[] filteredPrimes = primes.toArray(new BigInteger[0]);

        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l.compareTo(new BigInteger("3")) <= 0 || FastPrimalityTest.isPrime(l, new BigInteger("3"))).toArray(BigInteger[]::new);
        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l.compareTo(new BigInteger("5")) <= 0 || FastPrimalityTest.isPrime(l, new BigInteger("5"))).toArray(BigInteger[]::new);
        filteredPrimes = Arrays.stream(filteredPrimes).filter(l -> l.compareTo(new BigInteger("7")) <= 0 || FastPrimalityTest.isPrime(l, new BigInteger("7"))).toArray(BigInteger[]::new);

        System.out.println(Arrays.toString(filteredPrimes));
    }

}