//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.module.combat.*;
import net.minecraft.entity.*;
import com.kisman.cc.util.*;
import net.minecraft.util.math.*;

public class CityESP extends Module
{
    private Setting range;
    private Setting safe;
    private Setting unSafe;
    private Setting onlyIfCanPlaceCrystal;
    private Setting ignoreOwn;
    private ArrayList<Hole> holeBlocks;
    
    public CityESP() {
        super("CityESP", "CityESP", Category.RENDER);
        this.range = new Setting("Range", this, 50.0, 0.0, 100.0, true);
        this.safe = new Setting("Bebrock", this, false);
        this.unSafe = new Setting("Obby", this, true);
        this.onlyIfCanPlaceCrystal = new Setting("OnlyIfCanPlaceCrystal", this, true);
        this.ignoreOwn = new Setting("IgnoreOwn", this, false);
        this.holeBlocks = new ArrayList<Hole>();
        CityESP.setmgr.rSetting(this.range);
        CityESP.setmgr.rSetting(this.safe);
        CityESP.setmgr.rSetting(this.unSafe);
        CityESP.setmgr.rSetting(this.onlyIfCanPlaceCrystal);
        CityESP.setmgr.rSetting(this.ignoreOwn);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final EntityPlayer player : CityESP.mc.world.playerEntities) {
            if (CityESP.mc.player == player && this.ignoreOwn.getValBoolean()) {
                continue;
            }
            this.getBlock(player);
        }
        if (this.holeBlocks.isEmpty()) {
            return;
        }
        for (final Hole hole : this.holeBlocks) {
            hole.render();
        }
    }
    
    private void getBlock(final EntityPlayer player) {
        if (Surround.isInHole((Entity)player)) {
            boolean safe = true;
            if (Surround.isObsidianHole(player.getPosition()) && this.unSafe.getValBoolean()) {
                safe = false;
            }
            final BlockPos holePos = new BlockPos((double)Math.round(player.posX), (double)Math.round(player.posY), (double)Math.round(player.posZ));
            this.holeBlocks.add(new Hole(Vectors.X_PLUS, holePos, safe));
            this.holeBlocks.add(new Hole(Vectors.X_MINUS, holePos, safe));
            this.holeBlocks.add(new Hole(Vectors.Z_PLUS, holePos, safe));
            this.holeBlocks.add(new Hole(Vectors.Z_MINUS, holePos, safe));
        }
    }
    
    public class Hole
    {
        public Vectors vec;
        public BlockPos parentHolePos;
        public boolean safe;
        
        public Hole(final Vectors vec, final BlockPos parentHolePos, final boolean safe) {
            this.vec = vec;
            this.parentHolePos = parentHolePos;
            this.safe = safe;
        }
        
        public BlockPos getPos() {
            return this.parentHolePos.add(this.vec.vec);
        }
        
        public void render() {
            RenderUtil.drawBlockESP(this.getPos(), this.safe ? 0.0f : 1.0f, this.safe ? 1.0f : 0.0f, 0.0f);
        }
    }
    
    public enum Vectors
    {
        X_PLUS(new Vec3i(1, 0, 0)), 
        X_MINUS(new Vec3i(-1, 0, 0)), 
        Z_PLUS(new Vec3i(0, 0, 1)), 
        Z_MINUS(new Vec3i(0, 0, -1));
        
        public final Vec3i vec;
        
        private Vectors(final Vec3i vec) {
            this.vec = vec;
        }
    }
}
