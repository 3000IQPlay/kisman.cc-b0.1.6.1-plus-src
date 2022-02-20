//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf;

import com.kisman.cc.hypixel.skyblock.colf.network.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.common.*;
import com.kisman.cc.hypixel.skyblock.colf.event.*;
import org.apache.logging.log4j.*;

public class MainColf
{
    public static final Logger LOGGER;
    public static WSClientWrapper wrapper;
    public static final String[] webSocketURIPrefix;
    public static String commandUri;
    
    public void init(final FMLInitializationEvent event) {
        MainColf.LOGGER.info("Started!");
        MainColf.wrapper = new WSClientWrapper(MainColf.webSocketURIPrefix);
        MinecraftForge.EVENT_BUS.register((Object)new EventRegistry());
    }
    
    static {
        LOGGER = LogManager.getLogger("[ColfSky]");
        webSocketURIPrefix = new String[] { "wss://sky.coflnet.com/modsocket", "wss://sky-mod.coflnet.com/modsocket", "ws://sky.coflnet.com/modsocket", "ws://sky-mod.coflnet.com/modsocket" };
        MainColf.commandUri = "https://sky-commands.coflnet.com/api/mod/commands";
    }
}
