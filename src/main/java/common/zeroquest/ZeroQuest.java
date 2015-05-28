package common.zeroquest;

import java.io.File;

import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.command.*;
import common.zeroquest.core.handlers.*;
import common.zeroquest.core.helper.*;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.creativetab.*;
import common.zeroquest.events.*;
import common.zeroquest.lib.*;
import common.zeroquest.network.*;
import common.zeroquest.world.*;
import common.zeroquest.world.gen.*;

@Mod(modid = Constants.modid, name = Constants.name, version = Constants.version, useMetadata = true, guiFactory = Constants.guiFactory)
public class ZeroQuest {
	@Instance(value = Constants.modid)
	public static ZeroQuest instance;

	@SidedProxy(clientSide = Constants.clientProxy, serverSide = Constants.serverProxy)
	public static CommonProxy proxy;

	// Put sounds from Sound in sounds.json TODO

	public static ToolMaterial nileEssence;
	public static ToolMaterial darkEssence;

	public static final int NillaxID = 2;
	public static final int DarkaxID = 3;

	public static CreativeTabs ZeroTab;
	public static CreativeTabs DarkTab;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Constants.channel.toLowerCase() + File.separator + Constants.modid + ".cfg"));
		registerFMLCommonEventBus(new ConfigEvent());
		if (Constants.DEF_MODCHECKER == true) {
			registerFMLCommonEventBus(new ModVersionCheck(Constants.version, "ZeroQuest", Constants.isBeta));
		}
		LogHelper.info("-----PRE-CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Main Stuff...");
		this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
		if (Constants.DEF_DARKLOAD == true) {
			this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
		}
		registerGuiHandler(Constants.modid, proxy);
		proxy.registerMoreThings();
		LogHelper.info("Load Stuff Loaded Successfully!");
		LogHelper.info("Loading Block, Recipes, Events and Items...");
		nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
		ModBlocks.load();
		ModItems.load();
		ModEntities.loadCreatures();
		ModEntities.loadOthers();
		proxy.registerStateMappings();
		registerForgeEventBus(new FOVEvent());
		registerForgeEventBus(new BlastResistanceEvent()); // TODO
		registerFMLCommonEventBus(new ConfigEvent());
		ZeroQuestCrafting.loadRecipes();
		if (Constants.DEF_DARKLOAD == true) {
			darkEssence = EnumHelper.addToolMaterial("DarkEssence", 4, 5000, 21.0F, 5.0F, 40);
			ModBlocks.loadDarkBlocks();
			ModItems.loadDarkItems();
			ZeroQuestCrafting.loadDarkRecipes();
			ModEntities.loadDarkCreatures();
			proxy.registerStateMappingsForDark();
		}

		registerForgeEventBus(new FireEventHandler());
		registerFMLCommonEventBus(new ConnectionHandler());
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
		registerFMLCommonEventBus(new AchievementEvents());
		ModBiomes.loadBiomes();
		if (event.getSide().isClient()) {
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
		registerFuelHandler(new FuelHandler());
		registerWorldGenerator(new WorldGenZQuest(), 0);
		ModEntities.loadSpawns();
		registerProviderType(NillaxID, WorldProviderNillax.class, false);
		registerDimension(NillaxID, NillaxID);
		LogHelper.info("Crucial Stuff and Dimensions Loaded Successfully!");

		if (Constants.DEF_DARKLOAD == true) {
			LogHelper.warn("Dark Elemental Load is ENABLED!");
			LogHelper.info("Initating Dark Elemental Load!");
			ModBiomes.loadDarkBiomes();
			ModAchievements.loadDark();
			OreDic.loadDarkOre();
			registerProviderType(DarkaxID, WorldProviderDarkax.class, false);
			registerDimension(DarkaxID, DarkaxID);
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
		LogHelper.info("Loading Talents...");
		ModTalents.loadTalents();
		LogHelper.info("Talents Loaded Successfully!");
		LogHelper.info("-----POST-CONTENT LOAD FINSHED-----");

	}

	@EventHandler
	// TODO
	public void serverStart(FMLServerStartingEvent event) {
		LogHelper.info("-----SERVER CONTENT LOAD INITATING-----");
		LogHelper.info("Loading Commands...");
		registerCommand(new CommandZeroQuest());
		LogHelper.info("Commands Loaded Successfully!");
		LogHelper.info("-----SERVER CONTENT LOAD FINSHED-----");

	}

	// Registers\\
	public void registerGuiHandler(Object mod, IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
	}

	public void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	public void registerFuelHandler(IFuelHandler handler) {
		GameRegistry.registerFuelHandler(handler);
	}

	public void registerCommand(ICommand command) {
		MinecraftServer server = MinecraftServer.getServer();
		ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager();
		cmdman.registerCommand(command);
	}

	public void registerForgeEventBus(Object target) {
		MinecraftForge.EVENT_BUS.register(target);
	}

	public void registerFMLCommonEventBus(Object target) {
		FMLCommonHandler.instance().bus().register(target);
	}

	public void registerProviderType(int id, Class<? extends WorldProvider> provider, boolean keepLoaded) {
		DimensionManager.registerProviderType(id, provider, keepLoaded);
	}

	public void registerDimension(int id, int providerType) {
		DimensionManager.registerDimension(id, providerType);
	}
}