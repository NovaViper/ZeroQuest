package common.zeroquest;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.talent.BlastResist;
import common.zeroquest.talent.BodyMuncher;
import common.zeroquest.talent.CreeperSpotter;
import common.zeroquest.talent.CriticalClaw;
import common.zeroquest.talent.Dasher;
import common.zeroquest.talent.DeatheningRoar;
import common.zeroquest.talent.FireZertum;
import common.zeroquest.talent.FishingZertum;
import common.zeroquest.talent.GuardianAngel;
import common.zeroquest.talent.HardenedIce;
import common.zeroquest.talent.Hunter;
import common.zeroquest.talent.LifeGiver;
import common.zeroquest.talent.LightFall;
import common.zeroquest.talent.Mount;
import common.zeroquest.talent.PestSlayer;
import common.zeroquest.talent.RapidRegeneration;
import common.zeroquest.talent.RoundUp;
import common.zeroquest.talent.ScorchingFire;

public class ModTalents {

	public static void loadTalents() {
		registerTalent(new BlastResist());
		registerTalent(new BodyMuncher());
		registerTalent(new CreeperSpotter());
		registerTalent(new CriticalClaw());
		registerTalent(new Dasher());
		registerTalent(new DeatheningRoar());
		registerTalent(new FireZertum());
		registerTalent(new FishingZertum());
		registerTalent(new GuardianAngel());
		registerTalent(new HardenedIce());
		registerTalent(new Hunter());
		registerTalent(new LifeGiver());
		registerTalent(new LightFall());
		registerTalent(new Mount());
		registerTalent(new PestSlayer());
		registerTalent(new RapidRegeneration());
		registerTalent(new RoundUp());
		registerTalent(new RoundUp());
		registerTalent(new ScorchingFire());
	}

	public static void registerTalent(ITalent talent) {
		TalentRegistry.registerTalent(talent);
	}

}
