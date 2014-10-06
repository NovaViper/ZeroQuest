package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelKortor;
import common.zeroquest.entity.EntityKortor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderKortor extends RenderLiving
{
	private static final ResourceLocation kortorTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor.png");
	private static final ResourceLocation kortorTamedTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_tamed.png");
	private static final ResourceLocation kortorAngryTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_angry.png");
	private static final ResourceLocation kortorSaddleTexture = new ResourceLocation(ZeroQuest.modid + ":" + "textures/entity/kortor/kortor_saddle.png");
    
    public RenderKortor(ModelKortor par1ModelKortor, float par3)
    {
        super(par1ModelKortor, par3);
        this.setRenderPassModel(par1ModelKortor);
    }
    
    protected int func_82447_a(EntityKortor par1EntityKortor, int par2, float par3)
    {
        float f1;
        
        if (par2 == 1 && par1EntityKortor.getSaddled())
        {
            this.bindTexture(kortorSaddleTexture);
            f1 = 1.0F;
            int j = par1EntityKortor.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation getTextures(EntityKortor par1EntityKortor)
    {
    	return par1EntityKortor.isTamed() ? kortorTamedTexture : (par1EntityKortor.isAngry() ? kortorAngryTexture : kortorTexture);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((EntityKortor)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getTextures((EntityKortor)par1Entity);
    }
}
