//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.nodes;

import org.yaml.snakeyaml.error.*;
import java.util.*;

public abstract class CollectionNode<T> extends Node
{
    private Boolean flowStyle;
    
    public CollectionNode(final Tag tag, final Mark startMark, final Mark endMark, final Boolean flowStyle) {
        super(tag, startMark, endMark);
        this.flowStyle = flowStyle;
    }
    
    public abstract List<T> getValue();
    
    public Boolean getFlowStyle() {
        return this.flowStyle;
    }
    
    public void setFlowStyle(final Boolean flowStyle) {
        this.flowStyle = flowStyle;
    }
    
    public void setEndMark(final Mark endMark) {
        this.endMark = endMark;
    }
}
