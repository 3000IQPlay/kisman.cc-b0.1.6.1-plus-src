//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui;

import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.*;
import com.kisman.cc.oldclickgui.block.*;
import net.minecraft.client.*;
import java.io.*;

@SideOnly(Side.CLIENT)
public class BlockGui extends GuiScreen
{
    public Frame frame;
    public int x;
    public int y;
    public int heigth;
    public int width;
    
    public BlockGui() {
        this.heigth = 50;
        this.width = 13;
        this.x = Minecraft.getMinecraft().displayWidth;
        this.y = Minecraft.getMinecraft().displayWidth;
        this.frame = new Frame("Example", this.x, this.y, this.heigth, this.width);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.frame.renderFrame(this.fontRenderer);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) {
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
    }
    
    public void initGui() {
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}
