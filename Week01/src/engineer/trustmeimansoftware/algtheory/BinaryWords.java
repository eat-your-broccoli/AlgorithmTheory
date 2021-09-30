package engineer.trustmeimansoftware.algtheory;

import javax.naming.BinaryRefAddr;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class BinaryWords {

    HashMap<Integer, BigInteger> buffer;
    int k;

    public BinaryWords() {
        this(2);
    }

    public BinaryWords(int k) {
        this.buffer = new HashMap<>();
        this.k = k;
        for(int i = 0; i < this.k + 1; i++) {
            this.buffer.put(i, new BigInteger((i+1)+""));
        }
    }

    public BigInteger wordCountForN(int n) {
        if(this.buffer.containsKey(n)) return this.buffer.get(n);
        BigInteger result = this.wordCountForN(n-1).add(this.wordCountForN(n-(k+1)));
        this.buffer.put(n, result);

        return result;
    }


    public static void main(String[] args) {

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Enter # of zeros between ones:");
        int k;
        k = scanner.nextInt();
        BinaryWords bin = new BinaryWords(k);
        System.out.println("Enter any positive Number and compute its BinaryWord count. Enter neg. number for exit");
        while(true) {
            int x = scanner.nextInt();
            if(x < 0) {
                return;
            }

            BigInteger result = bin.wordCountForN(x);
            System.out.println("BinaryWord count for "+ x + ": "+result.toString());
        }
    }
}
