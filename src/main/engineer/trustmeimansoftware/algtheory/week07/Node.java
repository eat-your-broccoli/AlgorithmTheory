package engineer.trustmeimansoftware.algtheory.week07;

import java.util.ArrayList;
import java.util.List;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    T key;
    List<Node<T>> refs = new ArrayList<>();

    public Node(T value) {
        this.key = value;
    }

    @Override
    public int compareTo(Node<T> t) {
        return this.key.compareTo(t.key);
    }

    public void setRef(int index, Node<T> node) {
        if(index < refs.size()) refs.set(index, node);
        else refs.add(index, node);
    }

    public Node<T> getRef(int index) {
        try {
            return refs.get(index);
        } catch (Exception e) {
            return null;
        }
    }
}
