import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class KeyReader {

	private HashMap<PublicKey, String> keys = new HashMap<>();
	private String directoryPath = "PublicKeys";

	public KeyReader(String[] keyNames){
		System.out.println("Start to read in the PublicKeys");
		ObjectInputStream inputStream = null;
		try{
			for(int i = 0; i < keyNames.length; i++){
				String filePath = directoryPath + "\\" + keyNames[i];
				inputStream = new ObjectInputStream(new FileInputStream(filePath));
				final PublicKey publicKey = (PublicKey) inputStream.readObject();
				keys.put(publicKey, keyNames[i]);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("There are " + this.getKeyAmount() + " key(s) stored");
	}

	public int getKeyAmount(){
		return 	keys.size();
	}

	public void checkKeyClasses(){
		Iterator it = keys.entrySet().iterator();
		String[] errorKeys = new String[keys.size()];
		int index = 0;

		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			PublicKey actualKey = (PublicKey) pair.getKey();
			if((actualKey.getClass().toString().equals("class sun.security.rsa.RSAPublicKeyImpl"))){
				errorKeys[index] = (String) pair.getValue();
				index++;
			}
		}
		if(index > 0) System.out.println("There are problems with the following keys");
		for(int i = 0; i < errorKeys.length; i++){
			if(errorKeys[i] != null) System.out.println(errorKeys[i]);
		}
	}


}
