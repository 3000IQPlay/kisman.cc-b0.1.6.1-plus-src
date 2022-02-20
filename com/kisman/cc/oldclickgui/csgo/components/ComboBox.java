//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;
import net.minecraft.util.text.*;

public class ComboBox extends AbstractComponent
{
    private static final int PREFERRED_WIDTH = 180;
    private static final int PREFERRED_HEIGHT = 22;
    private int preferredWidth;
    private int preferredHeight;
    private boolean hovered;
    private boolean hoveredExtended;
    private ValueChangeListener<Integer> listener;
    private String[] values;
    private int selectedIndex;
    private boolean opened;
    private int mouseX;
    private int mouseY;
    
    public ComboBox(final IRenderer renderer, final int preferredWidth, final int preferredHeight, final String[] values, final int selectedIndex) {
        super(renderer);
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
        this.values = values;
        this.selectedIndex = selectedIndex;
        this.setWidth(preferredWidth);
        this.updateHeight();
    }
    
    public ComboBox(final IRenderer renderer, final String[] values, final int selectedIndex) {
        this(renderer, 180, 22, values, selectedIndex);
    }
    
    private void updateHeight() {
        if (this.opened) {
            this.setHeight(this.preferredHeight * this.values.length + 4);
        }
        else {
            this.setHeight(this.preferredHeight);
        }
    }
    
    public void render() {
        this.updateHeight();
        this.renderer.drawRect(this.x, this.y, this.getWidth(), this.getHeight(), Window.TERTIARY_FOREGROUND);
        if (this.hovered) {
            this.renderer.drawRect(this.x, this.y, this.getWidth(), this.preferredHeight, Window.SECONDARY_FOREGROUND);
        }
        else if (this.hoveredExtended) {
            int offset = this.preferredHeight + 4;
            for (int i = 0; i < this.values.length; ++i) {
                if (i != this.selectedIndex) {
                    int height = this.preferredHeight;
                    Label_0180: {
                        Label_0177: {
                            if (this.selectedIndex == 0) {
                                if (i == 1) {
                                    break Label_0177;
                                }
                            }
                            else if (i == 0) {
                                break Label_0177;
                            }
                            if (this.selectedIndex == this.values.length - 1) {
                                if (i != this.values.length - 2) {
                                    break Label_0180;
                                }
                            }
                            else if (i != this.values.length - 1) {
                                break Label_0180;
                            }
                        }
                        ++height;
                    }
                    if (this.mouseY >= this.getY() + offset && this.mouseY <= this.getY() + offset + this.preferredHeight) {
                        this.renderer.drawRect(this.x, this.y + offset, this.getWidth(), this.preferredHeight, Window.SECONDARY_FOREGROUND);
                        break;
                    }
                    offset += height;
                }
            }
        }
        this.renderer.drawRect(this.x + this.getWidth() - this.preferredHeight, this.y, this.preferredHeight, this.getHeight(), (this.hovered || this.opened) ? Window.TERTIARY_FOREGROUND : Window.SECONDARY_FOREGROUND);
        this.renderer.drawTriangle(this.x + this.getWidth() - this.preferredHeight + this.preferredHeight / 4.0, this.y + this.preferredHeight / 4.0, this.x + this.getWidth() - this.preferredHeight + this.preferredHeight / 2.0, this.y + this.preferredHeight * 3.0 / 4.0, this.x + this.getWidth() - this.preferredHeight + this.preferredHeight * 3.0 / 4.0, this.y + this.preferredHeight / 4.0, Window.FOREGROUND);
        this.renderer.drawOutline(this.x, this.y, this.getWidth(), this.getHeight(), 1.0f, (this.hovered && !this.opened) ? Window.SECONDARY_OUTLINE : Window.SECONDARY_FOREGROUND);
        final String text = (this.selectedIndex != -1) ? this.values[this.selectedIndex] : (TextFormatting.RED + "ERROR");
        this.renderer.drawString(this.x + 4, this.y + this.preferredHeight / 2 - this.renderer.getStringHeight(text) / 2, text, Window.FOREGROUND);
        if (this.opened) {
            int offset2 = this.preferredHeight + 4;
            for (int j = 0; j < this.values.length; ++j) {
                if (j != this.selectedIndex) {
                    int height2 = this.preferredHeight;
                    Label_0701: {
                        Label_0698: {
                            if (this.selectedIndex == 0) {
                                if (j == 1) {
                                    break Label_0698;
                                }
                            }
                            else if (j == 0) {
                                break Label_0698;
                            }
                            if (this.selectedIndex == this.values.length - 1) {
                                if (j != this.values.length - 2) {
                                    break Label_0701;
                                }
                            }
                            else if (j != this.values.length - 1) {
                                break Label_0701;
                            }
                        }
                        ++height2;
                    }
                    this.renderer.drawString(this.x + 4, this.y + offset2 + (11 - this.renderer.getStringHeight(this.values[j]) / 2), this.values[j], Window.FOREGROUND);
                    offset2 += height2;
                }
            }
        }
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        return false;
    }
    
    private void updateHovered(final int x, final int y, final boolean offscreen) {
        this.hovered = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.preferredHeight);
        this.hoveredExtended = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.getHeight());
        this.mouseX = x;
        this.mouseY = y;
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        if (button != 0) {
            return false;
        }
        if (this.hovered) {
            this.setOpened(!this.opened);
            this.updateHeight();
            return true;
        }
        if (this.hoveredExtended && this.opened) {
            int offset = this.y + this.preferredHeight + 4;
            for (int i = 0; i < this.values.length; ++i) {
                if (i != this.selectedIndex) {
                    if (y >= offset && y <= offset + this.preferredHeight) {
                        this.setSelectedChecked(i);
                        this.setOpened(false);
                        break;
                    }
                    offset += this.preferredHeight;
                }
            }
            this.updateHovered(x, y, offscreen);
            return true;
        }
        return false;
    }
    
    private void setSelectedChecked(final int i) {
        boolean change = true;
        if (this.listener != null) {
            change = this.listener.onValueChange(i);
        }
        if (change) {
            this.selectedIndex = i;
        }
    }
    
    public int getSelectedIndex() {
        return this.selectedIndex;
    }
    
    public void setSelectedIndex(final int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    
    public void setListener(final ValueChangeListener<Integer> listener) {
        this.listener = listener;
    }
    
    public void setOpened(final boolean opened) {
        this.opened = opened;
        this.updateHeight();
    }
}
