package common.zeroquest.talent;

import net.minecraft.entity.player.EntityPlayer;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class LifeGiver extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		EntityPlayer player = (EntityPlayer)dog.getOwner();
		
		int level = dog.talents.getLevel(this);
		
		if (player != null && player.getHealth() <= 6 && level != 0 && dog.getDogHunger() > this.healCost(dog)) {
            player.heal((int)(level * 1.5D));
            dog.setDogHunger(dog.getDogHunger() - this.healCost(dog));
        }
	}
	
	public int healCost(EntityZertumEntity dog) {
        byte byte0 = 100;

        if (dog.talents.getLevel(this) == 5)
            byte0 = 80;

        return byte0;
    }
		
	
	@Override
	public String getKey() {
		return "lifegiver";
	}

}