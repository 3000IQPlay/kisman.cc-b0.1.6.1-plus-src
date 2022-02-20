//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events.clickguiEvents.mouseClicked;

import com.kisman.cc.event.*;
import com.kisman.cc.event.events.clickguiEvents.drawScreen.render.*;

public class MouseClickedPreEvent extends Event
{
    public int mouseX;
    public int mouseY;
    public int mouseButton;
    public GuiRenderPostEvent.Gui gui;
    
    public MouseClickedPreEvent(final int mouseX, final int mouseY, final int mouseButton) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.mouseButton = mouseButton;
        this.gui = GuiRenderPostEvent.Gui.OldGui;
    }
    
    public MouseClickedPreEvent(final int mouseX, final int mouseY, final int mouseButton, final GuiRenderPostEvent.Gui gui) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.mouseButton = mouseButton;
        this.gui = gui;
    }
}
