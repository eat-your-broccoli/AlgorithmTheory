package engineer.trustmeimansoftware.algtheory.week07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    @Test
    public void create() {
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7);
        SkipList<Integer> sl = new SkipList<>(3, list);
        assertEquals(0, sl.head());
        assertEquals(7, sl.tail());
    }

    @Test
    public void search() {
        List<Integer> list = Arrays.asList(0, 2, 8, 12, 19);
        SkipList<Integer> sl = new SkipList<>(3, list);
        assertNull(sl.searchExact(18));
        assertNull(sl.searchExact(1));
        assertNull(sl.searchExact(23));
        assertNull(sl.searchExact(7));

        assertEquals(12, sl.searchClosestLeftNeighbor(18).key);
        assertEquals(2, sl.searchClosestLeftNeighbor(5).key);
        assertNull(sl.searchClosestLeftNeighbor(-1));
        assertEquals(19, sl.searchClosestLeftNeighbor(200).key);
    }

}