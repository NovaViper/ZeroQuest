package common.zeroquest.core.command;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityCustomTameable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;


public class CommandZeroQuest extends CommandBase {

	public static final Logger Log = ZeroQuest.Log;
	
   @Override
   public String getCommandName() {
       return "zquest";
   }
   
   @Override
   public String getCommandUsage(ICommandSender sender) {
       String stages = StringUtils.join("baby" + '|' + "adult");
       return String.format("/zquest <tame|heal|stage <%s> [global]", stages);
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
       boolean global = params[params.length - 1].equalsIgnoreCase("global");

       String command = params[0];
       if (command.equals("stage")) {
           if (params.length < 2) {
               throw new WrongUsageException(getCommandUsage(sender));
           }
           
           String parameter = params[1];
           
           if (parameter.equals("baby")) {
        	   appyModifier(sender, new AgeModifier(-24000), global);
           }
           else if (parameter.equals("adult")) {
        	   appyModifier(sender, new AgeModifier(1), global);
           }
       }
       
       else if (command.equals("heal")) {
    	   if (sender instanceof EntityPlayerMP) {
               appyModifier(sender, new HealthModifier(), global);
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.cantheal");
           }
       }
       
       else if (command.equals("tame")) {
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               appyModifier(sender, new TameModifier(player), global);
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.canttame");
           }
       }
       else {
           throw new WrongUsageException(getCommandUsage(sender));
       }
   }
   
   /**
    * Adds the strings available in this command to the given list of tab completion options.
    */
   public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
   {
       return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, new String[] {"tame", "heal", "stage"}): (astring.length == 2 && astring[0].equals("stage") ? getListOfStringsMatchingLastWord(astring, new String[] {"baby", "adult"}): null);
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
       public void modify(EntityCustomTameable dragon);
   }
   
   
   private class TameModifier implements EntityModifier {
       
       private EntityPlayerMP player;
       
       TameModifier(EntityPlayerMP player) {
           this.player = player;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   entity.tamedFor(player, true);
       }
   }
   
   private class AgeModifier implements EntityModifier {
	   
	   private int age;
	   
       AgeModifier(int age) {
    	   this.age = age;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   	entity.setGrowingAge(age);
       }
   }
   
   private class HealthModifier implements EntityModifier {
	   
	   HealthModifier() {}

       @Override
       public void modify(EntityCustomTameable entity) {
    	   	entity.setHealth(entity.getMaxHealth());
    	   	Log.info("Healed!");
       }
   }
}