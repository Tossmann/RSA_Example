import java.math.BigInteger;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionExternKeyChangeCreater implements Option {
    public void run() {
        System.out.println("Setting External Key Chain ...");
        String name = Inputer.getInput("Name: ","string");
        BigInteger recipient = new BigInteger(Inputer.getInput("Give me the Public Key: ","integer"));
        BigInteger n = new BigInteger(Inputer.getInput("Give me the Public N: ","integer"));
        KeyChainSaver newExternalKeyChain = new KeyChainSaver(recipient,n,name);
        newExternalKeyChain.saveKeysToFile();
        System.out.println("Key Chain for '" + name + "' was set");
    }
}
