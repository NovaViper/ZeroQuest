package net.novaviper.zeroquest.common.handlers;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.creature.EntityJakan;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.message.JakanJump;
import net.novaviper.zeroquest.common.message.KortorJump;
import net.novaviper.zeroquest.common.message.PacketHandler;
import net.novaviper.zeroquest.common.message.SealCommand;
import net.novaviper.zeroquest.common.message.ZertumJump;

import org.lwjgl.input.Keyboard;

/**
 * @author ProPercivalalb
 **/
public class KeyStateHandler {
	//@formatter:off
	public static final KeyBinding come = new KeyBinding(Constants.keyDesc + "come", Keyboard.KEY_W, Constants.keyCategory);
	public static final KeyBinding stay = new KeyBinding(Constants.keyDesc + "stay", Keyboard.KEY_S, Constants.keyCategory);
	public static final KeyBinding ok = new KeyBinding(Constants.keyDesc + "ok", Keyboard.KEY_D, Constants.keyCategory);
	public static final KeyBinding heel = new KeyBinding(Constants.keyDesc + "heel", Keyboard.KEY_A, Constants.keyCategory);
	public static final KeyBinding[] keyBindings = new KeyBinding[] { come, stay, ok, heel, Minecraft.getMinecraft().gameSettings.keyBindJump };
	//@formatter:on

	private final HashMap<KeyBinding, Boolean> keyState = new HashMap<KeyBinding, Boolean>();
	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void keyEvent(ClientTickEvent event) {
		this.keyTick(event.phase == Phase.END);
	}

	private void keyTick(boolean tickEnd) {
		for (KeyBinding kb : keyBindings) {
			if (kb.isKeyDown()) {
				if (!tickEnd && (!this.keyState.containsKey(kb) || !this.keyState.get(kb))) {
					this.keyState.put(kb, true);
					// Key Pressed
					EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

					if (kb == mc.gameSettings.keyBindJump) {
						if (player.ridingEntity instanceof EntityZertumEntity && mc.currentScreen == null) {
							EntityZertumEntity dog = (EntityZertumEntity) player.ridingEntity;
							PacketHandler.sendToServer(new ZertumJump(dog.getEntityId()));
						}

						else if (player.ridingEntity instanceof EntityJakan && mc.currentScreen == null) {
							EntityJakan dog = (EntityJakan) player.ridingEntity;
							PacketHandler.sendToServer(new JakanJump(dog.getEntityId()));
						}

						else if (player.ridingEntity instanceof EntityKortor && mc.currentScreen == null) {
							EntityKortor dog = (EntityKortor) player.ridingEntity;
							PacketHandler.sendToServer(new KortorJump(dog.getEntityId()));
						}
					}
					else if (FMLClientHandler.instance().getClient().inGameHasFocus && player != null && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == ModItems.commandSeal) {
						int command = -1;

						if (kb == come) {
							command = 1;
						}
						else if (kb == stay) {
							command = 2;
						}
						else if (kb == ok) {
							command = 3;
						}
						else if (kb == heel) {
							command = 4;
						}

						if (command != -1) {
							PacketHandler.sendToServer(new SealCommand(command));
						}
					}
				}
				else if (!tickEnd) {
					// Key Released

				}
			}
			else {
				this.keyState.put(kb, false);
			}
		}
	}
}