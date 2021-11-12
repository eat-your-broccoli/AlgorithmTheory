package engineer.trustmeimansoftware.algtheory.week07;

import engineer.trustmeimansoftware.algtheory.week05.RNG;

import java.util.ArrayList;
import java.util.List;

public class SkipList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int height;
    public int maxHeight;
    private RNG rand = new RNG(123);

    public SkipList(int maxHeight) {
        if(maxHeight < 0) throw new IllegalArgumentException("maxHeight cannot be less than 0");
        this.height = 0;
        this.maxHeight = maxHeight;
    }

    public SkipList(int maxHeight, List<T> values) {
        this(maxHeight);
        values.sort(Comparable::compareTo);
        int iHead = values.size() > 0 ? 0 : -1;
        int iTail = values.size() > 0 ? values.size() - 1 : -1;
        this.head = new Node<>(values.get(iHead));
        this.tail = new Node<>(values.get(iTail));
        // insert all elements between
        Node<T> lastNode = this.head;
        for(int i = 1; i < iTail; i++) {
            lastNode.setRef(0, new Node<>(values.get(i)));
            lastNode = lastNode.getRef(0);
        }
        lastNode.setRef(0, tail);
        buildIndex();
    }

    public T head() {
        if(this.head == null) return null;
        return this.head.key;
    }

    public T tail() {
        if(this.tail == null) return null;
        return this.tail.key;
    }


    public void buildIndex() {
        if(this.maxHeight <= 0) return;
        int c = 0;
        ArrayList<Node<T>> updates = new ArrayList<>();
        for(int i = 0; i <= this.maxHeight; i++){
            updates.add(i, this.head);
        }
        Node<T> lastNode;
        Node<T> node = this.head;
        while(node != null) {
            c++;
            lastNode = node;
            node = node.getRef(0);
            if(node == null) break;
            // values that are not power of 2 are ignored
            if(c % 2 == 1) continue;
            for(int i = 1; i <= maxHeight; i++) {
                // check if multiples of 2 of i th power
                // >>> inserts a 0
                // if yes, set as ref of ith height
                if(isMultipleOf2(c, i)) {
                    updates.get(i).setRef(i, node);
                    updates.set(i, node);
                } else break;
            }
        }
        for(int i = 1; i < updates.size(); i++) {
            Node<T> lastUpdate = updates.get(i);
            if(lastUpdate != tail) lastUpdate.setRef(i, tail);
        }
    }

    private boolean isMultipleOf2(int a, int pow) {
        if(pow == 1 && a == 2) return true;
        int div = (1 << pow);
        return (a == div) || (a > div && a % div == 0);
    }

    /**
     * returns a node with the exact value or less than that
     * this node can then be used to insert new value after the returned
     * null indicates that there is no node with a smaller value. A Node with searchValue then has to be inserted as new head
     * @param searchValue
     * @return
     */
    public Node<T> searchClosestLeftNeighbor(T searchValue) {
        Node<T> n = this.head;
        int i = maxHeight;
        for(; i >= 0; i--) {
            while(n.getRef(i) != null && n.getRef(i).key.compareTo(searchValue) < 0) {
                n = n.getRef(i);
            }
        }
        if(n.key.compareTo(searchValue) > 0) return null;
        return n;
    }

    public Node<T> searchExact(T searchValue) {
        Node<T> n = this.searchClosestLeftNeighbor(searchValue);
        if(n == null) return null;
        return (n.key.compareTo(searchValue) == 0) ? n : null;
    }

    public void insert(Node<T> n) {
        Node<T> insertNode = searchClosestLeftNeighbor(n.key);
        if(insertNode == null) {
            n.refs = this.head.refs;
            n.refs.set(0, this.head);
            this.head = this.tail;
        } else if (insertNode.equals(this.tail)) {
            T tmp = n.key;
            n.key = tail.key;
            Node<T> preTail = this.searchClosestLeftNeighbor(this.tail.key);
            preTail.setRef(0, n);
            // TODO
        }
    }

    private int randheight () {
        int height = 0;
        while (rand.rand(2) % 2 == 1 && height < maxHeight){
            height++;
        }
        return height;
    }
}

