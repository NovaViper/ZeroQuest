package common.zeroquest;

import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


public class ModEntities {
	
	public static int startEntityId = 300;
	public static final String tag = "EntityName";
	
	//Put Entities in CustomEntityList//
	//Put Renderers in ClientProxy//
	//Put Guis in CommonProxy//
	   public static void loadCreatures() {
	       	registerEntity(EntityZertum.class, "Zertum", 0);
	       	EntityRegistry.addSpawn(EntityZertum.class, 10, 2, 5, EnumCreatureType.creature);
	       	registerEntity(EntityRedZertum.class, "RedZertum", 1);
	       	EntityRegistry.addSpawn(EntityRedZertum.class, 10, 3, 6, EnumCreatureType.creature);
	       	registerEntity(EntityDestroZertum.class, "DestroZertum", 2);
	       	EntityRegistry.addSpawn(EntityDestroZertum.class, 10, 3, 6, EnumCreatureType.creature);
	}
	   public static void loadDarkCreatures(){
	       	registerEntity(EntityDarkZertum.class, "DarkZertum", 10);
	       	EntityRegistry.addSpawn(EntityDarkZertum.class, 10, 3, 6, EnumCreatureType.creature);
	   }
	   
	   public static void loadTileEntities() {
			GameRegistry.registerTileEntity(TileEntityNileTable.class, "Nile Worktable");
	   }
	   
		public static void registerEntity(Class entityClass, String saveName, int id) {
		    EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 80, 3, false);
		}

	
   	public static int getUniqueEntityId()
   	{
   		do
   		{
   			startEntityId++;
   		}
   		
   		while(EntityList.getStringFromID(startEntityId) != null);
   			
   			return startEntityId;
   	}
}
