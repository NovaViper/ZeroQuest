/*
 ** 2013 July 20
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityJakanPrime;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIHunt extends EntityCustomAITargetNonTamed {
    
    private EntityJakanPrime dragon;

    public EntityAIHunt(EntityJakanPrime dragon, Class clazz, int par3, boolean par4) {
        super(dragon, clazz, par3, par4);
        this.dragon = dragon;
    }

    @Override
    public boolean shouldExecute() {
        return !dragon.isChild() && super.shouldExecute();
    }
}
