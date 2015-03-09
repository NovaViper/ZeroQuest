package common.zeroquest;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import common.zeroquest.api.registry.DogBedRegistry;
import common.zeroquest.block.BlockBedRock;
import common.zeroquest.block.BlockDogBed;
import common.zeroquest.block.BlockFoodBowl;
import common.zeroquest.block.BlockNileBlock;
import common.zeroquest.block.BlockNileOre;
import common.zeroquest.block.BlockNileWorkbench;
import common.zeroquest.block.flora.BlockDrestroFlower;
import common.zeroquest.block.flora.BlockNileBlackFlower;
import common.zeroquest.block.flora.BlockNileBlueFlower;
import common.zeroquest.block.flora.BlockNilePinkFlower;
import common.zeroquest.block.flora.BlockVitoidCrop;
import common.zeroquest.block.portal.BlockNilePortalStone;
import common.zeroquest.item.ItemDogBed;
import common.zeroquest.lib.Constants;

public class ModBlocks {
	public static Block looseBedrock;
	//public static Block looseBedrockStairs;
	public static Block nileCoalOre;
	public static Block nileGrainOre;
	public static Block darkGrainOre;
	public static Block blockNileEssence;
	public static Block blockDarkEssence;
	
	public static Block nileBlueFlower;
	public static Block nilePinkFlower;
	public static Block nileBlackFlower;
	public static Block destroFlower;
	public static Block vitoidPlant;
	
	public static Block nileWorktable;
	public static Block dogBed;
	public static Block foodBowl;
	
	//Portal//
	public static Block nillaxStone;
	public static Block portalNillax;
	public static Block nileFire;
	public static Block darkaxStone;
	public static Block portalDarkax;
	public static Block darkFire;
	
	public static void load() {
		//Natural Blocks//
		looseBedrock = new BlockBedRock().setUnlocalizedName("looseBedrock");
		register(looseBedrock, "bedrock_loose");
		
		//looseBedrockStairs = new BlockLooseBedrockStairs(looseBedrockStairs.getDefaultState());
		//register(looseBedrockStairs, "bedrock_loose_stairs");

		
		//Basic Blocks//
		nileWorktable = new BlockNileWorkbench().setUnlocalizedName("nileWorktable");
		register(nileWorktable, "crafting_table_nile");
		foodBowl = new BlockFoodBowl().setUnlocalizedName("foodBowl").setCreativeTab(ZeroQuest.ZeroTab);
		register(foodBowl, "food_bowl");
		dogBed = new BlockDogBed().setUnlocalizedName("dogBed").setCreativeTab(ZeroQuest.ZeroTab);
		registerWithClass(dogBed, ItemDogBed.class,"dog_bed");
		//GameRegistry.registerTileEntity(TileEntityDogBed.class, "dog_bed");
		
		//Flowers/Plants//
		nileBlueFlower = new BlockNileBlueFlower().setUnlocalizedName("nileBlueFlower");
		register(nileBlueFlower, "nile_flower_blue");
		nileBlackFlower = new BlockNileBlackFlower().setUnlocalizedName("nileBlackFlower");
		register(nileBlackFlower, "nile_flower_black");
		nilePinkFlower = new BlockNilePinkFlower().setUnlocalizedName("nilePinkFlower");
		register(nilePinkFlower, "nile_flower_pink");
		destroFlower = new BlockDrestroFlower().setUnlocalizedName("destroFlower");
		register(destroFlower, "destro_flower");
		vitoidPlant = new BlockVitoidCrop().setUnlocalizedName("vitoidPlant");
		register(vitoidPlant, "vitoid_plant");
    	
		//Ores//
		nileCoalOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.0F, 5.0F, 0.6F).setUnlocalizedName("nileCoalOre");
		register(nileCoalOre, "nile_coal_ore");
    	nileGrainOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("nileGrainOre");
		register(nileGrainOre, "nile_grain_ore");	
    	blockNileEssence = new BlockNileBlock(ZeroQuest.ZeroTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockNileEssence");
		register(blockNileEssence, "nile_essence_block");
		
		//Portal Parts//
		nillaxStone = new BlockNilePortalStone(Material.rock).setUnlocalizedName("nillaxStone").setCreativeTab(ZeroQuest.ZeroTab);
		register(nillaxStone, "nillax_stone");
		/*portalNillax = new BlockPortalNillax().setUnlocalizedName("portalNillax");
		register(portalNillax, "portal_nillax");
		nileFire = new BlockNileFire().setUnlocalizedName("nileFire");
		register(nileFire, "nile_fire");*/
		}
	   
