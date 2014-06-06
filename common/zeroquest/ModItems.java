package common.zeroquest;

import common.zeroquest.item.CustomEntityEgg;
import common.zeroquest.item.ItemCSaddle;
import common.zeroquest.item.ItemCage;
import common.zeroquest.item.ItemDogTreat;
import common.zeroquest.item.ItemDust;
import common.zeroquest.item.ItemEssence;
import common.zeroquest.item.ItemGrain;
import common.zeroquest.item.ItemNileArmor;
import common.zeroquest.item.ItemNileAxe;
import common.zeroquest.item.ItemNileBone;
import common.zeroquest.item.ItemNileBow;
import common.zeroquest.item.ItemNileCoal;
import common.zeroquest.item.ItemNileHoe;
import common.zeroquest.item.ItemNilePickaxe;
import common.zeroquest.item.ItemNileSeed;
import common.zeroquest.item.ItemNileShovel;
import common.zeroquest.item.ItemNileSpark;
import common.zeroquest.item.ItemNileSword;
import common.zeroquest.item.ZeroQuestFood;
import common.zeroquest.item.dark.ItemDarkAxe;
import common.zeroquest.item.dark.ItemDarkHoe;
import common.zeroquest.item.dark.ItemDarkPickaxe;
import common.zeroquest.item.dark.ItemDarkShovel;
import common.zeroquest.item.dark.ItemDarkSword;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;

public class ModItems {

	public static Item nileSword;
	public static Item nileAxe;
	public static Item nilePickaxe;
	public static Item nileShovel;
	public static Item nileHoe;
	public static Item nileBow;
	//public static Item nileGun;
	
	public static Item nileHelmet;
	public static Item nileChest;
	public static Item nileLegs;
	public static Item nileBoots;
	
	public static Item zertumMeatRaw;
	public static Item zertumMeatCooked;
	public static Item vitoidSeed;
	public static Item vitoidFruit;
	
	public static Item dogTreat;
	public static Item nileEssence;
	public static Item nileDust;
	public static Item nileGrain;
	public static Item nileCoal;
	public static Item entityEgg;
	public static Item animalCage;
	public static Item nileSpark;
	public static Item nileBone;
	public static Item nileSaddle;
	
	//DARK ELEMENT//
	public static Item darkSword;
	public static Item darkAxe;
	public static Item darkPickaxe;
	public static Item darkShovel;
	public static Item darkHoe;
	public static Item darkBow;
	
	public static Item darkEssence;
	public static Item darkDust;
	public static Item darkGrain;
	public static Item darkNileBone;
	
	public static Item darkSpark;
	
	
	
