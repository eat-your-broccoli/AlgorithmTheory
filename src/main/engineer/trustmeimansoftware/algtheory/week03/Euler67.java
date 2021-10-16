package engineer.trustmeimansoftware.algtheory.week03;

import engineer.trustmeimansoftware.algtheory.week01.Matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class Euler67 {
    ArrayList<int[]> triangle;

    int dim;
    int[][] paths;

    int countComp = 0;
    int countCalls = 0;

    public Euler67() {
    }

    public void load() throws IOException {
        triangle = new ArrayList<>();
        FileReader fr = new FileReader("res/euler/67_triangles.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while(line != null) {
            int[] vals = Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray();
            triangle.add(vals);
            line = br.readLine();
        }
        dim = triangle.size();
    }

    public int solve() {
        paths = new int[dim][dim];
        for(int i = 0; i < triangle.size(); i++) {
            for(int j = 0; j <= i; j++) {
                path(i,j);
            }
        }
        // find max value in last row
        return maxInArray(paths[paths.length-1]);
    }
    public int path(int i, int j) {
        countCalls++;
        if(i < 0 || j < 0 || i >= dim || j >= dim) return 0;
        // j can't be larger than i because triangle
        if(j > i) return 0;

        // we already calculated the problem
        if(paths[i][j] > 0) return paths[i][j];

        int val = triangle.get(i)[j];

        if(i == 0 && j == 0) paths[i][j] = val;
        // we're at the leftmost, so only one value possible
        // else if(j == 0) paths[i][j] = val + path(i-1, j);
        else if(j == 0) paths[i][j] = val + paths[i-1][j];
        // we're at the rightmost, so only one value possible
        else if(i == j) paths[i][j] = val + paths[i-1][j-1];
        // we're somewhere in between, so maximum of the values above
        else paths[i][j] = val + max(paths[i-1][j], paths[i-1][j-1]);

        return paths[i][j];
    }

    private int max(int left, int right) {
        countComp++;
        return Math.max(left, right);
    }

    private int maxInArray(int[] arr) {
        OptionalInt max = Arrays.stream(arr).max();
        if(max.isPresent()) return max.getAsInt();
        return -1;
    }
}
