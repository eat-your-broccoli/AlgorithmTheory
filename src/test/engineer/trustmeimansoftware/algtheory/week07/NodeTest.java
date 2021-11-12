package engineer.trustmeimansoftware.algtheory.week07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    public void compareTo() {
        Node<String> a = new Node<>("hello");
        Node<String> b = new Node<>("jello");
        assertTrue(a.compareTo(b) < 0);

        a = new Node<>("hello");
        b = new Node<>("hello");
        assertEquals(0, a.compareTo(b));

        Node <Integer> i = new Node<>(12);
        Node <Integer> j = new Node<>(12);
        assertEquals(0, i.compareTo(j));

        j = new Node<>(2000);

        assertTrue(i.compareTo(j) < 0);


    }
}