package net.novaviper.zeroquest;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {
	// Examples on AchievementList//
	// Put Events in AchievementEvents\\
	public static AchievementPage ZeroQuestPage;
	public static Achievement nileStart;
	public static Achievement travelToNillax;
	public static Achievement zertumKill;
	public static Achievement buildNileBone;
	public static Achievement buildNileSword;
	public static Achievement buildNWorkBench;
	public static Achievement zertumTame;
	public static Achievement mountUp;

	public static AchievementPage DarkQuestPage;
	public static Achievement darkStart;
	public static Achievement travelToDarkax;
	public static Achievement dragonSlayer;

	public static void load() {
		nileStart = new Achievement("achievement.nileStart", "nileStart", 0, 0, ModItems.nileGrain, (Achievement) null).setIndependent().registerAchievement();
		travelToNillax = new Achievement("achievement.travelToNillax", "travelToNillax", 2, 1, ModBlocks.nillaxStone, nileStart).registerAchievement();
		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 4, -1, ModBlocks.nileWorktable, travelToNillax).registerAchievement();
		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 6, -1, ModItems.nileSword, buildNWorkBench).registerAchievement();
		zertumKill = new Achievement("achievement.zertumKill", "zertumKill", 8, -1, Items.bone, buildNileSword).registerAchievement();
		buildNileBone = new Achievement("achievement.buildNileBone", "buildNileBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerAchievement();
		zertumTame = new Achievement("achievement.zertumTame", "zertumTame", 0, -5, ModItems.dogTreat, buildNileBone).registerAchievement();
		mountUp = new Achievement("achievement.mountUp", "mountUp", -1, -3, Items.saddle, buildNileBone).registerAchievement();

		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[] {
				nileStart, zertumKill, buildNileBone, buildNileSword, buildNWorkBench, zertumTame,
				mountUp, travelToNillax, }));
	}

	public static void loadDark() {
		darkStart = new Achievement("achievement.darkStart", "darkStart", 0, 0, ModItems.darkGrain, (Achievement) null).setIndependent().registerAchievement();
		travelToDarkax = new Achievement("achievement.travelToDarkax", "travelToDarkax", 2, 1, ModBlocks.darkaxStone, darkStart).registerAchievement();
		dragonSlayer = new Achievement("achievement.dragonSlayer", "dragonSlayer", 4, -1, Items.diamond_sword, travelToDarkax).setSpecial().registerAchievement();

		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[] {
				darkStart, dragonSlayer, travelToDarkax }));
	}
}