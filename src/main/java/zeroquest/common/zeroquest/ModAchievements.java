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
	public static Achievement NileStart;
	public static Achievement TraveltoNillax;
	public static Achievement ZertKill;
	public static Achievement buildBone;
	public static Achievement buildNileSword;
	public static Achievement buildNWorkBench;
	public static Achievement ZertTame;
	public static Achievement MountUp;
	
	public static AchievementPage DarkQuestPage;
	public static Achievement DarkStart;
	public static Achievement TraveltoDarkax;
	public static Achievement DragonSlayer;

	public static void load() {
   		NileStart = new Achievement("achievement.NileStart", "NileStart", 0, 0, ModItems.nileGrain, (Achievement)null).initIndependentStat();
   		TraveltoNillax = new Achievement("achievement.TraveltoNillax", "TraveltoNillax", 2, 1, ModBlocks.nillaxStone, NileStart).registerStat();
   		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 4, -1, ModBlocks.nileWorktable, TraveltoNillax).registerStat();
   		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 6, -1, ModItems.nileSword, buildNWorkBench).registerStat();
   		ZertKill = new Achievement("achievement.ZertKill", "ZertKill", 8, -1, Items.bone, buildNileSword).registerStat();
   		buildBone = new Achievement("achievement.buildBone", "buildBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerStat().setSpecial();
   		ZertTame = new Achievement("achievement.ZertTame", "ZertTame", 0, -5, ModItems.dogTreat, buildBone).registerStat();
   		MountUp = new Achievement("achievement.MountUp", "MountUp", -1, -3, Items.saddle, buildBone).registerStat();
   		
		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[]{NileStart, ZertKill, buildBone, buildNileSword, buildNWorkBench, ZertTame, MountUp, TraveltoNillax}));
	}
	
	public static void loadDark(){
   		DarkStart = new Achievement("achievement.DarkStart", "DarkStart", 0, 0, ModItems.darkGrain, (Achievement)null).initIndependentStat();
   		TraveltoDarkax = new Achievement("achievement.TraveltoDarkax", "TraveltoDarkax", 2, 1, ModBlocks.darkaxStone, DarkStart).registerStat();
   		DragonSlayer = new Achievement("achievement.DragonSlayer", "DragonSlayer", 4, -1, Items.diamond_sword, TraveltoDarkax).setSpecial().registerStat();
 
   		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[]{DarkStart, DragonSlayer, TraveltoDarkax}));
	}
}