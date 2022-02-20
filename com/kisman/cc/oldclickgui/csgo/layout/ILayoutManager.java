//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.layout;

import java.util.*;
import com.kisman.cc.oldclickgui.csgo.*;

public interface ILayoutManager
{
    int[] getOptimalDiemension(final List<AbstractComponent> p0, final int p1);
    
    Layout buildLayout(final List<AbstractComponent> p0, final int p1, final int p2);
}
