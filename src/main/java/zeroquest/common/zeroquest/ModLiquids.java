package common.zeroquest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import common.zeroquest.block.fluid.BlockFluidAcid;
import common.zeroquest.block.fluid.BlockFluidNili;
import common.zeroquest.events.BucketHandler;
import common.zeroquest.fluid.AcidFluid;
import common.zeroquest.fluid.NiliFluid;
import common.zeroquest.item.ItemAcidBucket;
import common.zeroquest.item.ItemNiliBucket;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModLiquids {
	
	public static Block acidBlock;
	public static Fluid fluidAcid;
	public static Block niliBlock;
	public static Fluid fluidNili;
	public static Item acidBucket;
	public static Item niliBucket;
	
	   public static void load(){
		   fluidAcid = new AcidFluid("acid").setBlock(acidBlock);
		   FluidRegistry.registerFluid(fluidAcid);
		   fluidNili = new NiliFluid("nili").setBlock(niliBlock);
		   FluidRegistry.registerFluid(fluidNili);

		   acidBlock = new BlockFluidAcid(fluidAcid, Material.lava).setBlockName("acid");
		   registerBlocks(acidBlock, "acid");
		   niliBlock = new BlockFluidNili(fluidNili, Material.water).setBlockName("nili");
		   registerBlocks(niliBlock, "nili");
		   
	    	acidBucket = new ItemAcidBucket(acidBlock).setUnlocalizedName("acidBucket").setContainerItem(Items.bucket).setTextureName(ZeroQuest.modid + ":" + "acidBucket");
	    	registerItem(acidBucket, "acid_Bucket"); //TODO
	    	niliBucket = new ItemNiliBucket(niliBlock).setUnlocalizedName("niliBucket").setContainerItem(Items.bucket).setTextureName(ZeroQuest.modid + ":" + "niliBucket");
	    	registerItem(niliBucket, "nili_Bucket"); //TODO
		   
	    	FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidAcid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(acidBucket), new ItemStack(Items.bucket));
	    	FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidNili.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(niliBucket), new ItemStack(Items.bucket));
		   
		   BucketHandler.INSTANCE.buckets.put(acidBlock, acidBucket);
		   BucketHandler.INSTANCE.buckets.put(niliBlock, niliBucket);
	       MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	   }
	   
	   public static void registerItem(Item item, String name){
		   GameRegistry.registerItem(item, name);
	   }
	   
	   public static void registerBlocks(Block block, String name){
		   GameRegistry.registerBlock(block, name);
	   }
	
}
