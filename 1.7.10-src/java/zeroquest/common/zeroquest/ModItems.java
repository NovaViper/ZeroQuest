package common.zeroquest;

import common.zeroquest.item.ItemAcidBucket;
import common.zeroquest.item.ItemCage;
import common.zeroquest.item.ItemDarkSpark;
import common.zeroquest.item.ItemDogTreat;
import common.zeroquest.item.ItemDust;
import common.zeroquest.item.ItemEssence;
import common.zeroquest.item.ItemFPoisonball;
import common.zeroquest.item.ItemGrain;
import common.zeroquest.item.ItemNileArmor;
import common.zeroquest.item.ItemNileAxe;
import common.zeroquest.item.ItemNileBone;
import common.zeroquest.item.ItemNileBow;
import common.zeroquest.item.ItemNileCoal;
import common.zeroquest.item.ItemNileGrenade;
import common.zeroquest.item.ItemNileHoe;
import common.zeroquest.item.ItemNilePickaxe;
import common.zeroquest.item.ItemNileSeed;
import common.zeroquest.item.ItemNileShovel;
import common.zeroquest.item.ItemNileSpark;
import common.zeroquest.item.ItemNileStoneSlab;
import common.zeroquest.item.ItemNileSword;
import common.zeroquest.item.ItemNiliBucket;
import common.zeroquest.item.KurrSeed;
import common.zeroquest.item.ZQFood;
import common.zeroquest.item.VitoidFruit;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;

public class ModItems {

	public static Item nileSword;
	public static Item nileAxe;
	public static Item nilePickaxe;
	public static Item nileShovel;
	public static Item nileHoe;
	public static Item nileBow;
	public static Item nileGrenade;
	
	public static Item nileHelmet;
	public static Item nileChest;
	public static Item nileLegs;
	public static Item nileBoots;
	
	public static Item zertumMeatRaw;
	public static Item zertumMeatCooked;
	public static Item jakanMeatRaw;
	public static Item jakanMeatCooked;
	public static Item vitoidSeed;
	public static Item vitoidFruit;
	
	public static Item dogTreat;
	public static Item nileEssence;
	public static Item nileDust;
	public static Item nileGrain;
	public static Item nileCoal;
	public static Item animalCage;
	public static Item nileSpark;
	public static Item nileBone;
	
	public static Item FPoisonball;
	
	//DARK ELEMENT//
	public static Item darkSword;
	public static Item darkAxe;
	public static Item darkPickaxe;
	public static Item darkShovel;
	public static Item darkHoe;
	
	public static Item darkEssence;
	public static Item darkDust;
	public static Item darkGrain;
	public static Item darkNileBone;
	public static Item kurrSeeds;
	
	public static Item darkSpark;
	
