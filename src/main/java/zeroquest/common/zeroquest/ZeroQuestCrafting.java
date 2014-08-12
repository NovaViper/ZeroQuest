package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ZeroQuestCrafting 
{

	public static void loadRecipes() 
	{   //Weapons//
		GameRegistry.addRecipe(new ItemStack(ModItems.nileSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nilePickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Items.stick, 'X', new ItemStack(ModItems.nileEssence), 'Z', Items.string });
		
		//Nile Substances//
		GameRegistry.addRecipe(new ItemStack(ModItems.nileEssence, 2), new Object[]{
			"   ",
			"   ",
			"XXX",
			'X', new ItemStack(ModItems.nileDust) });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.nileGrain) });
		
		GameRegistry.addRecipe(new ItemStack(ModItems.nileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.nileDust), 'Z', Items.bone });
		
		//Armor//
		/*GameRegistry.addRecipe(new ItemStack(ModItems.nileHelmet), new Object[]{
			"   ",
			"XXX",
			"X X",
			'X', new ItemStack(ModItems.nileEssence) });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileChest), new Object[]{
			"X X",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence) });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileLegs), new Object[]{
			"XXX",
			"X X",
			"X X",
			'X', new ItemStack(ModItems.nileEssence) });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileBoots), new Object[]{
			"   ",
			"X X",
			"X X",
			'X', new ItemStack(ModItems.nileEssence) });*/
		GameRegistry.addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.nileGrain), 'Z', Blocks.crafting_table });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.nillaxStone), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence)});
		GameRegistry.addRecipe(new ItemStack(ModItems.vitoidSeed, 4), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'Z', ModItems.nileGrain, 'X', new ItemStack(Items.wheat_seeds) });
		/*GameRegistry.addRecipe(new ItemStack(ModItems.animalCage, 1), new Object[]{
			" Y ",
			"YXY",
			" Y ",
			'X', Item.egg, 'Y', ModItems.nileDust});*/
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.zertumMeatRaw });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.jakanMeatRaw });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.porkchop) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.beef) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.chicken) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.rotten_flesh) });
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.nileBone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.nileGrain, Items.gunpowder);		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.fire_charge);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.flint_and_steel);
		
		//Smelting// //TODO
		GameRegistry.addSmelting(ModBlocks.nileCobblestone, new ItemStack(ModBlocks.nileStone), 0.1F);
		//GameRegistry.addSmelting(ModItems.nileCoal.itemID, new ItemStack(ModBlocks.nileLog), 0.0F);

	}
	
	public static void loadDarkRecipes() 
	{
		//DarkWeapons
		GameRegistry.addRecipe(new ItemStack(ModItems.darkSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkPickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		/*GameRegistry.addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Item.stick, 'X', new ItemStack(ModItems.darkEssence), 'Z', Item.silk });*/
		
		//Dark Nile Substances//
		GameRegistry.addRecipe(new ItemStack(ModItems.darkEssence, 2), new Object[]{
			"   ",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.darkDust) });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.darkGrain) });
		//Others//
		GameRegistry.addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.darkDust), 'Z', Blocks.crafting_table });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkNileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', ModItems.nileBone });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.darkaxStone, 2), new Object[]{ //TODO
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Blocks.obsidian });
		/*GameRegistry.addRecipe(new ItemStack(ModItems.animalCage, 1), new Object[]{
			" Y ",
			"YXY",
			" Y ",
			'X', Item.egg, 'Y', ModItems.darkDust });*/
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.darkNileBone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.darkGrain, Items.gunpowder);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.fire_charge);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.flint_and_steel);
	}
}