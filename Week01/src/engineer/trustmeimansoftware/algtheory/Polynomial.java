package engineer.trustmeimansoftware.algtheory;

public class Polynomial {
    public int[] values;

    public Polynomial(int[] values) {
        this.values = values;
    }
    public Polynomial(int order) {
        this.values = new int[order];
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial(this.values.length + other.values.length -1);
        for(int i = 0; i < this.values.length; i++) {
            for(int j = 0; j < other.values.length; j++) {
                result.values[i+j] += this.values[i] * other.values[j];
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("(");
        for(int i = 0; i < this.values.length; i++) {
            int val = this.values[i];
            int valAbs = Math.abs(val);
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