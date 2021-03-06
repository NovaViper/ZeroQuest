package common.zeroquest.handlers;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.lib.Constants;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPickupNotifier;

public class PickupHandler implements IPickupNotifier {

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
		{
			if(item.getEntityItem().itemID == ModItems.nileGrain.itemID)
			{
			player.addStat(ModAchievements.ZQuestStart, 1);
			}
			if(item.getEntityItem().itemID == ModItems.nileDust.itemID)
			{
			player.addStat(ModAchievements.ZQuestStart, 1);
			}
			if(item.getEntityItem().itemID == ModItems.nileEssence.itemID)
			{
			player.addStat(ModAchievements.ZQuestStart, 1);
			}
			if(Constants.DEF_DARKLOAD == true){
				if(item.getEntityItem().itemID == ModItems.darkGrain.itemID)
				{
					player.addStat(ModAchievements.ZQuestStart, 1);
				}
				if(item.getEntityItem().itemID == ModItems.darkDust.itemID)
				{
					player.addStat(ModAchievements.ZQuestStart, 1);
				}
				if(item.getEntityItem().itemID == ModItems.darkEssence.itemID)
				{
					player.addStat(ModAchievements.ZQuestStart, 1);
				}
			}
		}
	}
