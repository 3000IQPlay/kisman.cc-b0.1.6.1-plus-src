//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import com.kisman.cc.util.cosmos.*;
import java.util.concurrent.*;
import net.minecraft.entity.item.*;
import net.minecraft.block.*;
import com.kisman.cc.util.*;
import net.minecraft.network.play.client.*;
import com.kisman.cc.mixin.mixins.accessor.*;
import java.util.*;

public class Surround extends Module
{
    private Setting surroundVec;
    private Setting completion;
    private Setting center;
    private Setting autoSwitch;
    private Setting hand;
    private Setting blocksPerTick;
    private Setting raytrace;
    private Setting packet;
    private Setting confirm;
    private Setting reactive;
    private Setting chainPop;
    private Setting rotate;
    private Setting rotateCenter;
    private Setting rotateRandom;
    private Setting render;
    private Setting renderMode;
    private Setting renderSafeColor;
    private Setting renderUnSafeColor;
    public static Surround instance;
    private int oldSlot;
    private int surroundPlaced;
    private BlockPos oldPos;
    private BlockPos surroundPosition;
    private Rotation surroundRotation;
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    public Surround() {
        super("Surround", "Surround", Category.COMBAT);
        this.surroundVec = new Setting("SurroundVec", this, SurroundVectors.BASE);
        this.completion = new Setting("Completion", this, Completion.AIR);
        this.center = new Setting("Center", this, Center.TELEPORT);
        this.autoSwitch = new Setting("Switch", this, InventoryUtil.Switch.NORMAL);
        this.hand = new Setting("Hand", this, PlayerUtil.Hand.MAINHAND);
        this.blocksPerTick = new Setting("BlocksPerTick", this, 4.0, 0.0, 10.0, true);
        this.raytrace = new Setting("RayTrace", this, false);
        this.packet = new Setting("Packet", this, false);
        this.confirm = new Setting("Confirm", this, false);
        this.reactive = new Setting("Reactive", this, true);
        this.chainPop = new Setting("ChainPop", this, false);
        this.rotate = new Setting("Rotate", this, Rotation.Rotate.NONE);
        this.rotateCenter = new Setting("RotateCenter", this, false);
        this.rotateRandom = new Setting("RotateRandom", this, false);
        this.render = new Setting("Render", this, true);
        this.renderMode = new Setting("RenderMode", this, RenderBuilder.Box.FILL);
        this.renderSafeColor = new Setting("SafeColor", this, "SafeColor", new float[] { 0.08f, 1.0f, 0.0f, 1.0f }, false);
        this.renderUnSafeColor = new Setting("UnSafeColor", this, "UnSafeColor", new float[] { 0.0f, 1.0f, 1.0f, 1.0f }, false);
        this.oldSlot = -1;
        this.surroundPlaced = 0;
        this.oldPos = BlockPos.ORIGIN;
        this.surroundPosition = BlockPos.ORIGIN;
        this.surroundRotation = new Rotation(Float.NaN, Float.NaN, (Rotation.Rotate)this.rotate.getValEnum());
        this.listener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayer && !Float.isNaN(this.surroundRotation.getYaw()) && !Float.isNaN(this.surroundRotation.getPitch())) {
                ((ICPacketPlayer)event.getPacket()).setYaw(this.surroundRotation.getYaw());
                ((ICPacketPlayer)event.getPacket()).setPitch(this.surroundRotation.getPitch());
            }
        }, new Predicate[0]);
        Surround.instance = this;
        Surround.setmgr.rSetting(this.surroundVec);
        Surround.setmgr.rSetting(this.completion);
        Surround.setmgr.rSetting(this.center);
        Surround.setmgr.rSetting(this.autoSwitch);
        Surround.setmgr.rSetting(this.hand);
        Surround.setmgr.rSetting(this.blocksPerTick);
        Surround.setmgr.rSetting(this.raytrace);
        Surround.setmgr.rSetting(this.packet);
        Surround.setmgr.rSetting(this.confirm);
        Surround.setmgr.rSetting(this.reactive);
        Surround.setmgr.rSetting(this.chainPop);
        Surround.setmgr.rSetting(this.rotate);
        Surround.setmgr.rSetting(this.rotateCenter);
        Surround.setmgr.rSetting(this.rotateRandom);
        Surround.setmgr.rSetting(this.render);
        Surround.setmgr.rSetting(this.renderMode);
        Surround.setmgr.rSetting(this.renderSafeColor);
        Surround.setmgr.rSetting(this.renderUnSafeColor);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        this.oldPos = new BlockPos(new Vec3d((double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().x, 0), (double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().y, 0), (double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().z, 0)));
        final String valString = this.center.getValString();
        switch (valString) {
            case "TELEPORT": {
                double xPosition = Surround.mc.player.getPositionVector().x;
                double zPosition = Surround.mc.player.getPositionVector().z;
                if (Math.abs(this.oldPos.getX() + 0.5 - Surround.mc.player.getPositionVector().x) >= 0.2) {
                    final int xDirection = (this.oldPos.getX() + 0.5 - Surround.mc.player.getPositionVector().x > 0.0) ? 1 : -1;
                    xPosition += 0.3 * xDirection;
                }
                if (Math.abs(this.oldPos.getZ() + 0.5 - Surround.mc.player.getPositionVector().z) >= 0.2) {
                    final int zDirection = (this.oldPos.getZ() + 0.5 - Surround.mc.player.getPositionVector().z > 0.0) ? 1 : -1;
                    zPosition += 0.3 * zDirection;
                }
                TeleportUtil.teleportPlayer(xPosition, Surround.mc.player.posY, zPosition);
                break;
            }
            case "MOTION": {
                Surround.mc.player.motionX = (Math.floor(Surround.mc.player.posX) + 0.5 - Surround.mc.player.posX) / 2.0;
                Surround.mc.player.motionZ = (Math.floor(Surround.mc.player.posZ) + 0.5 - Surround.mc.player.posZ) / 2.0;
                break;
            }
        }
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    @Override
    public void update() {
        if (Surround.mc.player == null && Surround.mc.world == null) {
            return;
        }
        this.surroundPlaced = 0;
        final String valString = this.completion.getValString();
        switch (valString) {
            case "AIR": {
                if (!this.oldPos.equals((Object)new BlockPos(new Vec3d((double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().x, 0), (double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().y, 0), (double)MathUtil.roundFloat(Surround.mc.player.getPositionVector().z, 0)))) || Surround.mc.player.posY > this.oldPos.getY()) {
                    super.setToggled(false);
                    return;
                }
                break;
            }
            case "SURROUNDED": {
                if (isInHole((Entity)Surround.mc.player)) {
                    super.setToggled(false);
                    return;
                }
                break;
            }
        }
        this.handleSurround();
    }
    
    private SurroundVectors getEnumByName(final String name) {
        switch (name) {
            case "BASE": {
                return SurroundVectors.BASE;
            }
            case "STANDART": {
                return SurroundVectors.STANDARD;
            }
            case "PROTECT": {
                return SurroundVectors.PROTECT;
            }
            case "PROTECTplus": {
                return SurroundVectors.PROTECTplus;
            }
            default: {
                return SurroundVectors.BASE;
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.render.getValBoolean()) {
            for (final Vec3d surroundVectors : this.getEnumByName(this.surroundVec.getValString()).vectors) {
                CosmosRenderUtil.drawBox(new RenderBuilder().position(new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ)))).color((Objects.equals(BlockUtil.getBlockResistance(new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ)))), BlockUtil.BlockResistance.RESISTANT) || Objects.equals(BlockUtil.getBlockResistance(new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ)))), BlockUtil.BlockResistance.UNBREAKABLE)) ? this.renderSafeColor.getColour().getColor() : this.renderUnSafeColor.getColour().getColor()).box((RenderBuilder.Box)this.renderMode.getValEnum()).setup().line(1.5f).cull(((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.GLOW) || ((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.REVERSE)).shade(((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.GLOW) || ((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.REVERSE)).alpha(((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.GLOW) || ((RenderBuilder.Box)this.renderMode.getValEnum()).equals(RenderBuilder.Box.REVERSE)).depth(true).blend().texture());
            }
        }
    }
    
    public void handleSurround() {
        this.oldSlot = Surround.mc.player.inventory.currentItem;
        if (!isInHole((Entity)Surround.mc.player)) {
            InventoryUtil.switchToSlot(Item.getItemFromBlock(Blocks.OBSIDIAN), (InventoryUtil.Switch)this.autoSwitch.getValEnum());
            this.placeSurround();
            InventoryUtil.switchToSlot(this.oldSlot, InventoryUtil.Switch.NORMAL);
        }
    }
    
    public void placeSurround() {
        for (final Vec3d surroundVectors : this.getEnumByName(this.surroundVec.getValString()).vectors) {
            if (Objects.equals(BlockUtil.getBlockResistance(new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ)))), BlockUtil.BlockResistance.BLANK) && this.surroundPlaced <= this.blocksPerTick.getValDouble()) {
                this.surroundPosition = new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ)));
                if (RaytraceUtil.raytraceBlock(this.surroundPosition, Raytrace.NORMAL) && this.raytrace.getValBoolean()) {
                    return;
                }
                if (this.surroundPosition != BlockPos.ORIGIN && !this.rotate.getValString().equals(Rotation.Rotate.NONE.name())) {
                    final float[] surroundAngles = this.rotateCenter.getValBoolean() ? AngleUtil.calculateCenter(this.surroundPosition) : AngleUtil.calculateAngles(this.surroundPosition);
                    this.surroundRotation = new Rotation((float)(surroundAngles[0] + (this.rotateRandom.getValBoolean() ? ThreadLocalRandom.current().nextDouble(-4.0, 4.0) : 0.0)), (float)(surroundAngles[1] + (this.rotateRandom.getValBoolean() ? ThreadLocalRandom.current().nextDouble(-4.0, 4.0) : 0.0)), (Rotation.Rotate)this.rotate.getValEnum());
                    if (!Float.isNaN(this.surroundRotation.getYaw()) && !Float.isNaN(this.surroundRotation.getPitch())) {
                        this.surroundRotation.updateModelRotations();
                    }
                }
                for (final Entity item : Surround.mc.world.loadedEntityList) {
                    if (item instanceof EntityItem && ((EntityItem)item).getItem().getItem().equals(Item.getItemFromBlock(Blocks.OBSIDIAN))) {
                        item.setDead();
                        Surround.mc.world.removeEntityFromWorld(item.getEntityId());
                    }
                }
                BlockUtil.placeBlock(new BlockPos(surroundVectors.add(new Vec3d(Surround.mc.player.posX, (double)Math.round(Surround.mc.player.posY), Surround.mc.player.posZ))), this.packet.getValBoolean(), this.confirm.getValBoolean());
                PlayerUtil.swingArm((PlayerUtil.Hand)this.hand.getValEnum());
                ++this.surroundPlaced;
            }
        }
    }
    
    public static HoleUtil.BlockSafety isBlockSafe(final Block block) {
        if (block == Blocks.BEDROCK) {
            return HoleUtil.BlockSafety.UNBREAKABLE;
        }
        if (block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL) {
            return HoleUtil.BlockSafety.RESISTANT;
        }
        return HoleUtil.BlockSafety.BREAKABLE;
    }
    
    public static boolean isInHole(final double posX, final double posY, final double posZ) {
        return isObsidianHole(new BlockPos(posX, (double)Math.round(posY), posZ)) || isBedRockHole(new BlockPos(posX, (double)Math.round(posY), posZ));
    }
    
    public static boolean isInHole(final Entity entity) {
        return isObsidianHole(new BlockPos(entity.posX, (double)Math.round(entity.posY), entity.posZ)) || isBedRockHole(new BlockPos(entity.posX, (double)Math.round(entity.posY), entity.posZ));
    }
    
    public static HashMap<HoleUtil.BlockOffset, HoleUtil.BlockSafety> getUnsafeSides(final BlockPos pos) {
        final HashMap<HoleUtil.BlockOffset, HoleUtil.BlockSafety> output = new HashMap<HoleUtil.BlockOffset, HoleUtil.BlockSafety>();
        HoleUtil.BlockSafety temp = isBlockSafe(Surround.mc.world.getBlockState(HoleUtil.BlockOffset.DOWN.offset(pos)).getBlock());
        if (temp != HoleUtil.BlockSafety.UNBREAKABLE) {
            output.put(HoleUtil.BlockOffset.DOWN, temp);
        }
        temp = isBlockSafe(Surround.mc.world.getBlockState(HoleUtil.BlockOffset.NORTH.offset(pos)).getBlock());
        if (temp != HoleUtil.BlockSafety.UNBREAKABLE) {
            output.put(HoleUtil.BlockOffset.NORTH, temp);
        }
        temp = isBlockSafe(Surround.mc.world.getBlockState(HoleUtil.BlockOffset.SOUTH.offset(pos)).getBlock());
        if (temp != HoleUtil.BlockSafety.UNBREAKABLE) {
            output.put(HoleUtil.BlockOffset.SOUTH, temp);
        }
        temp = isBlockSafe(Surround.mc.world.getBlockState(HoleUtil.BlockOffset.EAST.offset(pos)).getBlock());
        if (temp != HoleUtil.BlockSafety.UNBREAKABLE) {
            output.put(HoleUtil.BlockOffset.EAST, temp);
        }
        temp = isBlockSafe(Surround.mc.world.getBlockState(HoleUtil.BlockOffset.WEST.offset(pos)).getBlock());
        if (temp != HoleUtil.BlockSafety.UNBREAKABLE) {
            output.put(HoleUtil.BlockOffset.WEST, temp);
        }
        return output;
    }
    
    public static boolean isObsidianHole(final BlockPos blockPos) {
        return BlockUtil.getBlockResistance(blockPos.add(0, 1, 0)) == BlockUtil.BlockResistance.BLANK && !isBedRockHole(blockPos) && BlockUtil.getBlockResistance(blockPos.add(0, 0, 0)) == BlockUtil.BlockResistance.BLANK && BlockUtil.getBlockResistance(blockPos.add(0, 2, 0)) == BlockUtil.BlockResistance.BLANK && (BlockUtil.getBlockResistance(blockPos.add(0, 0, -1)) == BlockUtil.BlockResistance.RESISTANT || BlockUtil.getBlockResistance(blockPos.add(0, 0, -1)) == BlockUtil.BlockResistance.UNBREAKABLE) && (BlockUtil.getBlockResistance(blockPos.add(1, 0, 0)) == BlockUtil.BlockResistance.RESISTANT || BlockUtil.getBlockResistance(blockPos.add(1, 0, 0)) == BlockUtil.BlockResistance.UNBREAKABLE) && (BlockUtil.getBlockResistance(blockPos.add(-1, 0, 0)) == BlockUtil.BlockResistance.RESISTANT || BlockUtil.getBlockResistance(blockPos.add(-1, 0, 0)) == BlockUtil.BlockResistance.UNBREAKABLE) && (BlockUtil.getBlockResistance(blockPos.add(0, 0, 1)) == BlockUtil.BlockResistance.RESISTANT || BlockUtil.getBlockResistance(blockPos.add(0, 0, 1)) == BlockUtil.BlockResistance.UNBREAKABLE) && BlockUtil.getBlockResistance(blockPos.add(0.5, 0.5, 0.5)) == BlockUtil.BlockResistance.BLANK && (BlockUtil.getBlockResistance(blockPos.add(0, -1, 0)) == BlockUtil.BlockResistance.RESISTANT || BlockUtil.getBlockResistance(blockPos.add(0, -1, 0)) == BlockUtil.BlockResistance.UNBREAKABLE);
    }
    
    public static boolean isBedRockHole(final BlockPos blockPos) {
        return BlockUtil.getBlockResistance(blockPos.add(0, 1, 0)) == BlockUtil.BlockResistance.BLANK && BlockUtil.getBlockResistance(blockPos.add(0, 0, 0)) == BlockUtil.BlockResistance.BLANK && BlockUtil.getBlockResistance(blockPos.add(0, 2, 0)) == BlockUtil.BlockResistance.BLANK && BlockUtil.getBlockResistance(blockPos.add(0, 0, -1)) == BlockUtil.BlockResistance.UNBREAKABLE && BlockUtil.getBlockResistance(blockPos.add(1, 0, 0)) == BlockUtil.BlockResistance.UNBREAKABLE && BlockUtil.getBlockResistance(blockPos.add(-1, 0, 0)) == BlockUtil.BlockResistance.UNBREAKABLE && BlockUtil.getBlockResistance(blockPos.add(0, 0, 1)) == BlockUtil.BlockResistance.UNBREAKABLE && BlockUtil.getBlockResistance(blockPos.add(0.5, 0.5, 0.5)) == BlockUtil.BlockResistance.BLANK && BlockUtil.getBlockResistance(blockPos.add(0, -1, 0)) == BlockUtil.BlockResistance.UNBREAKABLE;
    }
    
    public enum SurroundVectors
    {
        BASE((List<Vec3d>)new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0)))), 
        STANDARD((List<Vec3d>)new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0)))), 
        PROTECT((List<Vec3d>)new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(3.0, 0.0, 0.0), new Vec3d(-3.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 3.0), new Vec3d(0.0, 0.0, -3.0)))), 
        PROTECTplus((List<Vec3d>)new ArrayList<Vec3d>(Arrays.asList(new Vec3d(0.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(3.0, 0.0, 0.0), new Vec3d(-3.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 3.0), new Vec3d(0.0, 0.0, -3.0))));
        
        private final List<Vec3d> vectors;
        
        private SurroundVectors(final List<Vec3d> vectors) {
            this.vectors = vectors;
        }
        
        public List<Vec3d> getVectors() {
            return this.vectors;
        }
    }
    
    public enum Center
    {
        TELEPORT, 
        MOTION, 
        NONE;
    }
    
    public enum Completion
    {
        AIR, 
        SURROUNDED, 
        PERSISTENT;
    }
}
