/*
 ** 2013 October 28
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package common.zeroquest.key;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.zeroquest.ZeroQuest;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class RemoteKey {
    
    private static final Logger L = ZeroQuest.L;
    
    private String name;
    private boolean down;
    
    public RemoteKey() {
    }
    
    public RemoteKey(String name, boolean down) {
        this.name = name;
        this.down = down;
    }
    
    public RemoteKey(KeyBinding kb) {
        this(kb.keyDescription, kb.pressed);
    }
    
    public RemoteKey(Packet250CustomPayload packet) {
        read(packet.data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public final byte[] write() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        
        try {
            dos.writeUTF(name);
            dos.writeBoolean(down);
            dos.close();
        } catch (IOException ex) {
            L.log(Level.WARNING, "Can't write packet", ex);
        }

        return bos.toByteArray();
    }
    
    public final void read(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bis);
        
        try {
            name = dis.readUTF();
            down = dis.readBoolean();
            dis.close();
        } catch (IOException ex) {
            L.log(Level.WARNING, "Can't read packet", ex);
        }
    }
}
