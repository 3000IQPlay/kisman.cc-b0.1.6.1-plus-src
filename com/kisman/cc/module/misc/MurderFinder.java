//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.module.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;

public class MurderFinder extends Module
{
    public MurderFinder() {
        super("MurderFinder", "MurderFinder", Category.MISC);
    }
    
    @Override
    public void update() {
        if (MurderFinder.mc.player == null && MurderFinder.mc.world == null) {
            return;
        }
        MurderFinder.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityPlayer).filter(entity -> entity == MurderFinder.mc.player).forEach(entity -> {
            if (isMurderer(entity)) {
                ((Entity)entity).setGlowing(true);
            }
        });
    }
    
    @Override
    public void onDisable() {
        if (MurderFinder.mc.player == null && MurderFinder.mc.world == null) {
            return;
        }
        MurderFinder.mc.world.loadedEntityList.stream().filter(entity -> entity.isGlowing()).filter(entity -> entity != MurderFinder.mc.player).forEach(entity -> entity.setGlowing(false));
    }
    
    public static boolean isMurderer(final EntityPlayer player) {
        for (int i = 0; i < 9; ++i) {
            if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemSword && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemBow) && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemMap) && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemPotion) && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemEmptyMap) && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemSnowball) && player.inventory.getStackInSlot(i).getItem() != null && player.inventory.getStackInSlot(i).getItem() != null && player.inventory.getStackInSlot(i).getItem() != null) {
                return true;
            }
        }
        return false;
    }
}
