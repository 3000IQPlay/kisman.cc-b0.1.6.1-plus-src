//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.*;
import com.kisman.cc.file.*;

public class Slider extends Command
{
    public Slider() {
        super("slider");
    }
    
    public void runCommand(final String s, final String[] args) {
        String module = "";
        String name = "";
        try {
            module = args[0];
            name = args[1];
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
        try {
            Kisman.instance.settingsManager.getSettingByName(Kisman.instance.moduleManager.getModule(module), name).getValDouble();
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Setting " + name + " in module " + module + " does not exist!"));
            return;
        }
        double value;
        try {
            value = Double.parseDouble(args[2]);
        }
        catch (Exception e) {
            ChatUtils.error((Object)"Value error! <value> not double!");
            return;
        }
        try {
            Kisman.instance.settingsManager.getSettingByName(Kisman.instance.moduleManager.getModule(module), name).setValDouble(value);
            ChatUtils.message((Object)("Slider " + name + " change value to " + value));
            SaveConfig.init();
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "Change slider value for modules setting";
    }
    
    public String getSyntax() {
        return "slider <module> <slider name> <value>";
    }
}
