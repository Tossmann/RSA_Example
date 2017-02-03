import com.sun.deploy.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class KeyChainReader extends KeyChain{

    public KeyChainReader(String name) {
        super();
        this.name = name;
        if (name.equals("own")) {
            isOwnKeyChain = true;
        } else {
            isOwnKeyChain = false;
        }

        saveKeys(readKeyStringsFromFile());
    }

    public String readKeyStringsFromFile() {


        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(name + path);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(name + path));
            String wholeKeyChain= "";
            while ((sCurrentLine = br.readLine()) != null) {
                wholeKeyChain += sCurrentLine;
            }
            return wholeKeyChain;
        } catch (IOException e) {
            System.err.println("KeyChain for this name is noch imported");
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private void saveKeys(String keyChain) {
        int endLoop;
        if (isOwnKeyChain) {
            endLoop = 3;
        }
        else {
            endLoop = 2;
        }
        for (int i = 0; i < endLoop; i++) {
            String startOfBlock = begin[i];
            String endOfBlock = end[i];
            int startIndex = keyChain.indexOf(startOfBlock) + startOfBlock.length();
            int endIndex = keyChain.indexOf(endOfBlock);
            String result = keyChain.substring(startIndex,endIndex);
            if (isOwnKeyChain)
                ownKeyChain[i] = new BigInteger(result);
            else
                externalKeyChain[i] = new BigInteger(result);
        }
    }

}
