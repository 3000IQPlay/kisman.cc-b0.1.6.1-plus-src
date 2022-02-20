//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event;

import net.minecraft.client.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraftforge.common.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.network.*;
import com.kisman.cc.hypixel.util.*;
import java.util.*;
import com.kisman.cc.command.commands.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.kisman.cc.event.events.subscribe.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EventProcessor
{
    private Minecraft mc;
    public boolean hasRan;
    @EventHandler
    private final Listener<PacketEvent.Receive> totempop;
    
    public EventProcessor() {
        this.mc = Minecraft.getMinecraft();
        this.hasRan = false;
        this.totempop = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketEntityStatus && ((SPacketEntityStatus)event.getPacket()).getOpCode() == 35) {
                final TotemPopEvent totemPopEvent = new TotemPopEvent(((SPacketEntityStatus)event.getPacket()).getEntity((World)this.mc.world));
                MinecraftForge.EVENT_BUS.post((Event)totemPopEvent);
                if (totemPopEvent.isCanceled()) {
                    event.cancel();
                }
            }
        }, new Predicate[0]);
        MinecraftForge.EVENT_BUS.register((Object)this);
        Kisman.EVENT_BUS.subscribe((Listener)this.totempop);
    }
    
    @SubscribeEvent
    public void onKey(final InputEvent.KeyInputEvent event) {
        Kisman.EVENT_BUS.post((Object)this);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        Kisman.EVENT_BUS.post((Object)this);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        Kisman.EVENT_BUS.post((Object)this);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        Kisman.EVENT_BUS.post((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatMessage(final ClientChatEvent event) {
        if (event.getMessage().startsWith(Kisman.instance.commandManager.cmdPrefixStr)) {
            try {
                final String str1 = event.getMessage();
                final String str2 = str1.substring(0);
                Kisman.instance.commandManager.runCommands(str2);
                event.setCanceled(true);
            }
            catch (Exception ex) {}
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (ConfigHandler.hasKey("general", "Flip")) {
            final Timer timer = new Timer();
            this.hasRan = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Flip.flip((EntityPlayer)EventProcessor.this.mc.player);
                }
            }, 2000L);
        }
        else {
            ConfigHandler.writeConfig("general", "Flip", "true");
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chat(final ClientChatReceivedEvent event) {
        if (!event.getMessage().getUnformattedText().startsWith("Your new API key is ")) {
            return;
        }
        final String key = event.getMessage().getUnformattedText().split("key is ")[1];
        ConfigHandler.writeConfig("general", "APIKey", key);
        ChatUtils.complete((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.GRAY + " API Key set to " + TextFormatting.GREEN + key));
    }
}
