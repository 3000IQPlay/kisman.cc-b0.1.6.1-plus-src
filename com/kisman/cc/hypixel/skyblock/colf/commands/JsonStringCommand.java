//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands;

import com.google.gson.reflect.*;
import com.google.gson.*;

public class JsonStringCommand extends Command<String>
{
    public JsonStringCommand(final String type, final String data) {
        this.setData((Object)data);
    }
    
    public JsonStringCommand() {
    }
    
    public JsonStringCommand(final CommandType type, final String data) {
        super(type, (Object)data);
    }
    
    public <T> Command<T> getAs(final TypeToken<T> type) {
        final T t = (T)new GsonBuilder().create().fromJson((String)this.getData(), type.getType());
        final Command<?> cmd = (Command<?>)new Command(this.getType(), (Object)t);
        return (Command<T>)cmd;
    }
}
