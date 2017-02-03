import java.io.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args){
        gui();
	}

        private static void gui() {
           try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("\n(Set)OnwKeyChain, Set(Ext)ernalKeyChain, Export(Own)KeyChain, (En)cryption Or (De)crpytion: ");
                    String input = br.readLine();
                    if (inputIsLegal(input)) {
                        runChoosedOption(input);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static boolean inputIsLegal(String inputRaw) {
            String input = inputRaw.toLowerCase();
            if (input.equals("set") ||input.equals("ext") || input.equals("own") ||input.equals("en") || input.equals("de") || input.equals("copy") )
                return true;
            return false;
        }

        private static void runChoosedOption(String input) {
            if (input.equals("set"))
                setNewKeyPair();
            else if (input.equals("own"))
                exportOwnKeyChain();
            else if (input.equals("ext"))
                saveExternalKeyChain();
            else if (input.equals("en"))
                encrypt();
            else if (input.equals("copy"))
                copyCurrentContactToExternalContact();
            else
                decrypt();
        }

    private static void setNewKeyPair() {
        System.out.println("Set new Key ...");
        RSAKeyGenerator newRSAKey = new RSAKeyGenerator();
        KeyChainSaver ownKey = new KeyChainSaver(newRSAKey.getPublicKey(),newRSAKey.getPrivateKey(),newRSAKey.getN());
        ownKey.saveKeysToFile();
        System.out.println("New Key Pair was Set!");
    }

    private static void exportOwnKeyChain() {
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        KeyChainSaver exportOwnKeyChain = new KeyChainSaver(ownKeyChain.getPublicKey(),ownKeyChain.getCommonN(),"ownForExport");
        exportOwnKeyChain.saveKeysToFile();
        System.out.println("Own KeyChain was successfully exported");
    }

    private static String saveExternalKeyChain() {
        System.out.println("Setting External Key Chain ...");
        String name = getInput("Name: ");
        BigInteger recipient = new BigInteger(getInput("Give me the Public Key: "));
        BigInteger n = new BigInteger(getInput("Give me the Public N: "));
        KeyChainSaver newExternalKeyChain = new KeyChainSaver(recipient,n,name);
        newExternalKeyChain.saveKeysToFile();
        System.out.println("Key Chain for '" + name + "' was set");
        return name;
    }

    private static void encrypt() {
        System.out.println("Encryption Process ...");
        String decision = getInput("Is Recipient already listed? (y/n)");
        String name = "";
        if (decision.equals("n")) {
            name = saveExternalKeyChain();
        } else {
            name = getInput("Name: ");
        }
        KeyChainReader externalKeyChain = new KeyChainReader(name);
        externalKeyChain.readKeyStringsFromFile();
        Encrypt newEncryption = new Encrypt((externalKeyChain.getPublicKey()).toString(),(externalKeyChain.getCommonN()).toString());
        String message = getInput("Give me the Message: ");
        System.out.println(newEncryption.encryptToBigInt(message));
    }

    private static String getInput(String input) {
        try {
            System.out.println(input);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void copyCurrentContactToExternalContact() {
        System.out.println("Setting External Key Chain ...");
        String name = getInput("Name: ");
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        KeyChainSaver newExternalKeyChain = new KeyChainSaver(ownKeyChain.getPublicKey(),ownKeyChain.getCommonN(),name);
        newExternalKeyChain.saveKeysToFile();
        System.out.println("Key Chain for '" + name + "' was set");

    }

    private static void decrypt() {
        System.out.println("Decryption Process");
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        ownKeyChain.readKeyStringsFromFile();
        BigInteger privateKey = ownKeyChain.getPrivateKey();
        BigInteger n = ownKeyChain.getCommonN();
        Decrypt newDecryption = new Decrypt(privateKey,n);
        String cypher = getInput("Give me the Cypher: ");
        System.out.println(newDecryption.decrypt(cypher));
    }
}