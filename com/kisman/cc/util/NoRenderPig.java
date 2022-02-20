//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class NoRenderPig extends RenderPig
{
    private Minecraft mc;
    
    public NoRenderPig(final RenderManager manager, final Minecraft mc) {
        super(manager);
        this.mc = mc;
    }
    
    public void doRender(final EntityPig pig, final double d0, double d1, final double d2, final float f1, final float f2) {
        if (this.mc.player.getRidingEntity() == pig) {
            d1 -= 0.5;
        }
        super.doRender((EntityLiving)pig, d0, d1, d2, f1, f2);
    }
}
