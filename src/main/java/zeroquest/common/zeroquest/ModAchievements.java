package common.zeroquest;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

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
   		NileStart = new Achievement("achievement.NileStart", "NileStart", 0, 0, ModItems.nileGrain, (Achievement)null).setIndependent().func_180788_c();
   		TraveltoNillax = new Achievement("achievement.TraveltoNillax", "TraveltoNillax", 2, 1, ModBlocks.nillaxStone, NileStart).func_180788_c();
   		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 4, -1, ModBlocks.nileWorktable, TraveltoNillax).func_180788_c();
   		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 6, -1, ModItems.nileSword, buildNWorkBench).func_180788_c();
   		ZertKill = new Achievement("achievement.ZertKill", "ZertKill", 8, -1, Items.bone, buildNileSword).func_180788_c();
   		buildBone = new Achievement("achievement.buildBone", "buildBone", 2, -3, ModItems.nileBone, buildNWorkBench).func_180788_c();
   		ZertTame = new Achievement("achievement.ZertTame", "ZertTame", 0, -5, ModItems.dogTreat, buildBone).func_180788_c();
   		MountUp = new Achievement("achievement.MountUp", "MountUp", -1, -3, Items.saddle, buildBone).func_180788_c();
   		
		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[]{NileStart, ZertKill, buildBone, buildNileSword, buildNWorkBench, ZertTame, MountUp, TraveltoNillax}));
	}
	
	public static void loadDark(){
   		DarkStart = new Achievement("achievement.DarkStart", "DarkStart", 0, 0, ModItems.darkGrain, (Achievement)null).setIndependent().func_180788_c();
   		TraveltoDarkax = new Achievement("achievement.TraveltoDarkax", "TraveltoDarkax", 2, 1, ModBlocks.darkaxStone, DarkStart).func_180788_c();
   		DragonSlayer = new Achievement("achievement.DragonSlayer", "DragonSlayer", 4, -1, Items.diamond_sword, TraveltoDarkax).setSpecial().func_180788_c();
 
   		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[]{DarkStart, DragonSlayer, TraveltoDarkax}));
	}
}