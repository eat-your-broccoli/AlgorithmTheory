package engineer.trustmeimansoftware.algtheory.test;

import engineer.trustmeimansoftware.algtheory.Polynomial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    @DisplayName("Polynomial.toString()")
    public void polyToString() {
        Polynomial p = new Polynomial(new int[]{-1,1,1});
        assertEquals("( - 1 + x + x^2)", p.toString());
    }

    @Test
    @DisplayName("multiply Polynomial (1+x)²")
    public void createPolynomial() {
        int[] values = {1,1};
        Polynomial p = new Polynomial(values);
        Polynomial result = p.multiply(p);

        assertEquals(3, result.values.length);
        assertEquals(1, result.values[0]);
        assertEquals(2, result.values[1]);
        assertEquals(1, result.values[2]);
    }

    @Test
    @DisplayName("multiply Polynomial (1 - 2x + 4x^4) x (-5x^2 +9x^4)")
    public void multiplyPolynomial() {
        Polynomial p1 = new Polynomial(new int[] {1,-2,0,0,4});
        Polynomial p2 = new Polynomial(new int[] {0,0,-5,0,9});
        Polynomial result = p1.multiply(p2);

        assertEquals(9, result.values.length);
        assertEquals(0, result.values[0]);
        assertEquals(0, result.values[1]);
        assertEquals(-5, result.values[2]);
        assertEquals(10, result.values[3]);
        assertEquals(9, result.values[4]);
        assertEquals(-18, result.values[5]);
        assertEquals(-20, result.values[6]);
        assertEquals(0, result.values[7]);
        assertEquals(36, result.values[8]);
    }


}