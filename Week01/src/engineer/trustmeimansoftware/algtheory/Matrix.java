package engineer.trustmeimansoftware.algtheory;
import java.math.BigInteger;

public class Matrix {

    public BigInteger[][] values;

    // non-quadratic matrices are not wanted yet, so we forbid them
    private Matrix(int rows, int cols ) {
        this.values = newBigIntMatrix(rows, cols);
    }

    public Matrix(int dim) {
        this(dim, dim);
    }

    public Matrix(BigInteger[][] values) {
        this.values = values;
    }

    public Matrix toThePowerOf(int n) {
        if(n < 0) throw new IllegalArgumentException("n cannot be below zero");
        if (n == 0) {
            // create identity matrix
           Matrix m = new Matrix(this.values.length);
           for(int i = 0; i < m.values.length; i++) {
               m.values[i][i] = BigInteger.ONE;
           }
           return m;
        }
        if(n == 1) {
            return new Matrix(this.values);
        } else if (n == 2) {
            return this.leftMultiply(this);
        } else if (n % 2 == 0) {
            Matrix m = this.toThePowerOf(n / 2);
            return m.leftMultiply(m);
        } else {
            Matrix m = this.toThePowerOf(n-1);
            return m.leftMultiply(this);
        }
    }

    private BigInteger[][] newBigIntMatrix(int rows, int cols) {
        return new BigInteger[rows][cols];
    }

    public Matrix leftMultiply(Matrix rightMatrix) {
        int rows = this.values.length;
        int cols = rightMatrix.values[0].length;

        BigInteger[][] newValues = newBigIntMatrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                BigInteger cellSum = BigInteger.ZERO;
                for(int i = 0; i < rows; i++) {
                    cellSum = cellSum.add(this.values[row][i].multiply(rightMatrix.values[i][col]));
                }
                newValues[row][col] = cellSum;
            }
        }
        return new Matrix(newValues);
    }
}
