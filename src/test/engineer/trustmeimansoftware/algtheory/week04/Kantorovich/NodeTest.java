package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

import org.junit.jupiter.api.Test;

public class NodeTest {
    @Test
    public void df() {
        // f(x,y) = 4x * y + x
        // df/dx = 4y + 1
        // df/dy = 4x

        Node x = Node.x();
        Node y = Node.y();
        Node const4 = Node.constant(4);
        Node x4 = Node.multiply(const4, x);
        Node xy4 = Node.multiply(x4, y);
        Node result = Node.add(xy4, x);

        System.out.println(result.df(x).toString());
        System.out.println(result.df(y).toString());
    }
}
