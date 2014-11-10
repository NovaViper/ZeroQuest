package common.zeroquest;


import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import common.zeroquest.block.BlockBedRock;
import common.zeroquest.block.BlockDarkGrainOre;
import common.zeroquest.block.BlockDrestroFlower;
import common.zeroquest.block.BlockNileB2Flower;
import common.zeroquest.block.BlockNileBFlower;
import common.zeroquest.block.BlockNileCoalOre;
import common.zeroquest.block.BlockNileGrainOre;
import common.zeroquest.block.BlockNilePinkFlower;
import common.zeroquest.block.BlockNileTable;
import common.zeroquest.block.BlockVitoid;
import common.zeroquest.block.fluid.BlockFluidAcid;
import common.zeroquest.block.fluid.BlockFluidNili;
import common.zeroquest.block.portal.BlockDarkaxFire;
import common.zeroquest.block.portal.BlockNileFire;
import common.zeroquest.block.portal.BlockNilePortalStone;
import common.zeroquest.block.portal.BlockPortalNillax;
import common.zeroquest.block.portal.BlockPortalDarkax;
import common.zeroquest.events.BucketHandler;
import common.zeroquest.fluid.AcidFluid;
import common.zeroquest.fluid.NiliFluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	//public static Block nileStone;
	//public static Block nileCobblestone;
	public static Block looseBedrock;
	public static Block nileCoalOre;
	public static Block nileGrainOre;
	public static Block darkGrainOre;	
	public static Block nileBlueFlower;
	public static Block nilePinkFlower;
	public static Block nileBlackFlower;
	public static Block destroFlower;
	public static Block vitoidPlant;
	
	public static Block nileWorktable;
	
	//Portal//
	public static Block nillaxStone;
	public static Block portalNillax;
	public static Block nileFire;
	public static Block darkaxStone;
	public static Block portalDarkax;
	public static Block darkaxFire;
	
	public static Block nileLeaf;
	public static Block nileLog;
	public static Block nileSapling;
	
	public static void load() {
		//Natural Blocks//
		/*nileStone = new BlockNileStone().setBlockName("nileStone").setBlockTextureName(ZeroQuest.modid + ":" + "nileStone");
		register(nileStone, "nile_ stone");
		nileCobblestone = new BlockNileCobblestone(Material.rock).setBlockName("nileCobblestone").setBlockTextureName(ZeroQuest.modid + ":" + "nileCobblestone");
		register(nileCobblestone, "nile_cobblestone");*/
		looseBedrock = new BlockBedRock().setBlockName("looseBedrock").setBlockTextureName(ZeroQuest.modid + ":" + "looseBedrock");
		register(looseBedrock, "bedrock_loose");
		
		//Basic Blocks//
		nileWorktable = new BlockNileTable().setBlockName("nileWorktable").setBlockTextureName(ZeroQuest.modid + ":" + "nileWorktable");
		register(nileWorktable, "crafting_table_nile");

		//Flowers/Plants//
		nileBlueFlower = new BlockNileBFlower().setBlockName("nileBlueFlower").setBlockTextureName(ZeroQuest.modid + ":" + "nileBlueFlower");
		register(nileBlueFlower, "nile_flower_blue");
		nileBlackFlower = new BlockNileB2Flower().setBlockName("nileBlackFlower").setBlockTextureName(ZeroQuest.modid + ":" + "nileBlackFlower");
		register(nileBlackFlower, "nile_flower_black");
		nilePinkFlower = new BlockNilePinkFlower().setBlockName("nilePinkFlower").setBlockTextureName(ZeroQuest.modid + ":" + "nilePinkFlower");
		register(nilePinkFlower, "nile_flower_pink");
		destroFlower = new BlockDrestroFlower().setBlockName("nileDestroFlower").setBlockTextureName(ZeroQuest.modid + ":" + "destroFlower");
		register(destroFlower, "nile_flower_destro");
		vitoidPlant = new BlockVitoid().setBlockName("vitoidPlant").setBlockTextureName(ZeroQuest.modid + ":" + "vitoidPlant");
		register(vitoidPlant, "vitoid_plant");

		//Ores//
		nileCoalOre = new BlockNileCoalOre(Material.rock).setBlockName("nileCoalOre").setBlockTextureName(ZeroQuest.modid + ":" + "nileCoalOre");
		register(nileCoalOre, "nile_coal_ore");
		nileGrainOre = new BlockNileGrainOre(Material.rock).setBlockName("nileGrainOre").setBlockTextureName(ZeroQuest.modid + ":" + "nileGrainOre");
		register(nileGrainOre, "nile_grain_Ore");
		
		//Portal Parts//
		nillaxStone = new BlockNilePortalStone(Material.rock).setBlockName("nillaxStone").setBlockTextureName(ZeroQuest.modid + ":" + "nillaxStone").setCreativeTab(ZeroQuest.ZeroTab);
		register(nillaxStone, "nillax_stone");
		portalNillax = new BlockPortalNillax().setBlockName("portalNillax");
		register(portalNillax, "portal_nillax");
		nileFire = new BlockNileFire().setBlockName("nileFire").setBlockTextureName(ZeroQuest.modid + ":" + "nileFire");
		register(nileFire, "nile_fire");
		}
	   
	   public static void loadDarkBlocks(){
		   
		    darkaxStone = new BlockNilePortalStone(Material.rock).setBlockName("darkaxStone").setBlockTextureName(ZeroQuest.modid + ":" + "darkaxStone").setCreativeTab(ZeroQuest.DarkTab);
		    register(darkaxStone, "darkax_stone");
	    	portalDarkax = new BlockPortalDarkax().setBlockName("portalDarkax");
	    	register(portalDarkax, "portal_darkax");
	    	darkaxFire = new BlockDarkaxFire().setBlockName("darkaxFire").setBlockTextureName(ZeroQuest.modid + ":" + "darkaxFire");
	    	register(darkaxFire, "darkax_fire");
		   
		   darkGrainOre = new BlockDarkGrainOre(Material.rock).setBlockName("darkGrainOre").setBlockTextureName(ZeroQuest.modid + ":" + "darkGrainOre");
		   register(darkGrainOre, "dark_grain_ore");     	
	   }
	   
	   public static void register(Block block, String name){
		   GameRegistry.registerBlock(block, name);
	   }
	   
	   public static void registerWithClass(Block block, Class itemClass,String name){
		   GameRegistry.registerBlock(block, itemClass, name);
	   }
}