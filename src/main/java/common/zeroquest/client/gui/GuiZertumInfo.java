package common.zeroquest.client.gui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.entity.zertum.util.ModeUtil.EnumMode;
import common.zeroquest.lib.Constants;
import common.zeroquest.network.PacketHandler;
import common.zeroquest.network.imessage.ZertumMode;
import common.zeroquest.network.imessage.ZertumName;
import common.zeroquest.network.imessage.ZertumObey;
import common.zeroquest.network.imessage.ZertumTalents;
import common.zeroquest.util.ResourceReference;

/**
 * @author ProPercivalalb
 */
public class GuiZertumInfo extends GuiScreen {

	public EntityZertumEntity dog;
	public EntityPlayer player;
	private ScaledResolution resolution;
	private final List<GuiTextField> textfieldList = new ArrayList<GuiTextField>();
	private GuiTextField nameTextField;
	private int currentPage = 0;
	private int maxPages = 1;
	public int btnPerPages = 0;
	private final DecimalFormat dfShort = new DecimalFormat("0.00");

	public GuiZertumInfo(EntityZertumEntity dog, EntityPlayer player) {
		this.dog = dog;
		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.labelList.clear();
		this.textfieldList.clear();
		this.resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		Keyboard.enableRepeatEvents(true);
		int topX = this.width / 2;
		int topY = this.height / 2;
		GuiTextField nameTextField = new GuiTextField(0, this.fontRendererObj, topX - 100, topY + 50, 200, 20) {
			@Override
			public boolean textboxKeyTyped(char character, int keyId) {
				boolean typed = super.textboxKeyTyped(character, keyId);
				if (typed) {
					PacketHandler.sendToServer(new ZertumName(dog.getEntityId(), this.getText()));
				}
				return typed;
			}
		};
		nameTextField.setFocused(false);
		nameTextField.setMaxStringLength(32);
		nameTextField.setText(this.dog.getPetName());
		this.nameTextField = nameTextField;

		this.textfieldList.add(nameTextField);

		int size = TalentRegistry.getTalents().size();

		int temp = 0;
		while ((temp + 2) * 21 + 10 < this.resolution.getScaledHeight()) {
			temp += 1;
		}

		this.btnPerPages = temp;

		if (temp < size) {
			this.buttonList.add(new GuiButton(-1, 25, temp * 21 + 10, 20, 20, "<"));
			this.buttonList.add(new GuiButton(-2, 48, temp * 21 + 10, 20, 20, ">"));
		}

		if (this.btnPerPages < 1) {
			this.btnPerPages = 1;
		}

		this.maxPages = (int) Math.ceil((double) TalentRegistry.getTalents().size() / (double) this.btnPerPages);

		if (this.currentPage >= this.maxPages) {
			this.currentPage = 0;
		}

		for (int i = 0; i < this.btnPerPages; ++i) {
			if ((this.currentPage * this.btnPerPages + i) >= TalentRegistry.getTalents().size()) {
				continue;
			}
			this.buttonList.add(new GuiButton(1 + this.currentPage * this.btnPerPages + i, 25, 10 + i * 21, 20, 20, "+"));
		}
		if (this.dog.isOwner(this.player)) {
			this.buttonList.add(new GuiButton(-5, this.width - 64, topY + 65, 42, 20, String.valueOf(this.dog.willObeyOthers())));
		}

		this.buttonList.add(new GuiButton(-6, topX + 40, topY + 25, 60, 20, this.dog.mode.getMode().modeName()));
	}

