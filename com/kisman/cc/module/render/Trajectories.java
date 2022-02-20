//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.concurrent.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import com.kisman.cc.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.block.state.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import java.util.*;

public class Trajectories extends Module
{
    private Setting width;
    private final Queue<Vec3d> flightPoint;
    
    public Trajectories() {
        super("Trajectories", "no salhack pasta!!!", Category.RENDER);
        this.width = new Setting("Width", this, 1.0, 0.0, 5.0, false);
        this.flightPoint = new ConcurrentLinkedQueue<Vec3d>();
        Trajectories.setmgr.rSetting(this.width);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        final ThrowableType throwingType = this.getTypeFromCurrentItem(Trajectories.mc.player);
        if (throwingType == ThrowableType.NONE) {
            return;
        }
        final FlightPath flightPath = new FlightPath(Trajectories.mc.player, throwingType);
        while (!flightPath.isCollided()) {
            flightPath.onUpdate();
            this.flightPoint.offer(new Vec3d(flightPath.position.x - Trajectories.mc.getRenderManager().viewerPosX, flightPath.position.y - Trajectories.mc.getRenderManager().viewerPosY, flightPath.position.z - Trajectories.mc.getRenderManager().viewerPosZ));
        }
        final boolean bobbing = Trajectories.mc.gameSettings.viewBobbing;
        Trajectories.mc.gameSettings.viewBobbing = false;
        Trajectories.mc.entityRenderer.setupCameraTransform(event.getPartialTicks(), 0);
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth((float)this.width.getValDouble());
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GlStateManager.disableDepth();
        GL11.glEnable(34383);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        while (!this.flightPoint.isEmpty()) {
            bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            final Vec3d head = this.flightPoint.poll();
            bufferbuilder.pos(head.x, head.y, head.z).color(255, 255, 255, 150).endVertex();
            if (this.flightPoint.peek() != null) {
                final Vec3d point = this.flightPoint.peek();
                bufferbuilder.pos(point.x, point.y, point.z).color(255, 255, 255, 150).endVertex();
            }
            tessellator.draw();
        }
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.enableDepth();
        GL11.glDisable(34383);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
        Trajectories.mc.gameSettings.viewBobbing = bobbing;
        Trajectories.mc.entityRenderer.setupCameraTransform(event.getPartialTicks(), 0);
        if (flightPath.collided) {
            final RayTraceResult hit = flightPath.target;
            AxisAlignedBB bb = null;
            if (hit == null) {
                return;
            }
            if (hit.typeOfHit == RayTraceResult.Type.BLOCK) {
                final BlockPos blockpos = hit.getBlockPos();
                final IBlockState iblockstate = Trajectories.mc.world.getBlockState(blockpos);
                if (iblockstate.getMaterial() != Material.AIR && Trajectories.mc.world.getWorldBorder().contains(blockpos)) {
                    final Vec3d interp = MathUtil.interpolateEntity((Entity)Trajectories.mc.player, Trajectories.mc.getRenderPartialTicks());
                    bb = iblockstate.getSelectedBoundingBox((World)Trajectories.mc.world, blockpos).grow(0.0020000000949949026).offset(-interp.x, -interp.y, -interp.z);
                }
            }
            else if (hit.typeOfHit == RayTraceResult.Type.ENTITY && hit.entityHit != null) {
                final AxisAlignedBB entityBB = hit.entityHit.getEntityBoundingBox();
                if (entityBB != null) {
                    bb = new AxisAlignedBB(entityBB.minX - Trajectories.mc.getRenderManager().viewerPosX, entityBB.minY - Trajectories.mc.getRenderManager().viewerPosY, entityBB.minZ - Trajectories.mc.getRenderManager().viewerPosZ, entityBB.maxX - Trajectories.mc.getRenderManager().viewerPosX, entityBB.maxY - Trajectories.mc.getRenderManager().viewerPosY, entityBB.maxZ - Trajectories.mc.getRenderManager().viewerPosZ);
                }
            }
            if (bb != null) {
                RenderUtil.drawBoundingBox(bb, this.width.getValDouble(), 1.0f, 1.0f, 1.0f, 150);
            }
        }
    }
    
