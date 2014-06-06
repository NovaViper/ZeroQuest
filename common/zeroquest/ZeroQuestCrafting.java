package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nilePickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Item.stick, 'X', new ItemStack(ModItems.nileEssence), 'Z', Item.silk });
		
		//Nile Substances//
		GameRegistry.addRecipe(new ItemStack(ModItems.nileEssence, 2), new Object[]{
			" X ",
			"X X",
			"XXX",
			'X', new ItemStack(ModItems.nileDust) });
		GameRegistry.addRecipe(new ItemStack(ModItems.nileDust), new Object[]{
			"  X",
			" X ",
			"X  ",
			'X', new ItemStack(ModItems.nileGrain) });
		
		GameRegistry.addRecipe(new ItemStack(ModItems.nileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.nileDust), 'Z', Item.bone });
		
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
			'X', new ItemStack(ModItems.nileGrain), 'Z', Block.workbench });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.nillaxStone, 2), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.nileDust), 'Z', Block.obsidian });
		GameRegistry.addRecipe(new ItemStack(ModItems.vitoidSeed, 4), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'Z', ModItems.nileGrain, 'X', new ItemStack(Item.seeds) });
		GameRegistry.addRecipe(new ItemStack(ModItems.animalCage, 1), new Object[]{
			" Y ",
			"YXY",
			" Y ",
			'X', Item.egg, 'Y', ModItems.nileDust});
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Item.bone), 'X', ModItems.zertumMeatRaw });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Item.bone), 'X', new ItemStack(Item.porkRaw) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Item.bone), 'X', new ItemStack(Item.beefRaw) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Item.bone), 'X', new ItemStack(Item.chickenRaw) });
		GameRegistry.addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Item.bone), 'X', new ItemStack(Item.rottenFlesh) });
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, 15), ModItems.nileBone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Item.fireballCharge);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Item.flintAndSteel);
		
		//Smelting//
		GameRegistry.addSmelting(ModBlocks.nileCobblestone.blockID, new ItemStack(ModBlocks.nileStone), 0.1F);
		//GameRegistry.addSmelting(ModItems.nileCoal.itemID, new ItemStack(ModBlocks.nileLog), 0.0F);
	}
	
	public static void loadDarkRecipes() 
	{
		//DarkWeapons
		GameRegistry.addRecipe(new ItemStack(ModItems.darkSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkPickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Item.stick });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Item.stick });
		/*GameRegistry.addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Item.stick, 'X', new ItemStack(ModItems.darkEssence), 'Z', Item.silk });*/
		
		//Dark Nile Substances//
		GameRegistry.addRecipe(new ItemStack(ModItems.darkEssence, 2), new Object[]{
			" X ",
			"X X",
			"XXX",
			'X', new ItemStack(ModItems.darkDust) });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkDust), new Object[]{
			"  X",
			" X ",
			"X  ",
			'X', new ItemStack(ModItems.darkGrain) });
		//Others//
		GameRegistry.addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.darkDust), 'Z', Block.workbench });
		GameRegistry.addRecipe(new ItemStack(ModItems.darkNileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', ModItems.nileBone });
		/*GameRegistry.addRecipe(new ItemStack(ModBlocks.darlaxStone, 2), new Object[]{ TODO
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Block.obsidian });*/
		GameRegistry.addRecipe(new ItemStack(ModItems.animalCage, 1), new Object[]{
			" Y ",
			"YXY",
			" Y ",
			'X', Item.egg, 'Y', ModItems.darkDust });
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, 15), ModItems.darkNileBone);
		//GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Item.fireballCharge);
		//GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Item.flintAndSteel);
	}
}
