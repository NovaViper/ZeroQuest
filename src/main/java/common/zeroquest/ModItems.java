package common.zeroquest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.item.*;
import common.zeroquest.item.tools.*;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Registers;

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

	public static Item darkSpark;
	private static Item petraBtye;

	public static void load() {
		// Tools/Weapons//
		nileSword = new ItemNileSword(ZeroQuest.nileEssence).setUnlocalizedName("nileSword").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nileSword, "nile_sword");
		nileAxe = new ItemNileAxe(ZeroQuest.nileEssence).setUnlocalizedName("nileAxe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nileAxe, "nile_axe");
		nilePickaxe = new ItemNilePickaxe(ZeroQuest.nileEssence).setUnlocalizedName("nilePickaxe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nilePickaxe, "nile_pickaxe");
		nileSpade = new ItemNileSpade(ZeroQuest.nileEssence).setUnlocalizedName("nileShovel").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nileSpade, "nile_shovel");
		nileHoe = new ItemNileHoe(ZeroQuest.nileEssence).setUnlocalizedName("nileHoe").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nileHoe, "nile_hoe");
		nileBow = new ItemNileBow("nile", 484).setUnlocalizedName("nileBow").setCreativeTab(ZeroQuest.ZeroTab);
		Registers.registerItem(nileBow, "nile_bow");
		nileGrenade = new ItemNileGrenade().setUnlocalizedName("nileGrenade");
		Registers.registerItem(nileGrenade, "nile_grenade");

		// Basics//
		nileEssence = new ItemZQ().setUnlocalizedName("nileEssence");
		Registers.registerItem(nileEssence, "nile_essence");
		nileDust = new ItemZQ().setUnlocalizedName("nileDust");
		Registers.registerItem(nileDust, "nile_dust");
		nileGrain = new ItemZQ().setUnlocalizedName("nileGrain");
		Registers.registerItem(nileGrain, "nile_grain");
		nileCoal = new ItemZQ().setUnlocalizedName("nileCoal");
		Registers.registerItem(nileCoal, "nile_coal");
		dogTreat = new ItemZQ().setUnlocalizedName("dogTreat");
		Registers.registerItem(dogTreat, "dog_treat");
		nileBone = new ItemZQ().setUnlocalizedName("nileBone");
		Registers.registerItem(nileBone, "nile_bone");
		commandSeal = new ItemCommandSeal().setUnlocalizedName("commandSeal");
		Registers.registerItem(commandSeal, "command_seal");
		toy = new ItemToy().setUnlocalizedName("toy");
		Registers.registerItem(toy, "toy");

		// Bits for Talents\\
		microBit = new ItemBit(0, 20).setUnlocalizedName("microBit");
		Registers.registerItem(microBit, "micro_bit");
		megaBit = new ItemBit(20, 50).setUnlocalizedName("megaBit");
		Registers.registerItem(megaBit, "mega_bit");
		omegaBit = new ItemBit(50, 80).setUnlocalizedName("omegaBit");
		Registers.registerItem(omegaBit, "omega_bit");
		alphaBit = new ItemAlphaBit().setUnlocalizedName("alphaBit");
		Registers.registerItem(alphaBit, "alpha_bit");
		deltaBit = new ItemDeltaBit().setUnlocalizedName("deltaBit");
		Registers.registerItem(deltaBit, "delta_bit");
		evoBit = new ItemZQ().setUnlocalizedName("evoBit");
		Registers.registerItem(evoBit, "evo_bit");
		pettraBit = new ItemZQ().setUnlocalizedName("pettraBit");
		Registers.registerItem(pettraBit, "pettra_bit");

		// Portal Sparks//
		nileSpark = new ItemNileSpark().setUnlocalizedName("nileSpark");
		Registers.registerItem(nileSpark, "nile_spark");

		// Projectiles//
		FPoisonball = new ItemProjectile().setUnlocalizedName("FPoisonball");
		Registers.registerItem(FPoisonball, "flaming_poisonball");
		iceBall = new ItemProjectile().setUnlocalizedName("iceBall");
		Registers.registerItem(iceBall, "ice_ball");

		// Food/Crops//
		zertumMeatRaw = new ZQFood(3, 1.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatRaw");
		Registers.registerItem(zertumMeatRaw, "zertum_meat_raw");
		zertumMeatCooked = new ZQFood(5, 2.5F, true, ZeroQuest.ZeroTab).setUnlocalizedName("zertumMeatCooked");
		Registers.registerItem(zertumMeatCooked, "zertum_meat_cooked");
		vitoidSeed = new ItemNileSeed(ModBlocks.vitoidPlant, Blocks.farmland).setUnlocalizedName("vitoidSeed");
		Registers.registerItem(vitoidSeed, "vitoid_seed");
		vitoidFruit = new VitoidFruit(2, 0.5F, false).setUnlocalizedName("vitoidFruit");
		Registers.registerItem(vitoidFruit, "vitoid_fruit");
		jakanMeatRaw = new ZQFood(4, 1.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatRaw");
		Registers.registerItem(jakanMeatRaw, "jakan_meat_raw");
		jakanMeatCooked = new ZQFood(5, 2.7F, true, ZeroQuest.ZeroTab).setUnlocalizedName("jakanMeatCooked");
		Registers.registerItem(jakanMeatCooked, "jakan_meat_cooked");
	}

	public static void loadDarkItems() {

		darkSword = new ItemNileSword(ZeroQuest.darkEssence).setUnlocalizedName("darkSword").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkSword, "dark_sword");
		darkAxe = new ItemNileAxe(ZeroQuest.darkEssence).setUnlocalizedName("darkAxe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkAxe, "dark_axe");
		darkPickaxe = new ItemNilePickaxe(ZeroQuest.darkEssence).setUnlocalizedName("darkPickaxe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkPickaxe, "dark_pickaxe");
		darkShovel = new ItemNileSpade(ZeroQuest.darkEssence).setUnlocalizedName("darkShovel").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkShovel, "dark_shovel");
		darkHoe = new ItemNileHoe(ZeroQuest.darkEssence).setUnlocalizedName("darkHoe").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkHoe, "dark_hoe");
		darkBow = new ItemNileBow("dark", 584).setUnlocalizedName("darkBow").setCreativeTab(ZeroQuest.DarkTab);
		Registers.registerItem(darkBow, "dark_bow");
		darkSpark = new ItemDarkSpark().setUnlocalizedName("darkSpark");
		Registers.registerItem(darkSpark, "dark_spark");

		darkEssence = new ItemDQ().setUnlocalizedName("darkEssence");
		Registers.registerItem(darkEssence, "dark_essence");
		darkDust = new ItemDQ().setUnlocalizedName("darkDust");
		Registers.registerItem(darkDust, "dark_dust");
		darkGrain = new ItemDQ().setUnlocalizedName("darkGrain");
		Registers.registerItem(darkGrain, "dark_grain");
		darkBone = new ItemDQ().setUnlocalizedName("darkBone");
		Registers.registerItem(darkBone, "dark_bone");
		kurrSeeds = new KurrSeed(4, 1.2F, false).setUnlocalizedName("kurrSeeds");
		Registers.registerItem(kurrSeeds, "kurr_seeds");
	}

	public static void loadRenderers() {

		Registers.registerItemRender(nileSword, 0, Constants.modid + ":" + "nile_sword", "inventory");
		Registers.registerItemRender(nileAxe, 0, Constants.modid + ":" + "nile_axe", "inventory");
		Registers.registerItemRender(nilePickaxe, 0, Constants.modid + ":" + "nile_pickaxe", "inventory");
		Registers.registerItemRender(nileSpade, 0, Constants.modid + ":" + "nile_shovel", "inventory");
		Registers.registerItemRender(nileHoe, 0, Constants.modid + ":" + "nile_hoe", "inventory");
		Registers.addItemVariant(nileBow, new String[] { Constants.modid + ":nile_bow",
				Constants.modid + ":nile_bow_pulling_0", Constants.modid + ":nile_bow_pulling_1",
				Constants.modid + ":nile_bow_pulling_2" });
		Registers.registerItemRender(nileBow, 0, Constants.modid + ":nile_bow", "inventory");
		Registers.registerItemRender(nileGrenade, 0, Constants.modid + ":" + "nile_grenade", "inventory");
		Registers.registerItemRender(nileEssence, 0, Constants.modid + ":" + "nile_essence", "inventory");
		Registers.registerItemRender(nileDust, 0, Constants.modid + ":" + "nile_dust", "inventory");
		Registers.registerItemRender(nileGrain, 0, Constants.modid + ":" + "nile_grain", "inventory");
		Registers.registerItemRender(nileCoal, 0, Constants.modid + ":" + "nile_coal", "inventory");
		Registers.registerItemRender(dogTreat, 0, Constants.modid + ":" + "dog_treat", "inventory");
		Registers.registerItemRender(nileBone, 0, Constants.modid + ":" + "nile_bone", "inventory");
		Registers.registerItemRender(nileSpark, 0, Constants.modid + ":" + "nile_spark", "inventory");
		Registers.registerItemRender(FPoisonball, 0, Constants.modid + ":" + "flaming_poisonball", "inventory");
		Registers.registerItemRender(iceBall, 0, Constants.modid + ":" + "ice_ball", "inventory");
		Registers.registerItemRender(zertumMeatRaw, 0, Constants.modid + ":" + "zertum_meat_raw", "inventory");
		Registers.registerItemRender(zertumMeatCooked, 0, Constants.modid + ":" + "zertum_meat_cooked", "inventory");
		Registers.registerItemRender(vitoidSeed, 0, Constants.modid + ":" + "vitoid_seed", "inventory");
		Registers.registerItemRender(vitoidFruit, 0, Constants.modid + ":" + "vitoid_fruit", "inventory");
		Registers.registerItemRender(jakanMeatRaw, 0, Constants.modid + ":" + "jakan_meat_raw", "inventory");
		Registers.registerItemRender(jakanMeatCooked, 0, Constants.modid + ":" + "jakan_meat_cooked", "inventory");
		Registers.registerItemRender(commandSeal, 0, Constants.modid + ":" + "command_seal", "inventory");
		Registers.registerItemRender(toy, 0, Constants.modid + ":" + "toy", "inventory");
		Registers.registerItemRender(toy, 1, Constants.modid + ":" + "toy_wet", "inventory");
		Registers.addItemVariant(toy, new String[] { Constants.modid + ":toy",
				Constants.modid + ":toy_wet" });
		Registers.registerItemRender(microBit, 0, Constants.modid + ":" + "micro_bit", "inventory");
		Registers.registerItemRender(megaBit, 0, Constants.modid + ":" + "mega_bit", "inventory");
		Registers.registerItemRender(omegaBit, 0, Constants.modid + ":" + "omega_bit", "inventory");
		Registers.registerItemRender(alphaBit, 0, Constants.modid + ":" + "alpha_bit", "inventory");
		Registers.registerItemRender(deltaBit, 0, Constants.modid + ":" + "delta_bit", "inventory");
		Registers.registerItemRender(evoBit, 0, Constants.modid + ":" + "evo_bit", "inventory");
		Registers.registerItemRender(pettraBit, 0, Constants.modid + ":" + "pettra_bit", "inventory");
	}

	public static void loadDarkRenderers() {
		Registers.registerItemRender(darkSword, 0, Constants.modid + ":" + "dark_sword", "inventory");
		Registers.registerItemRender(darkAxe, 0, Constants.modid + ":" + "dark_axe", "inventory");
		Registers.registerItemRender(darkPickaxe, 0, Constants.modid + ":" + "dark_pickaxe", "inventory");
		Registers.registerItemRender(darkShovel, 0, Constants.modid + ":" + "dark_shovel", "inventory");
		Registers.registerItemRender(darkHoe, 0, Constants.modid + ":" + "dark_hoe", "inventory");
		Registers.addItemVariant(darkBow, new String[] { Constants.modid + ":dark_bow",
				Constants.modid + ":dark_bow_pulling_0", Constants.modid + ":dark_bow_pulling_1",
				Constants.modid + ":dark_bow_pulling_2" });
		Registers.registerItemRender(darkBow, 0, Constants.modid + ":" + "dark_bow", "inventory");
		Registers.registerItemRender(darkSpark, 0, Constants.modid + ":" + "dark_spark", "inventory");
		Registers.registerItemRender(darkEssence, 0, Constants.modid + ":" + "dark_essence", "inventory");
		Registers.registerItemRender(darkDust, 0, Constants.modid + ":" + "dark_dust", "inventory");
		Registers.registerItemRender(darkGrain, 0, Constants.modid + ":" + "dark_grain", "inventory");
		Registers.registerItemRender(darkBone, 0, Constants.modid + ":" + "dark_bone", "inventory");
		Registers.registerItemRender(kurrSeeds, 0, Constants.modid + ":" + "kurr_seeds", "inventory");
	}
}