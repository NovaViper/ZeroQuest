package common.zeroquest;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {
	// Examples on AchievementList//
	// Put Events in AchievementEvents\\
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
		NileStart = new Achievement("achievement.NileStart", "NileStart", 0, 0, ModItems.nileGrain, (Achievement) null).setIndependent().registerAchievement();
		TraveltoNillax = new Achievement("achievement.TraveltoNillax", "TraveltoNillax", 2, 1, ModBlocks.nillaxStone, NileStart).registerAchievement();
		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 4, -1, ModBlocks.nileWorktable, TraveltoNillax).registerAchievement();
		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 6, -1, ModItems.nileSword, buildNWorkBench).registerAchievement();
		ZertKill = new Achievement("achievement.ZertKill", "ZertKill", 8, -1, Items.bone, buildNileSword).registerAchievement();
		buildBone = new Achievement("achievement.buildBone", "buildBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerAchievement();
		ZertTame = new Achievement("achievement.ZertTame", "ZertTame", 0, -5, ModItems.dogTreat, buildBone).registerAchievement();
		MountUp = new Achievement("achievement.MountUp", "MountUp", -1, -3, Items.saddle, buildBone).registerAchievement();

		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[] {
				NileStart, ZertKill, buildBone, buildNileSword, buildNWorkBench, ZertTame, MountUp,
				TraveltoNillax }));
	}

	public static void loadDark() {
		DarkStart = new Achievement("achievement.DarkStart", "DarkStart", 0, 0, ModItems.darkGrain, (Achievement) null).setIndependent().registerAchievement();
		TraveltoDarkax = new Achievement("achievement.TraveltoDarkax", "TraveltoDarkax", 2, 1, ModBlocks.darkaxStone, DarkStart).registerAchievement();
		DragonSlayer = new Achievement("achievement.DragonSlayer", "DragonSlayer", 4, -1, Items.diamond_sword, TraveltoDarkax).setSpecial().registerAchievement();

		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[] {
				DarkStart, DragonSlayer, TraveltoDarkax }));
	}
}