//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.*;
import com.kisman.cc.util.customfont.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ FontRenderer.class })
public class MixinFontRenderer
{
    @Inject(method = { "drawString" }, at = { @At("HEAD") }, cancellable = true)
    private void onDrawString(final String text, final float x, final float y, final int color, final boolean dropShadow, final CallbackInfoReturnable<Integer> cir) {
        if (CustomFont.instance != null && ClientFont.instance != null && Kisman.instance != null && CustomFont.instance.isToggled() && CustomFont.turnOn && ClientFont.instance.isToggled() && Kisman.instance.customFontRenderer != null) {
            int i = 0;
            final String valString = CustomFont.instance.mode.getValString();
            switch (valString) {
                case "Verdana": {
                    i = (int)Kisman.instance.customFontRenderer.drawString(text, x, y, color, dropShadow);
                    break;
                }
                case "Comfortaa": {
                    i = (int)CustomFontUtil.comfortaa18.drawString(text, x, y, color, dropShadow);
                    break;
                }
            }
            cir.setReturnValue((Object)i);
        }
    }
}
