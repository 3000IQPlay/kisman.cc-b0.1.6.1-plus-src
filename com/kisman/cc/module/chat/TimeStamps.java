//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import com.kisman.cc.util.*;
import java.util.function.*;
import com.kisman.cc.*;
import java.text.*;
import java.util.*;
import com.kisman.cc.event.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class TimeStamps extends Module
{
    private Setting color;
    @EventHandler
    private final Listener<PacketEvent> listener;
    
    public TimeStamps() {
        super("TimeStamps", "TimeStamps in your chat", Category.CHAT);
        this.color = new Setting("Color", this, TextUtil.Color.GRAY);
        this.listener = (Listener<PacketEvent>)new Listener(event -> {
            if (event.getEra().equals((Object)Event.Era.PRE) && this.color.getValEnum() != TextUtil.Color.NONE && event.getPacket() instanceof SPacketChat) {
                if (!((SPacketChat)event.getPacket()).isSystem()) {
                    return;
                }
                final String oldMessage = ((SPacketChat)event.getPacket()).chatComponent.getFormattedText();
                final String message = this.getTimeString() + oldMessage;
                ((SPacketChat)event.getPacket()).chatComponent = (ITextComponent)new TextComponentString(message);
            }
        }, new Predicate[0]);
        TimeStamps.setmgr.rSetting(this.color);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    public String getTimeString() {
        final String date = new SimpleDateFormat("k:mm").format(new Date());
        return TextUtil.coloredString(date, (TextUtil.Color)this.color.getValEnum()) + "§r";
    }
}
