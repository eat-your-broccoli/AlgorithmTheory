package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

public abstract class Node {
    double data;

    public abstract double get();
    public abstract double derivative(Node target);
    public abstract boolean containsTargetInput(Node target);

    // public abstract Func derivative();

    public static Node input() {
        return new InputNode();
    }

    public static Node constant(double d) {
        return new ConstantNode(d);
    }

    public static Node add(Node n1, Node n2) {
        return new AddNode(n1, n2);
    }

    public static Node minus(Node n1, Node n2) {
        return new MinusNode(n1, n2);
    }

    public static Node multiply(Node n1, Node n2) {
        return new MultiplyNode(n1, n2);
    }

    public static Node sin(Node n1, boolean isPositive) {
        return new SinNode(n1, isPositive);
    }

    public static Node cos(Node n1, boolean isPositive) {
        return new CosNode(n1, isPositive);
    }

    public static Node exp(Node n1, Node n2) {
        return new ExpNode(n1, n2);
    }


}

class InputNode extends Node {

    @Override
    public double get() {
        return this.data;
    }

    @Override
    public double derivative(Node target) {
        if(this == target) return 1;
        else return 0;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return (this == target);
    }
}

class ConstantNode extends Node {

    public ConstantNode(double d) {
        this.data = d;
    }
    @Override
    public double get() {
        return this.data;
    }

    @Override
    public double derivative(Node target) {
        return data;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return false;
    }
}


class AddNode extends Node {
    Node n1;
    Node n2;

    public AddNode(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public double get() {
        return this.n1.get() + this.n2.get();
    }

    @Override
    public double derivative(Node target) {
        return n1.derivative(target) + n2.derivative(target);
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }
}

class MinusNode extends Node {
    Node n1;
    Node n2;

    public MinusNode(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public double get() {
        return this.n1.get() - this.n2.get();
    }

    @Override
    public double derivative(Node target) {
        return n1.derivative(target) - n2.derivative(target);
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }
}

class MultiplyNode extends Node {
    Node n1;
    Node n2;

    public MultiplyNode(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public double get() {
        return this.n1.get() * this.n2.get();
    }

    @Override
    public double derivative(Node target) {
        return n1.derivative(target) * n2.get() + n1.get() * n2.derivative(target);
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }


}

class SinNode extends Node{
    Node n1;
    boolean isPositive = true;
    public SinNode(Node n1, boolean isPositive) {
        this.n1 = n1;
        this.isPositive = isPositive;
    }

    @Override
    public double get() {
        double res = Math.sin(this.n1.get());
        return isPositive ? res : (-1 * res);
    }

    @Override
    public double derivative(Node target) {
        return 0;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target);
    }
}

class CosNode extends Node{
    Node n1;
    boolean isPositive = true;
    public CosNode(Node n1, boolean isPositive) {
        this.n1 = n1;
        this.isPositive = isPositive;
    }

    @Override
    public double get() {
        double res = Math.cos(this.n1.get());
        return isPositive ? res : (-1 * res);
    }

    @Override
    public double derivative(Node target) {
        return 0;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return false;
    }
}

class ExpNode extends Node{
    Node n1;
    Node n2;

    public ExpNode(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public double get() {
        return Math.pow(n1.get(), n2.get());
    }

    @Override
    public double derivative(Node target) {
        return 0;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return false;
    }
}



