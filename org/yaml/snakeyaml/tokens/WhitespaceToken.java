//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.*;

public class WhitespaceToken extends Token
{
    public WhitespaceToken(final Mark startMark, final Mark endMark) {
        super(startMark, endMark);
    }
    
    public Token.ID getTokenId() {
        return Token.ID.Whitespace;
    }
}
