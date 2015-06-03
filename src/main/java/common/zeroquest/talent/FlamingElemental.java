package common.zeroquest.talent;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityIceZertum;
import common.zeroquest.entity.zertum.EntityRedZertum;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class FlamingElemental extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		if (dog instanceof EntityRedZertum) {
			int level = dog.talents.getLevel(this);

			if (level != 5) {
				if (dog.isServer()) {
					if (dog.isWet()) {
						dog.attackEntityFrom(DamageSource.drown, 1.0F - (level / 8));
					}
				}
			}
		}
	}

	@Override
	public int attackEntityAsMob(EntityZertumEntity dog, Entity entity, int damage) {
		int level = dog.talents.getLevel(this);
		if (dog instanceof EntityRedZertum) {
			if (level != 0) {
				if (!(entity instanceof EntityIceZertum)) {
					entity.setFire(level);
				}
				else if ((entity instanceof EntityIceZertum)) {
					entity.setFire(level + 2); // Elemental Advantage
				}
			}
		}
		return damage;
	}

	@Override
	public boolean attackEntityFrom(EntityZertumEntity dog, DamageSource damageSource, float damage) {
		if (dog instanceof EntityRedZertum) {
			if (dog.talents.getLevel(this) == 5) {
				if (damageSource.isFireDamage()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean setFire(EntityZertumEntity dog, int amount) {
		return !(dog instanceof EntityRedZertum);
	}

	@Override
	public String getKey() {
		return "flamingelemental";
	}

}
