//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import net.minecraft.util.math.*;
import com.kisman.cc.event.events.clickguiEvents.drawScreen.render.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.clickguiEvents.mouseClicked.*;
import com.kisman.cc.event.events.clickguiEvents.mouseReleased.*;
import java.util.function.*;
import com.kisman.cc.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.*;

public class ColorPickerSimpleButton extends Component
{
    public float PICKER_HEIGHT;
    private Setting set;
    public Button button;
    private Vec2f selectorPosition;
    private float brightnessPosition;
    private float transparencyPosition;
    private final ColorHolder selectedColor;
    private Vec2f pos;
    public int offset;
    public boolean open;
    private boolean drag;
    @EventHandler
    private final Listener<GuiRenderPostEvent> listener;
    @EventHandler
    private final Listener<MouseClickedPreEvent> listener1;
    @EventHandler
    private final Listener<MouseReleasedPreEvent> listener2;
    
    public ColorPickerSimpleButton(final Setting s, final Button parent, final int offset) {
        this.PICKER_HEIGHT = 64.0f;
        this.listener = (Listener<GuiRenderPostEvent>)new Listener(event -> {
            if (event.gui.equals((Object)GuiRenderPostEvent.Gui.OldGui)) {}
        }, new Predicate[0]);
        this.listener1 = (Listener<MouseClickedPreEvent>)new Listener(event -> {
            if (event.gui.equals((Object)GuiRenderPostEvent.Gui.OldGui)) {}
        }, new Predicate[0]);
        this.listener2 = (Listener<MouseReleasedPreEvent>)new Listener(event -> {
            if (event.gui.equals((Object)GuiRenderPostEvent.Gui.OldGui)) {
                this.drag = false;
            }
        }, new Predicate[0]);
        this.set = s;
        this.button = parent;
        this.offset = offset;
        final float[] color = s.getColorHSB();
        this.selectedColor = new ColorHolder(color[0], color[1], color[2], color[3]);
        this.pos = new Vec2f((float)this.button.parent.getX(), (float)(this.button.parent.getY() + offset));
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
    }
    
    public void renderComponent() {
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
        final Vec2f pos = new Vec2f((float)this.button.parent.getX(), (float)(this.button.parent.getY() + this.offset));
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.pos = new Vec2f((float)this.button.parent.getX(), (float)(this.button.parent.getY() + this.offset));
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
        }
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x >= this.pos.x && x <= this.pos.x + 98.0f && y >= this.pos.y && y <= this.pos.y + 14.0f;
    }
    
    public static class ColorHolder
    {
        private float hue;
        private float saturation;
        private float brightness;
        private float transparency;
        
        public ColorHolder(final float hue, final float saturation, final float brightness, final float transparency) {
            this.hue = hue;
            this.saturation = saturation;
            this.brightness = brightness;
            this.transparency = transparency;
        }
        
        public float getHue() {
            return this.hue;
        }
        
        public void setHue(final float in) {
            this.hue = in;
        }
        
        public float getSaturation() {
            return this.saturation;
        }
        
        public void setSaturation(final float in) {
            this.saturation = in;
        }
        
        public float getBrightness() {
            return this.brightness;
        }
        
        public void setBrightness(final float in) {
            this.brightness = in;
        }
        
        public float getTransparency() {
            return this.transparency;
        }
        
        public void setTransparency(final float in) {
            this.transparency = in;
        }
    }
}
