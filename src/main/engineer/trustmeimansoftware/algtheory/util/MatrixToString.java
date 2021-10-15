package engineer.trustmeimansoftware.algtheory.util;

import java.util.Arrays;

public class MatrixToString {
    public static String print(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for(int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            builder.append("\t");
            builder.append(Arrays.toString(row));
            builder.append("\n");
        }
        builder.append("]");
        return builder.toString();
    }
}
