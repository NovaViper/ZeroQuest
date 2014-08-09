package common.zeroquest.events;

import common.zeroquest.ModBlocks;
import common.zeroquest.block.BlockNileSapling;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ZeroQuestEvents {
	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event)
	{
		if(event.world.getBlockId(event.X, event.Y, event.Z) == ModBlocks.nileSapling.blockID)
		{
			((BlockNileSapling)ModBlocks.nileSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
		}
	}

	
	/*@ForgeSubscribe
	public void WhenIFillMyBucket(FillBucketEvent event) {
		
	}*/

}
