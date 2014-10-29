package common.zeroquest.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import common.zeroquest.ModItems;
import common.zeroquest.client.gui.GuiDarkPack;
import common.zeroquest.client.gui.GuiDestroPack;
import common.zeroquest.client.gui.GuiForisPack;
import common.zeroquest.client.gui.GuiIcePack;
import common.zeroquest.client.gui.GuiJakanPack;
import common.zeroquest.client.gui.GuiKortorPack;
import common.zeroquest.client.gui.GuiPack;
import common.zeroquest.client.gui.GuiRedPack;
import common.zeroquest.client.gui.NileTableGui;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.inventory.ContainerDarkPack;
import common.zeroquest.inventory.ContainerDestroPack;
import common.zeroquest.inventory.ContainerForisPack;
import common.zeroquest.inventory.ContainerIcePack;
import common.zeroquest.inventory.ContainerJakanPack;
import common.zeroquest.inventory.ContainerKortorPack;
import common.zeroquest.inventory.ContainerNileTable;
import common.zeroquest.inventory.ContainerPack;
import common.zeroquest.inventory.ContainerRedPack;
import common.zeroquest.tileentity.TileEntityNileTable;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{
	
	public static final int NileTable = 0;
	public static final int ZertumPack = 1;
	public static final int RedZertumPack = 2;
	public static final int DarkZertumPack = 3;
	public static final int DestroZertumPack = 4;
	public static final int IceZertumPack = 5;
	public static final int ForisZertumPack = 6;
	public static final int JakanPack = 7;
	public static final int KortorPack = 8;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 if(ID == NileTable) {
			TileEntity target = world.getTileEntity(x, y, z);
			if(!(target instanceof TileEntityNileTable)) 
				return null;

			TileEntityNileTable tileNileTable = (TileEntityNileTable)target;
			ContainerNileTable tableContainer = new ContainerNileTable(tileNileTable, player.inventory, world, x, y, z);
			return tableContainer;
		}
		 else if(ID == ZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityZertum)) {
            	return null;
            }
            EntityZertum dog = (EntityZertum)target;
			ContainerPack packPuppyContainer = new ContainerPack(player.inventory, dog);
			return packPuppyContainer;
		} 
		 else if(ID == RedZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityRedZertum)) {
            	return null;
            }
            EntityRedZertum dog = (EntityRedZertum)target;
            ContainerRedPack packPuppyContainer = new ContainerRedPack(player.inventory, dog);
			return packPuppyContainer;
		}
		 else if(ID == DarkZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityDarkZertum)) {
            	return null;
            }
            EntityDarkZertum dog = (EntityDarkZertum)target;
            ContainerDarkPack packPuppyContainer = new ContainerDarkPack(player.inventory, dog);
			return packPuppyContainer;
		} 
		 else if(ID == DestroZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityDestroZertum)) {
            	return null;
            }
            EntityDestroZertum dog = (EntityDestroZertum)target;
            ContainerDestroPack packPuppyContainer = new ContainerDestroPack(player.inventory, dog);
			return packPuppyContainer;
		}
		 else if(ID == IceZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityIceZertum)) {
            	return null;
            }
            EntityIceZertum dog = (EntityIceZertum)target;
            ContainerIcePack packPuppyContainer = new ContainerIcePack(player.inventory, dog);
			return packPuppyContainer;
		}
		 else if(ID == ForisZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityIceZertum)) {
            	return null;
            }
            EntityForisZertum dog = (EntityForisZertum)target;
            ContainerForisPack packPuppyContainer = new ContainerForisPack(player.inventory, dog);
			return packPuppyContainer;
		}
		 else if(ID == JakanPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityJakan)) {
            	return null;
            }
            EntityJakan dragon = (EntityJakan)target;
            ContainerJakanPack packPuppyContainer = new ContainerJakanPack(player.inventory, dragon);
			return packPuppyContainer;
		}
		 else if(ID == KortorPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityJakan)) {
            	return null;
            }
            EntityKortor raptor = (EntityKortor)target;
            ContainerKortorPack packPuppyContainer = new ContainerKortorPack(player.inventory, raptor);
			return packPuppyContainer;
		}
		return null;
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
		if(ID == NileTable) {
			TileEntity target = world.getTileEntity(x, y, z);
			if(!(target instanceof TileEntityNileTable)) 
				return null;

			TileEntityNileTable tileNileTable = (TileEntityNileTable)target;
			NileTableGui tableGui = new NileTableGui(player.inventory, tileNileTable, world, x, y, z);
			return tableGui;
		}
		else if(ID == ZertumPack) {
					Entity target = player.worldObj.getEntityByID(x);
		           if(!(target instanceof EntityZertum)) {
		           	return null;
		           }
		           EntityZertum dog = (EntityZertum)target;
					GuiPack packGui = new GuiPack(player.inventory, dog);
					return packGui;
			 	}
		else if(ID == RedZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
           if(!(target instanceof EntityRedZertum)) {
           	return null;
           }
           EntityRedZertum dog = (EntityRedZertum)target;
			GuiRedPack packGui = new GuiRedPack(player.inventory, dog);
			return packGui;
	 	}	
		 else if(ID == DestroZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
           if(!(target instanceof EntityDestroZertum)) {
           	return null;
           }
           EntityDestroZertum dog = (EntityDestroZertum)target;
           GuiDestroPack packGui = new GuiDestroPack(player.inventory, dog);
			return packGui;
		}
			else if(ID == DarkZertumPack) {
				Entity target = player.worldObj.getEntityByID(x);
	         if(!(target instanceof EntityDarkZertum)) {
	         	return null;
	         }
	         EntityDarkZertum dog = (EntityDarkZertum)target;
	         GuiDarkPack packGui = new GuiDarkPack(player.inventory, dog);
				return packGui;
			}
			else if(ID == IceZertumPack) {
				Entity target = player.worldObj.getEntityByID(x);
	         if(!(target instanceof EntityIceZertum)) {
	         	return null;
	         }
	         EntityIceZertum dog = (EntityIceZertum)target;
	         GuiIcePack packGui = new GuiIcePack(player.inventory, dog);
				return packGui;
			}
			else if(ID == ForisZertumPack) {
				Entity target = player.worldObj.getEntityByID(x);
	         if(!(target instanceof EntityForisZertum)) {
	         	return null;
	         }
	         EntityForisZertum dog = (EntityForisZertum)target;
	         GuiForisPack packGui = new GuiForisPack(player.inventory, dog);
				return packGui;
			}
		 else if(ID == JakanPack) {
			Entity target = player.worldObj.getEntityByID(x);
           if(!(target instanceof EntityJakan)) {
           	return null;
           }
           EntityJakan dragon = (EntityJakan)target;
           GuiJakanPack packGui = new GuiJakanPack(player.inventory, dragon);
			return packGui;
		 }
		 else if(ID == KortorPack) {
			Entity target = player.worldObj.getEntityByID(x);
           if(!(target instanceof EntityKortor)) {
           	return null;
           }
           EntityKortor dragon = (EntityKortor)target;
           GuiKortorPack packGui = new GuiKortorPack(player.inventory, dragon);
			return packGui;
		 } 
		return null;
	}
	
    public void registerChestItems() {
        ChestGenHooks chestGenHooksDungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
        chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 30));
        // chance < saddle (1/16, ca. 6%, in max 8 slots -> 40% at least 1 egg, 0.48 eggs per chest): I think that's okay

        ChestGenHooks chestGenHooksMineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
        chestGenHooksMineshaft.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 5));
        // chance == gold ingot (1/18, ca. 6%, in 3-6 slots -> 23% at least 1 egg, 0.27 eggs per chest):
        // exploring a random mine shaft in creative mode yielded 2 eggs out of about 10 chests in 1 hour

        ChestGenHooks chestGenHooksJungleChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
        chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 15));
        // chance == gold ingot (15/81, ca. 18%, in 2-5 slots -> 51% at least 1 egg, 0.65 eggs per chest, 1.3 eggs per temple):
        // jungle temples are so rare, it should be rewarded

        ChestGenHooks chestGenHooksDesertChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
        chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 10));
        // chance == iron ingot (10/76, ca. 13%, in 2-5 slots -> 39% at least 1 egg, 0.46 eggs per chest, 1.8 eggs per temple):
        // desert temples are so rare, it should be rewarded
    }
	
	public void registerRenderThings() {
		
	}
}