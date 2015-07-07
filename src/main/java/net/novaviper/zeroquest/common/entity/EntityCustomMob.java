package net.novaviper.zeroquest.common.entity;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityCustomMob extends EntityMob
{
    public int rare;

    public EntityCustomMob(World p_i1738_1_)
    {
        super(p_i1738_1_);
    }

    /**
     * Checks if this entity is running on a client.
     * 
     * Required since MCP's isClientWorld returns the exact opposite...
     * 
     * @return true if the entity runs on a client or false if it runs on a server
     */
    public boolean isClient() {
        return worldObj.isRemote;
    }
    
    
    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getPitch() {
    	if(!this.isChild())
    		return super.getSoundPitch() * -2;
    	else{
    		return super.getSoundPitch() * 2;
    		}
    	}
    
    /**
     * Checks if this entity is running on a server.
     * 
     * @return true if the entity runs on a server or false if it runs on a client
     */
    public boolean isServer() {
        return !worldObj.isRemote;
    }
}