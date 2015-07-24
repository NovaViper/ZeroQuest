package net.novaviper.zeroquest.common.lib;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.api.registry.TalentRegistry;
import net.novaviper.zeroquest.common.handlers.BucketHandler;

public class Registers {

	public static final String tag = "EntityName";
	public static int startEntityId = 300;

	// Zero Quest Registers\\
	public static void addGuiHandler(Object mod, IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
	}

	public static void addWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	public static void addFuelHandler(IFuelHandler handler) {
		GameRegistry.registerFuelHandler(handler);
	}

	public static void addCommand(ICommand command) {
		MinecraftServer server = MinecraftServer.getServer();
		ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager();
		cmdman.registerCommand(command);
	}

	public static void addForgeEventBus(Object target) {
		MinecraftForge.EVENT_BUS.register(target);
	}

	public static void addFMLCommonEventBus(Object target) {
		FMLCommonHandler.instance().bus().register(target);
	}

	public static void addDimensionProviderType(int id, Class<? extends WorldProvider> provider, boolean keepLoaded) {
		DimensionManager.registerProviderType(id, provider, keepLoaded);
	}

	public static void addDimension(int id, int providerType) {
		DimensionManager.registerDimension(id, providerType);
	}

	// Block Registers\\
	public static void addBlock(Block block, String name) {
		GameRegistry.registerBlock(block, name);
	}

	public static void addBlockWithClass(Block block, Class itemClass, String name) {
		GameRegistry.registerBlock(block, itemClass, name);
	}

	public static void addBlockRender(Block block, int metadata, String blockString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(blockString, location));
	}

	// Entity Registers\\
	public static void addEntitySpawn(Class entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes) {
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
	}

	public static void addEntity(Class entityClass, String saveName, int id, Object mod) {
		EntityRegistry.registerModEntity(entityClass, saveName, id, mod, 120, 1, true);
	}

	public static void addEntityEgg(Class<? extends Entity> entity, int main, int spots) {
		int id = getUniqueEntityId();
		EntityList.idToClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, main, spots));
	}

	public static void addTileEntity(Class entityTileClass, String saveName) {
		GameRegistry.registerTileEntity(entityTileClass, saveName);
	}

	public static void addProjectileEntity(Class entityClass, String saveName, int id, Object mod) {
		EntityRegistry.registerModEntity(entityClass, saveName, id, mod, 128, 1, true);
	}

	public static int getUniqueEntityId() {
		do {
			startEntityId++;
		}
		while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	// Item Registers\\
	public static void addItem(Item item, String name) {
		GameRegistry.registerItem(item, name);
	}

	public static void addItemRender(Item item, int metadata, String itemString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(itemString, location));
	}

	public static void addItemVariant(Item item, String... names) {
		ModelBakery.addVariantName(item, names);
	}

	// Liquids Registers\\
	public static void putLiquidsInBuckets(Block liquid, Item liquidBucket) {
		BucketHandler.INSTANCE.buckets.put(liquid, liquidBucket);
	}

	// Talent Registers\\
	public static void registerTalent(ITalent talent) {
		TalentRegistry.registerTalent(talent);
	}

	// Fluid Registers\\
	public static void addFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
		FluidContainerRegistry.registerFluidContainer(stack, filledContainer, emptyContainer);
	}

	public static void addFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
	}

	// Recipes Registers\\
	public static void addSmelting(ItemStack input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void addShapedRecipe(ItemStack output, Object... params) {
		GameRegistry.addShapedRecipe(output, params);
	}

	public static void addShapelessRecipe(ItemStack output, Object... params) {
		GameRegistry.addShapelessRecipe(output, params);
	}

	public static void addRecipe(ItemStack output, Object... params) {
		GameRegistry.addRecipe(output, params);
	}

	// Client Registers\\
	public static void addKeyBinding(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

	public static void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileentity, TileEntitySpecialRenderer render) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileentity, render);
	}

	public static void addEntityRender(Class entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
}