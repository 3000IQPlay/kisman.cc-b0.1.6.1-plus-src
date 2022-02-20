//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.events;

import org.yaml.snakeyaml.error.*;

public final class SequenceStartEvent extends CollectionStartEvent
{
    public SequenceStartEvent(final String anchor, final String tag, final boolean implicit, final Mark startMark, final Mark endMark, final Boolean flowStyle) {
        super(anchor, tag, implicit, startMark, endMark, flowStyle);
    }
    
    public boolean is(final Event.ID id) {
        return Event.ID.SequenceStart == id;
    }
}
