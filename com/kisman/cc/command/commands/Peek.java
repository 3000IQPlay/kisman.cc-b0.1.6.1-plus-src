//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.entity.*;
import net.minecraft.network.datasync.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.*;
import com.kisman.cc.oldclickgui.misc.*;
import java.util.*;
import net.minecraft.nbt.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import io.netty.buffer.*;
import net.minecraft.network.*;

public class Peek extends Command
{
    private GuiScreen screen;
    
    public Peek() {
        super("peek");
    }
    
    public void runCommand(final String s, final String[] args) {
        try {
            if (!args[0].equalsIgnoreCase("book") || !args[0].equalsIgnoreCase("shulker")) {
                ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                return;
            }
            final boolean book = args.length > 0 && args[0].equals("book");
            double distance = 0.0;
            ItemStack stack = null;
            for (final Entity entity : Peek.mc.world.getEntitiesWithinAABB((Class)Entity.class, Peek.mc.player.getEntityBoundingBox().grow(12.0, 4.0, 12.0))) {
                if (entity == Peek.mc.player) {
                    continue;
                }
                ItemStack current = null;
                for (final EntityDataManager.DataEntry<?> entry : entity.getDataManager().getAll()) {
                    if (entry.getValue() instanceof ItemStack && ((book && (((ItemStack)entry.getValue()).getItem() instanceof ItemWritableBook || ((ItemStack)entry.getValue()).getItem() instanceof ItemWrittenBook)) || (!book && ((ItemStack)entry.getValue()).getItem() instanceof ItemShulkerBox))) {
                        current = (ItemStack)entry.getValue();
                        break;
                    }
                }
                if (current == null) {
                    for (final ItemStack item : entity.getEquipmentAndArmor()) {
                        if ((!book && item.getItem() instanceof ItemShulkerBox) || (book && (item.getItem() instanceof ItemWritableBook || item.getItem() instanceof ItemWrittenBook))) {
                            current = item;
                            break;
                        }
                    }
                }
                final double sqDist = Peek.mc.player.getDistanceSq(entity);
                if (current == null || (stack != null && sqDist >= distance)) {
                    continue;
                }
                stack = current;
                distance = sqDist;
            }
            if (stack == null) {
                ChatUtils.error((Object)("No " + (book ? "book" : "shulker") + " item close to you"));
                return;
            }
            if (book) {
                if (stack.getItem() instanceof ItemWritableBook) {
                    stack = stack.copy();
                    stack.getTagCompound().setString("title", "Writable book");
                    stack.getTagCompound().setString("author", "No author");
                }
                if (!stack.getTagCompound().hasKey("pages", 9)) {
                    ChatUtils.error((Object)"Book has no data");
                    return;
                }
                ChatUtils.message((Object)("Book size: " + Integer.toString(this.getItemSize(stack)) + " bytes"));
                this.screen = (GuiScreen)new GuiScreenBook((EntityPlayer)Peek.mc.player, stack, false);
                MinecraftForge.EVENT_BUS.register((Object)this);
            }
            else {
                final NBTTagCompound tag = stack.getTagCompound();
                if (tag != null && tag.hasKey("BlockEntityTag") && tag.getTagId("BlockEntityTag") == 10) {
                    final NBTTagCompound blockTag = tag.getCompoundTag("BlockEntityTag");
                    if (blockTag.hasKey("Items") && blockTag.getTagId("Items") == 9) {
                        this.screen = new PreviewGui(blockTag.getTagList("Items", 10), false);
                        MinecraftForge.EVENT_BUS.register((Object)this);
                        return;
                    }
                }
                ChatUtils.error((Object)"Shulker is empty, or the server did not communicate its content");
            }
        }
        catch (Exception e) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
        }
    }
    
    public String getDescription() {
        return "";
    }
    
    public String getSyntax() {
        return "peek <shulcer/book>";
    }
    
    @SubscribeEvent
    public void onGuiOpened(final GuiOpenEvent event) {
        if (event.getGui() == null) {
            event.setGui(this.screen);
            this.screen = null;
        }
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    private int getItemSize(final ItemStack stack) {
        final PacketBuffer buff = new PacketBuffer(Unpooled.buffer());
        buff.writeItemStack(stack);
        final int size = buff.writerIndex();
        buff.release();
        return size;
    }
}
