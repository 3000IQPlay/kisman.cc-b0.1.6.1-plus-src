//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component;

import com.kisman.cc.newclickgui.component.catcomponents.*;
import java.awt.*;
import com.kisman.cc.*;
import com.kisman.cc.module.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.client.gui.*;
import java.util.*;

public class Frame
{
    private ArrayList<CatButton> cat;
    private CatButton listenCat;
    private FontRenderer fr;
    private String title;
    private int x;
    private int y;
    private int barX;
    private int barY;
    private int catOffset;
    private int catWidth;
    private int heigth;
    private int width;
    private Color b1;
    private Color b2;
    
    public Frame(final int x, final int y, final int heigth, final int width, final FontRenderer fr) {
        this.b1 = new Color(0.3f, 0.3f, 0.3f, 0.8f);
        this.b2 = new Color(0.16f, 0.15f, 0.15f, 1.0f);
        this.title = Kisman.getName() + " | " + Kisman.getVersion();
        this.x = x;
        this.y = y;
        this.barX = x + 7;
        this.barY = y + 7;
        this.heigth = heigth;
        this.width = width + 6;
        this.fr = fr;
        this.cat = new ArrayList<CatButton>();
        this.listenCat = null;
        this.catOffset = 0;
        this.catWidth = CustomFontUtil.getStringWidth(Category.MOVEMENT.name());
        for (final Category cat : Category.values()) {
            this.cat.add(new CatButton(this.barX + 1, this.barY + 1, this.catOffset, this.catWidth, cat.name(), this, cat));
            this.catOffset += CustomFontUtil.getFontHeight() + 2;
        }
    }
    
    public void renderComponent() {
        GuiScreen.drawRect(this.x, this.y, this.x + this.width, this.y + this.heigth, new Color(2434341).hashCode());
        GuiScreen.drawRect(this.x + 7, this.y + 7, this.x + this.width - 7, this.y + this.heigth - 7, new Color(1381653).hashCode());
        this.renderCategories();
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.cat.stream().forEach(catButton -> catButton.updateComponent(mouseX, mouseY));
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        for (final CatButton cat : this.cat) {
            cat.keyTyped(typedChar, keyCode);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnCategoryFrame(mouseX, mouseY) && this.listenCat != null) {
            this.listenCat.setListen(false);
        }
        this.mouseClickedComponent(mouseX, mouseY, button);
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        this.mouseReleasedComponent(mouseX, mouseY, button);
    }
    
    public boolean isMouseOnFrame(final int x, final int y) {
        return x > this.x + 6 && x < this.x + this.width - 8 && y > this.y + 6 && this.y > this.y + this.heigth - 8;
    }
    
    public boolean isMouseOnCategoryFrame(final int x, final int y) {
        return x > this.barX && x < this.barX + 2 + CustomFontUtil.getStringWidth(Category.MOVEMENT.name()) && y > this.barY && y < this.barY + this.catOffset;
    }
    
    private void renderCategories() {
        for (final CatButton cat : this.cat) {
            cat.renderComponent();
        }
    }
    
    private void mouseClickedComponent(final int mouseX, final int mouseY, final int button) {
        for (final CatButton cat : this.cat) {
            cat.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    private void mouseReleasedComponent(final int mouseX, final int mouseY, final int button) {
        for (final CatButton cat : this.cat) {
            cat.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    private void setOff(final int offset) {
        this.catOffset += offset;
    }
    
    public String getCatListenName() {
        return this.listenCat.getName();
    }
    
    public CatButton getCatListen() {
        return this.listenCat;
    }
    
    public void setListenCat(final CatButton cat) {
        this.listenCat = cat;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getBarX() {
        return this.barX;
    }
    
    public void setBarX(final int barX) {
        this.barX = barX;
    }
    
    public int getBarY() {
        return this.barY;
    }
    
    public void setBarY(final int barY) {
        this.barY = barY;
    }
    
    public int getCatOffset() {
        return this.catOffset;
    }
    
    public void setCatOffset(final int catOffset) {
        this.catOffset = catOffset;
    }
    
    public int getCatWidth() {
        return this.catWidth;
    }
    
    public void setCatWidth(final int catWidth) {
        this.catWidth = catWidth;
    }
    
    public int getHeigth() {
        return this.heigth;
    }
    
    public void setHeigth(final int heigth) {
        this.heigth = heigth;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
}
