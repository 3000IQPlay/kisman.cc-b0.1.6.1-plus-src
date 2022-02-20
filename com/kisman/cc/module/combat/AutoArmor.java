//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.util.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class AutoArmor extends Module
{
    public AutoArmor() {
        super("AutoArmor", "ebate srate lox!", Category.COMBAT);
        Kisman.instance.settingsManager.rSetting(new Setting("NoThorns", this, false));
    }
    
    @Override
    public void update() {
        if (AutoArmor.mc.player == null && AutoArmor.mc.world == null) {
            return;
        }
        final boolean noThorns = Kisman.instance.settingsManager.getSettingByName(this, "NoThorns").getValBoolean();
        if (AutoArmor.mc.player.ticksExisted % 2 == 0) {
            return;
        }
        if (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final List<ItemStack> armorInventory = (List<ItemStack>)AutoArmor.mc.player.inventory.armorInventory;
        final List<ItemStack> inventory = (List<ItemStack>)AutoArmor.mc.player.inventory.mainInventory;
        final int[] bestArmorSlots = { -1, -1, -1, -1 };
        final int[] bestArmorValues = { -1, -1, -1, -1 };
        for (int i = 0; i < 4; ++i) {
            final ItemStack oldArmour = armorInventory.get(i);
            if (oldArmour.getItem() instanceof ItemArmor) {
                bestArmorValues[i] = ((ItemArmor)oldArmour.getItem()).damageReduceAmount;
            }
        }
        final List<Integer> slots = InventoryUtil.findAllItemSlots((Class<? extends Item>)ItemArmor.class);
        final HashMap<Integer, ItemStack> armour = new HashMap<Integer, ItemStack>();
        final HashMap<Integer, ItemStack> thorns = new HashMap<Integer, ItemStack>();
        for (final Integer slot : slots) {
            final ItemStack item = inventory.get(slot);
            if (noThorns && EnchantmentHelper.getEnchantments(item).containsKey(Enchantment.getEnchantmentByID(7))) {
                thorns.put(slot, item);
            }
            else {
                armour.put(slot, item);
            }
        }
        final ItemArmor itemArmor;
        final int armorType;
        int armorValue;
        final Object o;
        final Object o2;
        armour.forEach((integer, itemStack) -> {
            itemArmor = (ItemArmor)itemStack.getItem();
            armorType = itemArmor.armorType.ordinal() - 2;
            if (armorType == 2 && AutoArmor.mc.player.inventory.armorItemInSlot(armorType).getItem().equals(Items.ELYTRA)) {
                return;
            }
            else {
                armorValue = itemArmor.damageReduceAmount;
                if (armorValue > o[armorType]) {
                    o2[armorType] = (int)integer;
                    o[armorType] = armorValue;
                }
                return;
            }
        });
        if (noThorns) {
            final ItemArmor itemArmor2;
            final int armorType2;
            final List list;
            final Object o3;
            int armorValue2;
            final Object o4;
            thorns.forEach((integer, itemStack) -> {
                itemArmor2 = (ItemArmor)itemStack.getItem();
                armorType2 = itemArmor2.armorType.ordinal() - 2;
                if (list.get(armorType2) != ItemStack.EMPTY || o3[armorType2] != -1) {
                    return;
                }
                else if (armorType2 == 2 && AutoArmor.mc.player.inventory.armorItemInSlot(armorType2).getItem().equals(Items.ELYTRA)) {
                    return;
                }
                else {
                    armorValue2 = itemArmor2.damageReduceAmount;
                    if (armorValue2 > o4[armorType2]) {
                        o3[armorType2] = (int)integer;
                        o4[armorType2] = armorValue2;
                    }
                    return;
                }
            });
        }
        for (int j = 0; j < 4; ++j) {
            int slot2 = bestArmorSlots[j];
            if (slot2 != -1) {
                if (slot2 < 9) {
                    slot2 += 36;
                }
                AutoArmor.mc.playerController.windowClick(0, slot2, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
                AutoArmor.mc.playerController.windowClick(0, 8 - j, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
                AutoArmor.mc.playerController.windowClick(0, slot2, 0, ClickType.PICKUP, (EntityPlayer)AutoArmor.mc.player);
            }
        }
    }
}
