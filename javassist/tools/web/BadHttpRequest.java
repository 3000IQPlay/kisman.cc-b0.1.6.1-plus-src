//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.tools.web;

public class BadHttpRequest extends Exception
{
    private Exception e;
    
    public BadHttpRequest() {
        this.e = null;
    }
    
    public BadHttpRequest(final Exception _e) {
        this.e = _e;
    }
    
    @Override
    public String toString() {
        if (this.e == null) {
            return super.toString();
        }
        return this.e.toString();
    }
}
