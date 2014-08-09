/*
 ** 2013 October 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.handlers;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.lwjgl.input.Keyboard;

import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.key.RemoteKey;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonKeyHandler extends KeyHandler {
    
    public static final KeyBinding KEY_FLY_UP = new KeyBinding("key.dragon.flyUp", Keyboard.KEY_R);
    public static final KeyBinding KEY_FLY_DOWN = new KeyBinding("key.dragon.flyDown", Keyboard.KEY_F);
    
    private final Minecraft mc = Minecraft.getMinecraft();

    public DragonKeyHandler() {
        super(new KeyBinding[] {KEY_FLY_UP, KEY_FLY_DOWN}, new boolean[] {false, false});
    }
    
    private void sendPacket(KeyBinding kb) {
        // don't send packets when not riding dragons
        if (mc.thePlayer == null || !(mc.thePlayer.ridingEntity instanceof EntityJakanPrime)) {
            return;
        }
        
        // don't send packets if the GUI is active
        if (mc.currentScreen != null) {
            return;
        }
        
        RemoteKey rk = new RemoteKey(kb);
        Packet250CustomPayload packet = new Packet250CustomPayload(RemoteKeyPacketHandler.CHANNEL, rk.write());
        PacketDispatcher.sendPacketToServer(packet);
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        if (tickEnd) {
            sendPacket(kb);
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
        if (tickEnd) {
            sendPacket(kb);
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return getClass().getSimpleName();
    }
}