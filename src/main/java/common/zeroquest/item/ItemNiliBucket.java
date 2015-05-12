package common.zeroquest.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import common.zeroquest.ZeroQuest;

public class ItemNiliBucket extends ItemBucket {

	private final Block liquidID;

	public ItemNiliBucket(Block liquidID) {
		super(liquidID);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setMaxStackSize(1);
		this.setContainerItem(Items.bucket);
		this.liquidID = liquidID;
	}

	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_) {
		return EnumRarity.RARE;
	}

	@Override
	public boolean tryPlaceContainedLiquid(World world, BlockPos pos) {
		{
			if (this.liquidID == Blocks.air) {
				return false;
			}
			else {
				Material material = world.getBlockState(pos).getBlock().getMaterial();
				boolean flag = !material.isSolid();

				if (!world.isAirBlock(pos) && !flag) {
					return false;
				}
				else {
					if (!world.isRemote && flag && !material.isLiquid()) {
						world.destroyBlock(pos, true);
					}

					world.setBlockState(pos, this.liquidID.getDefaultState(), 3);
				}

				return true;
			}
		}
	}
}
