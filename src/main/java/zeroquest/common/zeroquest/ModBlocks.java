package common.zeroquest;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import common.zeroquest.block.BlockBedRock;
import common.zeroquest.block.BlockDarkGrainOre;
import common.zeroquest.block.BlockDrestroFlower;
import common.zeroquest.block.BlockNileB2Flower;
import common.zeroquest.block.BlockNileBFlower;
import common.zeroquest.block.BlockNileCoalOre;
import common.zeroquest.block.BlockNileGrainOre;
import common.zeroquest.block.BlockNilePinkFlower;
import common.zeroquest.block.BlockNileWorkbench;
import common.zeroquest.block.BlockVitoid;
import common.zeroquest.block.portal.BlockNilePortalStone;
import common.zeroquest.lib.Constants;

public class ModBlocks {
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
	
	public static void load() {
		//Natural Blocks//
		looseBedrock = new BlockBedRock().setUnlocalizedName("looseBedrock");
		register(looseBedrock, "bedrock_loose");
    	registerRender(looseBedrock, 0, Constants.modid + ":" + "bedrock_loose", "inventory");
    	
		//Basic Blocks//
		nileWorktable = new BlockNileWorkbench().setUnlocalizedName("nileWorktable");
		register(nileWorktable, "crafting_table_nile");
    	registerRender(nileWorktable, 0, Constants.modid + ":" + "crafting_table_nile", "inventory");

		//Flowers/Plants//
		nileBlueFlower = new BlockNileBFlower().setUnlocalizedName("nileBlueFlower");
		register(nileBlueFlower, "nile_flower_blue");
    	registerRender(nileBlueFlower, 0, Constants.modid + ":" + "nile_flower_blue", "inventory");	
		nileBlackFlower = new BlockNileB2Flower().setUnlocalizedName("nileBlackFlower");
		register(nileBlackFlower, "nile_flower_black");
    	registerRender(nileBlackFlower, 0, Constants.modid + ":" + "nile_flower_black", "inventory");	
		nilePinkFlower = new BlockNilePinkFlower().setUnlocalizedName("nilePinkFlower");
		register(nilePinkFlower, "nile_flower_pink");
    	registerRender(nilePinkFlower, 0, Constants.modid + ":" + "nile_flower_pink", "inventory");	
		destroFlower = new BlockDrestroFlower().setUnlocalizedName("nileDestroFlower");
		register(destroFlower, "nile_flower_destro");
    	registerRender(destroFlower, 0, Constants.modid + ":" + "nile_flower_destro", "inventory");	
		vitoidPlant = new BlockVitoid().setUnlocalizedName("vitoidPlant");
		register(vitoidPlant, "vitoid_plant");
    	registerRender(vitoidPlant, 0, Constants.modid + ":" + "vitoid_plant", "inventory");
    	
		//Ores//
		nileCoalOre = new BlockNileCoalOre(Material.rock).setUnlocalizedName("nileCoalOre");
		register(nileCoalOre, "nile_coal_ore");
    	registerRender(nileCoalOre, 0, Constants.modid + ":" + "nile_coal_ore", "inventory");
    	nileGrainOre = new BlockNileGrainOre(Material.rock).setUnlocalizedName("nileGrainOre");
		register(nileGrainOre, "nile_grain_Ore");
    	registerRender(nileGrainOre, 0, Constants.modid + ":" + "nile_grain_Ore", "inventory");	
		
		//Portal Parts//
		nillaxStone = new BlockNilePortalStone(Material.rock).setUnlocalizedName("nillaxStone").setCreativeTab(ZeroQuest.ZeroTab);
		register(nillaxStone, "nillax_stone");
    	registerRender(nillaxStone, 0, Constants.modid + ":" + "nillax_stone", "inventory");	
		/*portalNillax = new BlockPortalNillax().setUnlocalizedName("portalNillax");
		register(portalNillax, "portal_nillax");
		nileFire = new BlockNileFire().setUnlocalizedName("nileFire");
		register(nileFire, "nile_fire");
    	registerRender(nileFire, 0, Constants.modid + ":" + "nile_fire", "inventory");*/
		}
	   
	   public static void loadDarkBlocks(){
		   
		    darkaxStone = new BlockNilePortalStone(Material.rock).setUnlocalizedName("darkaxStone").setCreativeTab(ZeroQuest.DarkTab);
		    register(darkaxStone, "darkax_stone");
	    	registerRender(darkaxStone, 0, Constants.modid + ":" + "darkax_stone", "inventory");	
	    	/*portalDarkax = new BlockPortalDarkax().setUnlocalizedName("portalDarkax");
	    	register(portalDarkax, "portal_darkax");
	        registerRender(portalDarkax, 0, Constants.modid + ":" + "portal_darkax", "inventory");
	    	darkaxFire = new BlockDarkaxFire().setUnlocalizedName("darkaxFire");
	    	register(darkaxFire, "darkax_fire");*/	
		   darkGrainOre = new BlockDarkGrainOre(Material.rock).setUnlocalizedName("darkGrainOre");
		   register(darkGrainOre, "dark_grain_ore"); 
	    	registerRender(darkGrainOre, 0, Constants.modid + ":" + "dark_grain_ore", "inventory");
	   }
	   
	   public static void register(Block block, String name){
		   GameRegistry.registerBlock(block, name);
	   }
	   
	   public static void registerWithClass(Block block, Class itemClass,String name){
		   GameRegistry.registerBlock(block, itemClass, name);
	   }
	   
	   public static void registerRender(Block block, int metadata, String blockString, String location){
		   Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(blockString, location));
	   }
}