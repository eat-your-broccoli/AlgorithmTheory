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
}
