package net.novaviper.zeroquest.common.talent;

import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;


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
