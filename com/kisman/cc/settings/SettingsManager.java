//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.settings;

import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.hud.hudmodule.*;

public class SettingsManager
{
    private ArrayList<Setting> settings;
    private ArrayList<Setting> subsetting;
    
    public SettingsManager() {
        this.settings = new ArrayList<Setting>();
        this.subsetting = new ArrayList<Setting>();
    }
    
    public void rSubSetting(final Setting in) {
        this.subsetting.add(in);
    }
    
    public void rSetting(final Setting in) {
        this.settings.add(in);
    }
    
    public void rSettings(final Setting... in) {
        for (final Setting set : in) {
            this.settings.add(set);
        }
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    public ArrayList<Setting> getSubSettingsByMod(final Module mod, final Setting set) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.subsetting) {
            if (s.getParentMod() == mod && s.getSetparent() == set) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public ArrayList<Setting> getSettingsByMod(final Module mod) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.getSettings()) {
            if (s.getParentMod() == mod) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public ArrayList<Setting> getSettingsByHudMod(final HudModule mod) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.getSettings()) {
            if (s.isHud() && s.getParentHudModule().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public Setting getSubSettingByName(final Module mod, final Setting set, final String name) {
        for (final Setting s : this.subsetting) {
            if (set.isHud()) {
                return null;
            }
            if (set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod && set.getSetparent() == set) {
                return set;
            }
        }
        System.out.println("[kisman.cc] Error Sub Setting NOT found: '" + name + "'!");
        return null;
    }
    
    public Setting getSettingByName(final Module mod, final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.isHud()) {
                return null;
            }
            if (set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod) {
                return set;
            }
        }
        System.err.println("[kisman.cc] Error Setting NOT found: '" + name + "'!");
        return null;
    }
    
    public Setting getHudSettingByName(final HudModule mod, final String name) {
        try {
            for (final Setting set : this.getSettings()) {
                if (!set.isHud()) {
                    return null;
                }
                if (set.getName().equalsIgnoreCase(name) && set.getParentHudModule() == mod) {
                    return set;
                }
            }
            System.err.println("[kisman.cc] Error HUD Setting NOT found: '" + name + "'!");
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public Setting getSettingByIndex(final int index) {
        try {
            for (final Setting set : this.getSettings()) {
                if (set.getIndex() == index) {
                    return set;
                }
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }
}
