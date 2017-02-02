import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by andreydelany on 02/02/2017.
 */
public class RSAKeyGenerator {

    int BIT_LENGTH = 2048;
    BigInteger publicKey;
    BigInteger privateKey;
    BigInteger n;

    public RSAKeyGenerator() {
        Random rand = new SecureRandom();
        BigInteger p = new BigInteger(BIT_LENGTH / 2, 100, rand);
        BigInteger q = new BigInteger(BIT_LENGTH / 2, 100, rand);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        n = p.multiply(q);
        do publicKey = new BigInteger(phi.bitLength(), rand);
        while (publicKey.compareTo(BigInteger.ONE) <= 0 || publicKey.compareTo(phi) >= 0 || !publicKey.gcd(phi).equals(BigInteger.ONE));
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getN() {
        return n;
    }
}

