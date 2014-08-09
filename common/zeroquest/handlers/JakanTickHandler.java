/*
 ** 2012 August 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.handlers;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

import java.util.EnumSet;

import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.util.ThirdPersonCameraAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class JakanTickHandler implements ITickHandler {
    
    private final Minecraft mc = Minecraft.getMinecraft();
    private final ThirdPersonCameraAccessor tpca = new ThirdPersonCameraAccessor();
    private int noticeTicks;
    private boolean ridingDragon;
    private boolean ridingDragonPrev;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (mc.thePlayer == null) {
            return;
        }
        
        ridingDragon = mc.thePlayer.ridingEntity instanceof EntityJakanPrime;
        
        // display a key binding notice after the vanilla notice
        if (ridingDragon && !ridingDragonPrev) {
            tpca.setThirdPersonDistance(6);
            noticeTicks = 70;
        } else if (!ridingDragon && ridingDragonPrev) {
            tpca.resetThirdPersonDistance();
            noticeTicks = 0;
        } else {
            if (noticeTicks > 0) {
                noticeTicks--;
            }
            
            if (noticeTicks == 1) {
                String keyUpName = GameSettings.getKeyDisplayString(JakanKeyHandler.KEY_FLY_UP.keyCode);
                String keyDownName = GameSettings.getKeyDisplayString(JakanKeyHandler.KEY_FLY_DOWN.keyCode);
                mc.ingameGUI.func_110326_a(I18n.getStringParams("dragon.mountNotice", new Object[] {keyUpName, keyDownName}), false);
            }
        }
        
        ridingDragonPrev = ridingDragon;
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.WORLD, TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return getClass().getSimpleName();
    }
    
}