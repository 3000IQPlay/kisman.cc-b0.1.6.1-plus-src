//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.friend;

import java.util.*;
import net.minecraft.entity.player.*;

public class FriendManager
{
    public static FriendManager instance;
    private ArrayList<String> friendsName;
    
    public FriendManager() {
        this.friendsName = new ArrayList<String>();
        FriendManager.instance = this;
    }
    
    public ArrayList<String> getFriends() {
        return this.friendsName;
    }
    
    public void addFriend(final String name) {
        if (!this.friendsName.contains(name)) {
            this.friendsName.add(name);
        }
    }
    
    public void removeFriend(final String name) {
        if (!this.friendsName.isEmpty() && this.friendsName.contains(name)) {
            this.friendsName.remove(name);
        }
    }
    
    public String getFriendsNames() {
        String str = "";
        for (final String friend : this.friendsName) {
            if (friend.isEmpty()) {
                continue;
            }
            str = str + friend + ", ";
        }
        return str;
    }
    
    public boolean isFriend(final EntityPlayer player) {
        return this.friendsName.contains(player.getName());
    }
    
    public boolean isFriend(final String name) {
        return this.friendsName.contains(name);
    }
}
