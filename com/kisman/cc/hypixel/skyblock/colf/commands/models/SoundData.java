//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands.models;

import com.google.gson.annotations.*;

public class SoundData
{
    @SerializedName("name")
    public String Name;
    @SerializedName("pitch")
    public float Pitch;
    
    public SoundData() {
    }
    
    public SoundData(final String name, final float pitch) {
        this.Name = name;
        this.Pitch = pitch;
    }
}
