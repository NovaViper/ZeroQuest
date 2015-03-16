package common.zeroquest;

import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.talent.BlastResist;
import common.zeroquest.talent.BodyMuncher;
import common.zeroquest.talent.CreeperSpotter;
import common.zeroquest.talent.CriticalClaw;
import common.zeroquest.talent.Dasher;
import common.zeroquest.talent.FireZertum;
import common.zeroquest.talent.FishingZertum;
import common.zeroquest.talent.GuardianAngel;
import common.zeroquest.talent.Hunter;
import common.zeroquest.talent.LifeGiver;
import common.zeroquest.talent.LightFall;
import common.zeroquest.talent.Mount;
import common.zeroquest.talent.PestSlayer;
import common.zeroquest.talent.RapidRegeneration;
import common.zeroquest.talent.RoundUp;

public class ModTalents {
	
	public static void loadTalents(){
    	TalentRegistry.registerTalent(new BlastResist());
    	TalentRegistry.registerTalent(new BodyMuncher());
    	TalentRegistry.registerTalent(new CreeperSpotter());
    	TalentRegistry.registerTalent(new CriticalClaw());
    	TalentRegistry.registerTalent(new Dasher());
    	TalentRegistry.registerTalent(new FireZertum());
    	TalentRegistry.registerTalent(new FishingZertum());
    	TalentRegistry.registerTalent(new GuardianAngel());
    	TalentRegistry.registerTalent(new Hunter());
    	TalentRegistry.registerTalent(new LifeGiver());
    	TalentRegistry.registerTalent(new LightFall());
    	TalentRegistry.registerTalent(new Mount());
    	TalentRegistry.registerTalent(new PestSlayer());
    	TalentRegistry.registerTalent(new RapidRegeneration());
    	TalentRegistry.registerTalent(new RoundUp());
    	
	}

}
