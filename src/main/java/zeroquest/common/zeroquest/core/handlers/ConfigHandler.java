package common.zeroquest.core.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
*
* @author Nico Bergemann <barracuda415 at yahoo.de>
*/
public class ConfigHandler {
	
	   private static final Logger L = ZeroQuest.Log;
	   public static Configuration config;
	   public static final String CATEGORY_LOAD = "load";
	   public static final String CATEGORY_FUNCTION = "function";
	   public static final String CATEGORY_MISC = "miscellaneous";
	   
	   public static void init(File file)
	   {	   
		   config = new Configuration(file);
		   
		   loadConfig();
	   }
   
   public static void loadConfig()
   {
       List<String> propOrder = new ArrayList<String>();
       Property prop;
	   
	   config.addCustomCategoryComment(CATEGORY_FUNCTION, "Here you can manage all mod and entity functions");
	   config.addCustomCategoryComment(CATEGORY_LOAD, "Here you can manage what the mod loads into the game");
	   
		   /*=================================Load Configurations==========================================*/
	   	   prop = config.get(CATEGORY_LOAD, "DarkElements", false);
	   	   prop.comment = "Toggles the Dark Elemental Load";
   		   prop.setLanguageKey("gui.config.property.DarkElements").setRequiresMcRestart(true);
   		   Constants.DEF_DARKLOAD =  prop.getBoolean(Constants.DEF_DARKLOAD);
           propOrder.add(prop.getName());

   		   /*=================================Function Configurations==========================================*/
	   	   prop = config.get(CATEGORY_FUNCTION, "Healing", false);
	   	   prop.comment = "Toggles the healing ability of ALL ZQ tameable creatures";
   		   prop.setLanguageKey("gui.config.property.Healing");
   		   Constants.DEF_HEALING =  prop.getBoolean(Constants.DEF_HEALING);
           propOrder.add(prop.getName());

    	   if(config.hasChanged()){
    		   config.save();
    	   }
   } 
}