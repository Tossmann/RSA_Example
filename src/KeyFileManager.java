import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyFileManager {

	public final String KEY_CHAIN_FILE_NAME = "chain.key";

	public void saveKeysToFile(BigInteger publicKey, BigInteger privateKey, BigInteger commonN) {
		try {

			File keyChainFile = new File("own" + KEY_CHAIN_FILE_NAME);

			if (keyChainFile.getParentFile() != null) {
                keyChainFile.getParentFile().mkdirs();
			}
            keyChainFile.createNewFile();

			ObjectOutputStream keyChainFileOS = new ObjectOutputStream(new FileOutputStream(keyChainFile));
            keyChainFileOS.writeObject(publicKey.toString());
            keyChainFileOS.writeObject(privateKey.toString());
            keyChainFileOS.writeObject(commonN.toString());
            keyChainFileOS.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	public static BigInteger getPublicKey() {
        return new BigInteger(readStringFromFile(PUBLIC_KEY_FILE));
	}

    public static BigInteger getPrivateKey() {
        return new BigInteger(readStringFromFile(PRIVATE_KEY_FILE));
    }

    public static BigInteger getCommonN() {
        return new BigInteger(readStringFromFile(COMMON_N));
    }
*/
	public static void readStringFromFile(String which) {
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(which);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(which));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] current = sCurrentLine.split("\\D+");
                int counter = 0;
                for (String currents: current) {
                    System.out.println(counter);
                    counter ++;
                    if(currents.length()>1 && currents.matches("[0-9]+"))
                        System.out.println(currents);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    }

}
