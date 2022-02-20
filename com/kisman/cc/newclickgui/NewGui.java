//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui;

import net.minecraft.client.gui.*;
import net.minecraftforge.fml.relauncher.*;
import com.kisman.cc.newclickgui.component.*;
import java.io.*;

@SideOnly(Side.CLIENT)
public class NewGui extends GuiScreen
{
    private Frame frame;
    
    public NewGui() {
        this.frame = new Frame(1, 1, 256, 400, this.fontRenderer);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.frame.renderComponent();
        this.frame.updateComponent(mouseX, mouseY);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        this.frame.keyTyped(typedChar, keyCode);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.frame.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        this.frame.mouseReleased(mouseX, mouseY, state);
    }
    
    public void updateScreen() {
        super.updateScreen();
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
    }
    
    public boolean doesGuiPauseGame() {
        return true;
    }
}
