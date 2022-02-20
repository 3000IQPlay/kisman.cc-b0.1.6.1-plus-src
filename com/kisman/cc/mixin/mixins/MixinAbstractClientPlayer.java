//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.*;
import com.kisman.cc.*;
import com.kisman.cc.module.render.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.module.client.*;

@Mixin(value = { AbstractClientPlayer.class }, priority = 10000)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer
{
    Minecraft mc;
    String str1;
    String str2;
    int count;
    TimerUtils timer;
    @Shadow
    public NetworkPlayerInfo playerInfo;
    
    public MixinAbstractClientPlayer() {
        this.mc = Minecraft.getMinecraft();
        this.str1 = "cape-";
        this.str2 = ".png";
        this.count = 0;
        this.timer = new TimerUtils();
    }
    
    @Shadow
    protected abstract boolean isSpectator();
    
    @Inject(method = { "getLocationSkin()Lnet/minecraft/util/ResourceLocation;" }, at = { @At("HEAD") }, cancellable = true)
    private void getLocationSkin(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (Kisman.instance.moduleManager.getModule("Charms").isToggled() && Kisman.instance.settingsManager.getSettingByName(Kisman.instance.moduleManager.getModule("Charms"), "Texture").getValBoolean() && Charms.instance.textureMode.getValString().equalsIgnoreCase("Texture")) {
            callbackInfoReturnable.setReturnValue((Object)new ResourceLocation("kismancc:charms/charms1.png"));
        }
    }
    
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    private void getLocationCape(final CallbackInfoReturnable<ResourceLocation> cir) {
        if (Cape.instance.isToggled() && this.playerInfo == this.mc.player.getPlayerInfo()) {
            final String valString = Cape.instance.mode.getValString();
            switch (valString) {
                case "Static": {
                    cir.setReturnValue((Object)new ResourceLocation("kismancc:cape/cape1.png"));
                    break;
                }
                case "Gif": {
                    cir.setReturnValue((Object)this.getCape());
                    break;
                }
                case "xulu+": {
                    cir.setReturnValue((Object)new ResourceLocation("kismancc:cape/xuluplus/xulupluscape.png"));
                    break;
                }
            }
        }
    }
    
    private ResourceLocation getCape() {
        if (this.count > 34) {
            this.count = 0;
        }
        final ResourceLocation cape = new ResourceLocation("kismancc:cape/rainbow/" + this.str1 + this.count + this.str2);
        if (this.timer.passedMillis(85L)) {
            ++this.count;
            this.timer.reset();
        }
        return cape;
    }
}
