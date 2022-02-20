//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui;

import net.minecraft.client.gui.*;
import java.awt.*;
import com.kisman.cc.util.customfont.*;
import java.io.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.*;

public class ColorPicker extends GuiScreen
{
    private final float[] color;
    private boolean pickingColor;
    private boolean pickingHue;
    private boolean pickingAlpha;
    private int pickerX;
    private int pickerY;
    private int pickerWidth;
    private int pickerHeight;
    private int hueSliderX;
    private int hueSliderY;
    private int hueSliderWidth;
    private int hueSliderHeight;
    private int alphaSliderX;
    private int alphaSliderY;
    private int alphaSliderWidth;
    private int alphaSliderHeight;
    private float rainbowSpeed;
    private boolean rainbowState;
    public boolean syns;
    private int selectedColorFinal;
    
    public ColorPicker() {
        this.rainbowSpeed = 20.0f;
        this.rainbowState = false;
        this.syns = false;
        this.color = new float[] { 0.4f, 1.0f, 1.0f, 1.0f };
        this.pickingColor = false;
    }
    
    public void initGui() {
        this.pickerWidth = 120;
        this.pickerHeight = 100;
        this.pickerX = this.width / 2 - this.pickerWidth / 2;
        this.pickerY = this.height / 2 - this.pickerHeight / 2;
        this.hueSliderX = this.pickerX;
        this.hueSliderY = this.pickerY + this.pickerHeight + 6;
        this.hueSliderWidth = this.pickerWidth;
        this.hueSliderHeight = 10;
        this.alphaSliderX = this.pickerX + this.pickerWidth + 6;
        this.alphaSliderY = this.pickerY;
        this.alphaSliderWidth = 10;
        this.alphaSliderHeight = this.pickerHeight;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.pickingHue) {
            if (this.hueSliderWidth > this.hueSliderHeight) {
                final float restrictedX = (float)Math.min(Math.max(this.hueSliderX, mouseX), this.hueSliderX + this.hueSliderWidth);
                this.color[0] = (restrictedX - this.hueSliderX) / this.hueSliderWidth;
            }
            else {
                final float restrictedY = (float)Math.min(Math.max(this.hueSliderY, mouseY), this.hueSliderY + this.hueSliderHeight);
                this.color[0] = (restrictedY - this.hueSliderY) / this.hueSliderHeight;
            }
        }
        if (this.pickingAlpha) {
            if (this.alphaSliderWidth > this.alphaSliderHeight) {
                final float restrictedX = (float)Math.min(Math.max(this.alphaSliderX, mouseX), this.alphaSliderX + this.alphaSliderWidth);
                this.color[3] = 1.0f - (restrictedX - this.alphaSliderX) / this.alphaSliderWidth;
            }
            else {
                final float restrictedY = (float)Math.min(Math.max(this.alphaSliderY, mouseY), this.alphaSliderY + this.alphaSliderHeight);
                this.color[3] = 1.0f - (restrictedY - this.alphaSliderY) / this.alphaSliderHeight;
            }
        }
        if (this.pickingColor) {
            final float restrictedX = (float)Math.min(Math.max(this.pickerX, mouseX), this.pickerX + this.pickerWidth);
            final float restrictedY2 = (float)Math.min(Math.max(this.pickerY, mouseY), this.pickerY + this.pickerHeight);
            this.color[1] = (restrictedX - this.pickerX) / this.pickerWidth;
            this.color[2] = 1.0f - (restrictedY2 - this.pickerY) / this.pickerHeight;
        }
        final int selectedX = this.pickerX + this.pickerWidth + 6;
        final int selectedY = this.pickerY + this.pickerHeight + 6;
        final int selectedWidth = 10;
        final int selectedHeight = 10;
        Gui.drawRect(this.pickerX - 2, this.pickerY - 2, this.pickerX + this.pickerWidth + 2, this.pickerY + this.pickerHeight + 2, -67108864);
        Gui.drawRect(this.hueSliderX - 2, this.hueSliderY - 2, this.hueSliderX + this.hueSliderWidth + 2, this.hueSliderY + this.hueSliderHeight + 2, -67108864);
        Gui.drawRect(this.alphaSliderX - 2, this.alphaSliderY - 2, this.alphaSliderX + this.alphaSliderWidth + 2, this.alphaSliderY + this.alphaSliderHeight + 2, -67108864);
        final int selectedColor = Color.HSBtoRGB(this.color[0], 1.0f, 1.0f);
        final float selectedRed = (selectedColor >> 16 & 0xFF) / 255.0f;
        final float selectedGreen = (selectedColor >> 8 & 0xFF) / 255.0f;
        final float selectedBlue = (selectedColor & 0xFF) / 255.0f;
        this.drawPickerBase(this.pickerX, this.pickerY, this.pickerWidth, this.pickerHeight, selectedRed, selectedGreen, selectedBlue, this.color[3]);
        this.drawHueSlider(this.hueSliderX, this.hueSliderY, this.hueSliderWidth, this.hueSliderHeight, this.color[0]);
        this.drawAlphaSlider(this.alphaSliderX, this.alphaSliderY, this.alphaSliderWidth, this.alphaSliderHeight, selectedRed, selectedGreen, selectedBlue, this.color[3]);
        this.selectedColorFinal = this.alpha(new Color(Color.HSBtoRGB(this.color[0], this.color[1], this.color[2])), this.color[3]);
        Gui.drawRect(selectedX - 2, selectedY - 2, selectedX + selectedWidth + 2, selectedY + selectedHeight + 2, -67108864);
        Gui.drawRect(selectedX, selectedY, selectedX + selectedWidth, selectedY + selectedHeight, this.selectedColorFinal);
        Gui.drawRect(selectedX - 2, selectedY + selectedHeight * 2 - 2, selectedX + 2 + selectedWidth, selectedY + selectedHeight * 3 + 2, -67108864);
        CustomFontUtil.drawString("RainBow", selectedX - 2 - CustomFontUtil.getStringWidth("RainBow"), selectedY + selectedHeight * 2 - (selectedHeight - CustomFontUtil.getFontHeight()) / 2, -67108864);
        Gui.drawRect(selectedX - 2, selectedY + selectedHeight * 2 - 2 + selectedWidth, selectedX + 2 + selectedWidth, selectedY + selectedHeight * 3 + 2 + selectedWidth, -67108864);
        CustomFontUtil.drawString("Syns", selectedX - 2 - CustomFontUtil.getStringWidth("Syns"), selectedY + selectedHeight * 2 - (selectedHeight - CustomFontUtil.getFontHeight()) / 2, -67108864);
        if (this.rainbowState) {
            Gui.drawRect(selectedX, selectedY + selectedHeight * 2, selectedX + selectedWidth, selectedY + selectedHeight * 3, -1);
        }
        if (this.syns) {
            Gui.drawRect(selectedX, selectedY + selectedHeight * 2 + selectedWidth, selectedX + selectedWidth, selectedY + selectedHeight * 3 + selectedWidth, -1);
        }
        final int cursorX = (int)(this.pickerX + this.color[1] * this.pickerWidth);
        final int cursorY = (int)(this.pickerY + this.pickerHeight - this.color[2] * this.pickerHeight);
        Gui.drawRect(cursorX - 2, cursorY - 2, cursorX + 2, cursorY + 2, -1);
    }
    
    final int alpha(final Color color, final float alpha) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        return new Color(red, green, blue, alpha).getRGB();
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.pickingColor = this.check(this.pickerX, this.pickerY, this.pickerX + this.pickerWidth, this.pickerY + this.pickerHeight, mouseX, mouseY);
        this.pickingHue = this.check(this.hueSliderX, this.hueSliderY, this.hueSliderX + this.hueSliderWidth, this.hueSliderY + this.hueSliderHeight, mouseX, mouseY);
        this.pickingAlpha = this.check(this.alphaSliderX, this.alphaSliderY, this.alphaSliderX + this.alphaSliderWidth, this.alphaSliderY + this.alphaSliderHeight, mouseX, mouseY);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        final boolean pickingColor = false;
        this.pickingAlpha = pickingColor;
        this.pickingHue = pickingColor;
        this.pickingColor = pickingColor;
    }
    
    private void drawHueSlider(final int x, int y, final int width, final int height, final float hue) {
        int step = 0;
        if (height > width) {
            Gui.drawRect(x, y, x + width, y + 4, -65536);
            y += 4;
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                this.drawGradientRect(x, y + step * (height / 6), x + width, y + (step + 1) * (height / 6), previousStep, nextStep);
                ++step;
            }
            final int sliderMinY = (int)(y + height * hue) - 4;
            Gui.drawRect(x, sliderMinY - 1, x + width, sliderMinY + 1, -1);
        }
        else {
            for (int colorIndex = 0; colorIndex < 6; ++colorIndex) {
                final int previousStep = Color.HSBtoRGB(step / 6.0f, 1.0f, 1.0f);
                final int nextStep = Color.HSBtoRGB((step + 1) / 6.0f, 1.0f, 1.0f);
                this.gradient(x + step * (width / 6), y, x + (step + 1) * (width / 6), y + height, previousStep, nextStep, true);
                ++step;
            }
            final int sliderMinX = (int)(x + width * hue);
            Gui.drawRect(sliderMinX - 1, y, sliderMinX + 1, y + height, -1);
        }
    }
    
    private void drawAlphaSlider(final int x, final int y, final int width, final int height, final float red, final float green, final float blue, final float alpha) {
        boolean left = true;
        for (int checkerBoardSquareSize = width / 2, squareIndex = -checkerBoardSquareSize; squareIndex < height; squareIndex += checkerBoardSquareSize) {
            if (!left) {
                Gui.drawRect(x, y + squareIndex, x + width, y + squareIndex + checkerBoardSquareSize, -1);
                Gui.drawRect(x + checkerBoardSquareSize, y + squareIndex, x + width, y + squareIndex + checkerBoardSquareSize, -7303024);
                if (squareIndex < height - checkerBoardSquareSize) {
                    final int minY = y + squareIndex + checkerBoardSquareSize;
                    final int maxY = Math.min(y + height, y + squareIndex + checkerBoardSquareSize * 2);
                    Gui.drawRect(x, minY, x + width, maxY, -7303024);
                    Gui.drawRect(x + checkerBoardSquareSize, minY, x + width, maxY, -1);
                }
            }
            left = !left;
        }
        this.gradient(x, y, x + width, y + height, new Color(red, green, blue, alpha).getRGB(), 0, false);
        final int sliderMinY = (int)(y + height - height * alpha);
        Gui.drawRect(x, sliderMinY - 1, x + width, sliderMinY + 1, -1);
    }
    
    private void drawPickerBase(final int pickerX, final int pickerY, final int pickerWidth, final int pickerHeight, final float red, final float green, final float blue, final float alpha) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(9);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((float)pickerX, (float)pickerY);
        GL11.glVertex2f((float)pickerX, (float)(pickerY + pickerHeight));
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)(pickerY + pickerHeight));
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)pickerY);
        GL11.glEnd();
        GL11.glDisable(3008);
        GL11.glBegin(9);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)pickerX, (float)pickerY);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glVertex2f((float)pickerX, (float)(pickerY + pickerHeight));
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)(pickerY + pickerHeight));
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)(pickerX + pickerWidth), (float)pickerY);
        GL11.glEnd();
        GL11.glEnable(3008);
        GL11.glShadeModel(7424);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    protected void gradient(final int minX, final int minY, final int maxX, final int maxY, final int startColor, final int endColor, final boolean left) {
        if (left) {
            final float startA = (startColor >> 24 & 0xFF) / 255.0f;
            final float startR = (startColor >> 16 & 0xFF) / 255.0f;
            final float startG = (startColor >> 8 & 0xFF) / 255.0f;
            final float startB = (startColor & 0xFF) / 255.0f;
            final float endA = (endColor >> 24 & 0xFF) / 255.0f;
            final float endR = (endColor >> 16 & 0xFF) / 255.0f;
            final float endG = (endColor >> 8 & 0xFF) / 255.0f;
            final float endB = (endColor & 0xFF) / 255.0f;
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(9);
            GL11.glColor4f(startR, startG, startB, startA);
            GL11.glVertex2f((float)minX, (float)minY);
            GL11.glVertex2f((float)minX, (float)maxY);
            GL11.glColor4f(endR, endG, endB, endA);
            GL11.glVertex2f((float)maxX, (float)maxY);
            GL11.glVertex2f((float)maxX, (float)minY);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
        }
        else {
            this.drawGradientRect(minX, minY, maxX, maxY, startColor, endColor);
        }
    }
    
    protected boolean check(final int minX, final int minY, final int maxX, final int maxY, final int curX, final int curY) {
        return curX >= minX && curY >= minY && curX < maxX && curY < maxY;
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == 1) {
            this.mc.displayGuiScreen((GuiScreen)Kisman.instance.clickGui);
        }
        if (keyCode == 19) {
            this.rainbowState = !this.rainbowState;
        }
        if (keyCode == 31) {
            this.syns = !this.syns;
        }
        if (keyCode == 203) {
            this.rainbowSpeed -= (float)0.1;
        }
        else if (keyCode == 205) {
            this.rainbowSpeed += (float)0.1;
        }
    }
    
    public int getColor() {
        return this.selectedColorFinal;
    }
    
    public void setColor(final int color) {
        this.selectedColorFinal = color;
    }
    
    public float getColor(final int index) {
        try {
            return this.color[index];
        }
        catch (Exception e) {
            return this.color[2];
        }
    }
    
    public float[] getColorHSB() {
        return this.color;
    }
    
    public void setColor(final int index, final float color) {
        try {
            this.color[index] = color;
        }
        catch (Exception e) {
            this.color[3] = color;
        }
    }
    
    public void setColor(final float[] color) {
        this.color[0] = color[0];
        this.color[1] = color[1];
        this.color[2] = color[2];
        this.color[3] = color[3];
    }
    
    public boolean isRainbowState() {
        return this.rainbowState;
    }
    
    public void setRainbowState(final boolean rainbowState) {
        this.rainbowState = rainbowState;
    }
}
