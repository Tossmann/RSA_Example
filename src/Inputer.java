import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by andreydelany on 03/02/2017.
 */
public class Inputer {

    public static String getInput(String input,String vorgabe) {
        try {
            System.out.println(input);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String currentInput = "";
            while(!isInputNotCorrect(vorgabe,currentInput)) {
                currentInput = br.readLine();
            }
            return currentInput;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isInputNotCorrect(String vorgabe, String input) {
        if (input.length()== 0)
            return false;

        if (vorgabe.equals("integer")){
            if (input.matches("[0-9]+"))
                return true;
            else
                return false;
        }

        if (vorgabe.equals("yesno")){
            if(input.equals("y")||input.equals("n"))
                return true;
            else
                return false;
        }

        if (vorgabe.equals("name")){
            if (KeyManager.isNameExisting(input)) {
                return true;
            } else {
                System.out.println("Such Entry does not exists. Try again ...");
                return false;
            }
        }
        return true;
    }

}
