import java.math.BigInteger;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class KeyChain {

    BigInteger[] ownKeyChain = new BigInteger[3];
    BigInteger[] externalKeyChain = new BigInteger[2];
    boolean isOwnKeyChain;
    String name;
    String path = "KeyChain.txt";
    String[] begin = {"---beginPublicKey---","---beginCommonN---","---beginPrivateKey---"};
    String[] end = {"---endPublicKey---","---endCommonN---","---endPrivateKey---"};

    public BigInteger getPublicKey() {
        if (isOwnKeyChain)
            return ownKeyChain[0];
        else
            return externalKeyChain[0];
    }

    public BigInteger getPrivateKey() {
        if (isOwnKeyChain)
            return ownKeyChain[2];
        else
            return null;
    }

    public BigInteger getCommonN() {
        if (isOwnKeyChain)
            return ownKeyChain[1];
        else
            return externalKeyChain[1];
    }

}
