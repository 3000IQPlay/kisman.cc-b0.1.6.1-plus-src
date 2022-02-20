//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import net.minecraft.item.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class Test extends Module
{
    public ItemStack[] xrayBlocks;
    private Setting testUpdateStringButton;
    
    public Test() {
        super("Test", "", Category.CLIENT);
        this.xrayBlocks = new ItemStack[] { new ItemStack(Blocks.COAL_ORE), new ItemStack(Blocks.IRON_ORE), new ItemStack(Blocks.REDSTONE_ORE), new ItemStack(Blocks.LAPIS_ORE), new ItemStack(Blocks.DIAMOND_ORE) };
        this.testUpdateStringButton = new Setting("TestStringButton", this, "46354", "46354", true).setOnlyNumbers(true);
        Test.setmgr.rSetting(new Setting("TestItemsButton", this, "TestItemsButton", this.xrayBlocks));
        Test.setmgr.rSetting(new Setting("ExampleEntityPreview", this, "Example", (Entity)new EntityEnderCrystal((World)Test.mc.world)));
        Test.setmgr.rSetting(this.testUpdateStringButton);
    }
    
    public void test() {
    }
}
