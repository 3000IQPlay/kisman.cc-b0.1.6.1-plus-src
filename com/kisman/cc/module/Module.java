//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module;

import net.minecraft.client.*;
import com.kisman.cc.*;
import net.minecraftforge.common.*;
import com.kisman.cc.settings.*;

public class Module
{
    protected static Minecraft mc;
    public static SettingsManager setmgr;
    private String name;
    private String description;
    private String displayInfo;
    private int key;
    private int priority;
    private Category category;
    private boolean toggled;
    public boolean visible;
    
    public Module(final String name, final Category category) {
        this.visible = true;
        this.name = name;
        this.description = "";
        this.displayInfo = "";
        this.key = 0;
        this.category = category;
        this.toggled = false;
        this.priority = 1;
        Module.setmgr = Kisman.instance.settingsManager;
    }
    
    public Module(final String name, final String description, final Category category) {
        this.visible = true;
        this.name = name;
        this.description = description;
        this.displayInfo = "";
        this.key = 0;
        this.category = category;
        this.toggled = false;
        this.priority = 1;
        Module.setmgr = Kisman.instance.settingsManager;
    }
    
    public Module(final String name, final String description, final Category category, final int key) {
        this.visible = true;
        this.name = name;
        this.description = description;
        this.displayInfo = "";
        this.key = key;
        this.category = category;
        this.toggled = false;
        this.priority = 1;
        Module.setmgr = Kisman.instance.settingsManager;
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
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public boolean isToggled() {
        return this.toggled;
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
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getDisplayInfo() {
        return this.displayInfo;
    }
    
    public void setDisplayInfo(final String displayInfo) {
        this.displayInfo = displayInfo;
    }
    
    public void update() {
    }
    
    public void render() {
    }
    
    public void key() {
    }
    
    public void key(final int key) {
    }
    
    public void key(final char typedChar, final int key) {
    }
    
    public Setting register(final Setting set) {
        Module.setmgr.rSetting(set);
        return set;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    static {
        Module.mc = Minecraft.getMinecraft();
    }
}
