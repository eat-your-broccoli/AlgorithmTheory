package engineer.trustmeimansoftware.algtheory.week12.ACO;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Ant {
    public HashSet<Integer> memory = new HashSet<>();
    public int[] route;
    public int currentCity;
    int counter;
    double alpha = 1;
    double beta = 5;


    public Ant(int cities, int startCity) {
        route = new int[cities];
        currentCity = startCity;
        route[0] = startCity;
        counter++;
    }

    public int selectCity(double[][] distances, double[][] pheromones) {
        double pSum = 0;
        double[] p = new double[distances.length];

        if(counter <= 1) {
            for(int i = 0; i < distances[this.currentCity].length; i++) {
                if(i != route[0] && !memory.contains(i)) {
                    p[i] = Math.pow(distances[this.currentCity][i], beta);
                    pSum += p[i];
                }
            }
        } else {
            for(int i = 0; i < distances[this.currentCity].length; i++) {
                if(i != route[0] && !memory.contains(i)) {
                    p[i] = Math.pow(pheromones[this.currentCity][i], alpha) * Math.pow(distances[this.currentCity][i], beta);
                    pSum += p[i];
                }
            }
        }

        for(int i = 0; i < p.length; i++) {
            p[i] = p[i] / pSum;
        }

        // 0.000001d to avoid the small chance that random is exactly 0
        // exactly 0 would be start city
        double random = ThreadLocalRandom.current().nextDouble(0.00001d, 1);
        double tmpSum = 0;
        for(int i = 0; i < p.length; i++) {
            tmpSum += p[i];
            if(tmpSum >= random) return i;
        }
        System.out.println("ERROR for Ant that started at: "+this.route[0]);
        System.out.println("Error occurred at step "+this.counter);
        System.out.println("Random val: "+random);
        throw new Error("should return one of the cities");
    }

    public void goToCity(int city) {
        this.memory.add(city);
        this.currentCity = city;
        route[counter] = city;
        counter++;
    }

    public double getRouteLength(double[][] distances) {
        // count all the ways from start city to start city again
        double dist = distances[route.length-1][0]; // distance from end to start
        for(int i = 0; i < route.length-1; i++) {
            int from = route[i];
            int to = route[i+1];
            dist += distances[from][to]; // and all the sub segments from start to end
        }
        return dist;
    }
}
