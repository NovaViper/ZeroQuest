package net.novaviper.zeroquest.common.entity.util;

import net.minecraft.nbt.NBTTagCompound;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.lib.DataValues;

import com.google.common.base.Strings;

/**
 * @author ProPercivalalb
 */
public class LevelUtil {

	private final EntityZertumEntity dog;

	public LevelUtil(EntityZertumEntity dog) {
		this.dog = dog;
	}

	public void writeTalentsToNBT(NBTTagCompound tagCompound) {
		tagCompound.setString("levels", this.getSaveString());
	}

	public void readTalentsFromNBT(NBTTagCompound tagCompound) {
		if (tagCompound.hasKey("levels", 8)) {
			this.dog.getDataWatcher().updateObject(DataValues.levelData, tagCompound.getString("levels"));
		}
		else {
			this.dog.getDataWatcher().updateObject(DataValues.levelData, "0:0");
		}
	}

	public String getSaveString() {
		String saveString = this.dog.getDataWatcher().getWatchableObjectString(DataValues.levelData);
		return Strings.isNullOrEmpty(saveString) ? "0:0" : saveString;
	}

	public int getLevel() {
		return Integer.valueOf(this.getSaveString().split(":")[0]);
	}

	public void increaseLevel() {
		this.setLevel(this.getLevel() + 1);
		this.dog.updateEntityAttributes();
	}

	public void addLevel(int level) {
		this.setLevel(this.getLevel() + level);
		this.dog.updateEntityAttributes();
	}

	public void subtractLevel(int level) {
		this.setLevel(this.getLevel() - level);
		this.dog.updateEntityAttributes();
	}

	public void setLevel(int level) {
		this.dog.getDataWatcher().updateObject(DataValues.levelData, level + ":" + 0);
		this.dog.updateEntityAttributes();
	}

	public void resetLevel() {
		this.dog.getDataWatcher().updateObject(DataValues.levelData, "0:0");
	}
}
