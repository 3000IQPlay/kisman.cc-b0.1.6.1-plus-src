//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;

public class ViewModelRewrite extends Module
{
    private Setting posX;
    private Setting posY;
    private Setting posZ;
    
    public ViewModelRewrite() {
        super("ViewModelRewrite", Category.RENDER);
        this.posX = new Setting("PosX", this, 0.0, -4.0, 4.0, false);
        this.posY = new Setting("PosY", this, 0.0, -4.0, 4.0, false);
        this.posZ = new Setting("PosZ", this, 0.0, -4.0, 4.0, false);
    }
}
