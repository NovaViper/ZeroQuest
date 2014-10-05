package common.zeroquest;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.zeroquest.*;
import common.zeroquest.lib.*;
import common.zeroquest.core.command.CommandZeroQuest;
import common.zeroquest.core.handlers.*;
import common.zeroquest.core.proxy.*;
import common.zeroquest.creativetab.*;
import common.zeroquest.events.*;
import common.zeroquest.world.*;
import common.zeroquest.world.gen.*;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = ZeroQuest.modid, name = "Zero Quest", version = ZeroQuest.version, useMetadata = true)
public class ZeroQuest 
{
	@Instance("zero_quest")
	public static ZeroQuest instance;
	public static final String modid = "zero_quest";
	public static final String version = "v1.0.5";
	public static final Logger Log = LogManager.getFormatterLogger("Zero Quest");
    public static final String channel = modid;
	
	@SidedProxy(clientSide = "common.zeroquest.core.proxy.ClientProxy", serverSide = "common.zeroquest.core.proxy.CommonProxy")
	public static CommonProxy proxy;

	//Put custom particles in ParticleEffects (Get regular particles from RenderGlobal)// //TODO
	//Put Sounds in sounds.json//
	
	public static ToolMaterial nileEssence;
	public static ArmorMaterial nileEssenceArmor;
	public static ToolMaterial darkEssence;
	public static ArmorMaterial darkEssenceArmor;
	
	public static final int NillaxID = 2;
	public static final int DarkaxID = 3;
	
	public static CreativeTabs ZeroTab;
	public static CreativeTabs DarkTab;
	
    private ConfigurationHandler config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        config = new ConfigurationHandler(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + channel + File.separator + modid.toLowerCase() + ".cfg"));   
   		FMLCommonHandler.instance().bus().register(new OnPlayerLogin(version, "ZeroQuest"));	    	
    	Log.info("-----PRE-CONTENT LOAD INITATING-----");
    	Log.info("Loading Main Stuff...");
        this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID());
    	if(Constants.DEF_DARKLOAD == true){
   		this.DarkTab = new DarkQuestTab(CreativeTabs.getNextID());
    	}
		NetworkRegistry.INSTANCE.registerGuiHandler(ZeroQuest.modid, proxy);
    	LocalizationHandler.loadLanguages();
    	ModLiquids.load(); //TODO
    	Log.info("Load Stuff Loaded Successfully!");		
    	Log.info("-----PRE-CONTENT LOAD FINISHED-----");
    	}
	
	@EventHandler
    public void load(FMLInitializationEvent event)
	{			
		Log.info("-----CONTENT LOAD INITATING-----");
    	nileEssence = EnumHelper.addToolMaterial("NileEssence", 4, 4000, 20.0F, 4.0F, 30);
    	nileEssenceArmor = EnumHelper.addArmorMaterial("NileEssenceArmor", 40, new int[]{4, 9, 7, 4}, 25);  
    	
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
        	darkEssenceArmor = EnumHelper.addArmorMaterial("DarkEssenceArmor", 41, new int[]{5, 10, 8, 1}, 30);  
        	ModItems.loadDarkItems();
        	ModBlocks.loadDarkBlocks();
      		ZeroQuestCrafting.loadDarkRecipes();
    		ModEntities.loadDarkCreatures();
    		ModBiomes.loadDarkBiomes();
    		ModAchievements.loadDark();
    		OreDic.loadDarkOre();
           	DimensionManager.registerProviderType(DarkaxID, WorldProviderDarkax.class, false);
            DimensionManager.registerDimension(DarkaxID, DarkaxID);
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
    	DimensionManager.registerProviderType(NillaxID, WorldProviderNillax.class, false);
    	DimensionManager.registerDimension(NillaxID, NillaxID);
    	Log.info("Dimensions Loaded Successfully!");
        Log.info("-----CONTENT LOAD FINSHED-----");
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
}