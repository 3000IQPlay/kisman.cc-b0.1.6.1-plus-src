//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import java.util.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.client.event.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.block.*;
import com.kisman.cc.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BlockOutline extends Module
{
    public static BlockOutline instance;
    private float[] color;
    private String renderMode;
    
    public BlockOutline() {
        super("BlockOutline", "BlockOutline", Category.RENDER);
        this.color = new float[] { 0.78f, 0.62f, 0.88f, 1.0f };
        this.renderMode = "";
        BlockOutline.instance = this;
        Kisman.instance.settingsManager.rSetting(new Setting("RenderMode", this, "Outline", new ArrayList<String>(Arrays.asList("Outline", "Box", "OutlineBox", "Flat"))));
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    public void update() {
        this.renderMode = Kisman.instance.settingsManager.getSettingByName(this, "RenderMode").getValString();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (BlockOutline.mc.objectMouseOver == null) {
            return;
        }
        if (BlockOutline.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            final Block block = BlockUtils.getBlock(BlockOutline.mc.objectMouseOver.getBlockPos());
            final BlockPos blockPos = BlockOutline.mc.objectMouseOver.getBlockPos();
            if (Block.getIdFromBlock(block) == 0) {
                return;
            }
            if (this.renderMode.equalsIgnoreCase("OutlineBox")) {
                RenderUtil.drawBlockESP(blockPos, this.color[0], this.color[1], this.color[2]);
            }
            else if (this.renderMode.equalsIgnoreCase("Flat")) {
                RenderUtil.drawBlockFlatESP(blockPos, this.color[0], this.color[1], this.color[2]);
            }
            else if (this.renderMode.equalsIgnoreCase("Outline")) {
                RenderUtil.drawBlockOutlineESP(blockPos, this.color[0], this.color[1], this.color[2]);
            }
        }
    }
}
