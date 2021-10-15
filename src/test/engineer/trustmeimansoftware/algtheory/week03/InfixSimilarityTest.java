package engineer.trustmeimansoftware.algtheory.week03;

import engineer.trustmeimansoftware.algtheory.week02.LatticePaths3D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfixSimilarityTest {

    @Test
    @DisplayName("get similarity between two chars")
    public void similarityTwoChars() {
        InfixSimilarity i = new InfixSimilarity("abc", "a-c");
        assertEquals(1, i.similarity(1, 1));
        assertEquals(-1, i.similarity(2, 1));
        assertEquals(-2, i.similarity(1, 2));
        assertEquals(-2, i.similarity(4, 2));
    }

    @Test
    @DisplayName("get similarity between two char sequences")
    public void similarityTwoCharSeq() {
        InfixSimilarity i = new InfixSimilarity("abcabc", "abca-c");
        assertEquals(3, i.similarity(1,1, 3));
        assertEquals(3, i.similarity(1,1, 6));
    }

    @Test
    @DisplayName("get similarity between two strings")
    public void similarityTwoStrings() {
        InfixSimilarity i = new InfixSimilarity("abcabc", "abca-c");
        assertEquals(4, i.similarity());
    }

    @Test
    void getMostSimilarInfixes() {
        InfixSimilarity i = new InfixSimilarity("abcabc", "abca-c");
        String[] infixes = i.getMostSimilarInfixes();
        assertEquals("abca", infixes[0]);
        assertEquals("abca", infixes[1]);
    }
}