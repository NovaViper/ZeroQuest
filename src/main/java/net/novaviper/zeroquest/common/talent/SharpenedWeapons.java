package net.novaviper.zeroquest.common.talent;

import net.minecraft.entity.player.EntityPlayer;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class SharpenedWeapons extends ITalent {

	@Override
	public double addToAttackDamage(EntityZertumEntity dog) {
		double damage = 0.0D;
		int level = dog.talents.getLevel(this);

		if ((!(dog.getAttackTarget() instanceof EntityZertumEntity) && !(dog.getAttackTarget() instanceof EntityPlayer)) || dog.riddenByEntity instanceof EntityPlayer) {
			if (level == 5) {
				damage += 2.0D;
			}
		}
		damage += level;
		return damage;
	}

	@Override
	public String getKey() {
		return "sharpen";
	}
}
