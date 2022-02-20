//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import java.util.*;
import com.kisman.cc.settings.*;
import net.minecraft.util.*;

public class Particle extends Module
{
    public Particle() {
        super("Particle", "Particle", Category.RENDER);
        Kisman.instance.settingsManager.rSetting(new Setting("RenderMode", this, "Single", new ArrayList<String>(Arrays.asList("Single", "Multy"))));
        Kisman.instance.settingsManager.rSetting(new Setting("Particle", this, "Particle"));
        Kisman.instance.settingsManager.rSetting(new Setting("Heart", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("Crit", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    public void update() {
        if (Particle.mc.player == null && Particle.mc.world == null) {
            return;
        }
        final String renderMode = Kisman.instance.settingsManager.getSettingByName(this, "RenderMode").getValString();
        final boolean heart = Kisman.instance.settingsManager.getSettingByName(this, "Heart").getValBoolean();
        final boolean crit = Kisman.instance.settingsManager.getSettingByName(this, "Crit").getValBoolean();
        if (renderMode.equalsIgnoreCase("Single") && heart) {
            Particle.mc.world.spawnParticle(EnumParticleTypes.HEART, Particle.mc.player.posX, Particle.mc.player.posY, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
        }
        if (renderMode.equalsIgnoreCase("Single") && crit) {
            Particle.mc.world.spawnParticle(EnumParticleTypes.CRIT, Particle.mc.player.posX, Particle.mc.player.posY, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
        }
        if (renderMode.equalsIgnoreCase("Multy") && heart) {
            Particle.mc.world.spawnParticle(EnumParticleTypes.HEART, Particle.mc.player.posX, Particle.mc.player.posY, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
            Particle.mc.world.spawnParticle(EnumParticleTypes.HEART, Particle.mc.player.posX, Particle.mc.player.posY + 0.5, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
            Particle.mc.world.spawnParticle(EnumParticleTypes.HEART, Particle.mc.player.posX, Particle.mc.player.posY + 0.5, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
            Particle.mc.world.spawnParticle(EnumParticleTypes.HEART, Particle.mc.player.posX, Particle.mc.player.posY + 0.5, Particle.mc.player.posZ, Particle.mc.player.motionX, Particle.mc.player.motionY, Particle.mc.player.motionZ, new int[] { 1 });
        }
    }
}
