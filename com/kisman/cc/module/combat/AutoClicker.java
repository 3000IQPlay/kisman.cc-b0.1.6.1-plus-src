//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import io.netty.util.internal.*;

public class AutoClicker extends Module
{
    private long lastClick;
    private long hold;
    private double speed;
    private double holdLength;
    private double min;
    private double max;
    
    public AutoClicker() {
        super("AutoClicker", "clicks automatically", Category.COMBAT);
        Kisman.instance.settingsManager.rSetting(new Setting("MinCPS", this, 8.0, 1.0, 20.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("MaxCPS", this, 12.0, 1.0, 20.0, false));
    }
    
    @Override
    public void update() {
        if (Mouse.isButtonDown(0)) {
            if (System.currentTimeMillis() - this.lastClick > this.speed * 1000.0) {
                this.lastClick = System.currentTimeMillis();
                if (this.hold < this.lastClick) {
                    this.hold = this.lastClick;
                }
                final int key = AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode();
                KeyBinding.setKeyBindState(key, true);
                KeyBinding.onTick(key);
                this.updateVals();
            }
            else if (System.currentTimeMillis() - this.hold > this.holdLength * 1000.0) {
                KeyBinding.setKeyBindState(AutoClicker.mc.gameSettings.keyBindAttack.getKeyCode(), false);
                this.updateVals();
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.updateVals();
    }
    
    private void updateVals() {
        this.min = Kisman.instance.settingsManager.getSettingByName(this, "MinCPS").getValDouble();
        this.max = Kisman.instance.settingsManager.getSettingByName(this, "MaxCPS").getValDouble();
        if (this.min >= this.max) {
            this.max = this.min + 1.0;
        }
        this.speed = 1.0 / ThreadLocalRandom.current().nextDouble(this.min - 0.2, this.max);
        this.holdLength = this.speed / ThreadLocalRandom.current().nextDouble(this.min, this.max);
    }
}
