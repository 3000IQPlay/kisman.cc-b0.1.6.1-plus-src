//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.layout.*;
import com.kisman.cc.oldclickgui.csgo.*;
import java.util.*;

public class Pane extends AbstractComponent
{
    protected List<AbstractComponent> components;
    protected Map<AbstractComponent, int[]> componentLocations;
    protected Layout layout;
    private final ILayoutManager layoutManager;
    
    public Pane(final IRenderer renderer, final ILayoutManager layoutManager) {
        super(renderer);
        this.components = new ArrayList<AbstractComponent>();
        this.componentLocations = new HashMap<AbstractComponent, int[]>();
        this.layoutManager = layoutManager;
    }
    
    public void render() {
        if (this.isSizeChanged()) {
            this.updateLayout();
            this.resetSizeChanged();
        }
        this.updateComponentLocation();
        for (final AbstractComponent component : this.components) {
            component.render();
        }
    }
    
    public boolean isSizeChanged() {
        for (final AbstractComponent component : this.components) {
            if (component.isSizeChanged()) {
                return true;
            }
        }
        return super.isSizeChanged();
    }
    
    private void resetSizeChanged() {
        for (final AbstractComponent component : this.components) {
            component.setSizeChanged(false);
        }
    }
    
    protected void updateComponentLocation() {
        for (final AbstractComponent component : this.components) {
            final int[] ints = this.componentLocations.get(component);
            if (ints == null) {
                this.updateLayout();
                this.updateComponentLocation();
                return;
            }
            component.setX(this.getX() + ints[0]);
            component.setY(this.getY() + ints[1]);
        }
    }
    
    public void updateLayout() {
        this.updateLayout(this.getWidth(), this.getHeight(), true);
    }
    
    protected void updateLayout(final int width, final int height, final boolean changeHeight) {
        this.layout = this.layoutManager.buildLayout(this.components, width, height);
        this.componentLocations = this.layout.getComponentLocations();
        if (changeHeight) {
            this.setHeight(this.layout.getMaxHeight());
        }
    }
    
    public void addComponent(final AbstractComponent component) {
        this.components.add(component);
        this.updateLayout(super.getWidth(), super.getHeight(), true);
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        final boolean[] consumed = { false };
        final Object o;
        this.components.stream().sorted(Comparator.comparingInt(AbstractComponent::getEventPriority)).forEach(component -> {
            if (!o[0] && component.mouseMove(x, y, offscreen)) {
                o[0] = true;
            }
            return;
        });
        return consumed[0];
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        final boolean[] consumed = { false };
        final Object o;
        this.components.stream().sorted(Comparator.comparingInt(AbstractComponent::getEventPriority)).forEach(component -> {
            if (!o[0] && component.mousePressed(button, x, y, offscreen)) {
                o[0] = true;
            }
            return;
        });
        return consumed[0];
    }
    
    public boolean mouseReleased(final int button, final int x, final int y, final boolean offscreen) {
        final boolean[] consumed = { false };
        final Object o;
        this.components.stream().sorted(Comparator.comparingInt(AbstractComponent::getEventPriority)).forEach(component -> {
            if (!o[0] && component.mouseReleased(button, x, y, offscreen)) {
                o[0] = true;
            }
            return;
        });
        return consumed[0];
    }
    
    public boolean keyPressed(final int key, final char c) {
        final boolean[] consumed = { false };
        final Object o;
        this.components.stream().sorted(Comparator.comparingInt(AbstractComponent::getEventPriority)).forEach(component -> {
            if (!o[0] && component.keyPressed(key, c)) {
                o[0] = true;
            }
            return;
        });
        return consumed[0];
    }
    
    public int getWidth() {
        if (super.getWidth() <= 0) {
            this.updateSize();
        }
        return super.getWidth();
    }
    
    public int getHeight() {
        if (super.getHeight() <= 0) {
            this.updateSize();
        }
        return super.getHeight();
    }
    
    private void updateSize() {
        for (final AbstractComponent component : this.components) {
            if (component instanceof Pane) {
                ((Pane)component).updateSize();
            }
        }
        final int[] optimalDiemension = this.layoutManager.getOptimalDiemension(this.components, Integer.MAX_VALUE);
        if (super.getWidth() <= 0) {
            this.setWidth(optimalDiemension[0]);
        }
        if (super.getHeight() <= 0) {
            this.setHeight(optimalDiemension[1]);
        }
    }
    
    public void clearComponents() {
        this.components.clear();
        this.updateLayout(super.getWidth(), super.getHeight(), true);
    }
}
