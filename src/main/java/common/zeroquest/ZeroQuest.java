package common.zeroquest;

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

import common.zeroquest.api.ZeroQuestAPI;
import common.zeroquest.command.CommandZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;
import common.zeroquest.core.handlers.ConnectionHandler;
import common.zeroquest.core.handlers.FuelHandler;
import common.zeroquest.core.helper.LogHelper;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.creativetab.DarkQuestTab;
import common.zeroquest.creativetab.ZeroQuestTab;
import common.zeroquest.events.AchievementEvents;
import common.zeroquest.events.BlastResistanceEvent;
import common.zeroquest.events.ConfigEvent;
import common.zeroquest.events.FOVEvent;
import common.zeroquest.events.FireEventHandler;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.IDs;
import common.zeroquest.lib.Registers;
import common.zeroquest.lib.OreDic;
import common.zeroquest.network.PacketHandler;
import common.zeroquest.world.WorldProviderDarkax;
import common.zeroquest.world.WorldProviderNillax;
import common.zeroquest.world.gen.WorldGenZQuest;

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
		Registers.registerFMLCommonEventBus(new ConfigEvent());
		if (Constants.DEF_MODCHECKER == true) {
			Registers.registerFMLCommonEventBus(new ModVersionCheck(Constants.version, "ZeroQuest", Constants.isBeta));
		}
		LogHelper.info("-----PRE-CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Main Stuff...");
		this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
		if (Constants.DEF_DARKLOAD == true) {
			this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
		}
		Registers.registerGuiHandler(Constants.modid, proxy);
		proxy.registerMoreThings();
		LogHelper.info("Load Stuff Loaded Successfully!");
		LogHelper.info("Loading Block, Liquids, Recipes, Events and Items...");
		nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
		ModBlocks.load();
		ModItems.load();
		ModLiquids.load();
		ModEntities.loadCreatures();
		ModEntities.loadOthers();
		proxy.registerStateMappings();
		Registers.registerForgeEventBus(new FOVEvent());
		Registers.registerForgeEventBus(new BlastResistanceEvent());
		Registers.registerFMLCommonEventBus(new ConfigEvent());
		ZeroQuestCrafting.loadRecipes();
		if (Constants.DEF_DARKLOAD == true) {
			darkEssence = EnumHelper.addToolMaterial("DarkEssence", 4, 5000, 21.0F, 5.0F, 40);
			ModBlocks.loadDarkBlocks();
			ModItems.loadDarkItems();
			ZeroQuestCrafting.loadDarkRecipes();
			ModEntities.loadDarkCreatures();
			proxy.registerStateMappingsForDark();
		}

		Registers.registerFMLCommonEventBus(new ConnectionHandler());
		LogHelper.info("Blocks, Recipes, Entites, Events and Items Loaded Successfully!");
		LogHelper.info("-----PRE-CONTENT LOAD FINISHED-----");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		LogHelper.info("-----CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Network Packets...");
		PacketHandler.registerPackets();
		LogHelper.info("Network Packets Loaded Successfully!");
		LogHelper.info("Loading Renderers, Achievements, Biomes and Ore Dictionary...");
		OreDic.loadOre();
		ModAchievements.load();
		Registers.registerFMLCommonEventBus(new AchievementEvents());
		ModBiomes.loadBiomes();
		if (event.getSide().isClient()) {
			Registers.registerForgeEventBus(new FireEventHandler());
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
		Registers.registerFuelHandler(new FuelHandler());
		Registers.registerWorldGenerator(new WorldGenZQuest(), 0);
		ModEntities.loadSpawns();
		Registers.registerProviderType(IDs.Nillax, WorldProviderNillax.class, false);
		Registers.registerDimension(IDs.Nillax, IDs.Nillax);
		LogHelper.info("Crucial Stuff and Dimensions Loaded Successfully!");

		if (Constants.DEF_DARKLOAD == true) {
			LogHelper.warn("Dark Elemental Load is ENABLED!");
			LogHelper.info("Initating Dark Elemental Load!");
			ModBiomes.loadDarkBiomes();
			ModAchievements.loadDark();
			OreDic.loadDarkOre();
			Registers.registerProviderType(IDs.Darkax, WorldProviderDarkax.class, false);
			Registers.registerDimension(IDs.Darkax, IDs.Darkax);
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
		ZeroQuestAPI.begDarkList.registerItem(ModItems.toy);
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
		Registers.registerCommand(new CommandZeroQuest());
		LogHelper.info("Commands Loaded Successfully!");
		LogHelper.info("-----SERVER CONTENT LOAD FINSHED-----");

	}
}