package common.zeroquest.talent;

import net.minecraft.entity.player.EntityPlayer;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class LifeGiver extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		EntityPlayer player = (EntityPlayer) dog.getOwner();

		int level = dog.talents.getLevel(this);

		if (player != null && player.getHealth() <= Constants.lowPlayerHP && level != 0 && dog.getZertumHunger() > this.healCost(dog)) {
			player.heal((int) (level * 1.5D));
			dog.setZertumHunger(dog.getZertumHunger() - this.healCost(dog));
		}
	}

	public int healCost(EntityZertumEntity dog) {
		byte byte0 = 95;

		if (dog.talents.getLevel(this) == 5) {
			byte0 = 40;
		}

		return byte0;
	}

	@Override
	public String getKey() {
		return "lifegiver";
	}

}