//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.combat;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.util.text.*;
import com.kisman.cc.module.*;
import com.kisman.cc.module.combat.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PvpInfo extends HudModule
{
    public PvpInfo() {
        super("PvpInfo", "PvpInfo", HudCategory.COMBAT);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final int y = (int)HUD.instance.pvpY.getValDouble();
        final int heigth = 2 + CustomFontUtil.getFontHeight();
        int count = 0;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "KA: " + (this.isToggled(KillAura.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "CA: " + (this.isToggled(AutoCrystalRewrite.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "FA: " + (this.isToggled(AutoFirework.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "OFF: " + (this.isToggled(OffHand.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "SURR: " + (this.isToggled(Surround.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "AT: " + (this.isToggled(AutoTrap.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
        ++count;
        CustomFontUtil.drawStringWithShadow(TextFormatting.GRAY + "HF: " + (this.isToggled(HoleFiller.instance) ? (TextFormatting.GREEN + "ON") : (TextFormatting.RED + "OFF")), 1.0, count * heigth + y, -1);
    }
    
    private boolean isToggled(final Module mod) {
        return mod.isToggled();
    }
}
