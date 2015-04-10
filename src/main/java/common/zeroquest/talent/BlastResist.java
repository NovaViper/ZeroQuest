package common.zeroquest.talent;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class BlastResist extends ITalent {

	@Override
	public String getKey() {
		return "blastresist";
	}
	
	@Override
	public int getHighestLevel(EntityZertumEntity dog) {
		return 5;
	}

}
