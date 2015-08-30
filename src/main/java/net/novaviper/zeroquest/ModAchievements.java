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
	public static Achievement skinningRiggator;
	public static Achievement buildNileBone;
	public static Achievement buildNileSword;
	public static Achievement buildNWorkBench;
	public static Achievement zertumTame;
	public static Achievement mountUp;
	public static Achievement alphaLevelUp;
	public static Achievement deltaLevelUp;

	public static AchievementPage DarkQuestPage;
	public static Achievement darkStart;
	public static Achievement travelToDarkax;
	public static Achievement buildDarkBone;
	public static Achievement buildDarkSword;
	public static Achievement darkZertumTame;
	public static Achievement dragonSlayer;

	public static void load() {
		nileStart = new Achievement("achievement.nileStart", "nileStart", 0, 0, ModItems.nileGrain, (Achievement) null).setIndependent().registerAchievement();
		travelToNillax = new Achievement("achievement.travelToNillax", "travelToNillax", 2, 1, ModBlocks.nillaxStone, nileStart).registerAchievement();
		buildNWorkBench = new Achievement("achievement.buildNWorkBench", "buildNWorkBench", 4, -1, ModBlocks.nileWorktable, travelToNillax).registerAchievement();
		buildNileSword = new Achievement("achievement.buildNileSword", "buildNileSword", 6, -1, ModItems.nileSword, buildNWorkBench).registerAchievement();
		zertumKill = new Achievement("achievement.zertumKill", "zertumKill", 8, -2, Items.bone, buildNileSword).registerAchievement();
		skinningRiggator = new Achievement("achievement.skinningRiggator", "skinningRiggator", 8, 0, ModItems.riggatorMeatRaw, buildNileSword).registerAchievement();
		buildNileBone = new Achievement("achievement.buildNileBone", "buildNileBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerAchievement();
		zertumTame = new Achievement("achievement.zertumTame", "zertumTame", 0, -5, ModItems.dogTreat, buildNileBone).registerAchievement();
		mountUp = new Achievement("achievement.mountUp", "mountUp", -1, -3, Items.saddle, buildNileBone).registerAchievement();
		alphaLevelUp = new Achievement("achievement.alphaLevelUp", "alphaLevelUp", -3, -5, ModItems.alphaBit, zertumTame).registerAchievement();
		deltaLevelUp = new Achievement("achievement.deltaLevelUp", "deltaLevelUp", -6, -5, ModItems.deltaBit, alphaLevelUp).registerAchievement().setSpecial();

		ZeroQuestPage.registerAchievementPage(new AchievementPage("Zero Quest", new Achievement[] {
				nileStart, zertumKill, skinningRiggator, buildNileBone, buildNileSword,
				buildNWorkBench, zertumTame, mountUp, travelToNillax, alphaLevelUp, deltaLevelUp }));
	}

	public static void loadDark() {
		darkStart = new Achievement("achievement.darkStart", "darkStart", 0, 0, ModItems.darkGrain, (Achievement) null).setIndependent().registerAchievement();
		travelToDarkax = new Achievement("achievement.travelToDarkax", "travelToDarkax", 2, 1, ModBlocks.darkaxStone, darkStart).registerAchievement();
		buildDarkSword = new Achievement("achievement.buildDarkSword", "buildDarkSword", 4, -1, ModItems.darkSword, travelToDarkax).registerAchievement();
		buildDarkBone = new Achievement("achievement.buildDarkBone", "buildDarkBone", 2, -3, ModItems.darkBone, travelToDarkax).registerAchievement();
		darkZertumTame = new Achievement("achievement.darkZertumTame", "darkZertumTame", 0, -5, ModItems.dogTreat, buildDarkBone).registerAchievement();
		dragonSlayer = new Achievement("achievement.dragonSlayer", "dragonSlayer", 6, -1, Items.diamond_sword, buildDarkSword).setSpecial().registerAchievement();

		DarkQuestPage.registerAchievementPage(new AchievementPage("Dark Quest", new Achievement[] {
				darkStart, dragonSlayer, travelToDarkax, buildDarkSword, buildDarkBone,
				darkZertumTame }));
	}
}