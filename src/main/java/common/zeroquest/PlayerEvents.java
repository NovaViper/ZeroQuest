package common.zeroquest;

import static net.minecraftforge.common.ForgeVersion.Status.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.core.helper.LogHelper;
import common.zeroquest.lib.Constants;

public class PlayerEvents{

	private String version;
	private String name;
	private boolean isBeta;
	public static Status status = PENDING;
	public static String link = "http://minecraft.curseforge.com/mc-mods/221194";
	
	public PlayerEvents(String modVersion, String modName, boolean beta){
		version = modVersion;
		name = modName;
		isBeta = beta;
	}

	@SubscribeEvent
	public void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {	
		verifyModVersion(event.player);
	}

	private void verifyModVersion(EntityPlayer player){
		try {
			URL url = new URL("https://raw.githubusercontent.com/NViper21/ZeroQuest/master/updates.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				String[] pairedString = str.split(":");
				int compare = pairedString[1].compareTo(version);
				if(pairedString[0].equals(name)){
					if(compare == 0){
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + pairedString[0] + " " + version +  EnumChatFormatting.RESET + " is up to date"));
                        status = UP_TO_DATE;
						break;
					}else if(isBeta == true && compare < 0){
						player.addChatMessage(ChatHelper.getChatComponent("Welcome to " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+version+")" + " Beta for Minecraft "+EnumChatFormatting.GREEN+MinecraftForge.MC_VERSION+EnumChatFormatting.RESET+"!"));
						player.addChatMessage(ChatHelper.getChatComponent("Thank you for beta testing!"));
                        status = BETA;
						break;
					}else if(compare < 0){
						player.addChatMessage(ChatHelper.getChatComponent("Welcome Modder/Owner to " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+version+")" + " Release for Minecraft "+EnumChatFormatting.GREEN+MinecraftForge.MC_VERSION+EnumChatFormatting.RESET+"!"));
                        status = AHEAD;
						break;	
					}else{
						player.addChatMessage(ChatHelper.getChatComponent("A new update: " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+pairedString[1]+")" + " is available for Minecraft "+EnumChatFormatting.GREEN+MinecraftForge.MC_VERSION+EnumChatFormatting.RESET+"!"));
						player.addChatMessage(ChatHelper.getChatComponent("Download newest version at: " + link));
                        if(isBeta == false){
                        	status = OUTDATED;
                        }else{
                        	status = BETA_OUTDATED;	
                        }
						break;
					}
				}			
			}
			in.close();
		} catch (Exception e) {
            e.printStackTrace();
            status = FAILED;
        }
		
        LogHelper.info("Received version data: %s", status);
        String chat = String.format("A new %s version exists: %s. Get it here: %s",Constants.name, version, link);
        String chat2 = String.format("A new %s beta version exists: %s. Get it here: %s",Constants.name, version, link);
        if(status == OUTDATED)
            LogHelper.info(chat);
        else if(status == BETA_OUTDATED){
            LogHelper.info(chat2);	
        }
	}

	/*===============UNUSED==============*/ //TODO
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
	}


	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
	}


	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
	}
}