package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.block.*;
import common.zeroquest.block.flora.*;
import common.zeroquest.block.portal.*;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Registers;

public class ModBlocks {
	public static Block looseBedrock;
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
	public static Block foodBowl;

	// Portal//
	public static Block nillaxStone;
	public static Block portalNillax;
	public static Block nileFire;
	public static Block darkaxStone;
	public static Block portalDarkax;
	public static Block darkFire;

	public static void load() {
		// Natural Blocks//
		looseBedrock = new BlockBedRock().setUnlocalizedName("looseBedrock");
		Registers.registerBlock(looseBedrock, "bedrock_loose");

		// Basic Blocks//
		nileWorktable = new BlockNileWorkbench().setUnlocalizedName("nileWorktable").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerBlock(nileWorktable, "crafting_table_nile");
		foodBowl = new BlockFoodBowl().setUnlocalizedName("foodBowl").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerBlock(foodBowl, "food_bowl");

		// Flowers/Plants//
		nileBlueFlower = new BlockNileBlueFlower().setUnlocalizedName("nileBlueFlower");
		Registers.registerBlock(nileBlueFlower, "nile_flower_blue");
		nileBlackFlower = new BlockNileBlackFlower().setUnlocalizedName("nileBlackFlower");
		Registers.registerBlock(nileBlackFlower, "nile_flower_black");
		nilePinkFlower = new BlockNilePinkFlower().setUnlocalizedName("nilePinkFlower");
		Registers.registerBlock(nilePinkFlower, "nile_flower_pink");
		destroFlower = new BlockDrestroFlower().setUnlocalizedName("destroFlower");
		Registers.registerBlock(destroFlower, "destro_flower");
		vitoidPlant = new BlockVitoidCrop().setUnlocalizedName("vitoidPlant");
		Registers.registerBlock(vitoidPlant, "vitoid_plant");

		// Ores//
		nileCoalOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.0F, 5.0F, 0.6F).setUnlocalizedName("nileCoalOre");
		Registers.registerBlock(nileCoalOre, "nile_coal_ore");
		nileGrainOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("nileGrainOre");
		Registers.registerBlock(nileGrainOre, "nile_grain_ore");
		blockNileEssence = new BlockNileBlock(ZeroQuest.ZeroTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockNileEssence");
		Registers.registerBlock(blockNileEssence, "nile_essence_block");

		// Portal Parts//
		nillaxStone = new BlockNilePortalStone().setUnlocalizedName("nillaxStone").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerBlock(nillaxStone, "nillax_stone");
		portalNillax = new BlockPortalNillax().setUnlocalizedName("portalNillax");
		Registers.registerBlockWithClass(portalNillax, null, "portal_nillax");
		nileFire = new BlockNileFire().setUnlocalizedName("nileFire");
		Registers.registerBlockWithClass(nileFire, null, "nile_fire");
	}

	public static void loadDarkBlocks() {

		darkaxStone = new BlockNilePortalStone().setUnlocalizedName("darkaxStone").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerBlock(darkaxStone, "darkax_stone");
		portalDarkax = new BlockPortalDarkax().setUnlocalizedName("portalDarkax");
		Registers.registerBlockWithClass(portalDarkax, null, "portal_darkax");
		darkFire = new BlockDarkFire().setUnlocalizedName("darkFire");
		Registers.registerBlockWithClass(darkFire, null, "dark_fire");

		darkGrainOre = new BlockNileOre(ZeroQuest.DarkTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("darkGrainOre");
		Registers.registerBlock(darkGrainOre, "dark_grain_ore");
		blockDarkEssence = new BlockNileBlock(ZeroQuest.DarkTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockDarkEssence");
		Registers.registerBlock(blockDarkEssence, "dark_essence_block");
	}

	public static void loadRenderers() {

		Registers.registerBlockRender(looseBedrock, 0, Constants.modid + ":" + "bedrock_loose", "inventory");
		Registers.registerBlockRender(nileWorktable, 0, Constants.modid + ":" + "crafting_table_nile", "inventory");
		Registers.registerBlockRender(nileBlueFlower, 0, Constants.modid + ":" + "nile_flower_blue", "inventory");
		Registers.registerBlockRender(nileBlackFlower, 0, Constants.modid + ":" + "nile_flower_black", "inventory");
		Registers.registerBlockRender(nilePinkFlower, 0, Constants.modid + ":" + "nile_flower_pink", "inventory");
		Registers.registerBlockRender(destroFlower, 0, Constants.modid + ":" + "destro_flower", "inventory");
		Registers.registerBlockRender(vitoidPlant, 0, Constants.modid + ":" + "vitoid_plant", "inventory");
		Registers.registerBlockRender(nileCoalOre, 0, Constants.modid + ":" + "nile_coal_ore", "inventory");
		Registers.registerBlockRender(nileGrainOre, 0, Constants.modid + ":" + "nile_grain_ore", "inventory");
		Registers.registerBlockRender(blockNileEssence, 0, Constants.modid + ":" + "nile_essence_block", "inventory");
		Registers.registerBlockRender(foodBowl, 0, Constants.modid + ":" + "food_bowl", "inventory");
		Registers.registerBlockRender(nillaxStone, 0, Constants.modid + ":" + "nillax_stone", "inventory");
	}

	public static void loadDarkRenderers() {

		Registers.registerBlockRender(darkaxStone, 0, Constants.modid + ":" + "darkax_stone", "inventory");
		Registers.registerBlockRender(darkGrainOre, 0, Constants.modid + ":" + "dark_grain_ore", "inventory");
		Registers.registerBlockRender(blockDarkEssence, 0, Constants.modid + ":" + "dark_essence_block", "inventory");
	}
}