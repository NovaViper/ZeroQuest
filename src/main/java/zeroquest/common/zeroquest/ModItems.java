package common.zeroquest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.item.ItemDarkSpark;
import common.zeroquest.item.ItemDogTreat;
import common.zeroquest.item.ItemDust;
import common.zeroquest.item.ItemEssence;
import common.zeroquest.item.ItemFPoisonball;
import common.zeroquest.item.ItemGrain;
import common.zeroquest.item.ItemNileAxe;
import common.zeroquest.item.ItemNileBone;
import common.zeroquest.item.ItemNileBow;
import common.zeroquest.item.ItemNileCoal;
import common.zeroquest.item.ItemNileGrenade;
import common.zeroquest.item.ItemNilePickaxe;
import common.zeroquest.item.ItemNileSeed;
import common.zeroquest.item.ItemNileSpade;
import common.zeroquest.item.ItemNileSpark;
import common.zeroquest.item.KurrSeed;
import common.zeroquest.item.VitoidFruit;
import common.zeroquest.item.ZQFood;
import common.zeroquest.lib.Constants;

public class ModItems {

	public static Item nileSword;
	public static Item nileAxe;
	public static Item nilePickaxe;
	public static Item nileSpade;
	public static Item nileHoe;
	public static Item nileBow;
	public static Item nileGrenade;
	
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
	public static Item darkBow;
	
	public static Item darkEssence;
	public static Item darkDust;
	public static Item darkGrain;
	public static Item darkBone;
	public static Item kurrSeeds;
	
	public static Item darkSpark;
	
