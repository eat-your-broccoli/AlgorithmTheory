package engineer.trustmeimansoftware.algtheory.week06;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LucasNumbersTest {
    @Test
    public void table0to100() {
        BigInteger[] table = LucasNumbers.LArray(100);
        for(int i = 0; i < table.length; i++) {
            System.out.println(i+": "+table[i]);
        }
    }

    @Test
    public void lucasNum4321() {
        System.out.println(LucasNumbers.L(4321));
    }

}