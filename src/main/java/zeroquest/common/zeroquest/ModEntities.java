package common.zeroquest;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityMetalZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityRiggator;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.entity.tileentity.TileEntityNileWorkbench;
import common.zeroquest.lib.Constants;


public class ModEntities {
	
	public static final String tag = "EntityName";
	public static int startEntityId = 300;
	
	//Put Renderers in ClientProxy//
	//Put Guis in CommonProxy//
	//Put Creature SFX in Sound and sounds.json//
	   public static void loadCreatures() {
	       	registerEntity(EntityZertum.class, "Zertum", 0);
	       	registerEntityEgg(EntityZertum.class, 0xCCCCCC, 0x33FFFF);
	       	registerEntity(EntityRedZertum.class, "RedZertum", 1);
	       	registerEntityEgg(EntityRedZertum.class, 0xCCCCCC, 0xFF0000);
	       	registerEntity(EntityDestroZertum.class, "DestroZertum", 2);
	       	registerEntityEgg(EntityDestroZertum.class, 0xCCCCCC, 0xE6CC80);
	       	registerEntity(EntityIceZertum.class, "IceZertum", 3);
	       	registerEntityEgg(EntityIceZertum.class, 0xCCCCCC, 0x6699FF);
	       	registerEntity(EntityForisZertum.class, "ForisZertum", 4);
	       	registerEntityEgg(EntityForisZertum.class, 0xCCCCCC, 0x33CC33);
	       	registerEntity(EntityMetalZertum.class, "MetalZertum", 5);
	       	registerEntityEgg(EntityMetalZertum.class, 0xCCCCCC, 0x666699);      	
	       	registerEntity(EntityJakan.class, "Jakan", 30);
	       	registerEntityEgg(EntityJakan.class, 0x0033CC, 0x00CCFF);
	       	registerEntity(EntityKortor.class, "Kortor", 31);
	       	registerEntityEgg(EntityKortor.class, 0x6699FF, 0xD1E0FF);
	       	registerEntity(EntityRiggator.class, "Riggator", 32);
	       	registerEntityEgg(EntityRiggator.class, 0x1D302C, 0x671734);
	}
	   public static void loadDarkCreatures(){
	       	registerEntity(EntityDarkZertum.class, "DarkZertum", 40);
	       	registerEntityEgg(EntityDarkZertum.class, 0xCCCCCC, 0x470047);
	       	registerEntity(EntityKurr.class, "Kurr", 41);
	       	registerEntityEgg(EntityKurr.class, 0xFF0000, 0x660000);
	   }
	   
	   public static void loadSpawns(){
	       	addSpawn(EntityZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.bioZone, ModBiomes.nileSavanna, ModBiomes.nileSavannaPlateau, ModBiomes.nileSwampland, ModBiomes.pinkZone);
	       	addSpawn(EntityRedZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.redSeed);
	       	addSpawn(EntityDestroZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.destroZone, ModBiomes.destroZoneHills);
	       	addSpawn(EntityIceZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.blueTaiga, ModBiomes.blueTaigaHills, ModBiomes.blueColdTaiga, ModBiomes.blueColdTaigaHills);
	       	addSpawn(EntityForisZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.nileJungle, ModBiomes.nileJungleHills, ModBiomes.nileJungleEdge, ModBiomes.nileSwampland);
	       	addSpawn(EntityMetalZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.walRockland);	 
	       	addSpawn(EntityJakan.class, 100, 2, 3, EnumCreatureType.CREATURE, ModBiomes.walRockland);
	       	addSpawn(EntityKortor.class, 100, 2, 3, EnumCreatureType.CREATURE, ModBiomes.nileSavanna, ModBiomes.nileSavannaPlateau, ModBiomes.nileJungle, ModBiomes.nileJungleEdge, ModBiomes.nileJungleHills);
	       	addSpawn(EntityRiggator.class, 100, 2, 3, EnumCreatureType.MONSTER, ModBiomes.bioZone, ModBiomes.nileSwampland, ModBiomes.nileMesa, ModBiomes.nileMesaPlateau, ModBiomes.nileMesaPlateau_F);
	   }
	   
	   public static void loadDarkSpawns(){
	       	addSpawn(EntityDarkZertum.class, 100, 4, 5, EnumCreatureType.CREATURE, ModBiomes.darkWasteland);
	       	addSpawn(EntityKurr.class, 100, 2, 3, EnumCreatureType.MONSTER, ModBiomes.darkWasteland);
	   }
	   
	   public static void loadOthers() {
		   registerTileEntity(TileEntityNileWorkbench.class, "Nile Worktable");
		   registerProjectileEntity(EntityFlamingPoisonball.class, "FPoisonball", 400);
		   EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 401, ZeroQuest.instance, 80, 3, true);
		   registerProjectileEntity(EntityIceball.class, "Iceball", 402);
		   registerProjectileEntity(EntityFireball.class, "Fireball", 403);
	   }
	   
		public static void addSpawn(Class entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes){
			EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
		}
	   
		public static void registerEntity(Class entityClass, String saveName, int id) {
			EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 120, 1, true);
		}

		public static void registerEntityEgg(Class<? extends Entity> entity, int main, int spots) 
		{
			int id = getUniqueEntityId();
			EntityList.idToClassMapping.put(id, entity);
			EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, main, spots));
		}
		
		public static void registerTileEntity(Class entityTileClass, String saveName){
			GameRegistry.registerTileEntity(entityTileClass, saveName);
		}
		
		public static void registerProjectileEntity(Class entityClass, String saveName, int id) {
		    EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 128, 1, true);
		}
		
		public static int getUniqueEntityId() 
		{
			do 
			{
				startEntityId ++;
			} 
			while (EntityList.getStringFromID(startEntityId) != null);

			return startEntityId;
		}
}