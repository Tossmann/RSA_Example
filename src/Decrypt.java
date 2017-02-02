import java.math.BigInteger;

/**
 * Created by andreydelany on 02/02/2017.
 */
public class Decrypt extends CryptionProcess {

    public Decrypt(String publicKey, String n) {
        super(publicKey,n);
    }

    public String decrypt(BigInteger cypher) {
        BigInteger dec = cypher.modPow(publicKey, n);
        System.out.println(dec);
        return new String(dec.toByteArray());
    }

    public String decrypt(String cypher) {
        if (cypher.matches("[0-9]+"))
            return decrypt(new BigInteger(cypher));
        else {
            BigInteger dec = (new BigInteger(cypher.getBytes())).modPow(publicKey, n);
            System.out.println(dec);
            return new String(dec.toByteArray());
        }
    }

}
