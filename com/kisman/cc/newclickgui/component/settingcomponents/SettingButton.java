//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component.settingcomponents;

import com.kisman.cc.module.*;
import com.kisman.cc.newclickgui.component.modulecomponents.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.newclickgui.component.settingcomponents.components.*;
import java.util.*;

public class SettingButton
{
    private ArrayList<Component> components;
    private Module parent;
    private ModuleButton button;
    private int x;
    private int y;
    private int offsetX;
    private int offsetY;
    private int width;
    
    public SettingButton(final int x, final int y, final int width, final Module parent, final ModuleButton mod) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.parent = parent;
        this.components = new ArrayList<Component>();
        this.offsetY = 0;
        if (Kisman.instance.settingsManager.getSettingsByMod(this.parent) != null) {
            for (final Setting s : Kisman.instance.settingsManager.getSettingsByMod(this.parent)) {
                if (s.isSlider()) {
                    this.components.add((Component)new Slider(200, this.y, this.offsetY, this.width, 15, this, s, parent));
                    this.offsetY += 15;
                }
            }
        }
    }
    
    public void renderComponent() {
        for (final Component comp : this.components) {
            comp.renderComponent();
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        for (final Component comp : this.components) {
            comp.updateComponent(mouseX, mouseY);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        for (final Component comp : this.components) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        for (final Component comp : this.components) {
            comp.mouseReleased(mouseX, mouseY, button);
        }
    }
}
