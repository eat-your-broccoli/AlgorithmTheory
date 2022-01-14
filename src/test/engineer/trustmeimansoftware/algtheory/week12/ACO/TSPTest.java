package engineer.trustmeimansoftware.algtheory.week12.ACO;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TSPTest {
    @Test
    public void parse() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("res/tsp/tsp225.tsp"));
        TSP tsp = TSP.fromFile(reader);
        assertEquals(tsp.cords[tsp.cords.length-1][0], 368.42d);
        assertEquals(tsp.cords[tsp.cords.length-1][1], 150.65d);
    }
}