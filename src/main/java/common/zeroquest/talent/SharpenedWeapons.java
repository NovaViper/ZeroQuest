package common.zeroquest.talent;

import net.minecraft.entity.player.EntityPlayer;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;

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
				damage += 3.0D;
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
