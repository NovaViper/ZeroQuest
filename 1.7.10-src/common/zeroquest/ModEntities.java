package common.zeroquest;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.projectile.EntityFireball;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.tileentity.TileEntityNileTable;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


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
	       	registerEntity(EntityJakan.class, "Jakan", 30);
	       	registerEntityEgg(EntityJakan.class, 0x0033CC, 0x00CCFF);
	       	registerEntity(EntityKortor.class, "Kortor", 31);
	       	registerEntityEgg(EntityKortor.class, 0x6699FF, 0xD1E0FF);
	}
	   public static void loadDarkCreatures(){
	       	registerEntity(EntityDarkZertum.class, "DarkZertum", 40);
	       	registerEntityEgg(EntityDarkZertum.class, 0xCCCCCC, 0x470047);
	       	registerEntity(EntityKurr.class, "Kurr", 41);
	       	registerEntityEgg(EntityKurr.class, 0xFF0000, 0x660000);
	   }
	   
	   public static void loadOthers() {
		   registerTileEntity(TileEntityNileTable.class, "Nile Worktable");
		   registerProjectileEntity(EntityFlamingPoisonball.class, "FPoisonball", 400);
		   EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 401, ZeroQuest.instance, 80, 3, true);
		   registerProjectileEntity(EntityIceball.class, "Iceball", 402);
		   registerProjectileEntity(EntityFireball.class, "Fireball", 403);
	   }
	   
		public static void registerEntity(Class entityClass, String saveName, int id) {
			EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 120, 1, true);
		}

		public static void registerEntityEgg(Class<? extends Entity> entity, int backgroundEggColor, int foregroundEggColor) 
		{
			int id = getUniqueEntityId();
			EntityList.IDtoClassMapping.put(id, entity);
			EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, backgroundEggColor, foregroundEggColor));
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