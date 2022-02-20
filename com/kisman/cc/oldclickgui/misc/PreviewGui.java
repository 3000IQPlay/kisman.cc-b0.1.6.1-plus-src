//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.misc;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import com.kisman.cc.util.nbt.*;
import net.minecraft.inventory.*;
import com.kisman.cc.oldclickgui.book.components.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import java.io.*;

@SideOnly(Side.CLIENT)
public class PreviewGui extends RightPanel
{
    private static final ResourceLocation GUI_TEXTURE;
    private static final int INNER_BORDER;
    private static int guiWidth;
    private static int guiHeight;
    private int x;
    private int y;
    private int x_end;
    private int y_end;
    private InventoryBasic inventory;
    private List<Slot> slots;
    private boolean hasParent;
    
    public PreviewGui(final NBTTagList list) {
        this(list, true);
    }
    
    public PreviewGui(final NBTTagList list, final boolean hasParent) {
        this.hasParent = hasParent;
        this.inventory = new InventoryBasic((String)null, false, 27);
        this.slots = new LinkedList<Slot>();
        for (final NBTBase i_base : list) {
            final NBTTagCompound i = (NBTTagCompound)i_base;
            final ItemStack stack = new ItemStack(i);
            if (!stack.isEmpty()) {
                final int index = i.getByte("Slot") & 0xFF;
                if (i.getShort("Damage") < 0) {
                    stack.setTagCompound((NBTTagCompound)new SpecialTagCompound(stack.getTagCompound(), i.getShort("Damage")));
                }
                this.inventory.setInventorySlotContents(index, stack);
                this.slots.add(new Slot((IInventory)this.inventory, index, 8 + index % 9 * 18, 26 + index / 9 * 18));
            }
        }
        if (!this.hasParent) {
            this.x = (this.width - PreviewGui.guiWidth) / 2;
            this.y = (this.height - PreviewGui.guiHeight) / 2;
        }
        else {
            this.x = (this.width - 148 - 12 - PreviewGui.guiWidth) / 2 + 148 + 12;
            this.y = (200 - PreviewGui.guiHeight) / 2 + 12;
            this.buttonList.add(new GenericButton(0, this.x + 3, this.y + 3, "Back") {
                public void onClick(final GuiScreen parent) {
                }
            });
        }
        this.x_end = this.x + PreviewGui.guiWidth;
        this.y_end = this.y + PreviewGui.guiHeight;
        for (final Slot slot : this.slots) {
            final Slot s = slot;
            slot.xPos += this.x;
            final Slot slot2 = s;
            slot2.yPos += this.y;
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.hasParent) {
            Gui.drawRect(this.x, this.y, this.x_end, this.y_end, new Color(0, 0, 0, 255).getRGB());
            Gui.drawRect(this.x, this.y, this.x_end, this.y + 1, PreviewGui.INNER_BORDER);
            Gui.drawRect(this.x, this.y, this.x + 1, this.y_end, PreviewGui.INNER_BORDER);
            Gui.drawRect(this.x_end - 1, this.y, this.x_end, this.y_end, PreviewGui.INNER_BORDER);
            Gui.drawRect(this.x, this.y_end - 1, this.x_end, this.y_end, PreviewGui.INNER_BORDER);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(PreviewGui.GUI_TEXTURE);
        this.drawTexturedModalRect(this.x, this.y + 18, 0, 0, PreviewGui.guiWidth, PreviewGui.guiHeight);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        for (final Slot s : this.slots) {
            this.itemRender.renderItemAndEffectIntoGUI((EntityLivingBase)this.mc.player, s.getStack(), s.xPos, s.yPos);
            this.itemRender.renderItemOverlayIntoGUI(this.fontRenderer, s.getStack(), s.xPos, s.yPos, (String)null);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (mouseX >= this.x + 8 && mouseY >= this.y + 26) {
            final int x = (mouseX - this.x - 8) / 18;
            final int y = (mouseY - this.y - 26) / 18;
            if (x >= 0 && x < 9 && y >= 0 && y < 3) {
                final ItemStack stack = this.inventory.getStackInSlot(y * 9 + x);
                if (!stack.isEmpty()) {
                    this.renderToolTip(stack, mouseX, mouseY);
                }
            }
        }
    }
    
    @Override
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
    }
    
    static {
        GUI_TEXTURE = new ResourceLocation("kismancc:gui/preview.png");
        INNER_BORDER = PreviewGui.INNER_BORDER;
        PreviewGui.guiWidth = 176;
        PreviewGui.guiHeight = 80;
    }
}
