package common.zeroquest.lib;

//@formatter:off
public class Constants {

	//Mod Details\\
	public static final String name										= "Zero Quest";
	public static final String channel									= "ZERO_QUEST";
	public static final String modid									= "zero_quest";
	public static final String version									= "v1.8.0";
	public static final String configVersion							= "1.0";
	public static final String java										= "8";
	public static final String releaseType								= "Release";
	public static final boolean isBeta									= false;

	//Configurations --- Put in ConfigHandler\\
	public static boolean DEF_DARKLOAD								 	= false;
	public static boolean DEF_HEALING								 	= false;
	public static boolean DEF_SNOWSTEP								 	= true;
	public static boolean DEF_GRASSSTEP								 	= true;
	public static boolean DEF_HOWL								 		= true;
	public static boolean DEF_IS_HUNGER_ON								= true;
	public static boolean DEF_STARTING_ITEMS							= true;
	public static boolean DEF_MODCHECKER								= true;

	//Mod Elements\\
	public static final String clientProxy								= "common.zeroquest.core.proxy.ClientProxy";
	public static final String serverProxy								= "common.zeroquest.core.proxy.CommonProxy";
	public static final String guiFactory								= "common.zeroquest.client.gui.config.GuiFactory";
	public static final String keyCategory								= "key.categories.zeroquest";
	public static final String keyDesc									= "zeroquest.key.";
	public static final String guiKey									= "gui.config.property.";

	//Values\\
	public static final int maxLevel								 	= 140;
	public static final int stage2Level								 	= 120;
	public static final int stage3Level							 		= 140;
	public static final int hungerTicks								 	= 100;
	public static final int startingPoints							 	= 20;
	public static final int lowHP 										= 10;
	public static final int lowPlayerHP 								= 4;
}