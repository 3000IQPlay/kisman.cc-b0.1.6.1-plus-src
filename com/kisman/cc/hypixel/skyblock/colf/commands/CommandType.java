//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands;

import com.google.gson.annotations.*;

public enum CommandType
{
    @SerializedName("writeToChat")
    WriteToChat, 
    @SerializedName("execute")
    Execute, 
    @SerializedName("tokenLogin")
    TokenLogin, 
    @SerializedName("clicked")
    Clicked, 
    @SerializedName("playSound")
    PlaySound, 
    @SerializedName("chatMessage")
    ChatMessage, 
    @SerializedName("purchaseStart")
    PurchaseStart, 
    @SerializedName("purchaseConfirm")
    PurchaseConfirm, 
    @SerializedName("reset")
    Reset;
}
