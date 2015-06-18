package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import common.zeroquest.block.fluid.AcidFluid;
import common.zeroquest.block.fluid.BlockFluidAcid;
import common.zeroquest.block.fluid.BlockFluidNili;
import common.zeroquest.block.fluid.NiliFluid;
import common.zeroquest.core.handlers.BucketHandler;
import common.zeroquest.item.ItemAcidBucket;
import common.zeroquest.item.ItemNiliBucket;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Registers;

public class ModLiquids {

	public static Block acidBlock;
	public static Block niliBlock;

	public static Fluid fluidAcid;
	public static Fluid fluidNili;

	public static Item acidBucket;
	public static Item niliBucket;

	public static void load() {
		fluidAcid = new AcidFluid("acid").setBlock(acidBlock);
		Registers.registerFluid(fluidAcid);
		fluidNili = new NiliFluid("nili").setBlock(niliBlock);
		Registers.registerFluid(fluidNili);

		acidBlock = new BlockFluidAcid(fluidAcid, Material.lava).setUnlocalizedName("acid");
		Registers.registerBlockWithClass(acidBlock, null, "acid");
		niliBlock = new BlockFluidNili(fluidNili, Material.water).setUnlocalizedName("nili");
		Registers.registerBlockWithClass(niliBlock, null, "nili");

		acidBucket = new ItemAcidBucket(acidBlock).setUnlocalizedName("acidBucket").setContainerItem(Items.bucket);
		Registers.registerItem(acidBucket, "acid_bucket");
		niliBucket = new ItemNiliBucket(niliBlock).setUnlocalizedName("niliBucket").setContainerItem(Items.bucket);
		Registers.registerItem(niliBucket, "nili_bucket");

		Registers.registerFluidContainer(FluidRegistry.getFluidStack(fluidAcid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(acidBucket), new ItemStack(Items.bucket));
		Registers.registerFluidContainer(FluidRegistry.getFluidStack(fluidNili.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(niliBucket), new ItemStack(Items.bucket));

		Registers.putLiquidsInBuckets(acidBlock, acidBucket);
		Registers.putLiquidsInBuckets(niliBlock, niliBucket);
		Registers.registerForgeEventBus(BucketHandler.INSTANCE);
	}

	public static void loadRenderers() {
		Registers.registerItemRender(acidBucket, 0, Constants.modid + ":" + "acid_bucket", "inventory");
		Registers.registerItemRender(niliBucket, 0, Constants.modid + ":" + "nili_bucket", "inventory");
	}

}