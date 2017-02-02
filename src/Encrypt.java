import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by andreydelany on 02/02/2017.
 */
public class Encrypt extends CryptionProcess{

    public Encrypt(String publicKey, String n) {
        super(publicKey,n);
    }

    public String encryptToString(String message) {
        return new String(encryptToBigInt(message).toByteArray());
    }

    public BigInteger encryptToBigInt(String message) {
        BigInteger msg = new BigInteger(message.getBytes());
        BigInteger enc = msg.modPow(publicKey, n);
        return enc;
    }

}
