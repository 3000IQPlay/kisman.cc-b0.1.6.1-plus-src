//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import com.kisman.cc.util.*;
import java.util.*;
import net.minecraft.util.math.*;

public class BowAimBot extends Module
{
    private Setting maxDist;
    
    public BowAimBot() {
        super("BowAimBot", "", Category.COMBAT);
        this.maxDist = new Setting("Max Distance", this, 20.0, 1.0, 50.0, true);
        BowAimBot.setmgr.rSetting(this.maxDist);
    }
    
    @Override
    public void update() {
        if (BowAimBot.mc.player == null && BowAimBot.mc.world == null) {
            return;
        }
        if (BowAimBot.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow && BowAimBot.mc.player.isHandActive() && BowAimBot.mc.player.getItemInUseMaxCount() >= 3) {
            EntityPlayer player = null;
            float tickDis = 100.0f;
            for (final EntityPlayer p : BowAimBot.mc.world.playerEntities) {
                if (p == BowAimBot.mc.player) {
                    continue;
                }
                if (p.getDistance((Entity)BowAimBot.mc.player) > this.maxDist.getValInt()) {
                    continue;
                }
                final float dis = p.getDistance((Entity)BowAimBot.mc.player);
                if (dis >= tickDis) {
                    continue;
                }
                tickDis = dis;
                player = p;
            }
            if (player != null) {
                final Vec3d pos = EntityUtil.getInterpolatedPos((Entity)player, BowAimBot.mc.getRenderPartialTicks());
                final float[] angels = AngleUtil.calculateAngle(EntityUtil.getInterpolatedPos((Entity)BowAimBot.mc.player, BowAimBot.mc.getRenderPartialTicks()), pos);
                BowAimBot.mc.player.rotationYaw = angels[0];
                BowAimBot.mc.player.rotationPitch = angels[1];
            }
        }
    }
}
