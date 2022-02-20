//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component.modulecomponents;

import com.kisman.cc.module.*;
import com.kisman.cc.newclickgui.component.catcomponents.*;
import com.kisman.cc.newclickgui.component.modulecomponents.components.*;
import com.kisman.cc.newclickgui.component.settingcomponents.*;
import java.awt.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.client.gui.*;

public class ModuleButton
{
    private int x;
    private int y;
    private int offset;
    private String name;
    private Module parent;
    private CatButton catParent;
    private BindButton bind;
    private SettingButton set;
    private SettingButton listenSet;
    private int mouseX;
    private int mouseY;
    private boolean toggle;
    private boolean hover;
    private boolean listen;
    private boolean listenSetb;
    private Color b1;
    
    public ModuleButton(final int x, final int y, final int offset, final String name, final Module parent, final CatButton catParent) {
        this.b1 = new Color(0.3f, 0.3f, 0.3f, 0.8f);
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.name = name;
        this.parent = parent;
        this.catParent = catParent;
        this.bind = new BindButton(this.x + CustomFontUtil.getStringWidth(this.name), this.y, this.offset, this.parent);
        this.set = new SettingButton(this.x, this.y, 200, this.parent, this);
    }
    
    public void renderComponent() {
        if (this.hover) {
            GuiScreen.drawRect(this.x, this.y + this.offset, this.x + CustomFontUtil.getStringWidth(this.name) + 1, this.y + this.offset + 2 + CustomFontUtil.getFontHeight(), 2434341);
        }
        CustomFontUtil.drawStringWithShadow(this.name, this.x, this.y + this.offset, this.toggle ? 6379211 : 3158064);
        this.bind.renderComponent();
        if (this.listen) {
            this.set.renderComponent();
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.toggle = this.parent.isToggled();
        this.hover = this.isMouseOnButton(mouseX, mouseY);
        this.bind.updateComponent(mouseX, mouseY);
        if (this.listen) {
            this.set.updateComponent(mouseX, mouseY);
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        this.bind.keyTyped(typedChar, keyCode);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(this.mouseX, this.mouseY) && button == 0) {
            this.parent.setToggled(!this.parent.isToggled());
            return;
        }
        if (this.isMouseOnButton(this.mouseX, this.mouseY) && button == 1) {
            this.listen = !this.listen;
            if (this.listen) {
                this.catParent.setListenSet(this);
                return;
            }
            if (!this.listen) {
                this.catParent.setListenSet((ModuleButton)null);
                return;
            }
        }
        this.bind.mouseClicked(mouseX, mouseY, button);
        if (this.listen) {
            this.set.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        if (this.listen) {
            this.set.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x - 1 && x < this.x + CustomFontUtil.getStringWidth(this.name) + 1 && y > this.y + this.offset && y < this.y + this.offset + CustomFontUtil.getFontHeight();
    }
    
    public SettingButton getListenSet() {
        return this.listenSet;
    }
    
    public void setListenSet(final SettingButton listenSet) {
        this.listenSet = listenSet;
    }
    
    public boolean isListen() {
        return this.listen;
    }
    
    public void setListen(final boolean listen) {
        this.listen = listen;
    }
}
