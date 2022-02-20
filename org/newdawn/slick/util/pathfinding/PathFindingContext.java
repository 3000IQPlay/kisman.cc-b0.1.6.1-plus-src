//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util.pathfinding;

public interface PathFindingContext
{
    Mover getMover();
    
    int getSourceX();
    
    int getSourceY();
    
    int getSearchDistance();
}
