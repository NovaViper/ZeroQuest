/*
 ** 2012 April 25
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai.ground;

import common.zeroquest.ModItems;
import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.ai.EntityAIRide;
import common.zeroquest.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

/**
 * AI for player-controlled ground movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideGround extends EntityAIRide {
    
    private static final float PLAYER_SPEED = 0.98f;
    private final double speed;

    public EntityAIRideGround(EntityJakanPrime dragon, double speed) {
        super(dragon);
        this.speed = speed;
    }
    
    @Override
    public void startExecuting() {
        dragon.getNavigator().clearPathEntity();
    }
    
    @Override
    public void updateTask() {
        super.updateTask();
        
        float speedX = rider.moveForward / PLAYER_SPEED;
        float speedY = rider.moveStrafing / PLAYER_SPEED;
        
        if (ItemUtils.hasEquipped(rider, ModItems.vitoidFruit)) {
            speedX = 1;
        }
        
        float speedPlayer = Math.max(Math.abs(speedX), Math.abs(speedY));

        Vec3 look = rider.getLookVec();
        float dir = Math.min(speedX, 0) * -1;
        dir += speedY / (speedX * 2 + (speedX < 0 ? -2 : 2));
        if (dir != 0) {
            look.rotateAroundY((float) Math.PI * dir);
        }

        if (speedPlayer > 0) {
            dragon.getMoveHelper().setMoveTo(dragon.posX + look.xCoord, dragon.posY, dragon.posZ + look.zCoord, speed * speedPlayer);
        }
        
        // lift off when pressing the fly-up key
        if (isFlyUp()) {
            dragon.liftOff();
        }
    }
}
