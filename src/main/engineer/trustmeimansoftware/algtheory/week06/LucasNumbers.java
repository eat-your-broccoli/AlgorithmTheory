package engineer.trustmeimansoftware.algtheory.week06;

import engineer.trustmeimansoftware.algtheory.week01.Matrix;

import java.math.BigInteger;
import java.util.HashMap;

public class LucasNumbers {

    static HashMap<String, BigInteger> lMemory = new HashMap<>();
    static HashMap<String, BigInteger> aMemory = new HashMap<>();

    static final Matrix matrix = new Matrix(new BigInteger[][]{{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}});
    static final Matrix vector = new Matrix(new BigInteger[][]{{BigInteger.TWO}, {BigInteger.ONE}});

    static final BigInteger modOperand = new BigInteger("9876543210");

    public static BigInteger A(BigInteger n) {
        if(aMemory.containsKey(n.toString())) return aMemory.get(n.toString());
        BigInteger modOp = new BigInteger("9876543210");
        aMemory.put(n.toString(), Lmod(L(n), modOp).mod(modOp));
        return aMemory.get(n.toString());
    }

    public static BigInteger AMat(BigInteger n) {
        BigInteger inner = Lmat(n);
        BigInteger outer = Lmat(inner);
        return outer.mod(new BigInteger("9876543210"));
    }

    public static BigInteger AMatMod(BigInteger n) {
        BigInteger inner = Lmat(n);
        BigInteger outer = LmatMod(inner, modOperand);
        return outer.mod(modOperand);
    }

    public static BigInteger Lmat(BigInteger n) {
        //if(lMemory.containsKey(n.toString())) return lMemory.get(n.toString());
        Matrix result =matrix.toThePowerOf(n.intValue()).leftMultiply(vector);
        return result.values[0][0];
    }
    public static BigInteger LmatMod(BigInteger n, BigInteger mod) {
        //if(lMemory.containsKey(n.toString())) return lMemory.get(n.toString());
        Matrix result =matrix.powMod(n, mod).leftMultiply(vector);
        return result.values[0][0];
    }


    public static BigInteger L(BigInteger n) {
        if(lMemory.containsKey(n.toString())) return lMemory.get(n.toString());
        BigInteger nBigInt =new BigInteger(String.valueOf(n));
        if(n.compareTo(BigInteger.ONE) <= 0) lMemory.put(n.toString(), BigInteger.TWO.subtract(nBigInt));
        else {
            BigInteger res = L(n.subtract(BigInteger.ONE)).add(L(n.subtract(BigInteger.TWO)));
            lMemory.put(n.toString(), res);
        }
        return lMemory.get(n.toString());
    }

    public static BigInteger Lmod(BigInteger n, BigInteger modOperator) {
        if(lMemory.containsKey(n.toString())) return lMemory.get(n.toString());
        BigInteger nBigInt =new BigInteger(String.valueOf(n));
        if(n.compareTo(BigInteger.ONE) <= 0) lMemory.put(n.toString(), BigInteger.TWO.subtract(nBigInt));
        else {
            BigInteger n1 = Lmod(n.subtract(BigInteger.ONE), modOperator);
            BigInteger n2 = Lmod(n.subtract(BigInteger.TWO), modOperator);
            BigInteger res = n1.add(n2).mod(modOperator);
            lMemory.put(n.toString(), res);
        }
        return lMemory.get(n.toString());
    }

    public static HashMap<String, BigInteger> LArray(BigInteger n) {
        L(n);
        return lMemory;
    }
}