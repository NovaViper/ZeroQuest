package common.zeroquest.handlers;

import common.zeroquest.ModBiomes;
import common.zeroquest.lib.Constants;
import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {

	public static Configuration configuration;


	public static void loadConfig(Configuration config) {
		config.load();
		configuration = config;
		config.addCustomCategoryComment("LoadSettings", "Here you can change what the mod loads.");
		Constants.DarkLoadOn = config.get("LoadSettings", "DarkLoad", true, "Toggles Dark Elemental Load").getBoolean(true);
		Constants.DarkParticlesLoadOn = config.get("LoadSettings", "DarkParticlesLoad", true, "Toggles Dark Particles").getBoolean(true);
		config.save();
	 }
}