import java.math.BigInteger;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionDecryption implements Option {
    public void run() {
        System.out.println("Decryption Process");
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        ownKeyChain.readKeyStringsFromFile();
        BigInteger privateKey = ownKeyChain.getPrivateKey();
        BigInteger n = ownKeyChain.getCommonN();
        Decrypt newDecryption = new Decrypt(privateKey,n);
        String cypher = Inputer.getInput("Give me the Cypher: ","integer");
        System.out.println(newDecryption.decrypt(cypher));

    }
}
