package common.zeroquest.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import common.zeroquest.ModEntities;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.helper.ChatHelper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCage extends Item{

	public ItemCage() {
		super();
		this.setCreativeTab(ZeroQuest.ZeroTab);
	}

	private NBTTagCompound animalCompound = new NBTTagCompound();

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey(ModEntities.tag)){
			par3List.add("Animal Stored : " + par1ItemStack.getTagCompound().getString(ModEntities.tag));
		}
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}


	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par1ItemStack.getTagCompound() != null && 
				par1ItemStack.getTagCompound().hasKey(ModEntities.tag)){
			if(!(par1ItemStack.getTagCompound().getString(ModEntities.tag).equals("none"))){
				//create an entity from String.
				Entity entity = EntityList.createEntityByName(par1ItemStack.getTagCompound().getString(ModEntities.tag), par3World);
				//remove that string so it doesnt slip in the new entity
				par1ItemStack.getTagCompound().removeTag(ModEntities.tag);
				FMLLog.getLogger().info("" + par1ItemStack.getTagCompound());
				if(entity != null){
					if(entity instanceof EntityAnimal){
						EntityAnimal storedAnimal = (EntityAnimal)entity;
						storedAnimal.readEntityFromNBT(par1ItemStack.getTagCompound());
	                    storedAnimal.setLocationAndAngles(par4, par5, par6, MathHelper.wrapAngleTo180_float(par3World.rand.nextFloat() * 360.0F), 0.0F);
	                    storedAnimal.rotationYawHead = storedAnimal.rotationYaw;
	                    storedAnimal.renderYawOffset = storedAnimal.rotationYaw;
	                    storedAnimal.onSpawnWithEgg((IEntityLivingData)null);
	                    storedAnimal.playLivingSound();
						if(!par2EntityPlayer.worldObj.isRemote)
							par3World.spawnEntityInWorld(entity);
						par1ItemStack.getTagCompound().setString(ModEntities.tag, "none");
						animalCompound = new NBTTagCompound();
						par1ItemStack.setTagCompound(animalCompound);
					}
				}
			}else{
				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage(ChatHelper.getChatComponent("The Animal Trap is empty ... "));
				animalCompound = new NBTTagCompound();
				par1ItemStack.setTagCompound(animalCompound);
			}
		}else{
			if(!par2EntityPlayer.worldObj.isRemote)
				par2EntityPlayer.addChatMessage(ChatHelper.getChatComponent("The Animal Trap is empty ... "));
			animalCompound = new NBTTagCompound();
			par1ItemStack.setTagCompound(animalCompound);
		}
		return true;
	}


	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
	{
		if(par1ItemStack.getTagCompound() == null)
			par1ItemStack.setTagCompound(animalCompound);
		if(par1ItemStack.getTagCompound().hasKey(ModEntities.tag) && (!par1ItemStack.getTagCompound().getString(ModEntities.tag).equals("none"))){
			if(!par2EntityPlayer.worldObj.isRemote)
				par2EntityPlayer.addChatMessage(ChatHelper.getChatComponent("The Animal Trap still has a " + par1ItemStack.getTagCompound().getString(ModEntities.tag) + " inside !"));
			return false;
		}else{
			if(par3EntityLivingBase instanceof EntityAnimal){
				NBTTagCompound nbt = new NBTTagCompound();


				// set nbt to the animals nbt
				par3EntityLivingBase.writeEntityToNBT(animalCompound);
				// sets our nbt to the animal nbt
				nbt = animalCompound;
				// add custom tag
				nbt.setString(ModEntities.tag, EntityList.getEntityString(par3EntityLivingBase));
				// set complete nbt to the item's stackTagCompound
				par1ItemStack.setTagCompound(nbt);
				par3EntityLivingBase.setDead();


				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage(ChatHelper.getChatComponent("Stored a " + EntityList.getEntityString(par3EntityLivingBase) + " in the Animal trap"));
				par2EntityPlayer.setCurrentItemOrArmor(0, par1ItemStack);
			}else{
				if(!par2EntityPlayer.worldObj.isRemote)
					par2EntityPlayer.addChatMessage(ChatHelper.getChatComponent("That's not an animal !"));
			}
		}
		return false;
	}
}
