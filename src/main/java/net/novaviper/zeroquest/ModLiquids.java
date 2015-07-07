package net.novaviper.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.novaviper.zeroquest.common.block.fluid.AcidFluid;
import net.novaviper.zeroquest.common.block.fluid.BlockFluidAcid;
import net.novaviper.zeroquest.common.block.fluid.BlockFluidNili;
import net.novaviper.zeroquest.common.block.fluid.NiliFluid;
import net.novaviper.zeroquest.common.handlers.BucketHandler;
import net.novaviper.zeroquest.common.item.ItemAcidBucket;
import net.novaviper.zeroquest.common.item.ItemNiliBucket;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Registers;

public class ModLiquids {

	public static Block acidBlock;
	public static Block niliBlock;

	public static Fluid fluidAcid;
	public static Fluid fluidNili;

	public static Item acidBucket;
	public static Item niliBucket;

	public static void load() {
		fluidAcid = new AcidFluid("acid").setBlock(acidBlock);
		Registers.addFluid(fluidAcid);
		fluidNili = new NiliFluid("nili").setBlock(niliBlock);
		Registers.addFluid(fluidNili);

		acidBlock = new BlockFluidAcid(fluidAcid, Material.lava).setUnlocalizedName("acid");
		Registers.addBlockWithClass(acidBlock, null, "acid");
		niliBlock = new BlockFluidNili(fluidNili, Material.water).setUnlocalizedName("nili");
		Registers.addBlockWithClass(niliBlock, null, "nili");

		acidBucket = new ItemAcidBucket(acidBlock).setUnlocalizedName("acidBucket").setContainerItem(Items.bucket);
		Registers.addItem(acidBucket, "acid_bucket");
		niliBucket = new ItemNiliBucket(niliBlock).setUnlocalizedName("niliBucket").setContainerItem(Items.bucket);
		Registers.addItem(niliBucket, "nili_bucket");

		Registers.addFluidContainer(FluidRegistry.getFluidStack(fluidAcid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(acidBucket), new ItemStack(Items.bucket));
		Registers.addFluidContainer(FluidRegistry.getFluidStack(fluidNili.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(niliBucket), new ItemStack(Items.bucket));

		Registers.putLiquidsInBuckets(acidBlock, acidBucket);
		Registers.putLiquidsInBuckets(niliBlock, niliBucket);
		Registers.addForgeEventBus(BucketHandler.INSTANCE);
	}

	public static void loadRenderers() {
		Registers.addItemRender(acidBucket, 0, Constants.modid + ":" + "acid_bucket", "inventory");
		Registers.addItemRender(niliBucket, 0, Constants.modid + ":" + "nili_bucket", "inventory");
	}

}