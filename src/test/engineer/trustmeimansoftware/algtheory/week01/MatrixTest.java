package engineer.trustmeimansoftware.algtheory.week01;

import engineer.trustmeimansoftware.algtheory.week01.Matrix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    @DisplayName("Creating quadratic instance of Matrix")
    void createMatrix_quadratic() {
        Matrix m = new Matrix(3);
        assertEquals(3, m.values.length);
        assertEquals(3, m.values[0].length);
    }

    @Test
    @DisplayName("Creating instance of Matrix from String")
    void createMatrix_fromString() {
        String s = "1 2 3; 4 5 6; 7 8 9";
        Matrix m = Matrix.fromString(s);

        assertEquals("1", m.values[0][0].toString());
        assertEquals("2", m.values[0][1].toString());
        assertEquals("3", m.values[0][2].toString());
        assertEquals("4", m.values[1][0].toString());
        assertEquals("5", m.values[1][1].toString());
        assertEquals("6", m.values[1][2].toString());
        assertEquals("7", m.values[2][0].toString());
        assertEquals("8", m.values[2][1].toString());
        assertEquals("9", m.values[2][2].toString());
        System.out.println(m);

    }

    @Test
    @DisplayName("Take Matrix to the power of 2")
    void multiplyWithItself() {
        BigInteger[][] values = {{BigInteger.ONE, BigInteger.TWO}, {new BigInteger("-1"), new BigInteger("3")}};
        Matrix m = new Matrix(values);
        m = m.leftMultiply(m);
        assertEquals("-1", m.values[0][0].toString());
        assertEquals("8", m.values[0][1].toString());
        assertEquals("-4", m.values[1][0].toString());
        assertEquals("7", m.values[1][1].toString());
    }

    @Test
    @DisplayName("Take Matrix to the power of 2")
    void toThePowerOf2() {
        BigInteger[][] values = {{BigInteger.ONE, BigInteger.TWO}, {new BigInteger("-1"), new BigInteger("3")}};
        Matrix m = new Matrix(values);
        m = m.toThePowerOf(2);
        assertEquals("-1", m.values[0][0].toString());
        assertEquals("8", m.values[0][1].toString());
        assertEquals("-4", m.values[1][0].toString());
        assertEquals("7", m.values[1][1].toString());
    }

    @Test
    @DisplayName("Take Matrix to the power of 3")
    void toThePowerOf3() {
        BigInteger[][] values = {{BigInteger.ONE, BigInteger.TWO}, {new BigInteger("-1"), new BigInteger("3")}};
        Matrix m = new Matrix(values);
        m = m.toThePowerOf(3);
        assertEquals("-9", m.values[0][0].toString());
        assertEquals("22", m.values[0][1].toString());
        assertEquals("-11", m.values[1][0].toString());
        assertEquals("13", m.values[1][1].toString());
    }

    @Test
    @DisplayName("Take Matrix to the power of 4")
    void toThePowerOf4() {
        BigInteger[][] values = {{BigInteger.ONE, BigInteger.TWO}, {new BigInteger("-1"), new BigInteger("3")}};
        Matrix m = new Matrix(values);
        m = m.toThePowerOf(4);
        assertEquals("-31", m.values[0][0].toString());
        assertEquals("48", m.values[0][1].toString());
        assertEquals("-24", m.values[1][0].toString());
        assertEquals("17", m.values[1][1].toString());
    }

    @Test
    @DisplayName("Take Matrix to the power of 23")
    void toThePowerOf23() {
        BigInteger[][] values = {
                {new BigInteger("1"), new BigInteger("2"), new BigInteger("0")},
                {new BigInteger("-1"), new BigInteger("3"), new BigInteger("-4")},
                {new BigInteger("2"), new BigInteger("-5"), new BigInteger("12")}
        };
        Matrix m = new Matrix(values);
        m = m.toThePowerOf(23);

        assertEquals("-1394593853838306128781001", m.values[0][0].toString());
        assertEquals("3143707370149613252673470", m.values[0][1].toString());
        assertEquals("-7299623734077356341620224", m.values[0][2].toString());

        assertEquals("-8871477419152162967956959", m.values[1][0].toString());
        assertEquals("19998172851504697977943029", m.values[1][1].toString());
        assertEquals("-46435345277724686384258172", m.values[1][2].toString());

        assertEquals("24130125605622012734831614", m.values[2][0].toString());
        assertEquals("-54394369730117179809512603", m.values[2][1].toString());
        assertEquals("126302605659904581427928972", m.values[2][2].toString());
    }

}