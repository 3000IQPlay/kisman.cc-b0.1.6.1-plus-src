//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.tileentity.*;

public class StorageESP extends Module
{
    private Setting distance;
    private Setting renderMode;
    boolean chest;
    boolean eChest;
    boolean shulkerBox;
    boolean dispenser;
    boolean furnace;
    boolean hopper;
    boolean dropper;
    
    public StorageESP() {
        super("StorageESP", "sosat", Category.RENDER);
        this.distance = new Setting("Distance", this, 100.0, 10.0, 100.0, true);
        this.renderMode = new Setting("RenderMode", this, RenderMode.Both);
        this.chest = true;
        this.eChest = true;
        this.shulkerBox = true;
        this.dispenser = true;
        this.furnace = true;
        this.hopper = true;
        this.dropper = true;
        StorageESP.setmgr.rSetting(this.distance);
        Kisman.instance.settingsManager.rSetting(new Setting("Chest", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("EChest", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("ShulkerBox", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Dispenser", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Furnace", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Hopper", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Dropper", this, true));
    }
    
    public void update() {
        this.chest = Kisman.instance.settingsManager.getSettingByName(this, "Chest").getValBoolean();
        this.eChest = Kisman.instance.settingsManager.getSettingByName(this, "EChest").getValBoolean();
        this.shulkerBox = Kisman.instance.settingsManager.getSettingByName(this, "ShulkerBox").getValBoolean();
        this.dispenser = Kisman.instance.settingsManager.getSettingByName(this, "Dispenser").getValBoolean();
        this.furnace = Kisman.instance.settingsManager.getSettingByName(this, "Furnace").getValBoolean();
        this.hopper = Kisman.instance.settingsManager.getSettingByName(this, "Hopper").getValBoolean();
        this.dropper = Kisman.instance.settingsManager.getSettingByName(this, "Dropper").getValBoolean();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        StorageESP.mc.world.loadedTileEntityList.stream().filter(tileEntity -> tileEntity.getDistanceSq(StorageESP.mc.player.posX, StorageESP.mc.player.posY, StorageESP.mc.player.posZ) <= this.distance.getValDouble()).forEach(tileEntity -> {
            if (tileEntity instanceof TileEntityChest && this.chest) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.94f, 0.6f, 0.11f);
            }
            if (tileEntity instanceof TileEntityEnderChest && this.eChest) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.53f, 0.11f, 0.94f);
            }
            if (tileEntity instanceof TileEntityShulkerBox && this.shulkerBox) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.8f, 0.08f, 0.93f);
            }
            if (tileEntity instanceof TileEntityDispenser && this.dispenser) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.34f, 0.32f, 0.34f);
            }
            if (tileEntity instanceof TileEntityFurnace && this.furnace) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.34f, 0.32f, 0.34f);
            }
            if (tileEntity instanceof TileEntityHopper && this.hopper) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.34f, 0.32f, 0.34f);
            }
            if (tileEntity instanceof TileEntityDropper && this.dropper) {
                RenderUtil.drawBlockESP(tileEntity.getPos(), 0.34f, 0.32f, 0.34f);
            }
        });
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both;
    }
}
