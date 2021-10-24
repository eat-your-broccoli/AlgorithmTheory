package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

public class Graph {

    Node[] inputs;
    Node[] outputs;
    Node[][] jacobian;

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

        for(int i = 0; i < inputVals.length; i++) {
            results[i] = outputs[i].calc();
        }

        return results;
    }
}
