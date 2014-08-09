/*
 ** 2013 October 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.entity.helper;

import java.util.Random;

import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.EntityJakanPrime;
import net.minecraft.entity.DataWatcher;
import net.minecraft.nbt.NBTTagCompound;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class CustomTameableHelper {

    protected final EntityCustomTameable tameable;
    protected final DataWatcher dataWatcher;
    protected final Random rand;

    public CustomTameableHelper(EntityCustomTameable tameable) {
        this.tameable = tameable;
        this.dataWatcher = tameable.getDataWatcher();
        this.rand = tameable.getRNG();
    }
    
    public void writeToNBT(NBTTagCompound nbt) {}
    public void readFromNBT(NBTTagCompound nbt) {}
    public void applyEntityAttributes() {}
    public void onLivingUpdate() {}
    public void onDeathUpdate() {}
    public void onDeath() {}
}
