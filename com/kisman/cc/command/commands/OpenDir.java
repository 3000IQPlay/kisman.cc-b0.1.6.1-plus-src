//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.*;
import java.io.*;
import java.awt.*;

public class OpenDir extends Command
{
    public OpenDir() {
        super("opendir");
    }
    
    public void runCommand(final String s, final String[] args) {
        if (args[0] == null) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
            return;
        }
        try {
            final File file = new File(Minecraft.getMinecraft().gameDir + "kisman.cc/");
            Desktop.getDesktop().open(file);
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "opening minecraft's directory";
    }
    
    public String getSyntax() {
        return "opendir";
    }
}
