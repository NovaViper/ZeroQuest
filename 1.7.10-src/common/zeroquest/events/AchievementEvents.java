package common.zeroquest.events;

import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class AchievementEvents{

	@SubscribeEvent
	public void CraftingEvent(PlayerEvent.ItemCraftedEvent event){
		if(event.crafting.getItem() == ModItems.nileEssence){
			event.player.addStat(ModAchievements.NileStart, 1);		
		}
		if(event.crafting.getItem() == ModItems.nileDust){
			event.player.addStat(ModAchievements.NileStart, 1);		
		}
		if(event.crafting.getItem() == ModItems.nileSword){
			event.player.addStat(ModAchievements.buildNileSword, 1);		
		}
		if(Block.getBlockFromItem(event.crafting.getItem()).equals(ModBlocks.nileWorktable)){
			event.player.addStat(ModAchievements.buildNWorkBench, 1);		
		}	
		if(event.crafting.getItem() == ModItems.nileBone){
			event.player.addStat(ModAchievements.buildBone, 1);		
		}
	
		if(Constants.DEF_DARKLOAD == true){
			if(event.crafting.getItem() == ModItems.darkEssence){
				event.player.addStat(ModAchievements.DarkStart, 1);		
			}
			if(event.crafting.getItem() == ModItems.darkDust){
				event.player.addStat(ModAchievements.DarkStart, 1);		
			}
			/*if(event.crafting.getItem() == ModItems.darkSword){
			event.player.addStat(ModAchievements.buildNileSword, 1);		
		}
		if(event.crafting.getItem() == ModItems.darkNileBone){
			event.player.addStat(ModAchievements.buildBone, 1);		
		}*/
	}
}

	@SubscribeEvent
	public void PickupEvent(PlayerEvent.ItemPickupEvent event){
		if(event.pickedUp.getEntityItem().getItem() == ModItems.nileGrain){
			event.player.addStat(ModAchievements.NileStart, 1);		
		}
		if(Constants.DEF_DARKLOAD == true){
			if(event.pickedUp.getEntityItem().getItem() == ModItems.darkGrain){
				event.player.addStat(ModAchievements.DarkStart, 1);		
			}
		}
	}
	
	@SubscribeEvent
	public void TravelEvent(PlayerEvent.PlayerChangedDimensionEvent event){
		if(event.toDim == ZeroQuest.NillaxID){
			event.player.addStat(ModAchievements.TraveltoNillax, 1);		
		}
		if(Constants.DEF_DARKLOAD == true){
			if(event.toDim == ZeroQuest.DarkaxID){
				event.player.addStat(ModAchievements.TraveltoDarkax, 1);		
			}
		}
	}
}