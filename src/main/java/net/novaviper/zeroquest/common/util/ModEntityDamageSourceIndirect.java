package net.novaviper.zeroquest.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class ModEntityDamageSourceIndirect extends EntityDamageSourceIndirect {

	private boolean fireDamage;
	private float hungerDamage;
	private boolean isUnblockable;

	public ModEntityDamageSourceIndirect(String par1Str, Entity par2Entity, Entity par3Entity) {
		super(par1Str, par2Entity, par3Entity);
	}

	@Override
	public DamageSource setFireDamage() {
		this.fireDamage = true;
		return this;
	}

	@Override
	public DamageSource setDamageBypassesArmor() {
		this.isUnblockable = true;
		this.hungerDamage = 0.0F;
		return this;
	}
}
