package common.zeroquest;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraftforge.client.event.sound.PlayBackgroundMusicEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.FMLCommonHandler;

public class SoundManagerZQuest {
	
	public static String [] soundFiles = {
		"zero_quest:jakan/breathe1.ogg",
		"zero_quest:jakan/breathe2.ogg",
		"zero_quest:jakan/death.ogg",
		"zero_quest:jakan/whine.ogg",
		"zero_quest:jakan/roar.ogg",
		"zero_quest:jakan/growl1.ogg",
		"zero_quest:jakan/growl2.ogg",
		"zero_quest:jakan/step1.ogg",
		"zero_quest:jakan/step2.ogg",
		"zero_quest:jakan/step3.ogg",
		"zero_quest:jakan/step4.ogg" };

	@ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {
        // For each custom sound file we have defined in Sounds
        for (String soundFile : soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
            	event.manager.addSound(soundFile);
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "Zero Quest Failed loading sound file: " + soundFile);
            }
        }
	}
}