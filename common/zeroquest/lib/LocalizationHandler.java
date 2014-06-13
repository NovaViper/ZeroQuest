package common.zeroquest.lib;



import java.io.BufferedReader;
import java.io.InputStreamReader;

import common.zeroquest.ZeroQuest;
import cpw.mods.fml.common.registry.LanguageRegistry;



/**
* @author ProPercivalalb
*/
public class LocalizationHandler {

    /***
* Loads in all the Localization files.
*/
    public static void loadLanguages() {
        //For every file specified, load them into the Language Registry
        for (Localizations localization : Localizations.values()) {
         String localizationFile = Localizations.LANG_RESOURCE_LOCATION + localization.getFile();
            try {
            BufferedReader paramReader = new BufferedReader(new InputStreamReader(ZeroQuest.class.getResourceAsStream(localizationFile)));
            		String line = "";
            		while((line = paramReader.readLine()) != null) {
            			if(!line.isEmpty() && !line.startsWith("#")) {
            				String[] split = line.split("=");
            				String inGameName = split[1];
            				int count = 2;
            				while(split.length > count) {
            						inGameName += split[count] + (split.length - 1 != count ? "=" : "");
            						++count;
            				}
            				LanguageRegistry.instance().addStringLocalization(split[0], LocalizationHelper.getLocaleFromFileName(localizationFile), inGameName);
            			}
            		}
            }
            catch(Exception e) {
             e.printStackTrace();
            }
        }
    }
}