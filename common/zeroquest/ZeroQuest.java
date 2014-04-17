package common.zeroquest;

import java.io.IOException;
import java.util.logging.Level;

import common.zeroquest.creativetab.ZeroQuestTab;
import common.zeroquest.events.ZeroQuestEvents;
import common.zeroquest.handlers.ConfigurationHandler;
import common.zeroquest.handlers.CraftingHandler;
import common.zeroquest.handlers.FuelHandler;
import common.zeroquest.handlers.PickupHandler;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.LocalizationHandler;
import common.zeroquest.lib.LogHelper;
import common.zeroquest.lib.OreDic;
import common.zeroquest.proxy.CommonProxy;
import common.zeroquest.renderer.ItemRendererNileTable;
import common.zeroquest.renderer.RendererNileTable;
import common.zeroquest.tileentity.TileEntityNileTable;
import common.zeroquest.world.WorldProviderNillax;
import common.zeroquest.world.gen.WorldGenZQuest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ZeroQuest.modid, name = "Zero Quest", version = ZeroQuest.version, dependencies = "required-after:Forge@[9.11.1.965,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ZeroQuest 
{
	@Instance("Zero_Quest")
	public static ZeroQuest instance;
	public static final String modid = "Zero_Quest";
	public static final String version = "0.0.1";
	
	@SidedProxy(clientSide = "common.zeroquest.proxy.ClientProxy", serverSide = "common.zeroquest.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Achievements are 40 and up//TODO
	//Natural Blocks are 240 - 250//
	//Entities are 300s//
	//Portal Parts are 520 - 530//
	//Flowers are 630 - 639//
	//Ores are 700 - 720//
	//Tree Blocks are 4000 - 4099//
	//Tools/Weapons are 5000 - 5099//
	//Basic Mod Items are 5100 - 5199//
	//Armor are 5200 - 5299//
	//Foods are 5300 - 5399//
	//Put custom particles in ParticleEffects (Get regular particles from RenderGlobal)//
	
	public static EnumToolMaterial nileEssenceMaterial;
	public static EnumArmorMaterial nileEssenceMaterial2;
	public static EnumToolMaterial darkEssenceMaterial;
	
	public static final int NillaxID = 2;
	
	public static CreativeTabs ZeroTab;
	

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
		//LogHelper.init();
		
		LogHelper.log(Level.INFO, "-----PRE-CONTENT LOAD INITATING-----");
		LogHelper.log(Level.INFO, "Loading Main Stuff...");
	   	ConfigurationHandler.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
		//GameRegistry.registerPlayerTracker(new OnPlayerLogin(version, "Zero Quest"));
   		this.ZeroTab = new ZeroQuestTab(CreativeTabs.getNextID()); 
    	LocalizationHandler.loadLanguages();
		LogHelper.log(Level.INFO, "Load Stuff Loaded Successfully!");
		
		LogHelper.log(Level.INFO, "-----PRE-CONTENT LOAD FINISHED-----");
    	}
	
	@EventHandler
    public void load(FMLInitializationEvent event)
	{			
		LogHelper.log(Level.INFO, "-----CONTENT LOAD INITATING-----");
    	nileEssenceMaterial = EnumHelper.addToolMaterial("NileEssenceMaterial", 3, 2000, 10.0F, 4.0F, 30);
    	darkEssenceMaterial = EnumHelper.addToolMaterial("DarkEssenceMaterial", 4, 4000, 20.0F, 5.0F, 40);
    	
    	nileEssenceMaterial2 = EnumHelper.addArmorMaterial("NileEssenceAMaterial", 40, new int[]{4, 9, 7, 4}, 25);  
    	
		LogHelper.log(Level.INFO, "Loading Block and Items...");
    	ModBlocks.load();
    	ModBlocks.addNames();
    	ModItems.load();
    	ModItems.addNames();
		LogHelper.log(Level.INFO, "Blocks and Items Loaded Successfully!");
		
		LogHelper.log(Level.INFO, "Loading Nile Recipes...");
       	ZeroQuestCrafting.loadRecipes();
		LogHelper.log(Level.INFO, "Recipes ready!");
		
		LogHelper.log(Level.INFO, "Loading Entities and Ore Dictionary...");
    	ModEntities.loadCreatures();
    	ModEntities.loadTileEntities();
   		OreDic.load();
		LogHelper.log(Level.INFO, "Entities and Ore Dictionary Loaded Successfully!");
		
		LogHelper.log(Level.INFO, "Loading Achievements...");
   		ModAchievements.load();
		LogHelper.log(Level.INFO, "Achievements Loaded Successfully!");
		
		LogHelper.log(Level.INFO, "Loading Biomes...");
    	ModBiomes.loadBiomes();
		LogHelper.log(Level.INFO, "Biomes Loaded Successfully!");
		
    	if(!Constants.DarkLoadOff){
		LogHelper.log(Level.INFO, "Loading Dark Elements");
        	ModItems.loadDarkItems();
        	ModBlocks.loadDarkBlocks();
      		ZeroQuestCrafting.loadDarkRecipes();
    		ModEntities.loadDarkCreatures();
    		ModBiomes.loadDarkBiomes();
    		LogHelper.log(Level.INFO, "Dark Elements Loaded Successfully!");
    	}
		
		
		LogHelper.log(Level.INFO, "Loading Crucial Stuff...");
       	proxy.registerRenderThings();
       	proxy.reigsterClientLangugaes();
		NetworkRegistry.instance().registerGuiHandler(ZeroQuest.modid, proxy);
    	GameRegistry.registerFuelHandler(new FuelHandler());
   		GameRegistry.registerCraftingHandler(new CraftingHandler());
   		GameRegistry.registerPickupHandler(new PickupHandler());
       	GameRegistry.registerWorldGenerator(new WorldGenZQuest());
    	//MinecraftForge.EVENT_BUS.register(new ZeroQuestEvents());
		LogHelper.log(Level.INFO, "The Crucial Stuff Loaded Successfully!");
		
		LogHelper.log(Level.INFO, "Loading Dimensions...");
       	DimensionManager.registerProviderType(NillaxID, WorldProviderNillax.class, false);
        DimensionManager.registerDimension(NillaxID, NillaxID);
		LogHelper.log(Level.INFO, "Dimensions Loaded Successfully!");
    	LogHelper.log(Level.INFO, "-----CONTENT LOAD FINSHED-----");
	}
	
	/*@EventHandler
    public void postInit(FMLPostInitializationEvent event){
    ModBiomes.loadBiomeDictionary();
	BiomeDictionary.registerAllBiomes();
	}
	
    /*@EventHandler TODO
    public void serverStart(FMLServerStartingEvent event)        
    {   
        	LogHelper.log(Level.INFO, "-----COMMAND CONTENT LOAD INITATING-----");
    	LogHelper.log(Level.INFO, "Loading Commands...");     
    	MinecraftServer server = MinecraftServer.getServer();
    	ICommandManager command = server.getCommandManager();
    	ServerCommandManager manager = (ServerCommandManager) command;
    	manager.registerCommand(new SpawnPet());
    	LogHelper.log(Level.INFO, "Commands Loaded Successfully!");  
    	LogHelper.log(Level.INFO, "-----COMMAND CONTENT LOAD FINSHED-----");
    	
    }*/
}