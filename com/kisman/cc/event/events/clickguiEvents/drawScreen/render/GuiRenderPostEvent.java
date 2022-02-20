//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events.clickguiEvents.drawScreen.render;

import com.kisman.cc.event.*;

public class GuiRenderPostEvent extends Event
{
    public int mouseX;
    public int mouseY;
    public float partialTicks;
    public Gui gui;
    
    public GuiRenderPostEvent(final int mouseX, final int mouseY, final float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
    }
    
    public GuiRenderPostEvent(final int mouseX, final int mouseY, final float partialTicks, final Gui gui) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.partialTicks = partialTicks;
        this.gui = gui;
    }
    
    public enum Gui
    {
        NewGui, 
        OldGui, 
        CSGOGui;
    }
}
