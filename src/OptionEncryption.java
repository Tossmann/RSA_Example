/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionEncryption implements Option {
    public void run() {
        System.out.println("Encryption Process ...");

        KeyManager.printPossibleKeys();

        String name = Inputer.getInput("Name: ","name");
        KeyChainReader externalKeyChain = new KeyChainReader(name);
        externalKeyChain.readKeyStringsFromFile();
        Encrypt newEncryption = new Encrypt((externalKeyChain.getPublicKey()).toString(),(externalKeyChain.getCommonN()).toString());
        String message = Inputer.getInput("Give me the Message: ","string");
        System.out.println(newEncryption.encryptToBigInt(message));
    }
}
