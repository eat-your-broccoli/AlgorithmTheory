package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpNodeTest {

    @Test
    public void calc() {
        Node base = Node.x();
        Node power = Node.constant(3);
        Node exp = Node.exp(base, power);

        base.data = 2;

        assertEquals(Math.pow(2,3), exp.calc());
    }

    @Test
    public void calcXpowX() {
        Node x = Node.x();
        Node exp = Node.exp(x, x);

        for(int i = 0; i <= 10; i++) {
            x.data = i;
            assertEquals(Math.pow(i,i), exp.calc());
        }
    }



}