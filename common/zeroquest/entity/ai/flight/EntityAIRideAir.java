/*
 ** 2012 April 25
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai.flight;

import common.zeroquest.ModItems;
import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.ai.EntityAIRide;
import common.zeroquest.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

/**
 * AI for player-controlled air movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideAir extends EntityAIRide {
        
    public EntityAIRideAir(EntityJakanPrime dragon) {
        super(dragon);
    }
    
    @Override
    public void updateTask() {
        super.updateTask();
        
        double dist = 100;
        
        // control with keyboard if carrot on a stick is equipped
        if (ItemUtils.hasEquipped(rider, ModItems.vitoidFruit)) {
            Vec3 wp = rider.getLookVec();
            
            // scale with distance
            wp.xCoord *= dist;
            wp.yCoord *= dist;
            wp.zCoord *= dist;
            
            // convert to absolute position
            wp.xCoord += dragon.posX;
            wp.yCoord += dragon.posY;
            wp.zCoord += dragon.posZ;
            
            dragon.getWaypoint().setVector(wp);
            
            dragon.setMoveSpeedAirHoriz(1);
            dragon.setMoveSpeedAirVert(0);
        } else {
            Vec3 wp = dragon.getLookVec();

            // scale with distance
            wp.xCoord *= dist;
            wp.yCoord *= dist;
            wp.zCoord *= dist;

            // convert to absolute position
            wp.xCoord += dragon.posX;
            wp.yCoord += dragon.posY;
            wp.zCoord += dragon.posZ;

            dragon.getWaypoint().setVector(wp);
            
            double speedAir = 0; 
            
            // change speed with forward
            if (rider.moveForward != 0) {
                speedAir = 1;
                
                // fly slower backwards
                // (I'm surprised this is kinda working at all...)
                if (rider.moveForward < 0) {
                    speedAir *= 0.5;
                }

                speedAir *= rider.moveForward;
            }
            
            dragon.setMoveSpeedAirHoriz(speedAir);

            // control rotation with strafing
            if (rider.moveStrafing != 0) {
                dragon.rotationYaw -= rider.moveStrafing * 6;
            }

            double verticalSpeed = 0;

            // control height with custom keys
            if (isFlyUp()) {
                verticalSpeed = 0.5f;
            } else if (isFlyDown()) {
                verticalSpeed = -0.5f;
            }
            
            dragon.setMoveSpeedAirVert(verticalSpeed);
        }
    }
}
