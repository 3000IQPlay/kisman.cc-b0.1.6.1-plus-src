//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.world.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;

public class Criticals extends Module
{
    private Setting strict;
    private Setting onlyKillaura;
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    public Criticals() {
        super("Criticals", "", Category.COMBAT);
        this.strict = new Setting("Strict", this, false);
        this.onlyKillaura = new Setting("OnlyKillAura", this, false);
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketUseEntity) {
                final CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
                if (packet.action.equals((Object)CPacketUseEntity.Action.ATTACK) && Criticals.mc.player.onGround && !Criticals.mc.player.isInLava() && !Criticals.mc.player.isInWater() && !Criticals.mc.player.isInWeb) {
                    if (this.onlyKillaura.getValBoolean() && !KillAura.instance.isToggled()) {
                        return;
                    }
                    final Entity entity = packet.getEntityFromWorld((World)Criticals.mc.world);
                    if (entity instanceof EntityLivingBase) {
                        final double x = Criticals.mc.player.posX;
                        final double y = Criticals.mc.player.posY;
                        final double z = Criticals.mc.player.posZ;
                        if (this.strict.getValBoolean()) {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + 0.07, z, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + 0.08, z, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
                        }
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + 0.05, z, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + 0.012, z, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
                        Criticals.mc.player.onCriticalHit(entity);
                    }
                }
            }
        }, new Predicate[0]);
        Criticals.setmgr.rSetting(this.strict);
        Criticals.setmgr.rSetting(this.onlyKillaura);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
}
