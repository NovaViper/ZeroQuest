package common.zeroquest.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.entity.tileentity.TileEntityFoodBowl;

/**
 * @author ProPercivalalb
 **/
public class BlockFoodBowl extends BlockContainer {

	public BlockFoodBowl() {
		super(Material.iron);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F - 0.0625F, 0.5F, 1.0F - 0.0625F);
	}

	private final Random rand = new Random();

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityFoodBowl();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullBlock() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		else {
			TileEntityFoodBowl tileentitydogfoodbowl = (TileEntityFoodBowl) worldIn.getTileEntity(pos);
			player.openGui(ZeroQuest.instance, CommonProxy.FoodBowl, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
		TileEntityFoodBowl foodBowl = (TileEntityFoodBowl) world.getTileEntity(pos);
		List list = null;
		list = world.getEntitiesWithinAABB(EntityZertumEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 0.5D, pos.getZ(), pos.getX() + 1, pos.getY() + 0.5D + 0.05000000074505806D, pos.getZ() + 1));

		if (list != null && list.size() > 0) {
			for (int l = 0; l < list.size(); l++) {
				EntityZertumEntity entitydtdoggy = (EntityZertumEntity) list.get(l);
				// TODO entitydtdoggy.saveposition.setBowlX(x);
				// TODO entitydtdoggy.saveposition.setBowlY(y);
				// TODO entitydtdoggy.saveposition.setBowlZ(z);
			}
		}

		if (entity instanceof EntityItem) {
			EntityItem entityItem = (EntityItem) entity;

			if (TileEntityHopper.func_145898_a(foodBowl, entityItem))
				world.playSoundEffect(pos.getX() + 0.5D, pos.getX() + 0.5D, pos.getZ() + 0.5D, "random.pop", 0.25F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

		}

		List list2 = null;
		list2 = world.getEntitiesWithinAABB(EntityZertumEntity.class, new AxisAlignedBB(pos.getX(), pos.getY() + 0.5D, pos.getZ(), pos.getX() + 1, pos.getY() + 0.5D + 0.05000000074505806D, pos.getZ() + 1));

		if (list2 != null && list2.size() > 0) {
			TileEntity tileentity1 = world.getTileEntity(pos);

			if (!(tileentity1 instanceof TileEntityFoodBowl)) {
				return;
			}

			TileEntityFoodBowl tileentitydogfoodbowl1 = (TileEntityFoodBowl) tileentity1;

			for (int j1 = 0; j1 < list2.size(); j1++) {
				EntityZertumEntity entitydtdoggy1 = (EntityZertumEntity) list2.get(j1);

				if (entitydtdoggy1.getDogHunger() <= 60 && tileentitydogfoodbowl1.getFirstDogFoodStack(entitydtdoggy1) >= 0) {
					tileentitydogfoodbowl1.feedDog(entitydtdoggy1, tileentitydogfoodbowl1.getFirstDogFoodStack(entitydtdoggy1), 1);
				}
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
	}

	public boolean canBlockStay(World world, BlockPos pos) {
		IBlockState blockstate = world.getBlockState(pos.down());
		return blockstate.getBlock().isSideSolid(world, pos.down(), EnumFacing.UP);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		dropInventory(worldIn, pos.getX(), pos.getY(), pos.getZ());
		super.breakBlock(worldIn, pos, state);
	}

	private void dropInventory(World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (!(tileEntity instanceof IInventory))
			return;
		IInventory inventory = (IInventory) tileEntity;
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			if (itemStack != null && itemStack.stackSize > 0) {
				float dX = rand.nextFloat() * 0.8F + 0.1F;
				float dY = rand.nextFloat() * 0.8F + 0.1F;
				float dZ = rand.nextFloat() * 0.8F + 0.1F;
				EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.getItem(), itemStack.stackSize, itemStack.getItemDamage()));
				if (itemStack.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
				}
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				itemStack.stackSize = 0;
			}
		}
	}
}
