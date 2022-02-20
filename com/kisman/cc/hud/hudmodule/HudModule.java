//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule;

import net.minecraft.client.*;
import net.minecraftforge.common.*;

public class HudModule
{
    protected static Minecraft mc;
    private String name;
    private String description;
    private int key;
    private final HudCategory category;
    private boolean toggled;
    public boolean visible;
    private int x;
    private int y;
    private int w;
    private int h;
    
    public HudModule(final String name, final String description, final HudCategory category) {
        this.visible = true;
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }
    
    public HudModule(final String name, final HudCategory category) {
        this.visible = true;
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.name = name;
        this.description = "";
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public HudCategory getCategory() {
        return this.category;
    }
    
    public void update() {
    }
    
    public void render() {
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
    
    public int getW() {
        return this.w;
    }
    
    public void setW(final int w) {
        this.w = w;
    }
    
    public int getH() {
        return this.h;
    }
    
    public void setH(final int h) {
        this.h = h;
    }
    
    static {
        HudModule.mc = Minecraft.getMinecraft();
    }
}
