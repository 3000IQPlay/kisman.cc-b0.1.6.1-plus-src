//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.combat;

import com.kisman.cc.util.customfont.*;
import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.combat.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

public class TargetHUD extends HudModule
{
    private float healthWidth;
    private static final CustomFontRenderer fontRenderer;
    private static final CustomFontRenderer fontRenderer1;
    
    public TargetHUD() {
        super("TargetHUD", "TargetInfo", HudCategory.COMBAT);
        this.healthWidth = 20.0f;
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        EntityLivingBase target = null;
        if (AutoCrystalBypass.instance.isToggled()) {
            target = (EntityLivingBase)AutoCrystalBypass.instance.target;
        }
        else if (AutoFirework.instance.isToggled()) {
            target = (EntityLivingBase)AutoFirework.instance.target;
        }
        try {
            if (target != null && target instanceof EntityPlayer) {
                final ScaledResolution scaledResolution = new ScaledResolution(TargetHUD.mc);
                Render2DUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 17.5, scaledResolution.getScaledHeight() / 2 + 42.5, (target.getName().length() > 15) ? ((double)(TargetHUD.fontRenderer.getStringWidth(target.getName()) + 48)) : 115.0, 45.0, new Color(31, 31, 31).hashCode());
                Render2DUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 20, scaledResolution.getScaledHeight() / 2 + 45, (target.getName().length() > 15) ? (TargetHUD.fontRenderer.getStringWidth(target.getName()) + 43) : 110, 40, new Color(18, 18, 18).hashCode());
                TargetHUD.mc.getTextureManager().bindTexture(TargetHUD.mc.getConnection().getPlayerInfo(target.getName()).getLocationSkin());
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                Gui.drawScaledCustomSizeModalRect(scaledResolution.getScaledWidth() / 2 + 25, scaledResolution.getScaledHeight() / 2 + 50, 8.0f, 8.0f, 8, 8, 25, 25, 64.0f, 64.0f);
                Render2DUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 53, scaledResolution.getScaledHeight() / 2 + 59, 70, 2, new Color(27, 27, 27).hashCode());
                Color health = Color.GREEN;
                if (target.getHealth() >= 16.0f) {
                    health = Color.GREEN;
                }
                else if (target.getHealth() >= 8.0f && target.getHealth() <= 16.0f) {
                    health = Color.YELLOW;
                }
                else if (target.getHealth() > 0.0f && target.getHealth() <= 8.0f) {
                    health = Color.RED;
                }
                Render2DUtil.drawRect(scaledResolution.getScaledWidth() / 2 + 53, scaledResolution.getScaledHeight() / 2 + 59, target.getHealth() / target.getMaxHealth() * 70.0f, 2.0, health.hashCode());
                TargetHUD.fontRenderer1.drawString("Health: " + (int)target.getHealth() + " | Range: " + (int)TargetHUD.mc.player.getDistance((Entity)target), (float)(scaledResolution.getScaledWidth() / 2 + 53), (float)(scaledResolution.getScaledHeight() / 2 + 65), -1);
                TargetHUD.fontRenderer.drawString(target.getName(), (float)(scaledResolution.getScaledWidth() / 2 + 53), (float)(scaledResolution.getScaledHeight() / 2 + 52), -1);
                int posX = scaledResolution.getScaledWidth() / 2 + 53;
                for (final ItemStack item : target.getArmorInventoryList()) {
                    GL11.glPushMatrix();
                    GL11.glTranslated((double)posX, (double)(scaledResolution.getScaledHeight() / 2 + 69), 0.0);
                    GL11.glScaled(0.8, 0.8, 0.8);
                    TargetHUD.mc.getRenderItem().renderItemIntoGUI(item, 0, 0);
                    GL11.glPopMatrix();
                    posX += 12;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        fontRenderer = new CustomFontRenderer(new Font("Arial", 1, 15), true, true);
        fontRenderer1 = new CustomFontRenderer(new Font("Arial", 0, 11), true, true);
    }
}
