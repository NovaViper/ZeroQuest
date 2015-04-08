package common.zeroquest.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

import common.zeroquest.ModItems;
import common.zeroquest.client.particle.EntityForisDustFX;
import common.zeroquest.client.particle.EntityIceDustFX;
import common.zeroquest.client.renderer.entity.RenderBeam;
import common.zeroquest.client.renderer.entity.RenderDarkZertum;
import common.zeroquest.client.renderer.entity.RenderDestroZertum;
import common.zeroquest.client.renderer.entity.RenderFPoisonball;
import common.zeroquest.client.renderer.entity.RenderForisZertum;
import common.zeroquest.client.renderer.entity.RenderGrenade;
import common.zeroquest.client.renderer.entity.RenderIceZertum;
import common.zeroquest.client.renderer.entity.RenderIceball;
import common.zeroquest.client.renderer.entity.RenderJakan;
import common.zeroquest.client.renderer.entity.RenderKortor;
import common.zeroquest.client.renderer.entity.RenderKurr;
import common.zeroquest.client.renderer.entity.RenderMetalZertum;
import common.zeroquest.client.renderer.entity.RenderRedZertum;
import common.zeroquest.client.renderer.entity.RenderRiggator;
import common.zeroquest.client.renderer.entity.RenderZertum;
import common.zeroquest.client.renderer.entity.model.ModelJakan;
import common.zeroquest.client.renderer.entity.model.ModelKortor;
import common.zeroquest.client.renderer.entity.model.ModelKurr;
import common.zeroquest.client.renderer.entity.model.ModelRiggator;
import common.zeroquest.client.renderer.entity.model.ModelZertum;
import common.zeroquest.client.renderer.tileentity.RenderFoodBowl;
import common.zeroquest.client.renderer.tileentity.RenderNileTable;
import common.zeroquest.core.handlers.KeyStateHandler;
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityMetalZertum;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityRiggator;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.entity.tileentity.TileEntityFoodBowl;
import common.zeroquest.entity.tileentity.TileEntityNileWorkbench;
import common.zeroquest.entity.util.EntityDoggyBeam;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderThings() {
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		registerRender(EntityZertum.class, new RenderZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityRedZertum.class, new RenderRedZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityDarkZertum.class, new RenderDarkZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityDestroZertum.class, new RenderDestroZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityIceZertum.class, new RenderIceZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityForisZertum.class, new RenderForisZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityMetalZertum.class, new RenderMetalZertum(renderManager, new ModelZertum(), 0.5F));
		registerRender(EntityJakan.class, new RenderJakan(renderManager, new ModelJakan(), 1.0F));
		registerRender(EntityKurr.class, new RenderKurr(renderManager, new ModelKurr(), 1.0F));
		registerRender(EntityKortor.class, new RenderKortor(renderManager, new ModelKortor(), 1.0F));
		registerRender(EntityRiggator.class, new RenderRiggator(renderManager, new ModelRiggator(), 1.0F));

		registerRender(EntityDoggyBeam.class, new RenderBeam(renderManager, Items.snowball, renderItem));

		registerRender(EntityFlamingPoisonball.class, new RenderFPoisonball(renderManager, ModItems.FPoisonball, renderItem));
		registerRender(EntityIceball.class, new RenderIceball(renderManager, Items.snowball, renderItem));
		registerRender(EntityGrenade.class, new RenderGrenade(renderManager, ModItems.nileGrenade, renderItem));

		bindTileEntitySpecialRenderer(TileEntityNileWorkbench.class, new RenderNileTable());
		bindTileEntitySpecialRenderer(TileEntityFoodBowl.class, new RenderFoodBowl());

	}

	@Override
	public void registerMoreThings() {

		registerKeyBinding(KeyStateHandler.come);
		registerKeyBinding(KeyStateHandler.stay);
		registerKeyBinding(KeyStateHandler.ok);
		registerKeyBinding(KeyStateHandler.heel);

		registerFMLCommonEventBus(new KeyStateHandler());
	}

	// Registers\\
	public void registerFMLCommonEventBus(Object target) {
		FMLCommonHandler.instance().bus().register(target);
	}

	public void registerKeyBinding(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

	public void bindTileEntitySpecialRenderer(Class<? extends TileEntity> tileentity, TileEntitySpecialRenderer render) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileentity, render);
	}

	public void registerRender(Class entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}

	// Client Objects\\
	@Override
	public EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

	@Override
	public void spawnCrit(World world, Entity entity) {
		FMLClientHandler.instance().getClient().effectRenderer.func_178926_a(entity, EnumParticleTypes.CRIT);
	}

	@Override
	public void spawnRoar(World world, Entity entity) {
		FMLClientHandler.instance().getClient().effectRenderer.func_178926_a(entity, EnumParticleTypes.SPELL_MOB_AMBIENT);
		FMLClientHandler.instance().getClient().effectRenderer.func_178926_a(entity, EnumParticleTypes.CRIT);
	}

	public static void spawnForisParticle(EntityCustomTameable entity) {
		double d0 = entity.getRNG().nextGaussian() * 0.04D; // TODO
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
}