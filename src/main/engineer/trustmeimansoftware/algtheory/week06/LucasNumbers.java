package engineer.trustmeimansoftware.algtheory.week06;

import java.math.BigInteger;
import java.util.Arrays;

public class LucasNumbers {
    public static BigInteger L(int n, BigInteger[] memory) {
        if(!memory[n].equals(BigInteger.ZERO)) return memory[n];
        BigInteger nBigInt =new BigInteger(String.valueOf(n));
        if(n <= 1) memory[n] = BigInteger.TWO.subtract(nBigInt);
        else memory[n] = L(n-1, memory).add(L(n-2, memory));
        return memory[n];
    }

    public static BigInteger[] LArray(int n) {
        BigInteger[] memory = new BigInteger[n+1];
        Arrays.fill(memory, BigInteger.ZERO);
        L(n, memory);
        return memory;
    }

    public static BigInteger L(int n) {
        return LArray(n)[n];
    }
}
