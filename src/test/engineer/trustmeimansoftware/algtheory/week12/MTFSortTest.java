package engineer.trustmeimansoftware.algtheory.week12;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MTFSortTest {
    @Test
    public void test4() {
        List<Integer> problem = new ArrayList<Integer>(Arrays.asList(4,1,3,2));

        assertEquals(5, MTFSort.sort(problem));
    }

    @Test
    public void testE4() {
        MTFSort.expectedMovesForLength(20);
    }
}