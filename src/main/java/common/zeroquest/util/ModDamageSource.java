package common.zeroquest.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

//@formatter:off
public class ModDamageSource extends DamageSource
{
    
	public static DamageSource ice = new ModDamageSource("ice");
	
    protected ModDamageSource(String par1Str) {
		super(par1Str);
	}
    
    /** This kind of damage can be blocked or not. */
    private boolean isUnblockable;
    private boolean isDamageAllowedInCreativeMode;
    /** Whether or not the damage ignores modification by potion effects or enchantments. */
    private boolean damageIsAbsolute;
    private final float hungerDamage = 0.3F;
    /** This kind of damage is based on fire or not. */
    private boolean fireDamage;
    /** This kind of damage is based on a projectile or not. */
    private boolean projectile;
    /** Whether this damage source will have its damage amount scaled based on the current difficulty. */
    private boolean difficultyScaled;
    /** Whether the damage is magic based. */
    private boolean magicDamage;
    private boolean explosion;
    public String damageType;
    
    public static DamageSource causeIceDamage(Entity p_76356_0_, Entity p_76356_1_)
    {
		return (new EntityDamageSourceIndirect("ice", p_76356_0_, p_76356_1_)).setProjectile().setDamageBypassesArmor().setMagicDamage();
    }
    
    /**
     * returns EntityDamageSourceIndirect of an arrow
     */
    public static DamageSource causeArrowDamage(Entity arrow, Entity p_76353_1_)
    {
        return (new EntityDamageSourceIndirect("arrow", arrow, p_76353_1_)).setProjectile();
    }
}