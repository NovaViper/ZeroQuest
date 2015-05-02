package common.zeroquest;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.talent.*;

public class ModTalents {

	public static void loadTalents() {
		registerTalent(new BlastResist());
		registerTalent(new BodyMuncher());
		registerTalent(new CreeperSpotter());
		registerTalent(new CriticalClaw());
		registerTalent(new Dasher());
		registerTalent(new DeatheningRoar());
		registerTalent(new FishingZertum());
		registerTalent(new FlamingElemental());
		registerTalent(new FrigidFrost());
		registerTalent(new HardenedSkin());
		registerTalent(new Hunter());
		registerTalent(new LifeGiver());
		registerTalent(new LightFall());
		registerTalent(new Mount());
		registerTalent(new PestSlayer());
		registerTalent(new RapidRegeneration());
		registerTalent(new RoundUp());
		registerTalent(new SharpenedWeapons());
	}

	public static void registerTalent(ITalent talent) {
		TalentRegistry.registerTalent(talent);
	}

}