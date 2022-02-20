//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SwingAnimation extends Module
{
    public static SwingAnimation instance;
    public Setting mode;
    private Setting simpleLine;
    private Setting strongLine;
    public Setting ignoreEating;
    public Setting strongMode;
    private ArrayList<String> swingMode;
    private String swingModeString;
    
    public SwingAnimation() {
        super("SwingAnimation", "SwingAnimation", Category.RENDER);
        this.mode = new Setting("Mode", this, "Strong", new ArrayList<String>(Arrays.asList("Hand", "Strong")));
        this.simpleLine = new Setting("SimpleLine", this, "Hand");
        this.strongLine = new Setting("StrongLine", this, "Strong");
        this.ignoreEating = new Setting("IgnoreEating", this, true);
        this.strongMode = new Setting("StrongMode", this, StrongMode.Blockhit1);
        this.swingMode = new ArrayList<String>(Arrays.asList("1", "2", "3"));
        SwingAnimation.instance = this;
        SwingAnimation.setmgr.rSetting(this.mode);
        SwingAnimation.setmgr.rSetting(this.simpleLine);
        Kisman.instance.settingsManager.rSetting(new Setting("SwingMode", this, "1", this.swingMode));
        SwingAnimation.setmgr.rSetting(this.strongLine);
        SwingAnimation.setmgr.rSetting(this.strongMode);
        SwingAnimation.setmgr.rSetting(this.ignoreEating);
    }
    
    public void update() {
        this.swingModeString = Kisman.instance.settingsManager.getSettingByName(this, "SwingMode").getValString();
    }
    
    @SubscribeEvent
    public void onRenderArms(final RenderSpecificHandEvent event) {
        if (this.mode.getValString().equalsIgnoreCase("Hand") && event.getSwingProgress() > 0.0f) {
            final float angle = (1.0f - event.getSwingProgress()) * 360.0f;
            final String swingModeString = this.swingModeString;
            switch (swingModeString) {
                case "1": {
                    GL11.glRotatef(angle, 1.0f, 0.0f, 0.0f);
                    break;
                }
                case "2": {
                    GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case "3": {
                    GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
                    break;
                }
            }
        }
    }
    
    public enum StrongMode
    {
        Blockhit1, 
        Blockhit2;
    }
}
