package common.zeroquest.talent;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class SilverfishSlayer extends ITalent {

	@Override
	public void onLivingUpdate(EntityZertumEntity dog) {
		int level = dog.talents.getLevel(this);
		
		if(level >= 0) {
            byte damage = 1;

            if (level == 5)
                damage = 2;

            List list = dog.worldObj.getEntitiesWithinAABB(EntitySilverfish.class, new AxisAlignedBB(dog.posX, dog.posY, dog.posZ, dog.posX + 1.0D, dog.posY + 1.0D, dog.posZ + 1.0D).expand(level * 3, 4D, level * 3));
            Iterator iterator = list.iterator();
            
            while(iterator.hasNext()) {
            	EntitySilverfish entitySilverfish = (EntitySilverfish)iterator.next();
            	if(dog.getRNG().nextInt(20) == 0)
            		entitySilverfish.attackEntityFrom(DamageSource.generic, damage);
            }
        }
	}
	
	@Override
	public String getKey() {
		return "silverslayer";
	}

}
