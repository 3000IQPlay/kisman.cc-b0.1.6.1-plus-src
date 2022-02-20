//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import net.minecraft.client.gui.inventory.*;
import com.kisman.cc.util.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import com.kisman.cc.friend.*;

public class OffHand extends Module
{
    public static OffHand instance;
    private final Setting health;
    private final Setting mode;
    private final Setting fallBackMode;
    private final Setting fallBackDistance;
    private final Setting totemOnElytra;
    private final Setting offhandGapOnSword;
    private final Setting hotbarFirst;
    
    public OffHand() {
        super("OffHand", "gg", Category.COMBAT);
        this.health = new Setting("Health", this, 11.0, 0.0, 20.0, true);
        this.mode = new Setting("Mode", this, "Totem", new ArrayList<String>(Arrays.asList("Totem", "Crystal", "Gapple", "Pearl", "Chorus", "Strength", "Shield")));
        this.fallBackMode = new Setting("FallBackMode", this, "Crystal", new ArrayList<String>(Arrays.asList("Totem", "Crystal", "Gapple", "Pearl", "Chorus", "Strength", "Shield")));
        this.fallBackDistance = new Setting("FallBackDistance", this, 15.0, 0.0, 100.0, true);
        this.totemOnElytra = new Setting("TotemOnElytra", this, true);
        this.offhandGapOnSword = new Setting("OffhandGapOnSword", this, true);
        this.hotbarFirst = new Setting("HotbarFirst", this, false);
        OffHand.instance = this;
        OffHand.setmgr.rSetting(this.health);
        OffHand.setmgr.rSetting(this.mode);
        OffHand.setmgr.rSetting(this.fallBackMode);
        OffHand.setmgr.rSetting(this.fallBackDistance);
        OffHand.setmgr.rSetting(this.totemOnElytra);
        OffHand.setmgr.rSetting(this.offhandGapOnSword);
        OffHand.setmgr.rSetting(this.hotbarFirst);
    }
    
    @Override
    public void update() {
        if (OffHand.mc.player == null && OffHand.mc.world == null) {
            return;
        }
        if (OffHand.mc.currentScreen != null && !(OffHand.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        super.setDisplayInfo("[" + this.mode.getValString() + "]");
        if (!OffHand.mc.player.getHeldItemMainhand().isEmpty() && this.health.getValDouble() <= OffHand.mc.player.getHealth() + OffHand.mc.player.getAbsorptionAmount() && OffHand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && this.offhandGapOnSword.getValBoolean()) {
            this.switchOffHandIfNeed("Gap");
            return;
        }
        if (this.health.getValDouble() > OffHand.mc.player.getHealth() + OffHand.mc.player.getAbsorptionAmount() || this.mode.getValString().equalsIgnoreCase("Totem") || (this.totemOnElytra.getValBoolean() && OffHand.mc.player.isElytraFlying()) || (OffHand.mc.player.fallDistance >= this.fallBackDistance.getValDouble() && !OffHand.mc.player.isElytraFlying()) || this.noNearbyPlayers()) {
            this.switchOffHandIfNeed("Totem");
            return;
        }
        this.switchOffHandIfNeed(this.mode.getValString());
    }
    
    private void switchOffHandIfNeed(final String mode) {
        final Item item = this.getItemFromModeVal(mode);
        if (OffHand.mc.player.getHeldItemOffhand().getItem() != item) {
            int slot = this.hotbarFirst.getValBoolean() ? PlayerUtil.GetRecursiveItemSlot(item) : PlayerUtil.GetItemSlot(item);
            Item fallback = this.getItemFromModeVal(this.fallBackMode.getValString());
            String display = this.getItemNameFromModeVal(mode);
            if (slot == -1 && item != fallback && OffHand.mc.player.getHeldItemOffhand().getItem() != fallback) {
                slot = PlayerUtil.GetRecursiveItemSlot(fallback);
                display = this.getItemNameFromModeVal(this.fallBackMode.getValString());
                if (slot == -1 && fallback != Items.TOTEM_OF_UNDYING) {
                    fallback = Items.TOTEM_OF_UNDYING;
                    if (item != fallback && OffHand.mc.player.getHeldItemOffhand().getItem() != fallback) {
                        slot = PlayerUtil.GetRecursiveItemSlot(fallback);
                        display = "Emergency Totem";
                    }
                }
            }
            if (slot != -1) {
                OffHand.mc.playerController.windowClick(OffHand.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)OffHand.mc.player);
                OffHand.mc.playerController.windowClick(OffHand.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)OffHand.mc.player);
                OffHand.mc.playerController.windowClick(OffHand.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)OffHand.mc.player);
                OffHand.mc.playerController.updateController();
                ChatUtils.complete((Object)(ChatFormatting.LIGHT_PURPLE + "Offhand now has a " + display));
            }
        }
    }
    
    private boolean isValidTarget(final EntityPlayer player) {
        return player != OffHand.mc.player && OffHand.mc.player.getDistance((Entity)player) <= 15.0f && !FriendManager.instance.isFriend(player);
    }
    
    public Item getItemFromModeVal(final String mode) {
        switch (mode) {
            case "Crystal": {
                return Items.END_CRYSTAL;
            }
            case "Gap": {
                return Items.GOLDEN_APPLE;
            }
            case "Pearl": {
                return Items.ENDER_PEARL;
            }
            case "Chorus": {
                return Items.CHORUS_FRUIT;
            }
            case "Strength": {
                return (Item)Items.POTIONITEM;
            }
            case "Shield": {
                return Items.SHIELD;
            }
            default: {
                return Items.TOTEM_OF_UNDYING;
            }
        }
    }
    
    private String getItemNameFromModeVal(final String mode) {
        switch (mode) {
            case "Crystal": {
                return "End Crystal";
            }
            case "Gap": {
                return "Gap";
            }
            case "Pearl": {
                return "Pearl";
            }
            case "Chorus": {
                return "Chorus";
            }
            case "Strength": {
                return "Strength";
            }
            case "Shield": {
                return "Shield";
            }
            default: {
                return "Totem";
            }
        }
    }
    
    private boolean noNearbyPlayers() {
        return this.mode.getValString().equalsIgnoreCase("Crystal") && OffHand.mc.world.playerEntities.stream().noneMatch(e -> e != OffHand.mc.player && this.isValidTarget(e));
    }
}
