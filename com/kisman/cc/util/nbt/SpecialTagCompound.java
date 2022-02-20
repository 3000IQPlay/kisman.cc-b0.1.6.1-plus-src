//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.nbt;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class SpecialTagCompound extends NBTTagCompound
{
    private boolean empty;
    private int true_damage;
    
    public static int getStackDamage(final ItemStack stack) {
        final NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag instanceof SpecialTagCompound) {
            return ((SpecialTagCompound)tag).getTrueDamage();
        }
        return stack.getItemDamage();
    }
    
    public SpecialTagCompound(final boolean empty, final int true_damage) {
        this.empty = empty;
        this.true_damage = true_damage;
    }
    
    public SpecialTagCompound(final NBTTagCompound old, final int true_damage) {
        if (old == null) {
            this.empty = true;
        }
        else {
            for (final String key : old.getKeySet()) {
                super.setTag(key, old.getTag(key));
            }
        }
        this.true_damage = true_damage;
    }
    
    public int getTrueDamage() {
        return this.true_damage;
    }
    
    public byte getId() {
        if (this.empty) {
            return 0;
        }
        return super.getId();
    }
    
    public NBTTagCompound copy() {
        final NBTTagCompound copy = new SpecialTagCompound(this.empty, this.true_damage);
        for (final String s : this.getKeySet()) {
            ((SpecialTagCompound)copy).setTagLegacy(s, this.getTag(s).copy());
        }
        return copy;
    }
    
    public boolean isEmpty() {
        if (super.isEmpty()) {
            this.empty = true;
        }
        return false;
    }
    
    public void setTag(final String key, final NBTBase value) {
        this.empty = false;
        super.setTag(key, value);
    }
    
    public void setTagLegacy(final String key, final NBTBase value) {
        super.setTag(key, value);
    }
    
    public void setInteger(final String key, final int value) {
        this.empty = false;
        super.setInteger(key, value);
    }
    
    public void setByte(final String key, final byte value) {
        this.empty = false;
        super.setByte(key, value);
    }
    
    public void setShort(final String key, final short value) {
        this.empty = false;
        super.setShort(key, value);
    }
    
    public void setLong(final String key, final long value) {
        this.empty = false;
        super.setLong(key, value);
    }
    
    public void setUniqueId(final String key, final UUID value) {
        this.empty = false;
        super.setUniqueId(key, value);
    }
    
    public void setFloat(final String key, final float value) {
        this.empty = false;
        super.setFloat(key, value);
    }
    
    public void setDouble(final String key, final double value) {
        this.empty = false;
        super.setDouble(key, value);
    }
    
    public void setString(final String key, final String value) {
        this.empty = false;
        super.setString(key, value);
    }
    
    public void setByteArray(final String key, final byte[] value) {
        this.empty = false;
        super.setByteArray(key, value);
    }
    
    public void setIntArray(final String key, final int[] value) {
        this.empty = false;
        super.setIntArray(key, value);
    }
}
