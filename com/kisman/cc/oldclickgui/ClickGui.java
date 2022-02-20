//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui;

import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.*;
import com.kisman.cc.util.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.event.events.clickguiEvents.drawScreen.render.*;
import com.kisman.cc.*;
import java.util.*;
import com.kisman.cc.event.events.clickguiEvents.mouseClicked.*;
import java.io.*;
import com.kisman.cc.event.events.clickguiEvents.mouseReleased.*;
import org.lwjgl.input.*;
import com.kisman.cc.module.client.*;

@SideOnly(Side.CLIENT)
public class ClickGui extends GuiScreen
{
    public static boolean line;
    public static boolean rainbowLine;
    public static boolean rainbowBackground;
    public static LineMode lineMode;
    public static LineMode setLineMode;
    public static TextMode textMode;
    public static HoveredMode hoveredMode;
    public static int RLine;
    public static int GLine;
    public static int BLine;
    public static int ALine;
    public static int RBackground;
    public static int GBackground;
    public static int BBackground;
    public static int ABackground;
    public static int RABackground;
    public static int GABackground;
    public static int BABackground;
    public static int AABackground;
    public static int RHoveredModule;
    public static int GHoveredModule;
    public static int BHoveredModule;
    public static int AHoveredModule;
    public static int RNoHoveredModule;
    public static int GNoHoveredModule;
    public static int BNoHoveredModule;
    public static int ANoHoveredModule;
    public static int RText;
    public static int GText;
    public static int BText;
    public static int AText;
    public static int RActiveText;
    public static int GActiveText;
    public static int BActiveText;
    public static int AActiveText;
    public static ArrayList<Frame> frames;
    
    public ClickGui() {
        ClickGui.frames = new ArrayList<Frame>();
        int frameX = 5;
        for (final Category category : Category.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            ClickGui.frames.add(frame);
            frameX += frame.getWidth() + 1;
        }
    }
    
