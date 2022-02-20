//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.tools.rmi;

public class ObjectNotFoundException extends Exception
{
    public ObjectNotFoundException(final String name) {
        super(name + " is not exported");
    }
    
    public ObjectNotFoundException(final String name, final Exception e) {
        super(name + " because of " + e.toString());
    }
}
