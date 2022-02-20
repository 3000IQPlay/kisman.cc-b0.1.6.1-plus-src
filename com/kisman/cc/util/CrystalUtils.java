//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class CrystalUtils
{
    private static Minecraft mc;
    public static List<Block> valid;
    
    public static boolean canSeePos(final BlockPos pos) {
        return CrystalUtils.mc.world.rayTraceBlocks(new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.posY + CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ), new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), false, true, false) == null;
    }
    
    public static boolean CanPlaceCrystalIfObbyWasAtPos(final BlockPos pos) {
        final Minecraft mc = Minecraft.getMinecraft();
        final Block floor = mc.world.getBlockState(pos.add(0, 1, 0)).getBlock();
        final Block ceil = mc.world.getBlockState(pos.add(0, 2, 0)).getBlock();
        return floor == Blocks.AIR && ceil == Blocks.AIR && mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(pos.add(0, 1, 0))).isEmpty();
    }
    
    public static boolean isEntityMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.motionX > Double.longBitsToDouble(Double.doubleToLongBits(0.5327718501168097) ^ 0x7FE10C778D0F6544L) || entityLivingBase.motionY > Double.longBitsToDouble(Double.doubleToLongBits(0.07461435496686485) ^ 0x7FB319ED266512E7L) || entityLivingBase.motionZ > Double.longBitsToDouble(Double.doubleToLongBits(0.9006325807477794) ^ 0x7FECD1FB6B00C2E7L);
    }
    
    public static boolean canPlaceCrystal(final BlockPos pos) {
        final Minecraft mc = Minecraft.getMinecraft();
        final Block block = mc.world.getBlockState(pos).getBlock();
        if (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) {
            final Block floor = mc.world.getBlockState(pos.add(0, 1, 0)).getBlock();
            final Block ceil = mc.world.getBlockState(pos.add(0, 2, 0)).getBlock();
            if (floor == Blocks.AIR && ceil == Blocks.AIR && mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(pos.add(0, 1, 0))).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean canPlaceCrystal(final BlockPos pos, final boolean check, final boolean entity, final boolean multiPlace) {
        if (!CrystalUtils.mc.world.getBlockState(pos).getBlock().equals(Blocks.BEDROCK) && !CrystalUtils.mc.world.getBlockState(pos).getBlock().equals(Blocks.OBSIDIAN)) {
            return false;
        }
        if (!CrystalUtils.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !CrystalUtils.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
            return false;
        }
        final BlockPos boost = pos.add(0, 1, 0);
        return !entity || CrystalUtils.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB((double)boost.getX(), (double)boost.getY(), (double)boost.getZ(), (double)(boost.getX() + 1), (double)(boost.getY() + (check ? 2 : 1)), (double)(boost.getZ() + 1)), e -> !(e instanceof EntityEnderCrystal) || multiPlace).size() == 0;
    }
    
    public static BlockPos GetPlayerPosFloored(final EntityPlayer p_Player) {
        return new BlockPos(Math.floor(p_Player.posX), Math.floor(p_Player.posY), Math.floor(p_Player.posZ));
    }
    
    public static List<BlockPos> findCrystalBlocks(final EntityPlayer p_Player, final float p_Range) {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.create();
        positions.addAll((Collection)BlockInteractionHelper.getSphere(GetPlayerPosFloored(p_Player), p_Range, (int)p_Range, false, true, 0).stream().filter(CrystalUtils::canPlaceCrystal).collect(Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    public static List<BlockPos> getSphere(final EntityPlayer target, final float range, final boolean sphere, final boolean hollow) {
        final ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = target.getPosition().getX() - (int)range; x <= target.getPosition().getX() + range; ++x) {
            for (int z = target.getPosition().getZ() - (int)range; z <= target.getPosition().getZ() + range; ++z) {
                int n;
                for (int y = n = (sphere ? (target.getPosition().getY() - (int)range) : target.getPosition().getY()); y < target.getPosition().getY() + range; ++y) {
                    final double distance = (target.getPosition().getX() - x) * (target.getPosition().getX() - x) + (target.getPosition().getZ() - z) * (target.getPosition().getZ() - z) + (sphere ? ((target.getPosition().getY() - y) * (target.getPosition().getY() - y)) : 0);
                    if (distance < range * range && (!hollow || distance >= (range - Double.longBitsToDouble(Double.doubleToLongBits(638.4060856917202) ^ 0x7F73F33FA9DAEA7FL)) * (range - Double.longBitsToDouble(Double.doubleToLongBits(13.015128470890444) ^ 0x7FDA07BEEB3F6D07L)))) {
                        blocks.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }
    
    public static List<BlockPos> getSphere(final float radius, final boolean ignoreAir) {
        final ArrayList<BlockPos> sphere = new ArrayList<BlockPos>();
        final BlockPos pos = new BlockPos(CrystalUtils.mc.player.getPositionVector());
        final int posX = pos.getX();
        final int posY = pos.getY();
        final int posZ = pos.getZ();
        final int radiuss = (int)radius;
        for (int x = posX - radiuss; x <= posX + radius; ++x) {
            for (int z = posZ - radiuss; z <= posZ + radius; ++z) {
                for (int y = posY - radiuss; y < posY + radius; ++y) {
                    if ((posX - x) * (posX - x) + (posZ - z) * (posZ - z) + (posY - y) * (posY - y) < radius * radius) {
                        final BlockPos position = new BlockPos(x, y, z);
                        if (!ignoreAir || CrystalUtils.mc.world.getBlockState(position).getBlock() != Blocks.AIR) {
                            sphere.add(position);
                        }
                    }
                }
            }
        }
        return sphere;
    }
    
    public static List<BlockPos> getSphere(final float range, final boolean sphere, final boolean hollow) {
        final ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = CrystalUtils.mc.player.getPosition().getX() - (int)range; x <= CrystalUtils.mc.player.getPosition().getX() + range; ++x) {
            for (int z = CrystalUtils.mc.player.getPosition().getZ() - (int)range; z <= CrystalUtils.mc.player.getPosition().getZ() + range; ++z) {
                int n;
                for (int y = n = (sphere ? (CrystalUtils.mc.player.getPosition().getY() - (int)range) : CrystalUtils.mc.player.getPosition().getY()); y < CrystalUtils.mc.player.getPosition().getY() + range; ++y) {
                    final double distance = (CrystalUtils.mc.player.getPosition().getX() - x) * (CrystalUtils.mc.player.getPosition().getX() - x) + (CrystalUtils.mc.player.getPosition().getZ() - z) * (CrystalUtils.mc.player.getPosition().getZ() - z) + (sphere ? ((CrystalUtils.mc.player.getPosition().getY() - y) * (CrystalUtils.mc.player.getPosition().getY() - y)) : 0);
                    if (distance < range * range && (!hollow || distance >= (range - Double.longBitsToDouble(Double.doubleToLongBits(638.4060856917202) ^ 0x7F73F33FA9DAEA7FL)) * (range - Double.longBitsToDouble(Double.doubleToLongBits(13.015128470890444) ^ 0x7FDA07BEEB3F6D07L)))) {
                        blocks.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }
    
    public static float calculateDamage(final World world, final double posX, final double posY, final double posZ, final Entity entity, final boolean terrain) {
        return calculateDamage(world, posX, posY, posZ, entity, 0, terrain);
    }
    
    public static float calculateDamage(final World world, final double posX, final double posY, final double posZ, final Entity entity, final int interlopedAmount) {
        return calculateDamage(world, posX, posY, posZ, entity, interlopedAmount, false);
    }
    
    public static float calculateDamage(final World world, final double posX, final double posY, final double posZ, final Entity entity, final int interlopedAmount, final boolean terrain) {
        if (entity == CrystalUtils.mc.player && CrystalUtils.mc.player.capabilities.isCreativeMode) {
            return 0.0f;
        }
        final float doubleExplosionSize = 12.0f;
        double dist = entity.getDistance(posX, posY, posZ);
        if (dist > doubleExplosionSize) {
            return 0.0f;
        }
        if (interlopedAmount > 0) {
            final Vec3d l_Interloped = EntityUtil.getInterpolatedAmount(entity, interlopedAmount);
            dist = EntityUtil.getDistance(l_Interloped.x, l_Interloped.y, l_Interloped.z, posX, posY, posZ);
        }
        final double distancedsize = dist / doubleExplosionSize;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = 0.0;
        try {
            if (terrain) {
                blockDensity = getBlockDensity(vec3d, entity.getEntityBoundingBox());
            }
            else {
                blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
            }
        }
        catch (Exception ex) {}
        final double v = (1.0 - distancedsize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * doubleExplosionSize + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(world, damage), new Explosion(world, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }
    
    public static float getBlockDensity(final Vec3d vec, final AxisAlignedBB bb) {
        final double d0 = 1.0 / ((bb.maxX - bb.minX) * 2.0 + 1.0);
        final double d2 = 1.0 / ((bb.maxY - bb.minY) * 2.0 + 1.0);
        final double d3 = 1.0 / ((bb.maxZ - bb.minZ) * 2.0 + 1.0);
        final double d4 = (1.0 - Math.floor(1.0 / d0) * d0) / 2.0;
        final double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        float j2 = 0.0f;
        float k2 = 0.0f;
        for (float f = 0.0f; f <= 1.0f; f += (float)d0) {
            for (float f2 = 0.0f; f2 <= 1.0f; f2 += (float)d2) {
                for (float f3 = 0.0f; f3 <= 1.0f; f3 += (float)d3) {
                    final double d6 = bb.minX + (bb.maxX - bb.minX) * f;
                    final double d7 = bb.minY + (bb.maxY - bb.minY) * f2;
                    final double d8 = bb.minZ + (bb.maxZ - bb.minZ) * f3;
                    if (rayTraceBlocks(new Vec3d(d6 + d4, d7, d8 + d5), vec, false, true, false) == null) {
                        ++j2;
                    }
                    ++k2;
                }
            }
        }
        return j2 / k2;
    }
    
    public static RayTraceResult rayTraceBlocks(Vec3d vec31, final Vec3d vec32, final boolean stopOnLiquid, final boolean ignoreBlockWithoutBoundingBox, final boolean returnLastUncollidableBlock) {
        if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
            return null;
        }
        if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
            final int i = MathHelper.floor(vec32.x);
            final int j = MathHelper.floor(vec32.y);
            final int k = MathHelper.floor(vec32.z);
            int l = MathHelper.floor(vec31.x);
            int i2 = MathHelper.floor(vec31.y);
            int j2 = MathHelper.floor(vec31.z);
            BlockPos blockpos = new BlockPos(l, i2, j2);
            IBlockState iblockstate = CrystalUtils.mc.world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();
            if (!CrystalUtils.valid.contains(block)) {
                block = Blocks.AIR;
                iblockstate = Blocks.AIR.getBlockState().getBaseState();
            }
            if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox((IBlockAccess)CrystalUtils.mc.world, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid)) {
                final RayTraceResult raytraceresult = iblockstate.collisionRayTrace((World)CrystalUtils.mc.world, blockpos, vec31, vec32);
                if (raytraceresult != null) {
                    return raytraceresult;
                }
            }
            RayTraceResult raytraceresult2 = null;
            int k2 = 200;
            while (k2-- >= 0) {
                if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
                    return null;
                }
                if (l == i && i2 == j && j2 == k) {
                    return returnLastUncollidableBlock ? raytraceresult2 : null;
                }
                boolean flag2 = true;
                boolean flag3 = true;
                boolean flag4 = true;
                double d0 = 999.0;
                double d2 = 999.0;
                double d3 = 999.0;
                if (i > l) {
                    d0 = l + 1.0;
                }
                else if (i < l) {
                    d0 = l + 0.0;
                }
                else {
                    flag2 = false;
                }
                if (j > i2) {
                    d2 = i2 + 1.0;
                }
                else if (j < i2) {
                    d2 = i2 + 0.0;
                }
                else {
                    flag3 = false;
                }
                if (k > j2) {
                    d3 = j2 + 1.0;
                }
                else if (k < j2) {
                    d3 = j2 + 0.0;
                }
                else {
                    flag4 = false;
                }
                double d4 = 999.0;
                double d5 = 999.0;
                double d6 = 999.0;
                final double d7 = vec32.x - vec31.x;
                final double d8 = vec32.y - vec31.y;
                final double d9 = vec32.z - vec31.z;
                if (flag2) {
                    d4 = (d0 - vec31.x) / d7;
                }
                if (flag3) {
                    d5 = (d2 - vec31.y) / d8;
                }
                if (flag4) {
                    d6 = (d3 - vec31.z) / d9;
                }
                if (d4 == -0.0) {
                    d4 = -1.0E-4;
                }
                if (d5 == -0.0) {
                    d5 = -1.0E-4;
                }
                if (d6 == -0.0) {
                    d6 = -1.0E-4;
                }
                EnumFacing enumfacing;
                if (d4 < d5 && d4 < d6) {
                    enumfacing = ((i > l) ? EnumFacing.WEST : EnumFacing.EAST);
                    vec31 = new Vec3d(d0, vec31.y + d8 * d4, vec31.z + d9 * d4);
                }
                else if (d5 < d6) {
                    enumfacing = ((j > i2) ? EnumFacing.DOWN : EnumFacing.UP);
                    vec31 = new Vec3d(vec31.x + d7 * d5, d2, vec31.z + d9 * d5);
                }
                else {
                    enumfacing = ((k > j2) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                    vec31 = new Vec3d(vec31.x + d7 * d6, vec31.y + d8 * d6, d3);
                }
                l = MathHelper.floor(vec31.x) - ((enumfacing == EnumFacing.EAST) ? 1 : 0);
                i2 = MathHelper.floor(vec31.y) - ((enumfacing == EnumFacing.UP) ? 1 : 0);
                j2 = MathHelper.floor(vec31.z) - ((enumfacing == EnumFacing.SOUTH) ? 1 : 0);
                blockpos = new BlockPos(l, i2, j2);
                IBlockState iblockstate2 = CrystalUtils.mc.world.getBlockState(blockpos);
                Block block2 = iblockstate2.getBlock();
                if (!CrystalUtils.valid.contains(block2)) {
                    block2 = Blocks.AIR;
                    iblockstate2 = Blocks.AIR.getBlockState().getBaseState();
                }
                if (ignoreBlockWithoutBoundingBox && iblockstate2.getMaterial() != Material.PORTAL && iblockstate2.getCollisionBoundingBox((IBlockAccess)CrystalUtils.mc.world, blockpos) == Block.NULL_AABB) {
                    continue;
                }
                if (block2.canCollideCheck(iblockstate2, stopOnLiquid)) {
                    return iblockstate2.collisionRayTrace((World)CrystalUtils.mc.world, blockpos, vec31, vec32);
                }
                raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
            }
            return returnLastUncollidableBlock ? raytraceresult2 : null;
        }
        return null;
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
    
    public static float calculateDamage(final World world, final EntityEnderCrystal crystal, final Entity entity) {
        return calculateDamage(world, crystal.posX, crystal.posY, crystal.posZ, entity, 0);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean check) {
        return canPlaceCrystal(blockPos, check, true);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean check, final boolean entity) {
        if (CrystalUtils.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtils.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        final BlockPos boost = blockPos.add(0, 1, 0);
        return CrystalUtils.mc.world.getBlockState(boost).getBlock() == Blocks.AIR && CrystalUtils.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR && (!entity || CrystalUtils.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB((double)boost.getX(), (double)boost.getY(), (double)boost.getZ(), (double)(boost.getX() + 1), (double)(boost.getY() + (check ? 2 : 1)), (double)(boost.getZ() + 1)), e -> !(e instanceof EntityEnderCrystal)).size() == 0);
    }
    
    public static float calculateDamage(final World world, final BlockPos pos, final Entity entity) {
        return calculateDamage(world, pos.getX(), pos.getY(), pos.getZ(), entity, 0);
    }
    
    static {
        CrystalUtils.mc = Minecraft.getMinecraft();
        CrystalUtils.valid = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL);
    }
}
