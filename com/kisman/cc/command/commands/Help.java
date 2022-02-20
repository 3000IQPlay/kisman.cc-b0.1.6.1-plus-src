//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class Help extends Command
{
    public Help() {
        super("help");
    }
    
    public void runCommand(final String s, final String[] args) {
        ChatUtils.message((Object)"Commands:");
        ChatUtils.message((Object)"bind <key> <module>");
        ChatUtils.message((Object)"bind list");
        ChatUtils.message((Object)"flip - this command only for Hypixel Skyblock");
        ChatUtils.message((Object)"friend <add/remove> <player's name>");
        ChatUtils.message((Object)"friend list");
        ChatUtils.message((Object)"help");
        ChatUtils.message((Object)"loadconfig");
        ChatUtils.message((Object)"opendir");
        ChatUtils.message((Object)"saveconfig");
        ChatUtils.message((Object)"setkey - this command only for Hypixel Skyblock");
        ChatUtils.message((Object)"slider <module> <slider's name> <value>");
        ChatUtils.message((Object)"toggle");
        ChatUtils.message((Object)"tp <x> <y> <z>");
        ChatUtils.message((Object)"tp <player's nickname>");
    }
    
    public String getDescription() {
        return "help of commands";
    }
    
    public String getSyntax() {
        return "help";
    }
}
