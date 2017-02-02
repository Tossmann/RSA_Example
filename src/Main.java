import java.io.*;

public class Main {

	public static void main(String[] args){
        gui();
	}

	private static void gui() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("\n(Set)KeyPair, (En)cryption Or (De)crpytion: ");
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
        if (input.equals("set") || input.equals("en") || input.equals("de"))
            return true;
        return false;
    }

    private static void runChoosedOption(String input) {
        if (input.equals("set"))
            setNewKeyPair();
        else if (input.equals("en"))
            encrypt();
        else
            decrypt();
    }

    private static void setNewKeyPair() {
        System.out.println("Set new Key ...");
        RSAKeyGenerator newRSAKey = new RSAKeyGenerator();
        System.out.println("private: " + newRSAKey.getPrivateKey());
        System.out.println("public: " + newRSAKey.getPublicKey());
        System.out.println("common N: " + newRSAKey.getN());
    }

    private static void encrypt() {
        System.out.println("Encryption Process ...");
        String recipient = getInput("Give me the Public Key: ");
        String n = getInput("Give me the Public N: ");
        Encrypt newEncryption = new Encrypt(recipient,n);
        String message = getInput("Give me the Message: ");
        System.out.println(newEncryption.encryptToBigInt(message));
        System.out.println(newEncryption.encryptToString(message));
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

    private static void decrypt() {
        System.out.println("Decryption Process");
        String privateKey = getInput("Give me your Private Key: ");
        String n = getInput("Give me the Public N: ");
        Decrypt newDecryption = new Decrypt(privateKey,n);
        String cypher = getInput("Give me the Cypher: ");
        System.out.println(newDecryption.decrypt(cypher));
    }
}