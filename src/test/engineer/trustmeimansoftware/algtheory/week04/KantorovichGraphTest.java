package engineer.trustmeimansoftware.algtheory.week04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class KantorovichGraphTest {
    @Test
    public void kantEvalG() {
        double[] result = KantorovichGraph.kantEvalG(2,3,4,5);
        System.out.println(Arrays.toString(result));
    }
}
