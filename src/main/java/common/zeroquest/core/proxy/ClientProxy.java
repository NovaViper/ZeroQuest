package common.zeroquest.core.proxy;

import net.minecraft.block.BlockFire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.block.portal.BlockDarkFire;
import common.zeroquest.block.portal.BlockNileFire;
import common.zeroquest.client.model.ModelJakan;
import common.zeroquest.client.model.ModelKortor;
import common.zeroquest.client.model.ModelKurr;
import common.zeroquest.client.model.ModelRiggator;
import common.zeroquest.client.model.ModelZertum;
import common.zeroquest.client.particle.EntityDarkPortalFX;
import common.zeroquest.client.particle.EntityForisDustFX;
import common.zeroquest.client.particle.EntityIceDustFX;
import common.zeroquest.client.render.entity.RenderJakan;
import common.zeroquest.client.render.entity.RenderKortor;
import common.zeroquest.client.render.entity.RenderKurr;
import common.zeroquest.client.render.entity.RenderProjectiles;
import common.zeroquest.client.render.entity.RenderRiggator;
import common.zeroquest.client.render.tileentity.RenderFoodBowl;
import common.zeroquest.client.render.tileentity.RenderNileTable;
import common.zeroquest.client.render.zertum.RenderDarkZertum;
import common.zeroquest.client.render.zertum.RenderDestroZertum;
import common.zeroquest.client.render.zertum.RenderForisZertum;
import common.zeroquest.client.render.zertum.RenderIceZertum;
import common.zeroquest.client.render.zertum.RenderMetalZertum;
import common.zeroquest.client.render.zertum.RenderRedZertum;
import common.zeroquest.client.render.zertum.RenderZertum;
import common.zeroquest.core.handlers.KeyStateHandler;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityRiggator;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.entity.projectile.EntityZertumBeam;
import common.zeroquest.entity.tileentity.TileEntityFoodBowl;
import common.zeroquest.entity.tileentity.TileEntityNileWorkbench;
import common.zeroquest.entity.zertum.EntityDarkZertum;
import common.zeroquest.entity.zertum.EntityDestroZertum;
import common.zeroquest.entity.zertum.EntityForisZertum;
import common.zeroquest.entity.zertum.EntityIceZertum;
import common.zeroquest.entity.zertum.EntityMetalZertum;
import common.zeroquest.entity.zertum.EntityRedZertum;
import common.zeroquest.entity.zertum.EntityZertum;
import common.zeroquest.lib.Registers;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderThings() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		Registers.registerEntityRender(EntityZertum.class, new RenderZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityRedZertum.class, new RenderRedZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityDarkZertum.class, new RenderDarkZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityDestroZertum.class, new RenderDestroZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityIceZertum.class, new RenderIceZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityForisZertum.class, new RenderForisZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityMetalZertum.class, new RenderMetalZertum(renderManager, new ModelZertum(), 0.5F));
		Registers.registerEntityRender(EntityJakan.class, new RenderJakan(renderManager, new ModelJakan(), 1.0F));
		Registers.registerEntityRender(EntityKurr.class, new RenderKurr(renderManager, new ModelKurr(), 1.0F));
		Registers.registerEntityRender(EntityKortor.class, new RenderKortor(renderManager, new ModelKortor(), 1.0F));
		Registers.registerEntityRender(EntityRiggator.class, new RenderRiggator(renderManager, new ModelRiggator(), 1.0F));

		Registers.registerEntityRender(EntityFlamingPoisonball.class, new RenderProjectiles(renderManager, ModItems.FPoisonball, renderItem));
		Registers.registerEntityRender(EntityIceball.class, new RenderProjectiles(renderManager, ModItems.iceBall, renderItem));
		Registers.registerEntityRender(EntityGrenade.class, new RenderProjectiles(renderManager, ModItems.nileGrenade, renderItem));
		Registers.registerEntityRender(EntityZertumBeam.class, new RenderProjectiles(renderManager, Items.snowball, renderItem));

		Registers.bindTileEntitySpecialRenderer(TileEntityNileWorkbench.class, new RenderNileTable());
		Registers.bindTileEntitySpecialRenderer(TileEntityFoodBowl.class, new RenderFoodBowl());
	}

	@Override
	public void registerMoreThings() {

		Registers.registerKeyBinding(KeyStateHandler.come);
		Registers.registerKeyBinding(KeyStateHandler.stay);
		Registers.registerKeyBinding(KeyStateHandler.ok);
		Registers.registerKeyBinding(KeyStateHandler.heel);
		Registers.registerFMLCommonEventBus(new KeyStateHandler());
	}

	@Override
	public void registerStateMappings() {
		Registers.addStateMapperToIgnore(ModBlocks.nileFire, BlockNileFire.AGE);
		Registers.addStateMapperToIgnore(ModBlocks.nileFire, BlockFire.AGE);
	}

	@Override
	public void registerStateMappingsForDark() {
		Registers.addStateMapperToIgnore(ModBlocks.darkFire, BlockDarkFire.AGE);
		Registers.addStateMapperToIgnore(ModBlocks.darkFire, BlockFire.AGE);
	}

	// Client Objects\\
	@Override
	public EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
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