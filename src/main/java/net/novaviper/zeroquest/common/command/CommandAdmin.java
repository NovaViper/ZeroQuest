package net.novaviper.zeroquest.common.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.novaviper.zeroquest.common.entity.EntityCustomTameable;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.helper.ChatHelper;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Strings;

public class CommandAdmin extends CommandBase {

	@Override
	public String getCommandName() {
		return "zeroquest";
	}

	@Override
	public List getCommandAliases() {
		List list = new ArrayList<String>();
		list.add("zquest");
		list.add("zero");
		list.add("zq");
		list.add("ZEROQUEST");
		list.add("ZQUEST");
		list.add("ZERO");
		list.add("ZQ");
		return list;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return String.format("/zeroquest,zquest,zq,zero help,h");
	}

	/**
	 * Return the required permission level for this command.
	 */
	@Override
	public int getRequiredPermissionLevel() {
		return 4;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) throws CommandException {
		if (params.length < 1 || params[0].isEmpty()) {
			throw new WrongUsageException(getCommandUsage(sender));
		}

		// last parameter, optional
		boolean global = params[params.length - 1].equalsIgnoreCase("global") || params[params.length - 1].equalsIgnoreCase("g") || params[params.length - 1].equalsIgnoreCase("all") || params[params.length - 1].equalsIgnoreCase("a");

		String command = params[0];
		if (command.equalsIgnoreCase("stage") || command.equalsIgnoreCase("s")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				if (params.length < 2) {
					throw new WrongUsageException(getCommandUsage(sender));
				}

				String parameter = params[1];

				if (parameter.equalsIgnoreCase("baby") || parameter.equalsIgnoreCase("b")) {
					appyModifier(sender, new AgeModifier(player, -24000), global);
				}
				else if (parameter.equalsIgnoreCase("adult") || parameter.equalsIgnoreCase("a")) {
					appyModifier(sender, new AgeModifier(player, 1), global);
				}
			}
		}

