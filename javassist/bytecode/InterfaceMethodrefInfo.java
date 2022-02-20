//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.bytecode;

import java.io.*;

class InterfaceMethodrefInfo extends MemberrefInfo
{
    static final int tag = 11;
    
    public InterfaceMethodrefInfo(final int cindex, final int ntindex, final int thisIndex) {
        super(cindex, ntindex, thisIndex);
    }
    
    public InterfaceMethodrefInfo(final DataInputStream in, final int thisIndex) throws IOException {
        super(in, thisIndex);
    }
    
    public int getTag() {
        return 11;
    }
    
    @Override
    public String getTagName() {
        return "Interface";
    }
    
    @Override
    protected int copy2(final ConstPool dest, final int cindex, final int ntindex) {
        return dest.addInterfaceMethodrefInfo(cindex, ntindex);
    }
}