    public void initGui() {
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.scrollWheelCheck();
        for (final Frame frame : ClickGui.frames) {
            frame.renderFrame(this.fontRenderer);
            frame.updatePosition(mouseX, mouseY);
            for (final Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
        final GuiRenderPostEvent event = new GuiRenderPostEvent(mouseX, mouseY, partialTicks);
        Kisman.EVENT_BUS.post((Object)event);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        final MouseClickedPreEvent event = new MouseClickedPreEvent(mouseX, mouseY, mouseButton);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            return;
        }
        for (final Frame frame : ClickGui.frames) {
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
        for (final Frame frame : ClickGui.frames) {
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
        final MouseReleasedPreEvent event = new MouseReleasedPreEvent(mouseX, mouseY, state);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            return;
        }
        for (final Frame frame : ClickGui.frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : ClickGui.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    private void scrollWheelCheck() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            for (final Frame frame : ClickGui.frames) {
                frame.setY(frame.getY() - (int)Config.instance.scrollSpeed.getValDouble());
            }
        }
        else if (dWheel > 0) {
            for (final Frame frame : ClickGui.frames) {
                frame.setY(frame.getY() + (int)Config.instance.scrollSpeed.getValDouble());
            }
        }
    }
    
    public static LineMode getSetLineMode() {
        return ClickGui.setLineMode;
    }
    
    public static void setSetLineMode(final LineMode setLineMode) {
        ClickGui.setLineMode = setLineMode;
    }
    
    public static int getRABackground() {
        return ClickGui.RABackground;
    }
    
    public static void setRABackground(final int RABackground) {
        ClickGui.RABackground = RABackground;
    }
    
    public static int getGABackground() {
        return ClickGui.GABackground;
    }
    
    public static void setGABackground(final int GABackground) {
        ClickGui.GABackground = GABackground;
    }
    
    public static int getBABackground() {
        return ClickGui.BABackground;
    }
    
    public static void setBABackground(final int BABackground) {
        ClickGui.BABackground = BABackground;
    }
    
    public static int getAABackground() {
        return ClickGui.AABackground;
    }
    
    public static void setAABackground(final int AABackground) {
        ClickGui.AABackground = AABackground;
    }
    
    public static boolean isRainbowBackground() {
        return ClickGui.rainbowBackground;
    }
    
    public static void setRainbowBackground(final boolean rainbowBackground) {
        ClickGui.rainbowBackground = rainbowBackground;
    }
    
    public static boolean isRainbowLine() {
        return ClickGui.rainbowLine;
    }
    
    public static void setRainbowLine(final boolean rainbowLine) {
        ClickGui.rainbowLine = rainbowLine;
    }
    
    public static HoveredMode getHoveredMode() {
        return ClickGui.hoveredMode;
    }
    
    public static void setHoveredMode(final HoveredMode hoveredMode) {
        ClickGui.hoveredMode = hoveredMode;
    }
    
    public static int getRHoveredModule() {
        return ClickGui.RHoveredModule;
    }
    
    public static void setRHoveredModule(final int RHoveredModule) {
        ClickGui.RHoveredModule = RHoveredModule;
    }
    
    public static int getGHoveredModule() {
        return ClickGui.GHoveredModule;
    }
    
    public static void setGHoveredModule(final int GHoveredModule) {
        ClickGui.GHoveredModule = GHoveredModule;
    }
    
    public static int getBHoveredModule() {
        return ClickGui.BHoveredModule;
    }
    
    public static void setBHoveredModule(final int BHoveredModule) {
        ClickGui.BHoveredModule = BHoveredModule;
    }
    
    public static int getAHoveredModule() {
        return ClickGui.AHoveredModule;
    }
    
    public static void setAHoveredModule(final int AHoveredModule) {
        ClickGui.AHoveredModule = AHoveredModule;
    }
    
    public static int getRNoHoveredModule() {
        return ClickGui.RNoHoveredModule;
    }
    
    public static void setRNoHoveredModule(final int RNoHoveredModule) {
        ClickGui.RNoHoveredModule = RNoHoveredModule;
    }
    
    public static int getGNoHoveredModule() {
        return ClickGui.GNoHoveredModule;
    }
    
    public static void setGNoHoveredModule(final int GNoHoveredModule) {
        ClickGui.GNoHoveredModule = GNoHoveredModule;
    }
    
    public static int getBNoHoveredModule() {
        return ClickGui.BNoHoveredModule;
    }
    
    public static void setBNoHoveredModule(final int BNoHoveredModule) {
        ClickGui.BNoHoveredModule = BNoHoveredModule;
    }
    
    public static int getANoHoveredModule() {
        return ClickGui.ANoHoveredModule;
    }
    
    public static void setANoHoveredModule(final int ANoHoveredModule) {
        ClickGui.ANoHoveredModule = ANoHoveredModule;
    }
    
    public static int getRActiveText() {
        return ClickGui.RActiveText;
    }
    
    public static void setRActiveText(final int RActiveText) {
        ClickGui.RActiveText = RActiveText;
    }
    
    public static int getGActiveText() {
        return ClickGui.GActiveText;
    }
    
    public static void setGActiveText(final int GActiveText) {
        ClickGui.GActiveText = GActiveText;
    }
    
    public static int getBActiveText() {
        return ClickGui.BActiveText;
    }
    
    public static void setBActiveText(final int BActiveText) {
        ClickGui.BActiveText = BActiveText;
    }
    
    public static int getAActiveText() {
        return ClickGui.AActiveText;
    }
    
    public static void setAActiveText(final int AActiveText) {
        ClickGui.AActiveText = AActiveText;
    }
    
    public static TextMode getTextMode() {
        return ClickGui.textMode;
    }
    
    public static void setTextMode(final TextMode textMode) {
        ClickGui.textMode = textMode;
    }
    
    public static int getRText() {
        return ClickGui.RText;
    }
    
    public static void setRText(final int RText) {
        ClickGui.RText = RText;
    }
    
    public static int getGText() {
        return ClickGui.GText;
    }
    
    public static void setGText(final int GText) {
        ClickGui.GText = GText;
    }
    
    public static int getBText() {
        return ClickGui.BText;
    }
    
    public static void setBText(final int BText) {
        ClickGui.BText = BText;
    }
    
    public static int getAText() {
        return ClickGui.AText;
    }
    
    public static void setAText(final int AText) {
        ClickGui.AText = AText;
    }
    
    public static LineMode getLineMode() {
        return ClickGui.lineMode;
    }
    
    public static void setLineMode(final LineMode lineMode) {
        ClickGui.lineMode = lineMode;
    }
    
    public static int getALine() {
        return ClickGui.ALine;
    }
    
    public static void setALine(final int ALine) {
        ClickGui.ALine = ALine;
    }
    
    public static int getABackground() {
        return ClickGui.ABackground;
    }
    
    public static void setABackground(final int ABackground) {
        ClickGui.ABackground = ABackground;
    }
    
    public static boolean isLine() {
        return ClickGui.line;
    }
    
    public static void setLine(final boolean line) {
        ClickGui.line = line;
    }
    
    public static int getRLine() {
        return ClickGui.RLine;
    }
    
    public static void setRLine(final int RLine) {
        ClickGui.RLine = RLine;
    }
    
    public static int getGLine() {
        return ClickGui.GLine;
    }
    
    public static void setGLine(final int GLine) {
        ClickGui.GLine = GLine;
    }
    
    public static int getBLine() {
        return ClickGui.BLine;
    }
    
    public static void setBLine(final int BLine) {
        ClickGui.BLine = BLine;
    }
    
    public static int getRBackground() {
        return ClickGui.RBackground;
    }
    
    public static void setRBackground(final int RBackground) {
        ClickGui.RBackground = RBackground;
    }
    
    public static int getGBackground() {
        return ClickGui.GBackground;
    }
    
    public static void setGBackground(final int GBackground) {
        ClickGui.GBackground = GBackground;
    }
    
    public static int getBBackground() {
        return ClickGui.BBackground;
    }
    
    public static void setBBackground(final int BBackground) {
        ClickGui.BBackground = BBackground;
    }
    
    static {
        ClickGui.line = false;
        ClickGui.rainbowLine = false;
        ClickGui.rainbowBackground = false;
        ClickGui.lineMode = LineMode.LEFT;
        ClickGui.setLineMode = LineMode.SETTINGDEFAULT;
        ClickGui.textMode = TextMode.DEFAULT;
        ClickGui.hoveredMode = HoveredMode.HOVERED;
        ClickGui.RLine = 255;
        ClickGui.GLine = 0;
        ClickGui.BLine = 0;
        ClickGui.ALine = 150;
        ClickGui.RBackground = 80;
        ClickGui.GBackground = 75;
        ClickGui.BBackground = 75;
        ClickGui.ABackground = 150;
        ClickGui.RABackground = 136;
        ClickGui.GABackground = 189;
        ClickGui.BABackground = 189;
        ClickGui.AABackground = 255;
        ClickGui.RHoveredModule = 95;
        ClickGui.GHoveredModule = 95;
        ClickGui.BHoveredModule = 87;
        ClickGui.AHoveredModule = 150;
        ClickGui.RNoHoveredModule = 14;
        ClickGui.GNoHoveredModule = 14;
        ClickGui.BNoHoveredModule = 14;
        ClickGui.ANoHoveredModule = 255;
        ClickGui.RText = 166;
        ClickGui.GText = 161;
        ClickGui.BText = 160;
        ClickGui.AText = 255;
        ClickGui.RActiveText = 255;
        ClickGui.GActiveText = 255;
        ClickGui.BActiveText = 255;
        ClickGui.AActiveText = 255;
    }
}
