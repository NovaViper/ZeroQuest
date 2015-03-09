package common.zeroquest.talent;

import net.minecraft.entity.player.EntityPlayer;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class Mount extends ITalent {

	@Override
	public boolean interactWithPlayer(EntityZertumEntity dog, EntityPlayer player) { 
		if(player.getHeldItem() == null) {
        	if(dog.talents.getLevel(this) > 0 && player.ridingEntity == null && !player.onGround) {
        		dog.getSitAI().setSitting(false);
        		dog.setSitting(false);
        		player.mountEntity(dog);
        		return true;
        	}
        }
		
		return false; 
	}
	
	@Override
	public int onHungerTick(EntityZertumEntity dog, int totalInTick) { 
		if(dog.riddenByEntity instanceof EntityPlayer)
			if(dog.talents.getLevel(this) >= 5)
				totalInTick += 1;
			else
				totalInTick += 3;
		return totalInTick;
	}
	
	@Override
	public int getHighestLevel(EntityZertumEntity dog) { return 3; }
	
	@Override
	public String getKey() {
		return "mount";
	}
}
