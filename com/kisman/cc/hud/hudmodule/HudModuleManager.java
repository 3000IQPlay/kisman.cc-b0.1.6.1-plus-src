//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule;

import net.minecraftforge.common.*;
import com.kisman.cc.hud.hudmodule.combat.*;
import com.kisman.cc.hud.hudmodule.movement.*;
import com.kisman.cc.hud.hudmodule.render.*;
import com.kisman.cc.hud.hudmodule.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.client.event.*;

public class HudModuleManager
{
    public ArrayList<HudModule> modules;
    
    public HudModuleManager() {
        this.modules = new ArrayList<HudModule>();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.init();
    }
    
    public void init() {
        this.modules.add((HudModule)new ArmorHUD());
        this.modules.add((HudModule)new PvpInfo());
        this.modules.add((HudModule)new PvpResources());
        this.modules.add((HudModule)new TargetHUD());
        this.modules.add(new Speed());
        this.modules.add(new com.kisman.cc.hud.hudmodule.render.ArrayList());
        this.modules.add(new Coord());
        this.modules.add(new Fps());
        this.modules.add(new Logo());
        this.modules.add(new Radar());
        this.modules.add(new Welcomer());
        this.modules.add(new Indicators());
        this.modules.add(new Ping());
        this.modules.add(new ServerIp());
        this.modules.add(new Tps());
    }
    
    public HudModule getModule(final String name) {
        for (final HudModule m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<HudModule> getModuleList() {
        return this.modules;
    }
    
    public ArrayList<HudModule> getModulesInCategory(final HudCategory c) {
        final ArrayList<HudModule> mods = new ArrayList<HudModule>();
        for (final HudModule m : this.modules) {
            if (m.getCategory() == c) {
                mods.add(m);
            }
        }
        return mods;
    }
    
    @SubscribeEvent
    public void onKey(final InputEvent.KeyInputEvent event) {
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        for (final HudModule m : this.modules) {
            if (m.isToggled()) {
                m.update();
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        for (final HudModule m : this.modules) {
            if (m.isToggled()) {
                m.render();
            }
        }
    }
}
