//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.pyro;

import net.minecraft.client.*;
import java.util.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class CrystalUtils2
{
    private static Minecraft mc;
    public static List<BlockPos> obbyRock;
    public static List<BlockPos> crystalBlocks;
    
    public static boolean canPlaceCrystalAt(final BlockPos blockpos, final IBlockState state) {
        final World worldIn = (World)CrystalUtils2.mc.world;
        final BlockPos blockpos2 = blockpos.up();
        final BlockPos blockpos3 = blockpos.up().up();
        boolean flag = !worldIn.isAirBlock(blockpos2) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable((IBlockAccess)worldIn, blockpos2);
        flag |= (!worldIn.isAirBlock(blockpos3) && !worldIn.getBlockState(blockpos3).getBlock().isReplaceable((IBlockAccess)worldIn, blockpos3));
        if (flag) {
            return false;
        }
        final double d0 = blockpos.getX();
        final double d2 = blockpos.getY();
        final double d3 = blockpos.getZ();
        return worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(d0, d2, d3, d0 + 1.0, d2 + 2.0, d3 + 1.0)).isEmpty();
    }
    
    static {
        CrystalUtils2.mc = Minecraft.getMinecraft();
    }
}
