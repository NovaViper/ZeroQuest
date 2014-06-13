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
   
   private File configFile;
   private Configuration config;
   
   // config properties
   private Property DarkElementLoad;
   private Property DarkParticlesLoad;
   
   public ConfigurationHandler(File configFile) {
       this.configFile = configFile;
       load();
       save();
   }
   
   public void load() {
       try {
           if (config == null) {
               config = new Configuration(configFile);
           } else {
               config.load();
           }
       } catch (Exception ex) {
           L.log(Level.WARNING, "Can't load config file" , ex);
       }
       
       try {
    	   DarkElementLoad = config.get("server", "DarkElements", Constants.DEF_DARKLOAD, "Toggles the Dark Elemental Load");
    	   DarkParticlesLoad = config.get("server", "DarkParticles", Constants.DEF_DPARTICLESLOAD, "Toggles the Dark Particles Load");
       } catch (Exception ex) {
           L.log(Level.WARNING, "Can't load config file", ex);
       }
   }
   
   public void save() {
       try {
           if (config.hasChanged()) {
               config.save();
           }
       } catch (Exception ex) {
           L.log(Level.WARNING, "Can't save config file", ex);
       }
   }
   
   public boolean canLoadDarkElements() {
       return DarkElementLoad.getBoolean(Constants.DEF_DARKLOAD);
   }
   
   public void setLoadDarkElementsOn(boolean enabled) {
	   DarkElementLoad.set(enabled);
   }
   
   public boolean canLoadDarkParticles() {
       return DarkParticlesLoad.getBoolean(Constants.DEF_DPARTICLESLOAD);
   }
   
   public void setLoadDarkParticlesOn(boolean enabled) {
	   DarkElementLoad.set(enabled);
   }
}
