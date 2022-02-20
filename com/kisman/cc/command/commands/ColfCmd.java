//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.hypixel.skyblock.colf.*;
import java.util.*;
import com.kisman.cc.hypixel.skyblock.colf.minecraft_integration.*;

public class ColfCmd extends Command
{
    public ColfCmd() {
        super("colf");
    }
    
    public void runCommand(final String s, final String[] args) {
        if (args.length >= 1) {
            final String s2 = args[0];
            switch (s2) {
                case "start": {
                    ChatUtils.message((Object)"Starting connection...");
                    MainColf.wrapper.startConnection();
                    break;
                }
                case "stop": {
                    ChatUtils.message((Object)"Stopping connection...");
                    MainColf.wrapper.stop();
                    break;
                }
                case "reset": {
                    this.handleReset();
                    break;
                }
                case "connect": {
                    if (args.length == 2) {
                        String destination = args[1];
                        if (!destination.contains("://")) {
                            destination = new String(Base64.getDecoder().decode(destination));
                        }
                        ChatUtils.message((Object)"Stopping connection!");
                        MainColf.wrapper.stop();
                        ChatUtils.message((Object)("Opening connection to " + destination));
                        if (MainColf.wrapper.initializeNewSocket(destination)) {
                            ChatUtils.message((Object)"Success");
                        }
                        else {
                            ChatUtils.message((Object)"Could not open connection, please check the logs");
                        }
                        break;
                    }
                    break;
                }
                default: {
                    ChatUtils.message((Object)("Use: " + this.getSyntax()));
                    break;
                }
            }
        }
    }
    
    public String getDescription() {
        return "colf";
    }
    
    public String getSyntax() {
        return "colf <start/stop/reset/connect";
    }
    
    private void handleReset() {
        MainColf.wrapper.stop();
        ChatUtils.message((Object)"Stopping Connection to CoflNet");
        CoflSessionManager.DeleteAllCoflSessions();
        ChatUtils.message((Object)"Deleting CoflNet sessions...");
        if (MainColf.wrapper.startConnection()) {
            ChatUtils.message((Object)"Started the Connection to CoflNet");
        }
    }
}
