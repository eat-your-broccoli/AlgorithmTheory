package engineer.trustmeimansoftware.algtheory.week01;

import engineer.trustmeimansoftware.algtheory.week01.SimpleGeneratingFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGeneratingFunctionTest {
    @Test
    @DisplayName("create SGF for 1 => 1 / (1 - x)")
    public void createSGFforOne() {
        SimpleGeneratingFunction f = new SimpleGeneratingFunction(1);
        assertEquals("(1 - x)", f.polynomial.toString());
    }

    @Test
    @DisplayName("create SGF for 4 => 1 / (1 - x^4)")
    public void createSGFforFour() {
        SimpleGeneratingFunction f = new SimpleGeneratingFunction(4);
        assertEquals("(1 - x^4)", f.polynomial.toString());
    }


}