//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.oldclickgui.alts.*;

@Mixin(value = { GuiMultiplayer.class }, priority = 10000)
public class MixinGuiMultiplayer extends GuiScreen
{
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    public void initGui(final CallbackInfo ci) {
        this.buttonList.add(new GuiButton(417, 7, 7, 60, 20, "Alts"));
    }
    
    @Inject(method = { "actionPerformed" }, at = { @At("RETURN") })
    public void actionPerformed(final GuiButton button, final CallbackInfo ci) {
        if (button.id == 417) {
            this.mc.displayGuiScreen((GuiScreen)new AltManagerGUI(this));
        }
    }
}