	@Override
	public void drawScreen(int xMouse, int yMouse, float partialTickTime) {
		this.drawDefaultBackground();
		// Background
		int topX = this.width / 2;
		int topY = this.height / 2;
		String health = dfShort.format(this.dog.getHealth());
		String healthMax = dfShort.format(this.dog.getMaxHealth());
		String healthRel = dfShort.format(this.dog.getHealthRelative() * 100);
		String healthState = health + "/" + healthMax + "(" + healthRel + "%)";
		String damageValue = dfShort.format(this.dog.getAIAttackDamage());
		String damageState = damageValue;
		String speedValue = dfShort.format(this.dog.getAIMoveSpeed());
		String speedState = speedValue;

		String tamedString = null;
		if (this.dog.isTamed()) {
			if (this.dog.getOwnerName().equals(this.player.getCommandSenderName()) || this.dog.getOwnerID().equals(this.player.getUniqueID().toString())) {
				tamedString = "Yes (You)";
			}
			else {
				tamedString = "Yes (" + StringUtils.abbreviate(this.dog.getOwnerName(), 22) + ")";
			}
		}

		String evoString = null;
		if (!this.dog.hasEvolved() && !this.dog.isChild() && this.dog.levels.getLevel() < Constants.maxLevel) {
			evoString = "Not at Alpha Level!";
		}
		else if (!this.dog.hasEvolved() && this.dog.isChild() && this.dog.levels.getLevel() < Constants.maxLevel) {
			evoString = "Too Young!";
		}
		else if (!this.dog.hasEvolved() && !this.dog.isChild() && this.dog.levels.getLevel() >= Constants.maxLevel) {
			evoString = "Ready!";
		}
		else if (this.dog.hasEvolved() && !this.dog.isChild()) {
			evoString = "Already Evolved!";
		}

		//@formatter:off
		this.fontRendererObj.drawString("New name:", topX - 100, topY + 38, 4210752);
		this.fontRendererObj.drawString("Level: " + this.dog.levels.getLevel(), topX-75, topY+75, 0xFF10F9);
		this.fontRendererObj.drawString("Points Left: " + this.dog.spendablePoints(), topX, topY+75, 0xFFFFFF);
		this.fontRendererObj.drawString("Health: " + healthState, topX+190, topY-170, 0xFFFFFF);
		this.fontRendererObj.drawString("Damage: " + damageState, topX+190, topY-160, 0xFFFFFF);
		this.fontRendererObj.drawString("Speed: " + speedState, topX+190, topY-150, 0xFFFFFF);
		this.fontRendererObj.drawString("Tamed: " + tamedString, topX+190, topY-140, 0xFFFFFF);
		this.fontRendererObj.drawString("State: " + evoString, topX+190, topY-130, 0xFFFFFF);
		this.fontRendererObj.drawString("Gender: ", topX+190, topY-120, 0xFFFFFF);
		//@formatter:on
		if (this.dog.getGender() == true) {
			mc.renderEngine.bindTexture(ResourceReference.male); // TODO
			this.drawModalRectWithCustomSizedTexture(topX + 230, topY - 120, 0, 0, 12, 12, 12, 12);
		}
		else {
			mc.renderEngine.bindTexture(ResourceReference.female);
			this.drawModalRectWithCustomSizedTexture(topX + 230, topY - 120, 0, 0, 12, 12, 12, 12);
		}

		if (this.dog.isOwner(this.player)) {
			this.fontRendererObj.drawString("Obey Others?", this.width - 76, topY + 55, 0xFFFFFF);
		}

		for (int i = 0; i < this.btnPerPages; ++i) {
			if ((this.currentPage * this.btnPerPages + i) >= TalentRegistry.getTalents().size()) {
				continue;
			}
			this.fontRendererObj.drawString(TalentRegistry.getTalent(this.currentPage * this.btnPerPages + i).getLocalisedName(), 50, 17 + i * 21, 0xFFFFFF);
		}

		for (GuiTextField field : this.textfieldList) {
			field.drawTextBox();
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		super.drawScreen(xMouse, yMouse, partialTickTime);
		RenderHelper.enableGUIStandardItemLighting();

		// Foreground

		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		for (int k = 0; k < this.buttonList.size(); ++k) {
			GuiButton button = (GuiButton) this.buttonList.get(k);
			if (button.mousePressed(this.mc, xMouse, yMouse)) {
				List list = new ArrayList();
				if (button.id >= 1 && button.id <= TalentRegistry.getTalents().size()) {
					ITalent talent = TalentRegistry.getTalent(button.id - 1);

					list.add(EnumChatFormatting.GREEN + talent.getLocalisedName());
					list.add("Level: " + this.dog.talents.getLevel(talent));
					list.add(EnumChatFormatting.GRAY + "--------------------------------");
					list.addAll(this.splitInto(talent.getLocalisedInfo(), 200, this.mc.fontRendererObj));
				}
				else if (button.id == -1) {
					list.add(EnumChatFormatting.ITALIC + "Previous Page");
				}
				else if (button.id == -2) {
					list.add(EnumChatFormatting.ITALIC + "Next Page");
				}
				else if (button.id == -6) {
					String str = StatCollector.translateToLocal("modeinfo." + button.displayString.toLowerCase());
					list.addAll(splitInto(str, 150, this.mc.fontRendererObj));
				}

				this.drawHoveringText(list, xMouse, yMouse, this.mc.fontRendererObj);
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id >= 1 && button.id <= TalentRegistry.getTalents().size()) {
			ITalent talent = TalentRegistry.getTalent(button.id - 1);
			int level = this.dog.talents.getLevel(talent);

			if (level < talent.getHighestLevel(this.dog) && this.dog.spendablePoints() >= talent.getCost(this.dog, level + 1)) {
				PacketHandler.sendToServer(new ZertumTalents(this.dog.getEntityId(), TalentRegistry.getTalent(button.id - 1).getKey()));
			}

		}
		else if (button.id == -1) {
			if (this.currentPage > 0) {
				this.currentPage -= 1;
				this.initGui();
			}
		}
		else if (button.id == -2) {
			if (this.currentPage + 1 < this.maxPages) {
				this.currentPage += 1;
				this.initGui();
			}
		}
		if (button.id == -5) {
			if (!this.dog.willObeyOthers()) {
				button.displayString = "true";
				PacketHandler.sendToServer(new ZertumObey(this.dog.getEntityId(), true));

			}
			else {
				button.displayString = "false";
				PacketHandler.sendToServer(new ZertumObey(this.dog.getEntityId(), false));
			}
		}

		if (button.id == -6) {
			int newMode = (dog.mode.getMode().ordinal() + 1) % EnumMode.values().length;
			EnumMode mode = EnumMode.values()[newMode];
			button.displayString = mode.modeName();
			PacketHandler.sendToServer(new ZertumMode(this.dog.getEntityId(), newMode));
		}
	}

	@Override
	public void updateScreen() {
		for (GuiTextField field : this.textfieldList) {
			field.updateCursorCounter();
		}
	}

	@Override
	public void mouseClicked(int xMouse, int yMouse, int mouseButton) throws IOException {
		super.mouseClicked(xMouse, yMouse, mouseButton);
		for (GuiTextField field : this.textfieldList) {
			field.mouseClicked(xMouse, yMouse, mouseButton);
		}
	}

	@Override
	public void keyTyped(char character, int keyId) {
		for (GuiTextField field : this.textfieldList) {
			field.textboxKeyTyped(character, keyId);
		}

		if (keyId == Keyboard.KEY_ESCAPE) {
			this.mc.thePlayer.closeScreen();
		}
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public List splitInto(String text, int maxLength, FontRenderer font) {
		List list = new ArrayList();

		String temp = "";
		String[] split = text.split(" ");

		for (int i = 0; i < split.length; ++i) {
			String str = split[i];
			int length = font.getStringWidth(temp + str);

			if (length > maxLength) {
				list.add(temp);
				temp = "";
			}

			temp += str + " ";

			if (i == split.length - 1) {
				list.add(temp);
			}
		}

		return list;
	}
}
