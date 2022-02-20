//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.oldclickgui.*;
import com.kisman.cc.util.*;

public class ExampleColorButton extends Component
{
    private Setting set;
    private Button button;
    private int offset;
    private Color color;
    
    public ExampleColorButton(final Setting set, final Button button, final int offset) {
        this.set = set;
        this.button = button;
        this.offset = offset;
    }
    
    public void renderComponent() {
        if (this.color != null) {
            final Minecraft mc = Minecraft.getMinecraft();
            Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, this.color.getRGB());
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getTitle(), (float)((this.button.parent.getX() + 4) * 2), (float)((this.button.parent.getY() + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
            GL11.glPopMatrix();
            Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
                Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.color = new Color(this.set.getRed() * 255.0f, this.set.getGreen() * 255.0f, this.set.getBlue() * 255.0f, this.set.getAlpha() * 255.0f);
    }
    
    public void newOff(final int newOff) {
        this.offset = newOff;
    }
}
