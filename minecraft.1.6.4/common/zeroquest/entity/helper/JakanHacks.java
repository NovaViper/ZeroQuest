/*
 ** 2014 February 06
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.helper;

import cpw.mods.fml.relauncher.ReflectionHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import common.zeroquest.ModEntities;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.ai.JakanBodyHelper;
import common.zeroquest.spawn.CustomEntityList;
import common.zeroquest.util.UnsafeReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityLiving;

/**
 * HAAAAAX! Workaround code for unsolved bugs and vanilla design failures.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class JakanHacks extends CustomJakanHelper {
    
    private static final Logger L = ZeroQuest.L;
    
    private final Minecraft mc = Minecraft.getMinecraft();
    private int updateTimer;

    public JakanHacks(EntityJakanPrime dragon) {
        super(dragon);
        
        // override EntityBodyHelper field, which is private and has no setter
        // required to fixate body while sitting. also slows down rotation while standing.
        try {
            int fieldIndex = UnsafeReflectionHelper.findFieldIndex(EntityLiving.class, EntityBodyHelper.class);
            ReflectionHelper.setPrivateValue(EntityLiving.class, dragon, new JakanBodyHelper(dragon), fieldIndex);
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't override EntityBodyHelper", ex);
        }
    }

    public void onLivingUpdate() {
        // don't run this too often
        if (--updateTimer > 0) {
            return;
        }
        updateTimer = 200;
        
        // fix tamed dragons in single player that mysteriously don't belong to the only player
        if (mc.isSingleplayer() && mc.thePlayer != null && dragon.isTamed()) {
            String ownerActual = dragon.getOwnerName();
            String ownerExpected = mc.thePlayer.username;
            String entity = CustomEntityList.getEntityString(dragon);
            
            if (!ownerActual.equals(ownerExpected)) {
                L.log(Level.WARNING, "{0} {1} with unexpected owner! Found: \"{2}\", expected: \"{3}\"",
                        new Object[]{entity, dragon.entityId, ownerActual, ownerExpected});
                dragon.setOwner(ownerExpected);
            }
        }
    }
}