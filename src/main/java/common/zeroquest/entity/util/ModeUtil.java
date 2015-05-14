package common.zeroquest.entity.util;

import net.minecraft.nbt.NBTTagCompound;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.DataValues;

/**
 * @author ProPercivalalb
 **/
public class ModeUtil {

	public EntityZertumEntity dog;

	public ModeUtil(EntityZertumEntity dog) {
		this.dog = dog;
	}

	public EnumMode getMode() {
		return EnumMode.values()[this.getModeID()];
	}

	public EnumMode getMode(int index) {
		return EnumMode.values()[index];
	}

	public static int getId(EnumMode mode) {
		return mode.ordinal();
	}

	public boolean isMode(EnumMode mode) {
		return this.getMode() == mode;
	}

	public void setMode(EnumMode mode) {
		this.setMode(this.getId(mode));
	}

	public void setMode(int mode) {
		this.dog.getDataWatcher().updateObject(DataValues.dogMode, mode);
	}

	protected int getModeID() {
		return this.dog.getDataWatcher().getWatchableObjectInt(DataValues.dogMode);
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		tagCompound.setInteger("mode", this.getModeID());
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		this.dog.getDataWatcher().updateObject(DataValues.dogMode, tagCompound.getInteger("mode"));
	}

	public enum EnumMode {

		DOCILE("Docile", "[D]"), WANDERING("Wandering", "[W]"), AGGRESIVE("Aggresive", "[A]"), BERSERKER("Berserker", "[B]"), TACTICAL("Tactical", "[T]");

		private String tip;
		private String name;

		private EnumMode(String name, String tip) {
			this.name = name;
			this.tip = tip;
		}

		public String getTip() {
			return tip;
		}

		public String modeName() {
			return name;
		}
	}
}
