//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.utils;

import net.minecraft.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import i.gishreloaded.gishcode.wrappers.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import i.gishreloaded.gishcode.utils.system.*;
import net.minecraftforge.fml.relauncher.*;
import java.lang.reflect.*;

public class PlayerControllerUtils
{
    public static void setReach(final Entity entity, final double range) {
        final Minecraft mc = Wrapper.INSTANCE.mc();
        final EntityPlayer player = (EntityPlayer)Wrapper.INSTANCE.player();
        if (player == entity) {
            class 1RangePlayerController extends PlayerControllerMP
            {
                private float range;
                
                public 1RangePlayerController(final Minecraft mcIn, final NetHandlerPlayClient netHandler) {
                    super(mcIn, netHandler);
                    this.range = (float)Wrapper.INSTANCE.player().getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
                }
                
                public float getBlockReachDistance() {
                    return this.range;
                }
                
                public void setBlockReachDistance(final float range) {
                    this.range = range;
                }
            }
            if (!(mc.playerController instanceof 1RangePlayerController)) {
                final GameType gameType = (GameType)ReflectionHelper.getPrivateValue((Class)PlayerControllerMP.class, (Object)mc.playerController, new String[] { Mapping.currentGameType });
                final NetHandlerPlayClient netClient = (NetHandlerPlayClient)ReflectionHelper.getPrivateValue((Class)PlayerControllerMP.class, (Object)mc.playerController, new String[] { Mapping.connection });
                final 1RangePlayerController controller = new 1RangePlayerController(mc, netClient);
                final boolean isFlying = player.capabilities.isFlying;
                final boolean allowFlying = player.capabilities.allowFlying;
                controller.setGameType(gameType);
                player.capabilities.isFlying = isFlying;
                player.capabilities.allowFlying = allowFlying;
                mc.playerController = controller;
            }
            ((1RangePlayerController)mc.playerController).setBlockReachDistance((float)range);
        }
    }
    
    public static void setIsHittingBlock(final boolean isHittingBlock) {
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.isHittingBlock);
            field.setAccessible(true);
            field.setBoolean(Wrapper.INSTANCE.controller(), isHittingBlock);
        }
        catch (Exception ex) {}
    }
    
    public static void setBlockHitDelay(final int blockHitDelay) {
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.blockHitDelay);
            field.setAccessible(true);
            field.setInt(Wrapper.INSTANCE.controller(), blockHitDelay);
        }
        catch (Exception ex) {}
    }
    
    public static float getCurBlockDamageMP() {
        float getCurBlockDamageMP = 0.0f;
        try {
            final Field field = PlayerControllerMP.class.getDeclaredField(Mapping.curBlockDamageMP);
            field.setAccessible(true);
            getCurBlockDamageMP = field.getFloat(Wrapper.INSTANCE.controller());
        }
        catch (Exception ex) {}
        return getCurBlockDamageMP;
    }
}
