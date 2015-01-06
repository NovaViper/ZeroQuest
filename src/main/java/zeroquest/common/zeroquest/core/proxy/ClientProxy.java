package common.zeroquest.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.client.render.RenderDarkZertum;
import common.zeroquest.client.render.RenderDestroZertum;
import common.zeroquest.client.render.RenderFPoisonball;
import common.zeroquest.client.render.RenderForisZertum;
import common.zeroquest.client.render.RenderGrenade;
import common.zeroquest.client.render.RenderIceZertum;
import common.zeroquest.client.render.RenderIceball;
import common.zeroquest.client.render.RenderJakan;
import common.zeroquest.client.render.RenderKortor;
import common.zeroquest.client.render.RenderKurr;
import common.zeroquest.client.render.RenderRedZertum;
import common.zeroquest.client.render.RenderZertum;
import common.zeroquest.client.render.model.ModelDarkZertum;
import common.zeroquest.client.render.model.ModelDestroZertum;
import common.zeroquest.client.render.model.ModelForisZertum;
import common.zeroquest.client.render.model.ModelIceZertum;
import common.zeroquest.client.render.model.ModelJakan;
import common.zeroquest.client.render.model.ModelKortor;
import common.zeroquest.client.render.model.ModelKurr;
import common.zeroquest.client.render.model.ModelRedZertum;
import common.zeroquest.client.render.model.ModelZertum;
import common.zeroquest.client.render.renderer.RendererNileTable;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityForisZertum;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.tileentity.TileEntityNileWorkbench;

public class ClientProxy extends CommonProxy{
	
	
	public void registerRenderThings() {
	   	RenderingRegistry.registerEntityRenderingHandler(EntityZertum.class, new RenderZertum(Minecraft.getMinecraft().getRenderManager(), new ModelZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityRedZertum.class, new RenderRedZertum(Minecraft.getMinecraft().getRenderManager(), new ModelRedZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDarkZertum.class, new RenderDarkZertum(Minecraft.getMinecraft().getRenderManager(), new ModelDarkZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDestroZertum.class, new RenderDestroZertum(Minecraft.getMinecraft().getRenderManager(), new ModelDestroZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityJakan.class, new RenderJakan(Minecraft.getMinecraft().getRenderManager(), new ModelJakan(), 1.0F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityIceZertum.class, new RenderIceZertum(Minecraft.getMinecraft().getRenderManager(), new ModelIceZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityForisZertum.class, new RenderForisZertum(Minecraft.getMinecraft().getRenderManager(), new ModelForisZertum(), 0.5F));
	   	
	   	RenderingRegistry.registerEntityRenderingHandler(EntityKurr.class, new RenderKurr(Minecraft.getMinecraft().getRenderManager(), new ModelKurr(), 1.0F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityKortor.class, new RenderKortor(Minecraft.getMinecraft().getRenderManager(), new ModelKortor(), 1.0F));
	   	
	   	RenderingRegistry.registerEntityRenderingHandler(EntityFlamingPoisonball.class, new RenderFPoisonball(Minecraft.getMinecraft().getRenderManager(), ModItems.FPoisonball, Minecraft.getMinecraft().getRenderItem()));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityIceball.class, new RenderIceball(Minecraft.getMinecraft().getRenderManager(), Items.snowball, Minecraft.getMinecraft().getRenderItem()));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(Minecraft.getMinecraft().getRenderManager(), ModItems.nileGrenade, Minecraft.getMinecraft().getRenderItem()));
	   	
	   	TileEntitySpecialRenderer render = new RendererNileTable();
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNileWorkbench.class, render);
	}
}
