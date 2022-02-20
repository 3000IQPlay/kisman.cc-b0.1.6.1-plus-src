//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.frame.sub;

import com.kisman.cc.oldclickgui.vega.component.components.frame.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.clickguiEvents.drawScreen.render.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.clickguiEvents.mouseClicked.*;
import java.util.function.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;

public class ModeButton extends Component
{
    public int x;
    public int y;
    public Frame parent;
    public Setting set;
    public boolean open;
    public int width;
    public int height;
    public int offset;
    private boolean hover;
    @EventHandler
    private final Listener<GuiRenderPostEvent> listener1;
    @EventHandler
    private final Listener<MouseClickedPreEvent> listener;
    
    public ModeButton(final int x, final int y, final int offset, final int width, final int height, final Frame parent, final Setting set) {
        this.listener1 = (Listener<GuiRenderPostEvent>)new Listener(event -> {
            Gui.drawRect(this.x - 3, this.y + 3 + this.offset, this.x + this.width + 3, this.y + this.height + 3 + this.offset, ColorUtils.getColor(33, 33, 42));
            Gui.drawRect(this.x - 3, this.y + this.offset, this.x + this.width + 3, this.y + this.height + this.offset, ColorUtils.getColor(33, 33, 42));
            Gui.drawRect(this.x - 2, this.y + 2 + this.offset, this.x + this.width + 2, this.y + this.height + 2 + this.offset, ColorUtils.getColor(45, 45, 55));
            Gui.drawRect(this.x - 2, this.y + this.offset, this.x + this.width + 2, this.y + this.height + this.offset, ColorUtils.getColor(45, 45, 55));
            Gui.drawRect(this.x - 1, this.y + 1 + this.offset, this.x + this.width + 1, this.y + this.height + 1 + this.offset, ColorUtils.getColor(60, 60, 70));
            Gui.drawRect(this.x - 1, this.y + this.offset, this.x + this.width + 1, this.y + this.height + this.offset, ColorUtils.getColor(60, 60, 70));
            Gui.drawRect(this.x, this.y + this.offset, this.x + this.width, this.y + this.height + this.offset, ColorUtils.getColor(34, 34, 40));
            CustomFontUtil.drawStringWithShadow(this.set.getName() + ": " + this.set.getValString(), this.x + 6, this.y + (this.height - CustomFontUtil.getFontHeight()) / 2 + this.offset, this.hover ? ColorUtils.astolfoColors(100, 100) : -1);
        }, new Predicate[0]);
        this.listener = (Listener<MouseClickedPreEvent>)new Listener(event -> {
            if (this.isMouseOnButton(event.mouseX, event.mouseY) && event.mouseButton == 0) {
                if (this.set.getOptions() != null) {
                    final int maxIndex = this.set.getOptions().size();
                    int modeIndex = 0;
                    if (modeIndex++ > maxIndex) {
                        modeIndex = 0;
                    }
                    this.set.setValString(this.set.getOptions().get(modeIndex));
                }
                else if (this.set.getOptionEnum() != null) {
                    final Enum nextSettingVal = this.set.getNextModeEnum();
                    this.set.setValString(nextSettingVal.name());
                    this.set.setValEnum(nextSettingVal);
                }
            }
            if (this.isMouseOnButton(event.mouseX, event.mouseY) && event.mouseButton == 1) {
                if (this.set.getOptions() != null) {
                    final int maxIndex = this.set.getOptions().size();
                    int modeIndex = 0;
                    if (modeIndex-- < 0) {
                        modeIndex = maxIndex;
                    }
                    this.set.setValString(this.set.getOptions().get(modeIndex));
                }
                else if (this.set.getOptionEnum() != null) {
                    final Enum nextSettingVal = this.set.getNextModeEnum();
                    this.set.setValString(nextSettingVal.name());
                    this.set.setValEnum(nextSettingVal);
                }
            }
        }, new Predicate[0]);
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.set = set;
        this.width = width;
        this.height = height;
        this.offset = offset;
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.x = this.parent.x;
        this.y = this.parent.y;
        this.hover = this.isMouseOnButton(mouseX, mouseY);
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y + this.offset && y <= this.y + this.offset + this.height;
    }
}
