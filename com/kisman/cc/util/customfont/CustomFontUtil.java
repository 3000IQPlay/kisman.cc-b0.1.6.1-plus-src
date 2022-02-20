//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.customfont;

import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.norules.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;
import com.kisman.cc.*;
import java.awt.*;
import com.kisman.cc.module.client.*;
import net.minecraft.util.text.*;

public class CustomFontUtil
{
    private static final FontRenderer fontRenderer;
    public static CFontRenderer comfortaal20;
    public static CFontRenderer comfortaal18;
    public static CFontRenderer comfortaal15;
    public static CFontRenderer comfortaal16;
    public static CFontRenderer comfortaab20;
    public static CFontRenderer comfortaab18;
    public static CFontRenderer comfortaab16;
    public static CFontRenderer comfortaa20;
    public static CFontRenderer comfortaa18;
    public static CFontRenderer comfortaa15;
    public static CFontRenderer consolas18;
    public static CFontRenderer consolas15;
    public static CustomFontRenderer verdana18;
    public static CustomFontRenderer verdana15;
    
    private static Font getFontTTF(final String name, final int size) {
        Font font;
        try {
            final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/" + name + ".ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, (float)size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    public static int getStringWidth(final String text) {
        return customFont() ? (getCustomFont().equals("Verdana") ? (Kisman.instance.customFontRenderer.getStringWidth(text) + 3) : (getCustomFont().equals("Consolas") ? (CustomFontUtil.consolas18.getStringWidth(text) + 3) : ((getCustomFont().equalsIgnoreCase("Comfortaa") || getCustomFont().equalsIgnoreCase("Comfortaa Light")) ? (CustomFontUtil.comfortaa18.getStringWidth(text) + 3) : CustomFontUtil.fontRenderer.getStringWidth(text)))) : CustomFontUtil.fontRenderer.getStringWidth(text);
    }
    
    public static int getStringWidth(final String text, final boolean gui) {
        return customFont() ? (getCustomFont().equals("Verdana") ? ((CSGOGui.instance.customSize.getValBoolean() && gui) ? (Kisman.instance.customFontRenderer1.getStringWidth(text) + 3) : (Kisman.instance.customFontRenderer.getStringWidth(text) + 3)) : (getCustomFont().equals("Consolas") ? ((CSGOGui.instance.customSize.getValBoolean() && gui) ? (CustomFontUtil.consolas15.getStringWidth(text) + 3) : (CustomFontUtil.consolas18.getStringWidth(text) + 3)) : ((getCustomFont().equalsIgnoreCase("Comfortaa") || getCustomFont().equalsIgnoreCase("Comfortaa Light")) ? ((CSGOGui.instance.customSize.getValBoolean() && gui) ? (CustomFontUtil.comfortaa15.getStringWidth(text) + 3) : (CustomFontUtil.comfortaal18.getStringWidth(text) + 3)) : CustomFontUtil.fontRenderer.getStringWidth(text)))) : CustomFontUtil.fontRenderer.getStringWidth(text);
    }
    
    public static void drawString(final String text, final double x, final double y, final int color, final boolean gui) {
        if (customFont()) {
            final String customFont = getCustomFont();
            switch (customFont) {
                case "Verdana": {
                    Kisman.instance.customFontRenderer.drawString(getStringModofiers() + text, x, y - 1.0, color, false);
                    break;
                }
                case "Comfortaa": {
                    CustomFontUtil.comfortaa18.drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Consolas": {
                    ((CSGOGui.instance.customSize.getValBoolean() && gui) ? CustomFontUtil.consolas15 : CustomFontUtil.consolas18).drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Comfortaa Light": {
                    CustomFontUtil.comfortaal18.drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
            }
        }
        else {
            CustomFontUtil.fontRenderer.drawString(getStringModofiers() + text, (int)x, (int)y, color);
        }
    }
    
    public static void drawString(final String text, final double x, final double y, final int color) {
        if (customFont()) {
            final String customFont = getCustomFont();
            switch (customFont) {
                case "Verdana": {
                    Kisman.instance.customFontRenderer.drawString(getStringModofiers() + text, x, y - 1.0, color, false);
                    break;
                }
                case "Comfortaa": {
                    CustomFontUtil.comfortaa18.drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Consolas": {
                    CustomFontUtil.consolas18.drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Comfortaa Light": {
                    CustomFontUtil.comfortaal18.drawString(getStringModofiers() + text, x, y, color);
                    break;
                }
            }
        }
        else {
            CustomFontUtil.fontRenderer.drawString(getStringModofiers() + text, (int)x, (int)y, color);
        }
    }
    
    public static void drawStringWithShadow(final String text, final double x, final double y, final int color) {
        if (customFont()) {
            final String customFont = getCustomFont();
            switch (customFont) {
                case "Verdana": {
                    Kisman.instance.customFontRenderer.drawStringWithShadow(getStringModofiers() + text, x, y - 1.0, color);
                    break;
                }
                case "Comfortaa": {
                    CustomFontUtil.comfortaa18.drawStringWithShadow(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Consolas": {
                    CustomFontUtil.consolas18.drawStringWithShadow(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Comfortaa Light": {
                    CustomFontUtil.comfortaal18.drawStringWithShadow(getStringModofiers() + text, x, y, color);
                    break;
                }
            }
        }
        else {
            CustomFontUtil.fontRenderer.drawStringWithShadow(getStringModofiers() + text, (float)x, (float)y, color);
        }
    }
    
    public static void drawCenteredStringWithShadow(final String text, final float x, final float y, final int color) {
        if (customFont()) {
            final String customFont = getCustomFont();
            switch (customFont) {
                case "Verdana": {
                    Kisman.instance.customFontRenderer.drawCenteredStringWithShadow(getStringModofiers() + text, x, y - 1.0f, color);
                    break;
                }
                case "Comfortaa": {
                    CustomFontUtil.comfortaa18.drawCenteredStringWithShadow(getStringModofiers() + text, x, y - 1.0f, color);
                    break;
                }
                case "Consolas": {
                    CustomFontUtil.consolas18.drawCenteredStringWithShadow(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Comfortaa Light": {
                    CustomFontUtil.comfortaal18.drawCenteredStringWithShadow(getStringModofiers() + text, x, y, color);
                    break;
                }
            }
        }
        else {
            CustomFontUtil.fontRenderer.drawStringWithShadow(getStringModofiers() + text, x - CustomFontUtil.fontRenderer.getStringWidth(getStringModofiers() + text) / 2.0f, y, color);
        }
    }
    
    public static void drawCenteredString(final String text, final float x, final float y, final int color) {
        if (customFont()) {
            final String customFont = getCustomFont();
            switch (customFont) {
                case "Verdana": {
                    Kisman.instance.customFontRenderer.drawCenteredString(getStringModofiers() + text, x, y - 1.0f, color);
                    break;
                }
                case "Comfortaa": {
                    CustomFontUtil.comfortaa18.drawCenteredString(getStringModofiers() + text, x, y - 1.0f, color);
                    break;
                }
                case "Consolas": {
                    CustomFontUtil.consolas18.drawCenteredString(getStringModofiers() + text, x, y, color);
                    break;
                }
                case "Comfortaa Light": {
                    CustomFontUtil.comfortaal18.drawCenteredString(getStringModofiers() + text, x, y, color);
                    break;
                }
            }
        }
        else {
            CustomFontUtil.fontRenderer.drawString(getStringModofiers() + text, (int)(x - getStringWidth(getStringModofiers() + text) / 2), (int)y, color);
        }
    }
    
    public static int getFontHeight(final boolean gui) {
        return customFont() ? (getCustomFont().equalsIgnoreCase("Verdana") ? (Kisman.instance.customFontRenderer.fontHeight / 2 - 1) : (getCustomFont().equalsIgnoreCase("Consolas") ? ((CSGOGui.instance.customSize.getValBoolean() && gui) ? ((CustomFontUtil.consolas15.fontHeight - 8) / 2) : ((CustomFontUtil.consolas18.fontHeight - 8) / 2)) : ((CustomFontUtil.comfortaa18.fontHeight - 8) / 2))) : CustomFontUtil.fontRenderer.FONT_HEIGHT;
    }
    
    public static int getFontHeight() {
        return customFont() ? (getCustomFont().equalsIgnoreCase("Verdana") ? (Kisman.instance.customFontRenderer.fontHeight / 2 - 1) : (getCustomFont().equalsIgnoreCase("Consolas") ? ((CustomFontUtil.consolas15.fontHeight - 8) / 2) : ((CustomFontUtil.comfortaa18.fontHeight - 8) / 2))) : CustomFontUtil.fontRenderer.FONT_HEIGHT;
    }
    
    public static boolean validateFont(final String font) {
        for (final String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (s.equals(font)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean customFont() {
        return CustomFont.turnOn;
    }
    
    private static String getCustomFont() {
        return CustomFont.instance.mode.getValString();
    }
    
    private static String getStringModofiers() {
        String str = "";
        if (CustomFont.instance != null) {
            if (CustomFont.instance.italic.getValBoolean()) {
                str += TextFormatting.ITALIC;
            }
            if (CustomFont.instance.bold.getValBoolean() && getCustomFont().equalsIgnoreCase("Verdana")) {
                str += TextFormatting.BOLD;
            }
        }
        return str;
    }
    
    static {
        fontRenderer = Minecraft.getMinecraft().fontRenderer;
        CustomFontUtil.comfortaal20 = new CFontRenderer(getFontTTF("comfortaa-light", 22), true, true);
        CustomFontUtil.comfortaal18 = new CFontRenderer(getFontTTF("comfortaa-light", 18), true, true);
        CustomFontUtil.comfortaal15 = new CFontRenderer(getFontTTF("comfortaa-light", 15), true, true);
        CustomFontUtil.comfortaal16 = new CFontRenderer(getFontTTF("comfortaa-light", 16), true, true);
        CustomFontUtil.comfortaab20 = new CFontRenderer(getFontTTF("comfortaa-bold", 22), true, true);
        CustomFontUtil.comfortaab18 = new CFontRenderer(getFontTTF("comfortaa-bold", 18), true, true);
        CustomFontUtil.comfortaab16 = new CFontRenderer(getFontTTF("comfortaa-bold", 16), true, true);
        CustomFontUtil.comfortaa20 = new CFontRenderer(getFontTTF("comfortaa-regular", 22), true, true);
        CustomFontUtil.comfortaa18 = new CFontRenderer(getFontTTF("comfortaa-regular", 18), true, true);
        CustomFontUtil.comfortaa15 = new CFontRenderer(getFontTTF("comfortaa-regular", 15), true, true);
        CustomFontUtil.consolas18 = new CFontRenderer(getFontTTF("consolas", 18), true, true);
        CustomFontUtil.consolas15 = new CFontRenderer(getFontTTF("consolas", 15), true, true);
        CustomFontUtil.verdana18 = Kisman.instance.customFontRenderer;
        CustomFontUtil.verdana15 = Kisman.instance.customFontRenderer1;
    }
}
