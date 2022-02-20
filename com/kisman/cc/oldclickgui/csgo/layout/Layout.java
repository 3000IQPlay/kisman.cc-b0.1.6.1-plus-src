//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.layout;

import java.util.*;
import com.kisman.cc.oldclickgui.csgo.*;

public class Layout
{
    private Map<AbstractComponent, int[]> componentLocations;
    private int maxHeight;
    private int maxWidth;
    
    Layout(final Map<AbstractComponent, int[]> componentLocations, final int maxHeight, final int maxWidth) {
        this.componentLocations = componentLocations;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }
    
    public Map<AbstractComponent, int[]> getComponentLocations() {
        return this.componentLocations;
    }
    
    public void setComponentLocations(final Map<AbstractComponent, int[]> componentLocations) {
        this.componentLocations = componentLocations;
    }
    
    public int getMaxHeight() {
        return this.maxHeight;
    }
    
    public void setMaxHeight(final int maxHeight) {
        this.maxHeight = maxHeight;
    }
    
    public int getMaxWidth() {
        return this.maxWidth;
    }
    
    public void setMaxWidth(final int maxWidth) {
        this.maxWidth = maxWidth;
    }
}
