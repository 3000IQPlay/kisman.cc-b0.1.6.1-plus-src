//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.combat;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.customfont.*;
import i.gishreloaded.gishcode.wrappers.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.function.*;

public class ArmorHUD extends HudModule
{
    private int offHandHeldItemCount;
    private int armourCompress;
    private int armourSpacing;
    
    public ArmorHUD() {
        super("ArmorHUD", "ArmorHUD", HudCategory.COMBAT);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        if (ArmorHUD.mc.player == null && ArmorHUD.mc.world == null) {
            return;
        }
        final ScaledResolution resolution = event.getResolution();
        final RenderItem itemRender = ArmorHUD.mc.getRenderItem();
        final int i = resolution.getScaledWidth() / 2;
        int iteration = 0;
        final int y = resolution.getScaledHeight() - 55 - (ArmorHUD.mc.player.isInWater() ? 10 : 0);
        for (final ItemStack is : ArmorHUD.mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) {
                continue;
            }
            final int x = i - 90 + (9 - iteration) * this.armourSpacing + this.armourCompress;
            GlStateManager.enableDepth();
            itemRender.zLevel = 200.0f;
            itemRender.renderItemAndEffectIntoGUI(is, x, y);
            itemRender.renderItemOverlayIntoGUI(ArmorHUD.mc.fontRenderer, is, x, y, "");
            itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (is.getCount() > 1) ? (is.getCount() + "") : "";
            final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
            CustomFontUtil.drawStringWithShadow(s, x + 19 - 2 - CustomFontUtil.getStringWidth(s), y + 9, color);
            if (HUD.instance.armDmg.getValBoolean()) {
                final float green = (is.getMaxDamage() - (float)is.getItemDamage()) / is.getMaxDamage();
                final float red = 1.0f - green;
                final int dmg = 100 - (int)(red * 100.0f);
                CustomFontUtil.drawStringWithShadow(dmg + "", x + 8 - CustomFontUtil.getStringWidth(dmg + "") / 2, y - 11, color);
            }
            if (HUD.instance.armExtra.getValBoolean()) {
                final ItemStack itemStack = ArmorHUD.mc.player.getHeldItemOffhand();
                final Item helfInOffHand = Wrapper.INSTANCE.player().getHeldItemOffhand().getItem();
                this.offHandHeldItemCount = this.getItemsOffHand(helfInOffHand);
                GlStateManager.pushMatrix();
                GlStateManager.disableAlpha();
                GlStateManager.clear(256);
                GlStateManager.enableBlend();
                GlStateManager.pushAttrib();
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.disableDepth();
                ArmorHUD.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 572, y);
                itemRender.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), itemStack, 572, y, String.valueOf(this.offHandHeldItemCount));
                GlStateManager.enableDepth();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.popAttrib();
                GlStateManager.disableBlend();
                GlStateManager.disableDepth();
                GlStateManager.disableLighting();
                GlStateManager.enableDepth();
                GlStateManager.enableAlpha();
                GlStateManager.popMatrix();
            }
            if (HUD.instance.armExtra.getValBoolean()) {
                final Item currentHeldItem = Wrapper.INSTANCE.inventory().getCurrentItem().getItem();
                final int currentHeldItemCount = Wrapper.INSTANCE.inventory().getCurrentItem().getCount();
                final ItemStack stackHeld = new ItemStack(currentHeldItem, 1);
                GlStateManager.pushMatrix();
                GlStateManager.disableAlpha();
                GlStateManager.clear(256);
                GlStateManager.enableBlend();
                GlStateManager.pushAttrib();
                RenderHelper.enableGUIStandardItemLighting();
                GlStateManager.disableDepth();
                Wrapper.INSTANCE.mc().getRenderItem().renderItemAndEffectIntoGUI(stackHeld, 556, y);
                itemRender.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), stackHeld, 556, y, String.valueOf(currentHeldItemCount));
                GlStateManager.enableDepth();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.popAttrib();
                GlStateManager.disableBlend();
                GlStateManager.disableDepth();
                GlStateManager.disableLighting();
                GlStateManager.enableDepth();
                GlStateManager.enableAlpha();
                GlStateManager.popMatrix();
            }
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
            if (HUD.instance.armExtra.getValBoolean()) {
                this.armourCompress = 14;
                this.armourSpacing = 17;
            }
            else {
                this.armourCompress = 2;
                this.armourSpacing = 20;
            }
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    int getItemsOffHand(final Item i) {
        return Wrapper.INSTANCE.inventory().offHandInventory.stream().filter(itemStack -> itemStack.getItem() == i).mapToInt(ItemStack::getCount).sum();
    }
}
