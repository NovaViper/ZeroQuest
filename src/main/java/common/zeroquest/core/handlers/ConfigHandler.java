package common.zeroquest.core.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;

public class ConfigHandler {
	
		//Add more Categories for GuiFactory\\
	   public static Configuration config;
	   public static final String CATEGORY_LOAD = "load";
	   public static final String CATEGORY_FUNCTION = "function";
	   public static final String CATEGORY_MISC = "miscellaneous";
	   public static final String CATEGORY_TERRAIN = "terrain";
	   
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
	   config.addCustomCategoryComment(CATEGORY_MISC, "Here you can manage the miscellaneous functions of the mod");
	   
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
           
	   	   /*prop = config.get(CATEGORY_FUNCTION, "isHungerOn", true);
   		   prop.setLanguageKey("gui.config.property.isHungerOn");
   		   Constants.IS_HUNGER_ON =  prop.getBoolean(Constants.IS_HUNGER_ON);
           propOrder.add(prop.getName());*/
           
	   	   prop = config.get(CATEGORY_FUNCTION, "SnowStep", true);
	   	   prop.comment = "Toggles the snow/ice footprints of Ice Zertums";
   		   prop.setLanguageKey("gui.config.property.SnowStep");
   		   Constants.DEF_SNOWSTEP =  prop.getBoolean(Constants.DEF_SNOWSTEP);
           propOrder.add(prop.getName());
           
	   	   prop = config.get(CATEGORY_FUNCTION, "GrassStep", true);
	   	   prop.comment = "Toggles the grass footprints of Foris Zertums";
   		   prop.setLanguageKey("gui.config.property.GrassStep");
   		   Constants.DEF_GRASSSTEP =  prop.getBoolean(Constants.DEF_GRASSSTEP);
           propOrder.add(prop.getName());
           
   		   /*=================================Miscellaneous Configurations==========================================*/
	   	   prop = config.get(CATEGORY_MISC, "Howl", true);
	   	   prop.comment = "Toggles the howling function on Zertums";
   		   prop.setLanguageKey("gui.config.property.Howl");
   		   Constants.DEF_HOWL =  prop.getBoolean(Constants.DEF_HOWL);
           propOrder.add(prop.getName());
   		   /*=================================Terrain Configurations==========================================*/


    	   if(config.hasChanged()){
    		   config.save();
    	   }
   } 
}