	public static void load() {    	
    	//Tools/Weapons//
    	nileSword = new ItemNileSword(5000, ZeroQuest.nileEssenceMaterial).setUnlocalizedName("nileSword").setTextureName(ZeroQuest.modid + ":" + "nileSword");
    	nileAxe = new ItemNileAxe(5001, ZeroQuest.nileEssenceMaterial).setUnlocalizedName("nileAxe").setTextureName(ZeroQuest.modid + ":" + "nileAxe");
    	nilePickaxe = new ItemNilePickaxe(5002, ZeroQuest.nileEssenceMaterial).setUnlocalizedName("nilePickaxe").setTextureName(ZeroQuest.modid + ":" + "nilePickaxe");
    	nileShovel = new ItemNileShovel(5003, ZeroQuest.nileEssenceMaterial).setUnlocalizedName("nileShovel").setTextureName(ZeroQuest.modid + ":" + "nileShovel");
    	nileHoe = new ItemNileHoe(5004, ZeroQuest.nileEssenceMaterial).setUnlocalizedName("nileHoe").setTextureName(ZeroQuest.modid + ":" + "nileHoe");
    	nileBow = new ItemNileBow(5005).setUnlocalizedName("nileBow");
    	//nileGun = new ItemNileGun(5010).setUnlocalizedName("nileGun").setTextureName(ZeroQuest.modid + ":" + "nileGun");
    	
    	//Basics//
    	nileEssence = new ItemEssence(5100).setUnlocalizedName("nileEssence").setTextureName(ZeroQuest.modid + ":" + "nileEssence");
    	nileDust = new ItemDust(5101).setUnlocalizedName("nileDust").setTextureName(ZeroQuest.modid + ":" + "nileDust");
       	nileGrain = new ItemGrain(5102).setUnlocalizedName("nileGrain").setTextureName(ZeroQuest.modid + ":" + "nileGrain");
       	nileCoal = new ItemNileCoal(5103).setUnlocalizedName("nileCoal").setTextureName(ZeroQuest.modid + ":" + "nileCoal");
    	dogTreat = new ItemDogTreat(5104).setUnlocalizedName("dogTreat").setTextureName(ZeroQuest.modid + ":" + "dogTreat");
    	nileBone = new ItemNileBone(5105).setUnlocalizedName("nileBone").setTextureName(ZeroQuest.modid + ":" + "nileBone");
    	animalCage = new ItemCage(5106).setUnlocalizedName("animalCage").setTextureName(ZeroQuest.modid + ":" + "animalCage");
    	//nileSaddle = new ItemCSaddle(5107).setUnlocalizedName("nileSaddle").setTextureName(ZeroQuest.modid + ":" + "nileSaddle");
    	
    	//Portal Sparks//
    	nileSpark = new ItemNileSpark(5140).setUnlocalizedName("nileSpark").setTextureName(ZeroQuest.modid + ":" + "nileSpark");
    	
    	//Armor//
    	/*nileHelmet = new ItemNileArmor(5200, ZeroQuest.nileEssenceMaterial2, 0, 0, "Nile").setUnlocalizedName("nileHelmet");
    	nileChest = new ItemNileArmor(5201, ZeroQuest.nileEssenceMaterial2, 0, 1, "Nile").setUnlocalizedName("nileChest");
    	nileLegs = new ItemNileArmor(5202, ZeroQuest.nileEssenceMaterial2, 0, 2, "Nile").setUnlocalizedName("nileLegs");
    	nileBoots = new ItemNileArmor(5203, ZeroQuest.nileEssenceMaterial2, 0, 3, "Nile").setUnlocalizedName("nileBoots");*/
    	
    	//Food/Crops//
    	zertumMeatRaw = new ZeroQuestFood(5300, 6, 1.5F, true).setUnlocalizedName("zertumMeatRaw").setTextureName(ZeroQuest.modid + ":" + "zertumMeat_raw");
    	zertumMeatCooked = new ZeroQuestFood(5301, 8, 2.5F, true).setUnlocalizedName("zertumMeatCooked").setTextureName(ZeroQuest.modid + ":" + "zertumMeat_cooked");
    	vitoidSeed = new ItemNileSeed(5302, 4, 0.5F, ModBlocks.vitoidPlant.blockID, Block.tilledField.blockID).setUnlocalizedName("vitoidSeed").setTextureName(ZeroQuest.modid + ":" + "vitoidSeed");
    	vitoidFruit = new ZeroQuestFood(5303, 4, 0.5F, false).setUnlocalizedName("vitoidFruit").setTextureName(ZeroQuest.modid + ":" + "vitoidFruit");
    	
    	//Entity Eggs//
    	entityEgg = new CustomEntityEgg(5360).setUnlocalizedName("entityEgg").setTextureName(ZeroQuest.modid + ":" + "entityEgg");
	}
	
