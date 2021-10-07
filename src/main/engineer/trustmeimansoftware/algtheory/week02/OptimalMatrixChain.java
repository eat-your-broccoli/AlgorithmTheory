package engineer.trustmeimansoftware.algtheory.week02;

public class OptimalMatrixChain {
    public int[] dims;
    public int [][] splits;
    public long [][] mins;

    public long optimalMatrixMultiplications;

    public OptimalMatrixChain(int[] dimensions) {
        this.dims = dimensions;
        this.splits = new int[this.dims.length][this.dims.length];
        this.mins = new long[this.dims.length][this.dims.length];
    }

    public long get() {
        if(this.optimalMatrixMultiplications > 0L) return this.optimalMatrixMultiplications;
        this.optimalMatrixMultiplications = this.m(1, this.dims.length -1);
        return this.optimalMatrixMultiplications;
    }

    public String getSequence() {
        if(this.optimalMatrixMultiplications == 0L) this.get();
        String result = this.getStrSequence(1, this.dims.length -1);
        return result;
    }

    private String getStrSequence(int i, int j) {
        if(i == j) return "M^"+i;
        int k = this.splits[i][j];
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(this.getStrSequence(i,k));
        builder.append(" * ");
        builder.append(this.getStrSequence(k+1, j));
        builder.append(")");
        return builder.toString();
    }

    private long m(int i, int j) {
        if(i == j) return 0;
        if(this.mins[i][j] > 0L) return this.mins[i][j];
        long minimum = Long.MAX_VALUE;
        long h = 0L;
        for(int k = i; k < j; k++) {
            h = this.m(i,k)+m(k+1,j)+ this.dims[i-1]*this.dims[k]*this.dims[j];
            if(h < minimum) {
                minimum = h;
                splits[i][j] = k;
            }
        }
        this.mins[i][j] = minimum;
        return minimum;
    }
}
