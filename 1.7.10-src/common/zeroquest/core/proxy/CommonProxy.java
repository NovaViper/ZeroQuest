package common.zeroquest.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import common.zeroquest.ModItems;
import common.zeroquest.client.gui.GuiPack;
import common.zeroquest.client.gui.NileTableGui;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.inventory.ContainerNileTable;
import common.zeroquest.inventory.ContainerPack;
import common.zeroquest.tileentity.TileEntityNileTable;

import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{
	
	public static final int NileTable = 0;
	public static final int PetPack = 1;


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
		 else if(ID == PetPack) {
			Entity target = player.worldObj.getEntityByID(x);
            if(!(target instanceof EntityCustomTameable)) {
            	return null;
            }
            EntityCustomTameable entity = (EntityCustomTameable)target;
			ContainerPack packContainer = new ContainerPack(player.inventory, entity);
			return packContainer;
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
		else if(ID == PetPack) {
					Entity target = player.worldObj.getEntityByID(x);
		           if(!(target instanceof EntityCustomTameable)) {
		           	return null;
		           }
		           EntityCustomTameable dog = (EntityCustomTameable)target;
					GuiPack packGui = new GuiPack(player.inventory, dog);
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