	public static void loadDarkItems(){
		
		darkSword = new ItemDarkSword(6000, ZeroQuest.darkEssenceMaterial).setUnlocalizedName("darkSword").setTextureName(ZeroQuest.modid + ":" + "darkSword");
    	darkAxe = new ItemDarkAxe(6001, ZeroQuest.darkEssenceMaterial).setUnlocalizedName("darkAxe").setTextureName(ZeroQuest.modid + ":" + "darkAxe");
    	darkPickaxe = new ItemDarkPickaxe(6002, ZeroQuest.darkEssenceMaterial).setUnlocalizedName("darkPickaxe").setTextureName(ZeroQuest.modid + ":" + "darkPickaxe");
    	darkShovel = new ItemDarkShovel(6003, ZeroQuest.darkEssenceMaterial).setUnlocalizedName("darkShovel").setTextureName(ZeroQuest.modid + ":" + "darkShovel");
    	darkHoe = new ItemDarkHoe(6004, ZeroQuest.darkEssenceMaterial).setUnlocalizedName("darkHoe").setTextureName(ZeroQuest.modid + ":" + "darkHoe");
    	
    	//darkSpark = new ItemDarkSpark(6090).setUnlocalizedName("darkSpark").setTextureName(ZeroQuest.modid + ":" + "darkSpark");
    	
       	darkEssence = new ItemEssence(6100).setUnlocalizedName("darkEssence").setTextureName(ZeroQuest.modid + ":" + "darkEssence");
    	darkDust = new ItemDust(6101).setUnlocalizedName("darkDust").setTextureName(ZeroQuest.modid + ":" + "darkDust");
    	darkGrain = new ItemGrain(6102).setUnlocalizedName("darkGrain").setTextureName(ZeroQuest.modid + ":" + "darkGrain");
    	darkNileBone = new ItemNileBone(6103).setUnlocalizedName("nileDarkBone").setTextureName(ZeroQuest.modid + ":" + "nileDarkBone");
		LanguageRegistry.addName(darkEssence, "Dark Essence");
		LanguageRegistry.addName(darkDust, "Dark Dust");
		LanguageRegistry.addName(darkGrain, "Dark Grain");
		LanguageRegistry.addName(darkSword, "Dark Sword");
		LanguageRegistry.addName(darkAxe, "Dark Axe");
		LanguageRegistry.addName(darkPickaxe, "Dark Pickaxe");
		LanguageRegistry.addName(darkShovel, "Dark Shovel");
		LanguageRegistry.addName(darkHoe, "Dark Hoe");
		LanguageRegistry.addName(darkNileBone, "Dark Nile Bone");
		//LanguageRegistry.addName(darkSpark, "Dark Spark");
	}
	
	
	
	public static void addNames() {
		LanguageRegistry.addName(nileSword, "Nile Sword");
		LanguageRegistry.addName(nileAxe, "Nile Axe");
		LanguageRegistry.addName(nilePickaxe, "Nile Pickaxe");
		LanguageRegistry.addName(nileShovel, "Nile Shovel");
		LanguageRegistry.addName(nileHoe, "Nile Hoe");
		LanguageRegistry.addName(vitoidSeed, "Vitoid Seed");
		LanguageRegistry.addName(vitoidFruit, "Vitoid Fruit");
		LanguageRegistry.addName(nileEssence, "Nile Essence");
		LanguageRegistry.addName(nileDust, "Nile Dust");
		LanguageRegistry.addName(nileGrain, "Nile Grain");
		LanguageRegistry.addName(nileBow, "Nile Bow");
		LanguageRegistry.addName(nileCoal, "Nile Coal");
		LanguageRegistry.addName(dogTreat, "Dog Treat");
		LanguageRegistry.addName(nileBone, "Nile Bone");
		LanguageRegistry.addName(animalCage, "Animal Cage");
		LanguageRegistry.addName(nileSpark, "Nile Spark");
		//LanguageRegistry.addName(nileSaddle, "Nile Saddle");
		LanguageRegistry.addName(zertumMeatRaw, "Raw Zertum Meat");
		LanguageRegistry.addName(zertumMeatCooked, "Cooked Zertum Meat");
		LanguageRegistry.addName(entityEgg, "Spawn");
		
		//LanguageRegistry.addName(nileGun, "Nile Gun");
		/*LanguageRegistry.addName(nileHelmet, "Nile Helmet");
		LanguageRegistry.addName(nileChest, "Nile Chestplate");
		LanguageRegistry.addName(nileLegs, "Nile Leggings");
		LanguageRegistry.addName(nileBoots, "Nile Boots");*/
		
	}

}
