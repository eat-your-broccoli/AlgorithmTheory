package engineer.trustmeimansoftware.algtheory.week07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

}