    private ThrowableType getTypeFromCurrentItem(final EntityPlayerSP player) {
        if (player.getHeldItemMainhand() == null) {
            return ThrowableType.NONE;
        }
        final ItemStack itemStack = player.getHeldItem(EnumHand.MAIN_HAND);
        switch (Item.getIdFromItem(itemStack.getItem())) {
            case 261: {
                if (player.isHandActive()) {
                    return ThrowableType.ARROW;
                }
                break;
            }
            case 346: {
                return ThrowableType.FISHING_ROD;
            }
            case 438:
            case 441: {
                return ThrowableType.POTION;
            }
            case 384: {
                return ThrowableType.EXPERIENCE;
            }
            case 332:
            case 344:
            case 368: {
                return ThrowableType.NORMAL;
            }
        }
        return ThrowableType.NONE;
    }
    
    public enum ThrowableType
    {
        NONE(0.0f, 0.0f), 
        ARROW(1.5f, 0.05f), 
        POTION(0.5f, 0.05f), 
        EXPERIENCE(0.7f, 0.07f), 
        FISHING_ROD(1.5f, 0.04f), 
        NORMAL(1.5f, 0.03f);
        
        private final float velocity;
        private final float gravity;
        
        private ThrowableType(final float velocity, final float gravity) {
            this.velocity = velocity;
            this.gravity = gravity;
        }
        
        public float getVelocity() {
            return this.velocity;
        }
        
        public float getGravity() {
            return this.gravity;
        }
    }
    
    public class FlightPath
    {
        private EntityPlayerSP shooter;
        private Vec3d position;
        private Vec3d motion;
        private float yaw;
        private float pitch;
        private AxisAlignedBB boundingBox;
        private boolean collided;
        private RayTraceResult target;
        private ThrowableType throwableType;
        
        public FlightPath(final EntityPlayerSP player, final ThrowableType throwableType) {
            this.shooter = player;
            this.throwableType = throwableType;
            this.setLocationAndAngles(this.shooter.posX, this.shooter.posY + this.shooter.getEyeHeight(), this.shooter.posZ, this.shooter.rotationYaw, this.shooter.rotationPitch);
            final Vec3d startingOffset = new Vec3d((double)(MathHelper.cos(this.yaw / 180.0f * 3.1415927f) * 0.16f), 0.1, (double)(MathHelper.sin(this.yaw / 180.0f * 3.1415927f) * 0.16f));
            this.setPosition(this.position = this.position.subtract(startingOffset));
            this.setThrowableHeading(this.motion = new Vec3d((double)(-MathHelper.sin(this.yaw / 180.0f * 3.1415927f) * MathHelper.cos(this.pitch / 180.0f * 3.1415927f)), (double)(-MathHelper.sin(this.pitch / 180.0f * 3.1415927f)), (double)(MathHelper.cos(this.yaw / 180.0f * 3.1415927f) * MathHelper.cos(this.pitch / 180.0f * 3.1415927f))), this.getInitialVelocity());
        }
        
        public void onUpdate() {
            Vec3d prediction = this.position.add(this.motion);
            final RayTraceResult blockCollision = this.shooter.getEntityWorld().rayTraceBlocks(this.position, prediction, this.throwableType == ThrowableType.FISHING_ROD, !this.collidesWithNoBoundingBox(), false);
            if (blockCollision != null) {
                prediction = blockCollision.hitVec;
            }
            this.onCollideWithEntity(prediction, blockCollision);
            if (this.target != null) {
                this.collided = true;
                this.setPosition(this.target.hitVec);
                return;
            }
            if (this.position.y <= 0.0) {
                this.collided = true;
                return;
            }
            this.position = this.position.add(this.motion);
            float motionModifier = 0.99f;
            if (this.shooter.getEntityWorld().isMaterialInBB(this.boundingBox, Material.WATER)) {
                motionModifier = ((this.throwableType == ThrowableType.ARROW) ? 0.6f : 0.8f);
            }
            if (this.throwableType == ThrowableType.FISHING_ROD) {
                motionModifier = 0.92f;
            }
            this.motion = MathUtil.mult(this.motion, motionModifier);
            this.motion = this.motion.subtract(0.0, (double)this.getGravityVelocity(), 0.0);
            this.setPosition(this.position);
        }
        
