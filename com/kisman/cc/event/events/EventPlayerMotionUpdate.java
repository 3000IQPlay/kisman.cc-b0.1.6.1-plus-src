//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;
import java.util.function.*;
import net.minecraft.client.entity.*;

public class EventPlayerMotionUpdate extends Event
{
    protected float yaw;
    protected float pitch;
    protected double x;
    protected double y;
    protected double z;
    protected boolean onGround;
    private Consumer<EntityPlayerSP> funcToCall;
    private boolean isForceCancelled;
    
    public EventPlayerMotionUpdate(final Event.Era era, final double posX, final double posY, final double posZ, final boolean OnGround) {
        super(era);
        this.funcToCall = null;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.onGround = OnGround;
    }
    
    public Consumer<EntityPlayerSP> getFunc() {
        return this.funcToCall;
    }
    
    public void setFunct(final Consumer<EntityPlayerSP> post) {
        this.funcToCall = post;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setYaw(final double yaw) {
        this.yaw = (float)yaw;
    }
    
    public void setPitch(final double pitch) {
        this.pitch = (float)pitch;
    }
    
    public void forceCancel() {
        this.isForceCancelled = true;
    }
    
    public boolean isForceCancelled() {
        return this.isForceCancelled;
    }
    
    public void setX(final double posX) {
        this.x = posX;
    }
    
    public void setY(final double d) {
        this.y = d;
    }
    
    public void setZ(final double posZ) {
        this.z = posZ;
    }
    
    public void setOnGround(final boolean b) {
        this.onGround = b;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public boolean getOnGround() {
        return this.onGround;
    }
}
