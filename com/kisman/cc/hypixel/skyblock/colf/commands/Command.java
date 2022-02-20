//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands;

import com.google.gson.annotations.*;

public class Command<T>
{
    @SerializedName("type")
    private CommandType Type;
    @SerializedName("data")
    private T data;
    
    public Command() {
    }
    
    public Command(final CommandType type, final T data) {
        this.Type = type;
        this.data = data;
    }
    
    public CommandType getType() {
        return this.Type;
    }
    
    public void setType(final CommandType type) {
        this.Type = type;
    }
    
    public T getData() {
        return this.data;
    }
    
    public void setData(final T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Command [Type=" + this.Type + ", data=" + this.data + "]";
    }
}
