//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

public class Swing extends Module
{
    private Setting mode;
    
    public Swing() {
        super("Swing", "swing", Category.PLAYER);
        this.mode = new Setting("Mode", this, Hand.MAINHAND);
        Swing.setmgr.rSetting(this.mode);
    }
    
    public void update() {
        if (Swing.mc.player == null && Swing.mc.world == null) {
            return;
        }
        if (this.mode.getValString().equals(Hand.MAINHAND.name())) {
            Swing.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        else if (this.mode.getValString().equals(Hand.OFFHAND.name())) {
            Swing.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        else if (this.mode.getValString().equals(Hand.PACKETSWING.name()) && Swing.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && Swing.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            Swing.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            Swing.mc.entityRenderer.itemRenderer.itemStackMainHand = Swing.mc.player.getHeldItemMainhand();
        }
    }
    
    public enum Hand
    {
        OFFHAND, 
        MAINHAND, 
        PACKETSWING;
    }
}
