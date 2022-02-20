//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.event.events.subscribe.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TotemPopCounter extends Module
{
    private Setting target;
    
    public TotemPopCounter() {
        super("TotemPopCounter", "totem pops count!", Category.CHAT);
        this.target = new Setting("Target", this, TargetMode.Both);
    }
    
    @SubscribeEvent
    public void onTotemPop(final TotemPopEvent event) {
        if (event.getPopEntity() instanceof EntityPlayer) {
            final boolean isFriend = Kisman.instance.friendManager.isFriend((EntityPlayer)event.getPopEntity());
            if (isFriend && this.target.getValString().equals("Only Other Players")) {
                return;
            }
            if (!isFriend && this.target.getValString().equals("Only Friends")) {
                return;
            }
            ChatUtils.warning((Object)((isFriend ? TextFormatting.AQUA : TextFormatting.GRAY) + event.getPopEntity().getName() + TextFormatting.GRAY + " was popped totem!"));
        }
    }
    
    public enum TargetMode
    {
        Friend("Only Friends"), 
        OtherPLayers("Only Other Players"), 
        Both("Both");
        
        public String name;
        
        private TargetMode(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
