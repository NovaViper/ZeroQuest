package net.novaviper.zeroquest.common.talent;

import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.creature.EntityIceZertum;

public class FrigidFrost extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		if (dog instanceof EntityIceZertum) {
			int level = dog.talents.getLevel(this);
			int x = MathHelper.floor_double(dog.posX);
			int y = MathHelper.floor_double(dog.posY);
			int z = MathHelper.floor_double(dog.posZ);

			if (level != 5) {
				if (dog.isServer()) {
					if (dog.worldObj.getBiomeGenForCoords(new BlockPos(x, 0, z)).getFloatTemperature(new BlockPos(x, y, z)) > 1.0F) {
						dog.attackEntityFrom(DamageSource.onFire, 1.0F - (level / 8));
					}
				}
			}
		}
	}

	@Override
	public boolean attackEntityFrom(EntityZertumEntity dog, DamageSource damageSource, float damage) {
		if (dog instanceof EntityIceZertum) {
			if (dog.talents.getLevel(this) == 5) {
				if (damageSource.isFireDamage()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String getKey() {
		return "frigidfrost";
	}
}