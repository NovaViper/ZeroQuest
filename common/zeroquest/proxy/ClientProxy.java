package common.zeroquest.proxy;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.SoundManagerZQuest;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityDestroZertum;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityJakanPrime;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import common.zeroquest.entity.model.ModelDarkZertum;
import common.zeroquest.entity.model.ModelDestroZertum;
import common.zeroquest.entity.model.ModelJakan;
import common.zeroquest.entity.model.ModelJakanPrime;
import common.zeroquest.entity.model.ModelRedZertum;
import common.zeroquest.entity.model.ModelZertum;
import common.zeroquest.entity.render.RenderDarkZertum;
import common.zeroquest.entity.render.RenderDestroZertum;
import common.zeroquest.entity.render.RenderJakan;
import common.zeroquest.entity.render.RenderJakanPrime;
import common.zeroquest.entity.render.RenderRedZertum;
import common.zeroquest.entity.render.RenderZertum;
import common.zeroquest.handlers.JakanKeyHandler;
import common.zeroquest.handlers.JakanTickHandler;
import common.zeroquest.handlers.RemoteKeyPacketHandler;
import common.zeroquest.renderer.ItemRendererNileTable;
import common.zeroquest.renderer.RendererNileTable;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{

	public void registerChannels(){
        NetworkRegistry.instance().registerChannel(RemoteKeyPacketHandler.getInstance(), RemoteKeyPacketHandler.CHANNEL, Side.SERVER);
	}
	
	public void registerRenderThings() {
	   	RenderingRegistry.registerEntityRenderingHandler(EntityZertum.class, new RenderZertum(new ModelZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityRedZertum.class, new RenderRedZertum(new ModelRedZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDarkZertum.class, new RenderDarkZertum(new ModelDarkZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityDestroZertum.class, new RenderDestroZertum(new ModelDestroZertum(), 0.5F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityJakan.class, new RenderJakan(new ModelJakan(), 1.0F));
	   	RenderingRegistry.registerEntityRenderingHandler(EntityJakanPrime.class, new RenderJakanPrime(new ModelJakanPrime(), 1.0F));
	   	
	   	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNileTable.class, new RendererNileTable());
	   	MinecraftForgeClient.registerItemRenderer(ModBlocks.nileWorktable.blockID, new ItemRendererNileTable());
}
	
	public void registerAdvanced(){
        KeyBindingRegistry.registerKeyBinding(new JakanKeyHandler());
        TickRegistry.registerTickHandler(new JakanTickHandler(), Side.CLIENT);
	}
    
	public void reigsterClientLangugaes(){
		//Creative Tabs//
	   	LanguageRegistry.instance().addStringLocalization(ZeroQuest.ZeroTab.getTranslatedTabLabel(), "Zero Quest Tab");
	   	
	   	//Entities//
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.Zertum.name", "Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.RedZertum.name", "Red Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.DarkZertum.name", "Dark Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.DestroZertum.name", "Destro Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.Jakan.name", "Jakan");
	   	LanguageRegistry.instance().addStringLocalization("entity.Zero_Quest.JakanPrime.name", "Jakan Prime");
	   	
	   	//EntityEggs//
	   	LanguageRegistry.instance().addStringLocalization("entity.Zertum.name", "Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.RedZertum.name", "Red Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.DarkZertum.name", "Dark Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.DestroZertum.name", "Destro Zertum");
	   	LanguageRegistry.instance().addStringLocalization("entity.Jakan.name", "Jakan");
	   	LanguageRegistry.instance().addStringLocalization("entity.JakanPrime.name", "Jakan Prime");
	   	
	   	//Achievements
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZQuestStart.getName(), "Start Zero Quest");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZQuestStart.getDescription(), "Gather Grains, Dust, or Essence");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNWorkBench.getName(), "Nile Crafting");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNWorkBench.getDescription(), "Craft a Nile Crafting Table");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNileSword.getName(), "Nile Combat");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildNileSword.getDescription(), "Craft a Nile Sword");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertKill.getName(), "Death to the Savages!");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertKill.getDescription(), "Kill a Zertum");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildBone.getName(), "Nile Taming");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.buildBone.getDescription(), "Craft a Nile Bone");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertTame.getName(), "Zertum, My Pet!");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.ZertTame.getDescription(), "Tame a Zertum");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.MountUp.getName(), "Mount up, Ladies!");
	   	LanguageRegistry.instance().addStringLocalization(ModAchievements.MountUp.getDescription(), "Mount on a Jakan or Jakan Prime");
	}
	
	public void registerSound(){
		
		MinecraftForge.EVENT_BUS.register(new SoundManagerZQuest());
	}
	
    public void registerChestItems() {
        ChestGenHooks chestGenHooksDungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
        chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 70));
        // chance < saddle (1/16, ca. 6%, in max 8 slots -> 40% at least 1 egg, 0.48 eggs per chest): I think that's okay

        ChestGenHooks chestGenHooksMineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
        chestGenHooksMineshaft.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 5));
        // chance == gold ingot (1/18, ca. 6%, in 3-6 slots -> 23% at least 1 egg, 0.27 eggs per chest):
        // exploring a random mine shaft in creative mode yielded 2 eggs out of about 10 chests in 1 hour

        ChestGenHooks chestGenHooksJungleChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
        chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 15));
        // chance == gold ingot (15/81, ca. 18%, in 2-5 slots -> 51% at least 1 egg, 0.65 eggs per chest, 1.3 eggs per temple):
        // jungle temples are so rare, it should be rewarded

        ChestGenHooks chestGenHooksDesertChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
        chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.nileEssence), 1, 1, 10));
        // chance == iron ingot (10/76, ca. 13%, in 2-5 slots -> 39% at least 1 egg, 0.46 eggs per chest, 1.8 eggs per temple):
        // desert temples are so rare, it should be rewarded
    }

}
