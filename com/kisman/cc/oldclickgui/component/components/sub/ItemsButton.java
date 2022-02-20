//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import net.minecraft.client.*;
import java.util.*;
import com.kisman.cc.oldclickgui.component.components.sub.itemsButton.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;

public class ItemsButton extends Component
{
    private Minecraft mc;
    private ArrayList<IButton> buttons;
    public Button button;
    public Setting set;
    public int offset;
    private boolean hover;
    private int armourCompress;
    private int armourSpacing;
    public int blocksOffset;
    public boolean open;
    
    public ItemsButton(final Button button, final Setting set, final int offset) {
        this.mc = Minecraft.getMinecraft();
        this.armourCompress = 2;
        this.armourSpacing = 20;
        this.button = button;
        this.set = set;
        this.offset = offset;
        this.buttons = new ArrayList<IButton>();
        int iterations = 0;
        for (int i = 0; i < set.getItems().length; ++i) {
            this.buttons.add(new IButton(button.x + 3 + iterations * (this.armourCompress + this.armourSpacing), button.y + this.offset + 12, set.getItems()[i], offset + 12));
            ++iterations;
        }
    }
    
    public void renderComponent() {
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, this.hover ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        Gui.drawRect(this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + this.mc.fontRenderer.FONT_HEIGHT - 5 + 5, this.button.parent.getX() + 7 + this.button.parent.getWidth() - 7 - 3, this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4 + 5, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (this.open) {
            this.buttons.stream().forEach(iButton -> iButton.render());
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getTitle(), (float)((this.button.parent.getX() + 4) * 2), (float)((this.button.parent.getY() + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hover = this.isMouseOnButton(mouseX, mouseY);
        this.buttons.stream().forEach(iButton -> iButton.update(mouseX, mouseY));
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.open = !this.open;
        }
        this.buttons.stream().forEach(iButton -> iButton.mouseClicked(mouseX, mouseY, button));
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
        this.buttons.stream().forEach(iButton -> iButton.setOff(newOff + 12));
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.button.parent.getX() && x < this.button.parent.getX() + 88 && y > this.button.parent.getY() + this.offset && y < this.button.parent.getY() + this.offset + 12;
    }
}
