//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component.catcomponents;

import com.kisman.cc.newclickgui.component.modulecomponents.*;
import com.kisman.cc.newclickgui.component.*;
import java.awt.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.*;
import net.minecraft.client.gui.*;
import java.util.*;
import com.kisman.cc.newclickgui.component.settingcomponents.*;
import com.kisman.cc.module.*;

public class CatButton
{
    private ArrayList<ModuleButton> modules;
    private int moduleX;
    private int moduleY;
    private int moduleOffset;
    private Category cat;
    private ModuleButton listenSet;
    private int x;
    private int y;
    private int offset;
    private int width;
    private String name;
    private boolean listen;
    private boolean listenSetb;
    private boolean hover;
    private Frame parent;
    private Color b1;
    
    public CatButton(final int x, final int y, final int offset, final int width, final String title, final Frame parent, final Category cat) {
        this.b1 = new Color(0.3f, 0.3f, 0.3f, 0.8f);
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.width = width;
        this.moduleX = parent.getBarX() + CustomFontUtil.getStringWidth(Category.MOVEMENT.name()) + 5;
        this.moduleY = parent.getBarY() + 1;
        this.moduleOffset = 0;
        this.name = title;
        this.parent = parent;
        this.cat = cat;
        this.modules = new ArrayList<ModuleButton>();
        Kisman.instance.moduleManager.modules.stream().filter(module -> module.getCategory() == this.cat).forEach(module -> {
            this.modules.add(new ModuleButton(this.moduleX, this.moduleY, this.moduleOffset, module.getName(), module, this));
            this.moduleOffset += CustomFontUtil.getFontHeight() + 2;
        });
    }
    
    public void renderComponent() {
        if (this.hover) {
            GuiScreen.drawRect(this.x, this.y + this.offset, this.x + CustomFontUtil.getStringWidth(this.name) + 1, this.y + this.offset + 2 + CustomFontUtil.getFontHeight(), 2434341);
        }
        CustomFontUtil.drawStringWithShadow(this.name, this.x, this.y + this.offset, this.listen ? 6379211 : 3158064);
        if (this.listen) {
            this.modules.stream().forEach(moduleButton -> moduleButton.renderComponent());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (this.listen) {
            this.modules.stream().forEach(moduleButton -> moduleButton.updateComponent(mouseX, mouseY));
        }
        this.hover = this.isMouseOnButton(mouseX, mouseY);
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.listen) {
            for (final ModuleButton mod : this.modules) {
                mod.keyTyped(typedChar, keyCode);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.da(mouseX, mouseY) && button == 1 && this.listenSet != null) {
            this.listenSet.setListen(false);
            this.listenSet.setListenSet(null);
            for (final ModuleButton mod : this.modules) {
                mod.setListen(false);
                System.out.println("1");
            }
        }
        if (this.parent.getCatListen() == this) {
            for (final ModuleButton mod : this.modules) {
                mod.mouseClicked(mouseX, mouseY, button);
            }
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.listen = !this.listen;
            if (this.listen) {
                this.parent.setListenCat(this);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        if (this.listen) {
            for (final ModuleButton mod : this.modules) {
                mod.mouseReleased(mouseX, mouseY, button);
            }
        }
    }
    
    public boolean da(final int x, final int y) {
        return x > this.moduleX && x < this.parent.getX() + 200 && y > this.moduleY && y < this.moduleY + this.offset;
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + CustomFontUtil.getStringWidth(Category.MOVEMENT.name()) && y > this.y + this.offset && y < this.y + this.offset + CustomFontUtil.getFontHeight();
    }
    
    private boolean isMouseOnModuleFrame(final int x, final int y) {
        return x > this.moduleX - 1 && x < this.moduleX + 100 + 1 && y > this.moduleY - 1 && y < this.moduleY + this.offset + 1;
    }
    
    public boolean isListen() {
        return this.listen;
    }
    
    public void setListen(final boolean listen) {
        this.listen = listen;
    }
    
    public String getName() {
        return this.name;
    }
    
    public ModuleButton getListenSet() {
        return this.listenSet;
    }
    
    public void setListenSet(final ModuleButton listenSet) {
        this.listenSet = listenSet;
    }
    
    public boolean isListenSetB() {
        return this.listenSetb;
    }
    
    public void setListenSetB(final boolean listenSet) {
        this.listenSetb = listenSet;
    }
}
