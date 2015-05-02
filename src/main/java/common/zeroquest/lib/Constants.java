package common.zeroquest.lib;

public class Constants {
	//Put some in ConfigHandler

	   public static boolean DEF_DARKLOAD								 	= false;
	   public static boolean DEF_HEALING								 	= false;
	   public static boolean DEF_SNOWSTEP								 	= true;
	   public static boolean DEF_GRASSSTEP								 	= true;
	   public static boolean DEF_HOWL								 		= true;
	   public static boolean IS_HUNGER_ON								 	= true;
	   public static boolean STARTING_ITEMS								 	= true;
	   public static final int maxLevel								 		= 120;
	   public static final int hungerTicks								 	= 100;
	   
	   public static final String name										= "Zero Quest";
	   public static final String channel									= "ZERO_QUEST";
	   public static final String modid										= "zero_quest";
	   public static final String clientProxy								= "common.zeroquest.core.proxy.ClientProxy";
	   public static final String serverProxy								= "common.zeroquest.core.proxy.CommonProxy";
	   public static final String guiFactory								= "common.zeroquest.client.gui.config.GuiFactory";
	   public static final String version									= "v1.6.0";
	   public static final String java										= "8";
	   public static final String releaseType								= "Release";
	   public static final boolean isBeta									= false;
}