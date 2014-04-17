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
		Constants.DarkLoadOff = config.get("LoadSettings", "DarkLoad", false, "Toggles Dark Elemental Load").getBoolean(false);

		config.save();
	 }
}