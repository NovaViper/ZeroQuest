package common.zeroquest.item;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import common.zeroquest.lib.Constants;

public class ItemNileBow extends ItemBow
{
	public String elementType;
	
    public ItemNileBow(String type) 
    {
    	elementType = type;
    }
    
    @Override
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
    {
        ModelResourceLocation modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow", "inventory");

        if(stack.getItem() == this && player.getItemInUse() != null)
        {
            if(useRemaining >= 18)
            {
                modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_2", "inventory");
            }
            else if(useRemaining > 13)
            {
                modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_1", "inventory");
            }
            else if(useRemaining > 0)
            {
                modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_0", "inventory");
            }
        }
        return modelresourcelocation;
    }
}