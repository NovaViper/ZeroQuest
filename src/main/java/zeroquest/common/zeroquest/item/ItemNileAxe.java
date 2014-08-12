package common.zeroquest.item;

import java.util.Set;

import com.google.common.collect.Sets;

import common.zeroquest.ZeroQuest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemNileAxe extends ItemTool
{
    private static final Set field_150917_c = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});
    private static final String __OBFID = "CL_00001770";

    public ItemNileAxe(Item.ToolMaterial nileEssenceMaterial)
    {
        super(3.0F, nileEssenceMaterial, field_150917_c);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.getMaterial() != Material.wood && p_150893_2_.getMaterial() != Material.plants && p_150893_2_.getMaterial() != Material.vine ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    }
}