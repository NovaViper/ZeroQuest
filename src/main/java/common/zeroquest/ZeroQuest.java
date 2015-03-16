package common.zeroquest;

import java.io.File;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import common.zeroquest.command.CommandZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;
import common.zeroquest.core.handlers.ConnectionHandler;
import common.zeroquest.core.handlers.FuelHandler;
import common.zeroquest.core.helper.LogHelper;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.creativetab.DarkQuestTab;
import common.zeroquest.creativetab.ZeroQuestTab;
import common.zeroquest.events.AchievementEvents;
import common.zeroquest.events.ConfigEvent;
import common.zeroquest.events.FOVEvent;
import common.zeroquest.events.LivingEvents;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.OreDic;
import common.zeroquest.network.NetworkManager;
import common.zeroquest.world.WorldProviderDarkax;
import common.zeroquest.world.WorldProviderNillax;
import common.zeroquest.world.gen.WorldGenZQuest;

@Mod(modid = Constants.modid, name = Constants.name, version = Constants.version, useMetadata = true, guiFactory = Constants.guiFactory)
public class ZeroQuest 
{
	@Instance(value = Constants.modid)
	public static ZeroQuest instance;
	
	@SidedProxy(clientSide = Constants.clientProxy, serverSide = Constants.serverProxy)
	public static CommonProxy proxy;
	public static NetworkManager networkManager;
	
	//Put sounds from Sound in sounds.json //TODO
	
	public static ToolMaterial nileEssence;
	public static ToolMaterial darkEssence;
	
	public static final int NillaxID = 2;
	public static final int DarkaxID = 3;
	
	public static CreativeTabs ZeroTab;
	public static CreativeTabs DarkTab;
	

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Constants.channel.toLowerCase() + File.separator + Constants.modid.toLowerCase() + ".cfg"));
        FMLCommonHandler.instance().bus().register(new ConfigEvent());
        FMLCommonHandler.instance().bus().register(new PlayerEvents(Constants.version, "ZeroQuest", Constants.isBeta));
    	LogHelper.info("-----PRE-CONTENT LOAD INITATING-----");
    	LogHelper.info("Loading Main Stuff...");
        this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
    	if(Constants.DEF_DARKLOAD == true){
   		this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
    	}
		NetworkRegistry.INSTANCE.registerGuiHandler(Constants.modid, proxy);
		proxy.registerMoreThings();
    	LogHelper.info("Load Stuff Loaded Successfully!");		
    	LogHelper.info("-----PRE-CONTENT LOAD FINISHED-----");
    	}
	
	@EventHandler
    public void load(FMLInitializationEvent event)
	{
		networkManager = new NetworkManager();
		LogHelper.info("-----CONTENT LOAD INITATING-----");
    	nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
    	
    	LogHelper.info("Loading Block, Recipes, Events and Items...");
    	ModBlocks.load();
    	ModItems.load();
    	if(event.getSide().isClient()){
    		ModBlocks.loadRenderers();
    		ModItems.loadRenderers();
    	}
    	
    	MinecraftForge.EVENT_BUS.register(new FOVEvent());
    	MinecraftForge.EVENT_BUS.register(new LivingEvents()); //TODO
       	ZeroQuestCrafting.loadRecipes();
    	LogHelper.info("Blocks, Recipes, Events and Items Loaded Successfully!");
		
    	LogHelper.info("Loading Entities and Ore Dictionary...");
    	ModEntities.loadCreatures();
    	ModEntities.loadOthers();
   		OreDic.load();
   		LogHelper.info("Entities and Ore Dictionary Loaded Successfully!");
		
   		LogHelper.info("Loading Achievements and Biomes...");
   		ModAchievements.load();
   		FMLCommonHandler.instance().bus().register(new AchievementEvents());
    	ModBiomes.loadBiomes();
    	LogHelper.info("Achievements and Biomes Loaded Successfully!");
		
    	if(Constants.DEF_DARKLOAD == true){
    	LogHelper.warn("Dark Elemental Load is ENABLED!");	
    	LogHelper.info("Initating Dark Elemental Load!");
    		darkEssence = EnumHelper.addToolMaterial("DarkEssence", 4, 5000, 21.0F, 5.0F, 40);
        	ModItems.loadDarkItems();
        	ModBlocks.loadDarkBlocks();
        	if(event.getSide().isClient()){
        		ModItems.loadDarkRenderers();
        		ModBlocks.loadDarkRenderers();
        	}
      		ZeroQuestCrafting.loadDarkRecipes();
    		ModEntities.loadDarkCreatures();
    		ModBiomes.loadDarkBiomes();
    		ModAchievements.loadDark();
    		OreDic.loadDarkOre();
           	registerProviderType(DarkaxID, WorldProviderDarkax.class, false);
            registerDimension(DarkaxID, DarkaxID);
        	ModEntities.loadDarkSpawns();
            LogHelper.info("Dark Elements Loaded Successfully!");
    	}else{
    		LogHelper.warn("Dark Elemental Load is not ENABLED! Change configurations to enable!");
    		LogHelper.info("Skipping Dark Elemental Load");

    	}
    	ModEntities.loadSpawns();
    	LogHelper.info("Loading Crucial Stuff...");
       	proxy.registerRenderThings();
       	proxy.registerChestItems();
    	GameRegistry.registerFuelHandler(new FuelHandler());
       	GameRegistry.registerWorldGenerator(new WorldGenZQuest(), 0);
       	LogHelper.info("The Crucial Stuff Loaded Successfully!");
		
       	LogHelper.info("Loading Dimensions...");
       	registerProviderType(NillaxID, WorldProviderNillax.class, false);
       	registerDimension(NillaxID, NillaxID);
    	LogHelper.info("Dimensions Loaded Successfully!");
    	
    	LogHelper.info("Logging the Network Stuff");
		FMLCommonHandler.instance().bus().register(new ConnectionHandler());
    	LogHelper.info("Network Stuff Loaded Successfully");		
        LogHelper.info("-----CONTENT LOAD FINSHED-----");
	}
	
	@EventHandler
	public void PostInt(FMLPostInitializationEvent event)
	{
    	LogHelper.info("-----POST-CONTENT LOAD INITATING-----");
    	ModTalents.loadTalents(); 		
        LogHelper.info("-----POST-CONTENT LOAD FINSHED-----");
    	
	}
	
    @EventHandler //TODO
    public void serverStart(FMLServerStartingEvent event)        
    {   
    	LogHelper.info("-----SERVER CONTENT LOAD INITATING-----");
    	LogHelper.info("Loading Commands...");     
        MinecraftServer server = MinecraftServer.getServer();
        ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager(); 
        cmdman.registerCommand(new CommandZeroQuest());
    	LogHelper.info("Commands Loaded Successfully!");  
    	LogHelper.info("-----SERVER CONTENT LOAD FINSHED-----");
    	
    }
    
    public void registerProviderType(int id, Class<? extends WorldProvider> provider, boolean keepLoaded){
       	DimensionManager.registerProviderType(id, provider, keepLoaded);
    }
    
    public void registerDimension(int id, int providerType){
        DimensionManager.registerDimension(id, providerType);
    }
}