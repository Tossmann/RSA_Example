import java.math.BigInteger;

/**
 * Created by andreydelany on 02/02/2017.
 */
public class CryptionProcess {

    BigInteger publicKey;
    BigInteger n;


    public CryptionProcess(String publicKey, String n) {
        if (publicKey.matches("[0-9]+") && n.matches("[0-9]+")) {
            this.publicKey = new BigInteger(publicKey);
            this.n = new BigInteger(n);
        }
        else if (publicKey.matches("[0-9]+") && !n.matches("[0-9]+")) {
            this.publicKey = new BigInteger(publicKey);
            this.n = new BigInteger(n.getBytes());
        }
        else if (!publicKey.matches("[0-9]+") && n.matches("[0-9]+")) {
            this.publicKey = new BigInteger(publicKey.getBytes());
            this.n = new BigInteger(n);
        }
        else {
            this.publicKey = new BigInteger(publicKey.getBytes());
            this.n = new BigInteger(n.getBytes());
        }
    }
}
