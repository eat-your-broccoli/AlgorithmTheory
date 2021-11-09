package engineer.trustmeimansoftware.algtheory.week06;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LucasNumbersTest {
    @Test
    public void table0to100() {
        HashMap<String, BigInteger> table = LucasNumbers.LArray(new BigInteger("100"));
        for(int i = 0; i < table.size(); i++) {
            System.out.println(i+": "+table.get(i+""));
        }
    }

    @Test
    public void lucasNum4321() {
        System.out.println(LucasNumbers.L(new BigInteger("4321")));
    }

    @Test
    public void Lto100() {
        HashMap<String, BigInteger> table = LucasNumbers.LArray(new BigInteger("100"));
        for(int i = 0; i < table.size(); i++) {
            System.out.println(i+": "+table.get(i+""));
        }
    }

    @Test
    public void ATable0to100() {
        for(int i = 0; i <= 100; i++) {
            System.out.println(i+": "+LucasNumbers.A(new BigInteger(i+"")));
        }
    }


    @Test
    public void test() {
        int i = 20;
        System.out.println(i+": "+LucasNumbers.A(new BigInteger(i+"")));
    }
}