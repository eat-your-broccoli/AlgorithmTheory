package engineer.trustmeimansoftware.algtheory.week01;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * BinaryWords models Strings consisting of 0s and 1s
 * k defines minimum distance between two 1s
 */
public class BinaryWords {

    // buffer to store values for dynamic programming
    HashMap<Integer, BigInteger> buffer;

    // min distance between 1s
    int k;

    public BinaryWords() {
        this(2);
    }

    public BinaryWords(int k) {
        this.buffer = new HashMap<>();
        this.k = k;
        // from zero to k, it increases by one
        for(int i = 0; i < this.k + 1; i++) {
            this.buffer.put(i, new BigInteger((i+1)+""));
        }
    }

    /**
     * calc word count for n
     * @return all words
     */
    public BigInteger wordCountForN(int n) {
        // TODO convert to recursion
        if(n < 0) return BigInteger.ZERO;

        // if we have a cache hit, use the value
        if(this.buffer.containsKey(n)) return this.buffer.get(n);
        // recursively calc result otherwise
        BigInteger result = this.wordCountForN(n-1).add(this.wordCountForN(n-(k+1)));
        // store for reuse
        this.buffer.put(n, result);

        return result;
    }
}
