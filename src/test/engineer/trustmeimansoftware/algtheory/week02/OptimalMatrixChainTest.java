package engineer.trustmeimansoftware.algtheory.week02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalMatrixChainTest {
    @Test
    @DisplayName("find optimal matrix chain for dims [30,35,15,5,10,20,25]")
    public void findOptimalOrder() {
        OptimalMatrixChain optMaxChain = new OptimalMatrixChain(new int[]{30,35,15,5,10,20,25});
        long result = optMaxChain.get();
        String resultSequence = optMaxChain.getSequence();
        assertEquals(15125L, result);
        assertEquals("((M^1 * (M^2 * M^3)) * ((M^4 * M^5) * M^6))", resultSequence);
    }

    @Test
    @DisplayName("find optimal matrix chain for dims [30, 30]")
    public void findOptimalOrderSmall() {
        OptimalMatrixChain optMaxChain = new OptimalMatrixChain(new int[]{30, 30});
        long result = optMaxChain.get();
        String resultSequence = optMaxChain.getSequence();
        assertEquals(0, result);
        assertEquals("M^1", resultSequence);
    }

    @Test
    @DisplayName("find optimal matrix chain for dims [30, 40, 5]")
    // matrices are 30x40 and 40 x 50
    // multiplcations are 30x40x5 = 6000
    public void findOptimalOrder30405() {
        OptimalMatrixChain optMaxChain = new OptimalMatrixChain(new int[]{30, 40, 5});
        long result = optMaxChain.get();
        String resultSequence = optMaxChain.getSequence();
        assertEquals(6000, result);
        assertEquals("(M^1 * M^2)", resultSequence);
    }

    @Test
    @DisplayName("find optimal matrix chain for dims [30, 40, 5]")
    // matrices are 30x40 and 40 x 50
    // multiplcations are 30x40x5 = 6000
    public void findOptimalOrderExercise() {
        OptimalMatrixChain optMaxChain = new OptimalMatrixChain(new int[]{10, 4, 5, 4, 5, 4, 5, 4, 5, 10, 4, 5, 4, 5, 4, 5, 7});
        long result = optMaxChain.get();
        String resultSequence = optMaxChain.getSequence();
        assertEquals(1500, result);
        System.out.println(resultSequence);
    }


}