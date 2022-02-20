//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo;

import net.minecraft.client.gui.*;
import com.kisman.cc.*;
import com.kisman.cc.oldclickgui.csgo.layout.*;
import com.kisman.cc.module.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.csgo.components.*;
import java.util.*;
import com.kisman.cc.util.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import java.io.*;
import org.lwjgl.input.*;

public class ClickGuiNew extends GuiScreen
{
    private final HashMap<Category, Pane> categoryPaneMap;
    private final Pane spoilerPane;
    private Window window;
    private IRenderer renderer;
    private List<ActionEventListener> onRenderListeners;
    
    public ClickGuiNew() {
        this.onRenderListeners = new ArrayList<ActionEventListener>();
        this.categoryPaneMap = new HashMap<Category, Pane>();
        this.renderer = new ClientBaseRendererImpl();
        this.spoilerPane = new Pane(this.renderer, new GridLayout(1));
        this.window = new Window(Kisman.getName(), 50, 50, 920, 420);
        final Pane conentPane = new ScrollPane(this.renderer, new GridLayout(1));
        final Pane buttonPane = new Pane(this.renderer, new FlowLayout());
        final HashMap<Category, Pane> paneMap = new HashMap<Category, Pane>();
        final List<Spoiler> spoilers = new ArrayList<Spoiler>();
        final List<Pane> paneList = new ArrayList<Pane>();
        for (final Category cat : Category.values()) {
            final Pane spoilerPane = new Pane(this.renderer, new GridLayout(1));
            final Button button = new Button(this.renderer, cat.name());
            buttonPane.addComponent(button);
            button.setOnClickListener(() -> this.setCurrentCategory(cat));
            for (final Module module : Kisman.instance.moduleManager.getModulesInCategory(cat)) {
                final Pane settingPane = new Pane(this.renderer, new GridLayout(4));
                settingPane.addComponent(new Label(this.renderer, "Toggle"));
                CheckBox cb = new CheckBox(this.renderer, "Toggled");
                settingPane.addComponent(cb);
                this.onRenderListeners.add(() -> cb.setSelected(module.isToggled()));
                cb.setListener(val -> {
                    module.setToggled((boolean)val);
                    return true;
                });
                settingPane.addComponent(new Label(this.renderer, "Keybind"));
                final KeybindButton kb = new KeybindButton(this.renderer, Keyboard::getKeyName);
                settingPane.addComponent(kb);
                this.onRenderListeners.add(() -> kb.setValue(module.getKey()));
                kb.setListener(val -> {
                    module.setKey((int)val);
                    return true;
                });
                settingPane.addComponent(new Label(this.renderer, "Visible"));
                cb = new CheckBox(this.renderer, "Visibled");
                settingPane.addComponent(cb);
                final CheckBox cb2;
                this.onRenderListeners.add(() -> cb2.setSelected(module.visible));
                cb.setListener(val -> {
                    module.visible = val;
                    return true;
                });
                if (Kisman.instance.settingsManager.getSettingsByMod(module) != null && !Kisman.instance.settingsManager.getSettingsByMod(module).isEmpty()) {
                    for (final Setting set : Kisman.instance.settingsManager.getSettingsByMod(module)) {
                        if (set.isString()) {
                            settingPane.addComponent(new Label(this.renderer, set.getName()));
                            final StringButton sb = new StringButton(this.renderer, set.getdString());
                            settingPane.addComponent(sb);
                            sb.setListener(val -> {
                                set.setValString(val);
                                return true;
                            });
                            this.onRenderListeners.add(() -> sb.setValue(set.getValString()));
                        }
                        if (set.isCheck()) {
                            settingPane.addComponent(new Label(this.renderer, set.getName()));
                            final CheckBox cb3 = new CheckBox(this.renderer, "Enabled");
                            settingPane.addComponent(cb3);
                            cb3.setListener(val -> {
                                set.setValBoolean(val);
                                return true;
                            });
                            this.onRenderListeners.add(() -> cb3.setSelected(set.getValBoolean()));
                        }
                        if (set.isSlider()) {
                            settingPane.addComponent(new Label(this.renderer, set.getName()));
                            Slider.NumberType type = Slider.NumberType.DECIMAL;
                            switch (set.getNumberType()) {
                                case INTEGER: {
                                    if (set.isOnlyint()) {
                                        type = Slider.NumberType.INTEGER;
                                        break;
                                    }
                                    break;
                                }
                                case PERCENT: {
                                    if (set.getMin() == 0.0 && set.getMax() == 100.0) {
                                        type = Slider.NumberType.PERCENT;
                                        break;
                                    }
                                    break;
                                }
                                case TIME: {
                                    type = Slider.NumberType.TIME;
                                    break;
                                }
                            }
                            final Slider sl = new Slider(this.renderer, set.getValDouble(), set.getMin(), set.getMax(), type);
                            settingPane.addComponent(sl);
                            sl.setListener(val -> {
                                set.setValDouble(val.doubleValue());
                                return true;
                            });
                            this.onRenderListeners.add(() -> sl.setValue(set.getValDouble()));
                        }
                        if (set.isCombo()) {
                            settingPane.addComponent(new Label(this.renderer, set.getName()));
                            final ComboBox cb4 = new ComboBox(this.renderer, set.getStringValues(), set.getSelectedIndex());
                            settingPane.addComponent(cb4);
                            final Setting setting;
                            cb4.setListener(val -> {
                                setting.setValString(setting.getStringFromIndex(val));
                                return true;
                            });
                            this.onRenderListeners.add(() -> cb4.setSelectedIndex(set.getSelectedIndex()));
                        }
                    }
                }
                final Spoiler spoiler = new Spoiler(this.renderer, module.getName(), this.width, settingPane, module);
                paneList.add(settingPane);
                spoilers.add(spoiler);
                spoilerPane.addComponent(spoiler);
                paneMap.put(cat, spoilerPane);
            }
            this.categoryPaneMap.put(cat, spoilerPane);
        }
        conentPane.addComponent(buttonPane);
        int maxWidth = Integer.MIN_VALUE;
        for (final Pane pane : paneList) {
            maxWidth = Math.max(maxWidth, pane.getWidth());
        }
        this.window.setWidth(28 + maxWidth);
        for (final Spoiler spoiler2 : spoilers) {
            spoiler2.setWidth(spoiler2.preferredWidth = maxWidth);
        }
        this.spoilerPane.setWidth(maxWidth);
        buttonPane.setWidth(maxWidth);
        conentPane.addComponent(this.spoilerPane);
        conentPane.updateLayout();
        this.window.setContentPane(conentPane);
        if (this.categoryPaneMap.keySet().size() > 0) {
            this.setCurrentCategory(this.categoryPaneMap.keySet().iterator().next());
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        for (final ActionEventListener onRenderListener : this.onRenderListeners) {
            onRenderListener.onActionEvent();
        }
        final Point point = MathUtil.calculateMouseLocation();
        this.window.mouseMoved(point.x * 2, point.y * 2);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        this.window.render(this.renderer);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.window.mouseMoved(mouseX * 2, mouseY * 2);
        this.window.mousePressed(mouseButton, mouseX * 2, mouseY * 2);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        this.window.mouseMoved(mouseX * 2, mouseY * 2);
        this.window.mouseReleased(state, mouseX * 2, mouseY * 2);
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    protected void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        this.window.mouseMoved(mouseX * 2, mouseY * 2);
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        final int eventDWheel = Mouse.getEventDWheel();
        this.window.mouseWheel(eventDWheel);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode != -1) {
            this.window.keyPressed(keyCode, typedChar);
        }
        else {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        super.keyTyped(typedChar, keyCode);
    }
    
    private void setCurrentCategory(final Category category) {
        this.spoilerPane.clearComponents();
        this.spoilerPane.addComponent(this.categoryPaneMap.get(category));
    }
}
