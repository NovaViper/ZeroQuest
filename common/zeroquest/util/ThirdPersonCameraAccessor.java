/*
 ** 2012 July 3
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.util;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import common.zeroquest.ZeroQuest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

/**
 * Accessor for an implemented, but unused feature of the rendering class to change
 * the third-person camera distance.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ThirdPersonCameraAccessor {
    
    private static final String[] FIELDS_THIRDPERSONDISTANCE = new String[] {"thirdPersonDistance", "field_78490_B", "A"};
    
    private static final Logger L = ZeroQuest.L;
    private final EntityRenderer renderer;
    private float defaultDist;
    
    public ThirdPersonCameraAccessor(EntityRenderer renderer) {
        this.renderer = renderer;
        setDefaultThirdPersonDistance();
    }
    
    public ThirdPersonCameraAccessor() {
        this(Minecraft.getMinecraft().entityRenderer);
    }
    
    public void resetThirdPersonDistance() {
        setThirdPersonDistance(defaultDist);
    }
    
    public void setThirdPersonDistance(float dist) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, renderer, dist, FIELDS_THIRDPERSONDISTANCE);
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't set third person distance!", ex);
        }
    }
    
    public float getThirdPersonDistance() {
        try {
            return (Float) ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, renderer, FIELDS_THIRDPERSONDISTANCE);
        } catch (Exception ex) {
            L.log(Level.WARNING, "Can't get third person distance!", ex);
            return 0;
        }
    }
    
    private void setDefaultThirdPersonDistance() {
        defaultDist = getThirdPersonDistance();
    }

    public float getDefaultThirdPersonDistance() {
        return defaultDist;
    }
}
