//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.entity.*;

public class PlayerModifier extends Module
{
    private Setting preview;
    private Setting rotateLine;
    public Setting rotateX;
    public Setting rotateY;
    public Setting rotateZ;
    public Setting autoRotateX;
    public Setting autoRotateY;
    public Setting autoRotateZ;
    private Setting scaleLine;
    
    public PlayerModifier() {
        super("PlayerModifier", "", Category.RENDER);
        this.preview = new Setting("Preview", this, "Player", (Entity)PlayerModifier.mc.player);
        this.rotateLine = new Setting("RotateLine", this, "Rotate");
        this.rotateX = new Setting("RotateX", this, 0.0, 0.0, 360.0, true);
        this.rotateY = new Setting("RotateY", this, 0.0, 0.0, 360.0, true);
        this.rotateZ = new Setting("RotateZ", this, 0.0, 0.0, 360.0, true);
        this.autoRotateX = new Setting("AutoRotateX", this, false);
        this.autoRotateY = new Setting("AutoRotateY", this, false);
        this.autoRotateZ = new Setting("AutoRotateZ", this, false);
        this.scaleLine = new Setting("ScaleLine", this, "Scale");
        PlayerModifier.setmgr.rSetting(this.preview);
        PlayerModifier.setmgr.rSetting(this.rotateLine);
        PlayerModifier.setmgr.rSetting(this.rotateX);
        PlayerModifier.setmgr.rSetting(this.rotateY);
        PlayerModifier.setmgr.rSetting(this.rotateZ);
        PlayerModifier.setmgr.rSetting(this.autoRotateX);
        PlayerModifier.setmgr.rSetting(this.autoRotateY);
        PlayerModifier.setmgr.rSetting(this.autoRotateZ);
        PlayerModifier.setmgr.rSetting(this.scaleLine);
    }
}
