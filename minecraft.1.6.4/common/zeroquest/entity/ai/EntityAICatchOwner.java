/*
 ** 2013 November 03
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityJakanPrime;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAICatchOwner extends EntityAIBase {
    
    protected final EntityJakanPrime dragon;
    protected EntityPlayer owner;
    
    public EntityAICatchOwner(EntityJakanPrime dragon) {
        this.dragon = dragon;
    }

    @Override
    public boolean shouldExecute() {
        owner = (EntityPlayer) dragon.getOwner();
        
        // don't catch if ownerless 
        if (owner == null) {
            return false;
        }

        // no point in catching players in creative mode
        if (owner.capabilities.isCreativeMode) {
            return false;
        }
        
        // don't catch if already being ridden
        if (dragon.riddenByEntity != null) {
            return false;
        }
        
        return owner.fallDistance > 4;
    }
}
