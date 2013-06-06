package fibonacci;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 24/05/2013
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class Fibonacci {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("0"), b = new BigInteger("1");
        BigInteger oldA = new BigInteger(a.toString());

        for (int i = 0; i < 100; ++i) {
            System.out.format("[%d] %s\n",i, new BigInteger(a.toString()).add(b).toString());
            oldA = new BigInteger(a.toString());
            a = b;
            b = oldA.add(b);
        }
    }
}
