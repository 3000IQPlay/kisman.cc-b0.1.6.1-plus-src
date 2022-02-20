//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.util.text.*;
import com.kisman.cc.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;

public class FakePlayer extends Module
{
    private Setting name;
    
    public FakePlayer() {
        super("FakePlayer", "FakePlayer", Category.MISC);
        this.name = new Setting("Name", this, "FinLicorice", "FinLicorice", true);
        super.setDisplayInfo("[" + this.name.getValString() + TextFormatting.GRAY + "]");
        FakePlayer.setmgr.rSetting(this.name);
        Kisman.instance.settingsManager.rSetting(new Setting("CopyInv", this, false));
    }
    
    @Override
    public void onEnable() {
        if (FakePlayer.mc.player == null && FakePlayer.mc.world == null) {
            if (super.isToggled()) {
                super.setToggled(false);
            }
            return;
        }
        final boolean armor = Kisman.instance.settingsManager.getSettingByName(this, "CopyInv").getValBoolean();
        if (FakePlayer.mc.world == null && FakePlayer.mc.player == null) {
            this.onDisable();
            return;
        }
        final EntityOtherPlayerMP clonedPlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("dbc45ea7-e8bd-4a3e-8660-ac064ce58216"), this.name.getValString()));
        clonedPlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        clonedPlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        clonedPlayer.rotationYaw = FakePlayer.mc.player.rotationYaw;
        clonedPlayer.rotationPitch = FakePlayer.mc.player.rotationPitch;
        clonedPlayer.setGameType(GameType.SURVIVAL);
        clonedPlayer.setHealth(20.0f);
        FakePlayer.mc.world.addEntityToWorld(-1337, (Entity)clonedPlayer);
        clonedPlayer.onLivingUpdate();
    }
    
    @Override
    public void onDisable() {
        if (FakePlayer.mc.world == null && FakePlayer.mc.player == null) {
            return;
        }
        FakePlayer.mc.world.removeEntityFromWorld(-1337);
    }
}
