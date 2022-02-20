//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoGlobal extends Module
{
    public AutoGlobal() {
        super("AutoGlobal", "for nn servers", Category.CHAT);
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        if (!event.getMessage().startsWith("/") && !event.getMessage().startsWith(Kisman.instance.commandManager.cmdPrefixStr) && !event.getMessage().startsWith(".") && !event.getMessage().startsWith(",") && !event.getMessage().startsWith(";") && !event.getMessage().startsWith(":") && !event.getMessage().startsWith("++") && !event.getMessage().startsWith("--") && !event.getMessage().startsWith("-") && !event.getMessage().startsWith("+")) {
            event.setMessage("!" + event.getMessage());
        }
    }
}
