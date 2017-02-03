import java.io.*;
import java.util.ArrayList;

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
                    runOption(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static boolean runOption(String inputRaw) {
            String input = inputRaw.toLowerCase();
            OptionFactory optionFactory = new OptionFactory();
            Option choosedOption = optionFactory.getOption(inputRaw);
            if (choosedOption == null)
                return false;
            choosedOption.run();
            return true;
        }

/*

    private static void copyCurrentContactToExternalContact() {
        System.out.println("Setting External Key Chain ...");
        String name = getInput("Name: ");
        KeyChainReader ownKeyChain = new KeyChainReader("own");
        KeyChainSaver newExternalKeyChain = new KeyChainSaver(ownKeyChain.getPublicKey(),ownKeyChain.getCommonN(),name);
        newExternalKeyChain.saveKeysToFile();
        System.out.println("Key Chain for '" + name + "' was set");

    }
    */
}