package engineer.trustmeimansoftware.algtheory.test;

import engineer.trustmeimansoftware.algtheory.BinaryWords;
import engineer.trustmeimansoftware.algtheory.Matrix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BinaryWordsTest {
    @Test
    @DisplayName("testing 0,1,2")
    void testBasics() {
        BinaryWords bin = new BinaryWords(2);
        String res0 = bin.wordCountForN(0).toString();
        String res1 = bin.wordCountForN(1).toString();
        String res2 = bin.wordCountForN(2).toString();
        assertEquals("1", res0);
        assertEquals("2", res1);
        assertEquals("3", res2);
    }

    @Test
    @DisplayName("testing 43")
    void test43() {
        BinaryWords bin = new BinaryWords(2);
        String res = bin.wordCountForN(43).toString();
        assertEquals("18059374", res);
    }

    @Test
    @DisplayName("k = 0; n = 3 => 8")
    void testk0n3() {
        BinaryWords bin = new BinaryWords(0);
        String res = bin.wordCountForN(3).toString();
        assertEquals("8", res);
    }



}