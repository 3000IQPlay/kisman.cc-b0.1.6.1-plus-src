//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util.pathfinding;

public interface TileBasedMap
{
    int getWidthInTiles();
    
    int getHeightInTiles();
    
    void pathFinderVisited(final int p0, final int p1);
    
    boolean blocked(final PathFindingContext p0, final int p1, final int p2);
    
    float getCost(final PathFindingContext p0, final int p1, final int p2);
}
