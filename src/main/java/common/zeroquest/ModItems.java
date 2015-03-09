package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.item.ItemCommandSeal;
import common.zeroquest.item.ItemFPoisonball;
import common.zeroquest.item.ItemNileBone;
import common.zeroquest.item.ItemNileSeed;
import common.zeroquest.item.ItemToy;
import common.zeroquest.item.KurrSeed;
import common.zeroquest.item.VitoidFruit;
import common.zeroquest.item.ZQFood;
import common.zeroquest.item.materials.ItemDust;
import common.zeroquest.item.materials.ItemEssence;
import common.zeroquest.item.materials.ItemGrain;
import common.zeroquest.item.materials.ItemNileCoal;
import common.zeroquest.item.tools.ItemDarkSpark;
import common.zeroquest.item.tools.ItemDogTreat;
import common.zeroquest.item.tools.ItemNileAxe;
import common.zeroquest.item.tools.ItemNileHoe;
import common.zeroquest.item.tools.ItemNilePickaxe;
import common.zeroquest.item.tools.ItemNileSpade;
import common.zeroquest.item.tools.ItemNileSpark;
import common.zeroquest.item.weapons.ItemNileBow;
import common.zeroquest.item.weapons.ItemNileGrenade;
import common.zeroquest.item.weapons.ItemNileSword;
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
	public static Item nileSpark;
	public static Item nileBone;
	public static Item commandSeal;
	
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
	public static Item toy;
	
	public static void load() {    	
    	//Tools/Weapons//
    	nileSword = new ItemNileSword(ZeroQuest.nileEssence).setUnlocalizedName("nileSword").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileSword, "nile_sword");
    	nileAxe = new ItemNileAxe(ZeroQuest.nileEssence).setUnlocalizedName("nileAxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileAxe, "nile_axe");
    	nilePickaxe = new ItemNilePickaxe(ZeroQuest.nileEssence).setUnlocalizedName("nilePickaxe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nilePickaxe, "nile_pickaxe");
    	nileSpade = new ItemNileSpade(ZeroQuest.nileEssence).setUnlocalizedName("nileShovel").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileSpade, "nile_shovel");
    	nileHoe = new ItemNileHoe(ZeroQuest.nileEssence).setUnlocalizedName("nileHoe").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileHoe, "nile_hoe");
    	nileBow = new ItemNileBow("nile", 484).setUnlocalizedName("nileBow").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileBow, "nile_bow");
    	nileGrenade = new ItemNileGrenade().setUnlocalizedName("nileGrenade");
    	register(nileGrenade, "nile_grenade");
    	
    	//Basics//
    	nileEssence = new ItemEssence().setUnlocalizedName("nileEssence").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileEssence, "nile_essence");
    	nileDust = new ItemDust().setUnlocalizedName("nileDust").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileDust, "nile_dust");
    	nileGrain = new ItemGrain().setUnlocalizedName("nileGrain").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileGrain, "nile_grain");
    	nileCoal = new ItemNileCoal().setUnlocalizedName("nileCoal").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileCoal, "nile_coal");
    	dogTreat = new ItemDogTreat().setUnlocalizedName("dogTreat").setCreativeTab(ZeroQuest.ZeroTab);
    	register(dogTreat, "dog_treat");
    	nileBone = new ItemNileBone().setUnlocalizedName("nileBone").setCreativeTab(ZeroQuest.ZeroTab);
    	register(nileBone, "nile_bone");
		commandSeal = new ItemCommandSeal().setUnlocalizedName("commandSeal").setCreativeTab(ZeroQuest.ZeroTab);
    	register(commandSeal, "command_seal");
    	toy = new ItemToy().setUnlocalizedName("toy").setCreativeTab(ZeroQuest.ZeroTab);
    	register(toy, "toy");
		
    	//Portal Sparks//
    	nileSpark = new ItemNileSpark().setUnlocalizedName("nileSpark");
    	register(nileSpark, "nile_spark");
    	
    	//Projectiles//
    	FPoisonball = new ItemFPoisonball().setUnlocalizedName("FPoisonball");
    	register(FPoisonball, "flaming_poisonball");


    	//Food/Crops//
    	zertumMeatRaw = new ZQFood(3, 1.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatRaw");
    	register(zertumMeatRaw, "zertum_meat_raw");
    	zertumMeatCooked = new ZQFood(5, 2.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatCooked");
    	register(zertumMeatCooked, "zertum_meat_cooked");
    	vitoidSeed = new ItemNileSeed(ModBlocks.vitoidPlant, Blocks.farmland).setUnlocalizedName("vitoidSeed");
    	register(vitoidSeed, "vitoid_seed");
    	vitoidFruit = new VitoidFruit(2, 0.5F, false).setUnlocalizedName("vitoidFruit");
    	register(vitoidFruit, "vitoid_fruit");
    	jakanMeatRaw = new ZQFood(4, 1.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatRaw");
    	register(jakanMeatRaw, "jakan_meat_raw");
    	jakanMeatCooked = new ZQFood(5, 2.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatCooked");
    	register(jakanMeatCooked, "jakan_meat_cooked");
	}
	
	public static void loadDarkItems(){
		
		darkSword = new ItemNileSword(ZeroQuest.darkEssence).setUnlocalizedName("darkSword").setCreativeTab(ZeroQuest.DarkTab);
		register(darkSword, "dark_sword");
		darkAxe = new ItemNileAxe(ZeroQuest.darkEssence).setUnlocalizedName("darkAxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkAxe, "dark_axe");
		darkPickaxe = new ItemNilePickaxe(ZeroQuest.darkEssence).setUnlocalizedName("darkPickaxe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkPickaxe, "dark_pickaxe");
		darkShovel = new ItemNileSpade(ZeroQuest.darkEssence).setUnlocalizedName("darkShovel").setCreativeTab(ZeroQuest.DarkTab);
		register(darkShovel, "dark_shovel");
		darkHoe = new ItemNileHoe(ZeroQuest.darkEssence).setUnlocalizedName("darkHoe").setCreativeTab(ZeroQuest.DarkTab);
		register(darkHoe, "dark_hoe");
    	darkBow = new ItemNileBow("dark", 584).setUnlocalizedName("darkBow").setCreativeTab(ZeroQuest.DarkTab);
    	register(darkBow, "dark_bow");
    	darkSpark = new ItemDarkSpark().setUnlocalizedName("darkSpark");
    	register(darkSpark, "dark_spark");
    	
       	darkEssence = new ItemEssence().setUnlocalizedName("darkEssence").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkEssence, "dark_essence");
       	darkDust = new ItemDust().setUnlocalizedName("darkDust").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkDust, "dark_dust");
       	darkGrain = new ItemGrain().setUnlocalizedName("darkGrain").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkGrain, "dark_grain");
    	darkBone = new ItemNileBone().setUnlocalizedName("darkBone").setCreativeTab(ZeroQuest.DarkTab);
       	register(darkBone, "dark_bone");
    	kurrSeeds = new KurrSeed(4, 1.2F, false).setUnlocalizedName("kurrSeeds");
    	register(kurrSeeds, "kurr_seeds");
	}
	
	public static void loadRenderers(){
		
    	registerRender(nileSword, 0, Constants.modid + ":" + "nile_sword", "inventory");
    	registerRender(nileAxe, 0, Constants.modid + ":" + "nile_axe", "inventory");
    	registerRender(nilePickaxe, 0, Constants.modid + ":" + "nile_pickaxe", "inventory");
    	registerRender(nileSpade, 0, Constants.modid + ":" + "nile_shovel", "inventory");
    	registerRender(nileHoe, 0, Constants.modid + ":" + "nile_hoe", "inventory");
    	addVariant(nileBow, new String[] {Constants.modid + ":nile_bow", Constants.modid + ":nile_bow_pulling_0", Constants.modid + ":nile_bow_pulling_1", Constants.modid + ":nile_bow_pulling_2"});
    	registerRender(nileBow, 0, Constants.modid + ":nile_bow", "inventory");
    	registerRender(nileGrenade, 0, Constants.modid + ":" + "nile_grenade", "inventory");
    	registerRender(nileEssence, 0, Constants.modid + ":" + "nile_essence", "inventory");
    	registerRender(nileDust, 0, Constants.modid + ":" + "nile_dust", "inventory");
    	registerRender(nileGrain, 0, Constants.modid + ":" + "nile_grain", "inventory");
    	registerRender(nileCoal, 0, Constants.modid + ":" + "nile_coal", "inventory");
    	registerRender(dogTreat, 0, Constants.modid + ":" + "dog_treat", "inventory");
    	registerRender(nileBone, 0, Constants.modid + ":" + "nile_bone", "inventory");
    	registerRender(nileSpark, 0, Constants.modid + ":" + "nile_spark", "inventory");
    	registerRender(FPoisonball, 0, Constants.modid + ":" + "flaming_poisonball", "inventory");
    	registerRender(zertumMeatRaw, 0, Constants.modid + ":" + "zertum_meat_raw", "inventory");
    	registerRender(zertumMeatCooked, 0, Constants.modid + ":" + "zertum_meat_cooked", "inventory");
    	registerRender(vitoidSeed, 0, Constants.modid + ":" + "vitoid_seed", "inventory");
    	registerRender(vitoidFruit, 0, Constants.modid + ":" + "vitoid_fruit", "inventory");
    	registerRender(jakanMeatRaw, 0, Constants.modid + ":" + "jakan_meat_raw", "inventory");
    	registerRender(jakanMeatCooked, 0, Constants.modid + ":" + "jakan_meat_cooked", "inventory");
    	registerRender(commandSeal, 0, Constants.modid + ":" + "command_seal", "inventory");
    	registerRender(toy, 0, Constants.modid + ":" + "toy", "inventory");
    	registerRender(toy, 1, Constants.modid + ":" + "toy_wet", "inventory");
    	addVariant(toy, new String[] {Constants.modid + ":toy", Constants.modid + ":toy_wet"});
	}
	
	public static void loadDarkRenderers(){
    	registerRender(darkSword, 0, Constants.modid + ":" + "dark_sword", "inventory");
    	registerRender(darkAxe, 0, Constants.modid + ":" + "dark_axe", "inventory");
    	registerRender(darkPickaxe, 0, Constants.modid + ":" + "dark_pickaxe", "inventory");
    	registerRender(darkShovel, 0, Constants.modid + ":" + "dark_shovel", "inventory");
    	registerRender(darkHoe, 0, Constants.modid + ":" + "dark_hoe", "inventory");
    	addVariant(darkBow, new String[] {Constants.modid + ":dark_bow", Constants.modid + ":dark_bow_pulling_0", Constants.modid + ":dark_bow_pulling_1", Constants.modid + ":dark_bow_pulling_2"});
    	registerRender(darkBow, 0, Constants.modid + ":" + "dark_bow", "inventory");
    	registerRender(darkSpark, 0, Constants.modid + ":" + "dark_spark", "inventory");
    	registerRender(darkEssence, 0, Constants.modid + ":" + "dark_essence", "inventory");
    	registerRender(darkDust, 0, Constants.modid + ":" + "dark_dust", "inventory");
    	registerRender(darkGrain, 0, Constants.modid + ":" + "dark_grain", "inventory");
    	registerRender(darkBone, 0, Constants.modid + ":" + "dark_bone", "inventory");
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