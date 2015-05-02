package common.zeroquest.lib;

import net.minecraftforge.oredict.OreDictionary;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;

public class OreDic {

	public static void loadOre() {
		OreDictionary.registerOre("oreNCoal", ModBlocks.nileCoalOre);
		OreDictionary.registerOre("ignotNCoal", ModItems.nileCoal);
		OreDictionary.registerOre("oreNGrain", ModBlocks.nileGrainOre);
		OreDictionary.registerOre("ingotNGrain", ModItems.nileGrain);
		OreDictionary.registerOre("blockNEssence", ModBlocks.blockNileEssence);

	}

	public static void loadDarkOre() {
		OreDictionary.registerOre("oreDGrain", ModBlocks.darkGrainOre);
		OreDictionary.registerOre("ingotDGrain", ModItems.darkGrain);
		OreDictionary.registerOre("blockDEssence", ModBlocks.blockDarkEssence);
	}
}