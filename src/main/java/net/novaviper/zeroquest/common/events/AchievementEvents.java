package net.novaviper.zeroquest.common.events;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.novaviper.zeroquest.ModAchievements;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.IDs;

public class AchievementEvents {
	// Get Achievements in ModAchievements\\

	@SubscribeEvent
	public void CraftingEvent(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting.getItem() == ModItems.nileEssence) {
			event.player.addStat(ModAchievements.nileStart, 1);
		}
		if (event.crafting.getItem() == ModItems.nileDust) {
			event.player.addStat(ModAchievements.nileStart, 1);
		}
		if (event.crafting.getItem() == ModItems.nileSword) {
			event.player.addStat(ModAchievements.buildNileSword, 1);
		}
		if (event.crafting.getItem() == Item.getItemFromBlock(ModBlocks.nileWorktable)) {
			event.player.addStat(ModAchievements.buildNWorkBench, 1);
		}
		if (event.crafting.getItem() == ModItems.nileBone) {
			event.player.addStat(ModAchievements.buildNileBone, 1);
		}

		if (Constants.DEF_DARKLOAD == true) {
			if (event.crafting.getItem() == ModItems.darkEssence) {
				event.player.addStat(ModAchievements.darkStart, 1);
			}
			if (event.crafting.getItem() == ModItems.darkDust) {
				event.player.addStat(ModAchievements.darkStart, 1);
			}

			if (event.crafting.getItem() == ModItems.darkSword) {
				event.player.addStat(ModAchievements.buildDarkSword, 1);
			}
			if (event.crafting.getItem() == ModItems.darkBone) {
				event.player.addStat(ModAchievements.buildDarkBone, 1);
			}
		}
	}

	@SubscribeEvent
	public void PickupEvent(PlayerEvent.ItemPickupEvent event) {
		if (event.pickedUp.getEntityItem().getItem() == ModItems.nileGrain) {
			event.player.addStat(ModAchievements.nileStart, 1);
		}
		if (Constants.DEF_DARKLOAD == true) {
			if (event.pickedUp.getEntityItem().getItem() == ModItems.darkGrain) {
				event.player.addStat(ModAchievements.darkStart, 1);
			}
		}
	}

	@SubscribeEvent
	public void TravelEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.toDim == IDs.Nillax) {
			event.player.addStat(ModAchievements.travelToNillax, 1);
		}
		if (Constants.DEF_DARKLOAD == true) {
			if (event.toDim == IDs.Darkax) {
				event.player.addStat(ModAchievements.travelToDarkax, 1);
			}
		}
	}
}