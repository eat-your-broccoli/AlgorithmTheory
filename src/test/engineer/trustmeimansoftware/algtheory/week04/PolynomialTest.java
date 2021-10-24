package engineer.trustmeimansoftware.algtheory.week04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    @Test
    public void evaluate() {
        Polynomial p = new Polynomial(new double[]{3.7});
        assertEquals(3.7d, p.evaluate(20));
        p = new Polynomial(new double[]{3.7, 1.1});
        assertEquals(3.7d + 1.1*20, p.evaluate(20));
        p = new Polynomial(new double[]{3.7, 1.1, 2.4});
        assertEquals(3.7d + 1.1*20 + Math.pow(2.4d, 2)*20, p.evaluate(20));
    }

    @Test
    void derivation() {
        Polynomial p = new Polynomial(new double[]{2.1, 2.3, 4.5, 6.632});
        Polynomial derivate = p.Derivation();
        assertEquals(3, derivate.p.length);
        assertEquals(2.3d, derivate.p[0]);
        assertEquals(9.0d, derivate.p[1]);
        assertEquals(6.632d * 3, derivate.p[2]);
    }

    @Test
    void doubleDerivation() {
        Polynomial p = new Polynomial(new double[]{2.1, 2.3, 4.5, 6.632});
        Polynomial derivate = p.Derivation().Derivation();
        assertEquals(2, derivate.p.length);
        assertEquals(9.0d, derivate.p[0]);
        assertEquals(6.632d * 3 * 2, derivate.p[1]);
    }
}