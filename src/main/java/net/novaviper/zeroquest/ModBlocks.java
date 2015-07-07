package net.novaviper.zeroquest;

import net.minecraft.block.Block;
import net.novaviper.zeroquest.common.block.BlockBedRock;
import net.novaviper.zeroquest.common.block.BlockDrestroFlower;
import net.novaviper.zeroquest.common.block.BlockFoodBowl;
import net.novaviper.zeroquest.common.block.BlockNileBlackFlower;
import net.novaviper.zeroquest.common.block.BlockNileBlock;
import net.novaviper.zeroquest.common.block.BlockNileBlueFlower;
import net.novaviper.zeroquest.common.block.BlockNileOre;
import net.novaviper.zeroquest.common.block.BlockNilePinkFlower;
import net.novaviper.zeroquest.common.block.BlockNileWorkbench;
import net.novaviper.zeroquest.common.block.BlockVitoidCrop;
import net.novaviper.zeroquest.common.block.portal.BlockDarkFire;
import net.novaviper.zeroquest.common.block.portal.BlockNileFire;
import net.novaviper.zeroquest.common.block.portal.BlockNilePortalStone;
import net.novaviper.zeroquest.common.block.portal.BlockPortalDarkax;
import net.novaviper.zeroquest.common.block.portal.BlockPortalNillax;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Registers;

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
		Registers.addBlock(looseBedrock, "bedrock_loose");

		// Basic Blocks//
		nileWorktable = new BlockNileWorkbench().setUnlocalizedName("nileWorktable").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addBlock(nileWorktable, "crafting_table_nile");
		foodBowl = new BlockFoodBowl().setUnlocalizedName("foodBowl").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addBlock(foodBowl, "food_bowl");

		// Flowers/Plants//
		nileBlueFlower = new BlockNileBlueFlower().setUnlocalizedName("nileBlueFlower");
		Registers.addBlock(nileBlueFlower, "nile_flower_blue");
		nileBlackFlower = new BlockNileBlackFlower().setUnlocalizedName("nileBlackFlower");
		Registers.addBlock(nileBlackFlower, "nile_flower_black");
		nilePinkFlower = new BlockNilePinkFlower().setUnlocalizedName("nilePinkFlower");
		Registers.addBlock(nilePinkFlower, "nile_flower_pink");
		destroFlower = new BlockDrestroFlower().setUnlocalizedName("destroFlower");
		Registers.addBlock(destroFlower, "destro_flower");
		vitoidPlant = new BlockVitoidCrop().setUnlocalizedName("vitoidPlant");
		Registers.addBlock(vitoidPlant, "vitoid_plant");

		// Ores//
		nileCoalOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.0F, 5.0F, 0.6F).setUnlocalizedName("nileCoalOre");
		Registers.addBlock(nileCoalOre, "nile_coal_ore");
		nileGrainOre = new BlockNileOre(ZeroQuest.ZeroTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("nileGrainOre");
		Registers.addBlock(nileGrainOre, "nile_grain_ore");
		blockNileEssence = new BlockNileBlock(ZeroQuest.ZeroTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockNileEssence");
		Registers.addBlock(blockNileEssence, "nile_essence_block");

		// Portal Parts//
		nillaxStone = new BlockNilePortalStone().setUnlocalizedName("nillaxStone").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addBlock(nillaxStone, "nillax_stone");
		portalNillax = new BlockPortalNillax().setUnlocalizedName("portalNillax");
		Registers.addBlockWithClass(portalNillax, null, "portal_nillax");
		nileFire = new BlockNileFire().setUnlocalizedName("nileFire");
		Registers.addBlockWithClass(nileFire, null, "nile_fire");
	}

	public static void loadDarkBlocks() {

		darkaxStone = new BlockNilePortalStone().setUnlocalizedName("darkaxStone").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addBlock(darkaxStone, "darkax_stone");
		portalDarkax = new BlockPortalDarkax().setUnlocalizedName("portalDarkax");
		Registers.addBlockWithClass(portalDarkax, null, "portal_darkax");
		darkFire = new BlockDarkFire().setUnlocalizedName("darkFire");
		Registers.addBlockWithClass(darkFire, null, "dark_fire");

		darkGrainOre = new BlockNileOre(ZeroQuest.DarkTab, 3.5F, 5.2F, 0.6F).setUnlocalizedName("darkGrainOre");
		Registers.addBlock(darkGrainOre, "dark_grain_ore");
		blockDarkEssence = new BlockNileBlock(ZeroQuest.DarkTab, 5.0F, 30.0F, 0F).setUnlocalizedName("blockDarkEssence");
		Registers.addBlock(blockDarkEssence, "dark_essence_block");
	}

	public static void loadRenderers() {

		Registers.addBlockRender(looseBedrock, 0, Constants.modid + ":" + "bedrock_loose", "inventory");
		Registers.addBlockRender(nileWorktable, 0, Constants.modid + ":" + "crafting_table_nile", "inventory");
		Registers.addBlockRender(nileBlueFlower, 0, Constants.modid + ":" + "nile_flower_blue", "inventory");
		Registers.addBlockRender(nileBlackFlower, 0, Constants.modid + ":" + "nile_flower_black", "inventory");
		Registers.addBlockRender(nilePinkFlower, 0, Constants.modid + ":" + "nile_flower_pink", "inventory");
		Registers.addBlockRender(destroFlower, 0, Constants.modid + ":" + "destro_flower", "inventory");
		Registers.addBlockRender(vitoidPlant, 0, Constants.modid + ":" + "vitoid_plant", "inventory");
		Registers.addBlockRender(nileCoalOre, 0, Constants.modid + ":" + "nile_coal_ore", "inventory");
		Registers.addBlockRender(nileGrainOre, 0, Constants.modid + ":" + "nile_grain_ore", "inventory");
		Registers.addBlockRender(blockNileEssence, 0, Constants.modid + ":" + "nile_essence_block", "inventory");
		Registers.addBlockRender(foodBowl, 0, Constants.modid + ":" + "food_bowl", "inventory");
		Registers.addBlockRender(nillaxStone, 0, Constants.modid + ":" + "nillax_stone", "inventory");
	}

	public static void loadDarkRenderers() {

		Registers.addBlockRender(darkaxStone, 0, Constants.modid + ":" + "darkax_stone", "inventory");
		Registers.addBlockRender(darkGrainOre, 0, Constants.modid + ":" + "dark_grain_ore", "inventory");
		Registers.addBlockRender(blockDarkEssence, 0, Constants.modid + ":" + "dark_essence_block", "inventory");
	}
}