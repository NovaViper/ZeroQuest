package common.zeroquest.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityRowarn extends EntityAnimal {
	private EntityAIWander wander;
	private static final String __OBFID = "CL_00002213";

	public EntityRowarn(World worldIn) {
		super(worldIn);
		this.setSize(1F, 1F);
		this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0D, 80));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityRowarn.class, 12.0F, 0.01F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.wander.setMutexBits(3);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
}