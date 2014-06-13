/*
 ** 2013 November 03
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai.flight;

import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.ai.EntityAICatchOwner;
import common.zeroquest.entity.ai.JakanFlightWaypoint;
import common.zeroquest.util.MathX;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAICatchOwnerAir extends EntityAICatchOwner {

    public EntityAICatchOwnerAir(EntityJakanPrime dragon) {
        super(dragon);
    }

    @Override
    public void updateTask() {
        JakanFlightWaypoint wp = dragon.getWaypoint();
        wp.setEntity(owner);
        
        double dist = wp.getDistance();
        double yOfs = MathX.clamp(dist, 0, 64);
        
        wp.posY -= (int) yOfs;
        
        if (wp.isNear()) {
            owner.mountEntity(dragon);
        }
        
        dragon.setMoveSpeedAirHoriz(1);
    }
}
