//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.xml;

public class Item
{
    protected String name;
    protected int condition;
    
    public void dump(final String prefix) {
        System.out.println(prefix + "Item " + this.name + "," + this.condition);
    }
}
