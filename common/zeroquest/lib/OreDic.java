package common.zeroquest.lib;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDic {

	   public static void load() {
		OreDictionary.registerOre("oreCoal", Block.oreCoal);
		OreDictionary.registerOre("ingotCoal", Item.coal);
		
		OreDictionary.registerOre("oreNCoal", ModBlocks.nileCoalOre);
		OreDictionary.registerOre("ignotNCoal", ModItems.nileCoal);
   		OreDictionary.registerOre("oreNGrain", ModBlocks.nileGrainOre);
   		OreDictionary.registerOre("ingotNGrain", ModItems.nileDust);
	   }
}