	public static void load() {    	
    	//Tools/Weapons//
    	nileSword = new ItemSword(ZeroQuest.nileEssence).setUnlocalizedName("nileSword").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileSword, "nile_sword");
    	registerRender(nileSword, 0, Constants.modid + ":" + "nile_sword", "inventory");
    	nileAxe = new ItemNileAxe(ZeroQuest.nileEssence).setUnlocalizedName("nileAxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileAxe, "nile_axe");
    	registerRender(nileAxe, 0, Constants.modid + ":" + "nile_axe", "inventory");
    	nilePickaxe = new ItemNilePickaxe(ZeroQuest.nileEssence).setUnlocalizedName("nilePickaxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nilePickaxe, "nile_pickaxe");
    	registerRender(nilePickaxe, 0, Constants.modid + ":" + "nile_pickaxe", "inventory");
    	nileSpade = new ItemNileSpade(ZeroQuest.nileEssence).setUnlocalizedName("nileShovel").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileSpade, "nile_shovel");
    	registerRender(nileSpade, 0, Constants.modid + ":" + "nile_shovel", "inventory");
    	nileHoe = new ItemHoe(ZeroQuest.nileEssence).setUnlocalizedName("nileHoe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileHoe, "nile_hoe");
    	registerRender(nileHoe, 0, Constants.modid + ":" + "nile_hoe", "inventory");
    	nileBow = new ItemNileBow("nile").setUnlocalizedName("nileBow").setCreativeTab(ZeroQuest.ZeroTab).setMaxDamage(484);
    	register(nileBow, "nile_bow");
    	registerRender(nileBow, 0, Constants.modid + ":" + "nile_bow", "inventory");
    	addVariant(ModItems.nileBow, new String[] {Constants.modid + ":nile_bow", Constants.modid + ":nile_bow_pulling_0", Constants.modid + ":nile_bow_pulling_1", Constants.modid + ":nile_bow_pulling_2"});
    	nileGrenade = new ItemNileGrenade().setUnlocalizedName("nileGrenade");
    	register(nileGrenade, "nile_grenade");
    	registerRender(nileGrenade, 0, Constants.modid + ":" + "nile_grenade", "inventory");
    	
    	//Basics//
    	nileEssence = new ItemEssence().setUnlocalizedName("nileEssence").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileEssence, "nile_essence");
    	registerRender(nileEssence, 0, Constants.modid + ":" + "nile_essence", "inventory");
    	nileDust = new ItemDust().setUnlocalizedName("nileDust").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileDust, "nile_dust");
    	registerRender(nileDust, 0, Constants.modid + ":" + "nile_dust", "inventory");
    	nileGrain = new ItemGrain().setUnlocalizedName("nileGrain").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileGrain, "nile_grain");
    	registerRender(nileGrain, 0, Constants.modid + ":" + "nile_grain", "inventory");
    	nileCoal = new ItemNileCoal().setUnlocalizedName("nileCoal").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileCoal, "nile_coal");
    	registerRender(nileCoal, 0, Constants.modid + ":" + "nile_coal", "inventory");
    	dogTreat = new ItemDogTreat().setUnlocalizedName("dogTreat").setCreativeTab(ZeroQuest.ZeroTab);
    	register(dogTreat, "dog_treat");
    	registerRender(dogTreat, 0, Constants.modid + ":" + "dog_treat", "inventory");
    	nileBone = new ItemNileBone().setUnlocalizedName("nileBone").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileBone, "nile_bone");
    	registerRender(nileBone, 0, Constants.modid + ":" + "nile_bone", "inventory");
    	
    	//Portal Sparks//
    	nileSpark = new ItemNileSpark().setUnlocalizedName("nileSpark");
    	register(nileSpark, "nile_spark");
    	registerRender(nileSpark, 0, Constants.modid + ":" + "nile_spark", "inventory");
    	
    	//Projectiles//
    	FPoisonball = new ItemFPoisonball().setUnlocalizedName("FPoisonball");
    	register(FPoisonball, "flaming_poisonball");
    	registerRender(FPoisonball, 0, Constants.modid + ":" + "flaming_poisonball", "inventory");

    	//Food/Crops//
    	zertumMeatRaw = new ZQFood(3, 1.5F, true).setUnlocalizedName("zertumMeatRaw");
    	register(zertumMeatRaw, "zertum_meat_raw");
    	registerRender(zertumMeatRaw, 0, Constants.modid + ":" + "zertum_meat_raw", "inventory");
    	zertumMeatCooked = new ZQFood(5, 2.5F, true).setUnlocalizedName("zertumMeatCooked");
    	register(zertumMeatCooked, "zertum_meat_cooked");
    	registerRender(zertumMeatCooked, 0, Constants.modid + ":" + "zertum_meat_cooked", "inventory");
    	vitoidSeed = new ItemNileSeed(ModBlocks.vitoidPlant, Blocks.farmland).setUnlocalizedName("vitoidSeed");
    	register(vitoidSeed, "vitoid_seed");
    	registerRender(vitoidSeed, 0, Constants.modid + ":" + "vitoid_seed", "inventory");
    	vitoidFruit = new VitoidFruit(2, 0.5F, false).setUnlocalizedName("vitoidFruit");
    	register(vitoidFruit, "vitoid_fruit");
    	registerRender(vitoidFruit, 0, Constants.modid + ":" + "vitoid_fruit", "inventory");
    	jakanMeatRaw = new ZQFood(4, 1.7F, true).setUnlocalizedName("jakanMeatRaw");
    	register(jakanMeatRaw, "jakan_meat_raw");
    	registerRender(jakanMeatRaw, 0, Constants.modid + ":" + "jakan_meat_raw", "inventory");
    	jakanMeatCooked = new ZQFood(5, 2.7F, true).setUnlocalizedName("jakanMeatCooked");
    	register(jakanMeatCooked, "jakan_meat_cooked");
    	registerRender(jakanMeatCooked, 0, Constants.modid + ":" + "jakan_meat_cooked", "inventory");
	}
	
	public static void loadDarkItems(){
		
		darkSword = new ItemSword(ZeroQuest.darkEssence).setUnlocalizedName("darkSword").setCreativeTab(ZeroQuest.DarkTab);
		register(darkSword, "dark_sword");
    	registerRender(darkSword, 0, Constants.modid + ":" + "dark_sword", "inventory");
		darkAxe = new ItemNileAxe(ZeroQuest.darkEssence).setUnlocalizedName("darkAxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkAxe, "dark_axe");
    	registerRender(darkAxe, 0, Constants.modid + ":" + "dark_axe", "inventory");
		darkPickaxe = new ItemNilePickaxe(ZeroQuest.darkEssence).setUnlocalizedName("darkPickaxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkPickaxe, "dark_pickaxe");
    	registerRender(darkPickaxe, 0, Constants.modid + ":" + "dark_pickaxe", "inventory");
		darkShovel = new ItemNileSpade(ZeroQuest.darkEssence).setUnlocalizedName("darkShovel").setCreativeTab(ZeroQuest.DarkTab);
		register(darkShovel, "dark_shovel");
    	registerRender(darkShovel, 0, Constants.modid + ":" + "dark_shovel", "inventory");
		darkHoe = new ItemHoe(ZeroQuest.darkEssence).setUnlocalizedName("darkHoe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkHoe, "dark_hoe");
    	registerRender(darkHoe, 0, Constants.modid + ":" + "dark_hoe", "inventory");
    	darkBow = new ItemNileBow("dark").setUnlocalizedName("darkBow").setCreativeTab(ZeroQuest.DarkTab).setMaxDamage(525);
    	register(darkBow, "dark_bow");
    	registerRender(darkBow, 0, Constants.modid + ":" + "dark_bow", "inventory");
    	addVariant(darkBow, new String[] {Constants.modid + ":dark_bow", Constants.modid + ":dark_bow_pulling_0", Constants.modid + ":dark_bow_pulling_1", Constants.modid + ":dark_bow_pulling_2"});
		
    	darkSpark = new ItemDarkSpark().setUnlocalizedName("darkSpark");
    	register(darkSpark, "dark_spark");
    	registerRender(darkSpark, 0, Constants.modid + ":" + "dark_spark", "inventory");
    	
       	darkEssence = new ItemEssence().setUnlocalizedName("darkEssence").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkEssence, "dark_essence");
    	registerRender(darkEssence, 0, Constants.modid + ":" + "dark_essence", "inventory");
       	darkDust = new ItemDust().setUnlocalizedName("darkDust").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkDust, "dark_dust");
    	registerRender(darkDust, 0, Constants.modid + ":" + "dark_dust", "inventory");
       	darkGrain = new ItemGrain().setUnlocalizedName("darkGrain").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkGrain, "dark_grain");
    	registerRender(darkGrain, 0, Constants.modid + ":" + "dark_grain", "inventory");
    	darkBone = new ItemNileBone().setUnlocalizedName("darkBone").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkBone, "dark_bone");
    	registerRender(darkBone, 0, Constants.modid + ":" + "dark_bone", "inventory");
    	kurrSeeds = new KurrSeed(4, 1.2F, false).setUnlocalizedName("kurrSeeds");
    	register(kurrSeeds, "kurr_seeds");
    	registerRender(kurrSeeds, 0, Constants.modid + ":" + "kurr_seeds", "inventory");
	}
	
	   public static void register(Item item, String name){
		   GameRegistry.registerItem(item, name);
	   }
	   
	   public static void registerRender(Item item, int metadata, String itemString, String location){
		   Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(itemString, location));
	   }
	   
	   public static void addVariant(Item item, String... names){
		   ModelBakery.addVariantName(item, names);
	   }
}