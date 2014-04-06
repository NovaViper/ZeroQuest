package common.zeroquest.proxy;

import common.zeroquest.ZeroQuest;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;

public class SoundManagerZQuest {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try 
		{
			event.manager.addSound(ZeroQuest.modid + ":" + "sound/mobs/zertum/dzertum.ogg");
			event.manager.addSound(ZeroQuest.modid + ":" + "sound/mobs/zertum/dzertum.ogg");


		}
		catch (Exception e)
		{
			FMLLog.getLogger().info("Could not load Zero Quest sound files. Please report to mod author!");
		}
	}
}

