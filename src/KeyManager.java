import java.io.File;
import java.util.ArrayList;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class KeyManager {


    public static void printPossibleKeys() {
        ArrayList<String> names = getPossibleNames();
        String result = "Keys are there for: ";
        for(String currentName : names)
            result += currentName + ", ";
        System.out.println(result);
    }

    public static boolean isNameExisting(String input) {
        ArrayList<String> names = getPossibleNames();
        for(String currentName : names)
            if (input.equals(currentName))
                return true;
        return false;
    }

    private static ArrayList<String> getPossibleNames() {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String currentFileName = listOfFiles[i].getName();
                if(currentFileName.contains("KeyChain.txt")) {
                    String name = currentFileName.replace("KeyChain.txt", "");
                    if(!name.equals("own"))
                        names.add(name);
                }
            }
        }
        return names;
    }
}
