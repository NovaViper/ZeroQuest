package common.zeroquest;

import common.zeroquest.block.fluid.AcidFluid;
import common.zeroquest.block.fluid.BlockFluidAcid;
import common.zeroquest.block.fluid.BlockFluidNili;
import common.zeroquest.block.fluid.NiliFluid;
import common.zeroquest.core.handlers.BucketHandler;
import common.zeroquest.item.ItemAcidBucket;
import common.zeroquest.item.ItemNiliBucket;
import common.zeroquest.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModLiquids {

	public static Block acidBlock;
	public static Block niliBlock;

	public static Fluid fluidAcid;
	public static Fluid fluidNili;

	public static Item acidBucket;
	public static Item niliBucket;

	public static void load() {
		fluidAcid = new AcidFluid("acid").setBlock(acidBlock);
		registerFluid(fluidAcid);
		fluidNili = new NiliFluid("nili").setBlock(niliBlock);
		registerFluid(fluidNili);

		acidBlock = new BlockFluidAcid(fluidAcid, Material.lava).setUnlocalizedName("acid");
		registerWithClass(acidBlock, null, "acid");
		niliBlock = new BlockFluidNili(fluidNili, Material.water).setUnlocalizedName("nili");
		registerWithClass(niliBlock, null, "nili");

		acidBucket = new ItemAcidBucket(acidBlock).setUnlocalizedName("acidBucket").setContainerItem(Items.bucket);
		registerItem(acidBucket, "acid_Bucket");
		niliBucket = new ItemNiliBucket(niliBlock).setUnlocalizedName("niliBucket").setContainerItem(Items.bucket);
		registerItem(niliBucket, "nili_Bucket");

		registerFluidContainer(FluidRegistry.getFluidStack(fluidAcid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(acidBucket), new ItemStack(Items.bucket));
		registerFluidContainer(FluidRegistry.getFluidStack(fluidNili.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(niliBucket), new ItemStack(Items.bucket));

		putLiquidsInBuckets(acidBlock, acidBucket);
		putLiquidsInBuckets(niliBlock, niliBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	}

	public static void loadRenderers() {
		registerItemRender(acidBucket, 0, Constants.modid + ":" + "acid_bucket", "inventory");
		registerItemRender(niliBucket, 0, Constants.modid + ":" + "nili_bucket", "inventory");
	}

	public static void registerBlockRender(Block block, int metadata, String blockString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(blockString, location));
	}

	public static void registerItemRender(Item item, int metadata, String itemString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(itemString, location));
	}

	public static void putLiquidsInBuckets(Block liquid, Item liquidBucket) {
		BucketHandler.INSTANCE.buckets.put(liquid, liquidBucket);
	}

	public static void registerFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
		FluidContainerRegistry.registerFluidContainer(stack, filledContainer, emptyContainer);
	}

	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
	}

	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, name);
	}

	public static void registerWithClass(Block block, Class itemClass, String name) {
		GameRegistry.registerBlock(block, itemClass, name);
	}
}