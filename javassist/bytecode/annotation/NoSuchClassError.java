//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.bytecode.annotation;

public class NoSuchClassError extends Error
{
    private String className;
    
    public NoSuchClassError(final String className, final Error cause) {
        super(cause.toString(), cause);
        this.className = className;
    }
    
    public String getClassName() {
        return this.className;
    }
}
