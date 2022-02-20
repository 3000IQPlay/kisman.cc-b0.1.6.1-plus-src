//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.book.components;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public abstract class ActionButton extends GuiButton
{
    public ActionButton(final int id, final int x, final int y, final int width, final int height, final String text) {
        super(id, x, y, width, height, text);
    }
    
    public abstract void onClick(final GuiScreen p0);
}
