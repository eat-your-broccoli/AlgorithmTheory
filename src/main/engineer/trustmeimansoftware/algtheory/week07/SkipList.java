package engineer.trustmeimansoftware.algtheory.week07;

import engineer.trustmeimansoftware.algtheory.week05.RNG;

import java.lang.reflect.Array;
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
                System.out.println("checking: "+c+ " : "+i);
                // check if multiples of 2 of i th power
                // >>> inserts a 0
                // if yes, set as ref of ith height
                if(isMultipleOf2(c, i)) {
                    System.out.println("Set node("+lastNode.key+")."+i+": "+node.key);
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
        System.out.println("Multiple: "+a + " pow: "+pow);
        if(pow == 1 && a == 2) return true;
        int div = (1 << pow);
        return (a == div) || (a > div && a % div == 0);
    }

    public void insert(Node<T> n) {

    }

    private int randheight () {
        int height = 0;
        while (rand.rand(2) % 2 == 1 && height < maxHeight){
            height++;
        }
        return height;
    }
}

