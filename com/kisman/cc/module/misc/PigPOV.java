//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.module.*;
import net.minecraft.entity.passive.*;
import com.kisman.cc.util.*;
import net.minecraft.client.renderer.entity.*;

public class PigPOV extends Module
{
    public PigPOV() {
        super("PigPOV", "", Category.MISC);
    }
    
    @Override
    public void onEnable() {
        PigPOV.mc.player.eyeHeight = 0.6f;
        PigPOV.mc.getRenderManager().entityRenderMap.put(EntityPig.class, new NoRenderPig(PigPOV.mc.getRenderManager(), PigPOV.mc));
    }
    
    @Override
    public void onDisable() {
        PigPOV.mc.player.eyeHeight = PigPOV.mc.player.getDefaultEyeHeight();
        PigPOV.mc.getRenderManager().entityRenderMap.put(EntityPig.class, new RenderPig(PigPOV.mc.getRenderManager()));
    }
}
