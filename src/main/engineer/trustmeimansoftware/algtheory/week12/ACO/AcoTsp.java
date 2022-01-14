package engineer.trustmeimansoftware.algtheory.week12.ACO;

import java.util.Arrays;

public class AcoTsp {

    public static final int QUANTUM = 100;
    public static final double RHO = 0.8d;
    public static final double PHEROMONE_INIT = 0.0001d;
    public static final int MAX_ITERATION = 100;



    TSP tsp;
    Ant[] ants;
    double[][] pheromones;

    public void init() {
        System.out.printf("Init %d ants for AOC\n", tsp.getSize());
        ants = new Ant[tsp.getSize()];

        // init an ant in each city
        for(int i = 0; i < ants.length; i++)
            ants[i] = new Ant(tsp.getSize(), i % tsp.getSize());

        pheromones = new double[tsp.getSize()][tsp.getSize()];
        for(double[] row : pheromones) {
            Arrays.fill(row, PHEROMONE_INIT);
        }
    }

    public double run() {
        double[] distances = new double[MAX_ITERATION];
        double shortestDistance = Double.MAX_VALUE;
        int[] shortestRoute = new int[tsp.getSize()];
        Arrays.fill(distances, Double.MAX_VALUE);

        for(int time = 0; time < MAX_ITERATION; time++) {
            // all the ants chose a path
            for(Ant ant: ants) {
                antTrip(ant);
            }

            // update pheromones
            // dissipating pheromones
            dissipatePheromones();

            // each ant dispenses pheromone along path
            for(Ant ant: ants) {
                dispensePheromones(ant);
                double distance = ant.getRouteLength(tsp.distances);
                // store shortest path for each iteration
                distances[time] = Math.min(distances[time], distance);
                shortestDistance = Math.min(shortestDistance, distances[time]);
                if(shortestDistance == distances[time]) {
                    shortestRoute = ant.route;
                }
                // reset ant
                int startCity = ant.route[0];
                ant.reset();
                ant.setStartCity(startCity);
            }
        }

        for(double path: distances) {
            System.out.printf("route: %.9f\n", path);
        }

        System.out.printf("shortest route found (dist): %.9f\n", shortestDistance);
        System.out.printf("shortest route found: %s\n", Arrays.toString(shortestRoute));


        return shortestDistance;
    }

    private void dissipatePheromones() {
        for(int i = 0; i < pheromones.length; i++) {
            for(int j = i + 1; j < pheromones.length; j++) {
                pheromones[i][j] = pheromones[i][j] * (1.0d - RHO);
                pheromones[j][i] = pheromones[i][j];
            }
        }
    }

    private void dispensePheromones(Ant ant) {
        double distance = ant.getRouteLength(tsp.distances);
        double addPheromone = QUANTUM / distance;
        // make +1 steps to include loop back to start point with (i mod route.length)
        for(int i = 0; i < ant.route.length; i++) {
            //
            int from = Math.min(ant.route[i], ant.route[(i+1) % ant.route.length]);
            int to = Math.max(ant.route[i], ant.route[(i+1) % ant.route.length]);;
            pheromones[from][to] += addPheromone;
            pheromones[to][from] = pheromones[from][to];
        }
    }



    private void antTrip(Ant ant) {
        int nextCity = ant.selectCity(tsp.distances, pheromones);
        while(nextCity >= 0) {
            ant.goToCity(nextCity);
            nextCity = ant.selectCity(tsp.distances, pheromones);
        }
    }
}
