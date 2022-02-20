//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class NameProtect extends Module
{
    private Setting name;
    public static NameProtect instance;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public NameProtect() {
        super("NameProtect", "NameProtect", Category.MISC);
        this.name = new Setting("Name", this, "Kisman", "Kisman", true);
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            final SPacketChat packet;
            if (event.getPacket() instanceof SPacketChat && (packet = (SPacketChat)event.getPacket()).getType() != ChatType.GAME_INFO && this.getChatNames(packet.getChatComponent().getFormattedText(), packet.getChatComponent().getUnformattedText())) {
                event.cancel();
            }
        }, new Predicate[0]);
        NameProtect.instance = this;
        NameProtect.setmgr.rSetting(this.name);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    private boolean getChatNames(final String message, final String unformatted) {
        String out = message;
        if (NameProtect.mc.player == null) {
            return false;
        }
        out = out.replace(NameProtect.mc.player.getName(), this.name.getValString());
        ChatUtils.simpleMessage((Object)out);
        return true;
    }
    
    public static String getPlayerName(final NetworkPlayerInfo networkPlayerInfoIn) {
        String dname = (networkPlayerInfoIn.getDisplayName() != null) ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        dname = dname.replace(NameProtect.mc.player.getName(), NameProtect.instance.name.getValString());
        return dname;
    }
}
