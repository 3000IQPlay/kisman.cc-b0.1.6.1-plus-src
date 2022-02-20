//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.combat;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PvpResources extends HudModule
{
    private int dOffset;
    
    public PvpResources() {
        super("PvpResources", "PvpResources", HudCategory.COMBAT);
        this.dOffset = 20;
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        final ScaledResolution sr = event.getResolution();
        final int x = sr.getScaledWidth() / 2 + 5;
        final int y = sr.getScaledHeight() / 2 + 5;
        final int totemCount = this.getTotemCount();
        final int crystalCount = this.getCrystalCount();
        final int expCount = this.getExpCount();
        final int gAppleCount = this.getGAppleCount();
        int count = 0;
        int offset = 0;
        final int offsetY = 90;
        if (totemCount != 0) {
            PvpResources.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.TOTEM_OF_UNDYING), x, y);
            final String s = (totemCount > 1) ? (totemCount + "") : "";
            CustomFontUtil.drawStringWithShadow(s, x + 19 - 2 - CustomFontUtil.getStringWidth(s), y + 9, 16777215);
            ++count;
            offset += 90;
        }
        if (crystalCount != 0) {
            PvpResources.mc.renderItem.renderItemIntoGUI(new ItemStack(Items.END_CRYSTAL), x + offset, y);
            final String s2 = (crystalCount > 1) ? (crystalCount + "") : "";
            CustomFontUtil.drawStringWithShadow(s2, x + 19 - 2 - CustomFontUtil.getStringWidth(s2) + offset, y + 9, 16777215);
            ++count;
            offset += 90;
        }
        if (expCount != 0) {
            if (count >= 2) {
                offset = 0;
            }
            PvpResources.mc.renderItem.renderItemIntoGUI(new ItemStack(Items.END_CRYSTAL), x + offset, y);
            final String s3 = (crystalCount > 1) ? (crystalCount + "") : "";
            CustomFontUtil.drawStringWithShadow(s3, x + 19 - 2 - CustomFontUtil.getStringWidth(s3) + offset, y + 9 + offsetY, 16777215);
            ++count;
            offset += 90;
        }
        if (gAppleCount != 0) {
            PvpResources.mc.renderItem.renderItemIntoGUI(new ItemStack(Items.END_CRYSTAL), x + offset, y);
            final String s3 = (crystalCount > 1) ? (crystalCount + "") : "";
            CustomFontUtil.drawStringWithShadow(s3, x + 19 - 2 - CustomFontUtil.getStringWidth(s3) + offset, y + 9 + offset, 16777215);
            ++count;
            offset += 90;
        }
    }
    
    private int getTotemCount() {
        int totemCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (PvpResources.mc.player.inventory.getStackInSlot(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
                ++totemCount;
            }
        }
        if (PvpResources.mc.player.getHeldItemOffhand().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            ++totemCount;
        }
        return totemCount;
    }
    
    private int getGAppleCount() {
        int gAppleCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (PvpResources.mc.player.inventory.getStackInSlot(i).getItem().equals(Items.GOLDEN_APPLE)) {
                ++gAppleCount;
            }
        }
        if (PvpResources.mc.player.getHeldItemOffhand().getItem().equals(Items.GOLDEN_APPLE)) {
            ++gAppleCount;
        }
        return gAppleCount;
    }
    
    private int getCrystalCount() {
        int crystalCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (PvpResources.mc.player.inventory.getStackInSlot(i).getItem().equals(Items.END_CRYSTAL)) {
                ++crystalCount;
            }
        }
        if (PvpResources.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL)) {
            ++crystalCount;
        }
        return crystalCount;
    }
    
    private int getExpCount() {
        int expCount = 0;
        for (int i = 0; i < 45; ++i) {
            if (PvpResources.mc.player.inventory.getStackInSlot(i).getItem().equals(Items.EXPERIENCE_BOTTLE)) {
                ++expCount;
            }
        }
        if (PvpResources.mc.player.getHeldItemOffhand().getItem().equals(Items.EXPERIENCE_BOTTLE)) {
            ++expCount;
        }
        return expCount;
    }
}
