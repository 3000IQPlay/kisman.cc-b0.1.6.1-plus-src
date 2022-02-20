//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class AutoTotem extends Module
{
    public AutoTotem() {
        super("AutoTotem", "simple offhand", Category.COMBAT);
        Kisman.instance.settingsManager.rSetting(new Setting("Health", this, 10.0, 1.0, 20.0, true));
    }
    
    @Override
    public void update() {
        if (AutoTotem.mc.player == null && AutoTotem.mc.world == null) {
            return;
        }
        int totemSlot = 0;
        final ItemStack offhand = AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
        final NonNullList<ItemStack> inv = (NonNullList<ItemStack>)AutoTotem.mc.player.inventory.mainInventory;
        final int health = (int)Kisman.instance.settingsManager.getSettingByName(this, "Health").getValDouble();
        for (int inventoryIndex = 0; inventoryIndex < inv.size(); ++inventoryIndex) {
            if (inv.get(inventoryIndex) != ItemStack.EMPTY && ((ItemStack)inv.get(inventoryIndex)).getItem() == Items.TOTEM_OF_UNDYING) {
                totemSlot = inventoryIndex;
            }
        }
        if (AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount() < health) {
            if (offhand == null || offhand.getItem() == Items.TOTEM_OF_UNDYING) {
                return;
            }
            if (totemSlot != 0) {
                AutoTotem.mc.playerController.windowClick(0, totemSlot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(0, totemSlot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            }
        }
    }
}
