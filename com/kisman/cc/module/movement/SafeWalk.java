//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import com.kisman.cc.event.*;

public class SafeWalk extends Module
{
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public SafeWalk() {
        super("SafeWalk", "SafeWalk", Category.MOVEMENT);
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra() == Event.Era.PRE) {
                double x = event.getX();
                final double y = event.getY();
                double z = event.getZ();
                if (SafeWalk.mc.player.onGround) {
                    final double increment = 0.05;
                    while (x != 0.0 && this.isOffsetBBEmpty(x, -1.0, 0.0)) {
                        if (x < increment && x >= -increment) {
                            x = 0.0;
                        }
                        else if (x > 0.0) {
                            x -= increment;
                        }
                        else {
                            x += increment;
                        }
                    }
                    while (z != 0.0 && this.isOffsetBBEmpty(0.0, -1.0, z)) {
                        if (z < increment && z >= -increment) {
                            z = 0.0;
                        }
                        else if (z > 0.0) {
                            z -= increment;
                        }
                        else {
                            z += increment;
                        }
                    }
                    while (x != 0.0 && z != 0.0 && this.isOffsetBBEmpty(x, -1.0, z)) {
                        x = ((x < increment && x >= -increment) ? 0.0 : ((x > 0.0) ? (x -= increment) : (x += increment)));
                        if (z < increment && z >= -increment) {
                            z = 0.0;
                        }
                        else if (z > 0.0) {
                            z -= increment;
                        }
                        else {
                            z += increment;
                        }
                    }
                }
                event.setX(x);
                event.setY(y);
                event.setZ(z);
                event.cancel();
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    public boolean isOffsetBBEmpty(final double offsetX, final double offsetY, final double offsetZ) {
        final EntityPlayerSP playerSP = SafeWalk.mc.player;
        return SafeWalk.mc.world.getCollisionBoxes((Entity)playerSP, playerSP.getEntityBoundingBox().offset(offsetX, offsetY, offsetZ)).isEmpty();
    }
}
