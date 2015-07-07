package net.novaviper.zeroquest.common.talent;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.creature.EntityMetalZertum;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Sound;

/**
 * @author ProPercivalalb
 */
public class CreeperSpotter extends ITalent {

	@Override
	public void onClassCreation(EntityZertumEntity dog) {
		dog.objects.put("canseecreeper", false);
	}

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		dog.objects.put("canseecreeper", false);
		int level = dog.talents.getLevel(this);

		if (dog.getAttackTarget() == null && dog.isTamed() && level > 0) {
			List list = dog.worldObj.getEntitiesWithinAABB(EntityCreeper.class, dog.getEntityBoundingBox().expand(level * 6, level * 6, level * 6));

			if (!list.isEmpty() && !dog.isSitting() && dog.getHealth() > Constants.lowHP && !dog.isChild()) {
				dog.objects.put("canseecreeper", true);
			}
			dog.tasks.addTask(10, dog.aiGlareAtCreeper);
			dog.tasks.removeTask(dog.aiStareAtPlayer);
		}
		else if ((Boolean) dog.objects.get("canseecreeper") == false) {
			dog.tasks.addTask(10, dog.aiStareAtPlayer);
			dog.tasks.removeTask(dog.aiGlareAtCreeper);
		}

		if (dog.getAttackTarget() instanceof EntityCreeper) {
			EntityCreeper creeper = (EntityCreeper) dog.getAttackTarget();
			creeper.setCreeperState(-1);
		}
	}

	@Override
	public String getLivingSound(EntityZertumEntity dog) {
		if (!(dog instanceof EntityMetalZertum) && (Boolean) dog.objects.get("canseecreeper")) {
			return "mob.wolf.growl";
		}
		else if ((dog instanceof EntityMetalZertum) && (Boolean) dog.objects.get("canseecreeper")) {
			return Sound.MetalZertumGrowl;
		}
		return "";
	}

	@Override
	public int getTalkInterval(EntityZertumEntity dog, int ticks) {
		if ((Boolean) dog.objects.get("canseecreeper") == true) {
			return ticks = 40;
		}
		return ticks;
	}

	@Override
	public boolean canAttackClass(EntityZertumEntity dog, Class entityClass) {
		return EntityCreeper.class == entityClass && dog.talents.getLevel(this) == 5;
	}

	@Override
	public boolean canAttackEntity(EntityZertumEntity dog, Entity entity) {
		return entity instanceof EntityCreeper && dog.talents.getLevel(this) == 5;
	}

	@Override
	public String getKey() {
		return "creeperspotter";
	}
}