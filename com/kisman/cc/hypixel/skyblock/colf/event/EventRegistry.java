//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.event;

import net.minecraft.item.*;
import com.mojang.realmsclient.util.*;
import java.time.*;
import net.minecraftforge.fml.common.network.*;
import com.kisman.cc.hypixel.skyblock.colf.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.hypixel.skyblock.colf.commands.models.*;
import com.kisman.cc.hypixel.skyblock.colf.commands.*;
import java.time.chrono.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.init.*;

public class EventRegistry
{
    public static ItemStack GOLD_NUGGET;
    public static final Pair<String, Pair<String, LocalDateTime>> EMPTY;
    public static Pair<String, Pair<String, LocalDateTime>> last;
    public static long lastStartTime;
    
    @SubscribeEvent
    public void onDisconnectedFromServerEvent(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        if (MainColf.wrapper.isRunning) {
            System.out.println("Disconnected from server");
            MainColf.wrapper.stop();
            System.out.println("CoflSky stopped");
        }
    }
    
    public static String ExtractUuidFromInventory(final IInventory inventory) {
        final ItemStack stack = inventory.getStackInSlot(13);
        if (stack != null) {
            try {
                final String uuid = stack.serializeNBT().getCompoundTag("tag").getCompoundTag("ExtraAttributes").getString("uuid");
                if (uuid.length() == 0) {
                    throw new Exception();
                }
                System.out.println("Item has the UUID: " + uuid);
                return uuid;
            }
            catch (Exception e) {
                System.out.println("Clicked item " + stack.getDisplayName() + " has the following meta: " + stack.serializeNBT().toString());
            }
        }
        return "";
    }
    
    @SubscribeEvent
    public void HandleChatEvent(final ClientChatReceivedEvent sce) {
        if (MainColf.wrapper.isRunning && EventRegistry.last.first() != null) {
            if (sce.getMessage().getUnformattedText().startsWith("You claimed ")) {
                final AuctionData ad = new AuctionData();
                ad.setItemId((String)((Pair)EventRegistry.last.second()).first());
                ad.setAuctionId("");
                final Command<AuctionData> data = (Command<AuctionData>)new Command(CommandType.PurchaseConfirm, (Object)ad);
                MainColf.wrapper.SendMessage(data);
                System.out.println("PurchaseConfirm");
                EventRegistry.last = EventRegistry.EMPTY;
            }
            else if (((LocalDateTime)((Pair)EventRegistry.last.second()).second()).plusSeconds(10L).isBefore(LocalDateTime.now())) {
                EventRegistry.last = EventRegistry.EMPTY;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void OnGuiClick(final GuiScreenEvent.MouseInputEvent mie) {
        if (MainColf.wrapper.isRunning && mie.getGui() instanceof GuiChest) {
            final ContainerChest chest = (ContainerChest)((GuiChest)mie.getGui()).inventorySlots;
            final IInventory inv = chest.getLowerChestInventory();
            if (inv.hasCustomName()) {
                final String chestName = inv.getName();
                if (chestName.equalsIgnoreCase("BIN Auction View") || chestName.equalsIgnoreCase("Ekwav")) {
                    final ItemStack heldItem = Minecraft.getMinecraft().player.inventory.getItemStack();
                    if (heldItem != null) {
                        System.out.println("Clicked on: " + heldItem.getItem().getRegistryName());
                        final String itemUUID = ExtractUuidFromInventory(inv);
                        if (System.currentTimeMillis() + 200L < EventRegistry.lastStartTime && heldItem.isItemEqual(EventRegistry.GOLD_NUGGET)) {
                            final AuctionData ad = new AuctionData();
                            ad.setItemId(itemUUID);
                            ad.setAuctionId("");
                            final Command<AuctionData> data = (Command<AuctionData>)new Command(CommandType.PurchaseStart, (Object)ad);
                            MainColf.wrapper.SendMessage(data);
                            System.out.println("PurchaseStart");
                            EventRegistry.last = (Pair<String, Pair<String, LocalDateTime>>)Pair.of((Object)"You claimed ", (Object)Pair.of((Object)itemUUID, (Object)LocalDateTime.now()));
                            EventRegistry.lastStartTime = System.currentTimeMillis();
                        }
                    }
                }
            }
        }
    }
    
    static {
        EventRegistry.GOLD_NUGGET = new ItemStack(Items.GOLD_NUGGET);
        EMPTY = Pair.of((Object)null, (Object)Pair.of((Object)"", (Object)LocalDateTime.MIN));
        EventRegistry.last = EventRegistry.EMPTY;
        EventRegistry.lastStartTime = Long.MAX_VALUE;
    }
}
