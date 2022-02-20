//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.settings.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.oldclickgui.component.components.*;
import net.minecraft.client.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.*;
import net.minecraft.world.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;

public class PreviewButton extends Component
{
    public Setting set;
    public EntityEnderCrystal entityEnderCrystal;
    public EntityPlayer entityPlayer;
    public boolean open;
    public int offset;
    public Button button;
    private boolean hover;
    public int x;
    public int y;
    private int mouseX;
    private int mouseY;
    private Minecraft mc;
    
    public PreviewButton(final Setting set, final Button parent, final int offset) {
        this.open = false;
        this.mc = Minecraft.getMinecraft();
        this.set = set;
        this.button = parent;
        this.offset = offset;
        this.x = this.button.parent.getX();
        this.y = this.button.parent.getY();
    }
    
    public void renderComponent() {
        final Entity entity = this.set.getEntity();
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, this.hover ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        Gui.drawRect(this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + this.mc.fontRenderer.FONT_HEIGHT - 5 + 5, this.button.parent.getX() + 7 + this.button.parent.getWidth() - 7 - 3, this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4 + 5, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (this.open) {
            Gui.drawRect(this.button.parent.getX() + 5, this.button.parent.getY() + this.offset + 12, this.button.parent.getX() + 88 - 5, this.button.parent.getY() + this.offset + 112, new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
            Gui.drawRect(this.button.parent.getX() + 5, this.button.parent.getY() + this.offset + 12, this.button.parent.getX() + 6, this.button.parent.getY() + this.offset + 112, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
                Gui.drawRect(this.button.parent.getX() + 88 - 6, this.button.parent.getY() + this.offset + 12, this.button.parent.getX() + 88 - 5, this.button.parent.getY() + this.offset + 112, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
            if (entity instanceof EntityEnderCrystal) {
                final EntityEnderCrystal ent = this.entityEnderCrystal = new EntityEnderCrystal((World)this.mc.world, Double.longBitsToDouble(Double.doubleToLongBits(9.310613315809524E306) ^ 0x7FAA847B55B02A7FL), Double.longBitsToDouble(Double.doubleToLongBits(1.7125394916952668E308) ^ 0x7FEE7BF580E967CDL), Double.longBitsToDouble(Double.doubleToLongBits(1.351057559302745E308) ^ 0x7FE80CB4154FF45AL));
                ent.setShowBottom(false);
                ent.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(1.1630837E38f) ^ 0x7EAF005B);
                ent.rotationPitch = Float.intBitsToFloat(Float.floatToIntBits(2.1111544E38f) ^ 0x7F1ED35B);
                ent.innerRotation = 0;
                ent.prevRotationYaw = Float.intBitsToFloat(Float.floatToIntBits(3.176926E38f) ^ 0x7F6F015F);
                ent.prevRotationPitch = Float.intBitsToFloat(Float.floatToIntBits(2.4984888E38f) ^ 0x7F3BF725);
                if (ent != null) {
                    GL11.glScalef(Float.intBitsToFloat(Float.floatToIntBits(6.72125f) ^ 0x7F57147B), Float.intBitsToFloat(Float.floatToIntBits(8.222657f) ^ 0x7E839001), Float.intBitsToFloat(Float.floatToIntBits(7.82415f) ^ 0x7F7A5F70));
                    this.drawEntityOnScreen(this.button.parent.getX() + 50, this.button.parent.getY() + this.offset + 102, 40, 0.0f, 0.0f, (Entity)ent);
                }
            }
            else {
                this.drawEntityOnScreen(this.button.parent.getX() + 44, this.button.parent.getY() + this.offset + 98, 40, 0.0f, 0.0f, (Entity)this.mc.player);
            }
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getTitle(), (float)((this.button.parent.getX() + 4) * 2), (float)((this.button.parent.getY() + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (this.isMouseOnButton(mouseX, mouseY)) {
            this.hover = true;
        }
        else {
            this.hover = false;
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.button.parent.refreshPosition();
        }
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.button.parent.getX() && x < this.button.parent.getX() + 88 && y > this.button.parent.getY() + this.offset && y < this.button.parent.getY() + this.offset + 12;
    }
    
    public void drawEntityOnScreen(final int posX, final int posY, final int scale, final float mouseX, final float mouseY, final Entity ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.translate((float)posX, (float)posY, 50.0f);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float f1 = ent.rotationYaw;
        final float f2 = ent.rotationPitch;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(mouseY / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        ent.setRenderYawOffset((float)Math.atan(mouseX / 40.0f) * 20.0f);
        ent.rotationYaw = (float)Math.atan(mouseX / 40.0f) * 40.0f;
        ent.rotationPitch = -(float)Math.atan(mouseY / 40.0f) * 20.0f;
        ent.setRotationYawHead(ent.rotationYaw);
        ent.prevRotationYaw = ent.rotationYaw;
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        rendermanager.setRenderShadow(true);
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
