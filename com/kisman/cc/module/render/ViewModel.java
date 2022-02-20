//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;

public class ViewModel extends Module
{
    public static ViewModel instance;
    public Setting itemFOV;
    public Setting fov;
    public Setting scaleRightX;
    public Setting scaleRightY;
    public Setting scaleRightZ;
    public Setting scaleLeftX;
    public Setting scaleLeftY;
    public Setting scaleLeftZ;
    public Setting autoRotateRigthX;
    public Setting autoRotateRigthY;
    public Setting autoRotateRigthZ;
    public Setting autoRotateLeftX;
    public Setting autoRotateLeftY;
    public Setting autoRotateLeftZ;
    private Setting handLine;
    public Setting hands;
    public Setting handRightX;
    public Setting handRightY;
    public Setting handRightZ;
    public Setting handRightRotateX;
    public Setting handRightRotateY;
    public Setting handRightRotateZ;
    public Setting handRightScaleX;
    public Setting handRightScaleY;
    public Setting handRightScaleZ;
    public Setting handLeftX;
    public Setting handLeftY;
    public Setting handLeftZ;
    public Setting handLeftRotateX;
    public Setting handLeftRotateY;
    public Setting handLeftRotateZ;
    public Setting handLeftScaleX;
    public Setting handLeftScaleY;
    public Setting handLeftScaleZ;
    private Setting itemLine;
    public Setting alpha;
    
