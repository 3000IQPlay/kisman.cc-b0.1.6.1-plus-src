//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import com.kisman.cc.hypixel.util.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class SetKey extends Command
{
    public SetKey() {
        super("setkey");
    }
    
    public void runCommand(final String s, final String[] args) {
        if (args.length > 0) {
            ConfigHandler.writeConfig("general", "APIKey", args[0]);
            ChatUtils.complete((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.GRAY + " API Key set to " + TextFormatting.GREEN + args[0]));
        }
        else {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "";
    }
    
    public String getSyntax() {
        return "setkey <Hypixel Skyblock API key>";
    }
}
