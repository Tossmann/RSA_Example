import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by andreydelany on 02/02/2017.
 */
public class MITPGPKeyGrabber {

    private static String readKeyFromURL(String url) throws Exception {
        URL keyURL = new URL("https://pgp.mit.edu/pks/lookup?op=get&search=" + url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(keyURL.openStream()));
        String result = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            result += inputLine;
        in.close();
        return result;
    }

    private static String splitAnswer(String answerFromWebsite) {
        String startOfBlock = "pgp.mit.edu";
        String endOfBlock = "-----END PGP PUBLIC KEY BLOCK-----";
        int startIndex = answerFromWebsite.indexOf(startOfBlock) + startOfBlock.length();
        int endIndex = answerFromWebsite.indexOf(endOfBlock);
        return answerFromWebsite.substring(startIndex,endIndex);
    }

    public static String getKey (String url) throws Exception{
        System.out.println("... processing ...");
        String unsplittedAnswer = readKeyFromURL(url);
        String pgpKey = splitAnswer(unsplittedAnswer);
        return pgpKey;
    }
}
