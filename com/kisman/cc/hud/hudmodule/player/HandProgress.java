//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.player;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HandProgress extends HudModule
{
    private double width;
    
    public HandProgress() {
        super("HandProgress", HudCategory.PLAYER);
        this.width = 0.0;
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final double cooldownPercent = MathHelper.clamp(HandProgress.mc.player.getItemInUseCount(), 0, HandProgress.mc.player.getItemInUseMaxCount());
        final double cdWidth = 100.0 * cooldownPercent / HandProgress.mc.player.getItemInUseMaxCount();
        this.width = AnimationUtils.animate(cdWidth, this.width, 0.05999999865889549);
        if (this.width > 0.061000000685453415) {
            Render2DUtil.drawRect(GLUtils.getScreenWidth() / 2 - 20, GLUtils.getScreenHeight() / 2 + 30, GLUtils.getScreenWidth() / 2 + 80, GLUtils.getScreenHeight() / 2 + 24, ColorUtils.getColor(11, 11, 11, 255));
            if (cdWidth > 95.0) {
                Render2DUtil.drawRect(GLUtils.getScreenWidth() / 2 - 20, GLUtils.getScreenHeight() / 2 + 30, GLUtils.getScreenWidth() / 2 + 80, GLUtils.getScreenHeight() / 2 + 24, ColorUtils.getColor(255, 0, 70, 255));
            }
            Render2DUtil.drawRect(GLUtils.getScreenWidth() / 2 - 19, GLUtils.getScreenHeight() / 2 - 19 + 48, GLUtils.getScreenWidth() / 2 - 19 + this.width, GLUtils.getScreenHeight() / 2 + 25, ColorUtils.getColor(255 - (int)cdWidth * 3, (int)cdWidth * 3, 0, 255));
        }
    }
}
