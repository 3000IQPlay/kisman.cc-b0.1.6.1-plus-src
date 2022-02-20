//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.hypixel.util.*;

public class NotEnoughCoinsModule extends Module
{
    public static NotEnoughCoinsModule instance;
    public Setting minProfit;
    public Setting demand;
    public Setting minProfitPercent;
    public Setting alertSound;
    public Setting onlyHypixelWorking;
    
    public NotEnoughCoinsModule() {
        super("NotEnoughCoins", "NotEnoughCoins", Category.CLIENT);
        this.minProfit = new Setting("MinProfit", this, 50000.0, 1.0, 2.147483647E9, true);
        this.demand = new Setting("Demand", this, 3.0, 1.0, 2.147483647E9, true);
        this.minProfitPercent = new Setting("MinProfitPercent", this, 0.0, 0.0, 100.0, true);
        this.alertSound = new Setting("AlertSound", this, true);
        this.onlyHypixelWorking = new Setting("OnlyHypixelWorking", this, true);
        NotEnoughCoinsModule.setmgr.rSetting(this.minProfit);
        NotEnoughCoinsModule.setmgr.rSetting(this.demand);
        NotEnoughCoinsModule.setmgr.rSetting(this.minProfitPercent);
        NotEnoughCoinsModule.setmgr.rSetting(this.alertSound);
        NotEnoughCoinsModule.setmgr.rSetting(this.onlyHypixelWorking);
    }
    
    @Override
    public void onEnable() {
        if (NotEnoughCoinsModule.mc.player == null && NotEnoughCoinsModule.mc.world == null) {
            return;
        }
        Label_0068: {
            if (this.onlyHypixelWorking.getValBoolean()) {
                if (NotEnoughCoinsModule.mc.getCurrentServerData() != null) {
                    if (NotEnoughCoinsModule.mc.getCurrentServerData() != null) {
                        if (NotEnoughCoinsModule.mc.getCurrentServerData().serverIP.equalsIgnoreCase("mc.hypixel.net")) {
                            break Label_0068;
                        }
                    }
                }
                return;
            }
        }
        if (ConfigHandler.getString("general", "Flip").equals("false")) {
            ConfigHandler.writeConfig("general", "Flip", "true");
        }
    }
    
    @Override
    public void onDisable() {
        if (NotEnoughCoinsModule.mc.player == null && NotEnoughCoinsModule.mc.world == null) {
            return;
        }
        if (ConfigHandler.getString("general", "Flip").equals("true")) {
            ConfigHandler.writeConfig("general", "Flip", "false");
        }
    }
}
