package common.zeroquest.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityKurr;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.model.ModelDarkZertum;
import common.zeroquest.entity.model.ModelDestroZertum;
import common.zeroquest.entity.model.ModelJakan;
import common.zeroquest.entity.model.ModelKortor;
import common.zeroquest.entity.model.ModelKurr;
import common.zeroquest.entity.model.ModelRedZertum;
import common.zeroquest.entity.model.ModelZertum;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.entity.projectile.EntityGrenade;
import common.zeroquest.entity.render.RenderDarkZertum;
import common.zeroquest.entity.render.RenderDestroZertum;
import common.zeroquest.entity.render.RenderFPoisonball;
import common.zeroquest.entity.render.RenderGrenade;
import common.zeroquest.entity.render.RenderJakan;
import common.zeroquest.entity.render.RenderKortor;
import common.zeroquest.entity.render.RenderKurr;
import common.zeroquest.entity.render.RenderRedZertum;
import common.zeroquest.entity.render.RenderZertum;
import common.zeroquest.renderer.ItemRendererNileTable;
import common.zeroquest.renderer.RendererNileTable;
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