        private boolean collidesWithNoBoundingBox() {
            switch (this.throwableType) {
                case FISHING_ROD:
                case NORMAL: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        
        private void onCollideWithEntity(final Vec3d prediction, final RayTraceResult blockCollision) {
            Entity collidingEntity = null;
            RayTraceResult collidingPosition = null;
            double currentDistance = 0.0;
            final List<Entity> collisionEntities = (List<Entity>)Minecraft.getMinecraft().world.getEntitiesWithinAABBExcludingEntity((Entity)this.shooter, this.boundingBox.expand(this.motion.x, this.motion.y, this.motion.z).grow(1.0, 1.0, 1.0));
            for (final Entity entity : collisionEntities) {
                if (!entity.canBeCollidedWith()) {
                    continue;
                }
                final float collisionSize = entity.getCollisionBorderSize();
                final AxisAlignedBB expandedBox = entity.getEntityBoundingBox().expand((double)collisionSize, (double)collisionSize, (double)collisionSize);
                final RayTraceResult objectPosition = expandedBox.calculateIntercept(this.position, prediction);
                if (objectPosition == null) {
                    continue;
                }
                final double distanceTo = this.position.distanceTo(objectPosition.hitVec);
                if (distanceTo >= currentDistance && currentDistance != 0.0) {
                    continue;
                }
                collidingEntity = entity;
                collidingPosition = objectPosition;
                currentDistance = distanceTo;
            }
            if (collidingEntity != null) {
                this.target = new RayTraceResult(collidingEntity, collidingPosition.hitVec);
            }
            else {
                this.target = blockCollision;
            }
        }
        
        private float getInitialVelocity() {
            switch (this.throwableType) {
                case ARROW: {
                    final int useDuration = this.shooter.getHeldItem(EnumHand.MAIN_HAND).getItem().getMaxItemUseDuration(this.shooter.getHeldItem(EnumHand.MAIN_HAND)) - this.shooter.getItemInUseCount();
                    float velocity = useDuration / 20.0f;
                    velocity = (velocity * velocity + velocity * 2.0f) / 3.0f;
                    if (velocity > 1.0f) {
                        velocity = 1.0f;
                    }
                    return velocity * 2.0f * this.throwableType.getVelocity();
                }
                default: {
                    return this.throwableType.getVelocity();
                }
            }
        }
        
        private float getGravityVelocity() {
            return this.throwableType.getGravity();
        }
        
        private void setLocationAndAngles(final double x, final double y, final double z, final float yaw, final float pitch) {
            this.position = new Vec3d(x, y, z);
            this.yaw = yaw;
            this.pitch = pitch;
        }
        
        private void setPosition(final Vec3d position) {
            this.position = new Vec3d(position.x, position.y, position.z);
            final double entitySize = ((this.throwableType == ThrowableType.ARROW) ? 0.5 : 0.25) / 2.0;
            this.boundingBox = new AxisAlignedBB(position.x - entitySize, position.y - entitySize, position.z - entitySize, position.x + entitySize, position.y + entitySize, position.z + entitySize);
        }
        
        private void setThrowableHeading(final Vec3d motion, final float velocity) {
            this.motion = MathUtil.div(motion, (float)motion.length());
            this.motion = MathUtil.mult(this.motion, velocity);
        }
        
        public boolean isCollided() {
            return this.collided;
        }
        
        public RayTraceResult getCollidingTarget() {
            return this.target;
        }
    }
}
