package engineer.trustmeimansoftware.algtheory.week01;

import java.math.BigInteger;
import java.util.*;

/**
 * Partitions into pieces
 */
public class Partitions {
    public int[] pieces;
    public int startMatrixDim;
    // generating functions for each piece
    public SimpleGeneratingFunction[] sgf;
    // resulting generating function of sgf
    public SimpleGeneratingFunction gf;

    public Matrix baseMatrix;
    public Matrix valueVector;
    public BigInteger[] startValues;

    public Partitions(int[] pieces) {
        if(pieces.length == 0) throw new IllegalArgumentException("pieces must contain at least one element");
        this.pieces = pieces;
        sgf = new SimpleGeneratingFunction[pieces.length];
        for (int i = 0; i < pieces.length; i++) {
            sgf[i] = new SimpleGeneratingFunction(pieces[i]);
        }
        Polynomial result = new Polynomial(new int[]{1});
        for (SimpleGeneratingFunction s : this.sgf) {
            result = result.multiply(s.polynomial);
        }
        this.gf = new SimpleGeneratingFunction(result);
        this.baseMatrix = this.createBaseMatrix();
        this.calcStartValues();
        this.createValueVector();
    }

    public Matrix createBaseMatrix() {
        this.startMatrixDim = this.getStartMatrixDim();
        Matrix m = new Matrix(startMatrixDim);
        // fill last row with values from gf
        for(int i = 0; i < startMatrixDim; i++) {
            m.values[startMatrixDim-1][i] = new BigInteger(this.gf.polynomial.values[i]+"");
        }
        // all cells above diagonal are one
        for(int i = 0; i < startMatrixDim -1; i++) {
            m.values[i][i+1] = BigInteger.ONE;
        }
        return m;
    }

    /**
     * calculates the first values inefficiently for matrix multiplication
     */
    public void calcStartValues() {
        // sort pieces
        Arrays.sort(this.pieces);

        // remove duplicates
        // only allow positive values
        Set<Integer> removedDuplicates = new HashSet<>();
        for(int piece: this.pieces) {
            if(piece > 0) removedDuplicates.add(piece);
            else throw new IllegalArgumentException("pieces contains element equal or less than zero");
        }

        // convert pieces to BigIntegers
        BigInteger[] piecesBigInt = new BigInteger[removedDuplicates.size()];
        Iterator it = removedDuplicates.iterator();
        int index = 0;
        while(it.hasNext()) {
            piecesBigInt[index] = new BigInteger(it.next()+"");
            index++;
        }

        this.startValues = new BigInteger[this.startMatrixDim +1+ 100];
        this.startValues[0] = BigInteger.ONE;

        // calculate all values necessary for valueVector
        for (int i = this.startMatrixDim +100; i > 0; i--) {
            if(this.startValues[i] == null) this.calcStartValues(piecesBigInt, new BigInteger(i+""));
        }
    }


    /**
     * calculate startValues for use in valueVector recursively
     * @example pieces={1,2,3}, n = 3
     * 1 -> 1 -> 1 ->   = 1+1+1 returns 1
     *        -> 2 ->   = 1+1+2 returns 0
     *        -> 3 ->   = 1+1+3 returns 0
     *   -> 2 ->        = 1+2 returns 1
     *   -> 3 ->        = 1+3 returns 0
     * 2 -> 2           = 2+2 returns 0
     *   -> 3           = 2+3 returns 0
     * 3 ->             = 3 returns 1
     */
    public BigInteger calcStartValues(BigInteger[] pieces, BigInteger n) {
        int nInt = n.intValue();

        // if we have a cache hit, use the value
        // use cache only, when all pieces are still present
        if(this.pieces.length == pieces.length
                && nInt >= 0
                && nInt < this.pieces.length
                && this.startValues[nInt] != null){
            return this.startValues[n.intValue()];
        }

        BigInteger result;
        // return directly because we cannot set negative index to cache
        if(n.compareTo(BigInteger.ZERO) < 0) return BigInteger.ZERO;
        else if(n.equals(BigInteger.ZERO)) result = BigInteger.ONE;
        else {
            BigInteger sum = BigInteger.ZERO;
            // for every piece, subtract it from n and calc recursively
            // this way, we prevent permutations of same sum, e.g. 1 + 2 and 2 + 1
            // e.g. P{1,2,3} = 111, 12, 3. Permutation 21 will not be counted
            for(int i = 0; i < pieces.length; i++) {
                BigInteger piece = pieces[i];
                sum = sum.add(this.calcStartValues(Arrays.copyOfRange(pieces, i, pieces.length), n.subtract(piece)));
            }
            result = sum;
        }
        if(this.pieces.length == pieces.length) this.startValues[n.intValue()] = result;
        return result;
    }

    /**
     * converts startValues to valueVector
     */
    public void createValueVector() {
        BigInteger[][] values = new BigInteger[this.startValues.length][1];
        for(int i = 0; i < this.startValues.length; i++) {
            values[i][0] = this.startValues[i];
        }
        this.valueVector = new Matrix(values);
    }

    /**
     * calculates count of partitions for n
     * @param n
     * @return
     */
    public BigInteger calcPartitionCountForN(int n) {
        Matrix m1 = this.baseMatrix.toThePowerOf(n);
        Matrix m2 = m1.leftMultiply(this.valueVector);
        return m2.values[0][0];
    }


    private int getStartMatrixDim() {
        return this.gf.polynomial.values.length -1;
    }
}
