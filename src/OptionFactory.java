/**
 * Created by andreydelany on 03/02/2017.
 */
public class OptionFactory {
    public Option getOption(String optionAsString) {
        if (optionAsString.equals("set"))
            return new OptionOwnKeyChangeCreater();
        else if (optionAsString.equals("own"))
            return new OptionExportOwnKeyChange();
        else if (optionAsString.equals("ext"))
            return new OptionExternKeyChangeCreater();
        else if (optionAsString.equals("en"))
            return new OptionEncryption();
        else if (optionAsString.equals("test"))
            return new OptionTest();
        else if (optionAsString.equals("de"))
            return new OptionDecryption();
        else
            return null;
    }
}
