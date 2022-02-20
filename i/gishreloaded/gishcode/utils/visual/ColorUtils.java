//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.utils.visual;

import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class ColorUtils
{
    public static Color rainbow() {
        final long offset = 999999999999L;
        final float hue = (System.nanoTime() + offset) / 1.0E10f % 1.0f;
        final long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        final Color c = new Color((int)color);
        return new Color(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
    }
    
    public static int astolfoColors(final int yOffset, final int yTotal) {
        float speed;
        float hue;
        for (speed = 2900.0f, hue = System.currentTimeMillis() % (int)speed + (float)((yTotal - yOffset) * 9); hue > speed; hue -= speed) {}
        if ((hue /= speed) > 0.5) {
            hue = 0.5f - (hue - 0.5f);
        }
        return Color.HSBtoRGB(hue += 0.5f, 0.5f, 1.0f);
    }
    
    public static Color astolfoColorsToColorObj(final int yOffset, final int yTotal, final int alpha) {
        final int color = astolfoColors(yOffset, yTotal);
        return new Color(getRed(color), getGreen(color), getBlue(color), alpha);
    }
    
    public static Color astolfoColorsToColorObj(final int yOffset, final int yTotal) {
        return astolfoColorsToColorObj(yOffset, yTotal, 255);
    }
    
    public static int rainbow(final int delay, final long index) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + index + delay)) / 15.0;
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), 0.4f, 1.0f).getRGB();
    }
    
    public static int rainbowLT(final int delay, final long index) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + index + delay)) / 3.0;
        return Color.getHSBColor((float)((rainbowState %= 248.0) / 248.0), 0.5f, 0.6f).getRGB();
    }
    
    public static void glColor(final int hex, final int alpha) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GlStateManager.color(red, green, blue, alpha / 255.0f);
    }
    
    public static int color(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int color(final float r, final float g, final float b, final float a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int getColor(final int a, final int r, final int g, final int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }
    
    public static int getColor(final int r, final int g, final int b) {
        return 0xFF000000 | r << 16 | g << 8 | b;
    }
    
    public static int getColor(final Color color) {
        return color.getRed() | color.getGreen() << 8 | color.getBlue() << 16 | color.getAlpha() << 24;
    }
    
    public static Color rainbow(final int delay, final float s, final float b) {
        return Color.getHSBColor((System.currentTimeMillis() + delay) % 11520L / 11520.0f, s, b);
    }
    
    public static int getRed(final int color) {
        return new Color(color).getRed();
    }
    
    public static int getGreen(final int color) {
        return new Color(color).getGreen();
    }
    
    public static int getBlue(final int color) {
        return new Color(color).getBlue();
    }
    
    public static int getAlpha(final int color) {
        return new Color(color).getAlpha();
    }
    
    public static Color rainbowRGB(final int delay, final float s, final float b) {
        return new Color(getRed(Color.HSBtoRGB((System.currentTimeMillis() + delay) % 11520L / 11520.0f, s, b)), getGreen(Color.HSBtoRGB((System.currentTimeMillis() + delay) % 11520L / 11520.0f, s, b)), getBlue(Color.HSBtoRGB((System.currentTimeMillis() + delay) % 11520L / 11520.0f, s, b)));
    }
    
    public static int getColor(final int brightness) {
        return getColor(brightness, brightness, brightness, 255);
    }
    
    public static int getColor(final int brightness, final int alpha) {
        return getColor(brightness, brightness, brightness, alpha);
    }
    
    public static Color injectAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static Color injectAlpha(final int color, final int alpha) {
        return new Color(getRed(color), getGreen(color), getBlue(color), alpha);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
}
