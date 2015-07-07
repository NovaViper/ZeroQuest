package net.novaviper.zeroquest.common.talent;

import net.minecraft.entity.Entity;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class CriticalClaw extends ITalent {

	@Override
	public int attackEntityAsMob(EntityZertumEntity dog, Entity entity, int damage) {
		int level = dog.talents.getLevel(this);
		
		int critChance = level == 5 ? 1 : 0;
        critChance += level;
        
        if (dog.getRNG().nextInt(6) < critChance) {
        	damage += (damage + 1) / 2;
        	ZeroQuest.proxy.spawnCrit(dog.worldObj, entity);
        }
        return damage;
	}
	
	@Override
	public String getKey() {
		return "criticalclaw";
	}

}
