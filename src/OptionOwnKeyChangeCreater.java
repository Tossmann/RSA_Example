/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionOwnKeyChangeCreater implements Option {
    public void run() {
        System.out.println("Set new Key ...");
        RSAKeyGenerator newRSAKey = new RSAKeyGenerator();
        KeyChainSaver ownKey = new KeyChainSaver(newRSAKey.getPublicKey(),newRSAKey.getPrivateKey(),newRSAKey.getN());
        ownKey.saveKeysToFile();
        System.out.println("New Key Pair was Set!");
    }

}
