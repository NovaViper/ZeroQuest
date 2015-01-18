package common.zeroquest;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.zeroquest.command.CommandZeroQuest;
import common.zeroquest.core.handlers.ConfigHandler;
import common.zeroquest.core.handlers.FuelHandler;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.creativetab.DarkQuestTab;
import common.zeroquest.creativetab.ZeroQuestTab;
import common.zeroquest.events.AchievementEvents;
import common.zeroquest.events.ConfigEvent;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.OreDic;
import common.zeroquest.world.WorldProviderDarkax;
import common.zeroquest.world.WorldProviderNillax;
import common.zeroquest.world.gen.WorldGenZQuest;

@Mod(modid = Constants.modid, name = "Zero Quest", version = Constants.version, useMetadata = true, guiFactory = "common.zeroquest.client.gui.config.GuiFactory")
public class ZeroQuest 
{
	@Instance("zero_quest")
	public static ZeroQuest instance;
	public static final Logger Log = LogManager.getFormatterLogger("Zero Quest");
    public static final String channel = Constants.modid;
	
	@SidedProxy(clientSide = "common.zeroquest.core.proxy.ClientProxy", serverSide = "common.zeroquest.core.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Put sounds from Sound in sounds.json //TODO
	
	public static ToolMaterial nileEssence;
	public static ToolMaterial darkEssence;
	
	public static final int NillaxID = 2;
	public static final int DarkaxID = 3;
	
	public static CreativeTabs ZeroTab;
	public static CreativeTabs DarkTab;
	

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + channel + File.separator + Constants.modid.toLowerCase() + ".cfg"));
        FMLCommonHandler.instance().bus().register(new ConfigEvent());
   		
        FMLCommonHandler.instance().bus().register(new PlayerEvents(Constants.version, "ZeroQuest", false));	    	
    	Log.info("-----PRE-CONTENT LOAD INITATING-----");
    	Log.info("Loading Main Stuff...");
        this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
    	if(Constants.DEF_DARKLOAD == true){
   		this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
    	}
		NetworkRegistry.INSTANCE.registerGuiHandler(Constants.modid, proxy);
    	Log.info("Load Stuff Loaded Successfully!");		
    	Log.info("-----PRE-CONTENT LOAD FINISHED-----");
    	}
	
	@EventHandler
    public void load(FMLInitializationEvent event)
	{			
		Log.info("-----CONTENT LOAD INITATING-----");
    	nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
    	
    	Log.info("Loading Block, Recipes and Items...");
    	ModBlocks.load();
    	ModItems.load();
       	ZeroQuestCrafting.loadRecipes();
    	Log.info("Blocks, Recipes and Items Loaded Successfully!");
		
    	Log.info("Loading Entities and Ore Dictionary...");
    	ModEntities.loadCreatures();
    	ModEntities.loadOthers();
   		OreDic.load();
   		Log.info("Entities and Ore Dictionary Loaded Successfully!");
		
   		Log.info("Loading Achievements and Biomes...");
   		ModAchievements.load();
   		FMLCommonHandler.instance().bus().register(new AchievementEvents());
    	ModBiomes.loadBiomes();
    	Log.info("Achievements and Biomes Loaded Successfully!");
		
    	if(Constants.DEF_DARKLOAD == true){
    	Log.warn("Dark Elemental Load is ENABLED!");	
    	Log.info("Initating Dark Elemental Load!");
    		darkEssence = EnumHelper.addToolMaterial("DarkEssence", 4, 4000, 21.0F, 5.0F, 40);
        	ModItems.loadDarkItems();
        	ModBlocks.loadDarkBlocks();
      		ZeroQuestCrafting.loadDarkRecipes();
    		ModEntities.loadDarkCreatures();
    		ModBiomes.loadDarkBiomes();
    		ModAchievements.loadDark();
    		OreDic.loadDarkOre();
           	registerProviderType(DarkaxID, WorldProviderDarkax.class, false);
            registerDimension(DarkaxID, DarkaxID);
            Log.info("Dark Elements Loaded Successfully!");
    	}else{
    		Log.warn("Dark Elemental Load is not ENABLED! Change configurations to enable!");
    		Log.info("Skipping Dark Elemental Load");

    	}
		
    	Log.info("Loading Crucial Stuff...");
       	proxy.registerRenderThings();
       	proxy.registerChestItems();
    	GameRegistry.registerFuelHandler(new FuelHandler());
       	GameRegistry.registerWorldGenerator(new WorldGenZQuest(), 0);
       	Log.info("The Crucial Stuff Loaded Successfully!");
		
       	Log.info("Loading Dimensions...");
       	registerProviderType(NillaxID, WorldProviderNillax.class, false);
       	registerDimension(NillaxID, NillaxID);
    	Log.info("Dimensions Loaded Successfully!");
        Log.info("-----CONTENT LOAD FINSHED-----");
	}
	
	@EventHandler
	public void PostInt(FMLPostInitializationEvent event)
	{
    	Log.info("-----POST-CONTENT LOAD INITATING-----");
    	Log.info("Nothing to be loaded as of now!");
        Log.info("-----POST-CONTENT LOAD FINSHED-----");
    	
	}
	
    @EventHandler //TODO
    public void serverStart(FMLServerStartingEvent event)        
    {   
    	Log.info("-----SERVER CONTENT LOAD INITATING-----");
    	Log.info("Loading Commands...");     
        MinecraftServer server = MinecraftServer.getServer();
        ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager(); 
        cmdman.registerCommand(new CommandZeroQuest());
    	Log.info("Commands Loaded Successfully!");  
    	Log.info("-----SERVER CONTENT LOAD FINSHED-----");
    	
    }
    
    public void registerProviderType(int id, Class<? extends WorldProvider> provider, boolean keepLoaded){
       	DimensionManager.registerProviderType(id, provider, keepLoaded);
    }
    
    public void registerDimension(int id, int providerType){
        DimensionManager.registerDimension(id, providerType);
    }
}