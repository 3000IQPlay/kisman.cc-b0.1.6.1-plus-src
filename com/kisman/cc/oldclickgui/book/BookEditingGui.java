//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.book;

import net.minecraftforge.fml.relauncher.*;
import java.lang.reflect.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.text.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.oldclickgui.book.components.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class BookEditingGui extends GuiScreenBook
{
    private int x;
    private int y;
    private Field gettingSigned;
    private Field bookTitle;
    private Method insert;
    
    public BookEditingGui(final EntityPlayer player, final ItemStack book) {
        super(player, book, true);
    }
    
    public void initGui() {
        super.initGui();
        this.x = (this.width - 192) / 2 - 58;
        this.y = 9;
        final GuiLabel title = new GuiLabel(this.fontRenderer, 0, this.x + 2, this.y + 4, 60, 16, -1).setCentered();
        title.addLine("Formatting");
        this.labelList.add(title);
        for (int i = 0; i < 16; ++i) {
            this.buttonList.add(new FormatButton(i, this.x + 16 + i / 8 * 24, this.y + 21 + i % 8 * 8, this.fontRenderer, TextFormatting.fromColorIndex(i)));
        }
        int i = this.y + 28 + 64;
        for (final String format : TextFormatting.getValidValues(false, true)) {
            this.buttonList.add(new FormatButton(0, this.x + 4, i, this.fontRenderer, TextFormatting.getValueByName(format)));
            i += 12;
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        Gui.drawRect(this.x, this.y, this.x + 64, this.y + 168, new Color(0, 0, 0, 255).getRGB());
        Gui.drawRect(this.x, this.y, this.x + 64, this.y + 1, -1);
        Gui.drawRect(this.x, this.y, this.x + 1, this.y + 168, -1);
        Gui.drawRect(this.x, this.y + 167, this.x + 64, this.y + 168, -1);
        Gui.drawRect(this.x + 63, this.y, this.x + 64, this.y + 168, -1);
        GlStateManager.pushMatrix();
        final float scale = 0.7f;
        GlStateManager.scale(scale, scale, scale);
        int i = this.y + 29 + 64;
        for (final String format : TextFormatting.getValidValues(false, true)) {
            final TextFormatting f = TextFormatting.getValueByName(format);
            this.drawString(this.fontRenderer, f.toString() + format, (int)((this.x + 14) / scale), (int)(i / scale), -1);
            i += 12;
        }
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void keyTyped(final char typedChat, final int keyCode) {
        try {
            final boolean signing = (boolean)this.gettingSigned.get(this);
            if (signing) {
                final String title = (String)this.bookTitle.get(this);
                super.keyTyped(typedChat, keyCode);
                if (keyCode != 14 && keyCode != 28 && keyCode != 156 && title.length() < 32) {
                    this.bookTitle.set(this, title + Character.toString(typedChat));
                }
            }
            else {
                super.keyTyped(typedChat, keyCode);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("FFP error: " + e.getMessage());
        }
    }
    
    public void appendFormat(final String format) {
        try {
            final boolean signing = (boolean)this.gettingSigned.get(this);
            if (signing) {
                final String title = (String)this.bookTitle.get(this);
                if (format.length() + title.length() <= 32) {
                    this.bookTitle.set(this, title + format);
                }
            }
            else {
                this.insert.invoke(this, format);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("FFP error: " + e.getMessage());
        }
    }
    
    protected void actionPerformed(final GuiButton button) {
        if (button instanceof ActionButton) {
            ((ActionButton)button).onClick((GuiScreen)this);
        }
    }
}
