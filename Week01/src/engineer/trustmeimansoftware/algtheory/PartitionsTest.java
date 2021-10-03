package engineer.trustmeimansoftware.algtheory;

import engineer.trustmeimansoftware.algtheory.test.Partitions;
import javafx.scene.chart.PieChart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class PartitionsTest {

    @Test
    @DisplayName("Create Partition's Generating Func with Pieces 1,2,3")
    public void createGFWithPiecesOneTwoThree() {
        Partitions p = new Partitions(new int[]{1,2,3});
        assertEquals("(1 - x - x^2 + x^4 + x^5 - x^6)", p.gf.polynomial.toString());
    }

    @Test
    @DisplayName("Create Partition's Generating Func with Pieces 1,2,3")
    public void leftMultiplyPartition() {
        Partitions p = new Partitions(new int[]{1,2,3});
        BigInteger[][] values = new BigInteger[][] {
                {new BigInteger("1")},
                {new BigInteger("1")},
                {new BigInteger("2")},
                {new BigInteger("3")},
                {new BigInteger("4")},
                {new BigInteger("5")},
        };
        Matrix m = new Matrix(values);
        Matrix result = p.startMatrix.leftMultiply(m);
    }

    @Test
    @DisplayName("calc start values for pieces 1,2,3")
    public void calcStartValuesForPiecesOneTwoThree() {
        Partitions p = new Partitions(new int[]{1,2,3});
        assertEquals("1", p.startValues[0].toString());
        assertEquals("1", p.startValues[1].toString());
        assertEquals("2", p.startValues[2].toString());
        assertEquals("3", p.startValues[3].toString());
    }

    @Test
    @DisplayName("calc start values for pieces 1,2,3")
    public void calcStartValuesForPiecesTwoFiveSeven() {
        Partitions p = new Partitions(new int[]{2,5,11});
        assertEquals("1", p.startValues[0].toString());
        assertEquals("0", p.startValues[1].toString());
        assertEquals("1", p.startValues[2].toString());
        assertEquals("0", p.startValues[3].toString());
        assertEquals("1", p.startValues[4].toString());
        assertEquals("1", p.startValues[5].toString());
        assertEquals("1", p.startValues[6].toString());
        assertEquals("1", p.startValues[7].toString());
        assertEquals("1", p.startValues[8].toString());
        assertEquals("1", p.startValues[9].toString());
        assertEquals("2", p.startValues[10].toString());
        assertEquals("2", p.startValues[11].toString());
    }

    @Test
    @DisplayName("calc partitions count for {1,2,3}, n = 10")
    public void calcPartitionsOneTwoThreeN60() {
        Partitions p = new Partitions(new int[]{1,2,3});
        assertEquals("1", p.calcPartitionCountForN(0).toString());
        assertEquals("1", p.calcPartitionCountForN(1).toString());
        assertEquals("2", p.calcPartitionCountForN(2).toString());
        assertEquals("3", p.calcPartitionCountForN(3).toString());
        assertEquals("4", p.calcPartitionCountForN(4).toString());
        assertEquals("5", p.calcPartitionCountForN(5).toString());
        assertEquals("7", p.calcPartitionCountForN(6).toString());
        assertEquals("8", p.calcPartitionCountForN(7).toString());
        assertEquals("10", p.calcPartitionCountForN(8).toString());
        assertEquals("12", p.calcPartitionCountForN(9).toString());
        assertEquals("331", p.calcPartitionCountForN(60).toString());
    }




}