	public static void load() {    	
    	//Tools/Weapons//
    	nileSword = new ItemNileSword(ZeroQuest.nileEssence).setUnlocalizedName("nileSword").setTextureName(ZeroQuest.modid + ":" + "nileSword").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileSword, "nile_sword");
    	nileAxe = new ItemNileAxe(ZeroQuest.nileEssence).setUnlocalizedName("nileAxe").setTextureName(ZeroQuest.modid + ":" + "nileAxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileAxe, "nile_axe");
    	nilePickaxe = new ItemNilePickaxe(ZeroQuest.nileEssence).setUnlocalizedName("nilePickaxe").setTextureName(ZeroQuest.modid + ":" + "nilePickaxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nilePickaxe, "nile_pickaxe");
    	nileShovel = new ItemNileShovel(ZeroQuest.nileEssence).setUnlocalizedName("nileShovel").setTextureName(ZeroQuest.modid + ":" + "nileShovel").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileShovel, "nile_shovel");
    	nileHoe = new ItemNileHoe(ZeroQuest.nileEssence).setUnlocalizedName("nileHoe").setTextureName(ZeroQuest.modid + ":" + "nileHoe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileHoe, "nile_Hoe");
    	nileBow = new ItemNileBow().setUnlocalizedName("nileBow").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileBow, "nile_Bow");
    	nileGrenade = new ItemNileGrenade().setUnlocalizedName("nileGrenade").setTextureName(ZeroQuest.modid + ":" + "nileGrenade");
    	register(nileGrenade, "nile_grenade");
    	
    	//itemNileStoneSlab = new ItemNileStoneSlab(ModBlocks.nileStone).setUnlocalizedName("itemNileStoneSlab");
    	
    	//Basics//
    	nileEssence = new ItemEssence().setUnlocalizedName("nileEssence").setTextureName(ZeroQuest.modid + ":" + "nileEssence").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileEssence, "nile_essence");
    	nileDust = new ItemDust().setUnlocalizedName("nileDust").setTextureName(ZeroQuest.modid + ":" + "nileDust").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileDust, "nile_dust");
    	nileGrain = new ItemGrain().setUnlocalizedName("nileGrain").setTextureName(ZeroQuest.modid + ":" + "nileGrain").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileGrain, "nile_grain");
    	nileCoal = new ItemNileCoal().setUnlocalizedName("nileCoal").setTextureName(ZeroQuest.modid + ":" + "nileCoal").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileCoal, "nile_coal");
    	dogTreat = new ItemDogTreat().setUnlocalizedName("dogTreat").setTextureName(ZeroQuest.modid + ":" + "dogTreat").setCreativeTab(ZeroQuest.ZeroTab);
    	register(dogTreat, "dog_treat");
    	nileBone = new ItemNileBone().setUnlocalizedName("nileBone").setTextureName(ZeroQuest.modid + ":" + "nileBone").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileBone, "nile_bone");
    	//animalCage = new ItemCage().setUnlocalizedName("animalCage").setTextureName(ZeroQuest.modid + ":" + "animalCage");
    	//register(animalCage, "animalCage");
    	
    	//Portal Sparks//
    	nileSpark = new ItemNileSpark().setUnlocalizedName("nileSpark").setTextureName(ZeroQuest.modid + ":" + "nileSpark");
    	register(nileSpark, "nile_spark");
    	
    	//Projectiles//
    	FPoisonball = new ItemFPoisonball().setUnlocalizedName("FPoisonball").setTextureName(ZeroQuest.modid + ":" + "FPoisonball");
    	register(FPoisonball, "flaming_poisonball");
    	
    	//Armor//
    	/*nileHelmet = new ItemNileArmor(ZeroQuest.nileEssenceArmorMaterial, 0, "nileHelmet");
    	register(nileHelmet,"nile_helmet");
    	nileChest = new ItemNileArmor(ZeroQuest.nileEssenceArmorMaterial, 1, "nileChest");
    	register(nileChest,"nile_chest");
    	nileLegs = new ItemNileArmor(ZeroQuest.nileEssenceArmorMaterial, 2, "nileLegs");
    	register(nileLegs,"nile_leggings");
    	nileBoots = new ItemNileArmor(ZeroQuest.nileEssenceArmorMaterial, 3, "nileBoots");
    	register(nileBoots,"nile_boots");*/
    	
    	//Food/Crops//
    	zertumMeatRaw = new ZQFood(3, 1.5F, true).setUnlocalizedName("zertumMeatRaw").setTextureName(ZeroQuest.modid + ":" + "zertumMeat_raw");
    	register(zertumMeatRaw, "zertum_meat_raw");
    	zertumMeatCooked = new ZQFood(5, 2.5F, true).setUnlocalizedName("zertumMeatCooked").setTextureName(ZeroQuest.modid + ":" + "zertumMeat_cooked");
    	register(zertumMeatCooked, "zertum_meat_cooked");
    	vitoidSeed = new ItemNileSeed(ModBlocks.vitoidPlant, Blocks.farmland).setUnlocalizedName("vitoidSeed").setTextureName(ZeroQuest.modid + ":" + "vitoidSeed");
    	register(vitoidSeed, "vitoid_seed");
    	vitoidFruit = new VitoidFruit(2, 0.5F, false).setUnlocalizedName("vitoidFruit").setTextureName(ZeroQuest.modid + ":" + "vitoidFruit");
    	register(vitoidFruit, "vitoid_fruit");
    	jakanMeatRaw = new ZQFood(4, 1.7F, true).setUnlocalizedName("jakanMeatRaw").setTextureName(ZeroQuest.modid + ":" + "jakanMeat_raw");
    	register(jakanMeatRaw, "jakan_meat_raw");
    	jakanMeatCooked = new ZQFood(5, 2.7F, true).setUnlocalizedName("jakanMeatCooked").setTextureName(ZeroQuest.modid + ":" + "jakanMeat_cooked");
    	register(jakanMeatCooked, "jakan_meat_cooked");
    	kurrSeeds = new KurrSeed(4, 1.2F, false).setUnlocalizedName("kurrSeeds").setTextureName(ZeroQuest.modid + ":" + "kurrSeed");
    	register(kurrSeeds, "kurr_seeds");
	}
	
	public static void loadDarkItems(){
		
		darkSword = new ItemNileSword(ZeroQuest.darkEssence).setUnlocalizedName("darkSword").setTextureName(ZeroQuest.modid + ":" + "darkSword").setCreativeTab(ZeroQuest.DarkTab);
		register(darkSword, "dark_sword");
		darkAxe = new ItemNileAxe(ZeroQuest.darkEssence).setUnlocalizedName("darkAxe").setTextureName(ZeroQuest.modid + ":" + "darkAxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkAxe, "dark_axe");
		darkPickaxe = new ItemNilePickaxe(ZeroQuest.darkEssence).setUnlocalizedName("darkPickaxe").setTextureName(ZeroQuest.modid + ":" + "darkPickaxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkPickaxe, "dark_pickaxe");
		darkShovel = new ItemNileShovel(ZeroQuest.darkEssence).setUnlocalizedName("darkShovel").setTextureName(ZeroQuest.modid + ":" + "darkShovel").setCreativeTab(ZeroQuest.DarkTab);
		register(darkShovel, "dark_shovel");
		darkHoe = new ItemNileHoe(ZeroQuest.darkEssence).setUnlocalizedName("darkHoe").setTextureName(ZeroQuest.modid + ":" + "darkHoe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkHoe, "dark_hoe");
		
    	darkSpark = new ItemDarkSpark().setUnlocalizedName("darkSpark").setTextureName(ZeroQuest.modid + ":" + "darkSpark");
    	register(darkSpark, "dark_spark");
    	
       	darkEssence = new ItemEssence().setUnlocalizedName("darkEssence").setTextureName(ZeroQuest.modid + ":" + "darkEssence").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkEssence, "dark_essence");
       	darkDust = new ItemDust().setUnlocalizedName("darkDust").setTextureName(ZeroQuest.modid + ":" + "darkDust").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkDust, "dark_dust");
       	darkGrain = new ItemGrain().setUnlocalizedName("darkGrain").setTextureName(ZeroQuest.modid + ":" + "darkGrain").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkGrain, "dark_grain");
       	darkNileBone = new ItemNileBone().setUnlocalizedName("nileDarkBone").setTextureName(ZeroQuest.modid + ":" + "nileDarkBone").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkNileBone, "nile_bone_dark");
	}
	
	   public static void register(Item item, String name){
		   GameRegistry.registerItem(item, name);
	   }
}