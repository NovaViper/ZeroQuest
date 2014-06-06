package common.zeroquest;

import common.zeroquest.entity.EntityZertum;
import common.zeroquest.handlers.CraftingHandler;
import common.zeroquest.handlers.PickupHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements 
{
	
	public static Achievement ZQuestStart;
	public static Achievement ZertKill;
	public static Achievement buildBone;
	public static Achievement buildNileSword;
	public static Achievement buildNWorkBench;
	public static Achievement ZertTame;
	public static Achievement TeamKill;
	public static Achievement MountUp;
	
	public static AchievementPage ZeroQuestPage;

	//Add Name and Desc in ClientProxy//
	public static void load() {   
   		ZQuestStart = new Achievement(150, "ZQuestStart", 0, 0, ModItems.nileGrain, null).registerAchievement();
   		buildNWorkBench = new Achievement(151, "buildNWorkBench", 2, 0, ModBlocks.nileWorktable, ZQuestStart).registerAchievement();
   		buildNileSword = new Achievement(152, "buildNileSword", 5, 0, ModItems.nileSword, buildNWorkBench).registerAchievement();
   		ZertKill = new Achievement(153, "ZertKill", 7, 0, Item.bone, buildNileSword).registerAchievement();
   		buildBone = new Achievement(154, "buildBone", 2, -3, ModItems.nileBone, buildNWorkBench).registerAchievement();
   		ZertTame = new Achievement(155, "ZertTame", 2, -5, ModItems.dogTreat, buildBone).registerAchievement();
   		MountUp = new Achievement(156, "MountUp", 5, -3, Item.saddle, buildBone).registerAchievement();
   		
   		ZeroQuestPage = new AchievementPage("Zero Quest", ZQuestStart, ZertKill, buildBone, buildNileSword, buildNWorkBench, ZertTame, MountUp);
   		AchievementPage.registerAchievementPage(ZeroQuestPage);
	}

	private void addAchievementName(String ach, String name)
	{
	LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
	}

	private void addAchievementDesc(String ach, String desc)
	{
	LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}

}
