package common.zeroquest.core.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.EntityCustomTameable;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;


public class CommandZeroQuest extends CommandBase {

	public static final Logger Log = ZeroQuest.Log;
	
   @Override
   public String getCommandName() {
       return "zquest";
   }
  
   @Override
   public List getCommandAliases()
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
   public void processCommand(ICommandSender sender, String[] params) {
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
           
           if (parameter.equalsIgnoreCase("baby")) {
        	   appyModifier(sender, new AgeModifier(-24000), global);
           }
           else if (parameter.equalsIgnoreCase("adult")) {
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
               player.addChatMessage(ChatHelper.getChatComponent("Zero Quest - "+EnumChatFormatting.GREEN+ZeroQuest.version));
               player.addChatMessage(ChatHelper.getChatComponent("File Type: "+EnumChatFormatting.AQUA+"Release"));
               player.addChatMessage(ChatHelper.getChatComponent("Minecraft Version: "+EnumChatFormatting.RED+"1.7.10"));
               player.addChatMessage(ChatHelper.getChatComponent("Java Version: "+EnumChatFormatting.BLUE+"7"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
           } else {
               // console can't tame nile entities
               throw new CommandException("You can't use this command from console!");
           }
       }
       
       else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) { //TODO
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"----------------- "+EnumChatFormatting.GREEN+"ZeroQuest - " +ZeroQuest.version+EnumChatFormatting.GOLD+" -----------------"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest tame,t [globa,g] - "+EnumChatFormatting.RESET+"Tames a nile tameable creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest stage,s <baby|adult> [global,g] - "+EnumChatFormatting.RESET+"Sets the stage of a tamed nile creature"));     
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest heal,hp, [global,g] - "+EnumChatFormatting.RESET+"Heals a tamed nile creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest help,h - "+EnumChatFormatting.RESET+"Pulls up help menu"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest version,v - "+EnumChatFormatting.RESET+"Displays mod version"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zquest purge,p <tamed|all> [global,g] - "+EnumChatFormatting.RESET+"Kills off nile creatures, Tamed parameter kills only 1 tamed nile creature (unless global parameter is added), all kills EVERY nile creature"));
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
           
           if (parameter.equalsIgnoreCase("tamed")) {
        	   appyModifier(sender, new PurgeModifier(false), global);
           }
           else if (parameter.equalsIgnoreCase("all")) {
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
   public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
   {
       return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, new String[] {/*"tame", "heal", "purge", "version", "help", "stage"*/}) : 
    	   (astring.length == 2 && astring[0].equalsIgnoreCase("stage") || astring[0].equalsIgnoreCase("s") ? getListOfStringsMatchingLastWord(astring, new String[] {"baby", "adult"}) 
    		   : (astring.length == 2 && astring[0].equalsIgnoreCase("purge") || astring[0].equalsIgnoreCase("p") ? getListOfStringsMatchingLastWord(astring, new String[] {"tamed", "all"}): null));
   }

   
   private void appyModifier(ICommandSender sender, EntityModifier modifier, boolean global) {
       if (!global && sender instanceof EntityPlayerMP) {
           EntityPlayerMP player = getCommandSenderAsPlayer(sender);
           double range = 64;
           AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(
                   player.posX - 1, player.posY - 1, player.posZ - 1,
                   player.posX + 1, player.posY + 1, player.posZ + 1);
           aabb = aabb.expand(range, range, range);
           List<Entity> entities = player.worldObj.getEntitiesWithinAABB(EntityCustomTameable.class, aabb);

           Entity closestEntity = null;
           float minPlayerDist = Float.MAX_VALUE;

           // get closest dragon
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