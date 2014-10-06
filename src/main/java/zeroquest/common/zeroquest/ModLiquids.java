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
import net.minecraftforge.fluids.FluidStack;
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
	public static Block niliBlock;
	
	public static Fluid fluidAcid;
	public static Fluid fluidNili;
	
	public static Item acidBucket;
	public static Item niliBucket;
	
	   public static void load(){
		   fluidAcid = new AcidFluid("acid").setBlock(acidBlock);
		   registerFluid(fluidAcid);
		   fluidNili = new NiliFluid("nili").setBlock(niliBlock);
		   registerFluid(fluidNili);

		   acidBlock = new BlockFluidAcid(fluidAcid, Material.lava).setBlockName("acid");
		   registerBlock(acidBlock, "acid");
		   niliBlock = new BlockFluidNili(fluidNili, Material.water).setBlockName("nili");
		   registerBlock(niliBlock, "nili");
		   
	    	acidBucket = new ItemAcidBucket(acidBlock).setUnlocalizedName("acidBucket").setContainerItem(Items.bucket).setTextureName(ZeroQuest.modid + ":" + "acidBucket");
	    	registerItem(acidBucket, "acid_Bucket"); 
	    	niliBucket = new ItemNiliBucket(niliBlock).setUnlocalizedName("niliBucket").setContainerItem(Items.bucket).setTextureName(ZeroQuest.modid + ":" + "niliBucket");
	    	registerItem(niliBucket, "nili_Bucket");
		   
	    	registerFluidContainer(FluidRegistry.getFluidStack(fluidAcid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(acidBucket), new ItemStack(Items.bucket));
	    	registerFluidContainer(FluidRegistry.getFluidStack(fluidNili.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(niliBucket), new ItemStack(Items.bucket));
		   
	       putLiquidsInBuckets(acidBlock, acidBucket);
	       putLiquidsInBuckets(niliBlock, niliBucket);
	       MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	   }
	   
	   public static void putLiquidsInBuckets(Block liquid, Item liquidBucket){
		   BucketHandler.INSTANCE.buckets.put(liquid, liquidBucket);
	   }
	   
	   public static void registerFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer){
	    	FluidContainerRegistry.registerFluidContainer(stack, filledContainer, emptyContainer);
	   }
	   
	   public static void registerFluid(Fluid fluid){
		   FluidRegistry.registerFluid(fluid);
	   }	   

	   
	   public static void registerItem(Item item, String name){
		   GameRegistry.registerItem(item, name);
	   }
	   
	   public static void registerBlock(Block block, String name){
		   GameRegistry.registerBlock(block, name);
	   }	
}