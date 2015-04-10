package common.zeroquest.talent;

import net.minecraft.entity.Entity;
import common.zeroquest.ZeroQuest;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;

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
