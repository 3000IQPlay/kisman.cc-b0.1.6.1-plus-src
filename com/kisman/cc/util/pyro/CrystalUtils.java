//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.pyro;

import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import com.kisman.cc.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import net.minecraft.entity.item.*;

public class CrystalUtils
{
    private static Minecraft mc;
    
    public static boolean canPlaceCrystalIfObbyWasAtPos(final BlockPos pos) {
        final Block floor = CrystalUtils.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock();
        final Block ceil = CrystalUtils.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock();
        return floor == Blocks.AIR && ceil == Blocks.AIR && CrystalUtils.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(pos.add(0, 1, 0))).isEmpty();
    }
    
    private static boolean canPlaceCrystal(final Object pos) {
        final BlockPos _pos = (BlockPos)pos;
        final Block block = CrystalUtils.mc.world.getBlockState(_pos).getBlock();
        if (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) {
            final Block floor = CrystalUtils.mc.world.getBlockState(_pos.add(0, 1, 0)).getBlock();
            final Block ceil = CrystalUtils.mc.world.getBlockState(_pos.add(0, 2, 0)).getBlock();
            if (floor == Blocks.AIR && ceil == Blocks.AIR && CrystalUtils.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(_pos.add(0, 1, 0))).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public static BlockPos getPlayerPosFloored(final EntityPlayer p_Player) {
        return new BlockPos(Math.floor(p_Player.posX), Math.floor(p_Player.posY), Math.floor(p_Player.posZ));
    }
    
    public static List<BlockPos> findCrystalBlocks(final EntityPlayer p_Player, final float p_Range) {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.create();
        positions.addAll((Collection)BlockInteractionHelper.getSphere(getPlayerPosFloored(p_Player), p_Range, (int)p_Range, false, true, 0).stream().filter(CrystalUtils::canPlaceCrystal).collect(Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    public static float calculateDamage(final World p_World, final double posX, final double posY, final double posZ, final Entity entity, final int p_InterlopedAmount) {
        if (entity == CrystalUtils.mc.player && CrystalUtils.mc.player.capabilities.isCreativeMode) {
            return 0.0f;
        }
        final float doubleExplosionSize = 12.0f;
        double l_Distance = entity.getDistance(posX, posY, posZ);
        if (l_Distance > 12.0) {
            return 0.0f;
        }
        if (p_InterlopedAmount > 0) {
            final Vec3d l_Interloped = EntityUtil.getInterpolatedAmount(entity, (double)p_InterlopedAmount);
            l_Distance = EntityUtil.getDistance(l_Interloped.x, l_Interloped.y, l_Interloped.z, posX, posY, posZ);
        }
        final double distancedsize = l_Distance / 12.0;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        final double blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        final double v = (1.0 - distancedsize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * 12.0 + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(p_World, damage), new Explosion(p_World, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }
    
    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer)entity;
            final DamageSource ds = DamageSource.causeExplosionDamage(explosion);
            damage = CombatRules.getDamageAfterAbsorb(damage, (float)ep.getTotalArmorValue(), (float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            final int k = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
            final float f = MathHelper.clamp((float)k, 0.0f, 20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive(Potion.getPotionById(11))) {
                damage -= damage / 4.0f;
            }
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb(damage, (float)entity.getTotalArmorValue(), (float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return damage;
    }
    
    private static float getDamageMultiplied(final World p_World, final float damage) {
        final int diff = p_World.getDifficulty().getId();
        return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
    }
    
    public static float calculateDamage(final World p_World, final EntityEnderCrystal crystal, final Entity entity) {
        return calculateDamage(p_World, crystal.posX, crystal.posY, crystal.posZ, entity, 0);
    }
    
    static {
        CrystalUtils.mc = Minecraft.getMinecraft();
    }
}
