package engineer.trustmeimansoftware.algtheory.week06;

import java.math.BigInteger;

public class FastPrimalityTest {

    public static boolean isPrime(long n, int base) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n == 3) return true;
        if(n == 5) return true;

        long z = base;
        long power = n - 1;
        while(power > 1) {
            z = (z * base) % n;
            power--;
        }
        return z == 1;
    }

    public static boolean isPrime(long n) {
        return isPrime(n, 2);
    }

    public static boolean isPrime(BigInteger n, BigInteger base) {
        if(n.equals(BigInteger.TWO)) return true;
        BigInteger z = base;
        BigInteger power = n.subtract(BigInteger.ONE);
        while(power.compareTo(BigInteger.ONE) > 0) {
            z = (z.multiply(base)).mod(n);
            power = power.subtract(BigInteger.ONE);
        }
        return z.equals(BigInteger.ONE);
    }

    public static boolean isPrime(BigInteger n) {
        return isPrime(n, BigInteger.TWO);
    }
}
