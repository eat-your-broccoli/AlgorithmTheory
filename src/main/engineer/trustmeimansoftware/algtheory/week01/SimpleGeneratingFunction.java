package engineer.trustmeimansoftware.algtheory.week01;

/**
 * generating function for a piece
 * only allows natural numbers
 *
 * generating function for a piece p is described as follows:
 * 1 / (1 - x^p)
 */
public class SimpleGeneratingFunction {

    public Polynomial polynomial;

    public SimpleGeneratingFunction(int piece) {
        if(piece <= 0) throw new IllegalArgumentException("negative numbers re forbidden");
        this.polynomial = new Polynomial(piece + 1);
        this.polynomial.values[0] = 1;
        this.polynomial.values[piece] = -1;
    }

    public SimpleGeneratingFunction(Polynomial polynomial) {
        this.polynomial = polynomial;
    }
}
