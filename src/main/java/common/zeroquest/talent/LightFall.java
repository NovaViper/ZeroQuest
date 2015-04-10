package common.zeroquest.talent;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;

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
