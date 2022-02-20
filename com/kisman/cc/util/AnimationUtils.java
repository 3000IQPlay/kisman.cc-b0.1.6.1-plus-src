//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

public class AnimationUtils
{
    public float value;
    public long lastTime;
    public float changePerMillisecond;
    public float start;
    public float end;
    public boolean increasing;
    
    public AnimationUtils(final float duration, final float start, final float end) {
        this((long)(duration * Float.intBitsToFloat(Float.floatToIntBits(7.1846804E-4f) ^ 0x7E465793)), start, end);
    }
    
    public AnimationUtils(final long duration, final float start, final float end) {
        this.value = start;
        this.end = end;
        this.start = start;
        this.increasing = (end > start);
        final float difference = Math.abs(start - end);
        this.changePerMillisecond = difference / duration;
        this.lastTime = System.currentTimeMillis();
    }
    
    public static AnimationUtils fromChangePerSecond(final float start, final float end, final float changePerSecond) {
        return new AnimationUtils(Math.abs(start - end) / changePerSecond, start, end);
    }
    
    public void reset() {
        this.value = this.start;
        this.lastTime = System.currentTimeMillis();
    }
    
    public float getValue() {
        if (this.value == this.end) {
            return this.value;
        }
        if (this.increasing) {
            if (this.value >= this.end) {
                return this.value = this.end;
            }
            this.value += this.changePerMillisecond * (System.currentTimeMillis() - this.lastTime);
            if (this.value > this.end) {
                this.value = this.end;
            }
            this.lastTime = System.currentTimeMillis();
            return this.value;
        }
        else {
            if (this.value <= this.end) {
                return this.value = this.end;
            }
            this.value -= this.changePerMillisecond * (System.currentTimeMillis() - this.lastTime);
            if (this.value < this.end) {
                this.value = this.end;
            }
            this.lastTime = System.currentTimeMillis();
            return this.value;
        }
    }
    
    public boolean isDone() {
        return this.value == this.end;
    }
    
    public static double animate(final double target, double current, double speed) {
        final boolean bl;
        final boolean larger = bl = (target > current);
        if (speed < 0.0) {
            speed = 0.0;
        }
        else if (speed > 1.0) {
            speed = 1.0;
        }
        final double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        current = (current = (larger ? (current + factor) : (current - factor)));
        return current;
    }
}
