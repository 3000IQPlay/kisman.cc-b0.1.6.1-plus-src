//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import com.kisman.cc.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class FriendCommand extends Command
{
    public String[] subCommands;
    
    public FriendCommand() {
        super("friend");
        this.subCommands = new String[] { "add", "remove", "list" };
    }
    
    public void runCommand(final String s, final String[] args) {
        try {
            if (args[0] != null && args[1] != null) {
                if (args[0].equalsIgnoreCase(this.subCommands[0])) {
                    Kisman.instance.friendManager.addFriend(args[1]);
                    ChatUtils.complete((Object)(args[1] + " added in friends!"));
                }
                else if (args[0].equalsIgnoreCase(this.subCommands[1])) {
                    Kisman.instance.friendManager.removeFriend(args[1]);
                    ChatUtils.complete((Object)(args[1] + " removed from friends :("));
                }
                else {
                    ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                }
            }
            else if (args[0] != null && args[0].equalsIgnoreCase(this.subCommands[2])) {
                ChatUtils.message((Object)("Friend's list: " + Kisman.instance.friendManager.getFriendsNames()));
            }
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "friend's command";
    }
    
    public String getSyntax() {
        return "friend <add/remove> <player's name> or friend list";
    }
}
