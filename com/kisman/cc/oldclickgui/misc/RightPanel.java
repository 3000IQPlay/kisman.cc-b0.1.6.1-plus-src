//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.misc;

import net.minecraftforge.fml.relauncher.*;
import com.kisman.cc.module.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.oldclickgui.book.components.*;
import java.io.*;

@SideOnly(Side.CLIENT)
public abstract class RightPanel extends GuiScreen
{
    protected Module dependence;
    protected GuiScreen parent;
    
    public RightPanel() {
        final ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        this.setWorldAndResolution(Minecraft.getMinecraft(), scale.getScaledWidth(), scale.getScaledHeight());
        this.parent = null;
        this.dependence = null;
    }
    
    public void setParent(final GuiScreen parent) {
        this.parent = parent;
    }
    
    public void dependsOn(final Module dependence) {
        this.dependence = dependence;
    }
    
    protected void actionPerformed(final GuiButton btn) throws IOException {
        if (btn instanceof ActionButton) {
            ((ActionButton)btn).onClick((GuiScreen)this);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    public void mouseWheel(final int wheel) {
    }
    
    public void keyTyped(final char keyChar, final int keyCode) throws IOException {
    }
}
