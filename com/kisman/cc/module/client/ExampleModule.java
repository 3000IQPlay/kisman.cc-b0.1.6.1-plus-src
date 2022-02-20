//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.function.*;
import com.kisman.cc.*;
import org.lwjgl.input.*;
import com.kisman.cc.event.*;

public class ExampleModule extends Module
{
    private Setting exampleBind;
    private Setting exampleEntityPreview;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> playerMotionUpdateListener;
    
    public ExampleModule() {
        super("ExampleModule", "example", Category.CLIENT);
        this.exampleBind = new Setting("ExampleBind", this, 0);
        this.exampleEntityPreview = new Setting("ExampleEntityPreview", this, "Example", (Entity)new EntityEnderCrystal((World)ExampleModule.mc.world));
        this.playerMotionUpdateListener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            ExampleModule.mc.player.sendChatMessage("event player motion update is done!");
            if (event.getEra() != Event.Era.PRE) {
                return;
            }
            ExampleModule.mc.player.sendChatMessage("6666");
        }, new Predicate[0]);
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
        Kisman.instance.settingsManager.rSetting(new Setting("ExampleCategory", this, "ExampleCategory", true));
        Kisman.instance.settingsManager.rSubSetting(new Setting("ExampleCLine", this, Kisman.instance.settingsManager.getSettingByName(this, "ExampleCategory"), "ExampleLine"));
        Kisman.instance.settingsManager.rSetting(new Setting("ExampleString", this, "kisman", "kisman", true));
        Kisman.instance.settingsManager.rSetting(new Setting("ExampleColorPicker", this, "ExampleColorPicker", new float[] { 3.0f, 0.03f, 0.33f, 1.0f }, false));
        ExampleModule.setmgr.rSetting(this.exampleBind);
    }
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public void key(final int key) {
        ExampleModule.mc.player.sendChatMessage("Typed Key " + key + " | " + Keyboard.getKeyName(key));
    }
    
    public void motion(final EventPlayerMotionUpdate event) {
        ExampleModule.mc.player.sendChatMessage("?");
    }
}
