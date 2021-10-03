package engineer.trustmeimansoftware.algtheory.week01;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Models Matrix
 */
public class Matrix {

    public BigInteger[][] values;

    public Matrix(int rows, int cols ) {
        this.values = newBigIntMatrix(rows, cols);
    }

    public Matrix(int dim) {
        this(dim, dim);
    }

    public Matrix(BigInteger[][] values) {
        this.values = values;
    }

    /**
     * multiplies matrix with itself n times
     */
    public Matrix toThePowerOf(int n) {
        if(n < 0) throw new IllegalArgumentException("n cannot be below zero");
        // create identity matrix
        if (n == 0) {
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

    /**
     * create a Matrix of BigIntegers initialized with BigInteger.ZERO
     */
    private BigInteger[][] newBigIntMatrix(int rows, int cols) {
        BigInteger[][] m = new BigInteger[rows][cols];
        for(BigInteger[] row: m) {
            Arrays.fill(row, BigInteger.ZERO);
        }
        return m;
    }

    /**
     * multiplies this with a Matrix to the right
     */
    public Matrix leftMultiply(Matrix rightMatrix) {
        int rows = this.values.length;
        int cols = rightMatrix.values[0].length;

        BigInteger[][] newValues = this.newBigIntMatrix(rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                BigInteger cellSum = BigInteger.ZERO;
                for(int i = 0; i < rows; i++) {
                    BigInteger a = this.values[row][i];
                    BigInteger b = rightMatrix.values[i][col];
                    cellSum = cellSum.add(a.multiply(b));
                }
                newValues[row][col] = cellSum;
            }
        }
        return new Matrix(newValues);
    }

    /**
     * create Matrix from a String
     * rows are separated by semicolon
     * cells are separated by spaces
     */
    public static Matrix fromString(String str) {
        String[] rawRows = str.split(";");
        // if input is suffixed with a ";", last row may be empty. remove it
        if(rawRows[rawRows.length -1].isEmpty()) {
            rawRows = Arrays.copyOfRange(rawRows, 0, rawRows.length -1);
        }
        // trim all inputs. leading spaces will throw errors
        for(int i = 0; i < rawRows.length; i++) {
            rawRows[i] = rawRows[i].trim();
        }
        int rowCount = rawRows.length;
        int colCount = rawRows[0].split(" ").length;

        BigInteger[][] values = new BigInteger[rowCount][colCount];
        for(int i = 0; i < rawRows.length; i++) {
            String[] elems = rawRows[i].split(" ");
            for(int j = 0; j < elems.length; j++) {
                values[i][j] = new BigInteger(elems[j]);
            }
        }
        return new Matrix(values);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for(BigInteger[] row: this.values) {
            builder.append("\t{");
            for(int i = 0; i < row.length; i++) {
                builder.append("\t").append(row[i]);
                if(i != row.length -1) builder.append(",");
            }
            builder.append("}\n");
        }
        builder.append("]");
        return builder.toString();
    }
}
