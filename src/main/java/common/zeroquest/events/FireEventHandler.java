package common.zeroquest.events;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import common.zeroquest.ModBlocks;
import common.zeroquest.network.PacketHandler;
import common.zeroquest.network.imessage.FireSound;

public class FireEventHandler {

	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		int button = event.button - 100;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		World world = mc.theWorld;
		int key = mc.gameSettings.keyBindAttack.getKeyCode();

		BlockPos pos = mc.objectMouseOver.getBlockPos();
		EnumFacing face = mc.objectMouseOver.sideHit;

		if ((button == key) && (Mouse.isButtonDown(button + 100))) {
			if (pos != null) {
				if (world.getBlockState(pos).getBlock() != null) {
					this.extinguishFire(player, pos, face, world, event, button);
				}
			}
		}
	}

	@SubscribeEvent
	public void onInputEvent(KeyInputEvent event) {
		int key = Keyboard.getEventKey();
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;
		World world = mc.theWorld;
		int bind = mc.gameSettings.keyBindAttack.getKeyCode();

		BlockPos pos = mc.objectMouseOver.getBlockPos();
		EnumFacing face = mc.objectMouseOver.sideHit;

		if ((key == bind) && (Keyboard.isKeyDown(key))) {
			if (world.getBlockState(pos).getBlock() != null) {
				this.extinguishFire(player, pos, face, world, event, key);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerMovement(LivingEvent event) {
		Entity player = event.entity;
		BlockPos pos = player.getPosition();
		World world = player.worldObj;
		Block block = world.getBlockState(pos).getBlock();
		if ((block == ModBlocks.nileFire) || (world.getBlockState(pos.up()).getBlock() == ModBlocks.nileFire)) {
			if (player.isImmuneToFire()) {

			}
			else if ((player instanceof EntityPlayer) && (((EntityPlayer) player).capabilities.isCreativeMode)) {

			}
			else {
				player.setFire(8);
			}
		}
		else if ((block == ModBlocks.darkFire) || (world.getBlockState(pos.up()).getBlock() == ModBlocks.darkFire)) {
			if (player.isImmuneToFire()) {

			}
			else if ((player instanceof EntityPlayer) && (((EntityPlayer) player).capabilities.isCreativeMode)) {

			}
			else {
				player.setFire(8);
			}
		}
	}

	private void extinguishFire(EntityPlayer player, BlockPos posIn, EnumFacing face, World world, Event event, int key) {
		BlockPos pos = posIn.offset(face);

		if (world.getBlockState(pos).getBlock() == ModBlocks.nileFire) {
			if ((event instanceof MouseEvent) || (event instanceof PlayerInteractEvent)) {
				PacketHandler.sendToServer(new FireSound(player, face, pos));
				event.setCanceled(true);
			}
			else if (event instanceof KeyInputEvent) {
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please contact the modder of Zero Quest if you are trying to put out a fire " + EnumChatFormatting.RED + "using a key on your keyboard as your attack key."));
			}
		}
		else if (world.getBlockState(pos).getBlock() == ModBlocks.darkFire) {
			if ((event instanceof MouseEvent) || (event instanceof PlayerInteractEvent)) {
				PacketHandler.sendToServer(new FireSound(player, face, pos));
				event.setCanceled(true);
			}
			else if (event instanceof KeyInputEvent) {
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please contact the modder of Zero Quest if you are trying to put out a fire " + EnumChatFormatting.RED + "using a key on your keyboard as your attack key."));
			}
		}
	}
}