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
        AcoTsp aco = new AcoTsp(100, 0.8d, 0.0001d, 30, 50);
        aco.tsp = tsp;

        aco.init();
        aco.run();
        System.out.println();

        reader = new BufferedReader(new FileReader("res/tsp/tsp225.opt.tour"));

        while(!reader.readLine().contains("TOUR_SECTION"));
        String line = reader.readLine();
        int counter = 0;
        int[] optRoute = new int[aco.tsp.getSize()];
        while(!line.contains("-1")) {
            optRoute[counter] = Integer.parseInt(line) - 1; // in solution cities index starts at 1, map it to 0 indexed
            counter++;
            line = reader.readLine();
        }
        System.out.println("opt route: "+ Arrays.toString(optRoute));

        Ant testAnt = new Ant(tsp.getSize(), optRoute[0]);
        testAnt.route = optRoute;
        double optRouteDistance = testAnt.getRouteLength(aco.tsp.distances);
        System.out.printf("opt route: %.2f\n", optRouteDistance);

        System.out.println();
        int[] naiveRoute = new int[tsp.getSize()];
        for(int i =0 ; i < tsp.getSize(); i++) naiveRoute[i] = i;
        Ant naiveAnt = new Ant(tsp.getSize(), optRoute[0]);
        naiveAnt.route = naiveRoute;
        System.out.printf("naive route: %.2f\n", naiveAnt.getRouteLength(tsp.distances));
    }

}