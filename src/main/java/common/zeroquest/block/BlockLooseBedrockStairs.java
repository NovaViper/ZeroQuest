package common.zeroquest.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockLooseBedrockStairs extends BlockStairs {
    /**
     * Initializes a new instance of the BlockStainedBrickStairs class.
     * 
      */
    public BlockLooseBedrockStairs(IBlockState modelState) {
        super(modelState);
        
        this.useNeighborBrightness = true;
    }
}
