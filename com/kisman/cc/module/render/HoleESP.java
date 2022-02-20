//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import java.util.concurrent.*;
import com.kisman.cc.module.*;
import com.google.common.collect.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import java.util.function.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.util.*;

public class HoleESP extends Module
{
    private Setting radius;
    private Setting ignoreOwnHole;
    private Setting flatOwn;
    private Setting slabHeight;
    private Setting width;
    private Setting type;
    private Setting mode;
    private Setting ufoAlpha;
    private Setting obby;
    private Setting obbyHoles;
    private Setting obbyColor;
    private Setting bedrock;
    private Setting bedrockHoles;
    private Setting bedrockColor;
    private Setting custom;
    private Setting customMode;
    private ConcurrentHashMap<AxisAlignedBB, Colour> holes;
    
    public HoleESP() {
        super("HoleESP", "HoleESP", Category.RENDER);
        this.radius = new Setting("Radius", this, 8.0, 0.0, 32.0, true);
        this.ignoreOwnHole = new Setting("IgnoreOwnHole", this, false);
        this.flatOwn = new Setting("FlatOwn", this, false);
        this.slabHeight = new Setting("SlabHeight", this, 0.5, 0.1, 1.5, false);
        this.width = new Setting("Width", this, 1.0, 1.0, 10.0, true);
        this.type = new Setting("Type", this, "Both", new ArrayList<String>(Arrays.asList("Outline", "Fill", "Both")));
        this.mode = new Setting("Mode", this, "Air", new ArrayList<String>(Arrays.asList("Air", "Ground", "Flat", "Slab", "Double")));
        this.ufoAlpha = new Setting("UFOAlpha", this, 255.0, 0.0, 255.0, true);
        this.obby = new Setting("_ObsidianHoles", this, "ObsidianHoles");
        this.obbyHoles = new Setting("ObsidianHoles", this, true);
        this.obbyColor = new Setting("ObbyColor", this, "ObbyColor", new float[] { 0.0f, 1.0f, 0.0f, 1.0f }, false);
        this.bedrock = new Setting("_BedrockHoles", this, "BedrockHoles");
        this.bedrockHoles = new Setting("BedrockHoles", this, true);
        this.bedrockColor = new Setting("BedrockColor", this, "BedrockColor", new float[] { 0.0f, 1.0f, 0.0f, 1.0f }, false);
        this.custom = new Setting("Custom", this, "CustomHoles");
        this.customMode = new Setting("CusomMode", this, "Single", new ArrayList<String>(Arrays.asList("Single", "Double", "Custom")));
        HoleESP.setmgr.rSetting(this.radius);
        HoleESP.setmgr.rSetting(this.ignoreOwnHole);
        HoleESP.setmgr.rSetting(this.flatOwn);
        HoleESP.setmgr.rSetting(this.slabHeight);
        HoleESP.setmgr.rSetting(this.width);
        HoleESP.setmgr.rSetting(this.type);
        HoleESP.setmgr.rSetting(this.mode);
        HoleESP.setmgr.rSetting(this.ufoAlpha);
        HoleESP.setmgr.rSetting(this.obby);
        HoleESP.setmgr.rSetting(this.obbyHoles);
        HoleESP.setmgr.rSetting(this.obbyColor);
        HoleESP.setmgr.rSetting(this.bedrock);
        HoleESP.setmgr.rSetting(this.bedrockHoles);
        HoleESP.setmgr.rSetting(this.bedrockColor);
        HoleESP.setmgr.rSetting(this.custom);
        HoleESP.setmgr.rSetting(this.customMode);
    }
    
