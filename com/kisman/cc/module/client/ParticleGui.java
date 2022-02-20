//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ParticleGui extends Module
{
    public static ParticleGui instance;
    public Setting rainbow;
    
    public ParticleGui() {
        super("Particle", "Particle", Category.CLIENT);
        this.rainbow = new Setting("RainBow", this, false);
        ParticleGui.instance = this;
        ParticleGui.setmgr.rSetting(this.rainbow);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        Kisman.instance.particleSystem.render();
    }
}
