/*
 ** 2012 March 18
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.handlers.RemoteKeyPacketHandler;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Abstract "AI" for player-controlled movements.
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class EntityAIRide extends EntityAIBase {

    protected final EntityJakanPrime dragon;
    protected EntityPlayer rider;
    private RemoteKeyPacketHandler remoteKey = RemoteKeyPacketHandler.getInstance();

    public EntityAIRide(EntityJakanPrime dragon) {
        this.dragon = dragon;
        setMutexBits(0xffffffff);
    }
    
    protected boolean isFlyUp() {
        return remoteKey.isKeyPressed(rider.username, "key.dragon.flyUp");
    }
    
    protected boolean isFlyDown() {
        return remoteKey.isKeyPressed(rider.username, "key.dragon.flyDown");
    }
    
    @Override
    public boolean shouldExecute() {   
        rider = dragon.getRidingPlayer();
        return rider != null;
    }
}
