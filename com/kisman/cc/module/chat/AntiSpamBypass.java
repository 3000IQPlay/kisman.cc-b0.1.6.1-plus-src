//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import java.util.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiSpamBypass extends Module
{
    private Random random;
    
    public AntiSpamBypass() {
        super("AntiSpamBypass", "AntiSpamBypass", Category.CHAT);
        (this.random = new Random()).nextInt();
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        if (!event.getMessage().startsWith("/") && !event.getMessage().startsWith(Kisman.instance.commandManager.cmdPrefixStr) && !event.getMessage().startsWith(".") && !event.getMessage().startsWith(",") && !event.getMessage().startsWith(";") && !event.getMessage().startsWith(":") && !event.getMessage().startsWith("++") && !event.getMessage().startsWith("--") && !event.getMessage().startsWith("-") && !event.getMessage().startsWith("+")) {
            event.setMessage(event.getMessage() + " | " + this.random.nextInt());
        }
    }
}
