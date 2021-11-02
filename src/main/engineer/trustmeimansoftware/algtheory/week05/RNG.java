package engineer.trustmeimansoftware.algtheory.week05;

public class RNG {

    int a = 16807; // 7^5
    int m = 2147483647; // 2^31 - 1 = a * q + r
    int q = 127773; // m / a
    int r = 2836; // m % a
    int seed = 0; // start seed

    public void setSeed (int seed) {
        this.seed = seed;
    }

    public double rand() {
        int gamma = a * (seed % q) - r * (seed / q); // in [-m-1, m]
        if (gamma >= 0) seed = gamma;
        else seed = gamma + m;
        return (double) seed / m;
    }

    public RNG(int seed) {
        this.seed = seed;
    }

    public RNG() {
        this((int) System.currentTimeMillis());
    }

    public int rand(int n) {
        return this.rand(0, n - 1);
    }

    public int rand(int min, int max) {
        return (int) (this.rand() * ((max - min) + 1)) + min;
    }
}