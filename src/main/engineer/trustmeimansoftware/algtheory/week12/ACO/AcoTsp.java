package engineer.trustmeimansoftware.algtheory.week12.ACO;

import java.util.Arrays;

public class AcoTsp {

    public static final int QUANTUM = 100;
    public static final double RHO = 0.8d;
    public static final double PHEROMONE_INIT = 0.001d;



    TSP tsp;
    Ant[] ants;
    double[][] pheromones;

    public void init() {
        ants = new Ant[tsp.getSize() * 20];

        // init an ant in each city
        for(int i = 0; i < ants.length; i++)
            ants[i] = new Ant(tsp.getSize(), i % tsp.getSize());

        pheromones = new double[tsp.getSize()][tsp.getSize()];
        for(double[] row : pheromones) {
            Arrays.fill(row, PHEROMONE_INIT);
        }
    }

    public int[] run() {
        for(int time = 1; time < tsp.getSize(); time++) {
            // all the ants make one step
            double[][] pheromonesUpdate = new double[pheromones.length][pheromones.length];
            for(Ant ant: ants) {
                // next city to visit
                int currentCity = ant.currentCity;
                int nextCity = ant.selectCity(tsp.distances, pheromones);
                ant.goToCity(nextCity);
                // pheromone level update
                // uniformly chose indices, because transitions A > B and B > A are the same, so write to same edge
                int from = Math.min(currentCity, nextCity);
                int to = Math.max(currentCity, nextCity);
                double dist = tsp.distances[from][to];
                pheromonesUpdate[from][to] += (double) QUANTUM / dist;
            }

            for(int i = 0; i < pheromones.length; i++) {
                for(int j = 0; j < pheromones.length; j++) {
                    pheromones[i][j] = ((pheromones[i][j] * (1.0d - RHO)) + (RHO * pheromonesUpdate[i][j]));
                    pheromones[j][i] = pheromones[i][j];
                }
            }
        }

        // determine fastest path
        Ant fastestAnt = ants[0];
        double shortesPath = fastestAnt.getRouteLength(tsp.distances);

        for( Ant ant: ants) {
            double rLen = ant.getRouteLength(tsp.distances);
            // System.out.println("Ant "+ant.route[0]+ " => "+rLen);
            // System.out.println(Arrays.toString(ant.route));
            if(rLen < shortesPath) {
                fastestAnt = ant;
                shortesPath = rLen;
            }
        }

        // print out fastest ant path
        System.out.println("fastest route: "+Arrays.toString(fastestAnt.route));
        System.out.printf("distance: %.9f\n", shortesPath);
        return fastestAnt.route;
    }
}
