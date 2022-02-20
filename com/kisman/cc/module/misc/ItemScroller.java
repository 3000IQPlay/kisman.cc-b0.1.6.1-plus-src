//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;

public class ItemScroller extends Module
{
    public static ItemScroller instance;
    public Setting scrollSpeed;
    
    public ItemScroller() {
        super("ItemScroller", "", Category.MISC);
        this.scrollSpeed = new Setting("ScrollSpeed", this, 20.0, 1.0, 100.0, true);
        ItemScroller.instance = this;
        ItemScroller.setmgr.rSetting(this.scrollSpeed);
    }
}
