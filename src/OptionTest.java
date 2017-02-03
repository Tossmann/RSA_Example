/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionTest implements Option{
    public void run() {
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        ownKeyChain.readKeyStringsFromFile();
        Encrypt newEncryption = new Encrypt((ownKeyChain.getPublicKey()).toString(),(ownKeyChain.getCommonN()).toString());
        System.out.println(newEncryption.encryptToBigInt("Test message for you du wiener"));
    }
}
