//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import java.awt.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import net.minecraft.entity.*;
import java.math.*;
import java.util.*;
import net.minecraft.util.math.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class MathUtil
{
    private static final Random random;
    
    public static double degToRad(final double deg) {
        return deg * 0.01745329238474369;
    }
    
    public static Vec3d direction(final float yaw) {
        return new Vec3d(Math.cos(degToRad(yaw + 90.0f)), 0.0, Math.sin(degToRad(yaw + 90.0f)));
    }
    
    public static Point calculateMouseLocation() {
        final Minecraft mc = Minecraft.getMinecraft();
        int scale = mc.gameSettings.guiScale;
        if (scale == 0) {
            scale = 1000;
        }
        int scaleFactor;
        for (scaleFactor = 0; scaleFactor < scale && mc.displayWidth / (scaleFactor + 1) >= 320 && mc.displayHeight / (scaleFactor + 1) >= 240; ++scaleFactor) {}
        return new Point(Mouse.getX() / scaleFactor, mc.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1);
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float time) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
    }
    
    public static double getInputFromPercent(final double value, final double minInput, final double maxInput) {
        return value * (maxInput - minInput) / 100.0 + minInput;
    }
    
    public static Vec3d mult(final Vec3d factor, final Vec3d multiplier) {
        return new Vec3d(factor.x * multiplier.x, factor.y * multiplier.y, factor.z * multiplier.z);
    }
    
    public static Vec3d mult(final Vec3d factor, final float multiplier) {
        return new Vec3d(factor.x * multiplier, factor.y * multiplier, factor.z * multiplier);
    }
    
    public static Vec3d div(final Vec3d factor, final Vec3d divisor) {
        return new Vec3d(factor.x / divisor.x, factor.y / divisor.y, factor.z / divisor.z);
    }
    
    public static Vec3d div(final Vec3d factor, final float divisor) {
        return new Vec3d(factor.x / divisor, factor.y / divisor, factor.z / divisor);
    }
    
    public static double[] directionSpeedNoForward(final double speed) {
        final Minecraft mc = Minecraft.getMinecraft();
        float forward = 1.0f;
        if (mc.gameSettings.keyBindLeft.isPressed() || mc.gameSettings.keyBindRight.isPressed() || mc.gameSettings.keyBindBack.isPressed() || mc.gameSettings.keyBindForward.isPressed()) {
            forward = mc.player.movementInput.moveForward;
        }
        float side = mc.player.movementInput.moveStrafe;
        float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    public static double roundDouble(final double number, final int scale) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static float roundFloat(final double number, final int scale) {
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(scale, RoundingMode.FLOOR);
        return bd.floatValue();
    }
    
    public static int getRandom(final int min, final int max) {
        return min + MathUtil.random.nextInt(max - min + 1);
    }
    
    public static double getRandom(final double min, final double max) {
        return MathHelper.clamp(min + MathUtil.random.nextDouble() * max, min, max);
    }
    
    public static float getRandom(final float min, final float max) {
        return MathHelper.clamp(min + MathUtil.random.nextFloat() * max, min, max);
    }
    
    public static int clamp(final int num, final int min, final int max) {
        return (num < min) ? min : Math.min(num, max);
    }
    
    public static float clamp(final float num, final float min, final float max) {
        return (num < min) ? min : Math.min(num, max);
    }
    
    public static double clamp(final double num, final double min, final double max) {
        return (num < min) ? min : Math.min(num, max);
    }
    
    public static float sin(final float value) {
        return MathHelper.sin(value);
    }
    
    public static float cos(final float value) {
        return MathHelper.cos(value);
    }
    
    public static float wrapDegrees(final float value) {
        return MathHelper.wrapDegrees(value);
    }
    
    public static double wrapDegrees(final double value) {
        return MathHelper.wrapDegrees(value);
    }
    
    public static Vec3d roundVec(final Vec3d vec3d, final int places) {
        return new Vec3d(round(vec3d.x, places), round(vec3d.y, places), round(vec3d.z, places));
    }
    
    public static double square(final double input) {
        return input * input;
    }
    
    public static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.doubleValue();
    }
    
    public static float wrap(final float valI) {
        float val = valI % 360.0f;
        if (val >= 180.0f) {
            val -= 360.0f;
        }
        if (val < -180.0f) {
            val += 360.0f;
        }
        return val;
    }
    
    public static float round(final float value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.floatValue();
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map, final boolean descending) {
        final LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if (descending) {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue(Comparator.reverseOrder()));
        }
        else {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue());
        }
        final LinkedHashMap result = new LinkedHashMap();
        for (final Map.Entry entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return (Map<K, V>)result;
    }
    
    public static String getTimeOfDay() {
        final Calendar c = Calendar.getInstance();
        final int timeOfDay = c.get(11);
        if (timeOfDay < 12) {
            return "Good Morning ";
        }
        if (timeOfDay < 16) {
            return "Good Afternoon ";
        }
        if (timeOfDay < 21) {
            return "Good Evening ";
        }
        return "Good Night ";
    }
    
    public static double radToDeg(final double rad) {
        return rad * 57.295780181884766;
    }
    
    public static double getIncremental(final double val, final double inc) {
        final double one = 1.0 / inc;
        return Math.round(val * one) / one;
    }
    
    public static double[] directionSpeed(final double speed) {
        final Minecraft mc = Minecraft.getMinecraft();
        float forward = mc.player.movementInput.moveForward;
        float side = mc.player.movementInput.moveStrafe;
        float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    public static List<Vec3d> getBlockBlocks(final Entity entity) {
        final ArrayList<Vec3d> vec3ds = new ArrayList<Vec3d>();
        final AxisAlignedBB bb = entity.getEntityBoundingBox();
        final double y = entity.posY;
        final double minX = round(bb.minX, 0);
        final double minZ = round(bb.minZ, 0);
        final double maxX = round(bb.maxX, 0);
        final double maxZ = round(bb.maxZ, 0);
        if (minX != maxX) {
            vec3ds.add(new Vec3d(minX, y, minZ));
            vec3ds.add(new Vec3d(maxX, y, minZ));
            if (minZ != maxZ) {
                vec3ds.add(new Vec3d(minX, y, maxZ));
                vec3ds.add(new Vec3d(maxX, y, maxZ));
                return vec3ds;
            }
        }
        else if (minZ != maxZ) {
            vec3ds.add(new Vec3d(minX, y, minZ));
            vec3ds.add(new Vec3d(minX, y, maxZ));
            return vec3ds;
        }
        vec3ds.add(entity.getPositionVector());
        return vec3ds;
    }
    
    public static boolean areVec3dsAligned(final Vec3d vec3d1, final Vec3d vec3d2) {
        return areVec3dsAlignedRetarded(vec3d1, vec3d2);
    }
    
    public static boolean areVec3dsAlignedRetarded(final Vec3d vec3d1, final Vec3d vec3d2) {
        final BlockPos pos1 = new BlockPos(vec3d1);
        final BlockPos pos2 = new BlockPos(vec3d2.x, vec3d1.y, vec3d2.z);
        return pos1.equals((Object)pos2);
    }
    
    public static double distance(final float x, final float y, final float x1, final float y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }
    
    public static double cot(final double x) {
        return 1.0 / Math.tan(x);
    }
    
    public static Vec2i getEndPortalCoords(final int x1, final int z1, final float a1, final int x2, final int z2, final float a2, final boolean debug) {
        final Vec2i coord = new Vec2i();
        final double p = 0.017453292519943295;
        Label_0530: {
            if (Math.abs(a1 - a2) < 1.0f) {
                if (debug) {
                    ChatUtils.error((Object)"The angles cannot be equal");
                    return null;
                }
            }
            else if (((a1 < 0.0f && a2 > 0.0f) || (a1 > 0.0f && a2 < 0.0f)) && Math.abs(Math.abs(Math.abs(a1) - 180.0f) - Math.abs(a2)) < 1.0f) {
                if (debug) {
                    ChatUtils.error((Object)"The angles cannot be opposite");
                    return null;
                }
            }
            else {
                switch (Math.round(a1)) {
                    case -180:
                    case 0:
                    case 180: {
                        coord.x = Math.round((float)x1);
                        coord.y = (int)Math.round(cot(-a2 * p) * x1 - (x2 * cot(-a2 * p) - z2));
                        break;
                    }
                    case -90:
                    case 90: {
                        coord.x = Math.round((float)z1);
                        coord.y = (int)Math.round(Math.round(x2 * cot(-a2 * p) - z2 + z1) / cot(-a2 * p));
                        break;
                    }
                    default: {
                        switch (Math.round(a2)) {
                            case -180:
                            case 0:
                            case 180: {
                                coord.x = Math.round((float)x2);
                                coord.y = (int)Math.round(cot(-a1 * p) * x2 - (x1 * cot(-a1 * p) - z1));
                                break Label_0530;
                            }
                            case -90:
                            case 90: {
                                coord.x = Math.round((float)z2);
                                coord.y = (int)Math.round(Math.round(x1 * cot(-a1 * p) - z1 + z2) / cot(-a1 * p));
                                break Label_0530;
                            }
                            default: {
                                coord.x = (int)Math.round((x1 * cot(-a1 * p) - z1 - (x2 * cot(-a2 * p) - z2)) / (cot(-a1 * p) - cot(-a2 * p)));
                                coord.y = (int)Math.round(cot(-a1 * p) * coord.x - (x1 * cot(-a1 * p) - z1));
                                break Label_0530;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return coord;
    }
    
    static {
        random = new Random();
    }
}
