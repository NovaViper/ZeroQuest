package common.zeroquest.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class SpawnPet extends CommandBase{

@Override
public String getCommandName()
{
return "petcall";
// Name of the command "test" will be called by "/test"
}

@Override
public String getCommandUsage(ICommandSender icommandsender)
{
return "commands.petcall.usage";
// Message to show when the user uses "/help test"
}

@Override
public void processCommand(ICommandSender icommandsender, String[] astring)
	{
	if(icommandsender instanceof EntityPlayer)
	{
		EntityPlayer player = (EntityPlayer) icommandsender;
		// Turn the sender into a player entity

		player.addChatMessage("Testing");
		// Send the player entity a message
		}
	}
}
