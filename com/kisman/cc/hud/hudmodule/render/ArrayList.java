//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import java.awt.*;
import com.kisman.cc.util.manager.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ArrayList extends HudModule
{
    public ArrayList() {
        super("ArrayList", "arrList", HudCategory.RENDER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final java.util.ArrayList<Module> mods = new java.util.ArrayList<Module>();
        final ScaledResolution sr = new ScaledResolution(ArrayList.mc);
        for (final Module mod : Kisman.instance.moduleManager.modules) {
            if (mod != null && mod.isToggled() && mod.visible) {
                mods.add(mod);
            }
        }
        String string;
        final StringBuilder sb;
        final String firstName;
        String string2;
        final StringBuilder sb2;
        final String secondName;
        final float dif;
        final Comparator<Module> comparator = (first, second) -> {
            new StringBuilder().append(first.getName());
            if (first.getDisplayInfo().equalsIgnoreCase("")) {
                string = "";
            }
            else {
                string = " " + TextFormatting.GRAY + first.getDisplayInfo();
            }
            firstName = sb.append(string).toString();
            new StringBuilder().append(second.getName());
            if (second.getDisplayInfo().equalsIgnoreCase("")) {
                string2 = "";
            }
            else {
                string2 = " " + TextFormatting.GRAY + second.getDisplayInfo();
            }
            secondName = sb2.append(string2).toString();
            dif = (float)(CustomFontUtil.getStringWidth(secondName) - CustomFontUtil.getStringWidth(firstName));
            return (dif != 0.0f) ? ((int)dif) : secondName.compareTo(firstName);
        };
        mods.sort(comparator);
        int count = 0;
        final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : new Color(HUD.instance.arrColor.getR(), HUD.instance.arrColor.getG(), HUD.instance.arrColor.getB(), HUD.instance.arrColor.getA()).getRGB();
        final float heigth = (float)(CustomFontUtil.getFontHeight() + 2);
        final float[] hsb = Color.RGBtoHSB(ColorUtils.getRed(color), ColorUtils.getGreen(color), ColorUtils.getBlue(color), null);
        for (final Module mod2 : mods) {
            if (mod2 != null && mod2.isToggled() && mod2.visible) {
                final String name = mod2.getName() + (mod2.getDisplayInfo().equalsIgnoreCase("") ? "" : (" " + TextFormatting.GRAY + mod2.getDisplayInfo()));
                final String valString = HUD.instance.arrGragient.getValString();
                switch (valString) {
                    case "None": {
                        CustomFontUtil.drawStringWithShadow(name, HUD.instance.arrMode.getValString().equalsIgnoreCase("LEFT") ? 1 : (sr.getScaledWidth() - CustomFontUtil.getStringWidth(name)), HUD.instance.arrY.getValDouble() + heigth * count, color);
                        break;
                    }
                    case "Simple": {
                        CustomFontUtil.drawStringWithShadow(name, HUD.instance.arrMode.getValString().equalsIgnoreCase("LEFT") ? 1 : (sr.getScaledWidth() - CustomFontUtil.getStringWidth(name)), HUD.instance.arrY.getValDouble() + heigth * count, ColorUtils.injectAlpha(ColorUtils.rainbow(count * HUD.instance.arrGradientDiff.getValInt(), hsb[1], Managers.instance.pulseManager.getDifference(count * 2) / 255.0f), 255).getRGB());
                        break;
                    }
                    case "Sideway": {
                        int update = HUD.instance.arrMode.getValString().equalsIgnoreCase("LEFT") ? 1 : (sr.getScaledWidth() - CustomFontUtil.getStringWidth(name));
                        for (int i = 0; i < mod2.getName().length(); ++i) {
                            final String str = String.valueOf(mod2.getName().charAt(i));
                            CustomFontUtil.drawStringWithShadow(str, update, HUD.instance.arrY.getValDouble() + heigth * count, ColorUtils.injectAlpha(ColorUtils.rainbow(i * HUD.instance.arrGradientDiff.getValInt(), hsb[1], Managers.instance.pulseManager.getDifference(count * 2) / 255.0f), 255).getRGB());
                            update += CustomFontUtil.getStringWidth(str);
                        }
                        break;
                    }
                }
                ++count;
            }
        }
    }
}
