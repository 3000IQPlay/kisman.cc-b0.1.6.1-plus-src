//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.book.components;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.text.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.oldclickgui.book.*;

@SideOnly(Side.CLIENT)
public class FormatButton extends ActionButton
{
    private TextFormatting format;
    private FontRenderer fontRenderer;
    
    public FormatButton(final int id, final int x, final int y, final FontRenderer fontRenderer, final TextFormatting format) {
        super(id, x, y, 0, 0, (String)null);
        this.fontRenderer = fontRenderer;
        this.format = format;
        this.width = 7;
        this.height = 7;
    }
    
    public void drawButton(final Minecraft client, final int mouseX, final int mouseY, final float partialTicks) {
        GlStateManager.pushMatrix();
        final float scale = 0.7f;
        GlStateManager.scale(scale, scale, scale);
        final int x = (int)(this.x / scale);
        final int y = (int)(this.y / scale);
        final int x_end = (int)((this.x + this.width) / scale);
        final int y_end = (int)((this.y + this.height) / scale);
        if (this.format.isColor()) {
            drawRect(x, y, x_end, y_end, 0xFF000000 | this.fontRenderer.getColorCode(this.format.toString().charAt(1)));
        }
        else {
            this.fontRenderer.drawString(this.format.toString().substring(1), x + 2, y + 1, -1);
        }
        drawRect(x, y, x_end, y + 1, -1);
        drawRect(x, y, x + 1, y_end, -1);
        drawRect(x, y_end - 1, x_end, y_end, -1);
        drawRect(x_end - 1, y, x_end, y_end, -1);
        GlStateManager.popMatrix();
    }
    
    public void onClick(final GuiScreen parent) {
        ((BookEditingGui)parent).appendFormat(this.format.toString());
    }
}
