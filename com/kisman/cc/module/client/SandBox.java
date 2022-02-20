//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;

public class SandBox extends Module
{
    public static SandBox instance;
    
    public SandBox() {
        super("SandBoxTest", "this, ", Category.CLIENT);
        this.setToggled(true);
        SandBox.instance = this;
    }
}
