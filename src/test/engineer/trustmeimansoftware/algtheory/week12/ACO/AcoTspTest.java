package engineer.trustmeimansoftware.algtheory.week12.ACO;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AcoTspTest {

    @Test
    public void runACO() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("res/tsp/tsp225.tsp"));

        TSP tsp = TSP.fromFile(reader);
        reader.close();
        AcoTsp aco = new AcoTsp();
        aco.tsp = tsp;

        aco.init();
        aco.run();

        reader = new BufferedReader(new FileReader("res/tsp/tsp225.opt.tour"));

        while(!reader.readLine().contains("TOUR_SECTION"));
        String line = reader.readLine();
        int counter = 0;
        int[] optRoute = new int[aco.tsp.getSize()];
        while(!line.contains("-1")) {
            optRoute[counter] = Integer.parseInt(line);
            counter++;
            line = reader.readLine();
        }
        System.out.println("opt route: "+ Arrays.toString(optRoute));
        Ant testAnt = new Ant(tsp.getSize(), optRoute[0]);
        double optRouteDistance = testAnt.getRouteLength(aco.tsp.distances);
        System.out.printf("opt route: %.9f\n", optRouteDistance);
    }

}