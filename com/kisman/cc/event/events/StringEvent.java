//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;
import com.kisman.cc.settings.*;

public class StringEvent extends Event
{
    public Setting set;
    public String str;
    public Event.Era era;
    public boolean active;
    
    public StringEvent(final Setting set, final String str, final Event.Era era, final boolean active) {
        this.set = set;
        this.str = str;
        this.era = era;
        this.active = active;
    }
}
