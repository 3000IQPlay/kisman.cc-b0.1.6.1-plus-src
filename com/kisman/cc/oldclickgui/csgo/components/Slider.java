//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;
import com.kisman.cc.module.client.*;
import java.util.function.*;
import java.util.*;
import i.gishreloaded.gishcode.utils.*;

public class Slider extends AbstractComponent
{
    private static final int PREFERRED_WIDTH = 180;
    private static final int PREFERRED_HEIGHT = 24;
    private int preferredWidth;
    private int preferredHeight;
    private boolean hovered;
    private double value;
    private double minValue;
    private double maxValue;
    private NumberType numberType;
    private ValueChangeListener<Number> listener;
    private boolean changing;
    
    public Slider(final IRenderer renderer, final double value, final double minValue, final double maxValue, final NumberType numberType, final int preferredWidth, final int preferredHeight) {
        super(renderer);
        this.changing = false;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.numberType = numberType;
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
        this.setWidth(preferredWidth);
        this.setHeight(preferredHeight);
    }
    
    public Slider(final IRenderer renderer, final double value, final double minValue, final double maxValue, final NumberType numberType) {
        this(renderer, value, minValue, maxValue, numberType, 180, 24);
    }
    
    public void render() {
        this.renderer.drawRect(this.x, this.y, this.getWidth(), this.getHeight(), (this.hovered || this.changing) ? Window.SECONDARY_FOREGROUND : Window.TERTIARY_FOREGROUND);
        this.renderer.drawOutline(this.x, this.y, this.getWidth(), this.getHeight(), 1.0f, (this.hovered || this.changing) ? Window.SECONDARY_OUTLINE : Window.SECONDARY_FOREGROUND);
        final int sliderWidth = 4;
        final double sliderPos = (this.value - this.minValue) / (this.maxValue - this.minValue) * (this.getWidth() - sliderWidth);
        this.renderer.drawRect(this.x + sliderPos, this.y + 2, sliderWidth, this.getHeight() - 3, (this.hovered || this.changing) ? Window.TERTIARY_FOREGROUND : Window.SECONDARY_FOREGROUND);
        final String text = this.numberType.getFormatter().apply(this.value);
        this.renderer.drawString(this.x + this.getWidth() / 2 - this.renderer.getStringWidth(text) / 2, this.y + this.getHeight() / 2 - this.renderer.getStringHeight(text) / 2, text, (Config.instance.guiAstolfo.getValBoolean() && this.hovered) ? this.renderer.astolfoColorToObj() : Window.FOREGROUND);
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        this.updateValue(x, y);
        return this.changing;
    }
    
    private void updateValue(final int x, final int y) {
        if (this.changing) {
            final double oldValue = this.value;
            final double newValue = Math.max(Math.min((x - this.x) / (double)this.getWidth() * (this.maxValue - this.minValue) + this.minValue, this.maxValue), this.minValue);
            boolean change = true;
            if (oldValue != newValue && this.listener != null) {
                change = this.listener.onValueChange(newValue);
            }
            if (change) {
                this.value = newValue;
            }
        }
    }
    
    private void updateHovered(final int x, final int y, final boolean offscreen) {
        this.hovered = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.getHeight());
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        if (button == 0) {
            this.updateHovered(x, y, offscreen);
            if (this.hovered) {
                this.changing = true;
                this.updateValue(x, y);
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseReleased(final int button, final int x, final int y, final boolean offscreen) {
        if (button == 0) {
            this.updateHovered(x, y, offscreen);
            if (this.changing) {
                this.changing = false;
                this.updateValue(x, y);
                return true;
            }
        }
        return false;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public void setListener(final ValueChangeListener<Number> listener) {
        this.listener = listener;
    }
    
    public enum NumberType
    {
        PERCENT(number -> String.format(Locale.ENGLISH, "%.1f%%", number.floatValue())), 
        TIME(number -> Utils.formatTime(number.longValue())), 
        DECIMAL(number -> String.format(Locale.ENGLISH, "%.4f", number.floatValue())), 
        INTEGER(number -> Long.toString(number.longValue()));
        
        private Function<Number, String> formatter;
        
        private NumberType(final Function<Number, String> formatter) {
            this.formatter = formatter;
        }
        
        public Function<Number, String> getFormatter() {
            return this.formatter;
        }
    }
}
