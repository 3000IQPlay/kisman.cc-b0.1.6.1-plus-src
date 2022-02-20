//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub.itemsButton;

import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;

public class IButton
{
    private Minecraft mc;
    public int x;
    public int y;
    public int offset;
    private int armourCompress;
    private int armourSpacing;
    public ItemStack item;
    private boolean hover;
    private boolean toggle;
    
    public IButton(final int x, final int y, final ItemStack item, final int offset) {
        this.mc = Minecraft.getMinecraft();
        this.armourCompress = 2;
        this.armourSpacing = 20;
        this.x = x;
        this.y = y;
        this.item = item;
    }
    
    public void render() {
        final RenderItem itemRender = this.mc.getRenderItem();
        GlStateManager.enableDepth();
        itemRender.zLevel = 200.0f;
        itemRender.renderItemAndEffectIntoGUI(this.item, this.x, this.y + this.offset);
        itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.item, this.x, this.y + this.offset, "");
        itemRender.zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
    }
    
    public void update(final int mouseX, final int mouseY) {
        this.hover = this.isMouseOnButton(mouseX, mouseY);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.toggle = !this.toggle;
        }
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.armourSpacing && y >= this.y && y <= this.y + this.armourSpacing;
    }
}
