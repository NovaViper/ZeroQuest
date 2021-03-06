package net.novaviper.zeroquest.common.talent;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;

/**
 * @author ProPercivalalb
 */
public class RoundUp extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		int level = dog.talents.getLevel(this);
		int masterOrder = dog.masterOrder();
		
		if(masterOrder == 3 && dog.getAttackTarget() != null) {
        	double d0 = dog.getDistanceSqToEntity(dog.getAttackTarget());
            if(d0 <= 4.0D) {
            	dog.getAttackTarget().mountEntity(dog);
            	dog.setAttackTarget(null);
            }
        }
		
		if(dog.isTamed() && masterOrder != 3 && dog.riddenByEntity instanceof EntityAnimal && level > 0) {
			dog.riddenByEntity.ridingEntity = null;
			dog.riddenByEntity = null;
		}
	}
	
	@Override
	public int onHungerTick(EntityZertumEntity dog, int totalInTick) { 
		if(dog.riddenByEntity != null && !(dog.riddenByEntity instanceof EntityPlayer))
			totalInTick += 5 - dog.talents.getLevel(this);
		return totalInTick;
	}
	
	@Override
	public boolean shouldDamageMob(EntityZertumEntity dog, Entity entity) { 
		if(dog.mode.isMode(EnumMode.DOCILE) && dog.masterOrder() == 3)
			return false;
		return true;
	}
	
	@Override
	public String getKey() {
		return "roundup";
	}
}
