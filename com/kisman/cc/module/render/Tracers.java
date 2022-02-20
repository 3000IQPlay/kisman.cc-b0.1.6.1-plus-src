//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.friend.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Tracers extends Module
{
    private Setting players;
    private Setting playersColor;
    private Setting playersAstolfo;
    private Setting friends;
    private Setting friendsColor;
    private Setting friendsAstolfo;
    private Setting items;
    private Setting itemsColor;
    private Setting itemsAstolfo;
    
    public Tracers() {
        super("Tracers", "Tracers", Category.RENDER);
        this.players = new Setting("Players", this, false);
        this.playersColor = new Setting("PlayersColor", this, "Color", new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
        this.playersAstolfo = new Setting("PlayersAstolfo", this, false);
        this.friends = new Setting("Friends", this, false);
        this.friendsColor = new Setting("friendsColor", this, "Color", new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
        this.friendsAstolfo = new Setting("FriendsAstolfo", this, true);
        this.items = new Setting("Items", this, false);
        this.itemsColor = new Setting("ItemsColor", this, "Color", new float[] { 1.0f, 1.0f, 1.0f, 1.0f });
        this.itemsAstolfo = new Setting("ItemsAstolfo", this, true);
        Tracers.setmgr.rSetting(this.players);
        Tracers.setmgr.rSetting(this.playersColor);
        Tracers.setmgr.rSetting(this.playersAstolfo);
        Tracers.setmgr.rSetting(this.friends);
        Tracers.setmgr.rSetting(this.friendsColor);
        Tracers.setmgr.rSetting(this.friendsAstolfo);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        for (final Entity entity : Tracers.mc.world.loadedEntityList) {
            if (entity == Tracers.mc.player) {
                continue;
            }
            if (entity instanceof EntityPlayer) {
                if (FriendManager.instance.isFriend((EntityPlayer)entity)) {
                    if (!this.friends.getValBoolean()) {
                        continue;
                    }
                    RenderUtil.drawTracer(entity, this.friendsAstolfo.getValBoolean() ? new Colour(this.friendsColor.getR() / 255.0f, this.friendsColor.getG() / 255.0f, this.friendsColor.getB() / 255.0f, this.friendsColor.getA() / 255.0f) : new Colour(ColorUtils.astolfoColors(100, 100)), event.getPartialTicks());
                }
                else {
                    if (!this.players.getValBoolean()) {
                        continue;
                    }
                    RenderUtil.drawTracer(entity, this.playersAstolfo.getValBoolean() ? new Colour(this.playersColor.getR() / 255.0f, this.playersColor.getG() / 255.0f, this.playersColor.getB() / 255.0f, this.friendsColor.getA() / 255.0f) : new Colour(ColorUtils.astolfoColors(100, 100)), event.getPartialTicks());
                }
            }
            else {
                if (!(entity instanceof EntityItem) || !this.items.getValBoolean()) {
                    continue;
                }
                RenderUtil.drawTracer(entity, this.itemsAstolfo.getValBoolean() ? new Colour(this.itemsColor.getR() / 255.0f, this.itemsColor.getG() / 255.0f, this.itemsColor.getB() / 255.0f, this.itemsColor.getA() / 255.0f) : new Colour(ColorUtils.astolfoColors(100, 100)), event.getPartialTicks());
            }
        }
    }
}
