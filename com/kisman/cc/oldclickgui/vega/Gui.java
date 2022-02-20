//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega;

import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.vega.component.components.*;
import com.kisman.cc.oldclickgui.vega.component.*;
import com.kisman.cc.event.events.clickguiEvents.drawScreen.render.*;
import com.kisman.cc.*;
import java.util.*;
import java.io.*;
import com.kisman.cc.event.events.clickguiEvents.mouseClicked.*;
import com.kisman.cc.event.events.clickguiEvents.mouseReleased.*;
import org.lwjgl.input.*;
import com.kisman.cc.module.client.*;

@SideOnly(Side.CLIENT)
public class Gui extends GuiScreen
{
    public ArrayList<Frame> frames;
    
    public Gui() {
        this.frames = new ArrayList<Frame>();
        int x = 3;
        final int y = 6;
        for (final Category cat : Category.values()) {
            this.frames.add(new Frame(cat, x, y));
            x += 120;
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.scrollWheelCheck();
        for (final Frame frame : this.frames) {
            frame.renderComponent();
            for (final Button b : frame.buttons) {
                b.x = b.parent.x;
                b.y = b.parent.y;
                for (final Component comp : b.comp) {
                    comp.updateComponent(mouseX, mouseY);
                }
            }
        }
        final GuiRenderPostEvent event = new GuiRenderPostEvent(mouseX, mouseY, partialTicks, GuiRenderPostEvent.Gui.NewGui);
        Kisman.EVENT_BUS.post((Object)event);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        for (final Frame frame : this.frames) {
            if (frame.open && keyCode != 1 && !frame.buttons.isEmpty()) {
                for (final Button b : frame.buttons) {
                    b.keyTyped(typedChar, keyCode);
                }
            }
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        final MouseClickedPreEvent event = new MouseClickedPreEvent(mouseX, mouseY, mouseButton, GuiRenderPostEvent.Gui.NewGui);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            return;
        }
        for (final Frame frame : this.frames) {
            if (frame.isMouseOnButton(mouseX, mouseY) && mouseButton == 0) {
                frame.dragging = true;
                frame.dragX = mouseX - frame.x;
                frame.dragY = mouseY - frame.y;
                frame.refresh();
            }
            if (frame.isMouseOnButton(mouseX, mouseY) && mouseButton == 1) {
                frame.open = !frame.open;
            }
            if (frame.open && !frame.buttons.isEmpty()) {
                for (final Button b : frame.buttons) {
                    b.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        final MouseReleasedPreEvent event = new MouseReleasedPreEvent(mouseX, mouseY, state, GuiRenderPostEvent.Gui.NewGui);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            return;
        }
        for (final Frame frame : this.frames) {
            frame.dragging = false;
        }
        for (final Frame frame : this.frames) {
            if (frame.open && !frame.buttons.isEmpty()) {
                for (final Button b : frame.buttons) {
                    b.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public void initGui() {
        super.initGui();
    }
    
    private void scrollWheelCheck() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            for (final Frame frame : this.frames) {
                frame.y -= (int)Config.instance.scrollSpeed.getValDouble();
            }
        }
        else if (dWheel > 0) {
            for (final Frame frame : this.frames) {
                frame.y += (int)Config.instance.scrollSpeed.getValDouble();
            }
        }
    }
}
