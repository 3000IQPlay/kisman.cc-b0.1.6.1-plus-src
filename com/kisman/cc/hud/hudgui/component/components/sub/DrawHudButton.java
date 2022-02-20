//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import java.awt.*;
import com.kisman.cc.util.*;

public class DrawHudButton extends Component
{
    private Setting set;
    private Button parent;
    private boolean drag;
    private int dragX;
    private int dragY;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    public DrawHudButton(final Setting s, final Button parent) {
        this.drag = false;
        this.set = s;
        this.parent = parent;
        this.x1 = s.getX1();
        this.y1 = s.getY1();
        this.x2 = s.getX2();
        this.y2 = s.getY2();
        this.dragX = 0;
        this.dragY = 0;
    }
    
    @Override
    public void renderComponent() {
        Render2DUtil.drawBox(this.x1 + this.dragX, this.y1 + this.dragY, this.x2 + this.dragX, this.y2 + this.dragY, 1, Color.BLACK);
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.drag = true;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseOnButton(mouseX, mouseY) && mouseButton == 0) {
            this.drag = false;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x1 && x < this.x2 && y > this.y1 && y < this.y2;
    }
}
