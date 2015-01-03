package common.zeroquest.core.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;

import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.lib.Constants;


public class CommandZeroQuest extends CommandBase {

	public static final Logger Log = ZeroQuest.Log;
	
   @Override
   public String getName() {
       return "zquest";
   }
  
   @Override
   public List getAliases()
   {
		List list = new ArrayList<String>();
		list.add("zquest");
		list.add("zero");
		list.add("zq");
		return list;
   }

   
   @Override
   public String getCommandUsage(ICommandSender sender) {
       return String.format("/zquest,zq,zero help,h");
   }
   
   /**
    * Return the required permission level for this command.
    */
   @Override
   public int getRequiredPermissionLevel() {
       return 4;
   }

   @Override
   public void execute(ICommandSender sender, String[] params) throws CommandException{
       if (params.length < 1 || params[0].isEmpty()) {
           throw new WrongUsageException(getCommandUsage(sender));
       }
       
       // last parameter, optional
       boolean global = params[params.length - 1].equalsIgnoreCase("global") || params[params.length - 1].equalsIgnoreCase("g");

       String command = params[0];
       if (command.equalsIgnoreCase("stage") || command.equalsIgnoreCase("s")) {
           if (params.length < 2) {
               throw new WrongUsageException(getCommandUsage(sender));
           }
           
           String parameter = params[1];
           
           if (parameter.equalsIgnoreCase("baby") || parameter.equalsIgnoreCase("b")) {
        	   appyModifier(sender, new AgeModifier(-24000), global);
           }
           else if (parameter.equalsIgnoreCase("adult") || parameter.equalsIgnoreCase("a")) {
        	   appyModifier(sender, new AgeModifier(1), global);
           }
       }
       
       else if (command.equalsIgnoreCase("heal") || command.equalsIgnoreCase("hp")) {
    	   if (sender instanceof EntityPlayerMP) {
               appyModifier(sender, new HealthModifier(), global);
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.cantheal");
           }
       }
       
       else if (command.equalsIgnoreCase("tame") || command.equalsIgnoreCase("t")) {
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               appyModifier(sender, new TameModifier(player), global);
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.canttame");
           }
       }
       
       else if (command.equalsIgnoreCase("version") || command.equalsIgnoreCase("v")) {
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
               player.addChatMessage(ChatHelper.getChatComponent("Zero Quest - "+EnumChatFormatting.GREEN+Constants.version));
               player.addChatMessage(ChatHelper.getChatComponent("File Type: "+EnumChatFormatting.AQUA+Constants.DEF_RELEASETYPE));
               player.addChatMessage(ChatHelper.getChatComponent("Minecraft Version: "+EnumChatFormatting.RED+Constants.DEF_MCVERSION));
               player.addChatMessage(ChatHelper.getChatComponent("Java Version: "+EnumChatFormatting.BLUE+Constants.DEF_JAVA));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
           } else {
               // console can't tame nile entities
               throw new CommandException("You can't use this command from console!");
           }
       }
       
       else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) { //TODO
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"----------------- "+EnumChatFormatting.GREEN+"ZeroQuest - " +Constants.version+EnumChatFormatting.GOLD+" -----------------"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest tame,t [globa,g] - "+EnumChatFormatting.RESET+"Tames a nile tameable creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest stage,s <baby,b|adult,a> [global,g] - "+EnumChatFormatting.RESET+"Sets the stage of a tamed nile creature"));     
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest heal,hp, [global,g] - "+EnumChatFormatting.RESET+"Heals a tamed nile creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest help,h - "+EnumChatFormatting.RESET+"Pulls up help menu"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest version,v - "+EnumChatFormatting.RESET+"Displays mod version"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest purge,p <tamed,t|all,a> [global,g] - "+EnumChatFormatting.RESET+"Kills off nile creatures, Tamed parameter kills only 1 tamed nile creature (unless global parameter is added), all kills EVERY nile creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
           
           } else {
               // console can't tame nile entities
               throw new CommandException("You can't use this command from console!");
           }
       }
       
       else if (command.equalsIgnoreCase("purge") || command.equalsIgnoreCase("p")) { //TODO
           if (params.length < 2) {
               throw new WrongUsageException(getCommandUsage(sender));
           }
           
           String parameter = params[1];
           
           if (parameter.equalsIgnoreCase("tamed") || parameter.equalsIgnoreCase("t")) {
        	   appyModifier(sender, new PurgeModifier(false), global);
           }
           else if (parameter.equalsIgnoreCase("all")|| parameter.equalsIgnoreCase("a")) {
        	   appyModifier(sender, new PurgeModifier(true), true);
           }
       }       
       else {
           throw new WrongUsageException(getCommandUsage(sender));
       }
   }
   
   /**
    * Adds the strings available in this command to the given list of tab completion options.
    */
   @Override
   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
   {
       return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {/*"tame", "heal", "purge", "version", "help", "stage"*/}) : 
    	   (args.length == 2 && args[0].equalsIgnoreCase("stage") || args[0].equalsIgnoreCase("s") ? getListOfStringsMatchingLastWord(args, new String[] {"baby", "adult"}) 
    		   : (args.length == 2 && args[0].equalsIgnoreCase("purge") || args[0].equalsIgnoreCase("p") ? getListOfStringsMatchingLastWord(args, new String[] {"tamed", "all"}): null));
   }

   
   private void appyModifier(ICommandSender sender, EntityModifier modifier, boolean global) throws CommandException {
       if (!global && sender instanceof EntityPlayerMP) {
           EntityPlayerMP player = getCommandSenderAsPlayer(sender);
           double range = 64;
           AxisAlignedBB aabb = AxisAlignedBB.fromBounds(
                   player.posX - 1, player.posY - 1, player.posZ - 1,
                   player.posX + 1, player.posY + 1, player.posZ + 1);
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
           } else {
               modifier.modify((EntityCustomTameable) closestEntity);
           }
       } else {
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
       
       private EntityPlayerMP player;
       
       TameModifier(EntityPlayerMP player) {
           this.player = player;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   entity.tamedFor(player, true);
    	   System.out.println("Tamed!");
       }
   }
   
   private class AgeModifier implements EntityModifier {
	   
	   private int age;
	   
       AgeModifier(int age) {
    	   this.age = age;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(entity.isTamed()){
    	   		entity.setGrowingAge(age);
    	   		entity.spawnAgeParticles(true);
    	   }
	   		System.out.println("Age set!");
       }
   }
   
   private class HealthModifier implements EntityModifier {
	   
	   HealthModifier() {}

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(entity.isTamed()){
    	   		entity.setHealth(entity.getMaxHealth());
    	   		entity.spawnHealParticles(true);
       		}
    	   System.out.println("Healed!");
       }
   }
   
   private class PurgeModifier implements EntityModifier {
	   
	   private boolean killAll;
	   
	   PurgeModifier(boolean kill) {
    	   this.killAll = kill;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(killAll == false){
    		   if(entity.isTamed()){
    	   			entity.setHealth(0);
    		   }
    	   }else{
	   				entity.setHealth(0);
    	   }
	   		System.out.println("Purge Completed!");
       }
   }
}