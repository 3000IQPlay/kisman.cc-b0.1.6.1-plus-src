//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.*;

public class Toggle extends Command
{
    public Toggle() {
        super("toggle");
    }
    
    public void runCommand(final String s, final String[] args) {
        String module = "";
        try {
            module = args[0];
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
            return;
        }
        try {
            Kisman.instance.moduleManager.getModule(module);
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Module " + module + " does not exist!"));
            return;
        }
        if (args.length > 1) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
            return;
        }
        try {
            Kisman.instance.moduleManager.getModule(module).setToggled(!Kisman.instance.moduleManager.getModule(module).isToggled());
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "toggled modules";
    }
    
    public String getSyntax() {
        return "toggle <module>";
    }
}
