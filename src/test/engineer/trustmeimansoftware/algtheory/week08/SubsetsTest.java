package engineer.trustmeimansoftware.algtheory.week08;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubsetsTest {

    @Test
    public void a_4() {
        assertEquals(10, Subsets.a(4));
    }

    @Test
    public void table() {
        for(int i = 1; i <= 1000; i++) {
            System.out.println(i+": "+Subsets.a(i));
        }
    }


}