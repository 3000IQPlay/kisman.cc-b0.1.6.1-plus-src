//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudgui;

import net.minecraft.client.gui.*;
import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.oldclickgui.component.*;
import java.util.*;
import java.io.*;

public class HudGui extends GuiScreen
{
    public static ArrayList<Frame> frames;
    
    public HudGui() {
        HudGui.frames = new ArrayList<Frame>();
        int frameX = 5;
        for (final HudCategory category : HudCategory.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            HudGui.frames.add(frame);
            frameX += frame.getWidth() + 1;
        }
    }
    
    public void initGui() {
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        for (final Frame frame : HudGui.frames) {
            frame.renderFrame(this.fontRenderer);
            frame.updatePosition(mouseX, mouseY);
            for (final Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        for (final Frame frame : HudGui.frames) {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) {
        for (final Frame frame : HudGui.frames) {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        for (final Frame frame : HudGui.frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : HudGui.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public boolean doesGuiPauseGame() {
        return true;
    }
}
