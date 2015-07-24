package net.novaviper.zeroquest.common.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.IDs;
import net.novaviper.zeroquest.common.lib.Registers;

public class ConfigHandler {

	// Add more Categories for GuiFactory\\
	public static Configuration config;
	public static final String CATEGORY_LOAD = "load";
	public static final String CATEGORY_FUNCTION = "function";
	public static final String CATEGORY_MISC = "miscellaneous";
	public static final String CATEGORY_DIMENSION = "dimensions";
	public static final String CATEGORY_BIOME = "biomes";

	public static void init(File file) {
		config = new Configuration(file, Constants.configVersion);
		loadConfig();
		Registers.addFMLCommonEventBus(new ConfigEvent());
	}

	public static void loadConfig() {
		config.addCustomCategoryComment(CATEGORY_FUNCTION, "Here you can manage all mod and entity functions");
		config.addCustomCategoryComment(CATEGORY_LOAD, "Here you can manage what the mod loads into the game");
		config.addCustomCategoryComment(CATEGORY_MISC, "Here you can manage the miscellaneous functions of the mod");
		config.addCustomCategoryComment(CATEGORY_DIMENSION, "Here you can manage the mod's dimension factors");
		config.addCustomCategoryComment(CATEGORY_BIOME, "Here you can manage the mod's biome factors");

		//@formatter:off
		/*=================================Function Configurations==========================================*/
		//@formatter:on
		List<String> orderFunc = new ArrayList<String>();
		Constants.DEF_MODCHECKER = config.get(CATEGORY_FUNCTION, "ModCheck", Constants.DEF_MODCHECKER).setLanguageKey(Constants.guiKey + "ModCheck").getBoolean(Constants.DEF_MODCHECKER);
		orderFunc.add("ModCheck");
		Constants.DEF_HEALING = config.get(CATEGORY_FUNCTION, "Healing", Constants.DEF_HEALING).setLanguageKey(Constants.guiKey + "Healing").getBoolean(Constants.DEF_HEALING);
		orderFunc.add("Healing");
		Constants.DEF_SNOWSTEP = config.get(CATEGORY_FUNCTION, "SnowStep", Constants.DEF_SNOWSTEP).setLanguageKey(Constants.guiKey + "SnowStep").getBoolean(Constants.DEF_SNOWSTEP);
		orderFunc.add("SnowStep");
		Constants.DEF_GRASSSTEP = config.get(CATEGORY_FUNCTION, "GrassStep", Constants.DEF_GRASSSTEP).setLanguageKey(Constants.guiKey + "GrassStep").getBoolean(Constants.DEF_GRASSSTEP);
		orderFunc.add("GrassStep");

		config.setCategoryPropertyOrder(CATEGORY_FUNCTION, orderFunc);

		//@formatter:off
		/*=================================Load Configurations==========================================*/
		//@formatter:on
		List<String> orderLoad = new ArrayList<String>();
		Constants.DEF_DARKLOAD = config.get(CATEGORY_LOAD, "DarkElements", Constants.DEF_DARKLOAD).setLanguageKey(Constants.guiKey + "DarkElements").setRequiresMcRestart(true).getBoolean(Constants.DEF_DARKLOAD);
		orderLoad.add("DarkElements");
		Constants.DEF_STARTING_ITEMS = config.get(CATEGORY_LOAD, "StartingItems", Constants.DEF_STARTING_ITEMS).setLanguageKey(Constants.guiKey + "StartingItems").getBoolean(Constants.DEF_STARTING_ITEMS);
		orderLoad.add("StartingItems");

		config.setCategoryPropertyOrder(CATEGORY_LOAD, orderLoad);

		//@formatter:off
		/*=================================Miscellaneous Configurations==========================================*/
		//@formatter:on
		List<String> orderMisc = new ArrayList<String>();
		Constants.DEF_HOWL = config.get(CATEGORY_MISC, "Howl", Constants.DEF_HOWL).setLanguageKey(Constants.guiKey + "Howl").getBoolean(Constants.DEF_HOWL);
		orderMisc.add("Howl");
		Constants.DEF_IS_HUNGER_ON = config.get(CATEGORY_MISC, "HungerDecay", Constants.DEF_IS_HUNGER_ON).setLanguageKey(Constants.guiKey + "HungerDecay").getBoolean(Constants.DEF_IS_HUNGER_ON);
		orderMisc.add("HungerDecay");

		config.setCategoryPropertyOrder(CATEGORY_MISC, orderMisc);
		//@formatter:off
		/*=================================DimensionsConfigurations==========================================*/
		//@formatter:on
		List<String> orderDimensions = new ArrayList<String>();
		IDs.Nillax = config.get(CATEGORY_DIMENSION, "NillaxID", IDs.Nillax).setLanguageKey(Constants.guiKey + "NillaxID").setRequiresMcRestart(true).getInt(IDs.Nillax);
		orderDimensions.add("NillaxID");
		IDs.Darkax = config.get(CATEGORY_DIMENSION, "DarkaxID", IDs.Darkax).setLanguageKey(Constants.guiKey + "DarkaxID").setRequiresMcRestart(true).getInt(IDs.Darkax);
		orderDimensions.add("DarkaxID");

		config.setCategoryPropertyOrder(CATEGORY_DIMENSION, orderDimensions);

		//@formatter:off
		/*=================================BiomesConfigurations==========================================*/
		//@formatter:on
		List<String> orderBiomes = new ArrayList<String>();
		IDs.bioZone = config.get(CATEGORY_BIOME, "BioZoneID", IDs.bioZone).setLanguageKey(Constants.guiKey + "BioZoneID").setRequiresMcRestart(true).getInt(IDs.bioZone);
		orderBiomes.add("BioZoneID");
		IDs.redSeed = config.get(CATEGORY_BIOME, "RedSeedID", IDs.redSeed).setLanguageKey(Constants.guiKey + "RedSeedID").setRequiresMcRestart(true).getInt(IDs.redSeed);
		orderBiomes.add("RedSeedID");
		IDs.blueTaiga = config.get(CATEGORY_BIOME, "BlueTaigaID", IDs.blueTaiga).setLanguageKey(Constants.guiKey + "BlueTaigaID").setRequiresMcRestart(true).getInt(IDs.blueTaiga);
		orderBiomes.add("BlueTaigaID");
		IDs.blueTaigaHills = config.get(CATEGORY_BIOME, "BlueTaigaHillsID", IDs.blueTaigaHills).setLanguageKey(Constants.guiKey + "BlueTaigaHillsID").setRequiresMcRestart(true).getInt(IDs.blueTaigaHills);
		orderBiomes.add("BlueTaigaHillsID");
		IDs.blueColdTaiga = config.get(CATEGORY_BIOME, "BlueColdTaigaID", IDs.blueColdTaiga).setLanguageKey(Constants.guiKey + "BlueColdTaigaID").setRequiresMcRestart(true).getInt(IDs.blueColdTaiga);
		orderBiomes.add("BlueColdTaigaID");
		IDs.blueColdTaigaHills = config.get(CATEGORY_BIOME, "BlueColdTaigaHillsID", IDs.blueColdTaigaHills).setLanguageKey(Constants.guiKey + "BlueColdTaigaHillsID").setRequiresMcRestart(true).getInt(IDs.blueColdTaigaHills);
		orderBiomes.add("BlueColdTaigaHillsID");
		IDs.blueMegaTaiga = config.get(CATEGORY_BIOME, "BlueMegaTaigaID", IDs.blueMegaTaiga).setLanguageKey(Constants.guiKey + "BlueMegaTaigaID").setRequiresMcRestart(true).getInt(IDs.blueMegaTaiga);
		orderBiomes.add("BlueMegaTaigaID");
		IDs.blueMegaTaigaHills = config.get(CATEGORY_BIOME, "BlueMegaTaigaHillsID", IDs.blueMegaTaigaHills).setLanguageKey(Constants.guiKey + "BlueMegaTaigaHillsID").setRequiresMcRestart(true).getInt(IDs.blueMegaTaigaHills);
		orderBiomes.add("BlueMegaTaigaHillsID");
		IDs.pinkZone = config.get(CATEGORY_BIOME, "PinkZoneID", IDs.pinkZone).setLanguageKey(Constants.guiKey + "PinkZoneID").setRequiresMcRestart(true).getInt(IDs.pinkZone);
		orderBiomes.add("PinkZoneID");
		IDs.destroZone = config.get(CATEGORY_BIOME, "DestroZoneID", IDs.destroZone).setLanguageKey(Constants.guiKey + "DestroZoneID").setRequiresMcRestart(true).getInt(IDs.destroZone);
		orderBiomes.add("DestroZoneID");
		IDs.destroZoneHills = config.get(CATEGORY_BIOME, "DestroZoneHillsID", IDs.destroZoneHills).setLanguageKey(Constants.guiKey + "DestroZoneHillsID").setRequiresMcRestart(true).getInt(IDs.destroZoneHills);
		orderBiomes.add("DestroZoneHillsID");
		IDs.walRockland = config.get(CATEGORY_BIOME, "WalRocklandID", IDs.walRockland).setLanguageKey(Constants.guiKey + "WalRocklandID").setRequiresMcRestart(true).getInt(IDs.walRockland);
		orderBiomes.add("WalRocklandID");
		IDs.nileSavanna = config.get(CATEGORY_BIOME, "NileSavannaID", IDs.nileSavanna).setLanguageKey(Constants.guiKey + "NileSavannaID").setRequiresMcRestart(true).getInt(IDs.nileSavanna);
		orderBiomes.add("NileSavannaID");
		IDs.nileSavannaPlateau = config.get(CATEGORY_BIOME, "NileSavannaPlateauID", IDs.nileSavannaPlateau).setLanguageKey(Constants.guiKey + "NileSavannaPlateauID").setRequiresMcRestart(true).getInt(IDs.nileSavannaPlateau);
		orderBiomes.add("NileSavannaPlateauID");
		IDs.nileJungle = config.get(CATEGORY_BIOME, "NileJungleID", IDs.nileJungle).setLanguageKey(Constants.guiKey + "NileJungleID").setRequiresMcRestart(true).getInt(IDs.nileJungle);
		orderBiomes.add("NileJungleID");
		IDs.nileJungleHills = config.get(CATEGORY_BIOME, "NileJungleHillsID", IDs.nileJungleHills).setLanguageKey(Constants.guiKey + "NileJungleHillsID").setRequiresMcRestart(true).getInt(IDs.nileJungleHills);
		orderBiomes.add("NileJungleHillsID");
		IDs.nileJungleEdge = config.get(CATEGORY_BIOME, "NileJungleEdgeID", IDs.nileJungleEdge).setLanguageKey(Constants.guiKey + "NileJungleEdgeID").setRequiresMcRestart(true).getInt(IDs.nileJungleEdge);
		orderBiomes.add("NileJungleEdgeID");
		IDs.nileSwampland = config.get(CATEGORY_BIOME, "NileSwamplandID", IDs.nileSwampland).setLanguageKey(Constants.guiKey + "NileSwamplandID").setRequiresMcRestart(true).getInt(IDs.nileSwampland);
		orderBiomes.add("NileSwamplandID");
		IDs.nileMesa = config.get(CATEGORY_BIOME, "NileMesaID", IDs.nileMesa).setLanguageKey(Constants.guiKey + "NileMesaID").setRequiresMcRestart(true).getInt(IDs.nileMesa);
		orderBiomes.add("NileMesaID");
		IDs.nileMesaPlateau_F = config.get(CATEGORY_BIOME, "NileMesaPlateauFID", IDs.nileMesaPlateau_F).setLanguageKey(Constants.guiKey + "NileMesaPlateauFID").setRequiresMcRestart(true).getInt(IDs.nileMesaPlateau_F);
		orderBiomes.add("NileMesaPlateauFID");
		IDs.nileMesaPlateau = config.get(CATEGORY_BIOME, "NileMesaPlateauID", IDs.nileMesaPlateau).setLanguageKey(Constants.guiKey + "NileMesaPlateauID").setRequiresMcRestart(true).getInt(IDs.nileMesaPlateau);
		orderBiomes.add("NileMesaPlateauID");
		IDs.nileMountains = config.get(CATEGORY_BIOME, "NileMountainsID", IDs.nileMountains).setLanguageKey(Constants.guiKey + "NileMountainsID").setRequiresMcRestart(true).getInt(IDs.nileMountains);
		orderBiomes.add("NileMountainsID");
		IDs.nileMountainsEdge = config.get(CATEGORY_BIOME, "NileMountainsEdgeID", IDs.nileMountainsEdge).setLanguageKey(Constants.guiKey + "NileMountainsEdgeID").setRequiresMcRestart(true).getInt(IDs.nileMountainsEdge);
		orderBiomes.add("NileMountainsEdgeID");
		IDs.nileMountainsPlus = config.get(CATEGORY_BIOME, "NileMountainsPlusID", IDs.nileMountainsPlus).setLanguageKey(Constants.guiKey + "NileMountainsPlusID").setRequiresMcRestart(true).getInt(IDs.nileMountainsPlus);
		orderBiomes.add("NileMountainsPlusID");
		IDs.darkWasteland = config.get(CATEGORY_BIOME, "DarkaxBiomeID", IDs.darkWasteland).setLanguageKey(Constants.guiKey + "DarkaxBiomeID").setRequiresMcRestart(true).getInt(IDs.darkWasteland);
		orderBiomes.add("DarkaxBiomeID");

		config.setCategoryPropertyOrder(CATEGORY_BIOME, orderBiomes);

		if (config.hasChanged()) {
			config.save();
		}
	}
}