    public ViewModel() {
        super("ViewModel", "modeL vieM", Category.RENDER);
        this.itemFOV = new Setting("ItemFOV", this, false);
        this.fov = new Setting("FOV", this, 130.0, 70.0, 200.0, true);
        this.scaleRightX = new Setting("ScaleRigthX", this, 1.0, -2.0, 2.0, false);
        this.scaleRightY = new Setting("ScaleRigthY", this, 1.0, -2.0, 2.0, false);
        this.scaleRightZ = new Setting("ScaleRigthZ", this, 1.0, -2.0, 2.0, false);
        this.scaleLeftX = new Setting("ScaleLeftX", this, 1.0, -2.0, 2.0, false);
        this.scaleLeftY = new Setting("ScaleLeftY", this, 1.0, -2.0, 2.0, false);
        this.scaleLeftZ = new Setting("ScaleLeftZ", this, 1.0, -2.0, 2.0, false);
        this.autoRotateRigthX = new Setting("AutoRotateRigthX", this, false);
        this.autoRotateRigthY = new Setting("AutoRotateRigthY", this, false);
        this.autoRotateRigthZ = new Setting("AutoRotateRigthZ", this, false);
        this.autoRotateLeftX = new Setting("AutoRotateLeftX", this, false);
        this.autoRotateLeftY = new Setting("AutoRotateLeftY", this, false);
        this.autoRotateLeftZ = new Setting("AutoRotateLeftZ", this, false);
        this.handLine = new Setting("HandLine", this, "Hand");
        this.hands = new Setting("Hands", this, false);
        this.handRightX = new Setting("HandRightX", this, 0.0, -4.0, 4.0, false);
        this.handRightY = new Setting("HandRightY", this, 0.0, -4.0, 4.0, false);
        this.handRightZ = new Setting("HandRightZ", this, 0.0, -4.0, 4.0, false);
        this.handRightRotateX = new Setting("HandRotateRightX", this, 0.0, 0.0, 360.0, false);
        this.handRightRotateY = new Setting("HandRotateRightY", this, 0.0, 0.0, 360.0, false);
        this.handRightRotateZ = new Setting("HandRotateRightZ", this, 0.0, 0.0, 360.0, false);
        this.handRightScaleX = new Setting("HandScaleRightX", this, 0.0, -2.0, 2.0, false);
        this.handRightScaleY = new Setting("HandScaleRightY", this, 0.0, -2.0, 2.0, false);
        this.handRightScaleZ = new Setting("HandScaleRightZ", this, 0.0, -2.0, 2.0, false);
        this.handLeftX = new Setting("HandLeftX", this, 0.0, -4.0, 4.0, false);
        this.handLeftY = new Setting("HandLeftY", this, 0.0, -4.0, 4.0, false);
        this.handLeftZ = new Setting("HandLeftZ", this, 0.0, -4.0, 4.0, false);
        this.handLeftRotateX = new Setting("HandRotateLeftX", this, 0.0, 0.0, 360.0, false);
        this.handLeftRotateY = new Setting("HandRotateLeftY", this, 0.0, 0.0, 360.0, false);
        this.handLeftRotateZ = new Setting("HandRotateLeftZ", this, 0.0, 0.0, 360.0, false);
        this.handLeftScaleX = new Setting("HandScaleLeftX", this, 0.0, -2.0, 2.0, false);
        this.handLeftScaleY = new Setting("HandScaleLeftY", this, 0.0, -2.0, 2.0, false);
        this.handLeftScaleZ = new Setting("HandScaleLeftZ", this, 0.0, -2.0, 2.0, false);
        this.itemLine = new Setting("ItemLine", this, "Item");
        this.alpha = new Setting("Alpha", this, 255.0, 0.0, 255.0, true);
        ViewModel.instance = this;
        ViewModel.setmgr.rSetting(this.itemFOV);
        ViewModel.setmgr.rSetting(this.fov);
        Kisman.instance.settingsManager.rSetting(new Setting("RightX", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RightY", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RightZ", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateRightX", this, 0.0, 0.0, 360.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateRightY", this, 0.0, 0.0, 360.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateRightZ", this, 0.0, 0.0, 360.0, false));
        ViewModel.setmgr.rSetting(this.autoRotateRigthX);
        ViewModel.setmgr.rSetting(this.autoRotateRigthY);
        ViewModel.setmgr.rSetting(this.autoRotateRigthZ);
        ViewModel.setmgr.rSetting(this.scaleRightX);
        ViewModel.setmgr.rSetting(this.scaleRightY);
        ViewModel.setmgr.rSetting(this.scaleRightZ);
        Kisman.instance.settingsManager.rSetting(new Setting("LeftX", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("LeftY", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("LeftZ", this, 0.0, -2.0, 2.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateLeftX", this, 0.0, 0.0, 360.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateLeftY", this, 0.0, 0.0, 360.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("RotateLeftZ", this, 0.0, 0.0, 360.0, false));
        ViewModel.setmgr.rSetting(this.autoRotateLeftX);
        ViewModel.setmgr.rSetting(this.autoRotateLeftY);
        ViewModel.setmgr.rSetting(this.autoRotateLeftZ);
        ViewModel.setmgr.rSetting(this.scaleLeftX);
        ViewModel.setmgr.rSetting(this.scaleLeftY);
        ViewModel.setmgr.rSetting(this.scaleLeftZ);
        ViewModel.setmgr.rSetting(this.handLine);
        ViewModel.setmgr.rSetting(this.hands);
        ViewModel.setmgr.rSetting(this.handRightX);
        ViewModel.setmgr.rSetting(this.handRightY);
        ViewModel.setmgr.rSetting(this.handRightZ);
        ViewModel.setmgr.rSetting(this.handRightRotateX);
        ViewModel.setmgr.rSetting(this.handRightRotateY);
        ViewModel.setmgr.rSetting(this.handRightRotateZ);
        ViewModel.setmgr.rSetting(this.handRightScaleX);
        ViewModel.setmgr.rSetting(this.handRightScaleY);
        ViewModel.setmgr.rSetting(this.handRightScaleZ);
        ViewModel.setmgr.rSetting(this.handLeftX);
        ViewModel.setmgr.rSetting(this.handLeftY);
        ViewModel.setmgr.rSetting(this.handLeftZ);
        ViewModel.setmgr.rSetting(this.handLeftRotateX);
        ViewModel.setmgr.rSetting(this.handLeftRotateY);
        ViewModel.setmgr.rSetting(this.handLeftRotateZ);
        ViewModel.setmgr.rSetting(this.handLeftScaleX);
        ViewModel.setmgr.rSetting(this.handLeftScaleY);
        ViewModel.setmgr.rSetting(this.handLeftScaleZ);
        ViewModel.setmgr.rSetting(this.itemLine);
        ViewModel.setmgr.rSetting(this.alpha);
    }
    
    @SubscribeEvent
    public void onItemFOV(final EntityViewRenderEvent.FOVModifier event) {
        if (this.itemFOV.getValBoolean()) {
            event.setFOV((float)this.fov.getValDouble());
        }
    }
    
    @SubscribeEvent
    public void onRenderArms(final RenderSpecificHandEvent event) {
        if (this.hands.getValBoolean()) {
            switch (event.getHand()) {
                case MAIN_HAND: {
                    GL11.glTranslated(this.handRightX.getValDouble(), this.handRightY.getValDouble(), this.handRightZ.getValDouble());
                    GL11.glRotated(this.handRightRotateX.getValDouble(), 1.0, 0.0, 0.0);
                    GL11.glRotated(this.handRightRotateY.getValDouble(), 0.0, 1.0, 0.0);
                    GL11.glRotated(this.handRightRotateZ.getValDouble(), 0.0, 0.0, 1.0);
                    GL11.glScaled(this.handRightScaleX.getValDouble(), this.handRightScaleY.getValDouble(), this.handRightScaleZ.getValDouble());
                    break;
                }
                case OFF_HAND: {
                    GL11.glTranslated(this.handLeftX.getValDouble(), this.handLeftY.getValDouble(), this.handLeftZ.getValDouble());
                    GL11.glRotated(this.handLeftRotateX.getValDouble(), 1.0, 0.0, 0.0);
                    GL11.glRotated(this.handLeftRotateY.getValDouble(), 0.0, 1.0, 0.0);
                    GL11.glRotated(this.handLeftRotateZ.getValDouble(), 0.0, 0.0, 1.0);
                    GL11.glScaled(this.handLeftScaleX.getValDouble(), this.handLeftScaleY.getValDouble(), this.handLeftScaleZ.getValDouble());
                    break;
                }
            }
        }
    }
}
