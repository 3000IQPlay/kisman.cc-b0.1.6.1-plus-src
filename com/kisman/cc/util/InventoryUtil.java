//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import com.kisman.cc.mixin.mixins.accessor.*;
import net.minecraft.block.*;
import java.util.*;

public class InventoryUtil
{
    private static final Minecraft mc;
    
    public static void switchToSlot(final int slot, final boolean silent) {
        if (!silent) {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        }
        else {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
            InventoryUtil.mc.player.inventory.currentItem = slot;
        }
    }
    
    public static int findWeaponSlot(final int min, final int max, final boolean shieldBreak) {
        for (int i = min; i <= max; ++i) {
            final ItemStack stack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (shieldBreak) {
                if (stack.getItem() instanceof ItemAxe) {
                    return i;
                }
            }
            else if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemAxe) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean isArmorLow(final EntityPlayer player, final int durability) {
        for (int i = 0; i < 4; ++i) {
            if (getDamageInPercent((ItemStack)player.inventory.armorInventory.get(i)) < durability) {
                return true;
            }
        }
        return false;
    }
    
    public static float getDamageInPercent(final ItemStack stack) {
        final float green = (stack.getMaxDamage() - (float)stack.getItemDamage()) / stack.getMaxDamage();
        final float red = 1.0f - green;
        return (float)(100 - (int)(red * 100.0f));
    }
    
    public static int findItem(final Item item, final int min, final int max) {
        for (int i = min; i <= max; ++i) {
            final ItemStack stack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int findAntiWeaknessTool() {
        return findAntiWeaknessTool(0, 9);
    }
    
    public static int findAntiWeaknessTool(final int min, final int max) {
        for (int i = min; i <= max; ++i) {
            final ItemStack stack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemPickaxe) {
                return i;
            }
        }
        return -1;
    }
    
    public static int findBlock(final Block block, final int min, final int max) {
        for (int i = min; i <= max; ++i) {
            final ItemStack stack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() instanceof ItemBlock) {
                final ItemBlock item = (ItemBlock)stack.getItem();
                if (item.getBlock() == block) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static void switchToSlot(final int slot, final Switch switchMode) {
        if (InventoryUtil.mc.player == null && InventoryUtil.mc.world == null) {
            return;
        }
        if (slot != -1 && InventoryUtil.mc.player.inventory.currentItem != slot) {
            switch (switchMode) {
                case NORMAL: {
                    InventoryUtil.mc.player.inventory.currentItem = slot;
                    InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
                    break;
                }
                case PACKET: {
                    InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
                    break;
                }
            }
        }
        InventoryUtil.mc.playerController.updateController();
    }
    
    public static void switchToSlot(final Item item, final Switch switchMode) {
        if (getItemSlot(item, Inventory.HOTBAR, true) != -1 && InventoryUtil.mc.player.inventory.currentItem != getItemSlot(item, Inventory.HOTBAR, true)) {
            switchToSlot(getItemSlot(item, Inventory.HOTBAR, true), switchMode);
        }
        ((IPlayerControllerMP)InventoryUtil.mc.playerController).syncCurrentPlayItem();
    }
    
    public static int getBlockInHotbar(final boolean onlyObby) {
        int i = 0;
        while (i < 9) {
            if (InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock) {
                if (onlyObby && ((ItemBlock)InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem()).block instanceof BlockObsidian) {
                    return i;
                }
                return i;
            }
            else {
                ++i;
            }
        }
        return -1;
    }
    
    public static int getItemSlot(final Item item, final Inventory inventory, final boolean hotbar) {
        switch (inventory) {
            case HOTBAR: {
                for (int i = 0; i < 9; ++i) {
                    if (InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                        return i;
                    }
                }
                break;
            }
            case INVENTORY: {
                for (int i = hotbar ? 9 : 0; i < 45; ++i) {
                    if (InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                        return i;
                    }
                }
                break;
            }
        }
        return -1;
    }
    
    public static int findItemInHotbar(final Class<? extends Item> itemToFind) {
        int slot = -1;
        final List<ItemStack> mainInventory = (List<ItemStack>)InventoryUtil.mc.player.inventory.mainInventory;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = mainInventory.get(i);
            if (stack != ItemStack.EMPTY) {
                if (itemToFind.isInstance(stack.getItem())) {
                    if (itemToFind.isInstance(stack.getItem())) {
                        slot = i;
                    }
                }
            }
        }
        return slot;
    }
    
    public static int findFirstItemSlot(final Class<? extends Item> itemToFind, final int lower, final int upper) {
        int slot = -1;
        final List<ItemStack> mainInventory = (List<ItemStack>)InventoryUtil.mc.player.inventory.mainInventory;
        for (int i = lower; i <= upper; ++i) {
            final ItemStack stack = mainInventory.get(i);
            if (stack != ItemStack.EMPTY) {
                if (itemToFind.isInstance(stack.getItem())) {
                    if (itemToFind.isInstance(stack.getItem())) {
                        slot = i;
                        break;
                    }
                }
            }
        }
        return slot;
    }
    
    public static int findFirstBlockSlot(final Class<? extends Block> blockToFind, final int lower, final int upper) {
        int slot = -1;
        final List<ItemStack> mainInventory = (List<ItemStack>)InventoryUtil.mc.player.inventory.mainInventory;
        for (int i = lower; i <= upper; ++i) {
            final ItemStack stack = mainInventory.get(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof ItemBlock) {
                    if (blockToFind.isInstance(((ItemBlock)stack.getItem()).getBlock())) {
                        slot = i;
                        break;
                    }
                }
            }
        }
        return slot;
    }
    
    public static List<Integer> findAllItemSlots(final Class<? extends Item> itemToFind) {
        final List<Integer> slots = new ArrayList<Integer>();
        final List<ItemStack> mainInventory = (List<ItemStack>)InventoryUtil.mc.player.inventory.mainInventory;
        for (int i = 0; i < 36; ++i) {
            final ItemStack stack = mainInventory.get(i);
            if (stack != ItemStack.EMPTY) {
                if (itemToFind.isInstance(stack.getItem())) {
                    slots.add(i);
                }
            }
        }
        return slots;
    }
    
    public static List<Integer> findAllBlockSlots(final Class<? extends Block> blockToFind) {
        final List<Integer> slots = new ArrayList<Integer>();
        final List<ItemStack> mainInventory = (List<ItemStack>)InventoryUtil.mc.player.inventory.mainInventory;
        for (int i = 0; i < 36; ++i) {
            final ItemStack stack = mainInventory.get(i);
            if (stack != ItemStack.EMPTY) {
                if (stack.getItem() instanceof ItemBlock) {
                    if (blockToFind.isInstance(((ItemBlock)stack.getItem()).getBlock())) {
                        slots.add(i);
                    }
                }
            }
        }
        return slots;
    }
    
    public static boolean holdingItem(final Class clazz) {
        boolean result = false;
        final ItemStack stack = InventoryUtil.mc.player.getHeldItemMainhand();
        result = isInstanceOf(stack, clazz);
        return result;
    }
    
    public static boolean isInstanceOf(final ItemStack stack, final Class clazz) {
        if (stack == null) {
            return false;
        }
        final Item item = stack.getItem();
        if (clazz.isInstance(item)) {
            return true;
        }
        if (item instanceof ItemBlock) {
            final Block block = Block.getBlockFromItem(item);
            return clazz.isInstance(block);
        }
        return false;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public enum Switch
    {
        NORMAL, 
        PACKET, 
        NONE;
    }
    
    public enum Inventory
    {
        INVENTORY, 
        HOTBAR, 
        CRAFTING;
    }
}
