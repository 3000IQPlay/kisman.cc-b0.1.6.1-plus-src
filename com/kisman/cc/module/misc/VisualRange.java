//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.module.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import i.gishreloaded.gishcode.utils.visual.*;
import java.util.*;

public class VisualRange extends Module
{
    private ArrayList<String> names;
    private ArrayList<String> newnames;
    
    public VisualRange() {
        super("VisualRange", "", Category.MISC);
        this.names = new ArrayList<String>();
        this.newnames = new ArrayList<String>();
    }
    
    @Override
    public void update() {
        this.newnames.clear();
        try {
            for (final Entity entity : VisualRange.mc.world.loadedEntityList) {
                if (entity instanceof EntityPlayer && !entity.getName().equalsIgnoreCase(VisualRange.mc.player.getName())) {
                    this.newnames.add(entity.getName());
                }
            }
            if (!this.names.equals(this.newnames)) {
                for (final String name : this.newnames) {
                    if (!this.names.contains(name)) {
                        final String msg = name + " entered visual range!";
                        ChatUtils.warning((Object)msg);
                    }
                }
                for (final String name : this.names) {
                    if (!this.newnames.contains(name)) {
                        final String msg = name + " left visual range!";
                        ChatUtils.message((Object)msg);
                    }
                }
                this.names.clear();
                for (final String name : this.newnames) {
                    this.names.add(name);
                }
            }
        }
        catch (Exception ex) {}
    }
}
