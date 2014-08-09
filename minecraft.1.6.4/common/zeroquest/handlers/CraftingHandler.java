package common.zeroquest.handlers;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) 
	{
		if (item.itemID == ModItems.nileEssence.itemID)
		{
			player.addStat(ModAchievements.ZQuestStart, 1);
		}
		if (item.itemID == ModItems.nileDust.itemID)
		{
			player.addStat(ModAchievements.ZQuestStart, 1);
		}
		if (item.itemID == ModItems.nileSword.itemID)
		{
			player.addStat(ModAchievements.buildNileSword, 1);
		}
		if (item.itemID == ModBlocks.nileWorktable.blockID)
		{
			player.addStat(ModAchievements.buildNWorkBench, 1);
		}
		if (item.itemID == ModItems.nileBone.itemID)
		{
			player.addStat(ModAchievements.buildBone, 1);
		}
		
		if(Constants.DEF_DARKLOAD == true){
			if (item.itemID == ModItems.darkEssence.itemID)
			{
				player.addStat(ModAchievements.ZQuestStart, 1);
			}
			if (item.itemID == ModItems.darkDust.itemID)
			{
				player.addStat(ModAchievements.ZQuestStart, 1);
			}
			if (item.itemID == ModItems.darkSword.itemID)
			{
				player.addStat(ModAchievements.buildNileSword, 1);
			}
			if (item.itemID == ModItems.darkNileBone.itemID)
			{
				player.addStat(ModAchievements.buildBone, 1);
			}
		}
	}


	@Override
	public void onSmelting(EntityPlayer player, ItemStack item)
	{

	}
}
