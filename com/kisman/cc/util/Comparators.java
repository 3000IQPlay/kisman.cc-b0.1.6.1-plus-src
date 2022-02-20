//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;

public class Comparators
{
    public static final EntityDistance entityDistance;
    public static final BlockDistance blockDistance;
    
    static {
        entityDistance = new EntityDistance();
        blockDistance = new BlockDistance();
    }
    
    private static class EntityDistance implements Comparator<Entity>
    {
        private static Minecraft mc;
        
        @Override
        public int compare(final Entity p1, final Entity p2) {
            final double one = Math.sqrt(EntityDistance.mc.player.getDistance(p1));
            final double two = Math.sqrt(EntityDistance.mc.player.getDistance(p2));
            return Double.compare(one, two);
        }
        
        static {
            EntityDistance.mc = Minecraft.getMinecraft();
        }
    }
    
    private static class BlockDistance implements Comparator<BlockPos>
    {
        private static Minecraft mc;
        
        @Override
        public int compare(final BlockPos pos1, final BlockPos pos2) {
            final double one = Math.sqrt(BlockDistance.mc.player.getDistanceSq(pos1.getX() + 0.5, pos1.getY() + 0.5, pos1.getZ() + 0.5));
            final double two = Math.sqrt(BlockDistance.mc.player.getDistanceSq(pos2.getX() + 0.5, pos2.getY() + 0.5, pos2.getZ() + 0.5));
            return Double.compare(one, two);
        }
        
        static {
            BlockDistance.mc = Minecraft.getMinecraft();
        }
    }
}
