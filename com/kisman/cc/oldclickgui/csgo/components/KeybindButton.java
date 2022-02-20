//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import java.util.function.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.oldclickgui.csgo.*;
import org.lwjgl.input.*;

public class KeybindButton extends Button
{
    private ValueChangeListener<Integer> listener;
    private boolean listening;
    private Function<Integer, String> keyNameResolver;
    private int value;
    
    public KeybindButton(final IRenderer renderer, final int preferredWidth, final int preferredHeight, final Function<Integer, String> keyNameResolver) {
        super(renderer, "", preferredWidth, preferredHeight);
        this.keyNameResolver = keyNameResolver;
        this.initialize();
    }
    
    public KeybindButton(final IRenderer renderer, final Function<Integer, String> keyNameResolver) {
        super(renderer, "");
        this.keyNameResolver = keyNameResolver;
        this.initialize();
    }
    
    private void initialize() {
        this.setOnClickListener(() -> {
            this.listening = !this.listening;
            this.updateState();
        });
        this.updateState();
    }
    
    public void render() {
        super.render();
        this.renderer.drawString(this.x + this.getWidth() / 2 - this.renderer.getStringWidth(this.title) / 2, this.y + this.getHeight() / 2 - this.renderer.getStringHeight(this.title) / 2, this.title, (Config.instance.guiAstolfo.getValBoolean() && this.listening) ? this.renderer.astolfoColorToObj() : Window.FOREGROUND);
    }
    
    public void setOnClickListener(final ActionEventListener listener) {
        if (this.getOnClickListener() != null) {
            final ActionEventListener old = this.getOnClickListener();
            super.setOnClickListener(() -> {
                listener.onActionEvent();
                old.onActionEvent();
            });
        }
        else {
            super.setOnClickListener(listener);
        }
    }
    
    public boolean keyPressed(final int key, final char c) {
        if (this.listening) {
            this.listening = false;
            if (Keyboard.getEventKey() != 256 && Keyboard.getEventCharacter() != '\0') {
                final int newValue = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + '\u0100') : Keyboard.getEventKey();
                if (this.listener != null && this.listener.onValueChange(newValue)) {
                    this.value = newValue;
                }
            }
            this.updateState();
        }
        return super.keyPressed(key, c);
    }
    
    public int getEventPriority() {
        return this.listening ? (super.getEventPriority() + 1) : super.getEventPriority();
    }
    
    private void updateState() {
        if (this.listening) {
            this.setTitle("Press a button...");
        }
        else {
            this.setTitle((String)this.keyNameResolver.apply(this.value));
        }
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(final int value) {
        this.value = value;
        this.updateState();
    }
    
    public Function<Integer, String> getKeyNameResolver() {
        return this.keyNameResolver;
    }
    
    public void setKeyNameResolver(final Function<Integer, String> keyNameResolver) {
        this.keyNameResolver = keyNameResolver;
    }
    
    public void setListener(final ValueChangeListener<Integer> listener) {
        this.listener = listener;
    }
}
