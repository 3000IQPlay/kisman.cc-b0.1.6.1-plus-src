//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.layout;

import com.kisman.cc.oldclickgui.csgo.*;
import java.util.*;

public class FlowLayout implements ILayoutManager
{
    private static final int DEFAULT_VERTICAL_PADDING = 7;
    private static final int DEFAULT_HORIZONTAL_PADDING = 7;
    private int verticalPadding;
    private int horizontalPadding;
    
    public FlowLayout(final int verticalPadding, final int horizontalPadding) {
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
    }
    
    public FlowLayout() {
        this(7, 7);
    }
    
    public int getVerticalPadding() {
        return this.verticalPadding;
    }
    
    public void setVerticalPadding(final int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }
    
    public int getHorizontalPadding() {
        return this.horizontalPadding;
    }
    
    public void setHorizontalPadding(final int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }
    
    @Override
    public int[] getOptimalDiemension(final List<AbstractComponent> components, final int maxWidth) {
        int width = -1;
        int height = -1;
        int currX = this.verticalPadding;
        int currY = this.horizontalPadding;
        int maxHeight = -1;
        for (final AbstractComponent component : components) {
            int newX = currX + component.getWidth() + this.verticalPadding;
            if (newX > maxWidth) {
                currY += maxHeight;
                maxHeight = -1;
                currX = this.verticalPadding;
                newX = currX + component.getWidth() + this.verticalPadding;
            }
            if (component.getHeight() + this.horizontalPadding > maxHeight) {
                maxHeight = component.getHeight() + this.horizontalPadding;
            }
            width = Math.max(width, newX);
            height = Math.max(height, currY + component.getHeight() + this.horizontalPadding);
            currX = newX;
        }
        return new int[] { width, height };
    }
    
    @Override
    public Layout buildLayout(final List<AbstractComponent> components, final int width, final int height) {
        final HashMap<AbstractComponent, int[]> map = new HashMap<AbstractComponent, int[]>();
        int currX = this.verticalPadding;
        int currY = this.horizontalPadding;
        int maxHeight = -1;
        for (final AbstractComponent component : components) {
            int newX = currX + component.getWidth() + this.verticalPadding;
            if (newX > width) {
                currY += maxHeight;
                maxHeight = -1;
                currX = this.verticalPadding;
                newX = currX + component.getWidth() + this.verticalPadding;
            }
            if (component.getHeight() + this.horizontalPadding > maxHeight) {
                maxHeight = component.getHeight() + this.horizontalPadding;
            }
            map.put(component, new int[] { currX, currY });
            currX = newX;
        }
        return new Layout(map, map.entrySet().stream().map(entry -> ((int[])(Object)entry.getValue())[1] + entry.getKey().getHeight()).max(Integer::compareTo).orElse(0), map.entrySet().stream().map(entry -> ((int[])(Object)entry.getValue())[0] + entry.getKey().getWidth()).max(Integer::compareTo).orElse(0));
    }
}
