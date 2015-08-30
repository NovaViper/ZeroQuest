package net.novaviper.zeroquest;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.novaviper.zeroquest.common.entity.creature.*;
import net.novaviper.zeroquest.common.entity.mob.*;
import net.novaviper.zeroquest.common.entity.projectile.*;
import net.novaviper.zeroquest.common.lib.Registers;
import net.novaviper.zeroquest.common.tileentity.*;

public class ModEntities {

	// Put Renderers in ClientProxy//
	// Put Guis in CommonProxy//
	// Put Creature SFX in Sound and sounds.json//
	public static void loadCreatures() {
		Registers.addEntity(EntityZertum.class, "Zertum", 0, ZeroQuest.instance);
		Registers.addEntityEgg(EntityZertum.class, 0xCCCCCC, 0x33FFFF);
		Registers.addEntity(EntityRedZertum.class, "RedZertum", 1, ZeroQuest.instance);
		Registers.addEntityEgg(EntityRedZertum.class, 0xCCCCCC, 0xFF0000);
		Registers.addEntity(EntityDestroZertum.class, "DestroZertum", 2, ZeroQuest.instance);
		Registers.addEntityEgg(EntityDestroZertum.class, 0xCCCCCC, 0xE6CC80);
		Registers.addEntity(EntityIceZertum.class, "IceZertum", 3, ZeroQuest.instance);
		Registers.addEntityEgg(EntityIceZertum.class, 0xCCCCCC, 0x6699FF);
		Registers.addEntity(EntityForisZertum.class, "ForisZertum", 4, ZeroQuest.instance);
		Registers.addEntityEgg(EntityForisZertum.class, 0xCCCCCC, 0x33CC33);
		Registers.addEntity(EntityMetalZertum.class, "MetalZertum", 5, ZeroQuest.instance);
		Registers.addEntityEgg(EntityMetalZertum.class, 0xCCCCCC, 0x666699);
		Registers.addEntity(EntityJakan.class, "Jakan", 20, ZeroQuest.instance);
		Registers.addEntityEgg(EntityJakan.class, 0x0033CC, 0x00CCFF);
		Registers.addEntity(EntityKortor.class, "Kortor", 21, ZeroQuest.instance);
		Registers.addEntityEgg(EntityKortor.class, 0x6699FF, 0xD1E0FF);
		Registers.addEntity(EntityRiggator.class, "Riggator", 22, ZeroQuest.instance);
		Registers.addEntityEgg(EntityRiggator.class, 0x1D302C, 0x671734);
	}

	public static void loadDarkCreatures() {
		Registers.addEntity(EntityDarkZertum.class, "DarkZertum", 6, ZeroQuest.instance);
		Registers.addEntityEgg(EntityDarkZertum.class, 0xCCCCCC, 0x470047);
		Registers.addEntity(EntityKurr.class, "Kurr", 41, ZeroQuest.instance);
		Registers.addEntityEgg(EntityKurr.class, 0xFF0000, 0x660000);
	}

	public static void loadSpawns() {
		Registers.addEntitySpawn(EntityZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.bioZone, ModBiomes.nileSavanna, ModBiomes.nileSavannaPlateau, ModBiomes.nileSwampland, ModBiomes.pinkZone);
		Registers.addEntitySpawn(EntityRedZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.redSeed);
		Registers.addEntitySpawn(EntityDestroZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.destroZone, ModBiomes.destroZoneHills);
		Registers.addEntitySpawn(EntityIceZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.blueTaiga, ModBiomes.blueTaigaHills, ModBiomes.blueColdTaiga, ModBiomes.blueColdTaigaHills);
		Registers.addEntitySpawn(EntityForisZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.nileJungle, ModBiomes.nileJungleHills, ModBiomes.nileJungleEdge, ModBiomes.nileSwampland);
		Registers.addEntitySpawn(EntityMetalZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.walRockland);
		Registers.addEntitySpawn(EntityJakan.class, 100, 2, 3, EnumCreatureType.CREATURE, ModBiomes.walRockland);
		Registers.addEntitySpawn(EntityKortor.class, 100, 2, 3, EnumCreatureType.CREATURE, ModBiomes.nileSavanna, ModBiomes.nileSavannaPlateau, ModBiomes.nileJungle, ModBiomes.nileJungleEdge, ModBiomes.nileJungleHills);
		Registers.addEntitySpawn(EntityRiggator.class, 100, 2, 3, EnumCreatureType.MONSTER, ModBiomes.bioZone, ModBiomes.nileSwampland, ModBiomes.nileMesa, ModBiomes.nileMesaPlateau, ModBiomes.nileMesaPlateau_F);
	}

	public static void loadDarkSpawns() {
		Registers.addEntitySpawn(EntityDarkZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.darkWasteland);
		Registers.addEntitySpawn(EntityKurr.class, 100, 2, 3, EnumCreatureType.MONSTER, ModBiomes.darkWasteland);
	}

	public static void loadOthers() {
		Registers.addTileEntity(TileEntityNileWorkbench.class, "Nile Worktable");
		Registers.addTileEntity(TileEntityFoodBowl.class, "Food Bowl");
		Registers.addProjectileEntity(EntityFlamingPoisonball.class, "FPoisonball", 400, ZeroQuest.instance);
		Registers.addProjectileEntity(EntityGrenade.class, "Grenade", 401, ZeroQuest.instance);
		Registers.addProjectileEntity(EntityIceball.class, "Iceball", 402, ZeroQuest.instance);
		Registers.addProjectileEntity(EntityZertumBeam.class, "ZertumBeam", 403, ZeroQuest.instance);
	}
}