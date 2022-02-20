//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import org.spongepowered.asm.mixin.*;
import org.lwjgl.input.*;
import com.kisman.cc.module.misc.*;

@Mixin(value = { GuiContainer.class }, priority = 10000)
public class MixinGuiContainer extends GuiScreen
{
    @Shadow
    private Slot hoveredSlot;
    
    @Overwrite
    protected void renderHoveredToolTip(final int p_191948_1_, final int p_191948_2_) {
        if (this.mc.player.inventory.getItemStack().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.getHasStack()) {
            this.renderToolTip(this.hoveredSlot.getStack(), p_191948_1_, p_191948_2_ + this.getScrollWheel());
        }
    }
    
    private int getScrollWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            return -ItemScroller.instance.scrollSpeed.getValInt();
        }
        if (dWheel > 0) {
            return ItemScroller.instance.scrollSpeed.getValInt();
        }
        return 0;
    }
}
