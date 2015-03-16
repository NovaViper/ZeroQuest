package common.zeroquest.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import common.zeroquest.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public abstract class ITalent {
	
	public void onClassCreation(EntityZertumEntity dog) {}
	public void writeToNBT(EntityZertumEntity dog, NBTTagCompound tagCompound) {}
	public void readFromNBT(EntityZertumEntity dog, NBTTagCompound tagCompound) {}
	public boolean interactWithPlayer(EntityZertumEntity dog, EntityPlayer player) { return false; }
	public void onUpdate(EntityZertumEntity dog) {}
	public void onLivingUpdate(EntityZertumEntity dog) {}
	public int onHungerTick(EntityZertumEntity dog, int totalInTick) { return totalInTick; }
	public int onRegenerationTick(EntityZertumEntity dog, int totalInTick) { return totalInTick; }
	public int attackEntityAsMob(EntityZertumEntity dog, Entity entity, int damage) { return damage; }
	public int changeFoodValue(EntityZertumEntity dog, ItemStack stack, int foodValue) { return foodValue; }
	public boolean isPostionApplicable(EntityZertumEntity dog, PotionEffect potionEffect) { return true; }
	public double addToMoveSpeed(EntityZertumEntity dog) { return 0.0D; }
	public boolean canBreatheUnderwater(EntityZertumEntity dog) { return false; }
	public boolean isImmuneToFalls(EntityZertumEntity dog) { return false; }
	public int fallProtection(EntityZertumEntity dog) { return 0; }
	public boolean attackEntityFrom(EntityZertumEntity dog, DamageSource damageSource, float damage) { return true; }
	public boolean shouldDamageMob(EntityZertumEntity dog, Entity entity) { return true; }
	public String getLivingSound(EntityZertumEntity dog) { return ""; }
	public boolean canAttackClass(EntityZertumEntity dog, Class entityClass) { return false; }
	public boolean canAttackEntity(EntityZertumEntity dog, Entity entity) { return false; }
	public boolean setFire(EntityZertumEntity dog, int amount) { return true; }
	
	public int getHighestLevel(EntityZertumEntity dog) { return 5; }
	//public int getTotalCost(EntityDog dog) { return 15; }
	
	public int getCumulativeCost(EntityZertumEntity dog, int level) {
		switch(level) {
        case 1: return 1;
		case 2: return 2;
        case 3: return 4;
        case 4: return 6;
        case 5: return 8;
        default: return 0;
        }
	}
	
	public int getCost(EntityZertumEntity dog, int level) {
		return level;
	}
	
	public String getLocalisedName() {
		return StatCollector.translateToLocal("gui.talentname." + this.getKey());
	}
	
	public String getLocalisedInfo() {
		return StatCollector.translateToLocal("gui.talentinfo." + this.getKey());
	}
	
	/**
	 * If you can try and keep the key as short as possible because
	 * it has to be sent to the client and server constantly so to 
	 * avoid packet size kept as small as possible
	 * @return The key that can be used to look up this talent
	 */
	public abstract String getKey();
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ITalent)
			return ((ITalent)obj).getKey().equals(this.getKey());
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getKey().hashCode();
	}
}
