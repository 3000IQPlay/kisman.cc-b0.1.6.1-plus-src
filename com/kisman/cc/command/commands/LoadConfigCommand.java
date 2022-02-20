//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.file.*;

public class LoadConfigCommand extends Command
{
    public LoadConfigCommand() {
        super("loadconfig");
    }
    
    public void runCommand(final String s, final String[] args) {
        try {
            if (args.length > 0) {
                ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                return;
            }
            ChatUtils.warning((Object)"Start loading configs!");
            LoadConfig.init();
            ChatUtils.message((Object)"Loaded Config!");
        }
        catch (Exception e) {
            ChatUtils.error((Object)"Loaded config is failed!");
            e.printStackTrace();
        }
    }
    
    public String getDescription() {
        return "loading config";
    }
    
    public String getSyntax() {
        return "loadconfig";
    }
}
