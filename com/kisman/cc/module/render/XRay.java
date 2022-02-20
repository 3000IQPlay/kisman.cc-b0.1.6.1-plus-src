//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import net.minecraft.block.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.init.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.util.*;

public class XRay extends Module
{
    public static XRay instance;
    public Block[] xrayBlocks;
    private final Colour[] blocksColor;
    public Setting range;
    public Setting coal;
    public Setting iron;
    public Setting gold;
    public Setting redstone;
    public Setting lapis;
    public Setting diamond;
    public Setting emerald;
    
    public XRay() {
        super("XRay", "", Category.RENDER);
        this.xrayBlocks = new Block[] { Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE };
        this.blocksColor = new Colour[] { new Colour(0.0f, 0.0f, 0.0f), new Colour(0.99f, 0.52f, 0.01f), new Colour(0.99f, 0.75f, 0.01f), new Colour(0.99f, 0.01f, 0.01f), new Colour(0.01f, 0.11f, 0.99f), new Colour(0.01f, 0.56f, 0.99f), new Colour(0.01f, 0.99f, 0.69f) };
        this.range = new Setting("Range", this, 50.0, 0.0, 100.0, false);
        this.coal = new Setting("Coal", this, false);
        this.iron = new Setting("Iron", this, false);
        this.gold = new Setting("Gold", this, false);
        this.redstone = new Setting("Redstone", this, false);
        this.lapis = new Setting("Lapis", this, false);
        this.diamond = new Setting("Diamond", this, false);
        this.emerald = new Setting("Emerald", this, false);
        XRay.instance = this;
        XRay.setmgr.rSetting(this.range);
        XRay.setmgr.rSetting(this.coal);
        XRay.setmgr.rSetting(this.iron);
        XRay.setmgr.rSetting(this.gold);
        XRay.setmgr.rSetting(this.redstone);
        XRay.setmgr.rSetting(this.lapis);
        XRay.setmgr.rSetting(this.diamond);
        XRay.setmgr.rSetting(this.emerald);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final BlockPos pos : BlockInteractionHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.range.getValDouble(), this.range.getValInt(), false, true, 0)) {
            for (int i = 0; i < this.xrayBlocks.length; ++i) {
                this.renderBlock(pos, i);
            }
        }
    }
    
    private void renderBlock(final BlockPos pos, final int i) {
        if (i == 0 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[0] && this.coal.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[0]);
        }
        else if (i == 1 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[1] && this.iron.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[1]);
        }
        else if (i == 2 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[2] && this.gold.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[2]);
        }
        else if (i == 3 && (XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[3] || XRay.mc.world.getBlockState(pos).getBlock() == Blocks.LIT_REDSTONE_ORE) && this.redstone.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[3]);
        }
        else if (i == 4 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[4] && this.lapis.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[4]);
        }
        else if (i == 5 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[5] && this.diamond.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[5]);
        }
        else if (i == 6 && XRay.mc.world.getBlockState(pos).getBlock() == this.xrayBlocks[6] && this.emerald.getValBoolean()) {
            this.drawBlockESP(pos, this.blocksColor[6]);
        }
    }
    
    private void drawBlockESP(final BlockPos pos, final Colour color) {
        RenderUtil.drawBlockESP(pos, color.r1, color.g1, color.b1);
    }
}
