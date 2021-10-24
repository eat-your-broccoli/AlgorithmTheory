package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

public class Graph {

    Node[] inputs;
    Node[] outputs;
    Node[][] jacobian;
    boolean computedJacobian = false;

    public Graph(Node[] inputs, Node[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
        jacobian = new Node[inputs.length][outputs.length];
    }

    public double[] compute(double[] inputVals) {
        for(int i = 0; i < inputVals.length; i++) {
            inputs[i].data = inputVals[i];
        }

        double[] results = new double[outputs.length];

        for(int i = 0; i < outputs.length; i++) {
            results[i] = outputs[i].calc();
        }

        return results;
    }

    public Node[][] getJacobian() {
        if(this.computedJacobian) return this.jacobian;
        for(int i = 0; i < inputs.length; i++) {
            for(int j = 0; j < outputs.length; j++) {
                jacobian[i][j] = outputs[j].df(inputs[i]);
            }
        }

        return jacobian;
    }
}
