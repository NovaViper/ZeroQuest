package common.zeroquest.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import common.zeroquest.ModItems;
import common.zeroquest.client.particle.EntityForisDustFX;
import common.zeroquest.client.particle.EntityIceDustFX;
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

public class ClientProxy extends CommonProxy{
	
	public void registerRenderThings() {
		Minecraft mc = Minecraft.getMinecraft();
		registerRender(EntityZertum.class, new RenderZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityRedZertum.class, new RenderRedZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityDarkZertum.class, new RenderDarkZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityDestroZertum.class, new RenderDestroZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityIceZertum.class, new RenderIceZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityForisZertum.class, new RenderForisZertum(mc.getRenderManager(), new ModelZertum(), 0.5F));
		registerRender(EntityMetalZertum.class, new RenderMetalZertum(mc.getRenderManager(), new ModelZertum(), 1.0F));
		registerRender(EntityJakan.class, new RenderJakan(mc.getRenderManager(), new ModelJakan(), 1.0F));
		registerRender(EntityKurr.class, new RenderKurr(mc.getRenderManager(), new ModelKurr(), 1.0F));
		registerRender(EntityKortor.class, new RenderKortor(mc.getRenderManager(), new ModelKortor(), 1.0F));
		registerRender(EntityRiggator.class, new RenderRiggator(mc.getRenderManager(), new ModelRiggator(), 1.0F));
		
		registerRender(EntityDoggyBeam.class, new RenderSnowball(mc.getRenderManager(), Items.snowball, mc.getRenderItem()));
		
		registerRender(EntityFlamingPoisonball.class, new RenderFPoisonball(mc.getRenderManager(), ModItems.FPoisonball, mc.getRenderItem()));
		registerRender(EntityIceball.class, new RenderIceball(mc.getRenderManager(), Items.snowball, mc.getRenderItem()));
		registerRender(EntityGrenade.class, new RenderGrenade(mc.getRenderManager(), ModItems.nileGrenade, mc.getRenderItem()));
	   	
	   	TileEntitySpecialRenderer render = new RenderNileTable();
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNileWorkbench.class, render);
	   	
	   	TileEntitySpecialRenderer render2 = new RenderFoodBowl();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoodBowl.class, render2);
		
	}
	
	public void registerMoreThings(){
		
		ClientRegistry.registerKeyBinding(KeyStateHandler.come);
		ClientRegistry.registerKeyBinding(KeyStateHandler.stay);
		ClientRegistry.registerKeyBinding(KeyStateHandler.ok);
		ClientRegistry.registerKeyBinding(KeyStateHandler.heel);
		
		FMLCommonHandler.instance().bus().register(new KeyStateHandler());		
	}
	
	public void registerRender(Class entityClass, Render render){
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
	
	public static void spawnForisParticle(EntityCustomTameable entity){
		double d0 = entity.getRNG().nextGaussian() * 0.04D; //TODO
		double d1 = entity.getRNG().nextGaussian() * 0.04D;
		double d2 = entity.getRNG().nextGaussian() * 0.04D;
		EntityForisDustFX var20 = new EntityForisDustFX(entity.worldObj, entity.posX + (double)(entity.getRNG().nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 0.5D + (double)(entity.getRNG().nextFloat() * entity.height), entity.posZ + (double)(entity.getRNG().nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2);
        
		for (int i = 0; i < 7; ++i)
        {
        		FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
        }
	}
	
	public static void spawnIceParticle(Entity entity){
    		EntityIceDustFX var20 = new EntityIceDustFX(entity.worldObj, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
    		FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
	}
	
	@Override
	public EntityPlayer getClientPlayer() {return FMLClientHandler.instance().getClientPlayerEntity();}
	
	@Override
	public void spawnCrit(World world, Entity entity) {
		FMLClientHandler.instance().getClient().effectRenderer.func_178926_a(entity, EnumParticleTypes.CRIT);
	}
	
}
