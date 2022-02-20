//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;

public class CSGOGui extends Module
{
    public static CSGOGui instance;
    private Setting gui;
    public Setting customSize;
    
    public CSGOGui() {
        super("CSGOGui", "CSGOGui", Category.CLIENT);
        this.gui = new Setting("Gui", this, "New", Arrays.asList("Old", "New"));
        this.customSize = new Setting("CustomFontSize", this, false);
        CSGOGui.instance = this;
        CSGOGui.setmgr.rSetting(this.gui);
        CSGOGui.setmgr.rSetting(this.customSize);
    }
    
    @Override
    public void onEnable() {
        CSGOGui.mc.displayGuiScreen(this.gui.getValString().equalsIgnoreCase("Old") ? Kisman.instance.newGui : Kisman.instance.clickGuiNew);
        this.setToggled(false);
    }
}
