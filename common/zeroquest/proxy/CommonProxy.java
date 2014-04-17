package common.zeroquest.proxy;

import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.gui.GuiDarkPack;
import common.zeroquest.gui.GuiDestroPack;
import common.zeroquest.gui.GuiPack;
import common.zeroquest.gui.GuiRedPack;
import common.zeroquest.gui.NileTableGui;
import common.zeroquest.inventory.ContainerDarkPack;
import common.zeroquest.inventory.ContainerDestroPack;
import common.zeroquest.inventory.ContainerNileTable;
import common.zeroquest.inventory.ContainerPack;
import common.zeroquest.inventory.ContainerRedPack;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{
	

	public static final int NileTable = 0;
	public static final int ZertumPack = 1;
	public static final int RedZertumPack = 2;
	public static final int DarkZertumPack = 3;
	public static final int DestroZertumPack = 4;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 if(ID == NileTable) {
			TileEntity target = world.getBlockTileEntity(x, y, z);
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
		return null;
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
		if(ID == NileTable) {
			TileEntity target = world.getBlockTileEntity(x, y, z);
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
		else if(ID == DarkZertumPack) {
			Entity target = player.worldObj.getEntityByID(x);
         if(!(target instanceof EntityDarkZertum)) {
         	return null;
         }
         EntityDarkZertum dog = (EntityDarkZertum)target;
         GuiDarkPack packGui = new GuiDarkPack(player.inventory, dog);
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
		return null;
	}


	public void registerRenderThings() {
		
	}

	public void reigsterClientLangugaes() {
		
	}
}
