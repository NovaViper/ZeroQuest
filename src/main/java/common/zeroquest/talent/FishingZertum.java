package common.zeroquest.talent;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;


/**
 * @author ProPercivalalb
 */
public class FishingZertum extends ITalent {
	
	@Override
	public boolean canBreatheUnderwater(EntityZertumEntity dog) { 
		return dog.talents.getLevel(this) == 5;
	}
	
	@Override
	public String getKey() {
		return "fishing";
	}

}
