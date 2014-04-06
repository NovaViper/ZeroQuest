package common.zeroquest.item;

import java.util.List;








import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityZertum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
	
public class ItemWhistle extends Item
	{
	public ItemWhistle(int i)

	{
	super(i);
	maxStackSize = 1;
	setMaxDamage(20);
	this.setCreativeTab(ZeroQuest.ZeroTab);
	}

	public boolean hasEffect(ItemStack par1ItemStack) {

	return true;

	}
}