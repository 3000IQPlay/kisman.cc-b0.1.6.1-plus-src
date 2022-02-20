//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.combat.*;
import java.util.stream.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;

public class PlayerUtil
{
    private static final Minecraft mc;
    
    public static void swingArm(final Hand hand) {
        switch (hand) {
            case MAINHAND: {
                PlayerUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                break;
            }
            case OFFHAND: {
                PlayerUtil.mc.player.swingArm(EnumHand.OFF_HAND);
                break;
            }
            case PACKET: {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(PlayerUtil.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
                break;
            }
        }
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static List<EntityPlayer> getPlayersInRadius(final double range) {
        return getPlayersInRadius(PlayerUtil.mc.player.getPositionVector(), range);
    }
    
    public static List<EntityPlayer> getPlayersInRadius(final Vec3d center, final double range) {
        return getEntitiesInRadius(EntityPlayer.class, center, range);
    }
    
    public static <T extends Entity> List<T> getEntitiesInRadius(final Class<T> entityClass, final Vec3d center, final double range) {
        final List<T> entity = new ArrayList<T>();
        for (final Entity entity2 : PlayerUtil.mc.world.loadedEntityList) {
            if (entity2.getDistance((Entity)PlayerUtil.mc.player) <= range) {
                entity.add((T)entity2);
            }
        }
        return entity;
    }
    
    public static List<EntityPlayer> getPlayerTargets(final double withinDistance) {
        final List<EntityPlayer> targets = new ArrayList<EntityPlayer>();
        targets.addAll(getPlayersInRadius(withinDistance).stream().filter(player -> AutoCrystalBypass.instance.isValidTarget(player)).collect((Collector<? super Object, ?, Collection<? extends EntityPlayer>>)Collectors.toList()));
        targets.sort((Comparator<? super EntityPlayer>)Comparators.entityDistance);
        return targets;
    }
    
    public static BlockPos[][] surroundBlockPos() {
        if (PlayerUtil.mc.player == null && PlayerUtil.mc.world == null) {
            return null;
        }
        return new BlockPos[][] { { null, new BlockPos(Math.floor(PlayerUtil.mc.player.posX) + 1.0, Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ)), null }, { new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ) + 1.0), null, new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ) - 1.0) }, { null, new BlockPos(Math.floor(PlayerUtil.mc.player.posX) - 1.0, Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ)), null } };
    }
    
    public static EntityPlayer findClosestTarget(double rangeMax, final EntityPlayer aimTarget) {
        rangeMax *= rangeMax;
        final List<EntityPlayer> playerList = (List<EntityPlayer>)PlayerUtil.mc.world.playerEntities;
        EntityPlayer closestTarget = null;
        for (final EntityPlayer entityPlayer : playerList) {
            if (EntityUtil.basicChecksEntity((Entity)entityPlayer)) {
                continue;
            }
            if (aimTarget == null && PlayerUtil.mc.player.getDistanceSq((Entity)entityPlayer) <= rangeMax) {
                closestTarget = entityPlayer;
            }
            else {
                if (aimTarget == null || PlayerUtil.mc.player.getDistanceSq((Entity)entityPlayer) > rangeMax || PlayerUtil.mc.player.getDistanceSq((Entity)entityPlayer) >= PlayerUtil.mc.player.getDistanceSq((Entity)aimTarget)) {
                    continue;
                }
                closestTarget = entityPlayer;
            }
        }
        return closestTarget;
    }
    
    public static EntityPlayer findClosestTarget() {
        final List<EntityPlayer> playerList = (List<EntityPlayer>)PlayerUtil.mc.world.playerEntities;
        EntityPlayer closestTarget = null;
        for (final EntityPlayer entityPlayer : playerList) {
            if (EntityUtil.basicChecksEntity((Entity)entityPlayer)) {
                continue;
            }
            if (closestTarget == null) {
                closestTarget = entityPlayer;
            }
            else {
                if (PlayerUtil.mc.player.getDistanceSq((Entity)entityPlayer) >= PlayerUtil.mc.player.getDistanceSq((Entity)closestTarget)) {
                    continue;
                }
                closestTarget = entityPlayer;
            }
        }
        return closestTarget;
    }
    
    public static double getEyeY(final EntityPlayer player) {
        return player.getPositionVector().y + player.getEyeHeight();
    }
    
    public static EntityPlayer findLookingPlayer(final double rangeMax) {
        final ArrayList<EntityPlayer> listPlayer = new ArrayList<EntityPlayer>();
        for (final EntityPlayer playerSin : PlayerUtil.mc.world.playerEntities) {
            if (EntityUtil.basicChecksEntity((Entity)playerSin)) {
                continue;
            }
            if (PlayerUtil.mc.player.getDistance((Entity)playerSin) > rangeMax) {
                continue;
            }
            listPlayer.add(playerSin);
        }
        EntityPlayer target = null;
        final Vec3d positionEyes = PlayerUtil.mc.player.getPositionEyes(PlayerUtil.mc.getRenderPartialTicks());
        final Vec3d rotationEyes = PlayerUtil.mc.player.getLook(PlayerUtil.mc.getRenderPartialTicks());
        final int precision = 2;
        for (int i = 0; i < (int)rangeMax; ++i) {
            for (int j = precision; j > 0; --j) {
                for (final EntityPlayer targetTemp : listPlayer) {
                    final AxisAlignedBB playerBox = targetTemp.getEntityBoundingBox();
                    final double xArray = positionEyes.x + rotationEyes.x * i + rotationEyes.x / j;
                    final double yArray = positionEyes.y + rotationEyes.y * i + rotationEyes.y / j;
                    final double zArray = positionEyes.z + rotationEyes.z * i + rotationEyes.z / j;
                    if (playerBox.maxY >= yArray && playerBox.minY <= yArray && playerBox.maxX >= xArray && playerBox.minX <= xArray && playerBox.maxZ >= zArray && playerBox.minZ <= zArray) {
                        target = targetTemp;
                    }
                }
            }
        }
        return target;
    }
    
    public static float getHealth() {
        return PlayerUtil.mc.player.getHealth() + PlayerUtil.mc.player.getAbsorptionAmount();
    }
    
    public static void centerPlayer(Vec3d centeredBlock) {
        final double xDeviation = Math.abs(centeredBlock.x - PlayerUtil.mc.player.posX);
        final double zDeviation = Math.abs(centeredBlock.z - PlayerUtil.mc.player.posZ);
        if (xDeviation <= 0.1 && zDeviation <= 0.1) {
            centeredBlock = Vec3d.ZERO;
        }
        else {
            double newX = -2.0;
            double newZ = -2.0;
            final int xRel = (PlayerUtil.mc.player.posX < 0.0) ? -1 : 1;
            final int zRel = (PlayerUtil.mc.player.posZ < 0.0) ? -1 : 1;
            if (BlockUtil.getBlock(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY - 1.0, PlayerUtil.mc.player.posZ) instanceof BlockAir) {
                if (Math.abs(PlayerUtil.mc.player.posX % 1.0) * 100.0 <= 30.0) {
                    newX = Math.round(PlayerUtil.mc.player.posX - 0.3 * xRel) + 0.5 * -xRel;
                }
                else if (Math.abs(PlayerUtil.mc.player.posX % 1.0) * 100.0 >= 70.0) {
                    newX = Math.round(PlayerUtil.mc.player.posX + 0.3 * xRel) - 0.5 * -xRel;
                }
                if (Math.abs(PlayerUtil.mc.player.posZ % 1.0) * 100.0 <= 30.0) {
                    newZ = Math.round(PlayerUtil.mc.player.posZ - 0.3 * zRel) + 0.5 * -zRel;
                }
                else if (Math.abs(PlayerUtil.mc.player.posZ % 1.0) * 100.0 >= 70.0) {
                    newZ = Math.round(PlayerUtil.mc.player.posZ + 0.3 * zRel) - 0.5 * -zRel;
                }
            }
            if (newX == -2.0) {
                if (PlayerUtil.mc.player.posX > Math.round(PlayerUtil.mc.player.posX)) {
                    newX = Math.round(PlayerUtil.mc.player.posX) + 0.5;
                }
                else if (PlayerUtil.mc.player.posX < Math.round(PlayerUtil.mc.player.posX)) {
                    newX = Math.round(PlayerUtil.mc.player.posX) - 0.5;
                }
                else {
                    newX = PlayerUtil.mc.player.posX;
                }
            }
            if (newZ == -2.0) {
                if (PlayerUtil.mc.player.posZ > Math.round(PlayerUtil.mc.player.posZ)) {
                    newZ = Math.round(PlayerUtil.mc.player.posZ) + 0.5;
                }
                else if (PlayerUtil.mc.player.posZ < Math.round(PlayerUtil.mc.player.posZ)) {
                    newZ = Math.round(PlayerUtil.mc.player.posZ) - 0.5;
                }
                else {
                    newZ = PlayerUtil.mc.player.posZ;
                }
            }
            PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(newX, PlayerUtil.mc.player.posY, newZ, true));
            PlayerUtil.mc.player.setPosition(newX, PlayerUtil.mc.player.posY, newZ);
        }
    }
    
    public static double[] forward(final double speed) {
        float forward = PlayerUtil.mc.player.movementInput.moveForward;
        float side = PlayerUtil.mc.player.movementInput.moveStrafe;
        float yaw = PlayerUtil.mc.player.prevRotationYaw + (PlayerUtil.mc.player.rotationYaw - PlayerUtil.mc.player.prevRotationYaw) * PlayerUtil.mc.getRenderPartialTicks();
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
    
    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873;
        if (PlayerUtil.mc.player != null && PlayerUtil.mc.player.isPotionActive(Potion.getPotionById(1))) {
            final int amplifier = PlayerUtil.mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }
    
    public static boolean isMoving(final EntityLivingBase entity) {
        return entity.moveForward != 0.0f || entity.moveStrafing != 0.0f;
    }
    
    public static void setSpeed(final EntityLivingBase entity, final double speed) {
        final double[] dir = forward(speed);
        entity.motionX = dir[0];
        entity.motionZ = dir[1];
    }
    
    public static boolean IsEating() {
        return PlayerUtil.mc.player != null && PlayerUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood && PlayerUtil.mc.player.isHandActive();
    }
    
    public static boolean isCurrentViewEntity() {
        return PlayerUtil.mc.getRenderViewEntity() == PlayerUtil.mc.player;
    }
    
    public static boolean CanSeeBlock(final BlockPos p_Pos) {
        return PlayerUtil.mc.player != null && PlayerUtil.mc.world.rayTraceBlocks(new Vec3d(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY + PlayerUtil.mc.player.getEyeHeight(), PlayerUtil.mc.player.posZ), new Vec3d((double)p_Pos.getX(), (double)p_Pos.getY(), (double)p_Pos.getZ()), false, true, false) == null;
    }
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static BlockPos entityPosToFloorBlockPos(final Entity e) {
        return new BlockPos(Math.floor(e.posX), Math.floor(e.posY), Math.floor(e.posZ));
    }
    
    public static int GetItemSlot(final Item input) {
        if (PlayerUtil.mc.player == null) {
            return 0;
        }
        for (int i = 0; i < PlayerUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            if (i != 0 && i != 5 && i != 6 && i != 7) {
                if (i != 8) {
                    final ItemStack s = (ItemStack)PlayerUtil.mc.player.inventoryContainer.getInventory().get(i);
                    if (!s.isEmpty()) {
                        if (s.getItem() == input) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public static int GetRecursiveItemSlot(final Item input) {
        if (PlayerUtil.mc.player == null) {
            return 0;
        }
        for (int i = PlayerUtil.mc.player.inventoryContainer.getInventory().size() - 1; i > 0; --i) {
            if (i != 0 && i != 5 && i != 6 && i != 7) {
                if (i != 8) {
                    final ItemStack s = (ItemStack)PlayerUtil.mc.player.inventoryContainer.getInventory().get(i);
                    if (!s.isEmpty()) {
                        if (s.getItem() == input) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public static void packetFacePitchAndYaw(final float p_Pitch, final float p_Yaw) {
        final boolean l_IsSprinting = PlayerUtil.mc.player.isSprinting();
        if (l_IsSprinting != PlayerUtil.mc.player.serverSprintState) {
            if (l_IsSprinting) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PlayerUtil.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            else {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PlayerUtil.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            PlayerUtil.mc.player.serverSprintState = l_IsSprinting;
        }
        final boolean l_IsSneaking = PlayerUtil.mc.player.isSneaking();
        if (l_IsSneaking != PlayerUtil.mc.player.serverSneakState) {
            if (l_IsSneaking) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PlayerUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            else {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PlayerUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            PlayerUtil.mc.player.serverSneakState = l_IsSneaking;
        }
        if (isCurrentViewEntity()) {
            final float l_Pitch = p_Pitch;
            final float l_Yaw = p_Yaw;
            final AxisAlignedBB axisalignedbb = PlayerUtil.mc.player.getEntityBoundingBox();
            final double l_PosXDifference = PlayerUtil.mc.player.posX - PlayerUtil.mc.player.lastReportedPosX;
            final double l_PosYDifference = axisalignedbb.minY - PlayerUtil.mc.player.lastReportedPosY;
            final double l_PosZDifference = PlayerUtil.mc.player.posZ - PlayerUtil.mc.player.lastReportedPosZ;
            final double l_YawDifference = l_Yaw - PlayerUtil.mc.player.lastReportedYaw;
            final double l_RotationDifference = l_Pitch - PlayerUtil.mc.player.lastReportedPitch;
            final EntityPlayerSP player = PlayerUtil.mc.player;
            ++player.positionUpdateTicks;
            boolean l_MovedXYZ = l_PosXDifference * l_PosXDifference + l_PosYDifference * l_PosYDifference + l_PosZDifference * l_PosZDifference > 9.0E-4 || PlayerUtil.mc.player.positionUpdateTicks >= 20;
            final boolean l_MovedRotation = l_YawDifference != 0.0 || l_RotationDifference != 0.0;
            if (PlayerUtil.mc.player.isRiding()) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PlayerUtil.mc.player.motionX, -999.0, PlayerUtil.mc.player.motionZ, l_Yaw, l_Pitch, PlayerUtil.mc.player.onGround));
                l_MovedXYZ = false;
            }
            else if (l_MovedXYZ && l_MovedRotation) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PlayerUtil.mc.player.posX, axisalignedbb.minY, PlayerUtil.mc.player.posZ, l_Yaw, l_Pitch, PlayerUtil.mc.player.onGround));
            }
            else if (l_MovedXYZ) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PlayerUtil.mc.player.posX, axisalignedbb.minY, PlayerUtil.mc.player.posZ, PlayerUtil.mc.player.onGround));
            }
            else if (l_MovedRotation) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(l_Yaw, l_Pitch, PlayerUtil.mc.player.onGround));
            }
            else if (PlayerUtil.mc.player.prevOnGround != PlayerUtil.mc.player.onGround) {
                PlayerUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer(PlayerUtil.mc.player.onGround));
            }
            if (l_MovedXYZ) {
                PlayerUtil.mc.player.lastReportedPosX = PlayerUtil.mc.player.posX;
                PlayerUtil.mc.player.lastReportedPosY = axisalignedbb.minY;
                PlayerUtil.mc.player.lastReportedPosZ = PlayerUtil.mc.player.posZ;
                PlayerUtil.mc.player.positionUpdateTicks = 0;
            }
            if (l_MovedRotation) {
                PlayerUtil.mc.player.lastReportedYaw = l_Yaw;
                PlayerUtil.mc.player.lastReportedPitch = l_Pitch;
            }
            PlayerUtil.mc.player.prevOnGround = PlayerUtil.mc.player.onGround;
            PlayerUtil.mc.player.autoJumpEnabled = PlayerUtil.mc.player.mc.gameSettings.autoJump;
        }
    }
    
    public static boolean isPlayerTrapped() {
        final BlockPos playerPos = GetLocalPlayerPosFloored();
        final BlockPos[] array;
        final BlockPos[] trapPos = array = new BlockPos[] { playerPos.down(), playerPos.up().up(), playerPos.north(), playerPos.south(), playerPos.east(), playerPos.west(), playerPos.north().up(), playerPos.south().up(), playerPos.east().up(), playerPos.west().up() };
        for (final BlockPos pos : array) {
            final IBlockState state = PlayerUtil.mc.world.getBlockState(pos);
            if (state.getBlock() != Blocks.OBSIDIAN && PlayerUtil.mc.world.getBlockState(pos).getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isEntityTrapped(final Entity e) {
        final BlockPos playerPos = entityPosToFloorBlockPos(e);
        final BlockPos[] array;
        final BlockPos[] l_TrapPositions = array = new BlockPos[] { playerPos.up().up(), playerPos.north(), playerPos.south(), playerPos.east(), playerPos.west(), playerPos.north().up(), playerPos.south().up(), playerPos.east().up(), playerPos.west().up() };
        for (final BlockPos l_Pos : array) {
            final IBlockState l_State = PlayerUtil.mc.world.getBlockState(l_Pos);
            if (l_State.getBlock() != Blocks.OBSIDIAN && PlayerUtil.mc.world.getBlockState(l_Pos).getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public enum Hand
    {
        MAINHAND, 
        OFFHAND, 
        PACKET, 
        NONE;
    }
}
