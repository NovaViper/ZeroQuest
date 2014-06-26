/*
 ** 2012 March 20
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.util.math.MathX;
import net.minecraft.entity.EntityBodyHelper;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class JakanBodyHelper extends EntityBodyHelper {

    private EntityJakanPrime dragon;
    private int turnTicks;
    private int turnTicksLimit = 20;
    private float prevRotationYawHead;

    public JakanBodyHelper(EntityJakanPrime dragon) {
        super(dragon);
        this.dragon = dragon;
    }

    @Override
    public void func_75664_a() {
        double deltaX = dragon.posX - dragon.prevPosX;
        double deltaY = dragon.posZ - dragon.prevPosZ;
        double dist = deltaX * deltaX + deltaY * deltaY;
        
        float yawSpeed = 90;

        // rotate instantly if flying or moving
        if (dragon.isFlying() || dist > 0.0001) {
            dragon.renderYawOffset = dragon.rotationYaw;
            dragon.rotationYawHead = MathX.updateRotation(dragon.renderYawOffset, dragon.rotationYawHead, yawSpeed);
            prevRotationYawHead = dragon.rotationYawHead;
            turnTicks = 0;
            return;
        }
        
        double yawDiff = Math.abs(dragon.rotationYawHead - prevRotationYawHead);

        if (dragon.isSitting() || yawDiff > 15) {
            turnTicks = 0;
            prevRotationYawHead = dragon.rotationYawHead;
        } else {
            turnTicks++;

            if (turnTicks > turnTicksLimit) {
                yawSpeed = Math.max(1 - (float) (turnTicks - turnTicksLimit) / turnTicksLimit, 0) * 75;
            }
        }

        dragon.renderYawOffset = MathX.updateRotation(dragon.rotationYawHead, dragon.renderYawOffset, yawSpeed);
    }
}