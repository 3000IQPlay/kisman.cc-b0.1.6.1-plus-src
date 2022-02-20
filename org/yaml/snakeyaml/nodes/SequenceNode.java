//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.nodes;

import org.yaml.snakeyaml.error.*;
import java.util.*;

public class SequenceNode extends CollectionNode<Node>
{
    private final List<Node> value;
    
    public SequenceNode(final Tag tag, final boolean resolved, final List<Node> value, final Mark startMark, final Mark endMark, final Boolean flowStyle) {
        super(tag, startMark, endMark, flowStyle);
        if (value == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = value;
        this.resolved = resolved;
    }
    
    public SequenceNode(final Tag tag, final List<Node> value, final Boolean flowStyle) {
        this(tag, true, value, null, null, flowStyle);
    }
    
    public NodeId getNodeId() {
        return NodeId.sequence;
    }
    
    public List<Node> getValue() {
        return this.value;
    }
    
    public void setListType(final Class<?> listType) {
        for (final Node node : this.value) {
            node.setType((Class)listType);
        }
    }
    
    public String toString() {
        return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", value=" + this.getValue() + ")>";
    }
}
