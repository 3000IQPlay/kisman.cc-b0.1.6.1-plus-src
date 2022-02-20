//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.util.glu.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class JumpCircle extends Module
{
    public JumpCircle() {
        super("JumpCircle", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        final Cylinder circle = new Cylinder();
        circle.setDrawStyle(100012);
    }
}
