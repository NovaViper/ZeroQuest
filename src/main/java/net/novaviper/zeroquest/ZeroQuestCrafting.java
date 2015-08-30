package net.novaviper.zeroquest;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.novaviper.zeroquest.common.lib.Registers;

public class ZeroQuestCrafting {

	//@formatter:off
	public static void loadRecipes()
	{   //Weapons//
		Registers.addRecipe(new ItemStack(ModItems.nileSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.nileAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.nilePickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.nileSpade), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.nileHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Items.stick, 'X', new ItemStack(ModItems.nileEssence), 'Z', Items.string });
		//Nile Blocks//
		Registers.addRecipe(new ItemStack(ModBlocks.blockNileEssence), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence) });
		//Nile Substances//
		Registers.addRecipe(new ItemStack(ModItems.nileEssence, 2), new Object[]{
			"   ",
			"   ",
			"XXX",
			'X', new ItemStack(ModItems.nileDust) });
		Registers.addRecipe(new ItemStack(ModItems.nileDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.nileGrain) });
		Registers.addRecipe(new ItemStack(ModItems.nileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.nileDust), 'Z', Items.bone });
		Registers.addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.nileGrain), 'Z', Blocks.crafting_table });
		Registers.addRecipe(new ItemStack(ModBlocks.nillaxStone, 2), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.getItemFromBlock(Blocks.obsidian)});
		Registers.addRecipe(new ItemStack(ModItems.vitoidSeed, 4), new Object[]{
			" X ",
			"XZX",
			" X ",
			'X', ModItems.nileGrain, 'Z', new ItemStack(Items.wheat_seeds) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.zertumMeatRaw });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.jakanMeatRaw });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.porkchop) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.beef) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.chicken) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.rotten_flesh) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.mutton) });
		Registers.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.rabbit) });
		Registers.addRecipe(new ItemStack(ModBlocks.foodBowl), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'Z', new ItemStack(ModItems.nileBone), 'X', new ItemStack(Items.iron_ingot) });
		Registers.addRecipe(new ItemStack(ModItems.toy), new Object[]{
			" X ",
			"XZX",
			" X ",
			'X', new ItemStack(Items.leather), 'Z', Items.slime_ball });
		Registers.addRecipe(new ItemStack(ModItems.commandSeal), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bow), 'X', new ItemStack(Item.getItemFromBlock(Blocks.stone)) });
		Registers.addRecipe(new ItemStack(ModItems.commandSeal), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bow), 'X', new ItemStack(Item.getItemFromBlock(Blocks.cobblestone)) });
		//Bits\\
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{ //NAV: Bit Recipes
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.rotten_flesh) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.chicken) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.beef) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.porkchop) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.mutton) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(Items.rabbit) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(ModItems.zertumMeatRaw) });
		Registers.addRecipe(new ItemStack(ModItems.microBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.iron_ingot), 'X', new ItemStack(ModItems.jakanMeatRaw) });
		Registers.addRecipe(new ItemStack(ModItems.megaBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.golden_apple), 'X', new ItemStack(ModItems.microBit) });
		Registers.addRecipe(new ItemStack(ModItems.omegaBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.blaze_powder), 'X', new ItemStack(ModItems.megaBit) });
		Registers.addRecipe(new ItemStack(ModItems.alphaBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.diamond), 'X', new ItemStack(ModItems.omegaBit) });
		Registers.addRecipe(new ItemStack(ModItems.evoBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(ModItems.nileEssence), 'X', new ItemStack(ModItems.alphaBit) });
		Registers.addRecipe(new ItemStack(ModItems.deltaBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.redstone), 'X', new ItemStack(ModItems.evoBit) });
		Registers.addRecipe(new ItemStack(ModItems.pettraBit), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.emerald), 'X', new ItemStack(ModItems.deltaBit) });

		Registers.addSmelting(new ItemStack(ModItems.jakanMeatRaw), new ItemStack(ModItems.jakanMeatCooked), 2);
		Registers.addSmelting(new ItemStack(ModItems.kortorMeatRaw), new ItemStack(ModItems.kortorMeatCooked), 2);
		Registers.addSmelting(new ItemStack(ModItems.riggatorMeatRaw), new ItemStack(ModItems.riggatorMeatCooked), 2);
		Registers.addSmelting(new ItemStack(ModItems.zertumMeatRaw), new ItemStack(ModItems.zertumMeatCooked), 2);
		Registers.addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.nileBone);
		Registers.addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.nileGrain, Items.gunpowder);
		Registers.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.fire_charge);
		Registers.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.flint_and_steel);

	}

	public static void loadDarkRecipes()
	{
		//DarkWeapons
		Registers.addRecipe(new ItemStack(ModItems.darkSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.darkAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.darkPickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.darkShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.darkHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		Registers.addRecipe(new ItemStack(ModItems.darkBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Items.stick, 'X', new ItemStack(ModItems.darkEssence), 'Z', Items.string });
		//Dark Blocks//
		Registers.addRecipe(new ItemStack(ModBlocks.blockDarkEssence), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence) });
		//Dark Substances//
		Registers.addRecipe(new ItemStack(ModItems.darkEssence, 2), new Object[]{
			"   ",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.darkDust) });
		Registers.addRecipe(new ItemStack(ModItems.darkDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.darkGrain) });
		//Others//
		Registers.addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.darkGrain), 'Z', Blocks.crafting_table });
		Registers.addRecipe(new ItemStack(ModItems.darkBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', ModItems.nileBone });
		Registers.addRecipe(new ItemStack(ModItems.darkBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', Items.bone });
		Registers.addRecipe(new ItemStack(ModBlocks.darkaxStone, 2), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Blocks.obsidian });
		Registers.addRecipe(new ItemStack(ModBlocks.foodBowl), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'Z', new ItemStack(ModItems.darkBone), 'X', new ItemStack(Items.iron_ingot) });

		Registers.addSmelting(new ItemStack(ModItems.kurrMeatRaw), new ItemStack(ModItems.kurrMeatCooked), 2);
		Registers.addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.darkBone);
		Registers.addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.darkGrain, Items.gunpowder);
		Registers.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.fire_charge);
		Registers.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.flint_and_steel);
	}
}