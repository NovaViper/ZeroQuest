package net.novaviper.zeroquest.common.message;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;
import net.novaviper.zeroquest.common.helper.ChatHelper;

public class SealCommand implements IMessage {

	public int commandId;

	public SealCommand() {}

	public SealCommand(int commandId) {
		this.commandId = commandId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		commandId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.commandId);
	}

	public static class Handler implements IMessageHandler<SealCommand, IMessage> {
		public Handler() {}

		@Override
		public IMessage onMessage(SealCommand message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack == null) {
				return null;
			}

			if (stack.getItem() == ModItems.commandSeal) {

				if (message.commandId == 1) {
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
					boolean isDog = false;
					List nearEnts = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(20D, 20D, 20D));
					for (Object o : nearEnts) {
						if (o instanceof EntityZertumEntity) {
							EntityZertumEntity dog = (EntityZertumEntity) o;
							if (dog.canInteract(player)) {
								dog.getSitAI().setSitting(false);
								dog.getNavigator().clearPathEntity();
								dog.setAttackTarget((EntityLivingBase) null);
								if (dog.mode.isMode(EnumMode.WANDERING)) {
									dog.mode.setMode(EnumMode.DOCILE);
								}
								isDog = true;
							}
						}
					}
					if (isDog) {
						player.addChatMessage(ChatHelper.getChatComponentTranslation("command.stand"));
					}
				}
				else if (message.commandId == 2) {
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
					boolean isDog = false;
					List nearEnts = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(20D, 20D, 20D));
					for (Object o : nearEnts) {
						if (o instanceof EntityZertumEntity) {
							EntityZertumEntity dog = (EntityZertumEntity) o;
							if (dog.canInteract(player)) {
								dog.getSitAI().setSitting(true);
								dog.getNavigator().clearPathEntity();
								dog.setAttackTarget((EntityLivingBase) null);
								if (dog.mode.isMode(EnumMode.WANDERING)) {
									dog.mode.setMode(EnumMode.DOCILE);
								}
								isDog = true;
							}
						}
					}
					if (isDog) {
						player.addChatMessage(ChatHelper.getChatComponentTranslation("command.sit"));
					}
				}
				else if (message.commandId == 3) {
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
					boolean isDog = false;
					List nearEnts = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(20D, 20D, 20D));
					for (Object o : nearEnts) {
						if (o instanceof EntityZertumEntity) {
							EntityZertumEntity dog = (EntityZertumEntity) o;
							if (dog.canInteract(player)) {
								if (dog.getMaxHealth() / 2 >= dog.getHealth()) {
									dog.getSitAI().setSitting(true);
									dog.getNavigator().clearPathEntity();
									dog.setAttackTarget((EntityLivingBase) null);
								}
								else {
									dog.getSitAI().setSitting(false);
									dog.getNavigator().clearPathEntity();
									dog.setAttackTarget((EntityLivingBase) null);
								}
								isDog = true;
							}
						}
					}
					if (isDog) {
						player.addChatMessage(ChatHelper.getChatComponentTranslation("command.attention"));
					}
				}
				else if (message.commandId == 4) {
					world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
					boolean isDog = false;
					List nearEnts = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(20D, 20D, 20D));
					for (Object o : nearEnts) {
						if (o instanceof EntityZertumEntity) {
							EntityZertumEntity dog = (EntityZertumEntity) o;
							if (dog.canInteract(player) && !dog.isSitting() && !dog.mode.isMode(EnumMode.WANDERING)) {
								int i = MathHelper.floor_double(player.posX) - 2;
								int j = MathHelper.floor_double(player.posZ) - 2;
								int k = MathHelper.floor_double(player.getEntityBoundingBox().minY);
								for (int l = 0; l <= 4; l++) {
									for (int i1 = 0; i1 <= 4; i1++) {
										if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(world, new BlockPos(i + l, k - 1, j + i1)) && !world.getBlockState(new BlockPos(i + l, k, j + i1)).getBlock().isFullBlock() && !world.getBlockState(new BlockPos(i + l, k + 1, j + i1)).getBlock().isFullBlock() && world.getBlockState(new BlockPos(i + l, k + 1, j + i1)) != Blocks.flowing_lava && world.getBlockState(new BlockPos(i + l, k + 1, j + i1)) != Blocks.lava) {
											dog.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, dog.rotationYaw, dog.rotationPitch);
										}
									}
								}
								isDog = true;
							}
						}
					}

					if (isDog) {
						player.addChatMessage(ChatHelper.getChatComponentTranslation("command.come"));
					}
				}

			}
			return message;
		}
	}
}
