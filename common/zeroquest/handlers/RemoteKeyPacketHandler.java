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

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import java.util.HashMap;
import java.util.Map;

import common.zeroquest.key.RemoteKey;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 * 
 */
public class RemoteKeyPacketHandler implements IPacketHandler {
    
    public static final String CHANNEL = "ZeroQuest";
    
    private static RemoteKeyPacketHandler instance;

    public static RemoteKeyPacketHandler getInstance() {
        if (instance == null) {
            instance = new RemoteKeyPacketHandler();
        }
        return instance;
    }
    
    private Map<String, Map<String, Boolean>> playerKeys = new HashMap<String, Map<String, Boolean>>();
    
    private RemoteKeyPacketHandler() {
    }
    
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            RemoteKey rk = new RemoteKey(packet);
            
            Map<String, Boolean> playerKeyMap;
            if (!playerKeys.containsKey(playerMP.username)) {
                playerKeyMap = new HashMap<String, Boolean>();
                playerKeys.put(playerMP.username, playerKeyMap);
            } else {
                playerKeyMap = playerKeys.get(playerMP.username);
            }
            playerKeyMap.put(rk.getName(), rk.isDown());
        }
    }
    
    public boolean isKeyPressed(String username, String keyname) {
        Map<String, Boolean> playerKeyMap = playerKeys.get(username);
        
        if (playerKeyMap == null) {
            return false;
        }
        
        Boolean pressed = playerKeyMap.get(keyname);
        
        return pressed != null && pressed.booleanValue();
    }
    
    public void clearKeyMapping() {
        playerKeys.clear();
    }
}
