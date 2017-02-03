/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionExportOwnKeyChange implements Option {
    public void run() {
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        KeyChainSaver exportOwnKeyChain = new KeyChainSaver(ownKeyChain.getPublicKey(),ownKeyChain.getCommonN(),"ownForExport");
        exportOwnKeyChain.saveKeysToFile();
        System.out.println("Own KeyChain was successfully exported");
    }
}
