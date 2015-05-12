package common.zeroquest.item;

import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemAcidBucket extends ItemBucket {

	private final Block liquidID;

	public ItemAcidBucket(Block liquidID) {
		super(liquidID);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setMaxStackSize(1);
		this.setContainerItem(Items.bucket);
		this.liquidID = liquidID;
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
