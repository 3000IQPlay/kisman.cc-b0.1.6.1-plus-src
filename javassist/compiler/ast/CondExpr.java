//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.compiler.ast;

import javassist.compiler.*;

public class CondExpr extends ASTList
{
    public CondExpr(final ASTree cond, final ASTree thenp, final ASTree elsep) {
        super(cond, new ASTList(thenp, new ASTList(elsep)));
    }
    
    public ASTree condExpr() {
        return this.head();
    }
    
    public void setCond(final ASTree t) {
        this.setHead(t);
    }
    
    public ASTree thenExpr() {
        return this.tail().head();
    }
    
    public void setThen(final ASTree t) {
        this.tail().setHead(t);
    }
    
    public ASTree elseExpr() {
        return this.tail().tail().head();
    }
    
    public void setElse(final ASTree t) {
        this.tail().tail().setHead(t);
    }
    
    public String getTag() {
        return "?:";
    }
    
    public void accept(final Visitor v) throws CompileError {
        v.atCondExpr(this);
    }
}
