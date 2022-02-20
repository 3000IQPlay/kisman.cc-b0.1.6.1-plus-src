//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.file.*;

public class SaveConfigCommand extends Command
{
    public SaveConfigCommand() {
        super("saveconfig");
    }
    
    public void runCommand(final String s, final String[] args) {
        try {
            if (args.length > 0) {
                ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                return;
            }
            ChatUtils.warning((Object)"Start saving configs!");
            SaveConfig.init();
            ChatUtils.message((Object)"Saved Config!");
        }
        catch (Exception e) {
            ChatUtils.error((Object)"Saving config is failed!");
            e.printStackTrace();
        }
    }
    
    public String getDescription() {
        return "saving confing";
    }
    
    public String getSyntax() {
        return "saveconfig";
    }
}