    public void update() {
        if (HoleESP.mc.player == null || HoleESP.mc.world == null) {
            return;
        }
        if (this.holes == null) {
            this.holes = new ConcurrentHashMap<AxisAlignedBB, Colour>();
        }
        else {
            this.holes.clear();
        }
        final int range = (int)Math.ceil(this.radius.getValDouble());
        final HashSet<BlockPos> possibleHoles = (HashSet<BlockPos>)Sets.newHashSet();
        final List<BlockPos> blockPosList = EntityUtil.getSphere(PlayerUtil.getPlayerPos(), (float)range, range, false, true, 0);
        for (final BlockPos pos2 : blockPosList) {
            if (!HoleESP.mc.world.getBlockState(pos2).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (HoleESP.mc.world.getBlockState(pos2.add(0, -1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(pos2.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(pos2.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            possibleHoles.add(pos2);
        }
        final HoleUtil.HoleInfo holeInfo;
        final HoleUtil.HoleType holeType;
        HoleUtil.BlockSafety holeSafety;
        AxisAlignedBB centerBlock;
        Colour colour;
        String mode;
        possibleHoles.forEach(pos -> {
            holeInfo = HoleUtil.isHole(pos, false, false);
            holeType = holeInfo.getType();
            if (holeType != HoleUtil.HoleType.NONE) {
                holeSafety = holeInfo.getSafety();
                centerBlock = holeInfo.getCentre();
                if (centerBlock != null) {
                    if (holeSafety == HoleUtil.BlockSafety.UNBREAKABLE) {
                        colour = new Colour(this.bedrockColor.getR(), this.bedrockColor.getG(), this.bedrockColor.getB(), 255);
                    }
                    else {
                        colour = new Colour(this.obbyColor.getR(), this.obbyColor.getG(), this.obbyColor.getB(), 255);
                    }
                    if (holeType == HoleUtil.HoleType.CUSTOM) {
                        colour = new Colour(255, 255, 255, 255);
                    }
                    mode = this.customMode.getValString();
                    if (mode.equalsIgnoreCase("Custom") && (holeType == HoleUtil.HoleType.CUSTOM || holeType == HoleUtil.HoleType.DOUBLE)) {
                        this.holes.put(centerBlock, colour);
                    }
                    else if (mode.equalsIgnoreCase("Double") && holeType == HoleUtil.HoleType.DOUBLE) {
                        this.holes.put(centerBlock, colour);
                    }
                    else if (holeType == HoleUtil.HoleType.SINGLE) {
                        this.holes.put(centerBlock, colour);
                    }
                }
            }
        });
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (HoleESP.mc.player == null || HoleESP.mc.world == null || this.holes == null || this.holes.isEmpty()) {
            return;
        }
        this.holes.forEach(this::renderHoles);
    }
    
    private void renderHoles(final AxisAlignedBB hole, final Colour color) {
        final String valString = this.type.getValString();
        switch (valString) {
            case "Outline": {
                this.renderOutline(hole, color);
                break;
            }
            case "Fill": {
                this.renderFill(hole, color);
                break;
            }
            case "Both": {
                this.renderOutline(hole, color);
                this.renderFill(hole, color);
                break;
            }
        }
    }
    
    private void renderFill(final AxisAlignedBB hole, final Colour color) {
        final Colour fillColor = new Colour(color, 50);
        final int ufoAlpha = this.ufoAlpha.getValInt() * 50 / 255;
        if (this.ignoreOwnHole.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
            return;
        }
        final String valString = this.mode.getValString();
        switch (valString) {
            case "Air": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBox(hole, true, 1.0, fillColor, ufoAlpha, 1);
                    break;
                }
                RenderUtil.drawBox(hole, true, 1.0, fillColor, ufoAlpha, 63);
                break;
            }
            case "Ground": {
                RenderUtil.drawBox(hole.offset(0.0, -1.0, 0.0), true, 1.0, new Colour(fillColor, ufoAlpha), fillColor.getAlpha(), 63);
                break;
            }
            case "Flat": {
                RenderUtil.drawBox(hole, true, 1.0, fillColor, ufoAlpha, 1);
                break;
            }
            case "Slab": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBox(hole, true, 1.0, fillColor, ufoAlpha, 1);
                    break;
                }
                RenderUtil.drawBox(hole, false, this.slabHeight.getValDouble(), fillColor, ufoAlpha, 63);
                break;
            }
            case "Double": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBox(hole, true, 1.0, fillColor, ufoAlpha, 1);
                    break;
                }
                RenderUtil.drawBox(hole.setMaxY(hole.maxY + 1.0), true, 2.0, fillColor, ufoAlpha, 63);
                break;
            }
        }
    }
    
    private void renderOutline(final AxisAlignedBB hole, final Colour color) {
        final Colour outlineColor = new Colour(color, 255);
        if (this.ignoreOwnHole.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
            return;
        }
        final String valString = this.mode.getValString();
        switch (valString) {
            case "Air": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBoundingBoxWithSides(hole, this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt(), 1);
                    break;
                }
                RenderUtil.drawBoundingBox(hole, this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt());
                break;
            }
            case "Ground": {
                RenderUtil.drawBoundingBox(hole.offset(0.0, -1.0, 0.0), this.width.getValInt(), new Colour(outlineColor, this.ufoAlpha.getValInt()), outlineColor.getAlpha());
                break;
            }
            case "Flat": {
                RenderUtil.drawBoundingBoxWithSides(hole, this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt(), 1);
                break;
            }
            case "Slab": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBoundingBoxWithSides(hole, this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt(), 1);
                    break;
                }
                RenderUtil.drawBoundingBox(hole.setMaxY(hole.minY + this.slabHeight.getValDouble()), this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt());
                break;
            }
            case "Double": {
                if (this.flatOwn.getValBoolean() && hole.intersects(HoleESP.mc.player.getEntityBoundingBox())) {
                    RenderUtil.drawBoundingBoxWithSides(hole, this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt(), 1);
                    break;
                }
                RenderUtil.drawBoundingBox(hole.setMaxY(hole.maxY + 1.0), this.width.getValInt(), outlineColor, this.ufoAlpha.getValInt() / 255);
                break;
            }
        }
    }
}
