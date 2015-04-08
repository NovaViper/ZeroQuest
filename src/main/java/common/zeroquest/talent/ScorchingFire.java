package common.zeroquest.talent;

import net.minecraft.util.DamageSource;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertumEntity;

public class ScorchingFire extends ITalent {

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
	public String getKey() {
		return "scorchingfire";
	}

}
