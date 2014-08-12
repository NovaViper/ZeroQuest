package common.zeroquest.lib;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;

public class OreDic {

	   public static void load() {
		//OreDictionary.registerOre("ingotCoal", Items.coal);
		
		OreDictionary.registerOre("oreNCoal", ModBlocks.nileCoalOre);
		OreDictionary.registerOre("ignotNCoal", ModItems.nileCoal);
   		OreDictionary.registerOre("oreNGrain", ModBlocks.nileGrainOre);
   		OreDictionary.registerOre("ingotNGrain", ModItems.nileGrain);

	}
	   public static void loadDarkOre(){
		OreDictionary.registerOre("oreDGrain", ModBlocks.darkGrainOre);
		OreDictionary.registerOre("ingotDGrain", ModItems.darkGrain);
	   }
}