package common.zeroquest.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fml.common.network.IGuiHandler;

import common.zeroquest.ModItems;
import common.zeroquest.client.gui.GuiDogInfo;
import common.zeroquest.client.gui.GuiFoodBowl;
import common.zeroquest.client.gui.GuiNileWorkbench;
import common.zeroquest.client.gui.GuiPack;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.entity.tileentity.TileEntityFoodBowl;
import common.zeroquest.entity.tileentity.TileEntityNileWorkbench;
import common.zeroquest.inventory.ContainerFoodBowl;
import common.zeroquest.inventory.ContainerNileWorkbench;
import common.zeroquest.inventory.ContainerPack;


public class CommonProxy implements IGuiHandler{
	
	public static final int NileTable = 0;
	public static final int PetPack = 1;
	public static final int PetInfo = 2;
	public static final int FoodBowl = 3;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		 if(ID == NileTable) {
			TileEntity target = world.getTileEntity(new BlockPos(x, y, z));
			if(!(target instanceof TileEntityNileWorkbench)) 
				return null;

			TileEntityNileWorkbench tileNileTable = (TileEntityNileWorkbench)target;
			ContainerNileWorkbench tableContainer = new ContainerNileWorkbench(tileNileTable, player.inventory, world, new BlockPos(x, y, z));
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
		else if(ID == FoodBowl) {
			TileEntity target = world.getTileEntity(new BlockPos(x, y, z));
            if(!(target instanceof TileEntityFoodBowl)) {
            	return null;
            }
            TileEntityFoodBowl foodbowl = (TileEntityFoodBowl)target;
            ContainerFoodBowl bowlContainer = new ContainerFoodBowl(player.inventory, foodbowl);
			return bowlContainer;
		}
		return null;
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
		if(ID == NileTable) {
			TileEntity target = world.getTileEntity(new BlockPos(x, y, z));
			if(!(target instanceof TileEntityNileWorkbench)) 
				return null;

			TileEntityNileWorkbench tileNileTable = (TileEntityNileWorkbench)target;
			GuiNileWorkbench tableGui = new GuiNileWorkbench(player.inventory, tileNileTable, world, new BlockPos(x, y, z));
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
		else if(ID == PetInfo) {
			Entity target = player.worldObj.getEntityByID(x);
           if(!(target instanceof EntityZertumEntity)) {
           	return null;
           }
           EntityZertumEntity dog = (EntityZertumEntity)target;
           GuiDogInfo petInfoGui = new GuiDogInfo(dog, player);
			return petInfoGui;
}
		else if(ID == FoodBowl) {
			TileEntity target = world.getTileEntity(new BlockPos(x, y, z));
			if(!(target instanceof TileEntityFoodBowl)) 
				return null;

			TileEntityFoodBowl foodBowl = (TileEntityFoodBowl)target;
			GuiFoodBowl foodBowlGui = new GuiFoodBowl(player.inventory, foodBowl);
			return foodBowlGui;
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
        
        ChestGenHooks chestGenHooksBonusChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
        chestGenHooksBonusChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 10));
        chestGenHooksBonusChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileGrain), 1, 2, 10));
    }
	
	public void registerRenderThings() {}

	public EntityPlayer getClientPlayer() {return null;}
	
	public void registerMoreThings() {}
	
	public void spawnCrit(World world, Entity entity) {}
}