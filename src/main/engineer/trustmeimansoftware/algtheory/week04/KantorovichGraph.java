package engineer.trustmeimansoftware.algtheory.week04;

public class KantorovichGraph {
    KNode[] v_;
    KNode[] outputs;

    public KantorovichGraph(KNode[] v_) {
        this.v_ = v_;
    }


    public static double[] kantEvalG(double vin_0,double vin_1,double vin_2,double vin_3 ) {
        double v1 = vin_2 * vin_1;
        double v2 = vin_3 + v1;
        double v3 = Math.sin(v1);
        double v4 = vin_0 * v2;
        double v5 = v2 - v4;
        double v6 = v4 * v3;
        double v7 = v4 + v3;
        return new double[] {v5, v6, v7};
    }
}
