//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChatSuffix extends Module
{
    public ChatSuffix() {
        super("ChatSuffix", "green", Category.CHAT);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onChat(final ClientChatEvent event) {
        event.setMessage(event.getMessage() + " | " + Kisman.getName() + " own you and all");
    }
}
