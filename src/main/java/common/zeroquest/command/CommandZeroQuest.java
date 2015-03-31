package common.zeroquest.command;

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
import net.minecraftforge.common.MinecraftForge;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.core.proxy.ClientProxy;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.lib.Constants;


public class CommandZeroQuest extends CommandBase {
	
   @Override
   public String getName() {
       return "zeroquest";
   }
  
   @Override
   public List getAliases()
   {
		List list = new ArrayList<String>();
		list.add("zeroquest");
		list.add("zquest");
		list.add("zero");
		list.add("zq");
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
   public void execute(ICommandSender sender, String[] params) throws CommandException{
       if (params.length < 1 || params[0].isEmpty()) {
           throw new WrongUsageException(getCommandUsage(sender));
       }
       
       // last parameter, optional
       boolean global = params[params.length - 1].equalsIgnoreCase("global") || params[params.length - 1].equalsIgnoreCase("g");

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
               player.addChatMessage(ChatHelper.getChatComponent("Release Type: "+EnumChatFormatting.AQUA+Constants.releaseType));
               player.addChatMessage(ChatHelper.getChatComponent("Minecraft Version: "+EnumChatFormatting.RED+MinecraftForge.MC_VERSION));
               player.addChatMessage(ChatHelper.getChatComponent("Java Version: "+EnumChatFormatting.BLUE+Constants.java));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.cantuse");
           }
       }
       
       else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")) { //TODO
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"----------------- "+EnumChatFormatting.GREEN+"ZeroQuest - " +Constants.version+EnumChatFormatting.GOLD+" -----------------"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest tame,t [globa,g] - "+EnumChatFormatting.RESET+"Tames a nile tameable creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest stage,s <baby,b|adult,a> [global,g] - "+EnumChatFormatting.RESET+"Sets the stage of a tamed nile creature"));     
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest heal,hp, [global,g] - "+EnumChatFormatting.RESET+"Heals a tamed nile creature"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest help,h - "+EnumChatFormatting.RESET+"Pulls up help menu"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest version,v - "+EnumChatFormatting.RESET+"Displays mod information"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest purge,p <tamed,t|all,a|wild,w> [global,g] - "+EnumChatFormatting.RESET+"Kills off nile creatures, Tamed parameter kills only 1 tamed nile creature (unless global parameter is added), all kills EVERY nile creature, and wild kills only 1 nontamed nile creature (unless global parameter is added)"));
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.AQUA+"/zeroquest evolve,e, [global,g] - "+EnumChatFormatting.RESET+"Evolves a tamed Zertum or any subspecies"));               
               player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GOLD+"---------------------------------------------------"));
           
           } else {
               // console can't tame nile entities
               throw new CommandException("commands.zeroquest.cantuse");
           }
       }
       
       else if (command.equalsIgnoreCase("purge") || command.equalsIgnoreCase("p")) { //TODO
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               if (params.length < 2) {
            	   throw new WrongUsageException(getCommandUsage(sender));
               }
           
               String parameter = params[1];
           
               if (parameter.equalsIgnoreCase("tamed") || parameter.equalsIgnoreCase("t")) {
            	   appyModifier(sender, new PurgeModifier(player,false, "tamed"), global);
               }
               else if (parameter.equalsIgnoreCase("all")|| parameter.equalsIgnoreCase("a")) {
            	   appyModifier(sender, new PurgeModifier(player, true, "all"), true);
               }
               else if (parameter.equalsIgnoreCase("wild")|| parameter.equalsIgnoreCase("w")) {
            	   appyModifier(sender, new PurgeModifier(player, false, "wild"), global);
               }
           }
       }
       
       else if (command.equalsIgnoreCase("evolve") || command.equalsIgnoreCase("e")) {
           if (sender instanceof EntityPlayerMP) {
               EntityPlayerMP player = (EntityPlayerMP) sender;
               appyModifier(sender, new EvolveModifier(player), global);
           } else {
               // console can't evolve nile entities
               throw new CommandException("commands.zeroquest.cantuse");
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
    		   : (args.length == 2 && args[0].equalsIgnoreCase("purge") || args[0].equalsIgnoreCase("p") ? getListOfStringsMatchingLastWord(args, new String[] {"tamed", "wild", "all"}): null));
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
			   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Tamed!"));
       }
   }
   
   private class AgeModifier implements EntityModifier {
	   
	   private int age;
       private EntityPlayerMP player;
	   
       AgeModifier(EntityPlayerMP player, int age) {
    	   this.age = age;
    	   this.player = player;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(entity.isTamed()){
    	   		entity.setGrowingAge(age);
 			   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Stage set!"));
    	   }else{
    		   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You cannot change the stage of a wild creature!!"));   
    	   }
       }
   }
   
   private class HealthModifier implements EntityModifier {
	   
       private EntityPlayerMP player;
	   HealthModifier(EntityPlayerMP player)
	   {
		   this.player = player;
	   }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(entity.isTamed()){
    	   		entity.setHealth(entity.getMaxHealth());
  			   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Healed!"));
       		}else{
 			   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You cannot heal wild creatures!"));
       		}
       }
   }
   
   private class PurgeModifier implements EntityModifier {
	   
	   private boolean killAll;
	   private String killWhat;
	   private EntityPlayerMP player;
	   
	   PurgeModifier(EntityPlayerMP player, boolean kill, String type) {
    	   this.killAll = kill;
    	   this.killWhat = type;
    	   this.player = player;
       }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(killAll == false){
    		   if(killWhat == "tamed"){
    			   if(entity.isTamed()){
    		   			entity.setDead();
    			   }
    		   }
    		   else if(killWhat == "wild"){
    			   if(!entity.isTamed()){
    		   			entity.setDead();
    			   }
    		   }
    	   }else{
	   			entity.setDead();
    	   }
			  player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Purge completed!"));
       }
   }
   
   private class EvolveModifier implements EntityModifier {
	   
	   private EntityPlayerMP player;
	   EvolveModifier(EntityPlayerMP player)
	   {
		   this.player = player;
	   }

       @Override
       public void modify(EntityCustomTameable entity) {
    	   if(entity.isTamed()){
    		   if(entity instanceof EntityZertumEntity){
    			   EntityZertumEntity zertum = (EntityZertumEntity)entity;
    			   if(!zertum.hasEvolved() && !zertum.isChild() && zertum.getOwner() == player || zertum.willObeyOthers())
    			   {
    				   zertum.setEvolved(true);
    				   zertum.worldObj.playBroadcastSound(1013, new BlockPos(zertum), 0);
    				   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + zertum.getDogName() +" has been evolved!"));
    			   }
    			   else if(zertum.hasEvolved() && !zertum.isChild() && zertum.getOwner() == player || zertum.willObeyOthers())
    			   {
    				   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getDogName() + " has already evolved!"));
    			   }
    			   else if(!zertum.hasEvolved() && zertum.isChild() && zertum.getOwner() == player || zertum.willObeyOthers())
    			   {
    				   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + zertum.getDogName() + " is too young for evolution!"));
    			   }
    			   else if(!zertum.hasEvolved() && !zertum.isChild() && zertum.getOwner() != player || !zertum.willObeyOthers())
    			   {
    				   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You do not own "+ zertum.getDogName() + " or their owner does not allow the Zertum to obey others!"));
    			   }
       			}
    	   }else{
    		   player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Wild creatures cannot be evolved!"));   
    	   }   
       }
   }
}