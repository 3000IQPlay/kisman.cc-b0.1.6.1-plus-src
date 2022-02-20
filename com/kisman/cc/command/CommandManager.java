//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command;

import com.kisman.cc.command.commands.*;
import i.gishreloaded.gishcode.utils.visual.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import i.gishreloaded.gishcode.wrappers.*;
import org.lwjgl.input.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class CommandManager
{
    public static ArrayList<Command> commands;
    public char cmdPrefix;
    public String cmdPrefixStr;
    
    public CommandManager() {
        this.cmdPrefix = '-';
        this.cmdPrefixStr = "" + this.cmdPrefix;
        this.addCommands();
    }
    
    public void addCommands() {
        CommandManager.commands.add(new Bind());
        CommandManager.commands.add(new ColfCmd());
        CommandManager.commands.add(new Flip());
        CommandManager.commands.add(new FriendCommand());
        CommandManager.commands.add(new Help());
        CommandManager.commands.add(new LoadConfigCommand());
        CommandManager.commands.add(new Slider());
        CommandManager.commands.add(new OpenDir());
        CommandManager.commands.add(new Peek());
        CommandManager.commands.add(new SaveConfigCommand());
        CommandManager.commands.add(new SetKey());
        CommandManager.commands.add(new Toggle());
        CommandManager.commands.add(new Tp());
    }
    
    public void runCommands(final String s) {
        final String readString = s.trim().substring(Character.toString(this.cmdPrefix).length()).trim();
        boolean commandResolved = false;
        final boolean hasArgs = readString.trim().contains(" ");
        final String commandName = hasArgs ? readString.split(" ")[0] : readString.trim();
        final String[] args = hasArgs ? readString.substring(commandName.length()).trim().split(" ") : new String[0];
        for (final Command command : CommandManager.commands) {
            if (command.getCommand().trim().equalsIgnoreCase(commandName.trim())) {
                command.runCommand(readString, args);
                commandResolved = true;
                break;
            }
        }
        if (!commandResolved) {
            ChatUtils.error((Object)("Cannot resolve internal command: §c" + commandName));
        }
    }
    
    @SubscribeEvent
    public void onKeyPressed(final InputEvent.KeyInputEvent event) {
        if (Wrapper.INSTANCE.mc().currentScreen != null) {
            return;
        }
        for (final Command cmd : CommandManager.commands) {
            if (cmd.getKey() == Keyboard.getEventKey()) {
                Kisman.instance.commandManager.runCommands("." + cmd.getExecute());
            }
        }
    }
    
    static {
        CommandManager.commands = new ArrayList<Command>();
    }
}
