package common.zeroquest.talent;

import net.minecraft.entity.player.EntityPlayer;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class Dasher extends ITalent {

	@Override
	public double addToMoveSpeed(EntityZertumEntity dog) {
		double speed = 0.0D;
		int level = dog.talents.getLevel(this);

		if((!(dog.getAttackTarget() instanceof EntityZertumEntity) && !(dog.getAttackTarget() instanceof EntityPlayer)) || dog.riddenByEntity instanceof EntityPlayer)
			if(level == 5)
				speed += 0.04D;
		
		speed += 0.03D * level;
		
		return speed;
	}
	
	@Override
	public String getKey() {
		return "dasher";
	}

}
