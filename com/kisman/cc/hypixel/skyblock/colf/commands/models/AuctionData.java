//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.commands.models;

import com.google.gson.annotations.*;

public class AuctionData
{
    @SerializedName("auctionId")
    private String auctionId;
    @SerializedName("itemId")
    private String itemId;
    
    public String getAuctionId() {
        return this.auctionId;
    }
    
    public void setAuctionId(final String auctionId) {
        this.auctionId = auctionId;
    }
    
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }
    
    public AuctionData(final String auctionId, final String itemId) {
        this.auctionId = auctionId;
        this.itemId = itemId;
    }
    
    public AuctionData() {
    }
}
