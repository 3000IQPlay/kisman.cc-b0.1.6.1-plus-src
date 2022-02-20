//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;

public class Label extends AbstractComponent
{
    private String text;
    
    public Label(final IRenderer renderer, final String text) {
        super(renderer);
        this.setText(text);
    }
    
    public void render() {
        this.renderer.drawString(this.x, this.y + 11 - this.renderer.getStringHeight(this.text) / 2, this.text, Window.FOREGROUND);
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.setWidth(this.renderer.getStringWidth(text));
        this.setHeight(this.renderer.getStringHeight(text));
        this.text = text;
    }
}
