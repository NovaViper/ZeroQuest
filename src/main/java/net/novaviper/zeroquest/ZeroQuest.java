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
import net.novaviper.zeroquest.common.*;
import net.novaviper.zeroquest.common.api.ZeroQuestAPI;
import net.novaviper.zeroquest.common.command.*;
import net.novaviper.zeroquest.common.config.*;
import net.novaviper.zeroquest.common.creativetab.*;
import net.novaviper.zeroquest.common.events.*;
import net.novaviper.zeroquest.common.handlers.*;
import net.novaviper.zeroquest.common.helper.*;
import net.novaviper.zeroquest.common.lib.*;
import net.novaviper.zeroquest.common.message.*;
import net.novaviper.zeroquest.common.world.*;
import net.novaviper.zeroquest.common.world.gen.*;

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
		ModVersionCheck.startVersionCheck();
		LogHelper.info("-----PRE-CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Main Stuff...");
		this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
		if (Constants.DEF_DARKLOAD == true) {
			this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
		}
		Registers.addGuiHandler(Constants.modid, proxy);
		proxy.registerMoreThings();
		LogHelper.info("Load Stuff Loaded Successfully!");
		LogHelper.info("Loading Block, Recipes, Events and Items...");
		nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
		ModBlocks.load();
		ModItems.load();
		ModEntities.loadCreatures();
		ModEntities.loadOthers();
		proxy.registerStateMappings();
		Registers.addForgeEventBus(new FOVEvent());
		Registers.addForgeEventBus(new BlastResistanceEvent());
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
		Registers.addCommand(new CommandAdmin());
		LogHelper.info("Commands Loaded Successfully!");
		LogHelper.info("-----SERVER CONTENT LOAD FINSHED-----");

	}
}