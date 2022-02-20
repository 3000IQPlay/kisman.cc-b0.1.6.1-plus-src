//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.util.*;
import com.kisman.cc.mixin.mixins.accessor.*;
import net.minecraft.client.entity.*;

public class NoSlowSneak extends Module
{
    public NoSlowSneak() {
        super("NoSlowSneak", "NoSlowSneak", Category.MOVEMENT);
    }
    
    public void update() {
        if (NoSlowSneak.mc.player == null && NoSlowSneak.mc.world == null) {
            return;
        }
        if (NoSlowSneak.mc.player.isSneaking()) {
            if (NoSlowSneak.mc.gameSettings.keyBindForward.isKeyDown()) {
                NoSlowSneak.mc.player.jumpMovementFactor = 0.1f;
                if (NoSlowSneak.mc.player.onGround) {
                    final EntityPlayerSP player = NoSlowSneak.mc.player;
                    player.motionX *= 5.0;
                    final EntityPlayerSP player2 = NoSlowSneak.mc.player;
                    player2.motionZ *= 5.0;
                    final EntityPlayerSP player3 = NoSlowSneak.mc.player;
                    player3.motionX /= 3.1495;
                    final EntityPlayerSP player4 = NoSlowSneak.mc.player;
                    player4.motionZ /= 3.1495;
                    MovementUtil.strafe(0.1245f);
                    if (NoSlowSneak.mc.gameSettings.keyBindBack.isKeyDown()) {
                        NoSlowSneak.mc.player.jumpMovementFactor = 0.08f;
                        if (NoSlowSneak.mc.player.onGround) {
                            final EntityPlayerSP player5 = NoSlowSneak.mc.player;
                            player5.motionX *= -5.0;
                            final EntityPlayerSP player6 = NoSlowSneak.mc.player;
                            player6.motionZ *= -5.0;
                            final EntityPlayerSP player7 = NoSlowSneak.mc.player;
                            player7.motionX /= -3.1495;
                            final EntityPlayerSP player8 = NoSlowSneak.mc.player;
                            player8.motionZ /= -3.1495;
                            MovementUtil.strafe(0.1245f);
                        }
                    }
                }
            }
        }
        else {
            NoSlowSneak.mc.player.jumpMovementFactor = 0.02f;
            ((AccessorEntityPlayer)NoSlowSneak.mc.player).setSpeedInAir(0.02f);
        }
    }
}
