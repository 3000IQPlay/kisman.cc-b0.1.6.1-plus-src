//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.utils;

public class TimerUtils
{
    private long lastMS;
    private long prevMS;
    private long nanoTime;
    
    public TimerUtils() {
        this.lastMS = 0L;
        this.prevMS = 0L;
        this.nanoTime = -1L;
    }
    
    public boolean isDelay(final long delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }
    
    public boolean hasTimeElapsed(final long time, final boolean reset) {
        if (time < 150L) {
            if (this.getTime() >= time / 1.63) {
                if (reset) {
                    this.reset();
                }
                return true;
            }
        }
        else if (this.getTime() >= time) {
            if (reset) {
                this.reset();
            }
            return true;
        }
        return false;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public void setLastMS(final long lastMS) {
        this.lastMS = lastMS;
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public int convertToMS(final int d) {
        return 1000 / d;
    }
    
    public void setTicks(final long ticks) {
        this.nanoTime = System.nanoTime() - this.convertTicksToNano(ticks);
    }
    
    public void setNano(final long time) {
        this.nanoTime = System.nanoTime() - time;
    }
    
    public void setMicro(final long time) {
        this.nanoTime = System.nanoTime() - this.convertMicroToNano(time);
    }
    
    public void setMillis(final long time) {
        this.nanoTime = System.nanoTime() - this.convertMillisToNano(time);
    }
    
    public void setSec(final long time) {
        this.nanoTime = System.nanoTime() - this.convertSecToNano(time);
    }
    
    public long getTicks() {
        return this.convertNanoToTicks(this.nanoTime);
    }
    
    public long getNano() {
        return this.nanoTime;
    }
    
    public long getMicro() {
        return this.convertNanoToMicro(this.nanoTime);
    }
    
    public long getMillis() {
        return this.convertNanoToMillis(this.nanoTime);
    }
    
    public long getSec() {
        return this.convertNanoToSec(this.nanoTime);
    }
    
    public boolean passedTicks(final long ticks) {
        return this.passedNano(this.convertTicksToNano(ticks));
    }
    
    public boolean passedNano(final long time) {
        return System.nanoTime() - this.nanoTime >= time;
    }
    
    public boolean passedMicro(final long time) {
        return this.passedNano(this.convertMicroToNano(time));
    }
    
    public boolean passedMillis(final long time) {
        return this.passedNano(this.convertMillisToNano(time));
    }
    
    public boolean passedSec(final long time) {
        return this.passedNano(this.convertSecToNano(time));
    }
    
    public boolean hasReached(final float f) {
        return this.getCurrentMS() - this.lastMS >= f;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
        this.nanoTime = System.nanoTime();
    }
    
    public void resetTimeSkipTo(final long ms) {
        this.lastMS = this.getCurrentMS() + ms;
        this.nanoTime = System.nanoTime();
    }
    
    public boolean delay(final float milliSec) {
        return this.getTime() - this.prevMS >= milliSec;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public long convertMillisToTicks(final long time) {
        return time / 50L;
    }
    
    public long convertTicksToMillis(final long ticks) {
        return ticks * 50L;
    }
    
    public long convertNanoToTicks(final long time) {
        return this.convertMillisToTicks(this.convertNanoToMillis(time));
    }
    
    public long convertTicksToNano(final long ticks) {
        return this.convertMillisToNano(this.convertTicksToMillis(ticks));
    }
    
    public long convertSecToMillis(final long time) {
        return time * 1000L;
    }
    
    public long convertSecToMicro(final long time) {
        return this.convertMillisToMicro(this.convertSecToMillis(time));
    }
    
    public long convertSecToNano(final long time) {
        return this.convertMicroToNano(this.convertMillisToMicro(this.convertSecToMillis(time)));
    }
    
    public long convertMillisToMicro(final long time) {
        return time * 1000L;
    }
    
    public long convertMillisToNano(final long time) {
        return this.convertMicroToNano(this.convertMillisToMicro(time));
    }
    
    public long convertMicroToNano(final long time) {
        return time * 1000L;
    }
    
    public long convertNanoToMicro(final long time) {
        return time / 1000L;
    }
    
    public long convertNanoToMillis(final long time) {
        return this.convertMicroToMillis(this.convertNanoToMicro(time));
    }
    
    public long convertNanoToSec(final long time) {
        return this.convertMillisToSec(this.convertMicroToMillis(this.convertNanoToMicro(time)));
    }
    
    public long convertMicroToMillis(final long time) {
        return time / 1000L;
    }
    
    public long convertMicroToSec(final long time) {
        return this.convertMillisToSec(this.convertMicroToMillis(time));
    }
    
    public long convertMillisToSec(final long time) {
        return time / 1000L;
    }
}
