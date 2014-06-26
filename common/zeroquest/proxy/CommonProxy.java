package common.zeroquest.proxy;

import common.zeroquest.ModItems;
import common.zeroquest.SoundManagerZQuest;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.model.ModelZertum;
import common.zeroquest.entity.render.RenderZertum;
import common.zeroquest.gui.GuiDarkPack;
import common.zeroquest.gui.GuiDestroPack;
import common.zeroquest.gui.GuiPack;
import common.zeroquest.gui.GuiRedPack;
import common.zeroquest.gui.NileTableGui;
import common.zeroquest.handlers.RemoteKeyPacketHandler;
import common.zeroquest.inventory.ContainerDarkPack;
import common.zeroquest.inventory.ContainerDestroPack;
import common.zeroquest.inventory.ContainerNileTable;
import common.zeroquest.inventory.ContainerPack;
import common.zeroquest.inventory.ContainerRedPack;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler{
	

	public static final int NileTable = 0;
	public static final int ZertumPack = 1;
	public static final int RedZertumPack = 2;
	public static final int DarkZertumPack = 3;
	public static final int DestroZertumPack = 4;
	public static final int JakanPack = 5;


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

	public void registerChannels(){
        NetworkRegistry.instance().registerChannel(RemoteKeyPacketHandler.getInstance(), ZeroQuest.channel, Side.SERVER);
	}
	
    public void registerChestItems() {
        ChestGenHooks chestGenHooksDungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
        chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 70));
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

	public void reigsterClientLangugaes() {
		
	}
	
	public void registerSound(){
		
	}
	
    public void registerAdvanced() {
    	
    }
}