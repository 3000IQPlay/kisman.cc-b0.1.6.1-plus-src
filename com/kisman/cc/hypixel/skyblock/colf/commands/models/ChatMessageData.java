//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands.models;

import com.google.gson.annotations.*;

public class ChatMessageData
{
    @SerializedName("text")
    public String Text;
    @SerializedName("onClick")
    public String OnClick;
    @SerializedName("hover")
    public String Hover;
    
    public ChatMessageData() {
    }
    
    public ChatMessageData(final String text, final String onClick, final String hover) {
        this.Text = text;
        this.OnClick = onClick;
        this.Hover = hover;
    }
}
