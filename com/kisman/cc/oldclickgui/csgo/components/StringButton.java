//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;

public class StringButton extends Button
{
    private ValueChangeListener<String> listener;
    private boolean listening;
    private String value;
    private String dString;
    
    public StringButton(final IRenderer renderer, final int preferredWidth, final int preferredHeight, final String dString) {
        super(renderer, "", preferredWidth, preferredHeight);
        this.dString = dString;
        this.initialize();
    }
    
    public StringButton(final IRenderer renderer, final String dString) {
        super(renderer, "");
        this.dString = dString;
        this.initialize();
    }
    
    private void initialize() {
        this.setOnClickListener(() -> {
            this.listening = !this.listening;
            this.updateState();
        });
        this.updateState();
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
            if (Keyboard.getEventKey() == 1) {
                return super.keyPressed(key, c);
            }
            if (28 == Keyboard.getEventKey()) {
                this.enterString();
            }
            else if (Keyboard.getEventKey() == 14) {
                if (!this.value.isEmpty()) {
                    this.value = this.value.substring(0, this.value.length() - 1);
                }
            }
            else if (ChatAllowedCharacters.isAllowedCharacter(Keyboard.getEventCharacter())) {
                this.value += Keyboard.getEventCharacter();
            }
            this.updateState();
        }
        return super.keyPressed(key, c);
    }
    
    private void enterString() {
        this.listening = false;
        if (this.value.isEmpty()) {
            this.listener.onValueChange(this.dString);
            this.setValue(this.dString);
        }
        else {
            this.listener.onValueChange(this.value);
            this.setValue(this.value);
        }
    }
    
    public int getEventPriority() {
        return this.listening ? (super.getEventPriority() + 1) : super.getEventPriority();
    }
    
    private void updateState() {
        this.setTitle(this.value + (this.listening ? "_" : ""));
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
        this.updateState();
    }
    
    public void setListener(final ValueChangeListener<String> listener) {
        this.listener = listener;
    }
}
