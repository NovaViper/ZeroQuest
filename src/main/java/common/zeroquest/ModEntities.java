package common.zeroquest;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.entity.*;
import common.zeroquest.entity.projectile.*;
import common.zeroquest.entity.zertum.*;
import common.zeroquest.entity.tileentity.*;
import common.zeroquest.lib.Registers;

public class ModEntities {

	public static final String tag = "EntityName";
	public static int startEntityId = 300;

	// Put Renderers in ClientProxy//
	// Put Guis in CommonProxy//
	// Put Creature SFX in Sound and sounds.json//
	public static void loadCreatures() {
		Registers.registerEntity(EntityZertum.class, "Zertum", 0);
		Registers.registerEntityEgg(EntityZertum.class, 0xCCCCCC, 0x33FFFF);
		Registers.registerEntity(EntityRedZertum.class, "RedZertum", 1);
		Registers.registerEntityEgg(EntityRedZertum.class, 0xCCCCCC, 0xFF0000);
		Registers.registerEntity(EntityDestroZertum.class, "DestroZertum", 2);
		Registers.registerEntityEgg(EntityDestroZertum.class, 0xCCCCCC, 0xE6CC80);
		Registers.registerEntity(EntityIceZertum.class, "IceZertum", 3);
		Registers.registerEntityEgg(EntityIceZertum.class, 0xCCCCCC, 0x6699FF);
		Registers.registerEntity(EntityForisZertum.class, "ForisZertum", 4);
		Registers.registerEntityEgg(EntityForisZertum.class, 0xCCCCCC, 0x33CC33);
		Registers.registerEntity(EntityMetalZertum.class, "MetalZertum", 5);
		Registers.registerEntityEgg(EntityMetalZertum.class, 0xCCCCCC, 0x666699);
		Registers.registerEntity(EntityJakan.class, "Jakan", 20);
		Registers.registerEntityEgg(EntityJakan.class, 0x0033CC, 0x00CCFF);
		Registers.registerEntity(EntityKortor.class, "Kortor", 21);
		Registers.registerEntityEgg(EntityKortor.class, 0x6699FF, 0xD1E0FF);
		Registers.registerEntity(EntityRiggator.class, "Riggator", 22);
		Registers.registerEntityEgg(EntityRiggator.class, 0x1D302C, 0x671734);
		/* registerEntity(EntityRowarn.class, "Rowarn", 23);
		 * registerEntityEgg(EntityRowarn.class, 0x004A7F, 0x002C4C); */
	}

	public static void loadDarkCreatures() {
		Registers.registerEntity(EntityDarkZertum.class, "DarkZertum", 40);
		Registers.registerEntityEgg(EntityDarkZertum.class, 0xCCCCCC, 0x470047);
		Registers.registerEntity(EntityKurr.class, "Kurr", 41);
		Registers.registerEntityEgg(EntityKurr.class, 0xFF0000, 0x660000);
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
		// addSpawn(EntityRowarn.class, 100, 1, 3,
		// EnumCreatureType.WATER_CREATURE, ModBiomes.walRockland);
	}

	public static void loadDarkSpawns() {
		Registers.addEntitySpawn(EntityDarkZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.darkWasteland);
		Registers.addEntitySpawn(EntityKurr.class, 100, 2, 3, EnumCreatureType.MONSTER, ModBiomes.darkWasteland);
	}

	public static void loadOthers() {
		Registers.registerTileEntity(TileEntityNileWorkbench.class, "Nile Worktable");
		Registers.registerTileEntity(TileEntityFoodBowl.class, "Food Bowl");
		Registers.registerProjectileEntity(EntityFlamingPoisonball.class, "FPoisonball", 400);
		Registers.registerProjectileEntity(EntityGrenade.class, "Grenade", 401);
		Registers.registerProjectileEntity(EntityIceball.class, "Iceball", 402);
		Registers.registerProjectileEntity(EntityZertumBeam.class, "ZertumBeam", 403);
	}
}