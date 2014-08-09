package common.zeroquest.handlers;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.zeroquest.ModBiomes;
import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.LogHelper;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
*
* @author Nico Bergemann <barracuda415 at yahoo.de>
*/
public class ConfigurationHandler {
   
   private static final Logger L = ZeroQuest.L;
   
   private Configuration config;
   
   public ConfigurationHandler(File file) {
       this.config = new Configuration(file);

       try{
    	   config.load();
    	   
   		   config.addCustomCategoryComment("ModSettings", "Here you can change what the mod loads");
   		   
   		   Constants.DEF_DARKLOAD = config.get("ModSettings", "DarkElements", false, "Toggles the Dark Elemental Load").getBoolean(false);
   		   Constants.DEF_DPARTICLESLOAD = config.get("ModSettings", "DarkParticles", true, "Toggles the Dark Particles Load").getBoolean(true);
   		   Constants.DEF_HEALING = config.get("ModSettings", "Healing", false, "Toggles the Healing ability of ALL Zero Quest tameable creatures").getBoolean(false);
    	   
       }
       catch(Exception e){
    	   L.log(Level.SEVERE, "Zero Quest encounted a problem while loading the config file: ", e);
       }
       finally{
    	   config.save();
       }
       
   }

}