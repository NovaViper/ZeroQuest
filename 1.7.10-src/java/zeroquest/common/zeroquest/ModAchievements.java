package common.zeroquest;

import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ModAchievements 
{
	//Examples on AchievementList//	
	public static AchievementPage ZeroQuestPage;
	public static Achievement ZQuestStart;
	public static Achievement ZertKill;
	public static Achievement buildBone;
	public static Achievement buildNileSword;
	public static Achievement buildNWorkBench;
	public static Achievement ZertTame;
	public static Achievement MountUp;
	public static Achievement DragonSlayer;
	
	public static AchievementPage DarkQuestPage;
	public static Achievement DarkStart;

	//Add Name and Desc in ClientProxy//
	public static void load() {   
   		ZQuestStart = new Achievement("achievement.ZQuestStart", "ZQuestStart", 0, 0, ModItems.nileGrain, (Achievement)null).initIndependentStat();
   		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 2, 0, ModBlocks.nileWorktable, ZQuestStart).registerStat();
   		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 5, 0, ModItems.nileSword, buildNWorkBench).registerStat();
   		ZertKill = new Achievement("achievement.ZertKill", "ZertKill", 7, 0, Items.bone, buildNileSword).registerStat();
   		buildBone = new Achievement("achievement.buildBone", "buildBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerStat().setSpecial();
   		ZertTame = new Achievement("achievement.ZertTame", "ZertTame", 2, -5, ModItems.dogTreat, buildBone).registerStat();
   		MountUp = new Achievement("achievement.MountUp", "MountUp", 5, -3, Items.saddle, buildBone).registerStat();
   		
		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[]{ZQuestStart, ZertKill, buildBone, buildNileSword, buildNWorkBench, ZertTame, MountUp}));
	}
	
	public static void loadDark(){
   		DarkStart = new Achievement("achievement.DarkStart", "DarkStart", 0, 0, ModItems.darkGrain, (Achievement)null).initIndependentStat();
   		DragonSlayer = new Achievement("achievement.DragonSlayer", "DragonSlayer", 2, 0, Items.diamond_sword, DarkStart).setSpecial().registerStat();
   		
   		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[]{DarkStart, DragonSlayer}));
	}
}