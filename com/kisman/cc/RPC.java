//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc;

import club.minnced.discord.rpc.*;

public class RPC
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void startRPC() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "895232773961445448";
        RPC.discordRPC.Discord_Initialize(discordID, eventHandlers, true, (String)null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.largeImageKey = "logo";
        RPC.discordRichPresence.largeImageText = "cracked by NotRocky";
        RPC.discordRichPresence.smallImageKey = "small";
        RPC.discordRichPresence.smallImageText = "kisman.cc+ cracked";
        RPC.discordRichPresence.details = "kisman.cc+ b0.1.6.1 CRACKED BY NOTROCKY";
        RPC.discordRichPresence.partyId = "5657657-351d-4a4f-ad32-2b9b01c91657";
        RPC.discordRichPresence.partySize = 1;
        RPC.discordRichPresence.partyMax = 10;
        RPC.discordRichPresence.joinSecret = "join";
        RPC.discordRPC.Discord_UpdatePresence(RPC.discordRichPresence);
    }
    
    public static void stopRPC() {
        RPC.discordRPC.Discord_Shutdown();
        RPC.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
}
