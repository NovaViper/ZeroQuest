package net.novaviper.zeroquest;

import static net.minecraftforge.common.ForgeVersion.Status.AHEAD;
import static net.minecraftforge.common.ForgeVersion.Status.OUTDATED;
import static net.minecraftforge.common.ForgeVersion.Status.PENDING;
import static net.minecraftforge.common.ForgeVersion.Status.UP_TO_DATE;
import static net.minecraftforge.common.ForgeVersion.Status.BETA;
import static net.minecraftforge.common.ForgeVersion.Status.BETA_OUTDATED;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.novaviper.zeroquest.common.helper.ChatHelper;
import net.novaviper.zeroquest.common.helper.LogHelper;
import net.novaviper.zeroquest.common.lib.Constants;

import org.apache.commons.io.IOUtils;

public class ModVersionCheck {
	private static String latestVersion = "";
	private static boolean isLatestVersion = false;
	public static boolean checkedVersion = false;
	public static Status status = PENDING;

	public static void startVersionCheck() {
		if (!Constants.DEF_MODCHECKER) {
			return;
		}

		new Thread("Zero Quest Version Check") {
			@Override
			public void run() {
				try {
					URL url = new URL("https://raw.githubusercontent.com/NovaViper/ZeroQuest/master/version.txt");
					InputStream in = url.openStream();
					latestVersion = IOUtils.readLines(in).get(0);

					IOUtils.closeQuietly(in);

					int compare = latestVersion.compareTo(Constants.version);
					if (compare == 0) {
						isLatestVersion = true;
						status = UP_TO_DATE;
					}
					else if (compare < 0) {
						isLatestVersion = true;
						status = AHEAD;
					}
					else if (Constants.releaseType.equals("Beta") && compare == 0) {
						isLatestVersion = true;
						status = BETA;
					}
					else {
						isLatestVersion = false;
						if (Constants.releaseType.equals("Release")) {
							status = OUTDATED;
						}
						else {
							status = BETA_OUTDATED;
						}
					}

				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}

				Side side = FMLCommonHandler.instance().getEffectiveSide();
				while (!checkedVersion && side == Side.CLIENT) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					EntityPlayer player = ZeroQuest.proxy.getPlayerEntity();
					if (player != null) {
						if (!ModVersionCheck.checkedVersion) {
							LogHelper.info("Received version data: %s", status);
							String chat = String.format("A new Zero Quest version exists: %s. Get it here: %s", latestVersion, Constants.url);
							if (status == OUTDATED || status == BETA_OUTDATED) {
								LogHelper.info(chat);
							}

							if (ModVersionCheck.isLatestVersion() && ModVersionCheck.status == UP_TO_DATE) {
								player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Zero Quest " + Constants.version + EnumChatFormatting.RESET + " is up to date!"));
								ModVersionCheck.checkedVersion = true;
							}
							if (ModVersionCheck.isLatestVersion() && ModVersionCheck.status == AHEAD) {
								player.addChatMessage(ChatHelper.getChatComponent("Welcome, " + EnumChatFormatting.GREEN + player.getDisplayNameString() + EnumChatFormatting.RESET + ", to " + EnumChatFormatting.GREEN + "Zero Quest" + EnumChatFormatting.RESET + "(" + Constants.version + ")" + " Release for Minecraft " + EnumChatFormatting.GREEN + MinecraftForge.MC_VERSION + EnumChatFormatting.RESET + "!"));
								ModVersionCheck.checkedVersion = true;
							}
							else if (ModVersionCheck.isLatestVersion() && ModVersionCheck.status == BETA) {
								player.addChatMessage(ChatHelper.getChatComponent("Welcome, " + EnumChatFormatting.GREEN + player.getDisplayNameString() + EnumChatFormatting.RESET + ", to " + EnumChatFormatting.GREEN + "Zero Quest" + EnumChatFormatting.RESET + "(" + Constants.version + ")" + " Beta for Minecraft " + EnumChatFormatting.GREEN + MinecraftForge.MC_VERSION + EnumChatFormatting.RESET + "!"));
								player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "Thank you for beta testing!"));
								ModVersionCheck.checkedVersion = true;
							}
							else if (status == OUTDATED || status == BETA_OUTDATED) {
								ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, Constants.url);
								ChatStyle linkStyle = new ChatStyle().setChatClickEvent(clickEvent);
								ChatComponentText linkText = new ChatComponentText("Minecraft Curse Forge");
								linkText.setChatStyle(linkStyle);
								linkText.getChatStyle().setColor(EnumChatFormatting.GREEN);
								linkText.getChatStyle().setUnderlined(true);
								linkText.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(Constants.url)));

								player.addChatMessage(ChatHelper.getChatComponentTranslation(EnumChatFormatting.RED + "Zero Quest" + (Constants.releaseType.equals("Beta")
										? " Beta build" : "") + "(" + Constants.version + ") is outdated!" + EnumChatFormatting.RESET + " The latest " + EnumChatFormatting.GREEN + "Zero Quest" + EnumChatFormatting.RESET + "(" + EnumChatFormatting.GREEN + ModVersionCheck.getLatestVersion() + EnumChatFormatting.RESET + ") build for Minecraft " + EnumChatFormatting.GREEN + MinecraftForge.MC_VERSION + EnumChatFormatting.RESET + " is available! Get it at %s!", linkText));
								ModVersionCheck.checkedVersion = true;
							}
						}
					}

				}
			}
		}.start();
	}

	public static boolean isLatestVersion() {
		return isLatestVersion;
	}

	public static String getLatestVersion() {
		return latestVersion;
	}
}