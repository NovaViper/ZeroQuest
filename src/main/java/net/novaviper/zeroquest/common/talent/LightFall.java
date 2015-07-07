package net.novaviper.zeroquest.common.talent;

import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class LightFall extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		if(dog.talents.getLevel(this) == 5)
			if(dog.motionY < 0.0F)
				dog.motionY = -0.12F;
	}
	
	@Override
	public boolean isImmuneToFalls(EntityZertumEntity dog) { 
		return dog.talents.getLevel(this) == 5; 
	}
	
	@Override
	public int fallProtection(EntityZertumEntity dog) { 
		return dog.talents.getLevel(this) * 3;
	}
	
	@Override
	public String getKey() {
		return "lightfall";
	}

}
