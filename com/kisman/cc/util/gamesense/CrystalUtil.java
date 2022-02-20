//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.gamesense;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import java.util.*;
import com.kisman.cc.util.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;

public class CrystalUtil
{
    private static final Minecraft mc;
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean newPlacement) {
        if (notValidBlock(CrystalUtil.mc.world.getBlockState(blockPos).getBlock())) {
            return false;
        }
        final BlockPos posUp = blockPos.up();
        if (newPlacement) {
            if (!CrystalUtil.mc.world.isAirBlock(posUp)) {
                return false;
            }
        }
        else if (notValidMaterial(CrystalUtil.mc.world.getBlockState(posUp).getMaterial()) || notValidMaterial(CrystalUtil.mc.world.getBlockState(posUp.up()).getMaterial())) {
            return false;
        }
        final AxisAlignedBB box = new AxisAlignedBB((double)posUp.getX(), (double)posUp.getY(), (double)posUp.getZ(), posUp.getX() + 1.0, posUp.getY() + 2.0, posUp.getZ() + 1.0);
        return CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, box, Entity::isEntityAlive).isEmpty();
    }
    
    public static boolean canPlaceCrystalExcludingCrystals(final BlockPos blockPos, final boolean newPlacement) {
        if (notValidBlock(CrystalUtil.mc.world.getBlockState(blockPos).getBlock())) {
            return false;
        }
        final BlockPos posUp = blockPos.up();
        if (newPlacement) {
            if (!CrystalUtil.mc.world.isAirBlock(posUp)) {
                return false;
            }
        }
        else if (notValidMaterial(CrystalUtil.mc.world.getBlockState(posUp).getMaterial()) || notValidMaterial(CrystalUtil.mc.world.getBlockState(posUp.up()).getMaterial())) {
            return false;
        }
        final AxisAlignedBB box = new AxisAlignedBB((double)posUp.getX(), (double)posUp.getY(), (double)posUp.getZ(), posUp.getX() + 1.0, posUp.getY() + 2.0, posUp.getZ() + 1.0);
        return CrystalUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, box, entity -> !entity.isDead && !(entity instanceof EntityEnderCrystal)).isEmpty();
    }
    
    public static boolean notValidBlock(final Block block) {
        return block != Blocks.BEDROCK && block != Blocks.OBSIDIAN;
    }
    
    public static boolean notValidMaterial(final Material material) {
        return material.isLiquid() || !material.isReplaceable();
    }
    
    public static List<BlockPos> findCrystalBlocks(final float placeRange, final boolean mode) {
        return (List<BlockPos>)EntityUtil.getSphere(PlayerUtil.getPlayerPos(), placeRange, (int)placeRange, false, true, 0).stream().filter(pos -> canPlaceCrystal(pos, mode)).collect(Collectors.toList());
    }
    
    public static List<BlockPos> findCrystalBlocksExcludingCrystals(final float placeRange, final boolean mode) {
        return (List<BlockPos>)EntityUtil.getSphere(PlayerUtil.getPlayerPos(), placeRange, (int)placeRange, false, true, 0).stream().filter(pos -> canPlaceCrystalExcludingCrystals(pos, mode)).collect(Collectors.toList());
    }
    
    public static void breakCrystal(final Entity crystal) {
        CrystalUtil.mc.playerController.attackEntity((EntityPlayer)CrystalUtil.mc.player, crystal);
        CrystalUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    public static void breakCrystalPacket(final Entity crystal) {
        CrystalUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(crystal));
        CrystalUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
