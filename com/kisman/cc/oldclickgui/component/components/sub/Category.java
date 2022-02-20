//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.*;
import com.kisman.cc.oldclickgui.component.components.sub.sub.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;
import java.util.*;

public class Category extends Component
{
    public boolean category;
    public int x;
    public int y;
    public ArrayList<SubComponent> components;
    public Button button;
    private Setting set;
    public int offset;
    public int opY;
    public boolean open;
    
    public Category(final Button button, final Setting set, final int offset) {
        this.components = new ArrayList<SubComponent>();
        this.category = true;
        this.button = button;
        this.set = set;
        this.offset = offset;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
        Kisman.instance.settingsManager.getSubSettingsByMod(set.getParentMod(), set).forEach(setting -> {
            if (setting.isCategoryLine()) {
                this.components.add(new LineButton());
                this.opY += 12;
            }
        });
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    public void renderComponent() {
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, -1);
        Gui.drawRect(this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 5 + 5, this.button.parent.getX() + 7 + this.button.parent.getWidth() - 7 - 3, this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4 + 5, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getTitle(), (float)((this.button.parent.getX() + 4) * 2), (float)((this.button.parent.getY() + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        if (this.open) {
            for (final SubComponent comp : this.components) {
                comp.renderComponent();
            }
        }
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (this.open) {
            this.components.forEach(component -> component.updateComponent(mouseX, mouseY));
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.open) {
            this.components.forEach(component -> component.mouseClicked(mouseX, mouseY, button));
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.button.parent.refresh();
            if (this.open) {
                this.button.setOff(this.opY + this.offset - 12, (Component)this);
                Minecraft.getMinecraft().player.sendChatMessage("1");
            }
            else {
                this.button.setOff(this.offset - 12, (Component)this);
                Minecraft.getMinecraft().player.sendChatMessage("2");
            }
        }
    }
    
    public boolean isCategory() {
        return this.category;
    }
    
    public ArrayList<SubComponent> getComponents() {
        return this.components;
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.button.parent.getX() && x < this.button.parent.getX() + 88 && y > this.button.parent.getY() + this.offset && y < this.button.parent.getY() + this.offset + 12;
    }
    
    public int getHeight() {
        if (this.open) {
            return 12 * (this.components.size() + 1);
        }
        return 0;
    }
}
