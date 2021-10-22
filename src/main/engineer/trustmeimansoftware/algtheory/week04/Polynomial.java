package engineer.trustmeimansoftware.algtheory.week04;

public class Polynomial {

    double[] p;

    public Polynomial(double[] p) {
        this.p = p;
    }

    public double evaluate(int x) {
        if(p.length == 0) return 0d;
        double sum = p[0];
        for(int i = 1; i < p.length; i++) {
            sum += Math.pow(p[i], i) * x;
        }
        return sum;
    }

    public Polynomial Derivative() {
        if(p.length == 0) return new Polynomial(new double[]{});
        double[] derivedP = new double[p.length-1];
        for(int i = 0; i < derivedP.length; i++) {
            derivedP[i] = this.p[i+1] * (i+1);
        }
        return new Polynomial(derivedP);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("(");
        for(int i = 0; i < this.p.length; i++) {
            double val = this.p[i];
            double valAbs = Math.abs(val);
            if(valAbs != 0) {
                if(i != 0 || val < 0) {
                    if(val > 0) str.append(" + ");
                    if(val < 0) str.append(" - ");
                }
                if(valAbs != 1 || i == 0) str.append(valAbs);
                if(i >= 1) str.append("x");
                if(i > 1) str.append("^").append(i);
            }
        }
        str.append(")");
        return str.toString();
    }
}
