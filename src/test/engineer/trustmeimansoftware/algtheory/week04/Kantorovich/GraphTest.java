package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {
    @Test
    public void computeGraph() {
        Node i1 = Node.input();
        Node i2 = Node.input();
        Node i3 = Node.input();
        Node[] inputs = {i1,i2,i3};

        Node v1 = Node.minus(i1, i2);
        Node v2 = Node.multiply(i2, i3);
        Node v3 = Node.add(i2, i3);

        Node o1 = Node.multiply(v1, v2);
        Node o2 = Node.minus(v1, v2);
        Node o3 = Node.multiply(v2, v3);

        Node[] outputs = new Node[] {o1,o2,o3};

        Graph g = new Graph(inputs, outputs);
        double[] result = g.compute(new double[]{1,2,3});
        assertEquals(-6d, result[0]);
        assertEquals(-7d, result[1]);
        assertEquals(30d, result[2]);
        System.out.println(Arrays.toString(result));
    }


    public Graph getProblem() {
        Node v0 = Node.input("x1");
        Node v_1 = Node.input("x2");
        Node v_2 = Node.input("x3");
        Node v_3 = Node.input("v4");

        Node v1 = Node.multiply(v_2, v_1);
        Node v2 = Node.add(v_3, v1);
        Node v3 = Node.sin(v1, true);
        Node v4 = Node.multiply(v0, v2);
        Node v5 = Node.minus(v2, v4);
        Node v6 = Node.multiply(v4, v3);
        Node v7 = Node.add(v4, v3);

        Node[] inputs = new Node[]{v0, v_1, v_2, v_3};
        Node[] outputs= new Node[]{v5, v6, v7};

        return new Graph(inputs, outputs);
    }

    @Test
    void compute() {
        Graph g = getProblem();
        double[] results = g.compute(new double[]{1,2,3,4});
        System.out.println(Arrays.toString(results));
    }

    @Test
    void kantEvalDG() {
        Graph g = getProblem();
        Node[][] jacobian = g.getJacobian();
        for(Node[] n : jacobian) {
            System.out.println(Arrays.deepToString(n));
        }
    }

    @Test
    void getJacobian22() {
        Graph g = getProblem();
        Node[][] jacobian = g.getJacobian();
        Node j = jacobian[2][2];

        for(int x2 = -10; x2 <= 10; x2++) {
            g.inputs[0].data = 4;
            g.inputs[1].data = x2;
            g.inputs[2].data = 0.5;
            g.inputs[3].data = -2;
            System.out.println("f'_[2,2](4,"+x2+",0.5,-2) = "+j.calc());
        }
    }
}
