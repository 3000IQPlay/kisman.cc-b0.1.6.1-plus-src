//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;

public class Rotation
{
    private Minecraft mc;
    private float yaw;
    private float pitch;
    private final Rotate rotate;
    
    public Rotation(final float yaw, final float pitch, final Rotate rotate) {
        this.mc = Minecraft.getMinecraft();
        this.yaw = yaw;
        this.pitch = pitch;
        this.rotate = rotate;
    }
    
    public Rotation(final float yaw, final float pitch) {
        this.mc = Minecraft.getMinecraft();
        this.yaw = yaw;
        this.pitch = pitch;
        this.rotate = Rotate.NONE;
    }
    
    public void updateModelRotations() {
        if (this.mc.player != null && this.mc.world != null) {
            switch (this.rotate) {
                case PACKET: {
                    this.mc.player.renderYawOffset = this.yaw;
                    this.mc.player.rotationYawHead = this.yaw;
                    break;
                }
                case CLIENT: {
                    this.mc.player.rotationYaw = this.yaw;
                    this.mc.player.rotationPitch = this.pitch;
                    break;
                }
            }
        }
    }
    
    public void restoreRotations() {
        if (this.mc.player != null && this.mc.world != null) {
            this.yaw = this.mc.player.rotationYaw;
            this.pitch = this.mc.player.rotationPitch;
        }
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public Rotate getRotation() {
        return this.rotate;
    }
    
    public enum Rotate
    {
        PACKET, 
        CLIENT, 
        NONE;
    }
}
