package engineer.trustmeimansoftware.algtheory.week01;

import java.math.BigInteger;
import java.util.*;

/**
 * Partitions into pieces
 */
public class PartitionsRecursive {
    public int[] pieces;
    public BigInteger[] piecesBigInt;

    public HashMap<String, HashMap<Integer, BigInteger>> buffer = new HashMap<>();

    public PartitionsRecursive(int[] pieces) {
        if(pieces.length == 0) throw new IllegalArgumentException("pieces must contain at least one element");
        this.pieces = pieces;

        this.cleanupPieces();
    }


    /**
     * calculates the first values inefficiently for matrix multiplication
     */
    public void cleanupPieces() {
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

        this.piecesBigInt = piecesBigInt;
    }

    public BigInteger calcPartitionCountForN(int n) {
        BigInteger result = BigInteger.ONE;
        for(int i = 1; i <= n; i++) {
            result = this.calcStartValues(this.piecesBigInt, new BigInteger(i+""));
        }
        return result;
    }



    public BigInteger calcStartValues(BigInteger[] pieces, BigInteger n) {
        int nInt = n.intValue();
        if(nInt < 0) return BigInteger.ZERO;
        if(nInt == 0) return BigInteger.ONE;

        String keyPieces = Arrays.toString(pieces);
        // if we have a cache hit, use the value
        // use cache only, when all pieces are still present
        if(this.buffer.containsKey(keyPieces)) {
            if(this.buffer.get(keyPieces).containsKey(nInt)) return this.buffer.get(keyPieces).get(nInt);
        } else { // we have no cache for this subproblem, so let's create it
            this.buffer.put(keyPieces, new HashMap<>());
        }


        BigInteger result;

        BigInteger sum = BigInteger.ZERO;
        // for every piece, subtract it from n and calc recursively
        // this way, we prevent permutations of same sum, e.g. 1 + 2 and 2 + 1
        // e.g. P{1,2,3} = 111, 12, 3. Permutation 21 will not be counted
        for(int i = 0; i < pieces.length; i++) {
            BigInteger piece = pieces[i];
            sum = sum.add(this.calcStartValues(Arrays.copyOfRange(pieces, i, pieces.length), n.subtract(piece)));
        }
        result = sum;
        this.buffer.get(keyPieces).put(nInt, result);
        return result;
    }

    public static void main(String[] args) {
        PartitionsRecursive p = new PartitionsRecursive(new int[] {2,3,4});

        BigInteger result;
        for(int i = 0; i <= 60; i++) {
            result = p.calcPartitionCountForN(i);
            System.out.println(i+ ": "+result.toString());
        }
    }
}
