package common.zeroquest.handlers;

import java.util.logging.Level;

import common.zeroquest.ModBiomes;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.LogHelper;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {

	public static Configuration configuration;


	public static void loadConfig(Configuration config) {
		configuration = config;
		try
		{
			config.load();

			config.addCustomCategoryComment("LoadSettings", "Here you can change what the mod loads.");
			Constants.DarkLoadOn = config.get("LoadSettings", "DarkLoad", true, "Toggles Dark Elemental Load").getBoolean(true);
			Constants.DarkParticlesLoadOn = config.get("LoadSettings", "DarkParticlesLoad", true, "Toggles Dark Particles").getBoolean(true);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, "Zero QUest has encountered a problem loading its configuration!!", e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	 }
}