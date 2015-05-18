package common.zeroquest.talent;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import common.zeroquest.ZeroQuest;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityMetalZertum;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.DataValues;
import common.zeroquest.lib.Sound;

/**
 * @author ProPercivalalb
 */
public class DeatheningRoar extends ITalent {

	int cooldown2;

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		int level = dog.talents.getLevel(this);
		int masterOrder = dog.masterOrder();

		if (masterOrder == 4 && dog.hasEvolved() && dog.getHealth() > DataValues.lowHP && !dog.isChild()) {
			if (level >= 0) {
				byte damage = (byte) level;

				if (level == 5) {
					damage = 10;
				}

				if (dog.isServer()) {
					if (this.cooldown2 > 0) {
						this.cooldown2--;
						// System.out.println(this.cooldown2);
					}
				}

				List list = dog.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(dog.posX, dog.posY, dog.posZ, dog.posX + 1.0D, dog.posY + 1.0D, dog.posZ + 1.0D).expand(level * 3, 4D, level * 3));
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					EntityMob entityMob = (EntityMob) iterator.next();
					if (dog.getRNG().nextInt(20) == 0) {
						if (cooldown2 <= 0) {
							this.roar(dog, entityMob, dog instanceof EntityMetalZertum
									? Sound.MetalZertumRoar : Sound.ZertumRoar);
							int knockback = level;
							entityMob.attackEntityFrom(DamageSource.generic, damage);
							entityMob.addVelocity(-MathHelper.sin(entityMob.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(entityMob.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
							cooldown2 = level == 5 ? 20 : 50;
						}
					}
				}
			}
		}
	}

	public void roar(EntityZertumEntity dog, Entity entity, String sound) {
		dog.openMouth();
		dog.playSound(sound, dog.getSoundVolume(), 1F);
		ZeroQuest.proxy.spawnRoar(dog.worldObj, entity);
	}

	@Override
	public String getKey() {
		return "deatheningroar";
	}
}