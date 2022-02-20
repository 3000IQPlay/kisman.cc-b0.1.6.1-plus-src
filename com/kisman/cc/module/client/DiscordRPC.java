//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;
import com.kisman.cc.*;

public class DiscordRPC extends Module
{
    public static DiscordRPC instance;
    
    public DiscordRPC() {
        super("DiscordRPC", "", Category.CLIENT);
        super.setToggled(true);
        DiscordRPC.instance = this;
    }
    
    @Override
    public void onEnable() {
        RPC.startRPC();
    }
    
    @Override
    public void onDisable() {
        RPC.stopRPC();
    }
}
