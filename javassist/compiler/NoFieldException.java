//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.compiler;

import javassist.compiler.ast.*;

public class NoFieldException extends CompileError
{
    private String fieldName;
    private ASTree expr;
    
    public NoFieldException(final String name, final ASTree e) {
        super("no such field: " + name);
        this.fieldName = name;
        this.expr = e;
    }
    
    public String getField() {
        return this.fieldName;
    }
    
    public ASTree getExpr() {
        return this.expr;
    }
}
