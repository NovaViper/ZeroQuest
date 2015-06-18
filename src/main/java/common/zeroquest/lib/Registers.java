package common.zeroquest.lib;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
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
import net.minecraftforge.client.model.ModelLoader;
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

import common.zeroquest.ModEntities;
import common.zeroquest.ZeroQuest;
import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.core.handlers.BucketHandler;

public class Registers {

	// Zero Quest Registers\\
	public static void registerGuiHandler(Object mod, IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
	}

	public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	public static void registerFuelHandler(IFuelHandler handler) {
		GameRegistry.registerFuelHandler(handler);
	}

	public static void registerCommand(ICommand command) {
		MinecraftServer server = MinecraftServer.getServer();
		ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager();
		cmdman.registerCommand(command);
	}

	public static void registerForgeEventBus(Object target) {
		MinecraftForge.EVENT_BUS.register(target);
	}

	public static void registerFMLCommonEventBus(Object target) {
		FMLCommonHandler.instance().bus().register(target);
	}

	public static void registerProviderType(int id, Class<? extends WorldProvider> provider, boolean keepLoaded) {
		DimensionManager.registerProviderType(id, provider, keepLoaded);
	}

	public static void registerDimension(int id, int providerType) {
		DimensionManager.registerDimension(id, providerType);
	}

	// Block Registers\\
	public static void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, name);
	}

	public static void registerBlockWithClass(Block block, Class itemClass, String name) {
		GameRegistry.registerBlock(block, itemClass, name);
	}

	public static void registerBlockRender(Block block, int metadata, String blockString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(blockString, location));
	}

	// Entity Registers\\
	public static void addEntitySpawn(Class entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes) {
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
	}

	public static void registerEntity(Class entityClass, String saveName, int id) {
		EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 120, 1, true);
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int main, int spots) {
		int id = getUniqueEntityId();
		EntityList.idToClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, main, spots));
	}

	public static void registerTileEntity(Class entityTileClass, String saveName) {
		GameRegistry.registerTileEntity(entityTileClass, saveName);
	}

	public static void registerProjectileEntity(Class entityClass, String saveName, int id) {
		EntityRegistry.registerModEntity(entityClass, saveName, id, ZeroQuest.instance, 128, 1, true);
	}

	public static int getUniqueEntityId() {
		do {
			ModEntities.startEntityId++;
		}
		while (EntityList.getStringFromID(ModEntities.startEntityId) != null);

		return ModEntities.startEntityId;
	}

	// Item Registers\\
	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, name);
	}

	public static void registerItemRender(Item item, int metadata, String itemString, String location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(itemString, location));
	}

	public static void addItemVariant(Item item, String... names) {
		ModelBakery.addVariantName(item, names);
	}

	// Liquids Registers\\
	public static void putLiquidsInBuckets(Block liquid, Item liquidBucket) {
		BucketHandler.INSTANCE.buckets.put(liquid, liquidBucket);
	}

	public static void registerFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
		FluidContainerRegistry.registerFluidContainer(stack, filledContainer, emptyContainer);
	}

	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
	}

	// Talent Registers\\
	public static void registerTalent(ITalent talent) {
		TalentRegistry.registerTalent(talent);
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

	//Client Registers\\
	public static void registerKeyBinding(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

	public static void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileentity, TileEntitySpecialRenderer render) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileentity, render);
	}

	public static void registerEntityRender(Class entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}

	public static void addStateMapperToIgnore(Block block, IProperty property) {
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).addPropertiesToIgnore(new IProperty[] { property }).build());
	}
}