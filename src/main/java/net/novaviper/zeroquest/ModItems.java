package net.novaviper.zeroquest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.novaviper.zeroquest.common.item.*;
import net.novaviper.zeroquest.common.item.food.Food;
import net.novaviper.zeroquest.common.item.food.ItemNileSeed;
import net.novaviper.zeroquest.common.item.food.KurrSeed;
import net.novaviper.zeroquest.common.item.food.RawMeat;
import net.novaviper.zeroquest.common.item.food.VitoidFruit;
import net.novaviper.zeroquest.common.item.tools.*;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Registers;

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
	public static Item riggatorMeatRaw;
	public static Item riggatorMeatCooked;
	public static Item kortorMeatRaw;
	public static Item kortorMeatCooked;

	public static Item vitoidSeed;
	public static Item vitoidFruit;

	public static Item nileEssence;
	public static Item nileDust;
	public static Item nileGrain;
	public static Item nileCoal;
	public static Item nileSpark;
	public static Item nileBone;

	public static Item dogTreat;
	public static Item commandSeal;
	public static Item toy;
	/** Lowest Class Bit **/
	public static Item microBit;
	/** Low-Middle Class Bit **/
	public static Item megaBit;
	/** Middle Class Bit **/
	public static Item omegaBit;
	/** High Class Bit **/
	public static Item alphaBit;
	/** Highest Class Bit **/
	public static Item deltaBit;
	/** First Stage Evolution Bit **/
	public static Item evoBit;
	/** Final Stage Evolution Bit **/
	public static Item pettraBit;

	public static Item FPoisonball;
	public static Item iceBall;

	// DARK ELEMENT//
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
	public static Item kurrMeatRaw;
	public static Item kurrMeatCooked;

	public static Item darkSpark;

	public static void load() {
		// Tools/Weapons//
		nileSword = new ItemNileSword(ZeroQuest.nileEssence).setUnlocalizedName("nileSword").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nileSword, "nile_sword");
		nileAxe = new ItemNileAxe(ZeroQuest.nileEssence).setUnlocalizedName("nileAxe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nileAxe, "nile_axe");
		nilePickaxe = new ItemNilePickaxe(ZeroQuest.nileEssence).setUnlocalizedName("nilePickaxe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nilePickaxe, "nile_pickaxe");
		nileSpade = new ItemNileSpade(ZeroQuest.nileEssence).setUnlocalizedName("nileShovel").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nileSpade, "nile_shovel");
		nileHoe = new ItemNileHoe(ZeroQuest.nileEssence).setUnlocalizedName("nileHoe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nileHoe, "nile_hoe");
		nileBow = new ItemNileBow("nile", 484).setUnlocalizedName("nileBow").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.addItem(nileBow, "nile_bow");
		nileGrenade = new ItemNileGrenade().setUnlocalizedName("nileGrenade");
		Registers.addItem(nileGrenade, "nile_grenade");

		// Basics//
		nileEssence = new ItemZQ().setUnlocalizedName("nileEssence");
		Registers.addItem(nileEssence, "nile_essence");
		nileDust = new ItemZQ().setUnlocalizedName("nileDust");
		Registers.addItem(nileDust, "nile_dust");
		nileGrain = new ItemZQ().setUnlocalizedName("nileGrain");
		Registers.addItem(nileGrain, "nile_grain");
		nileCoal = new ItemZQ().setUnlocalizedName("nileCoal");
		Registers.addItem(nileCoal, "nile_coal");
		dogTreat = new ItemZQ().setUnlocalizedName("dogTreat");
		Registers.addItem(dogTreat, "dog_treat");
		nileBone = new ItemZQ().setUnlocalizedName("nileBone");
		Registers.addItem(nileBone, "nile_bone");
		commandSeal = new ItemCommandSeal().setUnlocalizedName("commandSeal");
		Registers.addItem(commandSeal, "command_seal");
		toy = new ItemToy().setUnlocalizedName("toy");
		Registers.addItem(toy, "toy");

		// Bits for Talents\\
		microBit = new ItemBit(0, 20).setUnlocalizedName("microBit");
		Registers.addItem(microBit, "micro_bit");
		megaBit = new ItemBit(20, 50).setUnlocalizedName("megaBit");
		Registers.addItem(megaBit, "mega_bit");
		omegaBit = new ItemBit(50, 80).setUnlocalizedName("omegaBit");
		Registers.addItem(omegaBit, "omega_bit");
		alphaBit = new ItemAlphaBit().setUnlocalizedName("alphaBit");
		Registers.addItem(alphaBit, "alpha_bit");
		deltaBit = new ItemDeltaBit().setUnlocalizedName("deltaBit");
		Registers.addItem(deltaBit, "delta_bit");
		evoBit = new ItemZQ().setUnlocalizedName("evoBit");
		Registers.addItem(evoBit, "evo_bit");
		pettraBit = new ItemZQ().setUnlocalizedName("pettraBit");
		Registers.addItem(pettraBit, "pettra_bit");

		// Portal Sparks//
		nileSpark = new ItemNileSpark().setUnlocalizedName("nileSpark");
		Registers.addItem(nileSpark, "nile_spark");

		// Projectiles//
		FPoisonball = new ItemProjectile().setUnlocalizedName("FPoisonball");
		Registers.addItem(FPoisonball, "flaming_poisonball");
		iceBall = new ItemProjectile().setUnlocalizedName("iceBall");
		Registers.addItem(iceBall, "ice_ball");

		// Food/Crops//
		zertumMeatRaw = new RawMeat(1, 3F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatRaw");
		Registers.addItem(zertumMeatRaw, "zertum_meat_raw");
		zertumMeatCooked = new Food(5, 2.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatCooked");
		Registers.addItem(zertumMeatCooked, "zertum_meat_cooked");
		jakanMeatRaw = new RawMeat(1, 1.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatRaw");
		Registers.addItem(jakanMeatRaw, "jakan_meat_raw");
		jakanMeatCooked = new Food(5, 2.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatCooked");
		Registers.addItem(jakanMeatCooked, "jakan_meat_cooked");
		kortorMeatRaw = new RawMeat(1, 1.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("kortorMeatRaw");
		Registers.addItem(kortorMeatRaw, "kortor_meat_raw");
		kortorMeatCooked = new Food(4, 2.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("kortorMeatCooked");
		Registers.addItem(kortorMeatCooked, "kortor_meat_cooked");
		riggatorMeatRaw = new RawMeat(1, 1.1F, true, ZeroQuest.ZeroTab).setUnlocalizedName("riggatorMeatRaw");
		Registers.addItem(riggatorMeatRaw, "riggator_meat_raw");
		riggatorMeatCooked = new Food(4, 1.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("riggatorMeatCooked");
		Registers.addItem(riggatorMeatCooked, "riggator_meat_cooked");
		vitoidSeed = new ItemNileSeed(ModBlocks.vitoidPlant, Blocks.farmland).setUnlocalizedName("vitoidSeed");
		Registers.addItem(vitoidSeed, "vitoid_seed");
		vitoidFruit = new VitoidFruit(2, 0.5F, false).setUnlocalizedName("vitoidFruit");
		Registers.addItem(vitoidFruit, "vitoid_fruit");
	}

	public static void loadDarkItems() {

		darkSword = new ItemNileSword(ZeroQuest.darkEssence).setUnlocalizedName("darkSword").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkSword, "dark_sword");
		darkAxe = new ItemNileAxe(ZeroQuest.darkEssence).setUnlocalizedName("darkAxe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkAxe, "dark_axe");
		darkPickaxe = new ItemNilePickaxe(ZeroQuest.darkEssence).setUnlocalizedName("darkPickaxe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkPickaxe, "dark_pickaxe");
		darkShovel = new ItemNileSpade(ZeroQuest.darkEssence).setUnlocalizedName("darkShovel").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkShovel, "dark_shovel");
		darkHoe = new ItemNileHoe(ZeroQuest.darkEssence).setUnlocalizedName("darkHoe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkHoe, "dark_hoe");
		darkBow = new ItemNileBow("dark", 584).setUnlocalizedName("darkBow").setCreativeTab(ZeroQuest.DarkTab);
		Registers.addItem(darkBow, "dark_bow");
		darkSpark = new ItemDarkSpark().setUnlocalizedName("darkSpark");
		Registers.addItem(darkSpark, "dark_spark");

		darkEssence = new ItemDQ().setUnlocalizedName("darkEssence");
		Registers.addItem(darkEssence, "dark_essence");
		darkDust = new ItemDQ().setUnlocalizedName("darkDust");
		Registers.addItem(darkDust, "dark_dust");
		darkGrain = new ItemDQ().setUnlocalizedName("darkGrain");
		Registers.addItem(darkGrain, "dark_grain");
		darkBone = new ItemDQ().setUnlocalizedName("darkBone");
		Registers.addItem(darkBone, "dark_bone");

		kurrSeeds = new KurrSeed(4, 1.2F, false).setUnlocalizedName("kurrSeeds");
		Registers.addItem(kurrSeeds, "kurr_seeds");
		kurrMeatRaw = new RawMeat(1, 1.1F, true, ZeroQuest.DarkTab).setUnlocalizedName("kurrMeatRaw");
		Registers.addItem(kurrMeatRaw, "kurr_meat_raw");
		kurrMeatCooked = new Food(6, 1.7F, true, ZeroQuest.DarkTab).setUnlocalizedName("kurrMeatCooked");
		Registers.addItem(kurrMeatCooked, "kurr_meat_cooked");
	}

	public static void loadRenderers() {

		Registers.addItemRender(nileSword, 0, Constants.modid + ":" + "nile_sword", "inventory");
		Registers.addItemRender(nileAxe, 0, Constants.modid + ":" + "nile_axe", "inventory");
		Registers.addItemRender(nilePickaxe, 0, Constants.modid + ":" + "nile_pickaxe", "inventory");
		Registers.addItemRender(nileSpade, 0, Constants.modid + ":" + "nile_shovel", "inventory");
		Registers.addItemRender(nileHoe, 0, Constants.modid + ":" + "nile_hoe", "inventory");
		Registers.addItemVariant(nileBow, new String[] { Constants.modid + ":nile_bow",
				Constants.modid + ":nile_bow_pulling_0", Constants.modid + ":nile_bow_pulling_1",
				Constants.modid + ":nile_bow_pulling_2" });
		Registers.addItemRender(nileBow, 0, Constants.modid + ":nile_bow", "inventory");
		Registers.addItemRender(nileGrenade, 0, Constants.modid + ":" + "nile_grenade", "inventory");
		Registers.addItemRender(nileEssence, 0, Constants.modid + ":" + "nile_essence", "inventory");
		Registers.addItemRender(nileDust, 0, Constants.modid + ":" + "nile_dust", "inventory");
		Registers.addItemRender(nileGrain, 0, Constants.modid + ":" + "nile_grain", "inventory");
		Registers.addItemRender(nileCoal, 0, Constants.modid + ":" + "nile_coal", "inventory");
		Registers.addItemRender(dogTreat, 0, Constants.modid + ":" + "dog_treat", "inventory");
		Registers.addItemRender(nileBone, 0, Constants.modid + ":" + "nile_bone", "inventory");
		Registers.addItemRender(nileSpark, 0, Constants.modid + ":" + "nile_spark", "inventory");
		Registers.addItemRender(FPoisonball, 0, Constants.modid + ":" + "flaming_poisonball", "inventory");
		Registers.addItemRender(iceBall, 0, Constants.modid + ":" + "ice_ball", "inventory");
		Registers.addItemRender(zertumMeatRaw, 0, Constants.modid + ":" + "zertum_meat_raw", "inventory");
		Registers.addItemRender(zertumMeatCooked, 0, Constants.modid + ":" + "zertum_meat_cooked", "inventory");
		Registers.addItemRender(jakanMeatRaw, 0, Constants.modid + ":" + "jakan_meat_raw", "inventory");
		Registers.addItemRender(jakanMeatCooked, 0, Constants.modid + ":" + "jakan_meat_cooked", "inventory");
		Registers.addItemRender(kortorMeatRaw, 0, Constants.modid + ":" + "kortor_meat_raw", "inventory");
		Registers.addItemRender(kortorMeatCooked, 0, Constants.modid + ":" + "kortor_meat_cooked", "inventory");
		Registers.addItemRender(riggatorMeatRaw, 0, Constants.modid + ":" + "riggator_meat_raw", "inventory");
		Registers.addItemRender(riggatorMeatCooked, 0, Constants.modid + ":" + "riggator_meat_cooked", "inventory");
		Registers.addItemRender(vitoidSeed, 0, Constants.modid + ":" + "vitoid_seed", "inventory");
		Registers.addItemRender(vitoidFruit, 0, Constants.modid + ":" + "vitoid_fruit", "inventory");
		Registers.addItemRender(commandSeal, 0, Constants.modid + ":" + "command_seal", "inventory");
		Registers.addItemRender(toy, 0, Constants.modid + ":" + "toy", "inventory");
		Registers.addItemRender(toy, 1, Constants.modid + ":" + "toy_wet", "inventory");
		Registers.addItemVariant(toy, new String[] { Constants.modid + ":toy",
				Constants.modid + ":toy_wet" });
		Registers.addItemRender(microBit, 0, Constants.modid + ":" + "micro_bit", "inventory");
		Registers.addItemRender(megaBit, 0, Constants.modid + ":" + "mega_bit", "inventory");
		Registers.addItemRender(omegaBit, 0, Constants.modid + ":" + "omega_bit", "inventory");
		Registers.addItemRender(alphaBit, 0, Constants.modid + ":" + "alpha_bit", "inventory");
		Registers.addItemRender(deltaBit, 0, Constants.modid + ":" + "delta_bit", "inventory");
		Registers.addItemRender(evoBit, 0, Constants.modid + ":" + "evo_bit", "inventory");
		Registers.addItemRender(pettraBit, 0, Constants.modid + ":" + "pettra_bit", "inventory");
	}

	public static void loadDarkRenderers() {
		Registers.addItemRender(darkSword, 0, Constants.modid + ":" + "dark_sword", "inventory");
		Registers.addItemRender(darkAxe, 0, Constants.modid + ":" + "dark_axe", "inventory");
		Registers.addItemRender(darkPickaxe, 0, Constants.modid + ":" + "dark_pickaxe", "inventory");
		Registers.addItemRender(darkShovel, 0, Constants.modid + ":" + "dark_shovel", "inventory");
		Registers.addItemRender(darkHoe, 0, Constants.modid + ":" + "dark_hoe", "inventory");
		Registers.addItemVariant(darkBow, new String[] { Constants.modid + ":dark_bow",
				Constants.modid + ":dark_bow_pulling_0", Constants.modid + ":dark_bow_pulling_1",
				Constants.modid + ":dark_bow_pulling_2" });
		Registers.addItemRender(darkBow, 0, Constants.modid + ":" + "dark_bow", "inventory");
		Registers.addItemRender(darkSpark, 0, Constants.modid + ":" + "dark_spark", "inventory");
		Registers.addItemRender(darkEssence, 0, Constants.modid + ":" + "dark_essence", "inventory");
		Registers.addItemRender(darkDust, 0, Constants.modid + ":" + "dark_dust", "inventory");
		Registers.addItemRender(darkGrain, 0, Constants.modid + ":" + "dark_grain", "inventory");
		Registers.addItemRender(darkBone, 0, Constants.modid + ":" + "dark_bone", "inventory");
		Registers.addItemRender(kurrSeeds, 0, Constants.modid + ":" + "kurr_seeds", "inventory");
		Registers.addItemRender(kurrMeatRaw, 0, Constants.modid + ":" + "kurr_meat_raw", "inventory");
		Registers.addItemRender(kurrMeatCooked, 0, Constants.modid + ":" + "kurr_meat_cooked", "inventory");
	}
}