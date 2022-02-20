//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import java.awt.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.oldclickgui.*;
import com.kisman.cc.settings.*;
import net.minecraft.util.text.*;
import java.util.*;

public class ColorUtil
{
    public static List<String> colors;
    public static ArrayList<String> colours;
    public static float seconds;
    public static float saturation;
    public static float briqhtness;
    public int r;
    public int g;
    public int b;
    public int a;
    public int color;
    public ColorPicker colorPicker;
    
    public int getColor() {
        final float hue = System.currentTimeMillis() % (int)(ColorUtil.seconds * 1000.0f) / (ColorUtil.seconds * 1000.0f);
        final int color = Color.HSBtoRGB(hue, ColorUtil.saturation, ColorUtil.briqhtness);
        return this.color = color;
    }
    
    public static Color injectAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static Color injectAlpha(final int color, final int alpha) {
        return new Color(ColorUtils.getRed(color), ColorUtils.getGreen(color), ColorUtils.getBlue(color), alpha);
    }
    
    public static Color getGradientOffset(final Color one, final Color two, double offset, final int alpha) {
        if (offset > 1.0) {
            final double left = offset % 1.0;
            final int off = (int)offset;
            offset = ((off % 2 == 0) ? left : (1.0 - left));
        }
        final double inverse_percent = 1.0 - offset;
        final int redPart = (int)(one.getRed() * inverse_percent + two.getRed() * offset);
        final int greenPart = (int)(one.getGreen() * inverse_percent + two.getGreen() * offset);
        final int bluePart = (int)(one.getBlue() * inverse_percent + two.getBlue() * offset);
        return new Color(redPart, greenPart, bluePart, alpha);
    }
    
    public void getColorPickerRainBow(final ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
        if (colorPicker.isRainbowState()) {
            double rainbowState = Math.ceil((System.currentTimeMillis() + 200L) / 20.0);
            rainbowState %= 360.0;
            colorPicker.setColor(0, (float)(rainbowState / 360.0));
        }
    }
    
    public void rainbowLine() {
        if (this.colorPicker.isRainbowState()) {
            double rainbowState = Math.ceil((System.currentTimeMillis() + 200L) / 20.0);
            rainbowState %= 360.0;
            this.setLineColor((float)(rainbowState / 360.0), this.colorPicker.getColor(3));
        }
    }
    
    public void setLineColor(final float hue, final float alpha) {
        this.alpha(new Color(Color.HSBtoRGB(hue, this.colorPicker.getColor(1), this.colorPicker.getColor(2))), alpha);
        ClickGui.setRLine(this.r);
        ClickGui.setGLine(this.g);
        ClickGui.setBLine(this.b);
        ClickGui.setALine(this.a);
    }
    
    public int alpha(final Color color, final float alpha) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        this.r = (int)red;
        this.g = (int)green;
        this.b = (int)blue;
        this.a = (int)alpha;
        return new Color(red, green, blue, alpha).getRGB();
    }
    
    public static TextFormatting settingToTextFormatting(final Setting setting) {
        if (setting.getValString().equalsIgnoreCase("Black")) {
            return TextFormatting.BLACK;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Green")) {
            return TextFormatting.DARK_GREEN;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Red")) {
            return TextFormatting.DARK_RED;
        }
        if (setting.getValString().equalsIgnoreCase("Gold")) {
            return TextFormatting.GOLD;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Gray")) {
            return TextFormatting.DARK_GRAY;
        }
        if (setting.getValString().equalsIgnoreCase("Green")) {
            return TextFormatting.GREEN;
        }
        if (setting.getValString().equalsIgnoreCase("Red")) {
            return TextFormatting.RED;
        }
        if (setting.getValString().equalsIgnoreCase("Yellow")) {
            return TextFormatting.YELLOW;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Blue")) {
            return TextFormatting.DARK_BLUE;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Aqua")) {
            return TextFormatting.DARK_AQUA;
        }
        if (setting.getValString().equalsIgnoreCase("Dark Purple")) {
            return TextFormatting.DARK_PURPLE;
        }
        if (setting.getValString().equalsIgnoreCase("Gray")) {
            return TextFormatting.GRAY;
        }
        if (setting.getValString().equalsIgnoreCase("Blue")) {
            return TextFormatting.BLUE;
        }
        if (setting.getValString().equalsIgnoreCase("Light Purple")) {
            return TextFormatting.LIGHT_PURPLE;
        }
        if (setting.getValString().equalsIgnoreCase("White")) {
            return TextFormatting.WHITE;
        }
        if (setting.getValString().equalsIgnoreCase("Aqua")) {
            return TextFormatting.AQUA;
        }
        return null;
    }
    
    public static float getSeconds() {
        return ColorUtil.seconds;
    }
    
    public static void setSeconds(final float seconds) {
        ColorUtil.seconds = seconds;
    }
    
    public static float getSaturation() {
        return ColorUtil.saturation;
    }
    
    public static void setSaturation(final float saturation) {
        ColorUtil.saturation = saturation;
    }
    
    public static float getBriqhtness() {
        return ColorUtil.briqhtness;
    }
    
    public static void setBriqhtness(final float briqhtness) {
        ColorUtil.briqhtness = briqhtness;
    }
    
    static {
        ColorUtil.colors = Arrays.asList("Black", "Dark Green", "Dark Red", "Gold", "Dark Gray", "Green", "Red", "Yellow", "Dark Blue", "Dark Aqua", "Dark Purple", "Gray", "Blue", "Aqua", "Light Purple", "White");
        ColorUtil.colours = new ArrayList<String>(Arrays.asList("Black", "Dark Green", "Dark Red", "Gold", "Dark Gray", "Green", "Red", "Yellow", "Dark Blue", "Dark Aqua", "Dark Purple", "Gray", "Blue", "Aqua", "Light Purple", "White"));
        ColorUtil.seconds = 2.0f;
        ColorUtil.saturation = 1.0f;
        ColorUtil.briqhtness = 1.0f;
    }
}
