package net.novaviper.zeroquest.common.talent;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class Hunter extends ITalent {

	@SubscribeEvent
	public void onLootDrop(LivingDropsEvent event) {
		Entity entity = event.source.getEntity();
		if (entity instanceof EntityZertumEntity) {
			EntityZertumEntity dog = (EntityZertumEntity) entity;
			int level = dog.talents.getLevel(this);

			if (dog.getRNG().nextInt(10) < level + (level == 5 ? 1 : 0)) {
				for (EntityItem entityItem : event.drops) {
					event.entityLiving.entityDropItem(entityItem.getEntityItem().copy(), 0.0F);
				}
			}

		}
	}

	@Override
	public String getKey() {
		return "hunter";
	}

}
