//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.util.*;
import java.util.concurrent.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;

public class SpawnsESP extends Module
{
    private Setting crystals;
    private Setting players;
    private Setting mobs;
    private Setting boats;
    private Setting duration;
    private Setting width;
    public CopyOnWriteArrayList<VecCircle> circles;
    public ConcurrentHashMap<BlockPos, Long> blocks;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public SpawnsESP() {
        super("SpawnsESP", "        super(\"SpawnsESP\", )\n", Category.RENDER);
        this.crystals = new Setting("Crystals", this, true);
        this.players = new Setting("Players", this, false);
        this.mobs = new Setting("Mobs", this, false);
        this.boats = new Setting("Boats", this, false);
        this.duration = new Setting("Duration", this, 1.0, 0.10000000149011612, 5.0, false);
        this.width = new Setting("Widht", this, 2.5, 0.1, 10.0, false);
        this.circles = new CopyOnWriteArrayList<VecCircle>();
        this.blocks = new ConcurrentHashMap<BlockPos, Long>();
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketSpawnObject) {
                if (((SPacketSpawnObject)event.getPacket()).getType() == 51 && this.crystals.getValBoolean()) {
                    this.circles.add(new VecCircle(new Vec3d(((SPacketSpawnObject)event.getPacket()).getX(), ((SPacketSpawnObject)event.getPacket()).getY(), ((SPacketSpawnObject)event.getPacket()).getZ()), 1.5f, 0.5f));
                }
                else if (((SPacketSpawnObject)event.getPacket()).getType() == 1 && this.boats.getValBoolean()) {
                    this.circles.add(new VecCircle(new Vec3d(((SPacketSpawnObject)event.getPacket()).getX(), ((SPacketSpawnObject)event.getPacket()).getY(), ((SPacketSpawnObject)event.getPacket()).getZ()), 1.0f, 0.75f));
                }
            }
            else if (event.getPacket() instanceof SPacketSpawnPlayer && this.players.getValBoolean()) {
                this.circles.add(new VecCircle(new Vec3d(((SPacketSpawnPlayer)event.getPacket()).getX(), ((SPacketSpawnPlayer)event.getPacket()).getY(), ((SPacketSpawnPlayer)event.getPacket()).getZ()), 1.8f, 0.5f));
            }
            else if (event.getPacket() instanceof SPacketSpawnMob && this.mobs.getValBoolean()) {
                this.circles.add(new VecCircle(new Vec3d(((SPacketSpawnMob)event.getPacket()).getX(), ((SPacketSpawnMob)event.getPacket()).getY(), ((SPacketSpawnMob)event.getPacket()).getZ()), 1.8f, 0.5f));
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final VecCircle class442 : this.circles) {
            if ((float)(System.currentTimeMillis() - VecCircle.Method722(class442)) > 1000.0 * this.duration.getValDouble()) {
                this.circles.remove(class442);
            }
            else {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableTexture2D();
                GlStateManager.disableDepth();
                GlStateManager.disableLighting();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                final float[] fArray = Color.RGBtoHSB(1, 1, 1, null);
                float f = System.currentTimeMillis() % 7200L / 7200.0f;
                int n2 = Color.getHSBColor(f, fArray[1], fArray[2]).getRGB();
                final ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
                final double d = VecCircle.Method719(class442).x - SpawnsESP.mc.getRenderManager().renderPosX;
                final double d2 = VecCircle.Method719(class442).y - SpawnsESP.mc.getRenderManager().renderPosY;
                final double d3 = VecCircle.Method719(class442).z - SpawnsESP.mc.getRenderManager().renderPosZ;
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glLineWidth((float)this.width.getValDouble());
                GL11.glEnable(2848);
                GL11.glHint(3154, 4354);
                GL11.glBegin(1);
                for (int n3 = 0; n3 <= 360; ++n3) {
                    final Vec3d vec3d = new Vec3d(d + Math.sin(n3 * 3.141592653589793 / 180.0) * VecCircle.Method720(class442), d2 + VecCircle.Method721(class442) * ((float)(System.currentTimeMillis() - VecCircle.Method722(class442)) / (1000.0 * this.duration.getValDouble())), d3 + Math.cos(n3 * 3.141592653589793 / 180.0) * VecCircle.Method720(class442));
                    arrayList.add(vec3d);
                }
                for (int n3 = 0; n3 < arrayList.size() - 1; ++n3) {
                    final int n4 = n2 >> 24 & 0xFF;
                    final int n5 = n2 >> 16 & 0xFF;
                    final int n6 = n2 >> 8 & 0xFF;
                    final int n7 = n2 & 0xFF;
                    GL11.glColor4f(n5 / 255.0f, n6 / 255.0f, n7 / 255.0f, n4 / 255.0f);
                    GL11.glVertex3d(arrayList.get(n3).x, arrayList.get(n3).y, arrayList.get(n3).z);
                    GL11.glVertex3d(arrayList.get(n3 + 1).x, arrayList.get(n3 + 1).y, arrayList.get(n3 + 1).z);
                    n2 = Color.getHSBColor(f += 0.0027777778f, fArray[1], fArray[2]).getRGB();
                }
                GL11.glEnd();
                GL11.glDisable(2848);
                GlStateManager.enableLighting();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }
}