	   public static void loadDarkBlocks(){
		   
		    darkaxStone = new BlockNilePortalStone(Material.rock).setUnlocalizedName("darkaxStone").setCreativeTab(ZeroQuest.DarkTab);
		    register(darkaxStone, "darkax_stone");
	    	/*portalDarkax = new BlockPortalDarkax().setUnlocalizedName("portalDarkax");
	    	register(portalDarkax, "portal_darkax");
	    	darkFire = new BlockDarkFire().setUnlocalizedName("darkFire");
	    	register(darkFire, "dark_fire");*/
	    	darkGrainOre = new BlockNileOre(ZeroQuest.DarkTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("darkGrainOre");
	    	register(darkGrainOre, "dark_grain_ore"); 
	    	blockDarkEssence = new BlockNileBlock(ZeroQuest.DarkTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockDarkEssence");
			register(blockDarkEssence, "dark_essence_block");
	   }
	   
	   public static void loadRenderers(){
		   
	    	registerRender(looseBedrock, 0, Constants.modid + ":" + "bedrock_loose", "inventory");
	    	//registerRender(looseBedrockStairs, 0, Constants.modid + ":" + "bedrock_loose_stairs", "inventory");
	    	registerRender(nileWorktable, 0, Constants.modid + ":" + "crafting_table_nile", "inventory");
	    	registerRender(nileBlueFlower, 0, Constants.modid + ":" + "nile_flower_blue", "inventory");	
	    	registerRender(nileBlackFlower, 0, Constants.modid + ":" + "nile_flower_black", "inventory");	
	    	registerRender(nilePinkFlower, 0, Constants.modid + ":" + "nile_flower_pink", "inventory");	
	    	registerRender(destroFlower, 0, Constants.modid + ":" + "destro_flower", "inventory");	
	    	registerRender(vitoidPlant, 0, Constants.modid + ":" + "vitoid_plant", "inventory");
	    	registerRender(nileCoalOre, 0, Constants.modid + ":" + "nile_coal_ore", "inventory");
	    	registerRender(nileGrainOre, 0, Constants.modid + ":" + "nile_grain_ore", "inventory");
	    	registerRender(blockNileEssence, 0, Constants.modid + ":" + "nile_essence_block", "inventory");
	    	registerRender(foodBowl, 0, Constants.modid + ":" + "food_bowl", "inventory");
	    	registerRender(dogBed, 0, Constants.modid + ":" + "dog_bed", "inventory");
	    	
	    	registerRender(nillaxStone, 0, Constants.modid + ":" + "nillax_stone", "inventory");	
	        //registerRender(portalNillax, 0, Constants.modid + ":" + "portal_nillax", "inventory");
	    	//registerRender(nileFire, 0, Constants.modid + ":" + "nile_fire", "inventory");
	   }
	   
	  public static void loadDarkRenderers(){
		  
	    	registerRender(darkaxStone, 0, Constants.modid + ":" + "darkax_stone", "inventory");	
	        //registerRender(portalDarkax, 0, Constants.modid + ":" + "portal_darkax", "inventory");
	    	//registerRender(darkFire, 0, Constants.modid + ":" + "dark_fire", "inventory");
	    	
	    	registerRender(darkGrainOre, 0, Constants.modid + ":" + "dark_grain_ore", "inventory");
	    	registerRender(blockDarkEssence, 0, Constants.modid + ":" + "dark_essence_block", "inventory");
	  }
	  
	  public static void loadDogBedTypes(){
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 0, "minecraft:blocks/planks_oak");
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 1, "minecraft:blocks/planks_spruce");
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 2, "minecraft:blocks/planks_birch");
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 3, "minecraft:blocks/planks_jungle");
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 4, "minecraft:blocks/planks_acacia");
			DogBedRegistry.CASINGS.registerMaterial(Blocks.planks, 5, "minecraft:blocks/planks_big_oak");
			
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 0, "minecraft:blocks/wool_colored_white");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 1, "minecraft:blocks/wool_colored_orange");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 2, "minecraft:blocks/wool_colored_magenta");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 3, "minecraft:blocks/wool_colored_light_blue");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 4, "minecraft:blocks/wool_colored_yellow");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 5, "minecraft:blocks/wool_colored_lime");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 6, "minecraft:blocks/wool_colored_pink");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 7, "minecraft:blocks/wool_colored_gray");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 8, "minecraft:blocks/wool_colored_silver");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 9, "minecraft:blocks/wool_colored_cyan");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 10, "minecraft:blocks/wool_colored_purple");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 11, "minecraft:blocks/wool_colored_blue");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 12, "minecraft:blocks/wool_colored_brown");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 13, "minecraft:blocks/wool_colored_green");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 14, "minecraft:blocks/wool_colored_red");
			DogBedRegistry.BEDDINGS.registerMaterial(Blocks.wool, 15, "minecraft:blocks/wool_colored_black");
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