package net.novaviper.zeroquest;

import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.api.registry.TalentRegistry;
import net.novaviper.zeroquest.common.lib.Registers;
import net.novaviper.zeroquest.common.talent.*;

public class ModTalents {

	public static void loadTalents() {
		Registers.registerTalent(new BlastResist());
		Registers.registerTalent(new BodyMuncher());
		Registers.registerTalent(new CreeperSpotter());
		Registers.registerTalent(new CriticalClaw());
		Registers.registerTalent(new Dasher());
		Registers.registerTalent(new DeatheningRoar());
		Registers.registerTalent(new FishingZertum());
		Registers.registerTalent(new FlamingElemental());
		Registers.registerTalent(new FrigidFrost());
		Registers.registerTalent(new HardenedSkin());
		Registers.registerTalent(new Hunter());
		Registers.registerTalent(new LifeGiver());
		Registers.registerTalent(new LightFall());
		Registers.registerTalent(new Mount());
		Registers.registerTalent(new PestSlayer());
		Registers.registerTalent(new RapidRegeneration());
		Registers.registerTalent(new RoundUp());
		Registers.registerTalent(new SharpenedWeapons());
	}
}