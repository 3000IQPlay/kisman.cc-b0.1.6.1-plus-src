//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import com.kisman.cc.util.pyro.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.function.*;
import net.minecraftforge.common.*;
import com.kisman.cc.*;
import net.minecraft.client.entity.*;
import java.util.*;
import net.minecraft.util.text.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import org.apache.commons.lang3.*;

@SideOnly(Side.CLIENT)
public class RotationUtils
{
    private static Minecraft mc;
    private Random random;
    private int keepLength;
    public Rotation targetRotation;
    public Rotation serverRotation;
    public boolean keepCurrentRotation;
    private double x;
    private double y;
    private double z;
    final Packet<?>[] packet;
    final CPacketPlayer[] packetPlayer;
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    @SubscribeEvent
    public void update(final TickEvent.ClientTickEvent event) {
        if (this.targetRotation != null) {
            --this.keepLength;
            if (this.keepLength <= 0) {
                this.reset();
            }
        }
        if (this.random.nextGaussian() > 0.8) {
            this.x = Math.random();
        }
        if (this.random.nextGaussian() > 0.8) {
            this.y = Math.random();
        }
        if (this.random.nextGaussian() > 0.8) {
            this.z = Math.random();
        }
    }
    
    public RotationUtils() {
        this.packet = (Packet<?>[])new Packet[1];
        this.packetPlayer = new CPacketPlayer[1];
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            this.packet[0] = (Packet<?>)event.getPacket();
            if (this.packet[0] instanceof CPacketPlayer) {
                this.packetPlayer[0] = (CPacketPlayer)this.packet[0];
                if (this.packetPlayer[0].rotating) {
                    this.serverRotation = new Rotation(this.packetPlayer[0].yaw, this.packetPlayer[0].pitch);
                }
            }
        }, new Predicate[0]);
        this.random = new Random();
        this.serverRotation = new Rotation(0.0f, 0.0f);
        this.keepCurrentRotation = false;
        this.x = this.random.nextDouble();
        this.y = this.random.nextDouble();
        this.z = this.random.nextDouble();
        final Packet<?>[] packet = (Packet<?>[])new Packet[] { null };
        final CPacketPlayer[] packetPlayer = { null };
        MinecraftForge.EVENT_BUS.register((Object)this);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public static double yawDist(final BlockPos pos) {
        if (pos != null) {
            final Vec3d difference = new Vec3d((Vec3i)pos).subtract(RotationUtils.mc.player.getPositionEyes(RotationUtils.mc.getRenderPartialTicks()));
            final double d = Math.abs(RotationUtils.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(difference.z, difference.x)) - 90.0)) % 360.0;
            return (d > 180.0) ? (360.0 - d) : d;
        }
        return 0.0;
    }
    
    public static double yawDist(final Entity e) {
        if (e != null) {
            final Vec3d difference = e.getPositionVector().add(new Vec3d(0.0, (double)(e.getEyeHeight() / 2.0f), 0.0)).subtract(RotationUtils.mc.player.getPositionEyes(RotationUtils.mc.getRenderPartialTicks()));
            final double d = Math.abs(RotationUtils.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(difference.z, difference.x)) - 90.0)) % 360.0;
            return (d > 180.0) ? (360.0 - d) : d;
        }
        return 0.0;
    }
    
    public static boolean isInFov(final BlockPos pos) {
        return pos != null && (RotationUtils.mc.player.getDistanceSq(pos) < 4.0 || yawDist(pos) < getHalvedfov() + 2.0f);
    }
    
    public static boolean isInFov(final Entity entity) {
        return entity != null && (RotationUtils.mc.player.getDistanceSq(entity) < 4.0 || yawDist(entity) < getHalvedfov() + 2.0f);
    }
    
    public static float getFov() {
        return RotationUtils.mc.gameSettings.fovSetting;
    }
    
    public static float getHalvedfov() {
        return getFov() / 2.0f;
    }
    
    public static boolean isInFov(final Vec3d vec3d, final Vec3d other) {
        Label_0071: {
            if (RotationUtils.mc.player.rotationPitch > 30.0f) {
                if (other.y <= RotationUtils.mc.player.posY) {
                    break Label_0071;
                }
            }
            else if (RotationUtils.mc.player.rotationPitch >= -30.0f || other.y >= RotationUtils.mc.player.posY) {
                break Label_0071;
            }
            return true;
        }
        final float angle = calcAngleNoY(vec3d, other)[0] - transformYaw();
        if (angle < -270.0f) {
            return true;
        }
        final float fov = RotationUtils.mc.gameSettings.fovSetting / 2.0f;
        return angle < fov + 10.0f && angle > -fov - 10.0f;
    }
    
    public static float[] calcAngleNoY(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difZ = to.z - from.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0) };
    }
    
    public static Vec2f getRotationTo(final AxisAlignedBB box) {
        final EntityPlayerSP player = RotationUtils.mc.player;
        if (player == null) {
            return Vec2f.ZERO;
        }
        final Vec3d eyePos = player.getPositionEyes(1.0f);
        if (player.getEntityBoundingBox().intersects(box)) {
            return getRotationTo(eyePos, box.getCenter());
        }
        final double x = MathHelper.clamp(eyePos.x, box.minX, box.maxX);
        final double y = MathHelper.clamp(eyePos.y, box.minY, box.maxY);
        final double z = MathHelper.clamp(eyePos.z, box.minZ, box.maxZ);
        return getRotationTo(eyePos, new Vec3d(x, y, z));
    }
    
    public static Vec2f getRotationTo(final Vec3d posTo) {
        final EntityPlayerSP player = RotationUtils.mc.player;
        return (player != null) ? getRotationTo(player.getPositionEyes(1.0f), posTo) : Vec2f.ZERO;
    }
    
    public static void lookAtVec3d(final Vec3d vec3d) {
        final float[] angle = AngleUtil.calculateAngle(RotationUtils.mc.player.getPositionEyes(RotationUtils.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        setPlayerRotations(angle[0], angle[1]);
    }
    
    public static void setPlayerRotations(final com.kisman.cc.util.Rotation rotation) {
        setPlayerRotations(rotation.getYaw(), rotation.getPitch());
    }
    
    public static void setPlayerRotations(final float yaw, final float pitch) {
        RotationUtils.mc.player.rotationYaw = yaw;
        RotationUtils.mc.player.rotationYawHead = yaw;
        RotationUtils.mc.player.rotationPitch = pitch;
    }
    
    public static Vec2f getRotationTo(final Vec3d posFrom, final Vec3d posTo) {
        return getRotationFromVec(posTo.subtract(posFrom));
    }
    
    public static Vec2f getRotationFromVec(final Vec3d vec) {
        final double lengthXZ = Math.hypot(vec.x, vec.z);
        final double yaw = normalizeAngle(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
        final double pitch = normalizeAngle(Math.toDegrees(-Math.atan2(vec.y, lengthXZ)));
        return new Vec2f((float)yaw, (float)pitch);
    }
    
    public static double normalizeAngle(double angle) {
        angle %= 360.0;
        if (angle >= 180.0) {
            angle -= 360.0;
        }
        if (angle < -180.0) {
            angle += 360.0;
        }
        return angle;
    }
    
    public static float normalizeAngle(float angle) {
        angle %= 360.0f;
        if (angle >= 180.0f) {
            angle -= 360.0f;
        }
        if (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }
    
    public static float transformYaw() {
        float yaw = RotationUtils.mc.player.rotationYaw % 360.0f;
        if (RotationUtils.mc.player.rotationYaw > 0.0f) {
            if (yaw > 180.0f) {
                yaw = -180.0f + (yaw - 180.0f);
            }
        }
        else if (yaw < -180.0f) {
            yaw = 180.0f + (yaw + 180.0f);
        }
        if (yaw < 0.0f) {
            return 180.0f + yaw;
        }
        return -180.0f + yaw;
    }
    
    public static float[] getAverageRotations(final List list) {
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        for (final Object entityw : list) {
            final Entity entity = (Entity)entityw;
            d += entity.posX;
            d2 += entity.getEntityBoundingBox().maxY - 2.0;
            d3 += entity.posZ;
        }
        final float[] array = new float[2];
        final int n = 0;
        d /= list.size();
        d3 /= list.size();
        array[0] = getRotationFromPosition(d, d3, d2 /= list.size())[0];
        array[1] = getRotationFromPosition(d, d3, d2)[1];
        return array;
    }
    
    public static float[] getRotation(final Entity entity) {
        final Vec3d eyesPos = new Vec3d(RotationUtils.mc.player.posX, RotationUtils.mc.player.posY + RotationUtils.mc.player.getEyeHeight(), RotationUtils.mc.player.posZ);
        final double X = entity.getPositionVector().x - eyesPos.x + Math.random() / 4.0;
        final double Y = entity.getPositionVector().y + entity.getEyeHeight() - eyesPos.y + Math.random() / 4.0;
        final double Z = entity.getPositionVector().z - eyesPos.z + Math.random() / 4.0;
        final double XZ = Math.sqrt(X * X + Z * Z);
        float yaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(Z, X)) - 90.0f);
        float pitch = MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(Y, XZ))) + 5.0f);
        final float f = RotationUtils.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 10.0f;
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[] { MathHelper.clamp(yaw, -360.0f, 360.0f), MathHelper.clamp(pitch, -90.0f, 90.0f) };
    }
    
    public static float getDistanceBetweenAngles(final float f, final float f2) {
        float f3 = Math.abs(f - f2) % 360.0f;
        if (f3 > 180.0f) {
            f3 = 360.0f - f3;
        }
        return f3;
    }
    
    public static float getTrajAngleSolutionLow(final float f, final float f2, final float f3) {
        final float f4 = f3 * f3 * f3 * f3 - 0.006f * (0.006f * (f * f) + 2.0f * f2 * (f3 * f3));
        return (float)Math.toDegrees(Math.atan((f3 * f3 - Math.sqrt(f4)) / (0.006f * f)));
    }
    
    public static float[] getRotations(final double x, final double y, final double z) {
        final double diffX = x + 0.5 - RotationUtils.mc.player.posX;
        final double diffY = (y + 0.5) / 2.0 - (RotationUtils.mc.player.posY + RotationUtils.mc.player.getEyeHeight());
        final double diffZ = z + 0.5 - RotationUtils.mc.player.posZ;
        final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793));
        return new float[] { yaw, pitch };
    }
    
    public static float[] getRotationFromPosition(final double d, final double d2, final double d3) {
        final double d4 = d - Minecraft.getMinecraft().player.posX;
        final double d5 = d2 - Minecraft.getMinecraft().player.posZ;
        final double d6 = d3 - Minecraft.getMinecraft().player.posY - 0.6;
        final double d7 = MathHelper.sqrt(d4 * d4 + d5 * d5);
        final float f = (float)(Math.atan2(d5, d4) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / 3.141592653589793));
        return new float[] { f, f2 };
    }
    
    public static int getDirection4D() {
        return MathHelper.floor(RotationUtils.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
    }
    
    public static String getDirection4D(final boolean northRed) {
        final int dirnumber = getDirection4D();
        if (dirnumber == 0) {
            return "South (+Z)";
        }
        if (dirnumber == 1) {
            return "West (-X)";
        }
        if (dirnumber == 2) {
            return (northRed ? TextFormatting.RED : "") + "North (-Z)";
        }
        if (dirnumber == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }
    
    public static float[] getNeededRotations(final Entity entityLivingBase) {
        final double d = entityLivingBase.posX - Minecraft.getMinecraft().player.posX;
        final double d2 = entityLivingBase.posZ - Minecraft.getMinecraft().player.posZ;
        final double d3 = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY));
        final double d4 = MathHelper.sqrt(d * d + d2 * d2);
        final float f = (float)(MathHelper.atan2(d2, d) * 180.0 / 3.141592653589793) - 90.0f;
        final float f2 = (float)(-(MathHelper.atan2(d3, d4) * 180.0 / 3.141592653589793));
        return new float[] { f, f2 };
    }
    
    public static float[] getRotations(final EntityLivingBase entityLivingBase, final String string) {
        if (string == "Head") {
            final double d = entityLivingBase.posX;
            final double d2 = entityLivingBase.posZ;
            final double d3 = entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f;
            return getRotationFromPosition(d, d2, d3);
        }
        if (string == "Chest") {
            final double d = entityLivingBase.posX;
            final double d4 = entityLivingBase.posZ;
            final double d5 = entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 0.75;
            return getRotationFromPosition(d, d4, d5);
        }
        if (string == "Dick") {
            final double d = entityLivingBase.posX;
            final double d6 = entityLivingBase.posZ;
            final double d7 = entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 1.2;
            return getRotationFromPosition(d, d6, d7);
        }
        if (string == "Legs") {
            final double d = entityLivingBase.posX;
            final double d8 = entityLivingBase.posZ;
            final double d9 = entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 1.5;
            return getRotationFromPosition(d, d8, d9);
        }
        final double d = entityLivingBase.posX;
        final double d10 = entityLivingBase.posZ;
        final double d11 = entityLivingBase.posY + entityLivingBase.getEyeHeight() / 2.0f - 0.5;
        return getRotationFromPosition(d, d10, d11);
    }
    
    public static float getNewAngle(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }
    
    public VecRotation faceBlock(final BlockPos blockPos) {
        if (blockPos == null) {
            return null;
        }
        VecRotation vecRotation = null;
        for (double xSearch = 0.1; xSearch < 0.9; xSearch += 0.1) {
            for (double ySearch = 0.1; ySearch < 0.9; ySearch += 0.1) {
                for (double zSearch = 0.1; zSearch < 0.9; zSearch += 0.1) {
                    final Vec3d eyesPos = new Vec3d(RotationUtils.mc.player.posX, RotationUtils.mc.player.getEntityBoundingBox().minY + RotationUtils.mc.player.getEyeHeight(), RotationUtils.mc.player.posZ);
                    final Vec3d posVec = new Vec3d((Vec3i)blockPos).add(new Vec3d(xSearch, ySearch, zSearch));
                    final double dist = eyesPos.distanceTo(posVec);
                    final double diffX = posVec.x - eyesPos.x;
                    final double diffY = posVec.y - eyesPos.y;
                    final double diffZ = posVec.z - eyesPos.z;
                    final double diffXZ = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
                    final Rotation rotation = new Rotation(MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f), MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)))));
                    final Vec3d rotationVector = this.getVectorForRotation(rotation);
                    final Vec3d vector = eyesPos.add(new Vec3d(rotationVector.x * dist, rotationVector.y * dist, rotationVector.z * dist));
                    final RayTraceResult obj = RotationUtils.mc.world.rayTraceBlocks(eyesPos, vector, false, false, true);
                    if (obj.typeOfHit == RayTraceResult.Type.BLOCK) {
                        final VecRotation currentVec = new VecRotation(posVec, rotation, obj.sideHit);
                        if (vecRotation == null || this.getRotationDifference(currentVec.getRotation()) < this.getRotationDifference(vecRotation.getRotation())) {
                            vecRotation = currentVec;
                        }
                    }
                }
            }
        }
        return vecRotation;
    }
    
    public double getRotationDifference(final Entity entity) {
        final Rotation rotation = this.toRotation(this.getCenter(entity.getEntityBoundingBox()), true);
        return this.getRotationDifference(rotation, new Rotation(RotationUtils.mc.player.rotationYaw, RotationUtils.mc.player.rotationPitch));
    }
    
    public double getRotationDifference(final Rotation rotation) {
        return (this.serverRotation == null) ? 0.0 : this.getRotationDifference(rotation, this.serverRotation);
    }
    
    public double getRotationDifference(final Rotation a, final Rotation b) {
        return Math.hypot(this.getAngleDifference(a.getYaw(), b.getYaw()), a.getPitch() - b.getPitch());
    }
    
    public Rotation toRotation(final Vec3d vec, final boolean predict) {
        final Vec3d eyesPos = new Vec3d(RotationUtils.mc.player.posX, RotationUtils.mc.player.getEntityBoundingBox().minY + RotationUtils.mc.player.getEyeHeight(), RotationUtils.mc.player.posZ);
        if (predict) {
            eyesPos.add(new Vec3d(RotationUtils.mc.player.motionX, RotationUtils.mc.player.motionY, RotationUtils.mc.player.motionZ));
        }
        final double diffX = vec.x - eyesPos.x;
        final double diffY = vec.y - eyesPos.y;
        final double diffZ = vec.z - eyesPos.z;
        return new Rotation(MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f), MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ))))));
    }
    
    public Vec3d getCenter(final AxisAlignedBB bb) {
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5, bb.minY + (bb.maxY - bb.minY) * 0.5, bb.minZ + (bb.maxZ - bb.minZ) * 0.5);
    }
    
    public Vec3d getVectorForRotation(final Rotation rotation) {
        final float yawCos = MathHelper.cos(-rotation.getYaw() * 0.017453292f - 3.1415927f);
        final float yawSin = MathHelper.sin(-rotation.getYaw() * 0.017453292f - 3.1415927f);
        final float pitchCos = -MathHelper.cos(-rotation.getPitch() * 0.017453292f);
        final float pitchSin = MathHelper.sin(-rotation.getPitch() * 0.017453292f);
        return new Vec3d((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
    }
    
    private float getAngleDifference(final float a, final float b) {
        return ((a - b) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }
    
    public Rotation limitAngleChange(final Rotation currentRotation, final Rotation targetRotation, final float turnSpeed) {
        final float yawDifference = this.getAngleDifference(targetRotation.getYaw(), currentRotation.getYaw());
        final float pitchDifference = this.getAngleDifference(targetRotation.getPitch(), currentRotation.getPitch());
        return new Rotation(currentRotation.getYaw() + ((yawDifference > turnSpeed) ? turnSpeed : Math.max(yawDifference, -turnSpeed)), currentRotation.getPitch() + ((pitchDifference > turnSpeed) ? turnSpeed : Math.max(pitchDifference, -turnSpeed)));
    }
    
    public VecRotation searchCenter(final AxisAlignedBB bb, final boolean outborder, final boolean random, final boolean predict, final boolean throughWalls) {
        if (outborder) {
            final Vec3d Vec3d = new Vec3d(bb.minX + (bb.maxX - bb.minX) * (this.x * 0.3 + 1.0), bb.minY + (bb.maxY - bb.minY) * (this.y * 0.3 + 1.0), bb.minZ + (bb.maxZ - bb.minZ) * (this.z * 0.3 + 1.0));
            return new VecRotation(Vec3d, this.toRotation(Vec3d, predict));
        }
        final Vec3d randomVec = new Vec3d(bb.minX + (bb.maxX - bb.minX) * this.x * 0.8, bb.minY + (bb.maxY - bb.minY) * this.y * 0.8, bb.minZ + (bb.maxZ - bb.minZ) * this.z * 0.8);
        final Rotation randomRotation = this.toRotation(randomVec, predict);
        VecRotation vecRotation = null;
        for (double xSearch = 0.15; xSearch < 0.85; xSearch += 0.1) {
            for (double ySearch = 0.15; ySearch < 1.0; ySearch += 0.1) {
                for (double zSearch = 0.15; zSearch < 0.85; zSearch += 0.1) {
                    final Vec3d Vec3d2 = new Vec3d(bb.minX + (bb.maxX - bb.minX) * xSearch, bb.minY + (bb.maxY - bb.minY) * ySearch, bb.minZ + (bb.maxZ - bb.minZ) * zSearch);
                    final Rotation rotation = this.toRotation(Vec3d2, predict);
                    if (throughWalls || this.isVisible(Vec3d2)) {
                        final VecRotation currentVec = new VecRotation(Vec3d2, rotation);
                        if (vecRotation != null) {
                            if (random) {
                                if (this.getRotationDifference(currentVec.getRotation(), randomRotation) >= this.getRotationDifference(vecRotation.getRotation(), randomRotation)) {
                                    continue;
                                }
                            }
                            else if (this.getRotationDifference(currentVec.getRotation()) >= this.getRotationDifference(vecRotation.getRotation())) {
                                continue;
                            }
                        }
                        vecRotation = currentVec;
                    }
                }
            }
        }
        return vecRotation;
    }
    
    public boolean isVisible(final Vec3d Vec3d) {
        final Vec3d eyesPos = new Vec3d(RotationUtils.mc.player.posX, RotationUtils.mc.player.getEntityBoundingBox().minY + RotationUtils.mc.player.getEyeHeight(), RotationUtils.mc.player.posZ);
        return RotationUtils.mc.world.rayTraceBlocks(eyesPos, Vec3d) == null;
    }
    
    public void reset() {
        this.keepLength = 0;
        this.targetRotation = null;
    }
    
    public static float[] getMatrixRotations(final Entity e, final boolean oldPositionUse) {
        final double diffX = (oldPositionUse ? e.prevPosX : e.posX) - (oldPositionUse ? RotationUtils.mc.player.prevPosX : RotationUtils.mc.player.posX);
        final double diffZ = (oldPositionUse ? e.prevPosZ : e.posZ) - (oldPositionUse ? RotationUtils.mc.player.prevPosZ : RotationUtils.mc.player.posZ);
        double diffY;
        if (e instanceof EntityLivingBase) {
            final EntityLivingBase entitylivingbase = (EntityLivingBase)e;
            final float randomed = RandomUtils.nextFloat((float)(entitylivingbase.posY + entitylivingbase.getEyeHeight() / 1.5f), (float)(entitylivingbase.posY + entitylivingbase.getEyeHeight() - entitylivingbase.getEyeHeight() / 3.0f));
            diffY = randomed - (RotationUtils.mc.player.posY + RotationUtils.mc.player.getEyeHeight());
        }
        else {
            diffY = RandomUtils.nextFloat((float)e.getEntityBoundingBox().minY, (float)e.getEntityBoundingBox().maxY) - (RotationUtils.mc.player.posY + RotationUtils.mc.player.getEyeHeight());
        }
        final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793 - 90.0) + RandomUtils.nextFloat(-2.0f, 2.0f);
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793)) + RandomUtils.nextFloat(-2.0f, 2.0f);
        yaw = RotationUtils.mc.player.rotationYaw + GCDUtil.getFixedRotation(MathHelper.wrapDegrees(yaw - RotationUtils.mc.player.rotationYaw));
        pitch = RotationUtils.mc.player.rotationPitch + GCDUtil.getFixedRotation(MathHelper.wrapDegrees(pitch - RotationUtils.mc.player.rotationPitch));
        pitch = MathHelper.clamp(pitch, -90.0f, 90.0f);
        return new float[] { yaw, pitch };
    }
    
    static {
        RotationUtils.mc = Minecraft.getMinecraft();
    }
}
