package common.zeroquest;


import common.zeroquest.block.BlockBedRock;
import common.zeroquest.block.BlockDarkGrainOre;
import common.zeroquest.block.BlockDrestroFlower;
import common.zeroquest.block.BlockNileB2Flower;
import common.zeroquest.block.BlockNileBFlower;
import common.zeroquest.block.BlockNileCoalOre;
import common.zeroquest.block.BlockNileCobblestone;
import common.zeroquest.block.BlockNileGrainOre;
import common.zeroquest.block.BlockNileLeaf;
import common.zeroquest.block.BlockNileLog;
import common.zeroquest.block.BlockNilePinkFlower;
import common.zeroquest.block.BlockNileSapling;
import common.zeroquest.block.BlockNileStone;
import common.zeroquest.block.BlockNileTable;
import common.zeroquest.block.BlockVitoid;
import common.zeroquest.block.portal.BlockNileFire;
import common.zeroquest.block.portal.BlockNillaxStone;
import common.zeroquest.block.portal.BlockTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks {
	
	public static Block nileStone;
	public static Block nileCobblestone;
	public static Block looseBedrock;
	public static Block nileCoalOre;
	public static Block nileGrainOre;
	public static Block darkGrainOre;
	
	public static Block nileBlueFlower;
	public static Block nilePinkFlower;
	public static Block nileBlackFlower;
	public static Block destroFlower;
	
	public static Block nileWorktable;
	public static Block nileWorktableItem;
	
	public static Block nillaxStone;
	public static Block blockTeleporter;
	public static Block nileFire;
	public static Block darlaxStone;
	public static Block blockTeleporter2;
	public static Block darlaxFire;
	
	public static Block nileLeaf;
	public static Block nileLog;
	public static Block nileSapling;
	
	public static Block vitoidPlant;
	
	   public static void load() {
		   //Natural Blocks//
	    	nileStone = new BlockNileStone(240).setUnlocalizedName("nileStone").setTextureName(ZeroQuest.modid + ":" + "nileStone");
	    	GameRegistry.registerBlock(nileStone, ZeroQuest.modid + "." + nileStone.getUnlocalizedName().substring(5));
	    	nileCobblestone = new BlockNileCobblestone(241,Material.rock).setUnlocalizedName("nileCobblestone").setTextureName(ZeroQuest.modid + ":" + "nileCobblestone");
	    	GameRegistry.registerBlock(nileCobblestone, ZeroQuest.modid + "." + nileCobblestone.getUnlocalizedName().substring(5));
	    	looseBedrock = new BlockBedRock(243).setUnlocalizedName("looseBedrock").setTextureName(ZeroQuest.modid + ":" + "looseBedrock");
	    	GameRegistry.registerBlock(looseBedrock, ZeroQuest.modid + "." + looseBedrock.getUnlocalizedName().substring(5));
	    	
	    	//Basic Blocks//
	    	nileWorktable = new BlockNileTable(244).setUnlocalizedName("nileWorktable");
	    	GameRegistry.registerBlock(nileWorktable, ZeroQuest.modid + "." + nileWorktable.getUnlocalizedName().substring(5));
	    	
	    	//TODO TreeParts//
	    	/*nileLog = new BlockNileLog(4001).setUnlocalizedName("nileLog");
	    	GameRegistry.registerBlock(nileLog, ZeroQuest.modid + "." + nileLog.getUnlocalizedName().substring(5));
	    	nileLeaf = new BlockNileLeaf(4002).setUnlocalizedName("nileLeaf");
	    	GameRegistry.registerBlock(nileLeaf, ZeroQuest.modid + "." + nileLeaf.getUnlocalizedName().substring(5));
	    	nileSapling = new BlockNileSapling(4003).setUnlocalizedName("nileSapling");
	    	GameRegistry.registerBlock(nileSapling, ZeroQuest.modid + "." + nileSapling.getUnlocalizedName().substring(5));*/
	    	
	    	//Portal Parts//
	    	nillaxStone = new BlockNillaxStone(520,Material.rock).setUnlocalizedName("nillaxStone").setTextureName(ZeroQuest.modid + ":" + "nillaxStone");
	    	GameRegistry.registerBlock(nillaxStone, ZeroQuest.modid + "." + nillaxStone.getUnlocalizedName().substring(5));
	    	blockTeleporter = new BlockTeleporter(521).setUnlocalizedName("blockTeleporter");
	    	GameRegistry.registerBlock(blockTeleporter, ZeroQuest.modid + "." + blockTeleporter.getUnlocalizedName().substring(5));
	    	nileFire = new BlockNileFire(523).setUnlocalizedName("nileFire").setTextureName(ZeroQuest.modid + ":" + "nileFire");
	    	GameRegistry.registerBlock(nileFire, ZeroQuest.modid + "." + nileFire.getUnlocalizedName().substring(5));
	    	
	    	//Flowers/Plants//
	    	nileBlueFlower = new BlockNileBFlower(630).setUnlocalizedName("nileBlueFlower").setTextureName(ZeroQuest.modid + ":" + "nileBlueFlower");
	    	GameRegistry.registerBlock(nileBlueFlower, ZeroQuest.modid + "." + nileBlueFlower.getUnlocalizedName().substring(5));
	    	nileBlackFlower = new BlockNileB2Flower(631).setUnlocalizedName("nileBlackFlower").setTextureName(ZeroQuest.modid + ":" + "nileBlackFlower");
	    	GameRegistry.registerBlock(nileBlackFlower, ZeroQuest.modid + "." + nileBlackFlower.getUnlocalizedName().substring(5));
	    	nilePinkFlower = new BlockNilePinkFlower(632).setUnlocalizedName("nilePinkFlower").setTextureName(ZeroQuest.modid + ":" + "nilePinkFlower");
	    	GameRegistry.registerBlock(nilePinkFlower, ZeroQuest.modid + "." + nilePinkFlower.getUnlocalizedName().substring(5));
	    	destroFlower = new BlockDrestroFlower(633).setUnlocalizedName("destroFlower").setTextureName(ZeroQuest.modid + ":" + "destroFlower");
	    	GameRegistry.registerBlock(destroFlower, ZeroQuest.modid + "." + destroFlower.getUnlocalizedName().substring(5));
	    	vitoidPlant = new BlockVitoid(680).setUnlocalizedName("vitoidPlant").setTextureName(ZeroQuest.modid + ":" + "vitoidPlant");
	    	GameRegistry.registerBlock(vitoidPlant, ZeroQuest.modid + "." + vitoidPlant.getUnlocalizedName().substring(5));
	    	
	       	//Ores//
	    	nileCoalOre = new BlockNileCoalOre(700,Material.rock).setUnlocalizedName("nileCoalOre").setTextureName(ZeroQuest.modid + ":" + "nileCoalOre");
	    	GameRegistry.registerBlock(nileCoalOre, ZeroQuest.modid +"." + nileCoalOre.getUnlocalizedName().substring(5));
	    	nileGrainOre = new BlockNileGrainOre(701,Material.rock).setUnlocalizedName("nileGrainOre").setTextureName(ZeroQuest.modid + ":" + "nileGrainOre");
	    	GameRegistry.registerBlock(nileGrainOre, ZeroQuest.modid +"." + nileGrainOre.getUnlocalizedName().substring(5)); 
	   }
	   
	   public static void loadDarkBlocks(){
		   
		    /*darlaxStone = new BlockDarlaxStone(800,Material.rock).setUnlocalizedName("darlaxStone").setTextureName(ZeroQuest.modid + ":" + "darlaxStone");
	    	GameRegistry.registerBlock(darlaxStone, ZeroQuest.modid + "." + darlaxStone.getUnlocalizedName().substring(5));
	    	blockTeleporter2 = new BlockTeleporter(801).setUnlocalizedName("blockTeleporter2");
	    	GameRegistry.registerBlock(blockTeleporter2, ZeroQuest.modid + "." + blockTeleporter2.getUnlocalizedName().substring(5));
	    	darlaxFire = new BlockDarkFire(802).setUnlocalizedName("darlaxFire").setTextureName(ZeroQuest.modid + ":" + "darlaxFire");
	    	GameRegistry.registerBlock(darlaxFire, ZeroQuest.modid + "." + darlaxFire.getUnlocalizedName().substring(5));*/
		   
		   darkGrainOre = new BlockDarkGrainOre(803,Material.rock).setUnlocalizedName("darkGrainOre").setTextureName(ZeroQuest.modid + ":" + "darkGrainOre");
	    	GameRegistry.registerBlock(darkGrainOre, ZeroQuest.modid +"." + darkGrainOre.getUnlocalizedName().substring(5)); 
			/*LanguageRegistry.addName(darlaxStone, "Darlax Stone");
			LanguageRegistry.addName(blockTeleporter2, "Darlax Teleporter Block");
			LanguageRegistry.addName(darlaxFire, "Darlax Fire");*/
			LanguageRegistry.addName(darkGrainOre, "Dark Grain Ore");
	    	
	    	
	   }
	   
		public static void addNames() {

			LanguageRegistry.addName(nileStone, "Nile Stone");
			LanguageRegistry.addName(nileCobblestone, "Nile Cobbletone");
			LanguageRegistry.addName(vitoidPlant, "Vitoid Plant");
			//LanguageRegistry.addName(nileLog, "Nile Log");
			//LanguageRegistry.addName(nileLeaf, "Nile Leaves");
			//LanguageRegistry.addName(nileSapling, "Nile Sapling");
			LanguageRegistry.addName(nileWorktable, "Nile Crafting Table");
			LanguageRegistry.addName(nileBlackFlower, "Nile Black Flower");
			LanguageRegistry.addName(nillaxStone, "Nillax Stone");
			LanguageRegistry.addName(blockTeleporter, "Nillax Teleporter Block");
			LanguageRegistry.addName(nileFire, "Nile Fire");
			LanguageRegistry.addName(nileBlueFlower, "Nile Blue Flower");
			LanguageRegistry.addName(destroFlower, "Destro Flower");
			LanguageRegistry.addName(nilePinkFlower, "Nile Pink Flower");
			LanguageRegistry.addName(nileCoalOre, "Nile Coal Ore");
			LanguageRegistry.addName(nileGrainOre, "Nile Grain Ore");
			LanguageRegistry.addName(looseBedrock, "Loose Bedrock");
			
			
		}
}
