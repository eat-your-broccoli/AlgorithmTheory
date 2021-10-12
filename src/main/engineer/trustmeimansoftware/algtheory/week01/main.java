package engineer.trustmeimansoftware.algtheory.week01;


import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        boolean running = true;
        while(running) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Alg Theory Ex. 01.");
            System.out.println("Enter digit between 1 and 5 to select problem. Enter 'exit' to return");
            String input = sc.nextLine();

            switch (input) {
                case "1": ex01(); break;
                case "2": ex02(); break;
                case "3": ex03(); break;
                case "4": ex04(); break;
                // TODO this is broken for some partitions, e.g. {2, 7}
                case "5": ex05(); break;
                case "exit": running = false; break;
                default: System.out.println("Unknown problem: "+input);
            }
        }
    }

    public static void ex01() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProblem 01: Matrix powering");
        System.out.println("Enter your Matrix. Example: '1 2 3; 4 5 6; 7 8 9;'");
        String str = sc.nextLine();
        if(str.toLowerCase().equals("exit")) return;
        Matrix m;
        try {
            m = Matrix.fromString(str);
            if(m.values.length != m.values[0].length) throw new Error("Matrix must be quadratic");
        } catch (Throwable e) {
            System.out.println("Invalid input for Matrix: "+e.getMessage());
            return;
        }
        System.out.println("Your input:");
        System.out.println(m);
        System.out.println("Enter power:");
        int n = sc.nextInt();
        LocalTime start = LocalTime.now();
        Matrix result = m.toThePowerOf(n);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("The "+n+"-th power of input Matrix is:");
        System.out.println(result);
        System.out.println("Duration: "+ duration.toMillis() + " ms");
        System.out.println();
    }
    public static void ex02() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProblem 02: BinaryWords k = 2");
        System.out.println("Enter word length (n)");
        int n = sc.nextInt();

        System.out.println("Your input:");
        System.out.println(n);

        LocalTime start = LocalTime.now();
        BigInteger result = new BinaryWords(2).wordCountForN(n);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Binary Words for k = 2, n = "+n+": ");
        System.out.println(result);
        System.out.println("Duration: "+ duration.toMillis() + " ms");
        System.out.println();
    }
    public static void ex03() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProblem 03: BinaryWords for variable k");

        System.out.println("Enter min distance between two 1s (k)");
        int k = sc.nextInt();

        System.out.println("Enter word length (n)");
        int n = sc.nextInt();

        System.out.println("Your input:");
        System.out.println(n);

        LocalTime start = LocalTime.now();
        BigInteger result = new BinaryWords(k).wordCountForN(n);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Binary Words for k = "+k+", n = "+n+": ");
        System.out.println(result);
        System.out.println("Duration: "+ duration.toMillis() + " ms");
        System.out.println();
    }
    public static void ex04() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProblem 04: Partitions for {2,3,4}");

        System.out.println("Enter n");
        int n = sc.nextInt();

        System.out.println("Your input:");
        System.out.println(n);

        int[] pieces = new int[]{2,3,4};
        Partitions p = new Partitions(pieces);
        LocalTime start = LocalTime.now();
        BigInteger result = p.calcPartitionCountForN(n);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Partitions for pieces = "+ Arrays.toString(pieces) +", n = "+n+": ");
        System.out.println(result);
        System.out.println("Duration: "+ duration.toMillis() + " ms");
        System.out.println();
    }

    public static void ex05() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nProblem 05: Partitions for {m,k}");

        System.out.println("Enter n");
        int n = sc.nextInt();

        System.out.println("Enter piece m");
        int m = sc.nextInt();

        System.out.println("Enter piece k");
        int k = sc.nextInt();

        System.out.println("Your input:");
        System.out.println(n);

        int[] pieces = new int[]{m, k};
        Partitions p = new Partitions(pieces);
        LocalTime start = LocalTime.now();
        BigInteger result = p.calcPartitionCountForN(n);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Partitions for pieces = "+ Arrays.toString(pieces) +", n = "+n+": ");
        System.out.println(result);
        System.out.println("Duration: "+ duration.toMillis() + " ms");
        System.out.println();
    }

}
