package net.novaviper.zeroquest;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.novaviper.zeroquest.common.CommonProxy;
import net.novaviper.zeroquest.common.api.ZeroQuestAPI;
import net.novaviper.zeroquest.common.command.CommandZeroQuest;
import net.novaviper.zeroquest.common.config.ConfigEvent;
import net.novaviper.zeroquest.common.config.ConfigHandler;
import net.novaviper.zeroquest.common.creativetab.DarkQuestTab;
import net.novaviper.zeroquest.common.creativetab.ZeroQuestTab;
import net.novaviper.zeroquest.common.events.AchievementEvents;
import net.novaviper.zeroquest.common.events.BlastResistanceEvent;
import net.novaviper.zeroquest.common.events.FOVEvent;
import net.novaviper.zeroquest.common.events.FireEventHandler;
import net.novaviper.zeroquest.common.handlers.ConnectionHandler;
import net.novaviper.zeroquest.common.handlers.FuelHandler;
import net.novaviper.zeroquest.common.helper.LogHelper;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.IDs;
import net.novaviper.zeroquest.common.lib.OreDic;
import net.novaviper.zeroquest.common.lib.Registers;
import net.novaviper.zeroquest.common.message.PacketHandler;
import net.novaviper.zeroquest.common.world.WorldProviderDarkax;
import net.novaviper.zeroquest.common.world.WorldProviderNillax;
import net.novaviper.zeroquest.common.world.gen.WorldGenZQuest;

@Mod(modid = Constants.modid, name = Constants.name, version = Constants.version, useMetadata = true, guiFactory = Constants.guiFactory)
public class ZeroQuest {
	@Instance(value = Constants.modid)
	public static ZeroQuest instance;

	@SidedProxy(clientSide = Constants.clientProxy, serverSide = Constants.serverProxy)
	public static CommonProxy proxy;

	// Put sounds from Sound in sounds.json

	public static CreativeTabs ZeroTab;
	public static CreativeTabs DarkTab;

	public static ToolMaterial nileEssence;
	public static ToolMaterial darkEssence;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Constants.modid + File.separator + Constants.modid + ".cfg"));
		if (Constants.DEF_MODCHECKER == true) {
			Registers.addFMLCommonEventBus(new ModVersionCheck(Constants.version, "ZeroQuest", Constants.isBeta));
		}
		LogHelper.info("-----PRE-CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Main Stuff...");
		this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
		if (Constants.DEF_DARKLOAD == true) {
			this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
		}
		Registers.addGuiHandler(Constants.modid, proxy);
		proxy.registerMoreThings();
		LogHelper.info("Load Stuff Loaded Successfully!");
		LogHelper.info("Loading Block, Liquids, Recipes, Events and Items...");
		nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
		ModBlocks.load();
		ModItems.load();
		ModEntities.loadCreatures();
		ModEntities.loadOthers();
		proxy.registerStateMappings();
		Registers.addForgeEventBus(new FOVEvent());
		Registers.addForgeEventBus(new BlastResistanceEvent());
		Registers.addFMLCommonEventBus(new ConfigEvent());
		ZeroQuestCrafting.loadRecipes();
		if (Constants.DEF_DARKLOAD == true) {
			darkEssence = EnumHelper.addToolMaterial("DarkEssence", 4, 5000, 21.0F, 5.0F, 40);
			ModBlocks.loadDarkBlocks();
			ModItems.loadDarkItems();
			ZeroQuestCrafting.loadDarkRecipes();
			ModEntities.loadDarkCreatures();
			proxy.registerStateMappingsForDark();
		}

		Registers.addFMLCommonEventBus(new ConnectionHandler());
		LogHelper.info("Blocks, Recipes, Entites, Events and Items Loaded Successfully!");
		LogHelper.info("-----PRE-CONTENT LOAD FINISHED-----");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		LogHelper.info("-----CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Packets...");
		PacketHandler.registerPackets();
		LogHelper.info("Packets Loaded Successfully!");
		LogHelper.info("Loading Renderers, Achievements, Biomes and Ore Dictionary...");
		OreDic.loadOre();
		ModAchievements.load();
		Registers.addFMLCommonEventBus(new AchievementEvents());
		ModBiomes.loadBiomes();
		if (event.getSide().isClient()) {
			Registers.addForgeEventBus(new FireEventHandler());
			ModBlocks.loadRenderers();
			ModItems.loadRenderers();
			if (Constants.DEF_DARKLOAD == true) {
				ModItems.loadDarkRenderers();
				ModBlocks.loadDarkRenderers();
			}
		}
		proxy.registerRenderThings();
		LogHelper.info("Renderers, Achievements, Biomes and Ore Dictionary Loaded Successfully!");
		LogHelper.info("Loading Crucial Stuff and Dimensions...");
		proxy.registerChestItems();
		Registers.addFuelHandler(new FuelHandler());
		Registers.addWorldGenerator(new WorldGenZQuest(), 0);
		ModEntities.loadSpawns();
		Registers.addDimensionProviderType(IDs.Nillax, WorldProviderNillax.class, false);
		Registers.addDimension(IDs.Nillax, IDs.Nillax);
		LogHelper.info("Crucial Stuff and Dimensions Loaded Successfully!");

		if (Constants.DEF_DARKLOAD == true) {
			LogHelper.warn("Dark Elemental Load is ENABLED!");
			LogHelper.info("Initating Dark Elemental Load!");
			ModBiomes.loadDarkBiomes();
			ModAchievements.loadDark();
			OreDic.loadDarkOre();
			Registers.addDimensionProviderType(IDs.Darkax, WorldProviderDarkax.class, false);
			Registers.addDimension(IDs.Darkax, IDs.Darkax);
			ModEntities.loadDarkSpawns();
			LogHelper.info("Dark Elements Loaded Successfully!");
		}
		else {
			LogHelper.warn("Dark Elemental Load is not ENABLED! Change configurations to enable!");
			LogHelper.info("Skipping Dark Elemental Load");

		}
		LogHelper.info("-----CONTENT LOAD FINSHED-----");
	}

	@EventHandler
	public void PostInt(FMLPostInitializationEvent event) {
		LogHelper.info("-----POST-CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Talents and Item Lists...");
		ZeroQuestAPI.breedList.registerItem(ModItems.dogTreat);
		ZeroQuestAPI.begNileList.registerItem(ModItems.toy);
		ZeroQuestAPI.begNileList.registerItem(ModItems.nileBone);
		ZeroQuestAPI.begNileList.registerItem(ModItems.toy);
		if (Constants.DEF_DARKLOAD == true) {
			ZeroQuestAPI.begDarkList.registerItem(ModItems.darkBone);
		}
		ModTalents.loadTalents();
		LogHelper.info("Talents and Item Lists Loaded Successfully!");
		LogHelper.info("-----POST-CONTENT LOAD FINSHED-----");

	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		LogHelper.info("-----SERVER CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Commands...");
		Registers.addCommand(new CommandZeroQuest());
		LogHelper.info("Commands Loaded Successfully!");
		LogHelper.info("-----SERVER CONTENT LOAD FINSHED-----");

	}
}