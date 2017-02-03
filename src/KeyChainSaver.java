import java.io.*;
import java.math.BigInteger;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class KeyChainSaver  extends KeyChain{

    public KeyChainSaver(BigInteger publicKey, BigInteger privateKey, BigInteger commonN){
        ownKeyChain[0] = publicKey;
        ownKeyChain[1] = commonN;
        ownKeyChain[2] = privateKey;
        name = "own";
        isOwnKeyChain = true;
        saveKeysToFile();
     }

    public KeyChainSaver(BigInteger publicKey, BigInteger commonN, String name){
        externalKeyChain[0] = publicKey;
        externalKeyChain[1] = commonN;
        this.name = name;
        isOwnKeyChain = false;

        saveKeysToFile();
    }

    public void saveKeysToFile() {
        try {
            File keyChainFile = new File(name + path);
            if (keyChainFile.getParentFile() != null) {
                keyChainFile.getParentFile().mkdirs();
            }
            keyChainFile.createNewFile();


            PrintWriter out = new PrintWriter(name+path);
            if (isOwnKeyChain) {
                for(int i = 0; i < ownKeyChain.length; i ++) {
                    out.println(begin[i]);
                    out.println(ownKeyChain[i].toString());
                    out.println(end[i]);
                }
            } else {
                for(int i = 0; i < externalKeyChain.length; i ++) {
                    out.println(begin[i]);
                    out.println(externalKeyChain[i].toString());
                    out.println(end[i]);
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
