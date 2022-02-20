//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.hwid;

import com.kisman.cc.app.*;
import com.kisman.cc.*;
import com.kisman.cc.util.discord.*;
import net.minecraft.client.*;
import java.io.*;

public class Verificator
{
    public boolean preInit() {
        if (!HWID.getHWIDList().contains(HWID.getHWID()) && !HWID.getHWIDList().contains("0")) {
            HWIDWindow.init();
            throw new NoStackTraceThrowable("Verify HWID Failed!");
        }
        if (HWID.getHWIDList().contains(HWID.getHWID())) {
            Kisman.LOGGER.info("HWID Verify! Done");
            return true;
        }
        if (HWID.getHWIDList().contains("0")) {
            final Kisman instance = Kisman.instance;
            final DiscordWebhook hook = new DiscordWebhook(Kisman.HWID_LOGS);
            hook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("HWID Logs!").addField("ING: ", Minecraft.getMinecraft().session.getUsername(), true).addField("HWID: ", HWID.getHWID(), true));
            try {
                hook.execute();
            }
            catch (IOException ex) {}
            throw new NoStackTraceThrowable("Register HWID Complete! ShutDown!");
        }
        return false;
    }
}
