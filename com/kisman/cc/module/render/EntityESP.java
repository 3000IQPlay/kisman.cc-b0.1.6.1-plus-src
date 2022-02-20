//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import java.nio.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.awt.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.util.shaders.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import java.util.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import com.sun.javafx.geom.*;
import com.kisman.cc.util.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.*;

public class EntityESP extends Module
{
    private Setting range;
    private Setting playerMode;
    private Setting playerColor;
    private Setting playerShader;
    private Setting glowPlayer;
    private Setting quality;
    private Setting radius;
    private Setting monstersColor;
    private Setting itemsColor;
    private Setting entityColor;
    private Setting twodimetsionespline;
    private Setting box;
    private Setting tags;
    private Setting health;
    public final ArrayList<Entity> collectedEntities;
    private final IntBuffer viewport;
    private final FloatBuffer modelview;
    private final FloatBuffer projection;
    private final FloatBuffer vector;
    private final int backgroundColor;
    private final int black;
    @EventHandler
    private final Listener<EventRender2D> listener1;
    @EventHandler
    private final Listener<EventRenderEntityName> listener;
    
    public EntityESP() {
        super("EntityESP", "esp 1", Category.RENDER);
        this.range = new Setting("Range", this, 50.0, 0.0, 100.0, true);
        this.playerMode = new Setting("Players", this, "None", new ArrayList<String>(Arrays.asList("None", "Box1", "Box2", "2D", "2D #2", "Glow")));
        this.playerColor = new Setting("PlayerColor", this, "PlayerColor", new float[] { 0.54f, 0.11f, 0.92f, 1.0f });
        this.playerShader = new Setting("PlayerShader", this, false);
        this.glowPlayer = new Setting("GlowPlayer", this, false);
        this.quality = new Setting("Quality", this, 1.0, 0.0, 5.0, false);
        this.radius = new Setting("Radius", this, 2.0, 0.0, 5.0, false);
        this.monstersColor = new Setting("MonstersColor", this, "MonsterColor", new float[] { 0.11f, 0.92f, 0.73f, 1.0f });
        this.itemsColor = new Setting("ItemsColor", this, "ItemsColor", new float[] { 0.11f, 0.51f, 0.92f, 1.0f });
        this.entityColor = new Setting("EntityColor", this, "EntityColor", new float[] { 0.92f, 0.57f, 0.11f, 1.0f });
        this.twodimetsionespline = new Setting("2DESP", this, "2D ESP setting");
        this.box = new Setting("Box", this, true);
        this.tags = new Setting("Tags", this, false);
        this.health = new Setting("Health", this, true);
        this.collectedEntities = new ArrayList<Entity>();
        this.viewport = GLAllocation.createDirectIntBuffer(16);
        this.modelview = GLAllocation.createDirectFloatBuffer(16);
        this.projection = GLAllocation.createDirectFloatBuffer(16);
        this.vector = GLAllocation.createDirectFloatBuffer(4);
        this.backgroundColor = new Color(0, 0, 0, 120).getRGB();
        this.black = Color.BLACK.getRGB();
        this.listener1 = (Listener<EventRender2D>)new Listener(event -> {
            if (EntityESP.mc.player == null && EntityESP.mc.player == null && !this.playerMode.getValString().equalsIgnoreCase("2D #2")) {
                return;
            }
            final float partialTicks = event.getPartialTicks();
            GL11.glPushMatrix();
            this.collectEntities();
            final ScaledResolution sr = event.getResolution();
            final int scaleFactor = sr.getScaleFactor();
            final double scaling = scaleFactor / Math.pow(scaleFactor, 1.0);
            GL11.glScaled(scaling, scaling, scaling);
            final int black = this.black;
            final int background = this.backgroundColor;
            final float scale = 0.5f;
            final float upscale = 1.0f / scale;
            final RenderManager renderMng = EntityESP.mc.getRenderManager();
            final EntityRenderer entityRenderer = EntityESP.mc.entityRenderer;
            final ArrayList<Entity> collectedEntities = this.collectedEntities;
            final int i = 0;
            for (final Entity entity : collectedEntities) {
                if (this.isValid(entity) && RenderUtil.isInViewFrustrum(entity)) {
                    final double x = RenderUtil.interpolate(entity.posX, entity.lastTickPosX, partialTicks);
                    final double y = RenderUtil.interpolate(entity.posY, entity.lastTickPosY, partialTicks);
                    final double z = RenderUtil.interpolate(entity.posZ, entity.lastTickPosZ, partialTicks);
                    final double width = entity.width / 1.5;
                    final double var10000 = entity.height;
                    double var10001;
                    if (!entity.isSneaking() && (entity != EntityESP.mc.player || !EntityESP.mc.player.isSneaking())) {
                        var10001 = 0.1;
                    }
                    else {
                        var10001 = -0.0;
                    }
                    final double height = var10000 + var10001;
                    final AxisAlignedBB aabb = new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width);
                    final Vec3d[] vectors = { new Vec3d(aabb.minX, aabb.minY, aabb.minZ), new Vec3d(aabb.minX, aabb.maxY, aabb.minZ), new Vec3d(aabb.maxX, aabb.minY, aabb.minZ), new Vec3d(aabb.maxX, aabb.maxY, aabb.minZ), new Vec3d(aabb.minX, aabb.minY, aabb.maxZ), new Vec3d(aabb.minX, aabb.maxY, aabb.maxZ), new Vec3d(aabb.maxX, aabb.minY, aabb.maxZ), new Vec3d(aabb.maxX, aabb.maxY, aabb.maxZ) };
                    entityRenderer.setupCameraTransform(upscale, collectedEntities.indexOf(entity));
                    Vec4d position = null;
                    for (Vec3d vector : vectors) {
                        vector = this.project2D(scaleFactor, vector.x - renderMng.viewerPosX, vector.y - renderMng.viewerPosY, vector.z - renderMng.viewerPosZ);
                        if (vector != null && vector.z >= 0.0 && vector.z < 1.0) {
                            if (position == null) {
                                position = new Vec4d(vector.x, vector.y, vector.z, 0.0);
                            }
                            position.x = Math.min(vector.x, position.x);
                            position.y = Math.min(vector.y, position.y);
                            position.z = Math.max(vector.x, position.z);
                            position.w = Math.max(vector.y, position.w);
                        }
                    }
                    if (position != null) {
                        entityRenderer.setupOverlayRendering();
                        final double posX = position.x;
                        final double posY = position.y;
                        final double endPosX = position.z;
                        final double endPosY = position.w;
                        if (this.box.getValBoolean()) {
                            Render2DUtil.drawRect(posX + 0.5, posY, posX - 1.0, posY + (endPosY - posY) / 4.0 + 0.5, black);
                            Render2DUtil.drawRect(posX - 1.0, endPosY, posX + 0.5, endPosY - (endPosY - posY) / 4.0 - 0.5, black);
                            Render2DUtil.drawRect(posX - 1.0, posY - 0.5, posX + (endPosX - posX) / 3.0 + 0.5, posY + 1.0, black);
                            Render2DUtil.drawRect(endPosX - (endPosX - posX) / 3.0 - 0.5, posY - 0.5, endPosX, posY + 1.0, black);
                            Render2DUtil.drawRect(endPosX - 1.0, posY, endPosX + 0.5, posY + (endPosY - posY) / 4.0 + 0.5, black);
                            Render2DUtil.drawRect(endPosX - 1.0, endPosY, endPosX + 0.5, endPosY - (endPosY - posY) / 4.0 - 0.5, black);
                            Render2DUtil.drawRect(posX - 1.0, endPosY - 1.0, posX + (endPosX - posX) / 3.0 + 0.5, endPosY + 0.5, black);
                            Render2DUtil.drawRect(endPosX - (endPosX - posX) / 3.0 - 0.5, endPosY - 1.0, endPosX + 0.5, endPosY + 0.5, black);
                            Render2DUtil.drawRect(posX, posY, posX - 0.5, posY + (endPosY - posY) / 4.0, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(posX, endPosY, posX - 0.5, endPosY - (endPosY - posY) / 4.0, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(posX - 0.5, posY, posX + (endPosX - posX) / 3.0, posY + 0.5, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(endPosX - (endPosX - posX) / 3.0, posY, endPosX, posY + 0.5, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(endPosX - 0.5, posY, endPosX, posY + (endPosY - posY) / 4.0, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(endPosX - 0.5, endPosY, endPosX, endPosY - (endPosY - posY) / 4.0, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(posX, endPosY - 0.5, posX + (endPosX - posX) / 3.0, endPosY, ColorUtils.astolfoColors(1, 10));
                            Render2DUtil.drawRect(endPosX - (endPosX - posX) / 3.0, endPosY - 0.5, endPosX - 0.5, endPosY, ColorUtils.astolfoColors(1, 10));
                        }
                        final boolean living = entity instanceof EntityLivingBase;
                        if (living && this.health.getValBoolean()) {
                            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
                            final float hp2 = entityLivingBase.getHealth();
                            final float itemDurability = entityLivingBase.getMaxHealth();
                            final double durabilityWidth = hp2 / itemDurability;
                            final double diff1 = (endPosY - posY) * durabilityWidth;
                            Render2DUtil.drawRect(posX - 10.0, posY - 1.5, posX - 3.0, endPosY + 1.5, background);
                            if (hp2 > 0.1f) {
                                final float tagX = entityLivingBase.getAbsorptionAmount();
                                int healthColor2 = (int)hp2;
                                if (hp2 > 8.0f) {
                                    healthColor2 = ColorUtils.astolfoColors(1, 1);
                                }
                                else if (hp2 < 12.0f) {
                                    healthColor2 = new Color(255, 72, 72).getRGB();
                                }
                                Render2DUtil.drawRect(posX - 8.0, endPosY, posX - 5.0, endPosY - diff1, healthColor2);
                            }
                        }
                        if (living && this.tags.getValBoolean() && !NameTags.instance.isToggled()) {
                            final float scaledHeight = 16.0f;
                            String name = entity.getName();
                            if (entity instanceof EntityItem) {
                                name = ((EntityItem)entity).getItem().getDisplayName();
                            }
                            final int maxDamage = ColorUtils.rainbow(1, 10L);
                            final double durabilityWidth = (endPosX - posX) / 2.0;
                            StringBuilder var10003 = new StringBuilder().append(name).append(" §7");
                            final double diff1 = EntityESP.mc.fontRenderer.getStringWidth(var10003.append((int)EntityESP.mc.player.getDistance(entity)).append("32").toString()) * scale;
                            final float tagX = (float)((posX + durabilityWidth - diff1 / 2.0) * upscale);
                            final float tagY = (float)(posY * upscale) - scaledHeight;
                            GL11.glPushMatrix();
                            GL11.glScalef(scale, scale, scale);
                            if (living) {
                                Render2DUtil.drawRect(tagX - 2.0f, tagY - 2.0f, tagX + diff1 * upscale + 2.0, tagY + 9.0f, new Color(0, 0, 0, 140).getRGB());
                            }
                            var10003 = new StringBuilder().append(name).append(" §7");
                            EntityESP.mc.fontRenderer.drawStringWithShadow(var10003.append((int)EntityESP.mc.player.getDistance(entity)).append("m").toString(), (float)(tagX + 0.5), tagY + 0.5f, maxDamage);
                            GL11.glPopMatrix();
                        }
                    }
                }
                GL11.glPopMatrix();
                GL11.glEnable(2929);
                GlStateManager.enableBlend();
                entityRenderer.setupOverlayRendering();
            }
        }, new Predicate[0]);
        this.listener = (Listener<EventRenderEntityName>)new Listener(event -> {
            if (this.playerMode.getValString().equalsIgnoreCase("2D #2") && this.tags.getValBoolean()) {
                event.cancel();
            }
        }, new Predicate[0]);
        EntityESP.setmgr.rSetting(this.range);
        Kisman.instance.settingsManager.rSetting(new Setting("Distance", this, 100.0, 10.0, 260.0, true));
        Kisman.instance.settingsManager.rSetting(new Setting("PlayersLine", this, "Players"));
        EntityESP.setmgr.rSetting(this.playerColor);
        EntityESP.setmgr.rSetting(this.playerMode);
        EntityESP.setmgr.rSetting(this.playerShader);
        EntityESP.setmgr.rSetting(this.glowPlayer);
        EntityESP.setmgr.rSetting(this.quality);
        EntityESP.setmgr.rSetting(this.radius);
        EntityESP.setmgr.rSetting(this.twodimetsionespline);
        EntityESP.setmgr.rSetting(this.box);
        EntityESP.setmgr.rSetting(this.tags);
        EntityESP.setmgr.rSetting(this.health);
        Kisman.instance.settingsManager.rSetting(new Setting("MonstersLine", this, "Monsters"));
        EntityESP.setmgr.rSetting(this.monstersColor);
        Kisman.instance.settingsManager.rSetting(new Setting("Monsters", this, "None", new ArrayList<String>(Arrays.asList("None", "Box1", "Box2", "Glow"))));
        Kisman.instance.settingsManager.rSetting(new Setting("ItemsLine", this, "Items"));
        Kisman.instance.settingsManager.rSetting(new Setting("Items", this, "None", new ArrayList<String>(Arrays.asList("None", "Box1", "Box2", "Glow"))));
        EntityESP.setmgr.rSetting(this.itemsColor);
        Kisman.instance.settingsManager.rSetting(new Setting("EntityLine", this, "Entity"));
        Kisman.instance.settingsManager.rSetting(new Setting("Entity", this, "None", new ArrayList<String>(Arrays.asList("None", "Box1", "Box2", "Glow"))));
        EntityESP.setmgr.rSetting(this.entityColor);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderGameOverlayEvent event) {
        if (EntityESP.mc.player == null && EntityESP.mc.world == null) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            GL11.glPushMatrix();
            if (this.playerShader.getValBoolean()) {
                FlowShader.INSTANCE.startDraw(event.getPartialTicks());
                EntityESP.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer && e != EntityESP.mc.player).forEach(e -> EntityESP.mc.renderManager.renderEntityStatic(e, event.getPartialTicks(), true));
                FlowShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f);
                FlowShader.INSTANCE.stopDraw(new Color(this.playerColor.getR(), this.playerColor.getG(), this.playerColor.getB(), this.playerColor.getA()), (float)this.radius.getValDouble(), (float)this.quality.getValDouble());
            }
            if (this.glowPlayer.getValBoolean()) {
                GlowShader.INSTANCE.startDraw(event.getPartialTicks());
                EntityESP.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer && e != EntityESP.mc.player).forEach(e -> EntityESP.mc.renderManager.renderEntityStatic(e, event.getPartialTicks(), true));
                GlowShader.INSTANCE.stopDraw(new Color(this.playerColor.getR(), this.playerColor.getG(), this.playerColor.getB(), this.playerColor.getA()), (float)this.radius.getValDouble(), (float)this.quality.getValDouble());
            }
            GL11.glPopMatrix();
        }
    }
    
    public void onRenderWorld(final RenderWorldLastEvent e) {
        for (final EntityPlayer p : EntityESP.mc.world.playerEntities) {
            if (p == EntityESP.mc.player) {
                continue;
            }
            GL11.glPushMatrix();
            final double x = p.lastTickPosX + (p.posX - p.lastTickPosX) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosX;
            final double y = p.lastTickPosY + (p.posY - p.lastTickPosY) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosY + 1.0;
            final double z = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosZ;
            GL11.glTranslated(x, y, z);
            GL11.glRotated((double)(-EntityESP.mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
            GL11.glRotated((double)EntityESP.mc.getRenderManager().playerViewX, 1.0, 0.0, 0.0);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0f);
            GL11.glBegin(2);
            GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
            GL11.glVertex2d(0.4, 1.0);
            GL11.glVertex2d(0.4, -1.0);
            GL11.glVertex2d(-0.4, -1.0);
            GL11.glVertex2d(-0.4, 1.0);
            GL11.glEnd();
            GL11.glEnable(2929);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glDisable(2848);
            GL11.glPopMatrix();
        }
    }
    
    private void draw2D(final RenderWorldLastEvent e, final EntityPlayer p) {
        GL11.glPushMatrix();
        final double x = p.lastTickPosX + (p.posX - p.lastTickPosX) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosX;
        final double y = p.lastTickPosY + (p.posY - p.lastTickPosY) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosY + 1.0;
        final double z = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * e.getPartialTicks() - EntityESP.mc.getRenderManager().viewerPosZ;
        GL11.glTranslated(x, y, z);
        GL11.glRotated((double)(-EntityESP.mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
        GL11.glRotated((double)EntityESP.mc.getRenderManager().playerViewX, 1.0, 0.0, 0.0);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(2);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        GL11.glVertex2d(0.4, 1.0);
        GL11.glVertex2d(0.4, -1.0);
        GL11.glVertex2d(-0.4, -1.0);
        GL11.glVertex2d(-0.4, 1.0);
        GL11.glEnd();
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    private boolean isValid(final Entity entity) {
        return (EntityESP.mc.gameSettings.thirdPersonView != 0 || entity != EntityESP.mc.player) && !entity.isDead && !(entity instanceof EntityAnimal) && (entity instanceof EntityPlayer || (!(entity instanceof EntityArmorStand) && !(entity instanceof IAnimals) && !(entity instanceof EntityItemFrame) && (!(entity instanceof EntityArrow) && !(entity instanceof EntitySpectralArrow)) && !(entity instanceof EntityMinecart) && !(entity instanceof EntityBoat) && !(entity instanceof EntityDragonFireball) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityMinecartChest) && !(entity instanceof EntityTNTPrimed) && !(entity instanceof EntityMinecartTNT) && !(entity instanceof EntityVillager) && !(entity instanceof EntityExpBottle) && !(entity instanceof EntityLightningBolt) && !(entity instanceof EntityPotion) && !(entity instanceof Entity) && (!(entity instanceof EntityMob) && !(entity instanceof EntitySlime) && !(entity instanceof EntityDragon) && !(entity instanceof EntityGolem)) && entity != EntityESP.mc.player));
    }
    
    private void collectEntities() {
        this.collectedEntities.clear();
        final int i = 0;
        for (final EntityPlayer player : EntityESP.mc.world.playerEntities) {
            if (this.isValid((Entity)player)) {
                this.collectedEntities.add((Entity)player);
            }
        }
    }
    
    private Vec3d project2D(final int scaleFactor, final double x, final double y, final double z) {
        GL11.glGetFloat(2982, this.modelview);
        GL11.glGetFloat(2983, this.projection);
        GL11.glGetInteger(2978, this.viewport);
        return GLU.gluProject((float)x, (float)y, (float)z, this.modelview, this.projection, this.viewport, this.vector) ? new Vec3d((double)(this.vector.get(0) / scaleFactor), (double)((Display.getHeight() - this.vector.get(1)) / scaleFactor), (double)this.vector.get(2)) : null;
    }
}
