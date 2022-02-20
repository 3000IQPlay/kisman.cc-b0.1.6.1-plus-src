//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;

public class Cape extends Module
{
    public static Cape instance;
    public Setting mode;
    
    public Cape() {
        super("Cape", "Custom cape", Category.CLIENT);
        this.mode = new Setting("Cape Mode", this, "Static", Arrays.asList("Static", "Gif", "xulu+"));
        Cape.instance = this;
        Cape.setmgr.rSetting(this.mode);
    }
}
