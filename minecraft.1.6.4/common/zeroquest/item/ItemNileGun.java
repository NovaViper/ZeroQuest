package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNileGun extends Item{

	public ItemNileGun(int par1){
		super(par1);
		setMaxDamage(100);
		maxStackSize = 1;
		setFull3D();
		setCreativeTab(ZeroQuest.ZeroTab);
}

public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.ghastTear.itemID))
        {
            float f = 2.0F;

            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
            /*{

            	protected void doBlockCollisions(){
            		super.doBlockCollisions();
            		int i = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
        			int j = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
        			int k = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
        			int l = MathHelper.floor_double(this.boundingBox.maxX - 0.001D);
        			int i1 = MathHelper.floor_double(this.boundingBox.maxY - 0.001D);
        			int j1 = MathHelper.floor_double(this.boundingBox.maxZ - 0.001D);
        			World world = this.worldObj;
        			Entity entitiy = (Entity)par3EntityPlayer;
                 	int j2 = this.worldObj.getBlockId(i, j, k);

                  				      if (j2 > 0)
                  					      {
                      				      //%collide%
                   				     }

            	}

            	};*/

                entityarrow.setIsCritical(false);
                entityarrow.setDamage(5.0);
                entityarrow.setKnockbackStrength(5);

                par1ItemStack.damageItem(1, par3EntityPlayer);
            	par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.ghastTear.itemID);
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(entityarrow);
            }
        }

        return par1ItemStack;
    }



    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
}