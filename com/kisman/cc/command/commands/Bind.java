//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.*;
import com.kisman.cc.module.*;
import org.lwjgl.input.*;
import com.kisman.cc.file.*;
import java.util.*;

public class Bind extends Command
{
    public Bind() {
        super("bind");
    }
    
    public void runCommand(final String s, final String[] args) {
        try {
            final String key = args[0];
            final String isList = args[0];
            if (args.length == 1 && !isList.equalsIgnoreCase("list")) {
                ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                return;
            }
            if (args.length > 2 && isList.equalsIgnoreCase("list")) {
                ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                return;
            }
            if (args.length == 1 && isList.equalsIgnoreCase("list")) {
                ChatUtils.message((Object)"Bind List:");
                for (final Module mod : Kisman.instance.moduleManager.modules) {
                    if (0 != mod.getKey()) {
                        ChatUtils.message((Object)(mod.getName() + " | " + Keyboard.getKeyName(mod.getKey())));
                    }
                }
                return;
            }
            for (final Module mod : Kisman.instance.moduleManager.modules) {
                if (mod.getName().equalsIgnoreCase(args[1])) {
                    mod.setKey(Keyboard.getKeyIndex(key.toUpperCase()));
                    ChatUtils.message((Object)(mod.getName() + " binned to " + Keyboard.getKeyName(mod.getKey())));
                    SaveConfig.init();
                }
            }
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "Change key for modules/commands.";
    }
    
    public String getSyntax() {
        return "bind <key> <command/module> or bind list";
    }
}
