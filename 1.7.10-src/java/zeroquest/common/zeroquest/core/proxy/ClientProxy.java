package common.zeroquest.core.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.client.model.ModelDarkZertum;
import common.zeroquest.client.model.ModelDestroZertum;
import common.zeroquest.client.model.ModelJakan;
import common.zeroquest.client.model.ModelKortor;
import common.zeroquest.client.model.ModelKurr;
import common.zeroquest.client.model.ModelRedZertum;
import common.zeroquest.client.model.ModelZertum;
import common.zeroquest.client.render.RenderDarkZertum;
import common.zeroquest.client.render.RenderDestroZertum;
import common.zeroquest.client.render.RenderFPoisonball;
import common.zeroquest.client.render.RenderGrenade;
import common.zeroquest.client.render.RenderJakan;
import common.zeroquest.client.render.RenderKortor;
import common.zeroquest.client.render.RenderKurr;
import common.zeroquest.client.render.RenderRedZertum;
import common.zeroquest.client.render.RenderZertum;
import common.zeroquest.client.renderer.ItemRendererNileTable;
import common.zeroquest.client.renderer.RendererNileTable;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.tileentity.TileEntityNileTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	
	public void registerRenderThings() {
	   	RenderingRegistry.registerEntityRenderingHandler(EntityZertum.class, new RenderZertum(new ModelZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityRedZertum.class, new RenderRedZertum(new ModelRedZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDarkZertum.class, new RenderDarkZertum(new ModelDarkZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDestroZertum.class, new RenderDestroZertum(new ModelDestroZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityJakan.class, new RenderJakan(new ModelJakan(), 1.0F));
	   	//RenderingRegistry.registerEntityRenderingHandler(EntityJakanPrime.class, new RenderJakanPrime(new ModelJakanPrime(), 1.0F));
	   	
	   	RenderingRegistry.registerEntityRenderingHandler(EntityKurr.class, new RenderKurr(new ModelKurr(), 1.0F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityKortor.class, new RenderKortor(new ModelKortor(), 1.0F));
	   	
	   	RenderingRegistry.registerEntityRenderingHandler(EntityFlamingPoisonball.class, new RenderFPoisonball(1));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(ModItems.nileGrenade));
	   	TileEntitySpecialRenderer render = new RendererNileTable();
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNileTable.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.nileWorktable), new ItemRendererNileTable(render, new TileEntityNileTable()));
	}
}
