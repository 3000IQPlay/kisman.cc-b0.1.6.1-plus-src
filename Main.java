//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

import net.minecraftforge.fml.common.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.event.*;
import com.kisman.cc.util.hwid.*;
import com.kisman.cc.module.combat.*;
import net.minecraftforge.common.*;
import com.kisman.cc.module.client.*;
import java.io.*;
import net.minecraftforge.fml.common.network.*;
import com.kisman.cc.file.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mod(modid = "kisman", name = "kisman.cc+", version = "b0.1.6.1")
public class Main
{
    private final Kisman k;
    
    public Main() {
        this.k = new Kisman();
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws IOException, NoSuchFieldException, IllegalAccessException {
        this.k.init();
        if (!Kisman.instance.d1.preInit()) {
            throw new NoStackTraceThrowable("YesComment");
        }
        AutoTrap.instance.setToggled(false);
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (this.k.d2 == null && Dumper.instance.isToggled()) {
            throw new NoStackTraceThrowable("Dumper init Failed");
        }
        this.k.d2.init();
    }
    
    @SubscribeEvent
    public void disconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        AutoTrap.instance.setToggled(false);
        SaveConfig.init();
    }
}
