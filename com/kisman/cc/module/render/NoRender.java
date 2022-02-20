//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraft.potion.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoRender extends Module
{
    public static NoRender instance;
    public Setting fog;
    public Setting hurtCam;
    public Setting armor;
    public Setting overlay;
    
    public NoRender() {
        super("NoRender", "no render", Category.RENDER);
        this.fog = new Setting("Fog", this, false);
        this.hurtCam = new Setting("HurtCam", this, false);
        this.armor = new Setting("Armor", this, false);
        this.overlay = new Setting("Overlay", this, false);
        NoRender.instance = this;
        NoRender.setmgr.rSetting(this.fog);
        NoRender.setmgr.rSetting(this.hurtCam);
        NoRender.setmgr.rSetting(this.armor);
        NoRender.setmgr.rSetting(this.overlay);
        Kisman.instance.settingsManager.rSetting(new Setting("Potion", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("Weather", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("Block", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("Lava", this, false));
    }
    
    public void update() {
        if (NoRender.mc.player == null && NoRender.mc.world == null) {
            return;
        }
        final boolean potion = Kisman.instance.settingsManager.getSettingByName(this, "Potion").getValBoolean();
        final boolean weather = Kisman.instance.settingsManager.getSettingByName(this, "Weather").getValBoolean();
        if (potion) {
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(25))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(25));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(2))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(2));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(4))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(4));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(9))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(9));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(15))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(15));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(17))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(17));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(18))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(18));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(27))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(27));
            }
            if (NoRender.mc.player.isPotionActive(Potion.getPotionById(20))) {
                NoRender.mc.player.removeActivePotionEffect(Potion.getPotionById(20));
            }
        }
        if (weather) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
    }
    
    @SubscribeEvent
    public void renderBlockEvent(final RenderBlockOverlayEvent event) {
        final boolean block = Kisman.instance.settingsManager.getSettingByName(this, "Block").getValBoolean();
        final boolean lava = Kisman.instance.settingsManager.getSettingByName(this, "Lava").getValBoolean();
        if (NoRender.mc.player != null && NoRender.mc.world != null) {
            if (block) {
                event.setCanceled(true);
            }
            if (lava) {
                event.setCanceled(true);
            }
        }
    }
}
