package common.zeroquest.entity.zertum.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class TalentHelper {

	public static void onClassCreation(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			talent.onClassCreation(dog);
		}
	}

	public static void writeToNBT(EntityZertumEntity dog, NBTTagCompound tagCompound) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			talent.writeToNBT(dog, tagCompound);
		}
	}

	public static void readFromNBT(EntityZertumEntity dog, NBTTagCompound tagCompound) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			talent.readFromNBT(dog, tagCompound);
		}
	}

	public static boolean interactWithPlayer(EntityZertumEntity dog, EntityPlayer player) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (talent.interactWithPlayer(dog, player)) {
				return true;
			}
		}
		return false;
	}

	public static void onUpdate(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			talent.onUpdate(dog);
		}
	}

	public static void onLivingUpdate(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			talent.onLivingUpdate(dog);
		}
	}

	public static int getTalkInterval(EntityZertumEntity dog) {
		int slientTime = 0;
		for (ITalent talent : TalentRegistry.getTalents()) {
			slientTime = talent.getTalkInterval(dog, slientTime);
		}
		return slientTime;
	}

	public static int onHungerTick(EntityZertumEntity dog, int totalInTick) {
		int total = totalInTick;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total = talent.onHungerTick(dog, total);
		}
		return total;
	}

	public static int onRegenerationTick(EntityZertumEntity dog, int totalInTick) {
		int total = totalInTick;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total = talent.onRegenerationTick(dog, total);
		}
		return total;
	}

	public static int attackEntityAsMob(EntityZertumEntity dog, Entity entity, int damage) {
		int total = damage;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total = talent.attackEntityAsMob(dog, entity, total);
		}
		return total;
	}

	public static int changeFoodValue(EntityZertumEntity dog, ItemStack stack, int foodValue) {
		int total = foodValue;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total = talent.changeFoodValue(dog, stack, total);
		}
		return total;
	}

	public static int getUsedPoints(EntityZertumEntity dog) {
		int total = 0;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total += talent.getCumulativeCost(dog, dog.talents.getLevel(talent));
		}
		return total;
	}

	public static boolean isPostionApplicable(EntityZertumEntity dog, PotionEffect potionEffect) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (!talent.isPostionApplicable(dog, potionEffect)) {
				return false;
			}
		}
		return true;
	}

	public static double addToMoveSpeed(EntityZertumEntity dog) {
		double total = 0;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total += talent.addToMoveSpeed(dog);
			dog.updateEntityAttributes();
		}
		return total;
	}

	public static double addToAttackDamage(EntityZertumEntity dog) {
		double total = 0;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total += talent.addToAttackDamage(dog);
			dog.updateEntityAttributes();
		}
		return total;
	}

	public static boolean canBreatheUnderwater(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (talent.canBreatheUnderwater(dog)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isImmuneToFalls(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (talent.isImmuneToFalls(dog)) {
				return true;
			}
		}
		return false;
	}

	public static int fallProtection(EntityZertumEntity dog) {
		int total = 0;
		for (ITalent talent : TalentRegistry.getTalents()) {
			total += talent.fallProtection(dog);
		}
		return total;
	}

	public static boolean attackEntityFrom(EntityZertumEntity dog, DamageSource damageSource, float damage) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (!talent.attackEntityFrom(dog, damageSource, damage)) {
				return false;
			}
		}
		return true;
	}

	public static boolean shouldDamageMob(EntityZertumEntity dog, Entity entity) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (!talent.shouldDamageMob(dog, entity)) {
				return false;
			}
		}
		return true;
	}

	public static String getLivingSound(EntityZertumEntity dog) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			String sound = talent.getLivingSound(dog);
			if (!"".equals(sound)) {
				return sound;
			}
		}
		return "";
	}

	public static boolean canAttackClass(EntityZertumEntity dog, Class entityClass) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (talent.canAttackClass(dog, entityClass)) {
				return true;
			}
		}
		return false;
	}

	public static boolean canAttackEntity(EntityZertumEntity dog, Entity entity) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (talent.canAttackEntity(dog, entity)) {
				return true;
			}
		}
		return false;
	}

	public static boolean setFire(EntityZertumEntity dog, int amount) {
		for (ITalent talent : TalentRegistry.getTalents()) {
			if (!talent.setFire(dog, amount)) {
				return false;
			}
		}
		return true;
	}
}
