package common.zeroquest.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.world.World;

public class ParticleEffects
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static World theWorld = mc.theWorld;

	//Put custom particles in ParticleEffects (Get regular particles from RenderGlobal)//
	
	public static EntityFX spawnParticle(String particleName, double par2, double par4, double par6, double par8, double par10, double par12)
	{
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
		{
			int var14 = mc.gameSettings.particleSetting;

			if (var14 == 1 && theWorld.rand.nextInt(3) == 0)
			{
				var14 = 2;
			}

			double var15 = mc.renderViewEntity.posX - par2;
			double var17 = mc.renderViewEntity.posY - par4;
			double var19 = mc.renderViewEntity.posZ - par6;
			EntityFX var21 = null;
			double var22 = 16.0D;

			if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
			{
				return null;
			}
			else if (var14 > 1)
			{
				return null;
			}
			else
			{
				if (particleName.equals("bluedust"))
				{
					var21 = new EntityDustFX(theWorld, par2, par4, par6, (float)par8, (float)par10, (float)par12);
				}
				else if (particleName.equals("blackdust"))
				{
					var21 = new EntityDDustFX(theWorld, par2, par4, par6, (float)par8, (float)par10, (float)par12);
				}
				else if (particleName.equals("pinkdust"))
				{
					var21 = new EntityPDustFX(theWorld, par2, par4, par6, (float)par8, (float)par10, (float)par12);
				}
                else if (particleName.equals("acid"))
                {
                	var21 = new EntityAcidFX(theWorld, par2, par4, par6, (float)par8, (float)par10, (float)par12);
                }
                else if (particleName.equals("heart"))
                {
                	var21 = new EntityHeartFX(theWorld, par2, par4, par6, par8, par10, par12);
                }
                else if (particleName.equals("portal"))
                {
                    var21 = new EntityDarkPortalFX(theWorld, par2, par4, par6, par8, par10, par12);
                }
				

				mc.effectRenderer.addEffect((EntityFX)var21);
				return (EntityFX)var21;
			}
		}
		return null;
	}
}
