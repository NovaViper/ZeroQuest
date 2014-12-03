package common.zeroquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import common.zeroquest.core.helper.ChatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class OnPlayerLogin{

	private String version;
	private String name;
	private boolean isBeta;

	public OnPlayerLogin(String modVersion, String modName, boolean beta){
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
				if(pairedString[0].equals(name)){
					if(pairedString[1].equals(version)){
						player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + pairedString[0] + " " + version +  EnumChatFormatting.RESET + " is up to date"));
						break;
					}else if(isBeta == true){
						player.addChatMessage(ChatHelper.getChatComponent("Welcome to " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+version+")" + " Beta for Minecraft "+EnumChatFormatting.GREEN+pairedString[2]+EnumChatFormatting.RESET+"!"));
						player.addChatMessage(ChatHelper.getChatComponent("Thank you for beta testing!"));
						break;						
					}else{
						player.addChatMessage(ChatHelper.getChatComponent("A new update: " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+pairedString[1]+")" + " is available for Minecraft "+EnumChatFormatting.GREEN+pairedString[2]+EnumChatFormatting.RESET+"!"));
						player.addChatMessage(ChatHelper.getChatComponent("Download newest version at: http://minecraft.curseforge.com/mc-mods/221194"));
						break;
					}
					
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 1. Unknown URL");
			player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Could not verify version for " + name));
			player.addChatMessage(ChatHelper.getChatComponent("Reason: " + EnumChatFormatting.RED + "URL might be unavilaible or corrupted! Contact the mod's owner!"));
		} catch (IOException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 2. No Internet Connection");
			player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "Could not verify version for " + name));
			player.addChatMessage(ChatHelper.getChatComponent("Reason: " + EnumChatFormatting.RED + "Check your internet connection!"));
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