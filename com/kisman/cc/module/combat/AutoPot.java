//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.module.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.item.*;
import java.util.*;

public class AutoPot extends Module
{
    private TimerUtils counter;
    
    public AutoPot() {
        super("AutoPot", "auto potion", Category.COMBAT);
        this.counter = new TimerUtils();
    }
    
    @Override
    public void update() {
    }
    
    private void throwPot(final Potions potion) {
        final int slot = this.getPotionSlot(potion);
        AutoPot.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        AutoPot.mc.playerController.updateController();
        AutoPot.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        AutoPot.mc.playerController.updateController();
        AutoPot.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(AutoPot.mc.player.inventory.currentItem));
    }
    
    private int getPotionSlot(final Potions potion) {
        for (int i = 0; i < 9; ++i) {
            if (this.isStackPotion(AutoPot.mc.player.inventory.getStackInSlot(i), potion)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean isPotionOnHotBar() {
        for (int i = 0; i < 9; ++i) {
            if (this.isStackPotion(AutoPot.mc.player.inventory.getStackInSlot(i), Potions.STRENGTH) || this.isStackPotion(AutoPot.mc.player.inventory.getStackInSlot(i), Potions.SPEED) || this.isStackPotion(AutoPot.mc.player.inventory.getStackInSlot(i), Potions.FIRERES)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isStackPotion(final ItemStack stack, final Potions potion) {
        if (stack == null) {
            return false;
        }
        final Item item = stack.getItem();
        if (item == Items.SPLASH_POTION) {
            int id = 5;
            switch (potion) {
                case STRENGTH: {
                    id = 5;
                    break;
                }
                case SPEED: {
                    id = 1;
                    break;
                }
                case FIRERES: {
                    id = 12;
                    break;
                }
            }
            for (final PotionEffect effect : PotionUtils.getEffectsFromStack(stack)) {
                if (effect.getPotion() == Potion.getPotionById(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    enum Potions
    {
        STRENGTH, 
        SPEED, 
        FIRERES;
    }
}
