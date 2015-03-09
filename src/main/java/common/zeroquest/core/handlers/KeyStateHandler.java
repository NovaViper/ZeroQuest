package common.zeroquest.core.handlers;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import org.lwjgl.input.Keyboard;

import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.network.packet.PacketCommand;
import common.zeroquest.network.packet.PacketDogJump;
import common.zeroquest.network.packet.PacketJakanJump;
import common.zeroquest.network.packet.PacketKortorJump;

/**
 * @author ProPercivalalb
 **/
public class KeyStateHandler {
	
    public static final KeyBinding come = new KeyBinding("zeroquest.key.come", Keyboard.KEY_W, "key.categories.zeroquest");
    public static final KeyBinding stay = new KeyBinding("zeroquest.key.stay", Keyboard.KEY_S, "key.categories.zeroquest");
    public static final KeyBinding ok = new KeyBinding("zeroquest.key.ok", Keyboard.KEY_D, "key.categories.zeroquest");
    public static final KeyBinding heel = new KeyBinding("zeroquest.key.heel", Keyboard.KEY_A, "key.categories.zeroquest");
    public static final KeyBinding[] keyBindings = new KeyBinding[] {come, stay, ok, heel, Minecraft.getMinecraft().gameSettings.keyBindJump};
    
   	private HashMap<KeyBinding, Boolean> keyState = new HashMap<KeyBinding, Boolean>();
    private Minecraft mc = Minecraft.getMinecraft();
    
    
    @SubscribeEvent
    public void keyEvent(ClientTickEvent event) {
    	this.keyTick(event.phase == Phase.END);
    }
    
    private void keyTick(boolean tickEnd) {
    	for(KeyBinding kb : keyBindings) {
	        if(kb.isKeyDown()) {
	            if (!tickEnd && (!this.keyState.containsKey(kb) || !this.keyState.get(kb))) {
	            	this.keyState.put(kb, true);
	            	//Key Pressed
	            	EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
	            	
	            	if(kb == mc.gameSettings.keyBindJump) {
	            		if(player.ridingEntity instanceof EntityZertumEntity && mc.currentScreen == null) {
	            			EntityZertumEntity dog = (EntityZertumEntity)player.ridingEntity;
	            			ZeroQuest.networkManager.sendPacketToServer(new PacketDogJump(dog.getEntityId()));
	            		}
	            		
	            		else if(player.ridingEntity instanceof EntityJakan && mc.currentScreen == null) {
	            			EntityJakan dog = (EntityJakan)player.ridingEntity;
	            			ZeroQuest.networkManager.sendPacketToServer(new PacketJakanJump(dog.getEntityId()));
	            		}
	            		
	            		else if(player.ridingEntity instanceof EntityKortor && mc.currentScreen == null) {
	            			EntityKortor dog = (EntityKortor)player.ridingEntity;
	            			ZeroQuest.networkManager.sendPacketToServer(new PacketKortorJump(dog.getEntityId()));
	            		}
	            	}
	            	else if(FMLClientHandler.instance().getClient().inGameHasFocus && player != null && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ModItems.commandSeal) {
	            	    int command = -1;
	            	   
	                	if(kb == come) {
	                		command = 1;
	                	}
	                	else if(kb == stay) {
	                		command = 2;
	                	}
	                	else if(kb == ok) {
	                		command = 3;
	                	}
	                	else if(kb == heel) {
	                		command = 4;
	                	}

	                	if(command != -1)
	                		ZeroQuest.networkManager.sendPacketToServer(new PacketCommand(command));
	               }
	            }
	            else if(!tickEnd) {
	            	//Key Released
	            	
	            }
	        }
	        else {
	        	this.keyState.put(kb, false);
	        }
    	}
    }
}