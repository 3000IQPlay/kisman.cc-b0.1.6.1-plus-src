//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.kisman.cc.util.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PortalESP extends Module
{
    private Setting range;
    
    public PortalESP() {
        super("PortalESP", "esp on portal blocks", Category.RENDER);
        this.range = new Setting("Range", this, 50.0, 0.0, 100.0, false);
        PortalESP.setmgr.rSetting(this.range);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        final ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (final BlockPos pos : BlockInteractionHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.range.getValDouble(), this.range.getValInt(), false, true, 0)) {
            if (PortalESP.mc.world.getBlockState(pos).getBlock() == Blocks.PORTAL) {
                blocks.add(pos);
            }
        }
        for (final BlockPos pos : blocks) {
            RenderUtil.drawBlockESP(pos, 0.67f, 0.0f, 1.0f);
        }
    }
}