		else if (command.equalsIgnoreCase("heal") || command.equalsIgnoreCase("hp")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				appyModifier(sender, new HealthModifier(player), global);
			}
			else {
				// console can't tame nile entities
				throw new CommandException("commands.zeroquest.cantheal");
			}
		}

		else if (command.equalsIgnoreCase("tame") || command.equalsIgnoreCase("t")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				appyModifier(sender, new TameModifier(player), global);
			}
			else {
				// console can't tame nile entities
				throw new CommandException("commands.zeroquest.canttame");
			}
		}

		else if (command.equalsIgnoreCase("version") || command.equalsIgnoreCase("v")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD + Strings.commandSepartor));
				player.addChatMessage(ChatHelper.getChatComponent("Zero Quest - " + EnumChatFormatting.GREEN + Constants.version));
				player.addChatMessage(ChatHelper.getChatComponent("Release Type: " + EnumChatFormatting.AQUA + Constants.releaseType));
				player.addChatMessage(ChatHelper.getChatComponent("Minecraft Version: " + EnumChatFormatting.RED + MinecraftForge.MC_VERSION));
				player.addChatMessage(ChatHelper.getChatComponent("Minecraft Forge Version: " + EnumChatFormatting.LIGHT_PURPLE + ForgeVersion.getVersion()));
				player.addChatMessage(ChatHelper.getChatComponent("Java Version: " + EnumChatFormatting.BLUE + Constants.java));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD + Strings.commandSepartor));
			}
			else {
				// console can't tame nile entities
				throw new CommandException("commands.zeroquest.cantuse");
			}
		}

		else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) { // NAV:
			// Commands
			// Help
			// List
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD + Strings.commandSepartorHalf + EnumChatFormatting.GREEN + "ZeroQuest - " + Constants.version + EnumChatFormatting.GOLD + Strings.commandSepartorHalf));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest tame,t [global,g,all,a] - " + EnumChatFormatting.RESET + "Tames a nile tameable creature"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest stage,s <baby,b|adult,a> [global,g,all,a] - " + EnumChatFormatting.RESET + "Sets the stage of a tamed nile creature"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest heal,hp, [global,g,all,a] - " + EnumChatFormatting.RESET + "Heals a tamed nile creature"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest help,h - " + EnumChatFormatting.RESET + "Pulls up help menu"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest version,v - " + EnumChatFormatting.RESET + "Displays mod information"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest purge,p <tamed,t|all,a|wild,w> [global,g,all,a] - " + EnumChatFormatting.RESET + "Kills off nile creatures, Tamed parameter kills only 1 tamed nile creature (unless global parameter is added), all kills EVERY nile creature without the need of the global parameter, and wild kills only 1 nontamed nile creature (unless global parameter is added)"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest state,st <evolve,e,ev,evo|devolve,d,de,dev,devo> [global,g,all,a] - " + EnumChatFormatting.RESET + "Change the stage of a tamed Zertum or any subspecies, use multiple to either evolve to stage 3 or revert back to stage 1. Evolve parameter evolves only 1 tamed Zertum (unless global parameter is added) and devolve parameter devolves only 1 tamed Zertum (unless global parameter is added)"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest level,l <number> [global,g,all,a] - " + EnumChatFormatting.RESET + "Adds levels to a tamed Zertum or any subspecies"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA + "/zeroquest gender,g <male,m|female,f> [global,g,all,a] - " + EnumChatFormatting.RESET + "Change the gender of any nile creature"));
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD + Strings.commandSepartor));

			}
			else {
				// console can't tame nile entities
				throw new CommandException("commands.zeroquest.cantuse");
			}
		}

		else if (command.equalsIgnoreCase("purge") || command.equalsIgnoreCase("p")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				if (params.length < 2) {
					throw new WrongUsageException(getCommandUsage(sender));
				}

				String parameter = params[1];

				if (parameter.equalsIgnoreCase("tamed") || parameter.equalsIgnoreCase("t")) {
					appyModifier(sender, new PurgeModifier(player, false, "tamed"), global);
				}
				else if (parameter.equalsIgnoreCase("all") || parameter.equalsIgnoreCase("a")) {
					appyModifier(sender, new PurgeModifier(player, true, "all"), true);
				}
				else if (parameter.equalsIgnoreCase("wild") || parameter.equalsIgnoreCase("w")) {
					appyModifier(sender, new PurgeModifier(player, false, "wild"), global);
				}
			}
		}

		else if (command.equalsIgnoreCase("state") || command.equalsIgnoreCase("st")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				if (params.length < 2) {
					throw new WrongUsageException(getCommandUsage(sender));
				}

				String parameter = params[1];

				if (parameter.equalsIgnoreCase("evolve") || parameter.equalsIgnoreCase("e") || parameter.equalsIgnoreCase("ev") || parameter.equalsIgnoreCase("evo")) {

					appyModifier(sender, new EvolveModifier(player), global);
				}
				else if (parameter.equalsIgnoreCase("devolve") || parameter.equalsIgnoreCase("d") || parameter.equalsIgnoreCase("de") || parameter.equalsIgnoreCase("dev") || parameter.equalsIgnoreCase("devo")) {
					appyModifier(sender, new DevolveModifier(player), global);
				}
			}
		}

		else if (command.equalsIgnoreCase("level") || command.equalsIgnoreCase("l")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;

				if (params.length > 1) {
					int i;
					i = parseInt(params[1], 0);
					appyModifier(sender, new LevelUpModifier(player, i), global);
				}
				else {
					throw new WrongUsageException(getCommandUsage(sender));
				}
			}
			else {
				// console can't add levels zertums
				throw new CommandException("commands.zeroquest.cantuse");
			}
		}

		else if (command.equalsIgnoreCase("gender") || command.equalsIgnoreCase("g")) {
			if (sender instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				if (params.length < 2) {
					throw new WrongUsageException(getCommandUsage(sender));
				}

				String parameter = params[1];

				if (parameter.equalsIgnoreCase("male") || parameter.equalsIgnoreCase("m")) {
					appyModifier(sender, new GenderModifier(player, true), global);
				}
				else if (parameter.equalsIgnoreCase("female") || parameter.equalsIgnoreCase("f")) {
					appyModifier(sender, new GenderModifier(player, false), global);
				}
			}
		}

		else {
			throw new WrongUsageException(getCommandUsage(sender));
		}
	}

	/**
	 * Adds the strings available in this command to the given list of tab
	 * completion options.
	 */
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return args.length == 1
				? getListOfStringsMatchingLastWord(args, new String[] {})
				: (args.length == 2 && args[0].equalsIgnoreCase("stage") || args[0].equalsIgnoreCase("s")
						? getListOfStringsMatchingLastWord(args, new String[] { "baby", "adult" })
						: (args.length == 2 && args[0].equalsIgnoreCase("purge") || args[0].equalsIgnoreCase("p")
								? getListOfStringsMatchingLastWord(args, new String[] { "tamed",
										"wild", "all" })
								: (args.length == 2 && args[0].equalsIgnoreCase("state") || args[0].equalsIgnoreCase("st")
										? getListOfStringsMatchingLastWord(args, new String[] {
												"evolve", "devolve" }) : null)));
	}

	private void appyModifier(ICommandSender sender, EntityModifier modifier, boolean global) throws CommandException {
		if (!global && sender instanceof EntityPlayerMP) {
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			double range = 64;
			AxisAlignedBB aabb = AxisAlignedBB.fromBounds(player.posX - 1, player.posY - 1, player.posZ - 1, player.posX + 1, player.posY + 1, player.posZ + 1);
			aabb = aabb.expand(range, range, range);
			List<Entity> entities = player.worldObj.getEntitiesWithinAABB(EntityCustomTameable.class, aabb);

			Entity closestEntity = null;
			float minPlayerDist = Float.MAX_VALUE;

			// get closest entity
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				float playerDist = entity.getDistanceToEntity(player);
				if (entity.getDistanceToEntity(player) < minPlayerDist) {
					closestEntity = entity;
					minPlayerDist = playerDist;
				}
			}

			if (closestEntity == null) {
				throw new CommandException("commands.zeroquest.notameable");
			}
			else {
				modifier.modify((EntityCustomTameable) closestEntity);
			}
		}
		else {
			// scan all entities on all dimensions
			MinecraftServer server = MinecraftServer.getServer();
			for (WorldServer worldServer : server.worldServers) {
				List<Entity> entities = worldServer.loadedEntityList;

				for (int i = 0; i < entities.size(); i++) {
					Entity entity = entities.get(i);

					if (!(entity instanceof EntityCustomTameable)) {
						continue;
					}

					modifier.modify((EntityCustomTameable) entity);
				}
			}
		}
	}

	private interface EntityModifier {
		public void modify(EntityCustomTameable entity);
	}

	private class TameModifier implements EntityModifier {

		private final EntityPlayerMP player;

		TameModifier(EntityPlayerMP player) {
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (entity instanceof EntityZertumEntity) {
				EntityZertumEntity zertum = (EntityZertumEntity) entity;
				zertum.tamedFor(player, true);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Tamed!"));
			}
			else {
				entity.tamedFor(player, true);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Tamed!"));
			}
		}
	}

	private class AgeModifier implements EntityModifier {

		private final int age;
		private final EntityPlayerMP player;

		AgeModifier(EntityPlayerMP player, int age) {
			this.age = age;
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (entity.isTamed()) {
				if (entity instanceof EntityZertumEntity) {
					EntityZertumEntity zertum = (EntityZertumEntity) entity;
					if (!zertum.hasEvolved()) {
						entity.setGrowingAge(age);
						zertum.updateEntityAttributes();
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Stage set!"));
					}
					else {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You cannot change the stage of " + zertum.getPetName() + " because " + zertum.genderSubject() + " has " + EnumChatFormatting.RED + "evolved!"));
					}
				}
				else {
					entity.setGrowingAge(age);
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Stage set!"));
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You cannot change the stage of a wild creature!!"));
			}
		}
	}

	private class HealthModifier implements EntityModifier {

		private final EntityPlayerMP player;

		HealthModifier(EntityPlayerMP player) {
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {

			if (entity.isTamed()) {
				if (entity instanceof EntityZertumEntity) {
					EntityZertumEntity zertum = (EntityZertumEntity) entity;
					zertum.setHealth(entity.getMaxHealth());
					zertum.setZertumHunger(Constants.hungerTicks);
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Healed and refilled hunger for " + zertum.getPetName() + "!"));
				}
				else {
					entity.setHealth(entity.getMaxHealth());
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Healed " + entity.getCommandSenderName() + "!"));
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You cannot heal wild creatures!"));
			}
		}
	}

	private class PurgeModifier implements EntityModifier {

		private final boolean killAll;
		private final String killWhat;
		private final EntityPlayerMP player;

		PurgeModifier(EntityPlayerMP player, boolean kill, String type) {
			this.killAll = kill;
			this.killWhat = type;
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (killAll == false) {
				if (killWhat == "tamed") {
					if (entity.isTamed()) {
						entity.setDead();
					}
				}
				else if (killWhat == "wild") {
					if (!entity.isTamed()) {
						entity.setDead();
					}
				}
			}
			else {
				entity.setDead();
			}
			player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Purge completed!"));
		}
	}

	private class EvolveModifier implements EntityModifier {

		private final EntityPlayerMP player;

		EvolveModifier(EntityPlayerMP player) {
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (entity instanceof EntityZertumEntity) {
				EntityZertumEntity zertum = (EntityZertumEntity) entity;
				if (zertum.isTamed()) {
					if (!zertum.hasEvolved() && !zertum.inFinalStage() && !zertum.isChild() && zertum.canInteract(player)) {
						zertum.evolveOnServer(zertum, player);
					}
					else if (zertum.hasEvolved() && !zertum.inFinalStage() && !zertum.isChild() && zertum.canInteract(player)) {
						zertum.finaEvolveOnServer(zertum, player);
					}
					else if ((zertum.hasEvolved() || !zertum.inFinalStage()) && !zertum.isChild() && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " has already evolved!"));
					}
					else if (!zertum.hasEvolved() && zertum.isChild() && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " is too young for evolution!"));
					}
					else if (!zertum.hasEvolved() && !zertum.isChild() && !zertum.canInteract(player)) {
						zertum.doNotOwnMessage(zertum, player);
					}
				}
				else {
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Wild " + zertum.getCommandSenderName() + "s cannot be evolved!"));
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + entity.getCommandSenderName() + "s cannot be evolved!!"));
			}
		}
	}

	private class DevolveModifier implements EntityModifier {

		private final EntityPlayerMP player;

		DevolveModifier(EntityPlayerMP player) {
			this.player = player;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (entity instanceof EntityZertumEntity) {
				EntityZertumEntity zertum = (EntityZertumEntity) entity;
				if (zertum.isTamed()) {
					if (zertum.hasEvolved() && !zertum.inFinalStage() && zertum.canInteract(player)) {
						zertum.devolveOnServer(zertum, player);
					}
					else if (zertum.hasEvolved() && zertum.inFinalStage() && zertum.canInteract(player)) {
						zertum.devolveOnServer(zertum, player);
					}
					else if ((!zertum.hasEvolved() || !zertum.inFinalStage()) && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " has already evolved or has not evolved!"));
					}
					else if (zertum.hasEvolved() && !zertum.canInteract(player)) {
						zertum.doNotOwnMessage(zertum, player);
					}
				}
				else {
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Wild " + zertum.getCommandSenderName() + "s cannot be devolved!"));
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + entity.getCommandSenderName() + "s cannot be devolved!!"));
			}
		}
	}

	private class LevelUpModifier implements EntityModifier {

		private final EntityPlayerMP player;
		private final int level;

		LevelUpModifier(EntityPlayerMP player, int level) {
			this.player = player;
			this.level = level;
		}

		@Override
		public void modify(EntityCustomTameable entity) {
			if (entity.isTamed()) {
				if (entity instanceof EntityZertumEntity) {
					EntityZertumEntity zertum = (EntityZertumEntity) entity;
					if (level <= Constants.maxLevel && zertum.levels.getLevel() < Constants.maxLevel && !zertum.isChild() && zertum.canInteract(player)) {
						zertum.levels.addLevel(level);
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + Integer.toString(level) + (level > 1
								? " levels " : " level ") + "has been added to " + zertum.getPetName()));
					}
					else if (level <= Constants.maxLevel && zertum.levels.getLevel() >= Constants.maxLevel && !zertum.isChild() && zertum.canInteract(player)) {
						zertum.levels.setLevel(Constants.maxLevel);
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " is at its maxmimum level!"));
					}
					else if (level > Constants.maxLevel && zertum.levels.getLevel() < Constants.maxLevel && !zertum.isChild() && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + Integer.toString(level) + " is too big of a number!"));
					}
					else if (level <= Constants.maxLevel && zertum.levels.getLevel() < Constants.maxLevel && zertum.isChild() && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " is too young to gain levels!"));
					}
					else if (level <= Constants.maxLevel && zertum.levels.getLevel() < Constants.maxLevel && !zertum.isChild() && !zertum.canInteract(player)) {
						zertum.doNotOwnMessage(zertum, player);
					}
				}
				else {
					player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + entity.getCommandSenderName() + " cannot gain levels!"));
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Wild creatures cannot gain levels!"));
			}
		}
	}

	private class GenderModifier implements EntityModifier {

		private final EntityPlayerMP player;
		private final boolean gender;

		GenderModifier(EntityPlayerMP player, boolean gender) {
			this.player = player;
			this.gender = gender;
		}

		@Override
		//@formatter:off
		public void modify(EntityCustomTameable entity) {
			if (entity.isTamed()) {
				if (entity instanceof EntityZertumEntity) {
					EntityZertumEntity zertum = (EntityZertumEntity) entity;
					if (zertum.getGender() != gender&& zertum.canInteract(player)) {
						zertum.setGender(gender);
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + zertum.getPetName() + " has been set to a " + (gender ? "Male" : "Female")));
					}
					else if (zertum.getGender() == gender && zertum.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getPetName() + " is already a " + (gender ? "Male" : "Female")));
					}
					else if (zertum.getGender() != gender && !zertum.canInteract(player)) {
						zertum.doNotOwnMessage(zertum, player);
					}
				}
				else {
					if (entity.getGender() != gender && entity.canInteract(player)) {
						entity.setGender(gender);
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + entity.getCommandSenderName() + " has been set to a " + (gender ? "Male" : "Female")));
					}
					else if (entity.getGender() == gender && entity.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + entity.getCommandSenderName() + " is already a " + (gender ? "Male" : "Female")));
					}
					else if (entity.getGender() != gender && !entity.canInteract(player)) {
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You do not own " + entity.getCommandSenderName() + "!"));
					}
				}
			}
			else {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Wild creatures have their genders changed!"));
			}
		}
	}
}