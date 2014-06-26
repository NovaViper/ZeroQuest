package common.zeroquest.command;

import java.util.List;

import common.zeroquest.entity.EntityJakanPrime;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandZeroQuest extends CommandBase{

    @Override
    public String getCommandName() {
        return "dragon";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.dragon.usage";
    }
    
    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params){
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
            
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
	}

    private void appyModifier(ICommandSender sender, EntityModifier modifier, boolean global) {
        if (!global && sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            
            /*if (closestEntity == null) {
                throw new CommandException("commands.dragon.nodragons");
            } else {
                modifier.modify((EntityJakanPrime) closestEntity);
            }
        } else {
            // scan all entities on all dimensions
            MinecraftServer server = MinecraftServer.getServer();
            for (WorldServer worldServer : server.worldServers) {
                List<Entity> entities = worldServer.loadedEntityList;

                for (int i = 0; i < entities.size(); i++) {
                    Entity entity = entities.get(i);

                    if (!(entity instanceof EntityJakanPrime)) {
                        continue;
                    }

                    modifier.modify((EntityJakanPrime) entity);
                }
            }*/
        }
	}
    
    private interface EntityModifier {
        public void modify(EntityJakanPrime dragon);
    }
    
}
