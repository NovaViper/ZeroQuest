package net.novaviper.zeroquest.common.talent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.util.ItemUtils;

/**
 * @author ProPercivalalb
 */
public class Mount extends ITalent {

	@Override
	public boolean interactWithPlayer(EntityZertumEntity dog, EntityPlayer player) {
		if (dog.talents.getLevel(this) > 0) {
			if (!dog.isChild() && ItemUtils.consumeEquipped(player, Items.saddle) && !dog.isSaddled())
			{
				dog.setSaddled(true);
				dog.playSound("mob.horse.leather", 0.5F, 1.0F);
			}

			if (player.getHeldItem() == null) {
				if (dog.isSaddled() && player.ridingEntity == null && !player.onGround && dog.isServer()) {
					dog.getSitAI().setSitting(false);
					dog.setSitting(false);
					player.mountEntity(dog);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int onHungerTick(EntityZertumEntity dog, int totalInTick) {
		if (dog.riddenByEntity instanceof EntityPlayer) {
			if (dog.talents.getLevel(this) == 5) {
				totalInTick += 1;
			}
			else {
				totalInTick += 3;
			}
		}
		return totalInTick;
	}

	@Override
	public String getKey() {
		return "mount";
	}
}