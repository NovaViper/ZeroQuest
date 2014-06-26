package common.zeroquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class OnPlayerLogin implements IPlayerTracker{




	private String version;
	private String name;


	public OnPlayerLogin(String modVersion, String modName){
		version = modVersion;
		name = modName;
	}




	@Override
	public void onPlayerLogin(EntityPlayer player) {	
		verifyModVersion(player);
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
						player.addChatMessage(EnumChatFormatting.GREEN + pairedString[0] +  EnumChatFormatting.RESET + " is up to date");
						break;
					}else{
						player.addChatMessage("A new update is available for " + EnumChatFormatting.GREEN  + pairedString[0] + EnumChatFormatting.RESET + "("+pairedString[1]+")");
						player.addChatMessage("Download newest update from " + EnumChatFormatting.DARK_AQUA + "http://minecraft.curseforge.com/mc-mods/221194-forge-zero-quest/files");
						break;
					}
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 1.");
			player.addChatMessage("Could not verify version for " + name);
		} catch (IOException e) {
			FMLLog.getLogger().info("[ERROR] Couldn't Handle Update. Index 2. No Internet Connection");
			player.addChatMessage("Could not verify version for " + name);
		}
	}


	/*===============UNUSED==============*/
	@Override
	public void onPlayerLogout(EntityPlayer player) {
	}


	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}


	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}