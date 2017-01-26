import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Main {
	
	public static final String ALGORITHM = "RSA";
	public static final String PRIVATE_KEY_FILE = "private.key";
	public static final String PUBLIC_KEY_FILE = "public.key";

	public static byte[] encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	public static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(ALGORITHM);

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new String(dectyptedText);
	}


	@SuppressWarnings("resource")
	public static void main(String[] args) {

		String[] test = {"public.key", "public2.key", "public3.key"};
		KeyGenerator keyGen = new KeyGenerator();
		KeyReader keyReader = new KeyReader(test);
		keyReader.checkKeyClasses();
		try {
			// Check if the pair of keys are present else generate those.
			if (!keyGen.areKeysPresent()) {
				// Method generates a pair of keys using the RSA algorithm and stores it
				// in their respective files
				keyGen.generateKey();
			}

			final String originalText = "Text to be encrypted ";
			ObjectInputStream inputStream = null;

			// Encrypt the string using the public key
			inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			final byte[] cipherText = encrypt(originalText, publicKey);

			// Decrypt the cipher text using the private key.
			inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			final String plainText = decrypt(cipherText, privateKey);

			// Printing the Original, Encrypted and Decrypted Text
			System.out.println("Original: " + originalText);
			System.out.println("Encrypted: " + cipherText.toString());
			System.out.println("Decrypted: " + plainText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
