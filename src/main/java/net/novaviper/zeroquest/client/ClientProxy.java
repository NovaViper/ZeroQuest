package net.novaviper.zeroquest.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.client.model.ModelJakan;
import net.novaviper.zeroquest.client.model.ModelKortor;
import net.novaviper.zeroquest.client.model.ModelKurr;
import net.novaviper.zeroquest.client.model.ModelRiggator;
import net.novaviper.zeroquest.client.model.ModelZertumStage1;
import net.novaviper.zeroquest.client.particle.EntityDarkPortalFX;
import net.novaviper.zeroquest.client.particle.EntityForisDustFX;
import net.novaviper.zeroquest.client.particle.EntityIceDustFX;
import net.novaviper.zeroquest.client.render.entity.RenderJakan;
import net.novaviper.zeroquest.client.render.entity.RenderKortor;
import net.novaviper.zeroquest.client.render.entity.RenderKurr;
import net.novaviper.zeroquest.client.render.entity.RenderProjectiles;
import net.novaviper.zeroquest.client.render.entity.RenderRiggator;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderDarkZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderDestroZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderForisZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderIceZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderMetalZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderRedZertum;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderZertum;
import net.novaviper.zeroquest.client.render.tile.RenderFoodBowl;
import net.novaviper.zeroquest.client.render.tile.RenderNileTable;
import net.novaviper.zeroquest.common.CommonProxy;
import net.novaviper.zeroquest.common.block.portal.BlockDarkFire;
import net.novaviper.zeroquest.common.block.portal.BlockNileFire;
import net.novaviper.zeroquest.common.entity.EntityCustomTameable;
import net.novaviper.zeroquest.common.entity.creature.EntityDarkZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityDestroZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityIceZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityJakan;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;
import net.novaviper.zeroquest.common.entity.creature.EntityMetalZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityRedZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityZertum;
import net.novaviper.zeroquest.common.entity.mob.EntityKurr;
import net.novaviper.zeroquest.common.entity.mob.EntityRiggator;
import net.novaviper.zeroquest.common.entity.projectile.EntityFlamingPoisonball;
import net.novaviper.zeroquest.common.entity.projectile.EntityGrenade;
import net.novaviper.zeroquest.common.entity.projectile.EntityIceball;
import net.novaviper.zeroquest.common.entity.projectile.EntityZertumBeam;
import net.novaviper.zeroquest.common.handlers.KeyStateHandler;
import net.novaviper.zeroquest.common.lib.Registers;
import net.novaviper.zeroquest.common.tileentity.TileEntityFoodBowl;
import net.novaviper.zeroquest.common.tileentity.TileEntityNileWorkbench;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderThings() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		Registers.addEntityRender(EntityZertum.class, new RenderZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityRedZertum.class, new RenderRedZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityDarkZertum.class, new RenderDarkZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityDestroZertum.class, new RenderDestroZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityIceZertum.class, new RenderIceZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityForisZertum.class, new RenderForisZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityMetalZertum.class, new RenderMetalZertum(renderManager, new ModelZertumStage1(), 0.5F));
		Registers.addEntityRender(EntityJakan.class, new RenderJakan(renderManager, new ModelJakan(), 1.0F));
		Registers.addEntityRender(EntityKurr.class, new RenderKurr(renderManager, new ModelKurr(), 1.0F));
		Registers.addEntityRender(EntityKortor.class, new RenderKortor(renderManager, new ModelKortor(), 1.0F));
		Registers.addEntityRender(EntityRiggator.class, new RenderRiggator(renderManager, new ModelRiggator(), 1.0F));

		Registers.addEntityRender(EntityFlamingPoisonball.class, new RenderProjectiles(renderManager, ModItems.FPoisonball, renderItem));
		Registers.addEntityRender(EntityIceball.class, new RenderProjectiles(renderManager, ModItems.iceBall, renderItem));
		Registers.addEntityRender(EntityGrenade.class, new RenderProjectiles(renderManager, ModItems.nileGrenade, renderItem));
		Registers.addEntityRender(EntityZertumBeam.class, new RenderProjectiles(renderManager, Items.snowball, renderItem));

		Registers.bindTileEntitySpecialRenderer(TileEntityNileWorkbench.class, new RenderNileTable());
		Registers.bindTileEntitySpecialRenderer(TileEntityFoodBowl.class, new RenderFoodBowl());
	}

	@Override
	public void registerMoreThings() {

		Registers.addKeyBinding(KeyStateHandler.come);
		Registers.addKeyBinding(KeyStateHandler.stay);
		Registers.addKeyBinding(KeyStateHandler.ok);
		Registers.addKeyBinding(KeyStateHandler.heel);
		Registers.addFMLCommonEventBus(new KeyStateHandler());
	}

	@Override
	public void registerStateMappings() {
		addStateMapperToIgnore(ModBlocks.nileFire, BlockNileFire.AGE);
		addStateMapperToIgnore(ModBlocks.nileFire, BlockFire.AGE);
	}

	@Override
	public void registerStateMappingsForDark() {
		addStateMapperToIgnore(ModBlocks.darkFire, BlockDarkFire.AGE);
		addStateMapperToIgnore(ModBlocks.darkFire, BlockFire.AGE);
	}

	// Client Objects\\
	@Override
	public EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	public void addStateMapperToIgnore(Block block, IProperty property) {
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).addPropertiesToIgnore(new IProperty[] { property }).build());
	}

	@Override
	public void spawnCrit(World world, Entity entity) {
		FMLClientHandler.instance().getClient().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
	}

	@Override
	public void spawnRoar(World world, Entity entity) {
		FMLClientHandler.instance().getClient().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.SPELL_WITCH);
		FMLClientHandler.instance().getClient().effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
	}

	public static void spawnForisParticle(EntityCustomTameable entity) {
		double d0 = entity.getRNG().nextGaussian() * 0.04D;
		double d1 = entity.getRNG().nextGaussian() * 0.04D;
		double d2 = entity.getRNG().nextGaussian() * 0.04D;
		EntityForisDustFX var20 = new EntityForisDustFX(entity.worldObj, entity.posX + entity.getRNG().nextFloat() * entity.width * 2.0F - entity.width, entity.posY + 0.5D + entity.getRNG().nextFloat() * entity.height, entity.posZ + entity.getRNG().nextFloat() * entity.width * 2.0F - entity.width, d0, d1, d2);

		for (int i = 0; i < 7; ++i) {
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	public static void spawnIceParticle(Entity entity) {
		EntityIceDustFX var20 = new EntityIceDustFX(entity.worldObj, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
	}

	public static void spawnDarkParticle(World worldIn, double d0, double d1, double d2, double d3, double d4, double d5) {
		EntityDarkPortalFX entityFX = new EntityDarkPortalFX(worldIn, d0, d1, d2, d3, d4, d5);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
	}
}