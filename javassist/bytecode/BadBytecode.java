//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.bytecode;

public class BadBytecode extends Exception
{
    public BadBytecode(final int opcode) {
        super("bytecode " + opcode);
    }
    
    public BadBytecode(final String msg) {
        super(msg);
    }
    
    public BadBytecode(final String msg, final Throwable cause) {
        super(msg, cause);
    }
    
    public BadBytecode(final MethodInfo minfo, final Throwable cause) {
        super(minfo.toString() + " in " + minfo.getConstPool().getClassName() + ": " + cause.getMessage(), cause);
    }
}
