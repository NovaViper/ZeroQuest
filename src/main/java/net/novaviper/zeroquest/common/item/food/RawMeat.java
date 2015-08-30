package net.novaviper.zeroquest.common.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RawMeat extends Food {

	public RawMeat(int hunger, float saturation, boolean wolffood, CreativeTabs tab) {
		super(hunger, saturation, wolffood, tab);
		this.setCreativeTab(tab);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote && worldIn.rand.nextFloat() < 5f) {
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 150, 0));
		}
	}
}