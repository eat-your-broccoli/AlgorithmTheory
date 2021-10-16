package engineer.trustmeimansoftware.algtheory.week03;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Euler67Test {

    @Test
    void load() throws IOException {
        Euler67 e = new Euler67();
        e.load();
        assertEquals(100, e.dim);
        assertEquals(59, e.triangle.get(0)[0]);
    }

    @Test
    void solve() throws IOException {
        Euler67 e = new Euler67();
        e.load();
        int maxPath = e.solve();
        assertEquals(7273, maxPath);
        System.out.println("Solution: "+maxPath);
        System.out.println("Number of calculations: "+e.countCalls);
        System.out.println("Number of comparison calls: "+e.countComp);
    }
}