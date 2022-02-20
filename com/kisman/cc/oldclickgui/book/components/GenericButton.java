//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.book.components;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import java.awt.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class GenericButton extends ActionButton
{
    public FontRenderer fontRenderer;
    
    public GenericButton(final int id, final int x, final int y, final String text) {
        super(id, x, y, 0, 0, text);
        this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.width = this.fontRenderer.getStringWidth(this.displayString) + 4;
        this.height = this.fontRenderer.FONT_HEIGHT + 4;
    }
    
    public void drawButton(final Minecraft client, final int mouseX, final int mouseY, final float partialTicks) {
        if (this.enabled) {
            final int x_end = this.x + this.width;
            final int y_end = this.y + this.height;
            drawRect(this.x, this.y, x_end, y_end, new Color(0, 0, 0, 255).getRGB());
            drawRect(this.x, this.y, x_end, this.y + 1, -1);
            drawRect(this.x, this.y, this.x + 1, y_end, -1);
            drawRect(this.x, y_end - 1, x_end, y_end, -1);
            drawRect(x_end - 1, this.y, x_end, y_end, -1);
            this.fontRenderer.drawString(this.displayString, this.x + 2, this.y + 2, -1);
        }
    }
    
    public void onClick(final GuiScreen parent) {
    }
}
