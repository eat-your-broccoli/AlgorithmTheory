package engineer.trustmeimansoftware.algtheory.week04.Kantorovich;

public abstract class Node {
    double data;

    public abstract double calc();
    public abstract Node df(Node target);
    public abstract boolean containsTargetInput(Node target);

    public boolean isNull() {
        return (this instanceof ConstantNode && this.data == 0d);
    }

    // public abstract Func derivative();

    public static Node input() {
        return new InputNode("x");
    }
    public static Node x() {
        return Node.input("x");
    }
    public static Node y() {
        return Node.input("y");
    }
    public static Node z() {
        return Node.input("z");
    }

    public static Node input(String name) {
        return new InputNode(name);
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

//    public static Node sin(Node n1, boolean isPositive) {
//        return new SinNode(n1, isPositive);
//    }
//
//    public static Node cos(Node n1, boolean isPositive) {
//        return new CosNode(n1, isPositive);
//    }
//
//    public static Node exp(Node n1, Node n2) {
//        return new ExpNode(n1, n2);
//    }


}

class InputNode extends Node {

    String name = "x";


    public InputNode(String name) {
        this.name = name;
    }

    @Override
    public double calc() {
        return this.data;
    }

    @Override
    public Node df(Node target) {
        if(this == target) return new ConstantNode(1);
        else return new ConstantNode(0);
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return (this == target);
    }

    @Override
    public String toString() {
        return name;
    }
}

class ConstantNode extends Node {

    public ConstantNode(double d) {
        this.data = d;
    }
    @Override
    public double calc() {
        return this.data;
    }

    @Override
    public Node df(Node target) {
        return new ConstantNode(0);
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
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
    public boolean isNull() {
        return n1.isNull() && n2.isNull();
    }

    @Override
    public double calc() {
        return this.n1.calc() + this.n2.calc();
    }

    @Override
    public Node df(Node target) {
        if(this.n1.isNull() && this.n2.isNull()) return new ConstantNode(0);
        if(this.n1.isNull()) return n2.df(target);
        if(this.n2.isNull()) return n1.df(target);
        return new AddNode(n1.df(target), n2.df(target));
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }

    @Override
    public String toString() {
        if(n1.isNull() && n2.isNull()) return "";
        if(!n1.isNull() && n2.isNull()) return n1.toString();
        if(n1.isNull() && !n2.isNull()) return n2.toString();
        return "("+n1.toString()+" + "+n2.toString()+")";
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
    public double calc() {
        return this.n1.calc() - this.n2.calc();
    }

    @Override
    public Node df(Node target) {
        return new MinusNode(n1.df(target), n2.df(target));
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }

    @Override
    public String toString() {
        return "("+n1.toString()+" -"+n2.toString()+")";
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
    public double calc() {
        return this.n1.calc() * this.n2.calc();
    }

    @Override
    public Node df(Node target) {
        if(this.n1.isNull() || this.n2.isNull()) return new ConstantNode(0);
        if(this.n1.isNull()) return new MultiplyNode(n1, n2.df(target));
        if(this.n2.isNull()) return new MultiplyNode(n1.df(target), n2);
        return new AddNode(
                new MultiplyNode(n1.df(target), n2),
                new MultiplyNode(n1, n2.df(target))
        );
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return n1.containsTargetInput(target) || n2.containsTargetInput(target);
    }

    @Override
    public String toString() {
        if(n1.isNull() || n2.isNull()) return "";
        return "("+n1.toString()+" * "+n2.toString()+")";
    }

    @Override
    public boolean isNull() {
        return n1.isNull() || n2.isNull();
    }
}

/*class SinNode extends Node{
    Node n1;
    boolean isPositive = true;
    public SinNode(Node n1, boolean isPositive) {
        this.n1 = n1;
        this.isPositive = isPositive;
    }

    @Override
    public double calc() {
        double res = Math.sin(this.n1.calc());
        return isPositive ? res : (-1 * res);
    }

    @Override
    public double df(Node target) {
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
    public double calc() {
        double res = Math.cos(this.n1.calc());
        return isPositive ? res : (-1 * res);
    }

    @Override
    public double df(Node target) {
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
    public double calc() {
        return Math.pow(n1.calc(), n2.calc());
    }

    @Override
    public double df(Node target) {
        return 0;
    }

    @Override
    public boolean containsTargetInput(Node target) {
        return false;
    }
}*/



