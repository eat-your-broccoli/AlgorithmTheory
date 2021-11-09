package engineer.trustmeimansoftware.algtheory.week06;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LucasNumbersTest {
    @Test
    public void Ltable0to100() {
        HashMap<String, BigInteger> table = LucasNumbers.LArray(new BigInteger("100"));
        for(int i = 0; i < table.size(); i++) {
            System.out.println(i+": "+table.get(i+""));
        }
    }

    @Test
    public void Lmat() {
        assertEquals(2, LucasNumbers.Lmat(new BigInteger("0")).intValue());
        assertEquals(1, LucasNumbers.Lmat(new BigInteger("1")).intValue());
        assertEquals(3, LucasNumbers.Lmat(new BigInteger("2")).intValue());
        assertEquals(4, LucasNumbers.Lmat(new BigInteger("3")).intValue());
        assertEquals(7, LucasNumbers.Lmat(new BigInteger("4")).intValue());
        assertEquals(11, LucasNumbers.Lmat(new BigInteger("5")).intValue());
        assertEquals(18, LucasNumbers.Lmat(new BigInteger("6")).intValue());
        assertEquals(29, LucasNumbers.Lmat(new BigInteger("7")).intValue());
        assertEquals(47, LucasNumbers.Lmat(new BigInteger("8")).intValue());
    }

    @Test
    public void AMat() {
        assertEquals(3, LucasNumbers.AMat(new BigInteger("0")).intValue());
        assertEquals(1, LucasNumbers.AMat(new BigInteger("1")).intValue());
        assertEquals(4, LucasNumbers.AMat(new BigInteger("2")).intValue());
        assertEquals(7, LucasNumbers.AMat(new BigInteger("3")).intValue());
        assertEquals(29, LucasNumbers.AMat(new BigInteger("4")).intValue());
        assertEquals(199, LucasNumbers.AMat(new BigInteger("5")).intValue());
        assertEquals(5778, LucasNumbers.AMat(new BigInteger("6")).intValue());
        assertEquals(1149851, LucasNumbers.AMat(new BigInteger("7")).intValue());
        assertEquals(6643838879L, LucasNumbers.AMat(new BigInteger("8")).longValue());
    }

    @Test
    public void table0to100LMat() {
        HashMap<String, BigInteger> table = LucasNumbers.LArray(new BigInteger("100"));
        LucasNumbers.lMemory.clear();
        for(int i = 0; i < table.size(); i++) {
            assertEquals(table.get(i + ""), LucasNumbers.Lmat(new BigInteger(i + "")));
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
            //System.out.println(i+": "+LucasNumbers.A(new BigInteger(i+"")));
            System.out.println(i+": "+LucasNumbers.AMatMod(new BigInteger(i+"")));
        }
        int i = 4321;
        System.out.println(i+": "+LucasNumbers.AMatMod(new BigInteger(i+"")));
    }
}