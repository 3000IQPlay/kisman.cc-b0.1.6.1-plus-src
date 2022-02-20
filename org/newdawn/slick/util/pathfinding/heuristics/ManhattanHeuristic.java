//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.*;

public class ManhattanHeuristic implements AStarHeuristic
{
    private int minimumCost;
    
    public ManhattanHeuristic(final int minimumCost) {
        this.minimumCost = minimumCost;
    }
    
    public float getCost(final TileBasedMap map, final Mover mover, final int x, final int y, final int tx, final int ty) {
        return (float)(this.minimumCost * (Math.abs(x - tx) + Math.abs(y - ty)));
    }
}
