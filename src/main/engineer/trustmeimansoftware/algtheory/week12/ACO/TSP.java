package engineer.trustmeimansoftware.algtheory.week12.ACO;

import java.io.BufferedReader;
import java.io.IOException;

public class TSP {
    public double[][] distances;
    public double[][] cords;


    public int getSize() {
        return distances.length;
    }

    public static TSP fromFile(BufferedReader reader) throws IOException {
        TSP tsp = new TSP();
        tsp.getCityCordsFromReader(reader);
        tsp.calculateDistances();
        return tsp;
    }

    private void calculateDistances() {
        for (int i = 0; i < distances.length; i++) {
            for (int j = i + 1; j < distances.length; j++) {
                // euclidean distance
                distances[i][j] = Math.pow((cords[i][0] - cords[j][0]),2) + Math.pow((cords[i][1] - cords[j][1]), 2);
                // distances are symmetric
                distances[j][i] = distances[i][j];
            }
        }
    }

    private void getCityCordsFromReader(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while(!line.contains("DIMENSION")) {
            line = reader.readLine();
        }

        int dim = Integer.parseInt(line.split(" : ")[1]);
        this.distances = new double[dim][dim];
        this.cords = new double[dim][2];

        while(!line.contains("NODE_COORD_SECTION")) {
            line = reader.readLine();
        }
        line = reader.readLine();
        while(line != null && !line.equals("EOF")) {
            String[] split = line.trim().split(" ");
            int index = Integer.parseInt(split[0]) - 1;
            this.cords[index][0] = Double.parseDouble(split[1]);
            this.cords[index][1] = Double.parseDouble(split[2]);

            line = reader.readLine();
        }
    }
}
