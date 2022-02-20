//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import java.util.concurrent.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class BurrowCounter extends Module
{
    private final ConcurrentHashMap<EntityPlayer, Integer> players;
    List<EntityPlayer> anti_spam;
    private boolean flag;
    
    public BurrowCounter() {
        super("BurrowCounter", "BurrowCounter", Category.MISC);
        this.players = new ConcurrentHashMap<EntityPlayer, Integer>();
        this.anti_spam = new ArrayList<EntityPlayer>();
    }
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void update() {
        if (BurrowCounter.mc.player == null && BurrowCounter.mc.world == null) {
            return;
        }
        for (final EntityPlayer player : BurrowCounter.mc.world.playerEntities) {
            final BlockPos position = new BlockPos(player.posX, player.posY + 0.2, player.posZ);
            if (BurrowCounter.mc.world.getBlockState(position).getBlock().equals(Blocks.OBSIDIAN) && !this.flag) {
                if (this.anti_spam.contains(player)) {
                    continue;
                }
                this.add_player(player);
                this.anti_spam.add(player);
            }
            else {
                if (BurrowCounter.mc.world.getBlockState(position).getBlock().equals(Blocks.OBSIDIAN)) {
                    continue;
                }
                this.anti_spam.remove(player);
            }
        }
    }
    
    private void add_player(final EntityPlayer player) {
        if (player == null) {
            return;
        }
        if (this.players.containsKey(player)) {
            final int value = this.players.get(player) + 1;
            this.players.put(player, value);
            ChatUtils.warning((Object)(player.getName() + TextFormatting.DARK_RED + " has burrowed " + value + " times"));
        }
        else {
            this.players.put(player, 1);
            ChatUtils.warning((Object)(player.getName() + TextFormatting.DARK_RED + " has burrowed"));
        }
    }
}
